package services;

import java.util.TreeSet;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Vector;
import java.sql.*;

import org.postgis.PGgeometry;
import org.postgis.Geometry;
import org.postgis.Point;

import serviceFunctionality.socialStateLearning.Matching.Util.ElementMatch;
import serviceFunctionality.socialStateLearning.Matching.Util.MatchFactory;
import serviceFunctionality.socialStateLearning.Matching.Util.MatchInterface;

import ksoapDataObjects.socialStateLearning.*;
import dataObjects.common.*;
import dataObjects.socialStateLearning.*;
import ksoapDataObjects.common.*;
import ksoapDataObjects.common.KVMCoordinates;

import core.data.dao.CoreDaoException;
import edu.njit.sc.core.dao.SmartCampusDAO;
import core.data.dto.StringValue;

/**
 * This service provides the API for (Social State Learning) as described in the MobiSoc paper/documentation.
 * Due to restrictions imposed by KSOAP on the type of dataobects that can be exchanged, for ceratin methods
 * we provide two versions of the API. One that works with KSOAP and the other one works generally. If we do
 * not provide a specific KSOAP version, then the method works with KSOAP as well as in the general case
 * @author Maverick
 */
public class SocialStateLearningService {
	/**
	 * **************************************************************************************************
	 * **************************************************************************************************
	 * **************************************************************************************************
	 * *********************************Start Of  The People Profiling API*********************************
	 * **************************************************************************************************
	 * **************************************************************************************************
	 * **************************************************************************************************
	 */

	/**
	 * This function returns profiles (user id) that have a name that matches any of the keywords
	 * The profile is searched for first name and last name. 
	 * @param
	 *   Vector that contains search keywords(String)
	 * @return  
	 * 	 Vector that contains User Id(Long) for the searched profiles
	 * @author Maverick 
	 */
	public Vector<Long> searchProfiles(Vector<String> keywords)
	{
		Vector<Long> discoveredProfiles = new Vector<Long>();
		
		//basic query - note that the first where condition is always false
		StringBuffer queryBuffer = new StringBuffer("SELECT uid as user_id FROM facebook_user WHERE uid=-1 ");
		
		//extend query to search for keywords
		for(int i=0;i<keywords.size();i++)
		{
			queryBuffer.append("OR first_name LIKE '" + (String)keywords.get(i) + "' ");
			queryBuffer.append("OR last_name LIKE '" + (String)keywords.get(i) + "' ");
		}
		
		String query = queryBuffer.toString();
		
		try{
			user_id[] discoveredProfilesDB = (user_id[]) new SmartCampusDAO().query(query,user_id.class);
			
			if(discoveredProfilesDB.length > 0)
			{
				for(int i=0; i<discoveredProfilesDB.length; i++)
				{
					discoveredProfiles.add(new Long(discoveredProfilesDB[i].user_id));
				}
			}
		}catch(Exception e)
		{
			System.out.println("Error while searching profiles");
			e.printStackTrace();
			return null;
		}
		
		return discoveredProfiles;	
	}

	
	/**
	 * This function returns social groups that have a name that matches any of the keywords
	 * @param keywords
	 * 	Vector contaning strings of keywords
	 * @return
	 * 	Vector that contains Group Id's(Long) of group whose name matches the keywords
	 * @author Maverick 
	 */
	public Vector<Long> searchSocialGroups(Vector<String> keywords)
	{
		Vector<Long> discoveredSocialGroups = new Vector<Long>();
		
		//basic query- note that the where condition is always false
		StringBuffer queryBuffer = new StringBuffer("SELECT gid as group_id FROM facebook_group WHERE gid=-1 ");
		
		//extend query to search for keywords
		for(int i=0;i<keywords.size();i++)
		{
			queryBuffer.append("OR name LIKE '%" + (String)keywords.get(i) + "%' ");
		}
		
		String query = queryBuffer.toString();
		
		try{
			social_group_id[] discoveredSocialGroupsDB = (social_group_id[]) new SmartCampusDAO().query(query,social_group_id.class);
			
			if(discoveredSocialGroupsDB.length > 0)
			{
				for(int i=0; i<discoveredSocialGroupsDB.length; i++)
				{
					discoveredSocialGroups.add(new Long(discoveredSocialGroupsDB[i].group_id));
				}
			}
		}catch(Exception e)
		{
			System.out.println("Error while searching social groups");
			e.printStackTrace();
			return null;
		}
		
		return discoveredSocialGroups;	
	}
	

	

	/**
	 * This is KSOAP compatible API used to get information pertaining to a social group
	 * @param group_id
	 * Group Id for the group for which information is required	
	 * @return
	 * Information pertaining to a social group inside KVMSocialGroup object
	 * @author Maverick 
	 */
	public KVMSocialGroup getSocialGroupInfo(Long group_id)
	{
		//get social group info from main API
		SocialGroup socialGroupInfo = getSocialGroupInfo(group_id.longValue());
		//transfer data into KVM object and return it
		KVMSocialGroup kvmSocialGroupInfo = new KVMSocialGroup();
		kvmSocialGroupInfo.setDescription(socialGroupInfo.getDescription());
		kvmSocialGroupInfo.setGid(socialGroupInfo.getGid());
		kvmSocialGroupInfo.setGroup_type(socialGroupInfo.getGroup_type());
		kvmSocialGroupInfo.setLocation(socialGroupInfo.getLocation());
		kvmSocialGroupInfo.setName(socialGroupInfo.getName());
		kvmSocialGroupInfo.setOwner(socialGroupInfo.getOwner());
		kvmSocialGroupInfo.setRecent_news(socialGroupInfo.getRecent_news());
		kvmSocialGroupInfo.setUpdate_time(socialGroupInfo.getUpdate_time().getTime());
		kvmSocialGroupInfo.setWebsite(socialGroupInfo.getWebsite());
		return kvmSocialGroupInfo; 
	}
	
	
	/**
	 * This API is used to get information pertaining to a social group 
	 * @param group_id
	 * 		Group Id for the group for which information is required 
	 * @return
	 * 		Returns a class (SocialGroup) that contains information pertaining to a social group
	 * @author Maverick
	 */
	public SocialGroup getSocialGroupInfo(long group_id)
	{
		SocialGroup socialGroupInfo = new SocialGroup();
		
		Object[] params = new Object[] {
				group_id
				};
		
		String query = "SELECT * FROM facebook_group " +
					   "WHERE gid=?";

		try {
			social_group[] socialGroupInfoDB = (social_group[]) new SmartCampusDAO().query(query,params,social_group.class);
			
            if(socialGroupInfoDB.length > 0)
            {
            	socialGroupInfo.setDescription(socialGroupInfoDB[0].description);
            	socialGroupInfo.setGid(socialGroupInfoDB[0].gid);
            	socialGroupInfo.setGroup_type(socialGroupInfoDB[0].group_type);
            	socialGroupInfo.setLocation(socialGroupInfoDB[0].location);
            	socialGroupInfo.setName(socialGroupInfoDB[0].name);
            	socialGroupInfo.setOwner(socialGroupInfoDB[0].owner);
            	socialGroupInfo.setRecent_news(socialGroupInfoDB[0].recent_news);
            	socialGroupInfo.setUpdate_time(socialGroupInfoDB[0].update_time);
            	socialGroupInfo.setWebsite(socialGroupInfoDB[0].website);
            }
		}catch(Exception e)
            {
            	e.printStackTrace();
            	System.out.println("Error while fetching social group info");
            	return null;
            }
		return socialGroupInfo; 
	}
	
	
	
	/**
	 * This function is used to get profile info for a user
	 * @param userId
	 * UserId for the user whose profile data is requested
	 * @return
	 * Profile data for the user inside a KVMUser object
	 * @author Maverick
	 */
	public KVMUser getProfileInfo(Long userId)
	{
		//Transfer data from KVM object to regular object
		User regUserData  = getProfileInfo(userId.longValue());
		KVMUser kvmUserData = new KVMUser();
		
		kvmUserData.setAbout_me(regUserData.getAbout_me());
		kvmUserData.setActivities(regUserData.getActivities());
		kvmUserData.setBirthday(regUserData.getBirthday());
		kvmUserData.setBooks(regUserData.getBooks());
		kvmUserData.setFid(regUserData.getFid());
		kvmUserData.setFirst_name(regUserData.getFirst_name());
		kvmUserData.setGid(regUserData.getGid());
		kvmUserData.setHas_added_app(regUserData.getHas_added_app());
		kvmUserData.setInterests(regUserData.getInterests());
		kvmUserData.setIs_app_user(regUserData.getIs_app_user());
		kvmUserData.setLast_name(regUserData.getLast_name());
		kvmUserData.setLastupdated(regUserData.getLastupdated().getTime());
		kvmUserData.setMeeting_for(regUserData.getMeeting_for());
		kvmUserData.setMeeting_sex(regUserData.getMeeting_sex());
		kvmUserData.setMovies(regUserData.getMovies());
		kvmUserData.setMusic(regUserData.getMusic());
		kvmUserData.setName(regUserData.getName());
		kvmUserData.setNotes_count(regUserData.getNotes_count());
		kvmUserData.setPic(regUserData.getPic());
		kvmUserData.setPic_big(regUserData.getPic_big());
		kvmUserData.setPic_square(regUserData.getPic_square());
		kvmUserData.setPolitical(regUserData.getPolitical());
		kvmUserData.setProfile_update_time(regUserData.getProfile_update_time());
		kvmUserData.setQuotes(regUserData.getQuotes());
		kvmUserData.setRelationship_status(regUserData.getRelationship_status());
		kvmUserData.setReligion(regUserData.getReligion());
		kvmUserData.setSex(regUserData.getSex());
		kvmUserData.setSignificant_other_id(regUserData.getSignificant_other_id());
		kvmUserData.setStatus(regUserData.getStatus());
		kvmUserData.setTimezone(regUserData.getTimezone());
		kvmUserData.setTv(regUserData.getTv());
		kvmUserData.setUid(regUserData.getUid());
		kvmUserData.setWall_count(regUserData.getWall_count());
		kvmUserData.setWork_history(regUserData.getWork_history());
		
		//return the kvm object
		return kvmUserData;
	}
	
	
	/**
	 * This function is used to get profile info for a user
	 * @param userId
	 * UserId for the user whose profile data is requested
	 * @return
	 * Profile data for the user insider a (User) object
	 * @author Maverick
	 */
	public User getProfileInfo(long userId) {

		User userProfile = new User(); 
		Object[] params = new Object[] {
				new Long(userId),
				};
		
		String query = "SELECT * FROM facebook_user " +
								 "WHERE uid=?";

		try {
			facebook_user[] profile = (facebook_user[]) new SmartCampusDAO().query(query,params, facebook_user.class);
			
			if (profile.length > 0) 
			{
				userProfile.setAbout_me(profile[0].about_me);
				userProfile.setActivities(profile[0].activities);
				userProfile.setBirthday(profile[0].birthday);
				userProfile.setBooks(profile[0].books);
				userProfile.setFid(profile[0].fid);
				userProfile.setFirst_name(profile[0].first_name);
				userProfile.setGid(profile[0].gid);
				userProfile.setHas_added_app(profile[0].has_added_app);
				userProfile.setInterests(profile[0].interests);
				userProfile.setIs_app_user(profile[0].is_app_user);
				userProfile.setLast_name(profile[0].last_name);
				userProfile.setLastupdated(profile[0].lastupdated);
				userProfile.setMeeting_for(profile[0].meeting_for);
				userProfile.setMeeting_sex(profile[0].meeting_sex);
				userProfile.setMovies(profile[0].movies);
				userProfile.setMusic(profile[0].music);
				userProfile.setName(profile[0].name);
				userProfile.setNotes_count(profile[0].notes_count);
				userProfile.setPic(profile[0].pic);
				userProfile.setPic_big(profile[0].pic_big);
				userProfile.setPic_small(profile[0].pic_small);
				userProfile.setPic_square(profile[0].pic_square);
				userProfile.setPolitical(profile[0].political);
				userProfile.setProfile_update_time(profile[0].profile_update_time);
				userProfile.setQuotes(profile[0].quotes);
				userProfile.setRelationship_status(profile[0].relationship_status);
				userProfile.setReligion(profile[0].religion);
				userProfile.setSex(profile[0].sex);
				userProfile.setSignificant_other_id(profile[0].significant_other_id);
				userProfile.setStatus(profile[0].status);
				userProfile.setTimezone(profile[0].timezone);
				userProfile.setTv(profile[0].tv);
				userProfile.setUid(profile[0].uid);
				userProfile.setWall_count(profile[0].wall_count);
				userProfile.setWork_history(profile[0].work_history);
			}

		} catch (CoreDaoException e) {
			e.printStackTrace();
		}

		return userProfile;
	}
	
	
	/**
	 * This function is used to get the social contacts for a user
	 * @param userId
	 *  User Id for the user whose contacts are retrieved
	 * @return
	 *  A vector containing User Id's (Long) for all the contacts
	 * @author Maverick 
	 */
	public Vector<Long> getSocialContacts(Long userId)
	{
		Vector<Long> userSocialContacts = new Vector<Long>();
		
		Object[] params = new Object[] {
				new Long(userId),
				};
		
		String query = "SELECT distinct fid as user_id " +
					   "FROM facebook_friends " +
		               "WHERE uid=? ";
		               
		
		try{
			user_id[]userSocialContactsDB = (user_id[]) new SmartCampusDAO().query(query,params,user_id.class);
			
			if(userSocialContactsDB.length > 0)
			{
				for(int i=0; i<userSocialContactsDB.length; i++)
				{
					userSocialContacts.add(new Long(userSocialContactsDB[i].user_id));
				}
			}
		}catch(Exception e)
		{
			System.out.println("Error while fetching user social contacts");
			e.printStackTrace();
			return null;
		}
		
		return userSocialContacts;
	}
	
	
	
	/**
	 * This function is used to get the social groups that a user is associated with
	 * @param userId
	 *  User Id for the user whose groups are to be searched 
	 * @return
	 * 	Vector of group id's (Long) for the groups that the user is associated with
	 * @author Maverick
	 */
	public Vector<Long> getUserSocialGroups(Long userId)
	{
		Vector<Long> userSocialGroups = new Vector<Long>();
		
		Object[] params = new Object[] {
				new Long(userId),
				};
		
		String query = "SELECT distinct gid as group_id " +
					   "FROM facebook_group_membership " +
		               "WHERE uid=? ";
		               
		
		try{
			social_group_id[]userSocialGroupsDB = (social_group_id[]) new SmartCampusDAO().query(query,params,social_group_id.class);
			
			if(userSocialGroupsDB.length > 0)
			{
				for(int i=0; i<userSocialGroupsDB.length; i++)
				{
					userSocialGroups.add(new Long(userSocialGroupsDB[i].group_id));
				}
			}
		}catch(Exception e)
		{
			System.out.println("Error while fetching user social groups");
			e.printStackTrace();
			return null;
		}
		
		return userSocialGroups;
	}
	
	
	/**
	 * This function returns the latest user location from GIS table. 
	 * We provide an additional auxiliary function(next) that returns 
	 * user location betwen a given time frame 
	 * @param userid
	 *   User Id for the user whose location is requested
	 * @return
	 *   Latest user location in KVMLocationData object
	 */
	public static KVMLocationData getUserLocation(Long userid) {
		KVMLocationData locationData = new KVMLocationData();

		String query = "select * from gis_location where user_id="
				+ userid
				+ " and time in (select max(time) from gis_location	 where user_id="
				+ userid + ")";

		try {
			gis_location[] profile = (gis_location[]) new SmartCampusDAO()
					.query(query, gis_location.class);

			if (profile.length > 0) {
				String loc = profile[0].location.getValue();
				String split[] = loc.split("\\(");
				split = split[1].split("\\)");
				split = split[0].split(" ");

				locationData.setCnLat(split[1]);
				locationData.setCnLon(split[0]);
				locationData.setCurrentTime(profile[0].time.getTime());
				locationData.setCnFl(profile[0].floor);
				locationData.setUserId(profile[0].user_id);
				locationData.setCnHt(Float.toString((profile[0].height)));

			}

			return locationData;

		} catch (CoreDaoException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
//	/**
//	 * This function returns the user location in a given time frame
//	 * @param userid
//	 * @param starttime
//	 * @param endtime
//	 * @return
//	 */
//	public static KVMLocationData[] getUserLocation(Long userid, Timestamp starttime, Timestamp endtime) 
//	{
//		KVMLocationData locationData[] = null;
//		String query = "select * from gis_location where	 user_id=" + userid
//				+ " and time > timestamp '" + starttime
//				+ "' and time < timestamp '" + endtime + "' order by time asc";
//
//		try {
//			gis_location[] profile = (gis_location[]) new SmartCampusDAO().query(query, gis_location.class);
//
//			if (profile.length > 0) {
//				locationData = new KVMLocationData[profile.length];
//				for (int i = 0; i < profile.length; i++) {
//					locationData[i] = new KVMLocationData();
//					String loc = profile[i].location.getValue();
//					String split[] = loc.split("\\(");
//					split = split[1].split("\\)");
//					split = split[0].split(" ");
//
//					locationData[i].setCnLat(split[1]);
//					locationData[i].setCnLon(split[0]);
//					locationData[i].setCurrentTime(profile[i].time.getTime());
//					locationData[i].setCnFl(profile[i].floor);
//					locationData[i].setUserId(profile[i].user_id);
//					locationData[i]
//							.setCnHt(Float.toString((profile[i].height)));
//				}
//
//			}
//
//			return locationData;
//
//		} catch (CoreDaoException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
	
	
	/**
	 * **************************************************************************************************
	 * **************************************************************************************************
	 * **************************************************************************************************
	 * *********************************Start Of the People-People Afinity API*************************
	 * **************************************************************************************************
	 * **************************************************************************************************
	 * **************************************************************************************************
	 */
	
	/**
	 * This is KSOAP compatible wrapper for main API. This function returns an affinity matrix between 
	 * two users based on several matching factors
	 * @param userId1
	 *   User Id for the first user
	 * @param userId2
	 * 	 User Id for the second user
	 * @return
	 * 	 Vector of objects (KVMAffinityMatrixElement) representing matching elements and thier match scores.
	 *   Note that match scores are strings instead of doubles due to KSOAP restrictions 
	 * @author Ken 
	 */
	public Vector getAffinityMatrix(Long userid1, Long userid2){
		
		Vector regularAffinityMatrix = getAffinityMatrix(userid1.longValue(), userid2.longValue());
		Vector kvmAffinityMatrix = new Vector();
		AffinityMatrixElement regMatrixElement;
		
		//transfer data from regular object to kvm objects
		for(int i=0;i<regularAffinityMatrix.size();i++)
		{
			regMatrixElement = (AffinityMatrixElement)regularAffinityMatrix.get(i);
			KVMAffinityMatrixElement kvmMatrixElement = new KVMAffinityMatrixElement();
			kvmMatrixElement.setElement(regMatrixElement.getElement());
			kvmMatrixElement.setScore(regMatrixElement.getScore().toString());
			kvmAffinityMatrix.add(kvmMatrixElement);
		}
		
		return kvmAffinityMatrix;
	}
	
	/**
	 * This function returns an affinity matrix between two uses based on several matching factors
	 * @param userId1
	 *   User Id for the first user
	 * @param userId2
	 * 	 User Id for the second user	
	 * @return
	 *   Vector of objects (AffinityMatrixElement) representing matching elements and thier match scores
	 * @author Ken
	 */
	public Vector getAffinityMatrix(long userid1, long userid2){
		MatchInterface match=MatchFactory.getMatchInterface(MatchInterface.MATCH_OTHER_ID, userid2, userid2);
		match.generateScore();
		MatchInterface sub[]=match.getSubMatchclasses();
		
		ArrayList<ElementMatch> elem=new ArrayList<ElementMatch>();
		ElementMatch []temp;
		for (int i = 0; i < sub.length; i++) {
			temp=sub[i].getElementMatches();
			for (int j = 0; j < temp.length; j++) {
				elem.add(temp[j]);
			}
		}
		
		//create affinity matrix vector
		Vector affinityMatrix = new Vector();
		
		//insert data into the matrix vector
		for(int i=0; i<elem.size(); i++)
		{
			AffinityMatrixElement newMatrixElement = new AffinityMatrixElement();
			newMatrixElement.setElement(elem.get(i).getPrintName());
			newMatrixElement.setScore(elem.get(i).getScore());	
			affinityMatrix.add(newMatrixElement);
		}

		//return affinity mtrix vector
		return affinityMatrix;
	}
	
	
//	/**
//	 * PSEUDO-CODE (Daniel Boston 2008)
//	 * Prospective algorithm:
//	 *  Given: id1, id2
//	 *  Assuming: There is a table containing the interests of an ID
//	 *  Assign: Create a sorted list against which additions and matches
//	 *          will be made.
//	 *  Algorithm:
//	 *   0. Compare id1 and id2
//     *   0.A. If they are the same,
//	 *   0.A.i. Assign String intrs as the interests of id1
//	 *   0.A.ii. Build TreeSet list by performing the splitting on intrs
//	 *   0.A.iii. Return list.
//	 *   1. Assign String intr1 as interests of id1
//	 *   2. Assign String intr2 as interests of id2
//	 *   3. Build TreeSet list1 (size n) as split of intr1 [O(n ln(n))]
//	 *        Since list1 is already sorted, all intersections or duplicates will
//	 *        already be removed.
//	 *   4. Split intr2 into unsorted array arr1. [O(m)]
//	 *   5. Add each element of arr1 into list1. [O(m ln(m + n)]
//	 *   5.A. If the add function returns false, we have a duplicate;
//	 *          namely, we have a potential common interest. Make note of that
//	 *          intersection by attempting to store it in list2.
//	 *   5.A.i. If the add function of list2 returns false, we already
//	 *            have that interest in list2 - it is a duplicate in the
//	 *            source array arr1; discard it.
//	 *   6. Return list2. 
//	*/
	
	
	/**
	 * This API is used to get common interest between two users
	 * @param id1
	 * 	User Id of the first person whose interest list we
	 *  are going to consider.
	 * @param id2
	 * 	User Id of the second person whose interest list we
	 *  are going to consider.
	 * @return
	 *  A String array with all the intersections between the interests of
	 *  id1 and id2.
	 * @author Daniel Boston
	 **/
	public Vector<String> getCommonInterest(Long id1, Long id2)
	{
		SmartCampusDAO dao = new SmartCampusDAO();
		
		// So, we have two lists of user interest; we have no idea how
		// they are organized or separated. Typically we have a set of
		// separators, namely, "." "," ";" ":" "/" "*" "&"
		Pattern delims = Pattern.compile("\\.|,|;|:|/|\\&|\\*|\t|\n|\r|\f| ");
		// We will want to split each list into a set of strings,
		// turn them lower case, and remove any leading or trailing spaces.
		
		Object[] listGrabParam;
		String listGrab = "SELECT interests as value FROM facebook_user WHERE uid=?";
		StringValue[] interestRawList;
		TreeSet<String> list1;
		String[] arr1;
		Vector<String> ret;
		TreeSet<String> list2;

		try
		{
			listGrabParam = new Object[]{new Long(id1)};
			
			 // (0.A.i and 1)
			interestRawList = (StringValue[]) dao.query(listGrab, listGrabParam, StringValue.class);
			
			 // should have list of interests, then.
			String intrs1 = interestRawList[0].value;
			
			 // split it up. (0.A.ii and 3)
			arr1 = delims.split(intrs1, 0);
			
			// All comparisons are case-insensitive, so "MuSiC" will match "mUsIc".
			list1 = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
			
			 // created TreeSet -- it will have no duplicates.
			for (int a = 0; a < arr1.length; a++)
				list1.add(arr1[a].trim());
			
			 // (0)
			if (id1 == id2)
			{
				// (0.A)
				//arr1 = list1.toArray(new String[list1.size()]);
				ret = new Vector<String>();
				ret.addAll(list1);
				 // (0.A.iii)
				return ret;		
			}
			
			listGrabParam = new Object[]{new Long(id2)};
			
			interestRawList = (StringValue[]) dao.query(listGrab, listGrabParam, StringValue.class); // (2)
			
			 // should have second list of interests, then.
			String intrs2 = interestRawList[0].value;
			
			 // split it up. (4)
			arr1 = delims.split(intrs2, 0);
			
			 // intersection list.
			list2 = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
			
			String tempadd;
			for (int b = 0; b < arr1.length; b++)
			{
				tempadd = arr1[b].trim();
				
				 // only interested if this returns false, namely, intersection. (5)
				if(!list1.add(tempadd))
				{
					 // now, try to add to second list. Will only succeed if new (intersections discarded). (5.A. and 5.A.i)
					list2.add(tempadd);
				}
			}
			
			// Return the intersection array. (6)
			//arr1 = list2.toArray(new String[list2.size()]);
			ret = new Vector<String>();
			ret.addAll(list2);
			return ret;
		}
		catch (CoreDaoException e)
		{
			// error'd out on getting the lists.
			System.out.println("Data Core Exception");
			e.printStackTrace();
		}
		
		return new Vector<String>(); // (error'd out, return null.)
	}
	
	
	
	
	/**
	 * This function returns the social network distance between two users. The distance
	 * is (-1) if there is no connection.
	 * @param id1
	 * 		User Id for the first user
	 * @param id2
	 * 		User Id for the second user
	 * @return
	 * 		Social network distance between the two users. 
	 * 		The distance is (-1) if there is no connection.
	 * @author Daniel boston
	 */
	public Long getSocialNetworkDistance(Long id1, Long id2)
	{
		SmartCampusDAO dao = new SmartCampusDAO();
		
		// perform a search. How to optimize?
		// So, we are given two starting points, namely id1 and id2
		// We can get a list of friends of id1 (e1 (- list1)
		// We can get a list of friends of id2 (e2 (- list2)
		// This represents a distance of 1,
		//		if id2 is in list1
		//		if id1 is in list2
		// Else, if any e1 is equal to e2, there is a distance of 2.
	
		// PSEUDO-CODE (Daniel B. 2008)
		// Prospective algorithm:
		// Given: id1, id2
		// Assuming: There is a table of relationships between friend ids
		// Assign: Create an IndexHash called DistanceMatrix to store
		// 		friend IDs and the depths they were found at.
		// 		Create an Integer depth set equal to 0.
		//		Create an Integer maxDepth set to some reasonable value.
		// 0. Compare id1 to id2
		// 0.A. If id1 equals id2, return depth
		// 0.B. Else, add id1 to DistanceMatrix with value depth
		// 1. Set curIdx to 0
		// 2. While curIdx is less than sizeOf(DistanceMatrix) and depth less than or equal to maxDepth
		// 2.A. Set depth to GetValue(DistanceMatrix, curIdx) incremented by one (entry's depth plus one) 
		// 2.B. Assign tList as the friend list of GetKey(DistanceMatrix, curIdX)
		// 2.C. For each friend f1 in tList
		// 2.D.i. Compare f1 to id2
		// 2.D.ii.a. If equal, return depth
		// 2.D.ii.b. Else, add f1 to DistanceMatrix with value depth if not already in the matrix
		// 3. Compare curIdx and sizeOf(DistanceMatrix)
		// 3.A. If curIdx equals sizeOf(DistanceMatrix), there is no connection, return max Integer
		// 3.B. Else, return maxDepth. (questionable)
		
		IndexHash<Long, Long> distanceMatrix = new IndexHash<Long, Long>();
		
		Long depth = 0L;
		Long maxDepth = 10L;
		Object[] listGrabParam;
		String listGrab = "SELECT DISTINCT fid as user_id FROM facebook_friends WHERE uid=?";
		user_id[] listGrabbed;
		
		int curIdx;
		
		if (id1.longValue() == id2.longValue()) // 0.A.
		{
			return depth; // 0.B.
		}
		else
		{
			distanceMatrix.add(id1, new Long(depth)); // 0.C.
			
			curIdx = 0; // 1.
			while ((curIdx < distanceMatrix.size()) && (depth.compareTo(maxDepth) < 1)) // 2.
			{
				depth = new Long(distanceMatrix.get(curIdx) + 1); // get depth at this index (2.A.)
				
				// 2.B.
				listGrabParam = new Object[]{distanceMatrix.getKey(curIdx)};
				try
				{
					listGrabbed = (user_id[]) dao.query(listGrab, listGrabParam, user_id.class);
					
					for (int a = 0; a < listGrabbed.length; a++) // 2.C.
					{
						if (listGrabbed[a].user_id == id2.longValue()) // 2.C.i
						{
							// found.
							return depth; // 2.C.ii.a
						}
						else
						{
							distanceMatrix.add(listGrabbed[a].user_id, new Long(depth)); // 2.C.ii.b
						}
					}
					
					//increment the current index
					curIdx ++;
				}
				catch(CoreDaoException e)
				{
					System.out.println("Friend list grab failed for " + listGrabParam[0] + " at depth " + depth);
					curIdx = distanceMatrix.size(); // dump from loop.
				}
			}
		}
		
		if (curIdx >= distanceMatrix.size()) // 3.
		{
			return new Long(-1); // 3.A.
		}
		else
		{
			return depth; // 3.B.
		}
	}
	
	
	/**
	 * This is KSOAP compatibile wrapper function used get information pertaining to a social event
	 * @param group_id
	 * Event Id for the event for which information is required	
	 * @return
	 * KVMSocialEvent object containing social event information
	 * @author Maverick 
	 */
	public KVMSocialEvent getSocialEventInfo(Long event_id)
	{
		//get social event info from main API
		SocialEvent socialEventInfo = getSocialEventInfo(event_id.longValue());
		
		//transfer data into KVM object and return it
		KVMSocialEvent kvmSocialEventInfo = new KVMSocialEvent(); 
		kvmSocialEventInfo.setDescription(socialEventInfo.getDescription());
		kvmSocialEventInfo.setEid(socialEventInfo.getEid());
		kvmSocialEventInfo.setEnd_time(socialEventInfo.getEnd_time().getTime());
		kvmSocialEventInfo.setEvent_type(socialEventInfo.getEvent_type());
		kvmSocialEventInfo.setHost(socialEventInfo.getHost());
		kvmSocialEventInfo.setLocation(socialEventInfo.getLocation());
		kvmSocialEventInfo.setName(socialEventInfo.getName());
		kvmSocialEventInfo.setOwner(socialEventInfo.getOwner());
		kvmSocialEventInfo.setStart_time(socialEventInfo.getStart_time().getTime());
		kvmSocialEventInfo.setUpdate_time(socialEventInfo.getUpdate_time().getTime());
		return kvmSocialEventInfo; 
	}

	/**
	 * This API returns information pertaining to a social event
	 * @param event_id
	 * Event Id for the event for which information is required.
	 * @return
	 * This API returns information pertaining to a social event inside a SocialEvent object
	 * @author Maverick
	 */
	public SocialEvent getSocialEventInfo(long event_id)
	{
		SocialEvent socialEventInfo = new SocialEvent();
		
		Object[] params = new Object[] {
				new Long(event_id),
				};
		
		String query = "SELECT * FROM facebook_event " +
					   "WHERE eid=?";
		
		try {
			social_event[] socialEventInfoDB = (social_event[]) new SmartCampusDAO().query(query,params,social_event.class);
			
            if(socialEventInfoDB.length > 0)
            {
            	socialEventInfo.setDescription(socialEventInfoDB[0].description);
            	socialEventInfo.setEid(socialEventInfoDB[0].eid);
            	socialEventInfo.setEnd_time(socialEventInfoDB[0].end_time);
            	socialEventInfo.setEvent_type(socialEventInfoDB[0].event_type);
            	socialEventInfo.setHost(socialEventInfoDB[0].host);
            	socialEventInfo.setLocation(socialEventInfoDB[0].location);
            	socialEventInfo.setName(socialEventInfoDB[0].name);
            	socialEventInfo.setOwner(socialEventInfoDB[0].owner);
            	socialEventInfo.setStart_time(socialEventInfoDB[0].start_time);
            	socialEventInfo.setUpdate_time(socialEventInfoDB[0].update_time);
            }
		}catch(Exception e)
            {
            	e.printStackTrace();
            	System.out.println("Error while fetching social group info");
            	return null;
            }
	
		return socialEventInfo;
	}
	
	
	/**
	 *  This function is used to get the social contacts that are common between two users
	 * @param firstUserId
	 *   User Id of the first person whose groups we are going to consider.
	 * @param secondUserId
	 * 	 User Id of the second person whose groups we are going to consider.	
	 * @return
	 *   A vecotr that contains user id's (Long) for the common contacts
	 * @author Maverick 
	 */
	public Vector<Long> getCommonSocialContacts(Long firstUserId, Long secondUserId)
	{
		Vector<Long> commonSocialContacts = new Vector<Long>();
		
		Object[] params = new Object[] {
				new Long(firstUserId),
				new Long(secondUserId)
				};
		
		String query = "SELECT distinct A.fid as user_id " +
					   "FROM facebook_friends A, facebook_friends B " +
		               "WHERE A.uid=? " +
		               "AND B.uid=?" +
		               "AND A.fid=B.fid";
		               
		
		try{
			user_id[]commonSocialContactsDB = (user_id[]) new SmartCampusDAO().query(query,params,user_id.class);
			
			if(commonSocialContactsDB.length > 0)
			{
				for(int i=0; i<commonSocialContactsDB.length; i++)
				{
					commonSocialContacts.add(new Long(commonSocialContactsDB[i].user_id));
				}
			}
		}catch(Exception e)
		{
			System.out.println("Error while fetching common social contacts");
			e.printStackTrace();
			return null;
		}
		
		return commonSocialContacts;
	}
	
	
	
	
	/**
	 * This function is used to get the social groups that are common between two users
	 * @param firstUserId
	 *   User Id of the first person whose groups we are going to consider.
	 * @param secondUserId
	 * 	 User Id of the second person whose groups we are going to consider.	
	 * @return
	 *  A vector that contains the group id's (Long) for the common groups
	 * @author Maverick
	 */
	public Vector<Long> getCommonSocialGroups(Long firstUserId, Long secondUserId)
	{
		Vector<Long> commonSocialGroups = new Vector<Long>();
		
		Object[] params = new Object[] {
				new Long(firstUserId),
				new Long(secondUserId),
				};
		
		String query = "SELECT distinct A.gid as group_id " +
					   "FROM facebook_group_membership A, facebook_group_membership B " +
					   "WHERE A.gid=B.gid " +
					   "AND A.uid=? " +
					   "AND B.uid=? ";
		
		try{
			social_group_id[]commonSocialGroupsDB = (social_group_id[]) new SmartCampusDAO().query(query,params,social_group_id.class);
			
			if(commonSocialGroupsDB.length > 0)
			{
				for(int i=0; i<commonSocialGroupsDB.length; i++)
				{
					commonSocialGroups.add(new Long(commonSocialGroupsDB[i].group_id));
				}
			}
		}catch(Exception e)
		{
			System.out.println("Error while fetching common social groups");
			e.printStackTrace();
			return null;
		}
		
		return commonSocialGroups;
	}
	
	
	
	
	
	/**
	 * This is the KSOAP compatible version of getCopresenceHistory. 
	 * This function returns the co-presence history between two users in a given time frame. It 
	 * returns a vector of placeid, starttime and endtime. The placeid is the smallest place where 
	 * co-presence occured. ie if the users were copresent at a building inside a campus then the 
	 * placeid will return the building. There are important (pre-requisites) before using this API.
	 * The query is based on the assumtion that the placeid of the building is greater than that 
	 * of it's parent. Also it assumes that the function "extractCopresentUsers" from the data mining 
	 * service has already been invoked with the userid as the first user.   
	 * @param userIdOne
	 * 		User Id for the first user
	 * @param userIdTwo
	 * 		User Id for the second user
	 * @param searchStartDate
	 * 		Search start time (converted to milliseconds from a timestamp object)
	 * @param searchEndDate
	 * 		Search end time (converted to milliseconds from timestamp object) 
	 * @return
	 * A vector that contains the several objects of class KVMCopresenceHistory. 
	 * Each object represents a copresence instance with placeid, starttime and endtime
	 * The starttime and endtime are given in milliseconds (converted from timestamp object)
	 * @author Maverick 
	 * */
	public Vector getCopresenceHistory(Long userIdOne, Long userIdTwo, Long searchStartDate, Long searchEndDate)
	{
		// get copresence history from the main API
		Vector regularCopresenceHistory = getCopresenceHistory(userIdOne.longValue(), userIdTwo.longValue(),  searchStartDate.longValue(),  searchEndDate.longValue());
		
		//transfer the data into ksoap compatible vector
		Vector kvmCopresenceHistory = new Vector();
		
		for(int i=0; i<regularCopresenceHistory.size(); i++)
		{
			//create kvm copresence instance
			KVMCopresenceHistory kvmCopresenceInstance = new KVMCopresenceHistory();
			//create regular copresence instance
			CopresenceHistory regularCopresenceInstance = (CopresenceHistory)regularCopresenceHistory.get(i);
			//transfer data to kvm object
			kvmCopresenceInstance.setPlaceid(regularCopresenceInstance.getPlaceid());
			kvmCopresenceInstance.setStarttime(regularCopresenceInstance.getStarttime().getTime());
			kvmCopresenceInstance.setEndtime(regularCopresenceInstance.getEndtime().getTime());
			//add new object to vector
			kvmCopresenceHistory.add(kvmCopresenceInstance);
		}
		
		//return the new vector
		return  kvmCopresenceHistory;
	}
	
	
	
	/**
	 * This function returns the co-presence history between two users in a given time frame. It 
	 * returns a vector of placeid, starttime and endtime. The placeid is the smallest place where 
	 * co-presence occured. ie if the users were copresent at a building inside a campus then the 
	 * placeid will return the building. There are important (pre-requisites) before using this API.
	 * The query is based on the assumtion that the placeid of the building is greater than that 
	 * of it's parent. Also it assumes that the function "extractCopresentUsers" from the data mining 
	 * service has already been invoked with the userid as the first user.   
	 * @param userIdOne
	 * 		User Id for the first user
	 * @param userIdTwo
	 * 		User Id for the second user
	 * @param searchStartDate
	 * 		Search start time (converted to milliseconds from a timestamp object)
	 * @param searchEndDate
	 * 		Search end time (converted to milliseconds from timestamp object)
	 * @return
	 * 		A vector that contains the several objects of class copresenceHistory. 
	 * 		Each object represents a copresence instance with placeid, starttime and endtime
	 * @author Maverick
	 */
	public Vector getCopresenceHistory(long userIdOne, long userIdTwo, long searchStartDate, long searchEndDate)
	{
		Vector<CopresenceHistory> copresenceHistory = new Vector<CopresenceHistory>();
		
		Object[] params = new Object[] {
				new Long(userIdOne),
				new Long(userIdTwo),
				new Timestamp(searchStartDate),
				new Timestamp(searchEndDate),
				};
		
	    String query = "SELECT max(p.space_id) as placeid, utp.starttime as starttime, utp.endtime as endtime " +
	    			   "FROM gis_space p, gis_user_places up, gis_user_time_in_places utp " +
	    			   "WHERE utp.userid=? " +
	    			   "AND utp.group_user_id=? " +
	    			   "AND utp.starttime>? " +
	    			   "AND utp.endtime<? " +
	    			   "AND up.placeid = utp.placeid " +
	    			   "AND contains(p.location, up.location) "+
	    			   "GROUP BY utp.starttime, utp.endtime";
	    
		try{
			copresence_history[] copresenceHistoryDB = (copresence_history[]) new SmartCampusDAO().query(query,params,copresence_history.class);
			
			if(copresenceHistoryDB.length > 0)
			{
				for(int i=0; i<copresenceHistoryDB.length; i++)
				{
					CopresenceHistory newEntry = new CopresenceHistory();
					newEntry.setPlaceid(copresenceHistoryDB[i].placeid);
					newEntry.setStarttime(copresenceHistoryDB[i].starttime);
					newEntry.setEndtime(copresenceHistoryDB[i].endtime);
					
					copresenceHistory.add(newEntry);
				}
			}
		}catch(Exception e)
		{
			System.out.println("Error while fetching copresence history");
			e.printStackTrace();
			return null;
		}
		
		return copresenceHistory;
	}
	
	
	
	

	
	/**
	 * **************************************************************************************************
	 * **************************************************************************************************
	 * **************************************************************************************************
	 * *********************************Start Of the People-Place Afinity API*************************
	 * **************************************************************************************************
	 * **************************************************************************************************
	 * **************************************************************************************************
	 */
	
	/**
	 * This function returns all the people at a place in the given time frame
	 * @param placeId
	 * 		Place Id for the place where people are to be searched
	 * @param searchStartDate
	 * 		Start date for search in milliseconds (converted from timestamp object)
	 * @param searchEndDate
	 * 		End date for search in milliseconds (converted from timestamp object)
	 * @return
	 *      A Vector that contains the User Id's (Long) for all the users at a place
	 */
	public Vector<Long> getPeopleAtPlace(Long placeId, Long searchStartDate, Long searchEndDate)
	{
		Vector<Long> peopleAtPlace = new Vector<Long>();
		
		Object[] params = new Object[] {
				new Long(placeId),
				new Timestamp(searchStartDate),
				new Timestamp(searchEndDate),
				};
		
		String query = "SELECT distinct loc.user_id " +
					   "FROM gis_space sp, gis_location loc " +
		               "WHERE  sp.space_id=? " +
		               "AND contains(sp.location,loc.location) " +
		               "AND loc.time>? " +
		               "AND loc.time<? ";
		
		try{
			people_at_place[] peopleAtPlaceDB = (people_at_place[]) new SmartCampusDAO().query(query,params,people_at_place.class);
			
			if(peopleAtPlaceDB.length > 0)
			{
				for(int i=0; i<peopleAtPlaceDB.length; i++)
				{
					peopleAtPlace.add(new Long(peopleAtPlaceDB[i].user_id));
				}
			}
		}catch(Exception e)
		{
			System.out.println("Error while fetching people at place");
			e.printStackTrace();
			return null;
		}
		
		return peopleAtPlace;
	}
	

	
	/**
	 * This function returns a  user's social groups associated with the given place. 
	 * It searches for groups that are associated with a place or a subplace of that place (upto one level).
	 * This function can be modified to search for places more that one level.
	 * @param placeId
	 * 		Place Id for the place where groups are to be searched
	 * @param userId
	 *      User Id for the user whose gropus ar to be searched
	 * @return
	 * 		Returns a vector of group id's (Long) for the social groups at place
	 * @author Maverick
	 */
	public Vector<Long> getUserSocialGroupsAtPlace(Long placeId, Long userId)
	{
		Vector<Long> socialGroupsAtPlace = new Vector<Long>();
		
		Object[] params = new Object[] {
			userId,
			placeId,
			placeId
			};
	
	String query = "SELECT distinct(facebook_group.gid) AS group_id " 
		         + "FROM facebook_group, facebook_group_membership, gis_space "
		         + "WHERE gis_space.space_id = facebook_group.location "
		         + "AND facebook_group_membership.uid=? "
		         + "AND facebook_group.gid = facebook_group_membership.gid "
				 + "AND (gis_space.space_id=?  OR gis_space.parent_id=?)";
	
	try{
		social_group_id[] socialGroupsDiscovered = (social_group_id[]) new SmartCampusDAO().query(query,params,social_group_id.class);
		
		//insert the discovered social events in the vector
		if(socialGroupsDiscovered.length > 0)
		{
			for(int i=0;i<socialGroupsDiscovered.length;i++)
			{
				socialGroupsAtPlace.add(new Long(socialGroupsDiscovered[i].group_id));
			}
		}
	}catch(Exception e)
	{
		e.printStackTrace();
		System.out.println("Error while fetching social groups at place");
		return null;
	}
	
	return socialGroupsAtPlace;
	}
	
	/**
	 * This function returns the social groups associated with the place.
	 * It searches for groups that are associated with a place or a subplace of that place (upto one level).
	 * This function can be modified to search for places more that one level.
	 * @param placeId
	 * 		Place Id for the place where groups are to be searched
	 * @return
	 * 		Returns a vector of group id's (Long) for the social groups at place
	 * @author Maverick
	 */
	public Vector<Long> getSocialGroupsAtPlace(Long placeId)
	{
		Vector<Long> socialGroupsAtPlace = new Vector<Long>();
		
		Object[] params = new Object[] {
			new Long(placeId),
			new Long(placeId)
			};
	
	String query = "SELECT gid AS group_id " 
		         + "FROM facebook_group, gis_space "
		         + "WHERE gis_space.space_id = facebook_group.location "
				 + "AND (gis_space.space_id=?  OR gis_space.parent_id=?)";
	
	try{
		social_group_id[] socialGroupsDiscovered = (social_group_id[]) new SmartCampusDAO().query(query,params,social_group_id.class);
		
		//insert the discovered social events in the vector
		if(socialGroupsDiscovered.length > 0)
		{
			for(int i=0;i<socialGroupsDiscovered.length;i++)
			{
				socialGroupsAtPlace.add(new Long(socialGroupsDiscovered[i].group_id));
			}
		}
	}catch(Exception e)
	{
		e.printStackTrace();
		System.out.println("Error while fetching social groups at place");
		return null;
	}
	
	return socialGroupsAtPlace;
	}
	
	
	
	
	/**
	 * This is the KSOAP compatible wrapper for the getUserPlaceHistory method
	 * This function returns the various vists by a user at a given place in a given period.
	 * It returns a vector that contains the placeid, starttime and endtime for each visit.
	 * Note here that the placeid for each visit is a user specific placeid and not the
	 * general placeid that we assign to places. We asign user speicific place id's to all
	 * significant places for a user. This is used to account for the fact that the user
	 * might have more than one significant places inside a system defined places. For
	 * example a student might have more than one significant places inside his department
	 * building. Impotantly before using this API, the "extractPlaces" API from the DataMining 
	 * service should be invoked for the given uer betwen the given time frame.
	 * @param userId
	 * 		User Id for the user whose visits are to be detected 
	 * @param placeId
	 * 		Place Id for the place where user visits are to be detected
	 * @param searchStartDate
	 * 		Search start date in milliseconds (converted from timestamp object)
	 * @param searchEndDate
	 * 		Search end date in milliseconds (converted from timestamp object)
	 * @return
	 *   This function returns a vector that contains the visit startTime an endTime for every visit.
	 *   Addtionally for each visit, it contains a user-specific place id. The information is contained
	 *   inside a UserPlaceHistory object.
	 * @author Maverick
	 */
	public Vector getUserPlaceHistory(Long userId, Long placeId, Long searchStartDate, Long searchEndDate)
	{
			//get copresence history from the main API
			Vector regularUserPlaceHistory = getUserPlaceHistory(userId.longValue(), placeId.longValue(),  searchStartDate.longValue(),  searchEndDate.longValue());
			
			//transfer the data into ksoap compatible vector
			Vector kvmUserPlaceHistory = new Vector();
			
			for(int i=0; i<regularUserPlaceHistory.size(); i++)
			{
				//create kvm UserPlace instance
				KVMUserPlaceHistory kvmUserPlaceInstance = new KVMUserPlaceHistory();
				//create regular UserPlace instance
				UserPlaceHistory regularUserPlaceInstance = (UserPlaceHistory)regularUserPlaceHistory.get(i);
				//transfer data to kvm object
				kvmUserPlaceInstance.setPlaceId(regularUserPlaceInstance.getPlaceId());
				kvmUserPlaceInstance.setStartTime(regularUserPlaceInstance.getStartTime().getTime());
				kvmUserPlaceInstance.setEndTime(regularUserPlaceInstance.getEndTime().getTime());
				//add new object to vector
				kvmUserPlaceHistory.add(kvmUserPlaceInstance);
			}

			//return the new vector
			return  kvmUserPlaceHistory;
	}
	
	/**
	 * This function returns the various vists by a user at a given place in a given period.
	 * It returns a vector that contains the placeid, starttime and endtime for each visit.
	 * Note here that the placeid for each visit is a user specific placeid and not the
	 * general placeid that we assign to places. We asign user speicif place id's to all
	 * significant places for a user. This is used to account for the fact that the user
	 * might have more than one significant places inside a system defined places. For
	 * example a student might have more than one significant places inside his department
	 * building. Impotantly before using this API, the "extractPlaces" API from the DataMining 
	 * service should be invoked for the given uer betwen the given time frame.
	 * @param userId
	 * 		User Id for the user whose visits are to be detected 
	 * @param placeId
	 * 		Place Id for the place where user visits are to be detected
	 * @param searchStartDate
	 * 		Search start date in milliseconds (converted from timestamp object)
	 * @param searchEndDate
	 * 		Search end date in milliseconds (converted from timestamp object)
	 * @return
	 *   This function returns a vector that contains the visit startTime an endTime for every visit
	 *   Addtionally for each visit, it contains a user-specific place id. The information is contained
	 *   inside a UserPlaceHistory object.
	 * @author Maverick
	 */
	public Vector getUserPlaceHistory(long userId, long placeId, long searchStartDate, long searchEndDate)
	{
		Vector<UserPlaceHistory> userPlaceHistory = new Vector<UserPlaceHistory>();
		
		Object[] params = new Object[] {
				new Long(placeId),
				new Long(userId),
				new Long(userId),
				new Timestamp(searchStartDate),
				new Timestamp(searchEndDate),
				};
		
		String query = "SELECT * from gis_user_time_in_places " + 
					   "WHERE placeid IN " + 
					   		" (SELECT distinct(gis_user_places.placeid) FROM gis_user_places, gis_space " + 
					   		  "WHERE contains(gis_space.location,gis_user_places.location) " +
					   		  "AND gis_space.space_id=? " +
					   		  "AND gis_user_places.userid=?) "+
					   "AND userid=? " +
					   "AND startTime>? " +
					   "AND endtime<? " +
					   "AND group_user_id =-1"; 
			
		try{
			gis_user_time_in_places[] userPlaceHistoryDB = (gis_user_time_in_places[]) new SmartCampusDAO().query(query,params,gis_user_time_in_places.class);
		
			if(userPlaceHistoryDB.length > 0)
			{
				for(int i=0;i<userPlaceHistoryDB.length;i++)
				{
					UserPlaceHistory newPlaceVisit = new UserPlaceHistory();
					newPlaceVisit.setPlaceId(userPlaceHistoryDB[i].placeid);
					newPlaceVisit.setStartTime(userPlaceHistoryDB[i].starttime);
					newPlaceVisit.setEndTime(userPlaceHistoryDB[i].endtime);
					userPlaceHistory.add(newPlaceVisit);
				}
			}
		}catch(Exception e)
		{
			System.out.println("Error while geting user place history");
			e.printStackTrace();
			return null;
		}
		return userPlaceHistory;
	}
	
	
	
	
	
	

	/**
	 * **************************************************************************************************
	 * **************************************************************************************************
	 * **************************************************************************************************
	 * *********************************Start Of the Place Profiling API*************************
	 * **************************************************************************************************
	 * **************************************************************************************************
	 * **************************************************************************************************
	 */
	
	/**
	 * This is the KSOAP ompatible wrapper for the main API. 
	 * This function is used to get information about a place  
	 * @param place_id
	 * Place id for the place whose information is required
	 * @return
	 * Place data inside KVMPlaceData object.Note here that the location vector inside 
	 * the KVMPlaceData object hold KVMCoordinates instead of Coordinates object
	 * @author Maverick
	 */
	public KVMPlaceData getPlaceInfo(Long place_id)
	{
		//get place info from main API
		PlaceData placeInfo = getPlaceInfo(place_id.longValue());
		
		//transfer for regular location vector to kvm vector
		Vector regularLocationVector = placeInfo.getLocation(); 
		Vector kvmLocationVector = new Vector();
		
		for(int i=0; i< regularLocationVector.size() ; i++)
		{
			Coordinates regularCoordinates = (Coordinates)regularLocationVector.get(i);
			
			KVMCoordinates kvmCoordinates = new KVMCoordinates();
			kvmCoordinates.setAlt(new Float(regularCoordinates.getAltitude()).toString());
			kvmCoordinates.setLat(new Double(regularCoordinates.getLatitude()).toString());
			kvmCoordinates.setLon(new Double(regularCoordinates.getLongitude()).toString());
			
			kvmLocationVector.add(kvmCoordinates);
		}
		
		//transfer data into KVM object and return it
		KVMPlaceData kvmPlaceInfo = new KVMPlaceData();
		
		//insert data into kvmobject
		kvmPlaceInfo.setLocation(kvmLocationVector);
		kvmPlaceInfo.setName(placeInfo.getName());
		kvmPlaceInfo.setParent_id(placeInfo.getParent_id());
		kvmPlaceInfo.setPlace_id(placeInfo.getPlace_id());
		kvmPlaceInfo.setType(placeInfo.getType());
		
		//return kvm place info
		return kvmPlaceInfo; 
	}
	
	
	/**
	 * This function is used to get information about a place  
	 * @param place_id
	 * Place id for the place whose information is required
	 * @return
	 * Place data inside KVMPlaceData object.
	 * @author Maverick
	 */
	public PlaceData getPlaceInfo(long place_id)
	{
		PlaceData fetchedPlaceData = new PlaceData();
		
		
		
		Object[] params = new Object[] {
				new Long(place_id),
				};
	
		String query = "SELECT * FROM gis_space " +
					   "WHERE space_id=?";

		
		try {
			place_data[] fetchedPlaceDataDB = (place_data[]) new SmartCampusDAO().query(query,params,place_data.class);
			
            if(fetchedPlaceDataDB.length > 0)
            {
            	//convert the fetched location from PgGeomtry to a vector of points (coordinates)
            	Vector<Coordinates> listOfPoints = new Vector<Coordinates>();
            	
            	PGgeometry tempPGGeometry = fetchedPlaceDataDB[0].location;
            	Geometry tempGeometry = tempPGGeometry.getGeometry();
            	
            	for(int i=0;i<tempGeometry.numPoints(); i++)
            	{
            		Point tempPoint = tempGeometry.getPoint(i);
            		Coordinates tempCoordinates = new Coordinates(tempPoint.getY(), tempPoint.getX(), 0);
            		listOfPoints.add(tempCoordinates);
            	}
            	
            	//ad place data to class
            	fetchedPlaceData.setName(fetchedPlaceDataDB[0].name);
            	fetchedPlaceData.setParent_id(fetchedPlaceDataDB[0].parent_id);
            	fetchedPlaceData.setPlace_id(fetchedPlaceDataDB[0].space_id);
            	fetchedPlaceData.setType(fetchedPlaceDataDB[0].type);
            	fetchedPlaceData.setLocation(listOfPoints);
            }
		}catch(Exception e)
            {
            	e.printStackTrace();
            	System.out.println("Error while fetching place data");
            	return null;
            }
		return fetchedPlaceData;
	}
	
	
	/**
	 * This function returns places that have a name that matches any of the keywords
	 * @param keywords
	 * 	Vector contaning strings of keywords
	 * @return
	 * 	Vector that contains place id (Long) of places whose name matches the keywords
	 * @author Maverick
	 */
	public Vector<Long> searchPlaces(Vector<String> keywords)
	{
		Vector<Long> discoveredPlaces = new Vector<Long>();
		
		//basic query - note that the first where condition is always false
		StringBuffer queryBuffer = new StringBuffer("SELECT space_id as place_id FROM gis_space WHERE space_id=-1 ");
		
		//extend query to search for keywords
		for(int i=0;i<keywords.size();i++)
		{
			queryBuffer.append("OR name LIKE '%" + (String)keywords.get(i) + "%' ");
		}
		
		String query = queryBuffer.toString();
		
		try{
			place_id[] discoveredPlacesDB = (place_id[]) new SmartCampusDAO().query(query,place_id.class);
			
			if(discoveredPlacesDB.length > 0)
			{
				for(int i=0; i<discoveredPlacesDB.length; i++)
				{
					discoveredPlaces.add(new Long(discoveredPlacesDB[i].place_id));
				}
			}
		}catch(Exception e)
		{
			System.out.println("Error while searching for places");
			e.printStackTrace();
			return null;
		}
		
		return discoveredPlaces;	
	}	
	
	
	/**
	 * This function returns social events that have a name that matches any of the keywords
	 * @param keywords
	 * 	Vector contaning strings of keywords
	 * @return
	 * 	Vector that contains Event Id's(Long) of events whose name matches the keywords
	 * @author Maverick
	 */
	public Vector<Long> searchSocialEvents(Vector<String> keywords)
	{
		Vector<Long> discoveredSocialEvents = new Vector<Long>();
		
		//basic query - note that the first where condition is always false
		StringBuffer queryBuffer = new StringBuffer("SELECT eid as event_id FROM facebook_event WHERE eid=-1 ");
		
		//extend query to search for keywords
		for(int i=0;i<keywords.size();i++)
		{
			queryBuffer.append("OR name LIKE '%" + (String)keywords.get(i) + "%' ");
		}
		
		String query = queryBuffer.toString();
		
		try{
			social_event_id[] discoveredSocialEventsDB = (social_event_id[]) new SmartCampusDAO().query(query,social_event_id.class);
			
			if(discoveredSocialEventsDB.length > 0)
			{
				for(int i=0; i<discoveredSocialEventsDB.length; i++)
				{
					discoveredSocialEvents.add(new Long(discoveredSocialEventsDB[i].event_id));
				}
			}
		}catch(Exception e)
		{
			System.out.println("Error while searching social events");
			e.printStackTrace();
			return null;
		}
		
		return discoveredSocialEvents;	
	}	
	
	
	
//	/**
//	 * ** This function returns a vector that contains the demographics of people that visited a place for each day between a given time period
//	 *    Each entry in the vector represent the number of males and number of females for each day within the time frame
//	 * ** This function can be extended to search for other demgraphic patterns. 
//	 * ** The time frame is specified by the start time and the end time
//	 *    Addtionally you can optionally specify the hours of the day in which to do the search given by startHour and endHour
//	 *    Therefore we have tow overloaded methods with optional hour of the day
//	 * **
//	 * ** Important Caveat
//	 * **
//	 * This function assumes that the time period that you specify will be within a particular year.
//	 * Therefore your time period cannot span two or more different years. 
//	 *    
//	 * @param placeId
//	 * @param searchStartDate
//	 * @param searchEndDate
//	 * @param startHour
//	 * @param endHour
//	 * @return
//	 */

	//	public Vector getPlaceDemographicsPatterns(long placeId, Timestamp searchStartDate, Timestamp searchEndDate, long startHour, long endHour)
//	{
//		Vector<Long> placeAttendance = new Vector<Long>();
//		
//		Object[] params = new Object[] {
//				new Long(placeId),
//				searchStartDate,
//				searchEndDate,
//				startHour,
//				endHour
//				};
//		
//		
//		String query = "SELECT count(distinct loc.user_id) as count, extract(doy from time) as doy " +
//					   "FROM gis_space sp, gis_location loc " +
//		               "WHERE  sp.space_id=? " +
//		               "AND contains(sp.location,loc.location) " +
//		               "AND loc.time>? " +
//		               "AND loc.time<? " +
//					   "AND extract(hour from loc.time)>? " + 
//					   "AND extract(hour from loc.time)<? " + 
//					   "GROUP BY doy " +
//					   "ORDER BY doy";
//		
//		try{
//			place_attendance_patterns[] placeAttendanceDB = (place_attendance_patterns[]) new SmartCampusDAO().query(query,params,place_attendance_patterns.class);
//			
//			if(placeAttendanceDB.length > 0)
//			{
//				for(int i=0; i<placeAttendanceDB.length; i++)
//				{
//					placeAttendance.add(new Long(placeAttendanceDB[i].count));
//				}
//			}
//		}catch(Exception e)
//		{
//			System.out.println("Error while fetching place attendance patterns");
//			e.printStackTrace();
//			return null;
//		}
//		
//		return placeAttendance;
//	}
	
	
	
	/**
	 * This function returns a vector that contains the number of people that visited a place for 
	 * each day between a given time period. Each entry in the vector represent the number of people 
	 * for each day within the time frame. The time frame is specified by the start time and the end time
	 * Addtionally you can optionally specify the hours of the day in which to do the search given 
	 * by startHour and endHour. This function assumes that the time period that you specify will be 
	 * within a particular year. Therefore your time period cannot span two or more different years. 
	 * @param placeId
	 * 		Place Id for the place where attendance pattern is to be searched
	 * @param searchStartDate
	 * 		Start date for search in milliseconds (converted from timestamp object)
	 * @param searchEndDate
	 * 		End date for search in milliseconds (converted from timestamp object)
	 * @param startHour
	 * 		Start hour for search
	 * @param endHour
	 * 		End hour for search
	 * @return
	 * 		Vector that contains the number of people at the place (Long) for each day from
	 * 		start date and end time (inclusive) and also start hour and end hour (inclusive)
	 * @author Maverick
	 */
	public Vector getPlaceAttendancePatterns(Long placeId, Long searchStartDate, Long searchEndDate, Long startHour, Long endHour)
	{
		Vector<Long> placeAttendance = new Vector<Long>();
		
		Object[] params = new Object[] {
				new Long(placeId),
				new Timestamp(searchStartDate),
				new Timestamp(searchEndDate),
				startHour,
				endHour
				};
		
		
		String query = "SELECT count(distinct loc.user_id) as count, extract(doy from time) as doy " +
					   "FROM gis_space sp, gis_location loc " +
		               "WHERE  sp.space_id=? " +
		               "AND contains(sp.location,loc.location) " +
		               "AND loc.time>=? " +
		               "AND loc.time<=? " +
					   "AND extract(hour from loc.time)>=? " + 
					   "AND extract(hour from loc.time)<=? " + 
					   "GROUP BY doy " +
					   "ORDER BY doy";
		
		try{
			place_attendance_patterns[] placeAttendanceDB = (place_attendance_patterns[]) new SmartCampusDAO().query(query,params,place_attendance_patterns.class);
			
			if(placeAttendanceDB.length > 0)
			{
				for(int i=0; i<placeAttendanceDB.length; i++)
				{
					placeAttendance.add(new Long(placeAttendanceDB[i].count));
				}
			}
		}catch(Exception e)
		{
			System.out.println("Error while fetching place attendance patterns");
			e.printStackTrace();
			return null;
		}
		
		return placeAttendance;
	}
	
	
	
	/**
	 * This function returns a vector that contains the number of people that visited a place for 
	 * each day between a given time period. Each entry in the vector represent the number of people
	 * for each day within the time frame. The time frame is specified by the start time and the end time.
	 * This function assumes that the time period that you specify will be within a particular year.
	 * Therefore your time period cannot span two or more different years. 
	 * @param placeId
	 * 		Place Id for the place where aatttendance pattern is to be searched
	 * @param searchStartDate
	 * 		Start date for search in milliseconds (converted from timestamp object)
	 * @param searchEndDate
	 * 		End date for search in milliseconds (converted from timestamp object)
	 * @param startHour
	 * 		Start hour for search
	 * @param endHour
	 * 		End hour for search
	 * @return
	 * 		Vector that contains the number of people at the place (Long) for each day from
	 * 		start date and end time (inclusive) 
	 * @author Maverick
	 */
	public Vector getPlaceAttendancePatterns(Long placeId, Long searchStartDate, Long searchEndDate)
	{
		Vector<Long> placeAttendance = new Vector<Long>();
		
		Object[] params = new Object[] {
				new Long(placeId),
				new Timestamp(searchStartDate),
				new Timestamp(searchEndDate),
				};
		
		
		String query = "SELECT count(distinct loc.user_id) as count, extract(doy from time) as doy " +
					   "FROM gis_space sp, gis_location loc " +
		               "WHERE  sp.space_id=? " +
		               "AND contains(sp.location,loc.location) " +
		               "AND loc.time>=? " +
		               "AND loc.time<=? " + 
					   "GROUP BY doy " +
					   "ORDER BY doy";
		
		try{
			place_attendance_patterns[] placeAttendanceDB = (place_attendance_patterns[]) new SmartCampusDAO().query(query,params,place_attendance_patterns.class);
			
			if(placeAttendanceDB.length > 0)
			{
				for(int i=0; i<placeAttendanceDB.length; i++)
				{
					placeAttendance.add(new Long(placeAttendanceDB[i].count));
				}
			}
		}catch(Exception e)
		{
			System.out.println("Error while fetching place attendance patterns");
			e.printStackTrace();
			return null;
		}
		
		return placeAttendance;
	}
	
	
	
	
	
	/**
	 * This function returns the events that were associated with a place in a given period. 
	 * It searches for events that are associated with a place or a subplace of that place (upto one level).
	 * This function can be modified to search for places at more than one level.  
	 * @param place_id
	 * 		Place Id for the place at which event are to be searched
	 * @param startTime
	 * 		Start Time for the search period converted to a long value
	 * @param endTime
	 * 		End time for the search period coverted to a long value
	 * @return
	 *   	Returns a vector of associated event id's (Long). 
	 * @author 
	 * 		Maverick
	 */
	public Vector<Long> getSocialEventsPlaceHistory(Long placeId, Long searchStartDate, Long searchEndDate)
	{
		Vector<Long> associatedEvents = new Vector<Long>();
		
		Object[] params = new Object[] {
				new Timestamp(searchStartDate),
				new Timestamp(searchEndDate),
				new Long(placeId),
				new Long(placeId)
				};
		
		String query = "SELECT eid AS event_id " 
			         + "FROM facebook_event, gis_space "
			         + "WHERE facebook_event.start_time >=? "
			         + "AND facebook_event.end_time <=? "
			         + "AND gis_space.space_id = facebook_event.location "
					 + "AND (gis_space.space_id=?  OR gis_space.parent_id=?)";
		
		try{
			social_event_id[] socialEventsDiscovered = (social_event_id[]) new SmartCampusDAO().query(query,params,social_event_id.class);
			
			//insert the discovered social events in the vector
			if(socialEventsDiscovered.length > 0)
			{
				for(int i=0;i<socialEventsDiscovered.length;i++)
				{
					associatedEvents.add(new Long(socialEventsDiscovered[i].event_id));
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Error while fetching social events place history");
			return null;
		}
		return associatedEvents;
	}
	

	/**
	 * This function return events that are with a fixed distance of a place. 
	 * The distance between the event and the place is the distance between the
	 * centroid of the home place and the place where the event is held 
	 * @param placeId
	 * We search for events close to place given by this paramter
	 * @param distance
	 * The places should be within distance given by this parameter
	 * @return
	 *   It returns a vector that has eventId's and distances of the close events discovered
	 *   and the distance to these events. The object is "NearbyEvent"
	 * 	 Note here that the distance to place is converted from a double to string
	 * 	 because KSOAP does not support double values
	 * @author Maverick
	 */
	public Vector getNearbyEvents(Long placeId, Long distanceToPlace)
	{
		//get copresence history from the main API
		Vector regularNearbyEvents = getNearbyEvents(placeId.longValue(), distanceToPlace.longValue()); 
		
		//transfer the data into ksoap compatible vector
		Vector kvmNearbyEvents = new Vector();
		
		for(int i=0; i<regularNearbyEvents.size(); i++)
		{
			//create kvm copresence instance
			KVMNearbyEvent kvmNearbyEventInstance = new KVMNearbyEvent();
			//create regular copresence instance
			NearbyEvent regularNearbyEventInstance = (NearbyEvent)regularNearbyEvents.get(i);
			//transfer data to kvm object
			kvmNearbyEventInstance.setEventId(regularNearbyEventInstance.getEventId());
			kvmNearbyEventInstance.setDistanceToEvent(new Double(regularNearbyEventInstance.getDistanceToEvent()).toString());
			//add new object to vector
			kvmNearbyEvents.add(kvmNearbyEventInstance);
		}
		
		//return the new vector
		return  kvmNearbyEvents;
	}
	
	
	/**
	 * This function return events that are with a fixed distance of a place. 
	 * The distance between the event and the place is the distance between the
	 * centroid of the home place and the place where the event is held 
	 * @param placeId
	 * We search for events close to place given by this paramter
	 * @param distance
	 * The places should be within distance given by this parameter
	 * @return
	 * It returns a vector that has eventId's and distances of the close events 
	 * discovered and the distance to these events
	 * @author Maverick
	 */
	public Vector getNearbyEvents(long placeId, long distanceToEvent)
	{
		Vector<NearbyEvent> nearbyEventsVector = new  Vector<NearbyEvent>();
		
		Object[] params = new Object[] {
				new Long(placeId),
				new Double(distanceToEvent)
				};
		
		String query = "SELECT eid as event_id, distance_spheroid(centroid(space_a.location), centroid(space_b.location),'SPHEROID[\"WGS_1984\",6378137,298.257223563]') as distance_to_event " 
			         + "FROM gis_space space_a, gis_space space_b, facebook_event "
			         + "WHERE space_a.space_id = facebook_event.location AND "
			         + "space_b.space_id=? AND "
			         + "distance_spheroid(centroid(space_a.location), centroid(space_b.location),'SPHEROID[\"WGS_1984\",6378137,298.257223563]') < ?"; 
		
		try{
			nearby_event[] nearbyEvents= (nearby_event[]) new SmartCampusDAO().query(query,params,nearby_event.class);
			
			//insert the nearby places into the vector
			if(nearbyEvents.length > 0)
			{
				for(int i=0;i<nearbyEvents.length;i++)
				{
					NearbyEvent newNearbyEvent = new NearbyEvent();
					newNearbyEvent.setEventId(nearbyEvents[i].event_id);
					newNearbyEvent.setDistanceToEvent(nearbyEvents[i].distance_to_event);
					nearbyEventsVector.add(newNearbyEvent);
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Error while fetching nearby events");
			return null;
		}
		
		//return the vector of nearby places
		return nearbyEventsVector;
	}
	
	
	/**
	 * This is KSOAP compatible version of the main API. This function return places that are with 
	 * a fixed distance of a place. The distance between two places is the distance between thier centroids.
	 * @param placeId
	 * We search for places close to place given by this paramter
	 * @param distanceToPlace
	 * The places should be within distance given by this parameter
	 * @return
	 * A vector of objects (KVMNearbyPlace) that contain placeId'd and distance to places, 
	 * for the discovered places. Note here that the distance to place is coverted from a 
	 * double to string because KSOAP does not support double values
	 * @author Maverick
	 */
	public Vector getNearbyPlaces(Long placeId, Long distanceToPlace)
	{
		//get copresence history from the main API
		Vector regularNearbyPlaces = getNearbyPlaces(placeId.longValue(), distanceToPlace.longValue()); 
		
		//transfer the data into ksoap compatible vector
		Vector kvmNearbyPlaces = new Vector();
		
		for(int i=0; i<regularNearbyPlaces.size(); i++)
		{
			//create kvm copresence instance
			KVMNearbyPlace kvmNearbyPlaceInstance = new KVMNearbyPlace();
			//create regular copresence instance
			NearbyPlace regularNearbyPlaceInstance = (NearbyPlace)regularNearbyPlaces.get(i);
			//transfer data to kvm object
			kvmNearbyPlaceInstance.setPlaceId(regularNearbyPlaceInstance.getPlaceId());
			kvmNearbyPlaceInstance.setDistanceToPlace(new Double(regularNearbyPlaceInstance.getDistanceToPlace()).toString());
			//add new object to vector
			kvmNearbyPlaces.add(kvmNearbyPlaceInstance);
		}
		
		//return the new vector
		return  kvmNearbyPlaces;
	}
	
	
	/**
	 * This function return places that are with a fixed distance of a place. 
	 * The distance between two places is the distance between thier centroids. 
	 * @param placeId
	 * We search for places close to place given by this paramter
	 * @param distance
	 * The places should be within distance given by this parameter
	 * @return
	 *   It returns a vector that has placeId's of the close places discovered
	 *   and the distance to these places. The information is stored in an object
	 *   called "NearbyPlace"
	 * @author Maverick
	 */
	public Vector getNearbyPlaces(long placeId, long distanceToPlace)
	{
		Vector<NearbyPlace> nearbyPlacesVector = new  Vector<NearbyPlace>();
		
		Object[] params = new Object[] {
				new Long(placeId),
				new Double(distanceToPlace)
				};
		
		String query = "SELECT space_a.space_id as place_id, distance_spheroid(centroid(space_a.location), centroid(space_b.location),'SPHEROID[\"WGS_1984\",6378137,298.257223563]') as distance_to_place " 
			         + "FROM gis_space space_a, gis_space space_b "
			         + "WHERE space_a.space_id != space_b.space_id AND "
			         + "space_b.space_id=? AND "
			         + "distance_spheroid(centroid(space_a.location), centroid(space_b.location),'SPHEROID[\"WGS_1984\",6378137,298.257223563]') < ?"; 
		
		try{
			nearby_place[] nearbyPlaces= (nearby_place[]) new SmartCampusDAO().query(query,params,nearby_place.class);
			
			//insert the nearby places into the vector
			if(nearbyPlaces.length > 0)
			{
				for(int i=0;i<nearbyPlaces.length;i++)
				{
					NearbyPlace newNearbyPlace = new NearbyPlace();
					newNearbyPlace.setPlaceId(nearbyPlaces[i].place_id);
					newNearbyPlace.setDistanceToPlace(nearbyPlaces[i].distance_to_place);
					nearbyPlacesVector.add(newNearbyPlace);
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Error while fetching nearby places");
			return null;
		}
		
		//return the vector of nearby places
		return nearbyPlacesVector;
	}
	
	
//	public static void main(String args[])
//	{
//		SocialStateLearningService test = new SocialStateLearningService();
//		
//		/**
//		 * test to get nearby places
//		 */
////		Vector v = test.getNearbyPlaces(2, 300.5);
////		for(int i=0;i<v.size();i++)
////		{
////			NearbyPlace np = (NearbyPlace)v.get(i);
////			System.out.println(np.getPlaceId());
////			System.out.println(np.getDistanceToPlace());
////		}
//		
//		
//		/**
//		 * test to get nearby events
//		 */
////		Vector v2 = test.getNearbyEvents(2, 500.5);
////		for(int i=0;i<v2.size();i++)
////		{
////			NearbyEvent np = (NearbyEvent)v2.get(i);
////			System.out.println(np.getEventId());
////			System.out.println(np.getDistanceToEvent());
////		}
//		
//		
//		/**
//		 * test to get events at a place
//		 */
////		Timestamp startTime = Timestamp.valueOf("2007-09-22 00:00:00");
////		Timestamp endTime = Timestamp.valueOf("2009-10-30 13:01:00");
////		
////		Vector v3 = test.getSocialEventsPlaceHistory(1, startTime, endTime);
////		for(int i=0;i<v3.size();i++)
////		{
////			System.out.println(v3.get(i));
////		}		
//		
//		
//		/**
//		 * test to get user place hsitory
//		 * 		 */
////		Timestamp startTime = Timestamp.valueOf("2007-09-22 00:00:00");
////		Timestamp endTime = Timestamp.valueOf("2009-10-30 13:01:00");
////		
////		Vector v3 = test.getUserPlaceHistory(1001,0, startTime, endTime);
////		for(int i=0;i<v3.size();i++)
////		{
////			System.out.println(v3.get(i));
////		}
//		
//		/**
//		 * test to get events at a place
//		 */
////		Timestamp startTime = Timestamp.valueOf("2007-09-22 00:00:00");
////		Timestamp endTime = Timestamp.valueOf("2009-10-30 13:01:00");
////		
////		Vector v3 = test.getSocialGroupsAtPlace(1);
////		for(int i=0;i<v3.size();i++)
////		{
////			System.out.println(v3.get(i));
////		}	
//		
//		
//		/**
//		 * test to check for copresence history
//		 */
//	}
	
}
