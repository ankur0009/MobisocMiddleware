package services;
/**
 * Gloabal Data
 */
import globalData.*;
/**
 * Basic utilitites
 */
import java.sql.Timestamp;
import java.util.*;
/**
 * KSOAP data objects
 */
import ksoapDataObjects.common.KVMAccessPointInfo;
import ksoapDataObjects.common.KVMAlerts;
import ksoapDataObjects.common.KVMCBTrigger;
import ksoapDataObjects.common.KVMCoordinates;
import ksoapDataObjects.common.KVMEvent;
import ksoapDataObjects.common.KVMEventData;
import ksoapDataObjects.common.KVMLocationData;
import ksoapDataObjects.common.KVMPlaceData;
import ksoapDataObjects.common.KVMSocialEvent;
import ksoapDataObjects.common.KVMSocialGroup;
import ksoapDataObjects.common.KVMUser;
import ksoapDataObjects.privacyService.KVMAccessControlMgrRow;
import ksoapDataObjects.privacyService.KVMAccessRestrictionsRow;
import ksoapDataObjects.privacyService.KVMActionToTakeRow;
import ksoapDataObjects.privacyService.KVMHideInformationRow;
import ksoapDataObjects.privacyService.KVMReturnAccessInfo;
import ksoapDataObjects.privacyService.KVMStatementData;

/**
 * General data objects
 */
import dataObjects.dataCollection.*;
import dataObjects.common.*;
/**
 * KSOAP library
 */
import org.ksoap.ClassMap;
import org.ksoap.SoapObject;
import org.ksoap.transport.HttpTransportSE;
/**
 * Database connectivity 
 */
import edu.njit.sc.core.dao.SmartCampusDAO;
import core.data.dao.CoreDaoException;
import core.util.log.Log;
/**
 * Post GIS
 */
import org.postgis.PGgeometry;
import org.postgis.Geometry;
import org.postgis.Point;


/**
 * This service provides the API for (Data Collection) as described in the MobiSoc paper/documentation.
 * Due to restrictions imposed by KSOAP on the type of dataobects that can be exchanged, for ceratin methods
 * we provide two versions of the API. One that works with KSOAP and the other one works generally. If we do
 * not provide a specific KSOAP version, then the method works with KSOAP as well as in the general case
 * @author Maverick
 */
public class DataCollectionService {
	private ClassMap classMap; //map for the ksoap data object
	private String connectString; 
	
	/**
	 * The contructor sets the connection paprameters to connect to the servr for SOAP calls.
	 * It also sets the classmap for SOAP based communication. 
	 */
	public DataCollectionService()
	{
		/**
		 * get the connection string
		 */
		connectString = ConnectionParameters.connectString;
		/**
		 * set classmap for KSOAP based communication
		 */
		classMap = new ClassMap ();
        classMap.addMapping("soapservice", "KVMCoordinates", new KVMCoordinates().getClass());
        classMap.addMapping("soapservice", "KVMCBTrigger", new KVMCBTrigger().getClass());
        classMap.addMapping("soapservice", "KVMEvent", new KVMEvent().getClass());
        classMap.addMapping("soapservice", "KVMAlerts", new KVMAlerts().getClass());
        classMap.addMapping("soapservice", "KVMEventData", new KVMEventData().getClass());        
        classMap.addMapping("soapservice", "KVMAccessPointInfo", new KVMAccessPointInfo().getClass());
        classMap.addMapping("soapservice", "KVMLocationData", new KVMLocationData().getClass());
        classMap.addMapping("soapservice", "KVMAccessControlMgrRow", new KVMAccessControlMgrRow().getClass());
        classMap.addMapping("soapservice", "KVMAccessRestrictionsRow", new KVMAccessRestrictionsRow().getClass());
        classMap.addMapping("soapservice", "KVMActionToTakeRow", new KVMActionToTakeRow().getClass());
        classMap.addMapping("soapservice", "KVMHideInformationRow", new KVMHideInformationRow().getClass());
        classMap.addMapping("soapservice", "KVMReturnAccessInfo", new KVMReturnAccessInfo().getClass());
        classMap.addMapping("soapservice", "KVMStatementData", new KVMStatementData().getClass());
	}
	
	/**
	 * **************************************************************************************************
	 * **************************************************************************************************
	 * **************************************************************************************************
	 * *********************************Start Of the People API*************************
	 * **************************************************************************************************
	 * **************************************************************************************************
	 * **************************************************************************************************
	 */
	
	/**
	 * This function is used to add a new social group member
	 * @param group_id
	 *  	Group Id for the event whos member is added
	 * @param user_id
	 * 		User Id for the user who is to be added
	 * @return
	 * 		Boolean object that represents success or failure of the operation
	 * @author Maverick
	 */
	public Boolean addSocialGroupMember(Long group_id, Long user_id)
	{
		String fbInsert = "INSERT INTO facebook_group_membership (gid, uid) " +
		        		  "VALUES(?,?)";
		
		// Array of parameter values (primtives should wrapped in objects)
		Object[] params = new Object[] { 
				new Long(group_id),
				new Long(user_id)
		};
		
		try{
			int rowsUpdated = new SmartCampusDAO().update(fbInsert, params);
		}catch(Exception e)
		{
		e.printStackTrace();
		System.out.println("Error while adding new social group member");
		return false;
		}		
		
		return true;
	}
	
	
	
	
	
	/**
	 * 
	 * This is KSOAP compatibile wrapper function used to create a new social group
	 * @param kvmNewSocialGroup
	 * 		Details of the new group inside a KVMSocialGroup object 
	 * @return
	 * 		Boolean object representing the success or failure of
	 * 		the operation.
	 * @author Maverick
	 */
	public Boolean createSocialGroup(KVMSocialGroup kvmNewSocialGroup)
	{
		//create a regular social group object from the ksoap object
		SocialGroup regularNewSocialGroup = new SocialGroup();
		//transfer data
		if(kvmNewSocialGroup.getDescription() != null)
		{
			regularNewSocialGroup.setDescription(kvmNewSocialGroup.getDescription());
		}
		
		if(kvmNewSocialGroup.getGid() != null)
		{
			regularNewSocialGroup.setGid(kvmNewSocialGroup.getGid());
		}
		
		if(kvmNewSocialGroup.getGroup_type() != null)
		{
			regularNewSocialGroup.setGroup_type(kvmNewSocialGroup.getGroup_type());
		}
		
		if(kvmNewSocialGroup.getLocation() != null)
		{
			regularNewSocialGroup.setLocation(kvmNewSocialGroup.getLocation());
		}
		
		if(kvmNewSocialGroup.getName() != null)
		{
			regularNewSocialGroup.setName(kvmNewSocialGroup.getName());
		}
		
		if(kvmNewSocialGroup.getOwner() != null)
		{
			regularNewSocialGroup.setOwner(kvmNewSocialGroup.getOwner());	
		}
		
		if(kvmNewSocialGroup.getRecent_news() != null)
		{
			regularNewSocialGroup.setRecent_news(kvmNewSocialGroup.getRecent_news());
		}
		
		if(kvmNewSocialGroup.getUpdate_time() != null)
		{
			regularNewSocialGroup.setUpdate_time(new Timestamp(kvmNewSocialGroup.getUpdate_time()));
		}
		
		if(kvmNewSocialGroup.getWebsite() != null)
		{
			regularNewSocialGroup.setWebsite(kvmNewSocialGroup.getWebsite());
		}
		
		//call the regular method
		return createSocialGroup(regularNewSocialGroup);
	}
	
	
	
	/**
	 * This API is used to create a new social group
	 * @param newSocialGroup
	 * 		Details of the new group inside a SocialGroup object 
	 * @return
	 * 		Boolean object representing the success or failure of
	 * 		the operation.
	 * @author Maverick
	 */
	public Boolean createSocialGroup(SocialGroup newSocialGroup)
	{
		String fbInsert = "INSERT INTO facebook_group (name, description, group_type, recent_news, owner, update_time, location, website)" +
			              " VALUES(?,?,?,?,?,?,?,?)";

		// Array of parameter values (primtives should wrapped in objects)
		Object[] params = new Object[] { 
				newSocialGroup.getName(), 
				newSocialGroup.getDescription(),
				newSocialGroup.getGroup_type(),
				newSocialGroup.getRecent_news(),
				new Long(newSocialGroup.getOwner()),
				newSocialGroup.getUpdate_time(),
				new Long(newSocialGroup.getLocation()),
				newSocialGroup.getWebsite()
		};
		
		try{
			int rowsUpdated = new SmartCampusDAO().update(fbInsert, params);
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Error while creating new social group");
			return false;
		}		
		return true;
	}
	
	
	
	/**
	 * This API is used to create a new user acount
	 * @param username
	 * 		User name for the new user
	 * @param password
	 * 		Password for the new user
	 * @param email
	 * 		Email address for the new user
	 * @param type
	 * 		Type for the new user
	 * @return
	 * 		A String describing the results of the insert opeation. 
	 * 		Specifically, the username or email may already be there
	 * 		in the database or the password length might not be sufficient.
	 * @author Maverick
	 */
	public String createAccount(String username, String password, String email, String type) {
		if (CheckUserExist(username)) {
			return "User Already Exists";
		}

		if (CheckUserEmailExist(email)) {
			return "User Email Already Exists";
		}

		if (password.length() < 6) {
			return "Password must be atleast 6 characters";
		}

		// Array of parameter values (primtives should wrapped in objects)
		Object[] params = new Object[] { username, // username
				password, // password
				new Integer(0), // status
				email, // email
				type // type
		};
		
		String fofInsert = "INSERT INTO fof_user (username,password, status,email,type)   VALUES(?,?,?,?,?)";

		try{
			int rowsUpdated = new SmartCampusDAO().update(fofInsert, params);
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Error while creating account");
			return "Error while creating account";
		}
		
		return "User Created";
	}
	
	
	
	
	/**
	 * This is KSOAP wrapper for API to update user profile data
	 * @param kvmUserData
	 * 		KVMUserData object containing data about the user
	 * @return
	 * 		Boolean value indicating if the update was succesful or not
	 * @author Maverick
	 */
	public Boolean updateProfile(KVMUser kvmUserData)
	{
		//transfer data from KVM object to resular object
		User regularUserData = new User();
		if(kvmUserData.getAbout_me() != null)
		{
			regularUserData.setAbout_me(kvmUserData.getAbout_me());
		}
		
		if(kvmUserData.getActivities() != null)
		{
			regularUserData.setActivities(kvmUserData.getActivities());
		}
		
		if(kvmUserData.getBirthday() != null)
		{
			regularUserData.setBirthday(kvmUserData.getBirthday());
		}
		
		if(kvmUserData.getBooks() != null)
		{
			regularUserData.setBooks(kvmUserData.getBooks());
		}
		
		if(kvmUserData.getFid() != null)
		{
			regularUserData.setFid(kvmUserData.getFid());
		}
		
		if(kvmUserData.getFirst_name() != null)
		{
			regularUserData.setFirst_name(kvmUserData.getFirst_name());
		}
		
		if(kvmUserData.getGid() != null)
		{
			regularUserData.setGid(kvmUserData.getGid());
		}
		
		if(kvmUserData.getHas_added_app() != null)
		{
			regularUserData.setHas_added_app(kvmUserData.getHas_added_app());
		}
		
		if(kvmUserData.getInterests() != null)
		{
			regularUserData.setInterests(kvmUserData.getInterests());
		}
		
		if(kvmUserData.getIs_app_user() != null)
		{
			regularUserData.setIs_app_user(kvmUserData.getIs_app_user());
		}
		
		if(kvmUserData.getLast_name() != null)
		{
			regularUserData.setLast_name(kvmUserData.getLast_name());
		}
		
		if(kvmUserData.getLastupdated() != null)
		{
			regularUserData.setLastupdated(new Timestamp(kvmUserData.getLastupdated()));
		}
		
		if(kvmUserData.getMeeting_for() != null)
		{
			regularUserData.setMeeting_for(kvmUserData.getMeeting_for());
		}
		
		if(kvmUserData.getMeeting_sex() != null)
		{
			regularUserData.setMeeting_sex(kvmUserData.getMeeting_sex());
		}
		
		if(kvmUserData.getMovies() != null)
		{
			regularUserData.setMovies(kvmUserData.getMovies());
		}
		
		if(kvmUserData.getMusic() != null)
		{
			regularUserData.setMusic(kvmUserData.getMusic());
		}
		
		if(kvmUserData.getName() != null)
		{
			regularUserData.setName(kvmUserData.getName());
		}
		
		if(kvmUserData.getNotes_count() != null)
		{
			regularUserData.setNotes_count(kvmUserData.getNotes_count());
		}
		
		if(kvmUserData.getPic() != null)
		{
			regularUserData.setPic(kvmUserData.getPic());
		}
		
		if(kvmUserData.getPic_big() != null)
		{
			regularUserData.setPic_big(kvmUserData.getPic_big());
		}
		
		if(kvmUserData.getPic_square() != null)
		{
			regularUserData.setPic_square(kvmUserData.getPic_square());
		}
		
		if(kvmUserData.getPolitical() != null)
		{
			regularUserData.setPolitical(kvmUserData.getPolitical());
		}
		
		if(kvmUserData.getProfile_update_time() != null)
		{
			regularUserData.setProfile_update_time(kvmUserData.getProfile_update_time());
		}
		
		if(kvmUserData.getQuotes() != null)
		{
			regularUserData.setQuotes(kvmUserData.getQuotes());
		}
		
		if(kvmUserData.getRelationship_status() != null)
		{
			regularUserData.setRelationship_status(kvmUserData.getRelationship_status());
		}
		
		if(kvmUserData.getReligion() != null)
		{
			regularUserData.setReligion(kvmUserData.getReligion());
		}
		
		if(kvmUserData.getSex() != null)
		{
			regularUserData.setSex(kvmUserData.getSex());
		}
		
		if(kvmUserData.getSignificant_other_id() != null)
		{
			regularUserData.setSignificant_other_id(kvmUserData.getSignificant_other_id());
		}
		
		if(kvmUserData.getStatus() != null)
		{
			regularUserData.setStatus(kvmUserData.getStatus());
		}
		
		if(kvmUserData.getTimezone() != null)
		{
			regularUserData.setTimezone(kvmUserData.getTimezone());
		}
		
		if(kvmUserData.getTv() != null)
		{
			regularUserData.setTv(kvmUserData.getTv());
		}
		
		if(kvmUserData.getUid() != null)
		{
			regularUserData.setUid(kvmUserData.getUid());
		}
		
		if(kvmUserData.getWall_count() != null)
		{
			regularUserData.setWall_count(kvmUserData.getWall_count());
		}
		
		if(kvmUserData.getWork_history() != null)
		{
			regularUserData.setWork_history(kvmUserData.getWork_history());
		}
		
		//insert data using main API
		return updateProfile(regularUserData);
	}
	
	
	/**
	 * This functiion is used to update profile data about a user
	 * @param f
	 * 	(User) object containing data about the user
	 * @return
	 * 	Boolean object indicating if the update was succesful or not
	 * @author Maverick
	 */
	public Boolean updateProfile(User f) {
		String fofInsert = "INSERT INTO facebook_user (uid,about_me,activities,books,first_name,has_added_app,interests,is_app_user,last_name, meeting_for,meeting_sex,movies,music,name,notes_count,political,relationship_status,religion,significant_other_id,sex,status,timezone,tv,wall_count)  VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String fofupdate = "No Update";
		try {
			Object[] params = new Object[] {
					new Long(f.getUid()), // userid1
					f.getAbout_me(), f.getActivities(), f.getBooks(),
					f.getFirst_name(), f.getHas_added_app(), f.getInterests(),
					f.getIs_app_user(), f.getLast_name(), f.getMeeting_for(),
					f.getMeeting_sex(), f.getMovies(), f.getMusic(),
					f.getName(), f.getNotes_count(), f.getPolitical(),
					f.getRelationship_status(), f.getReligion(),
					f.getSignificant_other_id(), f.getSex(), f.getStatus(),
					f.getTimezone(), f.getTv(), f.getWall_count() };
			// Invoke update method on DAO to perform insert
			int rowsUpdated;
			try {
				rowsUpdated = new SmartCampusDAO().update(fofInsert, params);
			} catch (Exception e) {
				// e.printStackTrace();
				String del = "Delete from facebook_user where uid='"
						+ f.getUid() + "'";
				rowsUpdated = new SmartCampusDAO().update(del, null);
				rowsUpdated = new SmartCampusDAO().update(fofInsert, params);
				if (rowsUpdated > 0) {
					return true;
				}
			}
			Log.put("rowsUpdated", "=" + rowsUpdated);
			if (rowsUpdated > 0) {
				return true;
			}

		}

		catch (CoreDaoException e) {
			System.out.println(fofupdate);
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	/**
	 * This function is used to add a social contact. Note that to insert a mutual social 
	 * relationship, this funcion should be called twice with the order of user id's reversed. 
	 * Calling it once will imply that "fid" is a friend of "uid" but not vice a versa 
	 * @param uid
	 *   User Id for the main user
	 * @param fid
	 *   User Id for the friend
	 * @return
	 * 	 Boolean object representing succes or failure of the operation
	 * @author Maverick
	 */
	public Boolean addSocialContact(Long uid, Long fid) {
		Object[] params = new Object[] { new Long(uid), // userid1
				new Long(fid) // userid2
		};
		String fofInsert = "INSERT INTO facebook_friends (uid,fid)  VALUES(?,?)";
		
		int rowsUpdated = 0;
		try {
			// Invoke update method on DAO to perform insert
			rowsUpdated = new SmartCampusDAO().update(fofInsert, params);

			if (rowsUpdated > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error whn adding social contact");
		}
		return false;
	}
	
	
	/**
	 * This function registers an event with the event dispatcher to request a social contact.
	 * The request is generated 20 seconds after it is submitted.
	 * @param reuestingUserId
	 * 		User Id for the user requesting a social contact
	 * @param requestedUserId
	 * 		User Id for the requested social contact
	 * @param serviceName
	 * 		Service that will recieve the request
	 * @return Boolean
	 * 		Boolean object reprsenting success or failure of the operation
	 * @author Maverick
	 */
	public Boolean requestSocialContact(Long requestingUserId, Long requestedUserId, String serviceName)
	{
		//get the user name for requesting user from database
		Object[] params = new Object[] {
				new Long(requestingUserId),
				};
		
	    String query = "SELECT username from fof_user " + 
	    			   "WHERE userid=?";			   
	    
		try{
			user_name[] usernameDB = (user_name[]) new SmartCampusDAO().query(query,params,user_name.class);
			
			
			if(usernameDB.length > 0)
			{
				Vector<Long> targetUser = new Vector<Long>();
				targetUser.add(new Long(requestedUserId));
				
				//register an event to request a social contact
				KVMCBTrigger newTrigger = new KVMCBTrigger();
				newTrigger.setColocationConstraints(false);
				newTrigger.setLocationConstraints(false);
				newTrigger.setTimeConstraints(true);
				newTrigger.setStartTime( new Long(System.currentTimeMillis() + 20000));
				newTrigger.setEndTime(new Long(System.currentTimeMillis() + 40000));
				newTrigger.setDate(new Long(System.currentTimeMillis()));
				newTrigger.setFrequency("onetime");
				
				KVMEvent socialContactRequest = new KVMEvent();
				socialContactRequest.setCBTrigger(newTrigger);
				socialContactRequest.setSourceUser(requestingUserId);
				socialContactRequest.setTargetUsers(targetUser);
				socialContactRequest.setType("SocialContactRequest");
				socialContactRequest.setDescription(usernameDB[0].username +  " has requested to add you as a social contact");
				socialContactRequest.setServiceName(serviceName);
				
				//setup SOAP stuff to call the registerEvent method in the event service
			    SoapObject rpc = new SoapObject("soapservice","registerEvent");
				rpc.addProperty("X", socialContactRequest);
				HttpTransportSE tx = new HttpTransportSE (connectString + "EventService", "registerEvent"); 
				tx.setClassMap (classMap);
				
				//call the registerEvent method in the event service
				tx.call(rpc);		
			}
		}catch(Exception e)
		{
			System.out.println("Error while requesting social contact");
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * This function registers an event with the event dispatcher to request membership
	 * to a social group. The request is generated 20 seconds after it is submitted.
	 * @param reuestingUserId
	 * 		User Id for the user requesting membership
	 * @param requestedUserId
	 * 		User Id for the requested user
	 * @param groupId
	 *      Group Id for the group for which membership is requested
	 * @param serviceName
	 * 		Service that will recieve the request
	 * @return Boolean
	 * 		Boolean object reprsenting success or failure of the operation
	 * @author Maverick
	 */
	public Boolean requestSocialGroupMembership(Long requestingUserId, Long requestedUserId, Long groupId, String serviceName)
	{
		//get the user name for requesting user from database
		Object[] params1 = new Object[] {
				new Long(requestingUserId),
				};
		
		Object[] params2 = new Object[] {
				new Long(groupId),
				};
		
	    String query1 = "SELECT username from fof_user " + 
	    			    "WHERE userid=?";			   
	    String query2 = "SELECT name from facebook_group " + 
		   				"WHERE gid=?"; 
	    
		try{
			user_name[] usernameDB = (user_name[]) new SmartCampusDAO().query(query1,params1,user_name.class);
			group_name[] groupnameDB = (group_name[]) new SmartCampusDAO().query(query2,params2,group_name.class);
			
			if(usernameDB.length > 0 && groupnameDB.length > 0)
			{
				Vector<Long> targetUser = new Vector<Long>();
				targetUser.add(new Long(requestedUserId));
				
				//register an event to request social contact
				KVMCBTrigger newTrigger = new KVMCBTrigger();
				newTrigger.setColocationConstraints(false);
				newTrigger.setLocationConstraints(false);
				newTrigger.setTimeConstraints(true);
				newTrigger.setStartTime( new Long(System.currentTimeMillis() + 20000));
				newTrigger.setEndTime(new Long(System.currentTimeMillis() + 40000));
				newTrigger.setDate(new Long(System.currentTimeMillis()));
				newTrigger.setFrequency("onetime");
				
				KVMEvent socialGroupMembershipRequest = new KVMEvent();
				socialGroupMembershipRequest.setCBTrigger(newTrigger);
				socialGroupMembershipRequest.setSourceUser(requestingUserId);
				socialGroupMembershipRequest.setTargetUsers(targetUser);
				socialGroupMembershipRequest.setType("SocialGroupMembershipRequest");
				socialGroupMembershipRequest.setDescription(usernameDB[0].username +  " has requested you to join the group " + groupnameDB[0].name);
				socialGroupMembershipRequest.setServiceName(serviceName);
				
				//setup SOAP stuff to call the registerEvent method in the event service
			    SoapObject rpc = new SoapObject("soapservice","registerEvent");
				rpc.addProperty("X", socialGroupMembershipRequest);
				HttpTransportSE tx = new HttpTransportSE (connectString + "EventService", "registerEvent"); 
				tx.setClassMap (classMap);
				
				//call the registerEvent method in the event service
				tx.call(rpc);		
			}
		}catch(Exception e)
		{
			System.out.println("Error while requesting social contact");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	/**
	 * **************************************************************************************************
	 * **************************************************************************************************
	 * **************************************************************************************************
	 * *********************************Start Of the Place API*************************
	 * **************************************************************************************************
	 * **************************************************************************************************
	 * **************************************************************************************************
	 */

	
	
	/**
	 * This function is used to set a tag for a place
	 * @param place_id
	 *     Place Id for the place whose tag is to inserted
	 * @param user_id
	 *     User Id for the user inserting the tag
	 * @param tag
	 *     Description of the tag
	 * @return
	 *     Boolean object indicating uccess or failure of the operation
	 * @author Maverick
	 */
	public Boolean setPlaceTag(Long place_id, Long user_id, String tag)
	{
		String fbInsert = "INSERT INTO gis_space_tags (space_id, user_id, description)" +
		  				  " VALUES(?,?,?)";

		// Array of parameter values (primtives should wrapped in objects)
		Object[] params = new Object[] { 
		new Long(place_id),
		new Long(user_id),
		tag
		};
		
		try{
		int rowsUpdated = new SmartCampusDAO().update(fbInsert, params);
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Error while adding new plce tag");
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * This function is used to add a new social event member.
	 * @param event_id
	 * 		Event Id for the event whos member is added
	 * @param user_id
	 * 		User Id for the user who is to be added
	 * @return
	 * 		Boolean object that represents success or failure of the operation
	 * @author Maverick
	 */
	public Boolean addSocialEventMember(Long event_id, Long user_id)
	{
		String fbInsert = "INSERT INTO facebook_event_membership (eid, uid) " +
		        		  "VALUES(?,?)";
		
		// Array of parameter values (primtives should wrapped in objects)
		Object[] params = new Object[] { 
				new Long(event_id),
				new Long(user_id)
		};
		
		try{
			int rowsUpdated = new SmartCampusDAO().update(fbInsert, params);
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Error while adding new social event member");
			return false;
		}		
		
		return true;
	}
	
	
	
	/**
	 * This is KSOAP compatibile wrapper function used to set a new social event.
	 * @param kvmNewSocialEvent
	 *    A KVMSocialEvent object that contains details for the social event
	 * @return
	 * 	  Boolean object that represents success or failure of the operation
	 * @author Maverick
	 */
	public Boolean addSocialEvent(KVMSocialEvent kvmNewSocialEvent)
	{
		//create a new social event object
		SocialEvent regularNewSocialEvent = new SocialEvent();
		//transfer data from kvm object to regular object
		if(kvmNewSocialEvent.getDescription() != null)
		{
			regularNewSocialEvent.setDescription(kvmNewSocialEvent.getDescription());
		}
		
		if(kvmNewSocialEvent.getEid() != null)
		{
			regularNewSocialEvent.setEid(kvmNewSocialEvent.getEid());
		}
		
		if(kvmNewSocialEvent.getEnd_time() != null)
		{
			regularNewSocialEvent.setEnd_time(new Timestamp(kvmNewSocialEvent.getEnd_time()));
		}
		
		if(kvmNewSocialEvent.getEvent_type() != null)
		{
			regularNewSocialEvent.setEvent_type(kvmNewSocialEvent.getEvent_type());
		}
		
		if(kvmNewSocialEvent.getHost() != null)
		{
			regularNewSocialEvent.setHost(kvmNewSocialEvent.getHost());
		}
		if(kvmNewSocialEvent.getLocation() != null)
		{
			regularNewSocialEvent.setLocation(kvmNewSocialEvent.getLocation());
		}
		
		if(kvmNewSocialEvent.getName() != null)
		{
			regularNewSocialEvent.setName(kvmNewSocialEvent.getName());
		}
		
		if(kvmNewSocialEvent.getOwner() != null)
		{
			regularNewSocialEvent.setOwner(kvmNewSocialEvent.getOwner());
		}
		
		if(kvmNewSocialEvent.getStart_time() != null)
		{
			regularNewSocialEvent.setStart_time(new Timestamp(kvmNewSocialEvent.getStart_time()));
		}
		
		if(kvmNewSocialEvent.getUpdate_time() != null)
		{
			regularNewSocialEvent.setUpdate_time(new Timestamp(kvmNewSocialEvent.getUpdate_time()));
		}
		
		//call the rular api
		return addSocialEvent(regularNewSocialEvent);
	}
	
	
	/**
	 * This API is used to create a new social event
	 * @param newSocialEvent
	 *    A SocialEvent object that contains details for the social event
	 * @return
	 * 	  Boolean object that represents success or failure of the operation
	 * @author Maverick
	 */
	public Boolean addSocialEvent(SocialEvent newSocialEvent)
	{  
		String fbInsert = "INSERT INTO facebook_event (name, host, description, event_type, owner, start_time, end_time, update_time, location)" +
			              " VALUES(?,?,?,?,?,?,?,?,?)";
		
		
		// Array of parameter values (primtives should wrapped in objects)
		Object[] params = new Object[] { 
				newSocialEvent.getName(),
				newSocialEvent.getHost(),
				newSocialEvent.getDescription(),
				newSocialEvent.getEvent_type(),
				new Long(newSocialEvent.getOwner()),
				newSocialEvent.getStart_time(),
				newSocialEvent.getEnd_time(),
				newSocialEvent.getUpdate_time(),
				new Long(newSocialEvent.getLocation())
		};
		
		try{
			int rowsUpdated = new SmartCampusDAO().update(fbInsert, params);
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Error while creating new social event");
			return false;
		}		
		return true;
	}
	
	
	/**
	 * This function registers an event with the event dispatcher to request attendance
	 * to a social event. The request is generated 20 seconds after it is submitted.
	 * @param reuestingUserId
	 * 		User Id for the user requesting attendance
	 * @param requestedUserId
	 * 		User Id for the requested user
	 * @param eventId
	 *      Event Id for the event to which attendance is requested
	 * @param serviceName
	 * 		Service that will recieve the request
	 * @return Boolean
	 * 		Boolean object reprsenting success or failure of the operation
	 * @author Maverick
	 */
	public Boolean requestSocialEventAttendance(Long requestingUserId, Long requestedUserId, Long eventId, String serviceName)
	{
		//get the user name for requesting user from database
		Object[] params1 = new Object[] {
				new Long(requestingUserId),
				};
		
		Object[] params2 = new Object[] {
				new Long(eventId),
				};
		
	    String query1 = "SELECT username from fof_user " + 
	    			    "WHERE userid=?";			   
	    String query2 = "SELECT name from facebook_event " + 
		   				"WHERE eid=?"; 
	    
		try{
			user_name[] usernameDB = (user_name[]) new SmartCampusDAO().query(query1,params1,user_name.class);
			event_name[] eventnameDB = (event_name[]) new SmartCampusDAO().query(query2,params2,event_name.class);
			
			
			if(usernameDB.length > 0 && eventnameDB.length > 0)
			{
				Vector<Long> targetUser = new Vector<Long>();
				targetUser.add(new Long(requestedUserId));
				
				//register an event to request social contact
				KVMCBTrigger newTrigger = new KVMCBTrigger();
				newTrigger.setColocationConstraints(false);
				newTrigger.setLocationConstraints(false);
				newTrigger.setTimeConstraints(true);
				newTrigger.setStartTime( new Long(System.currentTimeMillis() + 20000));
				newTrigger.setEndTime(new Long(System.currentTimeMillis() + 40000));
				newTrigger.setDate(new Long(System.currentTimeMillis()));
				newTrigger.setFrequency("onetime");
				
				KVMEvent socialEventAttendanceRequest = new KVMEvent();
				socialEventAttendanceRequest.setCBTrigger(newTrigger);
				socialEventAttendanceRequest.setSourceUser(requestingUserId);
				socialEventAttendanceRequest.setTargetUsers(targetUser);
				socialEventAttendanceRequest.setType("SocialEventAttendanceRequest");
				socialEventAttendanceRequest.setDescription(usernameDB[0].username +  " has invited you to the event " + eventnameDB[0].name);
				socialEventAttendanceRequest.setServiceName(serviceName);
				
				//setup SOAP stuff to call the registerEvent method in the event service
			    SoapObject rpc = new SoapObject("soapservice","registerEvent");
				rpc.addProperty("X", socialEventAttendanceRequest);
				HttpTransportSE tx = new HttpTransportSE (connectString + "EventService", "registerEvent"); 
				tx.setClassMap (classMap);
				
				//call the registerEvent method in the event service
				tx.call(rpc);		
			}
		}catch(Exception e)
		{
			System.out.println("Error while requesting social contact");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	
	
	/**
	 * This is KSOAP compatibile wrapper function used to set new place data
	 * Note that the location data vector should use KVMCoordinates instead 
	 * of regular Coordinates class 
	 * @param kvmNewPlaceData
	 *   This is the place data inside a KVMPlaceData object. 
	 * @return
	 *   Returns a boolean value indicating success or failure of the insert operation
	 * @author Maverick 
	 */
	public Boolean setPlaceData(KVMPlaceData kvmNewPlaceData)
	{
		//create regular object to store place data
		PlaceData regularNewPlaceData = new PlaceData();
		
		if(kvmNewPlaceData.getLocation() != null)
		{
			//create  vector of regular coordinates from kvm coordinates
			Vector tempLocationVector = kvmNewPlaceData.getLocation();
			Vector newLocationVector = new Vector();
			
			
			for(int i=0; i<tempLocationVector.size(); i++)
			{
				KVMCoordinates tempCoordinates = (KVMCoordinates)tempLocationVector.get(i);
				Coordinates newCoordinates = new Coordinates(0.0,0.0,0);
				if(tempCoordinates.getLat() != null)
				{
						newCoordinates.setLatitude(new Double(tempCoordinates.getLat()));
				}
				if(tempCoordinates.getLon() != null)
				{
						newCoordinates.setLatitude(new Double(tempCoordinates.getLat()));
				}
				if(tempCoordinates.getAlt() != null)
				{
						newCoordinates.setAltitude(new Float(tempCoordinates.getAlt()));
				}
				
				newLocationVector.add(newCoordinates);
			}
			
			//transfer location vector to regular place object
			regularNewPlaceData.setLocation(newLocationVector);
		}
		
		//transfer data from kvm object
		if(kvmNewPlaceData.getName() != null)
		{
			regularNewPlaceData.setName(kvmNewPlaceData.getName());
		}
		
		if(kvmNewPlaceData.getPlace_id() != null)
		{
			regularNewPlaceData.setPlace_id(kvmNewPlaceData.getPlace_id());
		}
		
		if(kvmNewPlaceData.getParent_id() != null)
		{
			regularNewPlaceData.setParent_id(kvmNewPlaceData.getParent_id());
		}
	
		if(kvmNewPlaceData.getType() != null)
		{
			regularNewPlaceData.setType(kvmNewPlaceData.getType());
		}
		
		//call the reular method
		return setPlaceData(regularNewPlaceData);
	}
	
	
	/**
	 * This function enters basic data for a place
	 * @param PlaceData
	 * 	This is data for the place to be inserted inside a PlaceData object 
	 * @return
	 * 	 Returns a boolean value indicating success or failure of the insert operation
	 * @author Maverick
	 */
	public Boolean setPlaceData(PlaceData newPlaceData)
	{
		//get the vector for place coordinates
		Vector placeCoordinatesList = newPlaceData.getLocation();
		Coordinates tempPointCoordinates; 
		
		//now create a string that represents all the points in keeping with the GIS format
		StringBuffer listOfPoints = new StringBuffer(); //this string stores the polygon
		StringBuffer singlePoint = new StringBuffer(); //this string stores a single point
		
		//take all the coordinate points and create a string for GIS entry
		for(int i=0; i<placeCoordinatesList.size(); i++)
		{
			tempPointCoordinates = (Coordinates)placeCoordinatesList.get(i); 
			//create a string for single point
			singlePoint.append(tempPointCoordinates.getLongitude());
			singlePoint.append(" ");
			singlePoint.append(tempPointCoordinates.getLatitude());
			
			//add single point to the string ensuring that the required format is maintained
			if(listOfPoints.length() == 0)
			{
				listOfPoints.append(singlePoint);
			}
			else
			{
				listOfPoints.append(", ");
				listOfPoints.append(singlePoint);
			}
			
			//clear the single point buffer
			singlePoint.setLength(0);
		}
		
		//convert to regular string object to insert in database
		String location = listOfPoints.toString();
		
		try{
			Object[] params = new Object[]{
					newPlaceData.getPlace_id(),
					newPlaceData.getName(),
					newPlaceData.getParent_id(),
					location,
					newPlaceData.getType()
			};
					
			
		//String query = "INSERT INTO gis_space (space_id, name, parent_id, location, type) VALUES(?,?,?,GeomFromText('POLYGON ((?))',4326),?)";
		
		String insertAPData = "INSERT INTO gis_space (space_id, name,parent_id, location,type ) VALUES("
			+ newPlaceData.getPlace_id()
			+ ",'"
			+ newPlaceData.getName()
			+ "',"
			+ newPlaceData.getParent_id()
			+ ",GeomFromText('POLYGON(("
			+ location
			+ "))',4326),'"
			+ newPlaceData.getType() + "');";
		
		
				int rowsUpdated = new SmartCampusDAO().update(insertAPData);
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Error while inserting place data");
			return false;
		}
		return true;
		
	}
	
	
	
	/**
	 * **************************************************************************************************
	 * **************************************************************************************************
	 * **************************************************************************************************
	 * *********************************Start Of the Location API*************************
	 * **************************************************************************************************
	 * **************************************************************************************************
	 * **************************************************************************************************
	 */
	
	/**
	 * This method stores a single location data object. 
	 * Note that this method does not provide a non-KVM interface
	 * since the data is coming from mobile phones as KVM objects 
	 * @param locationData
	 * 		Location information inside a KVMLocationdata object
	 * @return
	 * 		Boolean object representing success or failure of the operation
	 * @author Maverick
	 */
	public Boolean setUserLocation(KVMLocationData locationData)
	{
		//variables to store location information recieved from the client
		Double fpLat, fpLon, fpHt, cnLat, cnLon, cnHt;
		Integer fpFl, cnFl;
		Long userId;
		Vector accessPointInfoVector;
		Timestamp timeOfReading;
		
		try{
			//get data from location object
			fpLat = Double.valueOf(locationData.getFpLat());
			fpLon = Double.valueOf(locationData.getFpLon());
			fpHt = Double.valueOf(locationData.getFpHt());
			fpFl = locationData.getFpFl();
			cnLat = Double.valueOf(locationData.getCnLat());
			cnLon = Double.valueOf(locationData.getCnLon());
			cnHt = Double.valueOf(locationData.getCnHt());
			cnFl = locationData.getCnFl();
			userId = locationData.getUserId();
			accessPointInfoVector = locationData.getAccessPointInfo();
		
			if(locationData.getCurrentTime() != null)
			{
				timeOfReading = new Timestamp(locationData.getCurrentTime());
			}
			else
			{
				timeOfReading = new Timestamp(System.currentTimeMillis());
			}
			
			
			//store the fingerprinting location
			storeFingerPrintingLocation(fpLat, fpLon, fpHt, fpFl, timeOfReading, userId);
			//store the centroid location
			storeCentroidLocation(cnLat, cnLon, cnHt, cnFl, timeOfReading, userId);
			//store Access Point Info
			storeGISCentroidLocation(cnLat, cnLon, cnHt, cnFl, userId);
			//store Access Point Info
			storeAccessPointInfo(accessPointInfoVector,  timeOfReading, userId);
			
			
			//return true if location was stored succesfully;
			return true;
		
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * This method stores a vector of location data objects
	 * @param locationData
	 * 		A Vector of location objects (KVMLocationData)
	 * @return
	 * 		A Boolean object represeting success or failure of operation
	 * @author Maverick
	 */
	public Boolean storeLocationVector(Vector locationData)
	{
		try{
			KVMLocationData tempLocationData;
			
			for(int i=0;i<locationData.size(); i++)
			{
				tempLocationData = (KVMLocationData)locationData.get(i);
				setUserLocation(tempLocationData);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	
	/*********************************************************************************************************************
	 * *******************************************************************************************************************
	 * *******************************************************************************************************************
	 * *************************************Data Collection API ends here*************************************************
	 * *******************************************************************************************************************
	 * *******************************************************************************************************************
	 * *******************************************************************************************************************
	 */
	
	/**
	 * This function stores data obtained from the centroid method in the GIS 
	 * location tables
	 */
	private static void storeGISCentroidLocation(Double cnLat, Double cnLon,
			Double cnHt, Integer cnFl, Long userId) {

		try {
			PGgeometry test = new PGgeometry();
			test.setType("POINT");
			test.setGeometry(PGgeometry.geomFromString("POINT ("
					+ new Double(cnLon) + " " + new Double(cnLat) + ")"));

			Object[] fpLocationData = new Object[] { userId,
					new Timestamp(System.currentTimeMillis()), "Centroid",
					test.getGeometry(), cnHt, new Integer(0), new Integer(0),
					cnFl };

			String insertFpLocation = "INSERT INTO gis_location (user_id, time, mode, location, height, num_visible_ap, num_known_ap, floor) VALUES (?,?,?,GeomFromText(?),?,?,?,?)";

			int rowsUpdated = new SmartCampusDAO().update(insertFpLocation, fpLocationData);
			
		} catch (Exception e) {
			System.out.println("Error while storing GIS centroid location");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Stores the location Obtained Via Fingerprinting Method
	 * @param fpLat
	 * @param fpLon
	 * @param fpHt
	 * @param fpFl
	 * @param userId
	 */
	private void storeFingerPrintingLocation(Double fpLat, Double fpLon, Double fpHt, Integer fpFl, Timestamp timeOfReading, Long userId )
	{
		Object[] fpLocationData = new Object[] {
				userId,
				timeOfReading,
				"Finger Printing",
				fpLat,
				fpLon,
				fpHt,
				new Integer(0),
				new Integer(0),
				fpFl
				};

		String insertFpLocation = "INSERT INTO locationinfo_location (userid, time, mode, lat, lon, height, numberofap, numberofknownap, floor) VALUES (?,?,?,?,?,?,?,?,?)";
		
		try{
			int rowsUpdated = new SmartCampusDAO().update(insertFpLocation, fpLocationData);
		}catch(Exception e)
		{
			e.printStackTrace();
		}	
	}

	/**
	 * 
	 * Stores the location obtained via Centroid Method
	 * @param fpLat
	 * @param fpLon
	 * @param fpHt
	 * @param fpFl
	 * @param userId
	 */
	private void storeCentroidLocation(Double cnLat, Double cnLon, Double cnHt, Integer cnFl, Timestamp timeOfReading, Long userId)
	{
		Object[] fpLocationData = new Object[] {
				userId,
				timeOfReading,
				"Centroid",
				cnLat,
				cnLon,
				cnHt,
				new Integer(0),
				new Integer(0),
				cnFl
				};

		String insertFpLocation = "INSERT INTO locationinfo_location (userid, time, mode, lat, lon, height, numberofap, numberofknownap, floor) VALUES (?,?,?,?,?,?,?,?,?)";
		
		try{
			int rowsUpdated = new SmartCampusDAO().update(insertFpLocation, fpLocationData);
		}catch(Exception e)
		{
			e.printStackTrace();
		}	
	}
	
	/**
	 * 
	 * Stores Access point Info in the Database
	 * @param accessPointInfo
	 */
	private void storeAccessPointInfo(Vector accessPointInfoVector, Timestamp timeOfReading, Long userId)
	{
		for(int i=0; i<accessPointInfoVector.size(); i++)
		{
			KVMAccessPointInfo accessPointInfo= (KVMAccessPointInfo)accessPointInfoVector.elementAt(i);
			
			Object[] accessPointData = new Object[]{
					userId,
					accessPointInfo.getMacId(),
					timeOfReading,
					Float.valueOf(accessPointInfo.getSignalStrength()),
					accessPointInfo.getSsId(),
					accessPointInfo.getIsPointKnown()
					};
			  
			String insertAPData = "INSERT INTO locationinfo_userdata (userid, macid, time, signalstrength, name, known) VALUES (?,?,?,?,?,?)";
			
			try{
				int rowsUpdated = new SmartCampusDAO().update( insertAPData, accessPointData);
			}catch(Exception e)
			{
				e.printStackTrace();
			}							
		}
	}
	
	
	/**
	 * 
	 * this function checks if an email exists in the database
	 * @param Email-
	 *            check if a user exists by email
	 * @return true if email exist in database
	 */
	private boolean CheckUserEmailExist(String Email) {
		// checks if the specified user email exists

		String fofSelect = "Select userid FROM fof_user where email='" + Email
				+ "'";
		Log.put("demoSELECT", fofSelect);

		try {
			userid[] fofUsers = (userid[]) new SmartCampusDAO()
					.query(fofSelect, userid.class);
			Log.put(fofSelect, fofUsers);

			return fofUsers.length > 0;

		} catch (CoreDaoException e) {

			e.printStackTrace();
		}
		return false;
	}

	/**
	 * this function checks if a user name already exists in the database
	 * @param Username-
	 *            username to be searched in user table
	 * @return true if the username exists
	 */
	private boolean CheckUserExist(String username) {

		String fofSelect = "Select userid FROM fof_user where username='"
				+ username + "'";
		Log.put("demoSELECT", fofSelect);

		try {
			userid[] fofUsers = (userid[]) new SmartCampusDAO().query(fofSelect, userid.class);
			Log.put(fofSelect, fofUsers);
			
			return fofUsers.length > 0;
		} catch (CoreDaoException e) {
			e.printStackTrace();
		}
		return false;
	}
	

	
	/**
	 * ****************************************************************************************
	 * *****************************************************************
	 * ********************** Utility Functions*************************
	 * *****************************************************************
	 * *****************************************************************
	 */
	
	
	private static void loadDataToPostgres() {

		String[][] data = {
				{
						"1",
						"NJIT Campus",
						"0",
						"-74.179802,40.744659,0 -74.177177,40.743839,0 -74.175911,40.743404,0 -74.17742200000001,40.7407,0 -74.17794800000002,40.739651,0 -74.18006900000002,40.74025,0 -74.18042800000001,40.74041,0 -74.18081700000001,40.74068100000001,0 -74.181107,40.74094,0 -74.181297,40.741241,0 -74.181389,40.741589,0 -74.181343,40.741959,0 -74.179802,40.744659",
						"Campus" },
				{
						"2",
						"GITC",
						"1",
						"-74.17976400000001,40.744507,0 -74.17955000000001,40.744461,0 -74.17948199999999,40.744522,0 -74.17832199999999,40.744152,0 -74.178566,40.743732,0 -74.17997,40.744164,0 -74.17989300000001,40.744324,0 -74.17976400000001,40.744507",
						"Building" },
				{
						"3",
						"Tennis Courts",
						"1",
						"-74.180077,40.744045,0 -74.180367,40.743538,0 -74.17996200000002,40.743408,0 -74.17968000000001,40.743919,0 -74.180077,40.744045",
						"Outdoor" },
				{
						"4",
						"Gym",
						"1",
						"-74.180367,40.74352300000001,0 -74.18002300000001,40.743416,0 -74.180061,40.743351,0 -74.179298,40.743099,0 -74.179512,40.742729,0 -74.180588,40.74308,0 -74.180367,40.74352300000001",
						"Building" },
				{
						"5",
						"Soccer Field",
						"1",
						"-74.180618,40.743019,0 -74.17952,40.742668,0 -74.180077,40.741692,0 -74.181168,40.742069,0 -74.180618,40.743019",
						"Outdoor" },

				{
						"6",
						"Greens",
						"1",
						"-74.17926799999999,40.743069,0 -74.17868,40.74284,0 -74.179008,40.742172,0 -74.179649,40.742378,0 -74.17926799999999,40.743069",
						"Outdoor" },
				{
						"7",
						"Library",
						"1",
						"-74.17826100000001,40.744045,0 -74.178375,40.743824,0 -74.1782,40.743782,0 -74.17828400000001,40.743622,0 -74.177818,40.743519,0 -74.17767300000001,40.743755,0 -74.177612,40.743771,0 -74.177544,40.743843,0 -74.17826100000001,40.744045",
						"Building" },
				{
						"8",
						"Redwood Hall",
						"1",
						"-74.17968000000001,40.743923,0 -74.17948199999999,40.743855,0 -74.17971,40.743454,0 -74.17989300000001,40.743507,0 -74.17968000000001,40.743923",
						"Building" },
				{
						"9",
						"Cypress Hall",
						"1",
						"-74.179283,40.743652,0 -74.17939000000001,40.74369,0 -74.17931400000001,40.743797,0 -74.17899300000001,40.743675,0 -74.17920700000001,40.743271,0 -74.179565,40.743393,0 -74.17949700000001,40.7435,0 -74.179382,40.743462,0 -74.179283,40.743652",
						"Building" },
				{
						"10",
						"Oak Hall",
						"1",
						"-74.17929100000001,40.740036,0 -74.179138,40.740299,0 -74.179237,40.740322,0 -74.179306,40.740261,0 -74.179535,40.740337,0 -74.179733,40.74036,0 -74.17983200000001,40.74020400000001,0 -74.17929100000001,40.740036",
						"Building" },
				{
						"11",
						"Laurel Hall",
						"1",
						"-74.17955000000001,40.740356,0 -74.179176,40.740925,0 -74.178848,40.740829,0 -74.178726,40.741055,0 -74.179329,40.741245,0 -74.179817,40.740425,0 -74.17955000000001,40.740356",
						"Building" },
				{
						"12",
						"Parking Deck",
						"1",
						"-74.17879499999999,40.740559,0 -74.177643,40.740265,0 -74.17796300000001,40.739662,0 -74.17910000000001,40.740013,0 -74.17879499999999,40.740559",
						"Building" },
				{
						"13",
						"Campus Center",
						"1",
						"-74.17744399999999,40.74317900000001,0 -74.17781100000001,40.743286,0 -74.177841,40.743198,0 -74.177933,40.743282,0 -74.177994,40.743324,0 -74.178062,40.74335900000001,0 -74.178139,40.743374,0 -74.178291,40.743385,0 -74.17839100000001,40.743378,0 -74.17849,40.74335900000001,0 -74.178566,40.743301,0 -74.17862700000002,40.743221,0 -74.178658,40.743149,0 -74.17868,40.743092,0 -74.17868799999999,40.743034,0 -74.17868,40.742973,0 -74.178673,40.742943,0 -74.17862700000002,40.742886,0 -74.17781100000001,40.742596,0 -74.17744399999999,40.74317900000001",
						"Building" },
				{
						"14",
						"Kufrian Hall",
						"1",
						"-74.17865000000001,40.742737,0 -74.17888600000001,40.742264,0 -74.178314,40.742016,0 -74.17797899999999,40.742542,0 -74.17865000000001,40.742737",
						"Building" },
				{
						"15",
						"Campbell Hall",
						"1",
						"-74.178108,40.74179500000001,0 -74.17817700000001,40.741676,0 -74.17729199999999,40.741379,0 -74.177216,40.741505,0 -74.178108,40.74179500000001",
						"Building" },
				{
						"16",
						"Colton Hall",
						"1",
						"-74.178185,40.74163100000001,0 -74.177559,40.741425,0 -74.177635,40.741253,0 -74.17829900000001,40.741482,0 -74.178185,40.74163100000001",
						"Building" },
				{
						"17",
						"Weston Hall",
						"1",
						"-74.177628,40.741238,0 -74.17755099999999,40.741428,0 -74.17731499999999,40.741348,0 -74.177216,40.741505,0 -74.177002,40.741436,0 -74.177368,40.740784,0 -74.177589,40.740883,0 -74.17746,40.741142,0 -74.177628,40.741238",
						"Building" },
				{
						"18",
						"YCEES",
						"1",
						"-74.178513,40.740967,0 -74.17860400000001,40.740784,0 -74.17757400000001,40.740479,0 -74.177475,40.740643,0 -74.178513,40.740967",
						"Building" },
				{
						"19",
						"Spetch Building",
						"1",
						"-74.17828400000001,40.741337,0 -74.177643,40.741154,0 -74.17775,40.740955,0 -74.17836800000001,40.741161,0 -74.17828400000001,40.741337",
						"Building" },
				{
						"20",
						"Central High School",
						"1",
						"-74.177773,40.742371,0 -74.178062,40.74184800000001,0 -74.17775,40.74173700000001,0 -74.177719,40.741783,0 -74.177536,40.741722,0 -74.177567,40.741676,0 -74.176979,40.741505,0 -74.176689,40.74203900000001,0 -74.177223,40.742188,0 -74.177238,40.742149,0 -74.17746699999999,40.742207,0 -74.17744399999999,40.742256,0 -74.177773,40.742371",
						"Building" },
				{
						"21",
						"ECE Building",
						"1",
						"-74.17862700000002,40.741192,0 -74.178482,40.74144,0 -74.17881,40.741554,0 -74.178955,40.741314,0 -74.17862700000002,40.741192",
						"Building" },
				{
						"22",
						"Micro Electornics Buildings",
						"1",
						"-74.17841300000001,40.741554,0 -74.178253,40.741852,0 -74.17858099999999,40.741947,0 -74.17873400000001,40.741653,0 -74.17841300000001,40.741554",
						"Building" },
				{
						"23",
						"Tiernan Hall",
						"1",
						"-74.17918400000001,40.742153,0 -74.17952699999999,40.741497,0 -74.17997,40.741646,0 -74.179626,40.742294,0 -74.17918400000001,40.742153",
						"Building" },
				{
						"24",
						"Faculty Memorial Hall",
						"1",
						"-74.179169,40.742146,0 -74.179321,40.741844,0 -74.178757,40.741646,0 -74.178589,40.741947,0 -74.179169,40.742146",
						"Building" },
				{
						"25",
						"Parking Lot 16",
						"1",
						"-74.17952699999999,40.741314,0 -74.180153,40.740292,0 -74.180443,40.740417,0 -74.18069500000001,40.740616,0 -74.180908,40.740788,0 -74.181152,40.74102,0 -74.181313,40.741356,0 -74.18135100000001,40.74168,0 -74.18129,40.741928,0 -74.17952699999999,40.741314",
						"Building" },
				{
						"26",
						"Fenster Hall",
						"1",
						"-74.177567,40.74277100000001,0 -74.17767300000001,40.742519,0 -74.176582,40.742168,0 -74.17646000000001,40.742367,0 -74.177567,40.74277100000001",
						"Building" },
				{
						"27",
						"Cullimore Hall",
						"1",
						"-74.177345,40.743168,0 -74.177544,40.742794,0 -74.177223,40.742672,0 -74.176987,40.74305,0 -74.177345,40.743168",
						"Building" },
				{
						"28",
						"Eberhardt Hall",
						"1",
						"-74.176964,40.743034,0 -74.176682,40.742947,0 -74.176872,40.742611,0 -74.177155,40.742676,0 -74.176964,40.743034",
						"Building" },
				{
						"29",
						"Parking Lot 5",
						"1",
						"-74.179321,40.74179500000001,0 -74.17948199999999,40.741486,0 -74.178955,40.741341,0 -74.17884100000001,40.74163100000001,0 -74.179321,40.74179500000001",
						"Building" },
				{
						"30",
						"Parking Lot #7",
						"1",
						"-74.177559,40.743881,0 -74.17778800000001,40.7435,0 -74.17744399999999,40.743385,0 -74.17723100000001,40.74377800000001,0 -74.177559,40.743881",
						"Building" },
				// { "", "", "1", "", "Building" },

				{
						"0",
						"Newark",
						"0",
						"-74.167152,40.720657,0 -74.166725,40.758114,0 -74.209045,40.758701,0 -74.20929700000001,40.720982,0 -74.167152,40.720657",
						"City" },

		};
		try {
			for (int i = 0; i < data.length; i++) {
				String temp = data[i][3].replaceAll(",0", "XX");
				temp = temp.replaceAll(",", " ");
				temp = temp.replaceAll("XX", ",");
				// System.out.println(temp);

				PGgeometry test = new PGgeometry();
				test.setType("POLYGON");
				test.setGeometry(PGgeometry.geomFromString("POLYGON (" + temp
						+ ")"));
				
				Object[] accessPointData = new Object[] {
						new Integer(data[i][0]), data[i][1],
						new Integer(data[i][2]), test.getGeometry(), data[i][4] };
				// String insertAPData="";
				String insertAPData = "INSERT INTO gis_space (space_id, name,parent_id, location,type ) VALUES("
						+ data[i][0]
						+ ",'"
						+ data[i][1]
						+ "',"
						+ data[i][2]
						+ ",GeomFromText('POLYGON(("
						+ temp
						+ "))',4326),'"
						+ data[i][4] + "');";

				System.out.println(insertAPData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
//	public static void main(String args[])
//	{
//		
//		DataCollectionService dc = new DataCollectionService();
//		
//		KVMSocialEvent X = new KVMSocialEvent();
//    	X.setDescription("Cool Event");
//    	X.setName("New Event 1");
//    	//X.setStart_time(new Long(System.currentTimeMillis()));
//    	//X.setUpdate_time(new Long(System.currentTimeMillis()));
//    	//X.setEnd_time(new Long(System.currentTimeMillis()));
//    	X.setHost(null);
//    	X.setEvent_type("Cool");
//    	
//		dc.addSocialEvent(X);
//		//dc.loadDataToPostgres();
//		//System.out.println(dc.setPlaceData());
//	}
	
}

