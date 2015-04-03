package serviceFunctionality.privacyService;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

import edu.njit.sc.core.dao.SmartCampusDAO;
import ksoapDataObjects.privacyService.*;
import dataObjects.privacyService.*;

/**
 * AccessControlModule class
 * NOTE: This class has the logic for checking the access control statement that apply to the passed in condition
 * and returns whether the access to the infomation is allowed or disallowed depending on the setting (access control statments) 
 * by the user.
 * If the access is not specified the method checkIfAccessAllowed() returns default rule set by the system.
 * The default rule is: if the secondary is same as primary then allow the access else disallow the access.
 * @author NeerajR.
 *
 */

public class AccessControlModule {
	
	String szQueryToGetAC_StRows = "select acm_ac_st_id, acm_pe_id, acm_se_id, acm_access_allowed, hinfo_location, hinfo_time_based_events, hinfo_loc_based_events, hinfo_all_events, hinfo_user_profile_name , ar_loc_based, ar_loc_longitude, ar_loc_latitute, ar_loc_height, ar_time_based, ar_time_start_time, ar_time_end_time, ar_time_date, ar_co_loc_based, ar_co_loc_user_id, ar_co_loc_distance, ar_time_date_of_the_month, ar_time_month_of_the_year, ar_time_frequency, ar_time_day_of_the_week, att_type_primary_msg, att_typ_secondary_msg, att_type_sys_msg, att_primary_msg, att_secondary_msg, att_sys_msg from ac_access_control_mgr, ac_hide_info, ac_access_restrictions, ac_action_to_take where (acm_pe_id = ? AND acm_se_id = ? AND acm_st_enabled = true AND acm_ac_st_id = hinfo_id AND acm_ac_st_id = ar_id AND acm_ac_st_id = att_id)";
	public long timeToGetPrivacyStatement;
	public long timeToGetUserLocation;
	
	public KVMReturnAccessInfo checkIfAccessAllowed(Long _objPE_id, Long _objSE_id, Integer _objAccessReason)
	{
		Object [] objParamsForGetAC_Rows = new Object[] {_objPE_id,_objSE_id };
		AccessControlStRow[] objArrAC_Rows;

		int nRowCount = 0;
		
		try
		{
			GregorianCalendar ProfileStTime = new GregorianCalendar();
			objArrAC_Rows = (AccessControlStRow[]) new SmartCampusDAO().query(szQueryToGetAC_StRows,objParamsForGetAC_Rows,AccessControlStRow.class);
			GregorianCalendar ProfileEndTime = new GregorianCalendar();
			//set and print the time to get the privacy statement  
			timeToGetPrivacyStatement = ProfileEndTime.getTimeInMillis() - ProfileStTime.getTimeInMillis();
			System.out.println("");
			System.out.println("TIME TAKEN TO GET PRIVACY STATEMENT: " + timeToGetPrivacyStatement);
		}
		catch(Exception _e)
		{
			_e.printStackTrace();
			
			return null;
		}
		
		nRowCount = objArrAC_Rows.length;
		//System.out.println("nRowCount: " + nRowCount);
		
		for(int i = 0; i < nRowCount; i ++)
		{
			//System.out.println("\n\nChecking for i= " + i);
			//System.out.println("acm_ac_st_id: " + objArrAC_Rows[i].acm_ac_st_id);
			//System.out.println("acm_pe_id: " + objArrAC_Rows[i].acm_pe_id);
			//System.out.println("acm_access_allowed: " + objArrAC_Rows[i].acm_access_allowed);

			//System.out.println("\n\nobjArrAC_Rows[i].acm_ac_st_id = " + objArrAC_Rows[i].acm_ac_st_id + "\nobjArrAC_Rows[i].hinfo_location = " + objArrAC_Rows[i].hinfo_location + "\nobjArrAC_Rows[i].hinfo_time_based_events = " + objArrAC_Rows[i].hinfo_time_based_events);
			//System.out.println("\n\n");
			
			/*
			 * Check if the hidden information matches with information accessed.
			 */
			if(((_objAccessReason.intValue() == AC_RunTime_Contants.ACCESS_INFO_LOC) && objArrAC_Rows[i].hinfo_location)
					|| ((_objAccessReason.intValue() == AC_RunTime_Contants.ACCESS_INFO_LOC_BASED_EVENTS) && objArrAC_Rows[i].hinfo_loc_based_events)
					|| ((_objAccessReason.intValue() == AC_RunTime_Contants.ACCESS_INFO_ALL_EVENTS) && objArrAC_Rows[i].hinfo_all_events)
					|| ((_objAccessReason.intValue() == AC_RunTime_Contants.ACCESS_INFO_TIME_BASED_EVENTS) && objArrAC_Rows[i].hinfo_time_based_events)
					|| ((_objAccessReason.intValue() == AC_RunTime_Contants.ACCESS_INFO_USER_PROFILE) && objArrAC_Rows[i].hinfo_user_profile_name))
			{
				/*
				 * As the accessing information matches the information being hidden, now we check if the access control conditions 
				 * satisfied.
				 */
				boolean AccessControlCondSatisfied = checkAccessControl(objArrAC_Rows[i]);

			
				/*
				 * If access control condition statifies and if it is not any type of event.
				 */
				if(AccessControlCondSatisfied == true) 
				{
					/*
					 * If access condition is satisfied then we need to return back with the appropriate data in return object.
					 * Case 1) Returning IsAccessAllowed = true and remaining empty object (When secondary is allowed access). 
					 * Case 2) Return the whole object with only "IsAccessAllowed" = false and other 6 fields (When secondary 
					 * 			is not allowed access, in this case we need to send the reason also ... i.e. other 6 fields).
					 */

					KVMReturnAccessInfo objKVM_AccessInfo = new KVMReturnAccessInfo();
					objKVM_AccessInfo.IsAccessAllowed = objArrAC_Rows[i].acm_access_allowed; ///Setting IsAccessAllowed to true/false.					

					if(objArrAC_Rows[i].acm_access_allowed != true)
					{
						/*
						 * Into case (2) of above comment.
						 */
						objKVM_AccessInfo.ShouldPrimaryReceiveMsg = objArrAC_Rows[i].att_type_primary_msg;
						objKVM_AccessInfo.ShouldSecondaryReceiveMsg = objArrAC_Rows[i].att_typ_secondary_msg;
						objKVM_AccessInfo.ShouldSystemMsgGetLogged = objArrAC_Rows[i].att_type_sys_msg;
						objKVM_AccessInfo.MessageToPrimary = objArrAC_Rows[i].att_primary_msg;
						objKVM_AccessInfo.MessageToSecondary = objArrAC_Rows[i].att_secondary_msg;
						objKVM_AccessInfo.MessageSystemShouldLog = objArrAC_Rows[i].att_sys_msg;
					}

					/*
					 * Now returning a KVM class instead of raw class.
					 */ 
					return objKVM_AccessInfo;
				}
			}
		}
			
			
			
		/*
		 * Very IMPORTANT.
		 * Need to considered groups (System defined "All" or "EveryOne" group or user created groups) 
		 */
		
		
		KVMReturnAccessInfo objKVM_AccessInfo = new KVMReturnAccessInfo();
		if(_objPE_id.longValue() == _objSE_id.longValue())
		{
			
			//If the user is trying to access his own information or a event is set by himself let his access.
			objKVM_AccessInfo.IsAccessAllowed = true;
			//System.out.println("Returning from default rule: Primary same as Secondary.");
		}
		else
		{
			/*
			When a user is trying to access other users information the default rule is do not allow the access.
			*/
			objKVM_AccessInfo.IsAccessAllowed = false;
			//System.out.println("Returning from default rule: Primary different from Secondary.");
		}
		
		/*
		 * Now returning a KVM class instead of raw class.
		 */
		return objKVM_AccessInfo;
	}
	
	/**
	 * @param objCurrentACRow
	 * @return true or false
	 * 
	 * This method receives the whole AccessControl Statement, using the AccessRestrictions part (database table ac_access_restrictions) of it 
	 * the code finds out whether any of the set conditions match or not. 
	 */
	boolean checkAccessControl(AccessControlStRow objCurrentACRow)
	{
		boolean bReturnVar = true;
		
		try
		{
			//System.out.println("ar_co_loc_based: " + objArrAR_Row[0].ar_co_loc_based + "\nar_loc_based: " + objArrAR_Row[0].ar_loc_based + "\nar_time_based: " + objArrAR_Row[0].ar_time_based);

			if(objCurrentACRow.ar_loc_based)
			{
				GregorianCalendar ProfileStTime = new GregorianCalendar();
				Object [] paramsForFindingLocDis = new Object[] {new Long(objCurrentACRow.acm_pe_id),new Long(objCurrentACRow.acm_pe_id) };
				String szQueryForFindingLocDis = "select * from locationinfo_location where userid=? and mode='Centroid' and time = (select max(time) from locationinfo_location where userid=?)";
				locationinfo_location[] objArrCurLocInfo = (locationinfo_location[]) new SmartCampusDAO().query(szQueryForFindingLocDis,paramsForFindingLocDis,locationinfo_location.class );
				GregorianCalendar ProfileEndTime = new GregorianCalendar();
				
				//set and print the time taken to get user location
				timeToGetUserLocation = ProfileEndTime.getTimeInMillis() - ProfileStTime.getTimeInMillis();
				System.out.println("TIME TAKEN TO GET USER LOCATION: " + timeToGetUserLocation);
				
				UserLocationTime objUsersCurLocation = new UserLocationTime();
				UserLocationTime objTargetLocationOfUser = new UserLocationTime();

				if(objArrCurLocInfo != null && objArrCurLocInfo.length > 0)
				{
					Coordinates objUsersCurCoordinates = new Coordinates(objArrCurLocInfo[0].lat, objArrCurLocInfo[0].lon, new Float(objArrCurLocInfo[0].height).floatValue());
					Coordinates objTargetCoordinatesOfUser = new Coordinates(objCurrentACRow.ar_loc_longitude, objCurrentACRow.ar_loc_latitute, new Float(objCurrentACRow.ar_loc_height).floatValue());

					objUsersCurLocation.setFloor(objArrCurLocInfo[0].floor);
					objTargetLocationOfUser.setFloor(objArrCurLocInfo[0].floor);
					objUsersCurLocation.setMode(objArrCurLocInfo[0].mode);
					objTargetLocationOfUser.setMode(objArrCurLocInfo[0].mode);
					objUsersCurLocation.setNumberOfAP(objArrCurLocInfo[0].numberofap);
					objTargetLocationOfUser.setNumberOfAP(objArrCurLocInfo[0].numberofap);
					objUsersCurLocation.setNumberOfKnownAP(objArrCurLocInfo[0].numberofknownap);
					objTargetLocationOfUser.setNumberOfKnownAP(objArrCurLocInfo[0].numberofknownap);
					objUsersCurLocation.setTime(objArrCurLocInfo[0].time);
					objTargetLocationOfUser.setTime(objArrCurLocInfo[0].time);
					objUsersCurLocation.setLocation(objUsersCurCoordinates);
					objTargetLocationOfUser.setLocation(objTargetCoordinatesOfUser);
				
					//check that the location is not very old. Not more that 3 minutes (180000 millsec)old.
					if(objUsersCurLocation.getTime().after(new Timestamp(System.currentTimeMillis() - AC_RunTime_Contants.LOCATION_FRESHNESS)))
					{
						if(objUsersCurLocation.getLocation().groundDistance(objTargetLocationOfUser.getLocation()) < AC_RunTime_Contants.VARIATION_DISTANCE)
						{
							bReturnVar &= true;
						}
						else
						{
							bReturnVar &= false;
						}
					}
					else
					{
						bReturnVar &= false;
					}
				}
				else
				{
					bReturnVar &= false;
				}
			}
			if(objCurrentACRow.ar_co_loc_based)
			{
				Object [] paramsForFindingUsersLocDis = new Object[] {new Long(objCurrentACRow.acm_pe_id),new Long(objCurrentACRow.acm_pe_id) };
				String szQueryForFindingUsersLocDis = "select * from locationinfo_location where userid=? and mode='Centroid' and time = (select max(time) from locationinfo_location where userid=?)";
				locationinfo_location[] objArrUsersCurLocInfo = (locationinfo_location[]) new SmartCampusDAO().query(szQueryForFindingUsersLocDis,paramsForFindingUsersLocDis,locationinfo_location.class );
				
				Object [] paramsForFindingAnotherUserLocDis = new Object[] {new Long(objCurrentACRow.ar_co_loc_user_id),new Long(objCurrentACRow.ar_co_loc_user_id) };
				String szQueryForFindingAnotherUsersLocDis = "select * from locationinfo_location where userid=? and mode='Centroid' and time = (select max(time) from locationinfo_location where userid=?)";
				locationinfo_location[] objArrAnotherUsersCurLocInfo = (locationinfo_location[]) new SmartCampusDAO().query(szQueryForFindingAnotherUsersLocDis,paramsForFindingAnotherUserLocDis,locationinfo_location.class );

				if(objArrUsersCurLocInfo != null && objArrAnotherUsersCurLocInfo != null && objArrUsersCurLocInfo.length > 0 && objArrAnotherUsersCurLocInfo.length > 0)
				{
					Coordinates objUsersCurCoordinates = new Coordinates(objArrUsersCurLocInfo[0].lat, objArrUsersCurLocInfo[0].lon, new Float(objArrUsersCurLocInfo[0].height).floatValue());
					UserLocationTime objUsersCurLocation = new UserLocationTime();

					objUsersCurLocation.setFloor(objArrUsersCurLocInfo[0].floor);
					objUsersCurLocation.setMode(objArrUsersCurLocInfo[0].mode);
					objUsersCurLocation.setNumberOfAP(objArrUsersCurLocInfo[0].numberofap);
					objUsersCurLocation.setNumberOfKnownAP(objArrUsersCurLocInfo[0].numberofknownap);
					objUsersCurLocation.setTime(objArrUsersCurLocInfo[0].time);
					objUsersCurLocation.setLocation(objUsersCurCoordinates);

					Coordinates objTargetCoordinatesOfUser = new Coordinates(objArrAnotherUsersCurLocInfo[0].lat, objArrAnotherUsersCurLocInfo[0].lon, new Float(objArrAnotherUsersCurLocInfo[0].height).floatValue());
					UserLocationTime objAnotherUsersCurLocation = new UserLocationTime();

					objAnotherUsersCurLocation.setFloor(objArrAnotherUsersCurLocInfo[0].floor);
					objAnotherUsersCurLocation.setMode(objArrAnotherUsersCurLocInfo[0].mode);
					objAnotherUsersCurLocation.setNumberOfAP(objArrAnotherUsersCurLocInfo[0].numberofap);
					objAnotherUsersCurLocation.setNumberOfKnownAP(objArrAnotherUsersCurLocInfo[0].numberofknownap);
					objAnotherUsersCurLocation.setTime(objArrAnotherUsersCurLocInfo[0].time);
					objAnotherUsersCurLocation.setLocation(objTargetCoordinatesOfUser);

					//check that the location is not very old. Not more that 3 minutes (180000 millsec)old.				
					if(objUsersCurLocation.getTime().after(new Timestamp(System.currentTimeMillis() - AC_RunTime_Contants.LOCATION_FRESHNESS))
						&& objAnotherUsersCurLocation.getTime().after(new Timestamp(System.currentTimeMillis() - AC_RunTime_Contants.LOCATION_FRESHNESS))
						)
					{
						if(objUsersCurLocation.getLocation().groundDistance(objAnotherUsersCurLocation.getLocation()) < objCurrentACRow.ar_co_loc_distance)
						{
							bReturnVar &= true;
						}
						else
						{
							bReturnVar &= false;
						}
					}
					else
					{
						bReturnVar &= false;
					}
				}
				else
				{
					bReturnVar &= false;
				}
			}
			
			
			/*
			 * Time Contraints ......
			 */			
			if(objCurrentACRow.ar_time_based)
			{
				GregorianCalendar objGCalForCurTime = new  GregorianCalendar();
				GregorianCalendar objGCalForAR_Time_Date = new  GregorianCalendar();
				
				//Get the current year, month, date, hour, minutes, seconds.
				int nCurYear = objGCalForCurTime.get(Calendar.YEAR);
				int nCurMonth = objGCalForCurTime.get(Calendar.MONTH);
				int nCurDayOfTheMonth = objGCalForCurTime.get(Calendar.DAY_OF_MONTH);
				int nCurDayOfTheWeek = objGCalForCurTime.get(Calendar.DAY_OF_WEEK);
				int nCurHour = objGCalForCurTime.get(Calendar.HOUR_OF_DAY);
				int nCurMinutes = objGCalForCurTime.get(Calendar.MINUTE);

				if(objCurrentACRow.ar_time_frequency.equalsIgnoreCase(AC_RunTime_Contants.STR_ONE_TIME))
				{
					objGCalForAR_Time_Date.setTimeInMillis(objCurrentACRow.ar_time_date.getTime());
					
					if((objGCalForAR_Time_Date.get(Calendar.DAY_OF_MONTH) == nCurDayOfTheMonth)
						&& (objGCalForAR_Time_Date.get(Calendar.MONTH) == nCurMonth)
						&& (objGCalForAR_Time_Date.get(Calendar.YEAR) == nCurYear))
					{
						bReturnVar &= checkIfHrMinContraintSatisfied(objCurrentACRow.ar_time_start_time, objCurrentACRow.ar_time_end_time, nCurHour, nCurMinutes);
					}
					else
					{
						bReturnVar &= false;
					}
				}
				if(objCurrentACRow.ar_time_frequency.equalsIgnoreCase(AC_RunTime_Contants.STR_EVERYDAY))
				{
					bReturnVar &= checkIfHrMinContraintSatisfied(objCurrentACRow.ar_time_start_time, objCurrentACRow.ar_time_end_time, nCurHour, nCurMinutes);					
				}
				if(objCurrentACRow.ar_time_frequency.equalsIgnoreCase(AC_RunTime_Contants.STR_ONCE_A_WEEK))
				{
					if(nCurDayOfTheWeek == objCurrentACRow.ar_time_day_of_the_week)
					{
						bReturnVar &= checkIfHrMinContraintSatisfied(objCurrentACRow.ar_time_start_time, objCurrentACRow.ar_time_end_time, nCurHour, nCurMinutes);
					}
					else
					{
						bReturnVar &= false;
					}
				}
				if(objCurrentACRow.ar_time_frequency.equalsIgnoreCase(AC_RunTime_Contants.STR_ONCE_A_MONTH))
				{
					if(nCurDayOfTheMonth == objCurrentACRow.ar_time_date_of_the_month)
					{
						bReturnVar &= checkIfHrMinContraintSatisfied(objCurrentACRow.ar_time_start_time, objCurrentACRow.ar_time_end_time, nCurHour, nCurMinutes);					
					}
					else
					{
						bReturnVar &= false;
					}
				}
				if(objCurrentACRow.ar_time_frequency.equalsIgnoreCase(AC_RunTime_Contants.STR_ONCE_A_YEAR))
				{
					if((nCurMonth == objCurrentACRow.ar_time_month_of_the_year) 
						&& (nCurDayOfTheMonth == objCurrentACRow.ar_time_date_of_the_month))
					{
						bReturnVar &= checkIfHrMinContraintSatisfied(objCurrentACRow.ar_time_start_time, objCurrentACRow.ar_time_end_time, nCurHour, nCurMinutes);
					}
					else
					{
						bReturnVar &= false;
					}
				}
			}			
		}
		catch(Exception _e)
		{
			_e.printStackTrace();
			return false;
		}
		return bReturnVar;	
	}
	
	boolean checkIfHrMinContraintSatisfied(Timestamp StartTime, Timestamp EndTime, int nCurHour, int nCurMinutes)
	{
		GregorianCalendar objGCalStartTime = new  GregorianCalendar();
		GregorianCalendar objGCalEndTime = new  GregorianCalendar();
		
		objGCalStartTime.setTimeInMillis(StartTime.getTime());
		objGCalEndTime.setTimeInMillis(EndTime.getTime());
		
		long nStartTime_Hours = objGCalStartTime.get(Calendar.HOUR_OF_DAY);
		long nStartTime_Mins = objGCalStartTime.get(Calendar.MINUTE);
		long nEndTime_Hours = objGCalEndTime.get(Calendar.HOUR_OF_DAY);
		long nEndTime_Mins = objGCalEndTime.get(Calendar.MINUTE);
		
		long nCurTimeInMins = ((nCurHour * 60 )+ nCurMinutes);
		
		if((((nStartTime_Hours * 60) + nStartTime_Mins) <= nCurTimeInMins)
			&& (nCurTimeInMins <= ((nEndTime_Hours * 60) + nEndTime_Mins)))
		{
			return true;
		}
		
		return false;
	}
}
