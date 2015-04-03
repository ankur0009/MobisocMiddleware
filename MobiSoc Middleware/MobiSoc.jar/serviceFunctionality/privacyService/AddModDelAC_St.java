package serviceFunctionality.privacyService;

import ksoapDataObjects.privacyService.*;
import dataObjects.privacyService.*;
import edu.njit.sc.core.dao.SmartCampusDAO;
import java.util.Vector;

/**
 * AddModDelAC_St class
 * NOTE: This class is responsible for additions/deletion/modifications of the access control statement.
 * This class also has a method that retrives all the rows that are present for a particular primary (userid).
 * @author NeerajR.
 *
 */

public class AddModDelAC_St {
	
	public boolean AddAC_St(KVMAccessControlMgrRow objKVM_ACMrgRow, KVMHideInformationRow objKVM_HI_Row, KVMAccessRestrictionsRow objKVM_AR_Row, KVMActionToTakeRow objKVM_ATT_Row)
	{
		
		
		
		/*
		 * Very IMPORTANT.
		 * Database Savepoint should happen now so that it is easy to rollback.
		 * or we can delete the rows with the AccessControlStatementIDs from all the tables relating to Access Control input module.  
		 */
		
		AccessControlMgrRow objACMrgRow = new AccessControlMgrRow(objKVM_ACMrgRow);
		HideInformationRow objHI_Row = new HideInformationRow(objKVM_HI_Row);
		AccessRestrictionsRow objAR_Row = new AccessRestrictionsRow(objKVM_AR_Row);
		ActionToTakeRow objATT_Row = new ActionToTakeRow(objKVM_ATT_Row);

		String szQueryInsertIntoACM = "insert into ac_access_control_mgr(acm_pe_id, acm_se_id, acm_st_enabled, acm_access_allowed) VALUES(?,?,?,?)";
		String szQueryInsertIntoHI = "insert into ac_hide_info(hinfo_id, hinfo_location, hinfo_time_based_events, hinfo_loc_based_events, hinfo_all_events, hinfo_user_profile_name) VALUES(?,?,?,?,?,?)";
		String szQueryInsertIntoAR = "insert into ac_access_restrictions(ar_id, ar_loc_based, ar_loc_longitude, ar_loc_latitute, ar_loc_height, ar_time_based, ar_time_start_time, ar_time_end_time, ar_time_date, ar_co_loc_based, ar_co_loc_user_id, ar_co_loc_distance, ar_time_date_of_the_month, ar_time_month_of_the_year, ar_time_frequency, ar_time_day_of_the_week) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String szQueryInsertIntoATT = "insert into ac_action_to_take(att_id, att_type_primary_msg, att_typ_secondary_msg, att_type_sys_msg, att_primary_msg, att_secondary_msg, att_sys_msg) VALUES(?,?,?,?,?,?,?)";
		
		int	bRow_Inserted = 0;
		
		try
		{
			Object [] objParamsToInsertIntoACM = new Object[] {
					objACMrgRow.acm_pe_id, 
					objACMrgRow.acm_se_id,
					objACMrgRow.acm_st_enabled,
					objACMrgRow.acm_access_allowed
					};

			bRow_Inserted = new SmartCampusDAO().update(szQueryInsertIntoACM, objParamsToInsertIntoACM);
			/*
			 * Very IMPORTANT.
			 * If savepoint option is taken into consideration then will have to rollback the database else do nothing.
			 */

			String szQueryToGetLastSequenceAdded = "select max(acm_ac_st_id) from ac_access_control_mgr";
			
			ACM_MaxSeqNoRow [] objArrACM_MaxSeqRows = (ACM_MaxSeqNoRow[]) new SmartCampusDAO().query(szQueryToGetLastSequenceAdded,ACM_MaxSeqNoRow.class );
			
			if(objArrACM_MaxSeqRows.length > 0)
			{
				objACMrgRow.acm_ac_st_id = objArrACM_MaxSeqRows[0].max;
				objHI_Row.hinfo_id = objACMrgRow.acm_ac_st_id;
				objAR_Row.ar_id = objACMrgRow.acm_ac_st_id;
				objATT_Row.att_id = objACMrgRow.acm_ac_st_id;
			}
			else
			{
				//System.out.println("\n\n\n\nError: The Sequence no: returned for acm_ac_st_id from ac_access_control_mgr table is less than 0.\n\n\n\n");
				
				return false;
			}

		}
		catch(Exception _e)
		{
			_e.printStackTrace();
			
			return false;
		}
		System.out.println("bRowInserted for szQueryInsertIntoACM: " + bRow_Inserted);

	
		try
		{
			Object [] objParamsToInsertIntoHI = new Object[] {
					objHI_Row.hinfo_id,
					objHI_Row.hinfo_location,
					objHI_Row.hinfo_time_based_events,
					objHI_Row.hinfo_loc_based_events,
					objHI_Row.hinfo_all_events,
					objHI_Row.hinfo_user_profile_name			
					};

			bRow_Inserted = new SmartCampusDAO().update(szQueryInsertIntoHI, objParamsToInsertIntoHI);
			
			
			/*
			 * Very IMPORTANT.
			 * If savepoint option is taken into consideration then will have to rollback the database else we will have to delete the row corresponding to the Statement ID from all the tables.
			 */
		}
		catch(Exception _e)
		{
			_e.printStackTrace();
		
			int	bRowDeleted = 0;
			String szQueryDeleteFromACM = "delete from ac_access_control_mgr where acm_ac_st_id = ?";
			Object [] objParamsDeleteFromACM = new Object[] {
					objACMrgRow.acm_ac_st_id
					};
			
			try
			{
				bRowDeleted = new SmartCampusDAO().update(szQueryDeleteFromACM, objParamsDeleteFromACM);
			}
			catch(Exception objException)
			{
				objException.printStackTrace();
				System.out.println("bRowDeleted for szQueryDeleteFromACM: " + bRowDeleted);


				
				//Very IMPORTANT.
				//There should be a better way to log error information.

			 
				//System.out.println("Error: Could not delete row from ac_access_control_mgr table with id: " + objACMrgRow.acm_ac_st_id);
				return false;
			}				
			return false;
		}
		//System.out.println("bRowInserted for szQueryInsertIntoHI: " + bRow_Inserted);
			
		
		try
		{
			Object [] objParamsToInsertIntoAR = new Object[] {
					objAR_Row.ar_id,
					objAR_Row.ar_loc_based,
					objAR_Row.ar_loc_longitude,
					objAR_Row.ar_loc_latitute,
					objAR_Row.ar_loc_height,
					objAR_Row.ar_time_based,
					objAR_Row.ar_time_start_time,
					objAR_Row.ar_time_end_time,
					objAR_Row.ar_time_date,
					objAR_Row.ar_co_loc_based,
					objAR_Row.ar_co_loc_user_id,
					objAR_Row.ar_co_loc_distance,
					objAR_Row.ar_time_date_of_the_month,
					objAR_Row.ar_time_month_of_the_year,
					objAR_Row.ar_time_frequency,
					objAR_Row.ar_time_day_of_the_week
					};
			
			bRow_Inserted = new SmartCampusDAO().update(szQueryInsertIntoAR, objParamsToInsertIntoAR);
			/*
			 * Very IMPORTANT.
			 * If savepoint option is taken into consideration then will have to rollback the database else we will have to delete the row corresponding to the Statement ID from all the tables.
			 */
		}
		catch(Exception _e)
		{
			_e.printStackTrace();
			

			
			int	bRowDeleted = 0;
			String szQueryDeleteFromACM = "delete from ac_access_control_mgr where acm_ac_st_id = ?";
			Object [] objParamsDeleteFromACM = new Object[] {
					objACMrgRow.acm_ac_st_id
					};
			
			try
			{
				bRowDeleted = new SmartCampusDAO().update(szQueryDeleteFromACM, objParamsDeleteFromACM);
			}
			catch(Exception objException)
			{
				objException.printStackTrace();
				System.out.println("bRowDeleted for szQueryDeleteFromACM: " + bRowDeleted);


				//Very IMPORTANT.
				//There should be a better way to log error information.


			
				//System.out.println("Error: Could not delete row from ac_access_control_mgr table with id: " + objACMrgRow.acm_ac_st_id);
				return false;
			}
		
			String szQueryDeleteFromHI = "delete from ac_hide_info where hinfo_id = ?";
			Object [] objParamsDeleteFromHI = new Object[] {
					objACMrgRow.acm_ac_st_id
					};
			
			try
			{
				bRowDeleted = new SmartCampusDAO().update(szQueryDeleteFromHI, objParamsDeleteFromHI);
			}
			catch(Exception objException)
			{
				objException.printStackTrace();
				//System.out.println("bRowDeleted for szQueryDeleteFromHI: " + bRowDeleted);


				
				//Very IMPORTANT.
				//There should be a better way to log error information.



				//System.out.println("Error: Could not delete row from ac_hide_info table with id: " + objACMrgRow.acm_ac_st_id);
				return false;
			}
			return false;
		}
		//System.out.println("bRowInserted for szQueryInsertIntoAR: " + bRow_Inserted);


		try
		{
			Object [] objParamsToInsertIntoATT = new Object[] {
					objATT_Row.att_id,
					objATT_Row.att_type_primary_msg,
					objATT_Row.att_typ_secondary_msg,
					objATT_Row.att_type_sys_msg,
					objATT_Row.att_primary_msg,
					objATT_Row.att_secondary_msg,
					objATT_Row.att_sys_msg
					};

			bRow_Inserted = new SmartCampusDAO().update(szQueryInsertIntoATT, objParamsToInsertIntoATT);

			/*
			 * Very IMPORTANT.
			 * If savepoint option is taken into consideration then will have to rollback the database else we will have to delete the row corresponding to the Statement ID from all the tables.
			 */
		}
		catch(Exception _e)
		{
			_e.printStackTrace();
		
			int	bRowDeleted = 0;
			String szQueryDeleteFromACM = "delete from ac_access_control_mgr where acm_ac_st_id = ?";
			Object [] objParamsDeleteFromACM = new Object[] {
					objACMrgRow.acm_ac_st_id
					};
			
			try
			{
				bRowDeleted = new SmartCampusDAO().update(szQueryDeleteFromACM, objParamsDeleteFromACM);
			}
			catch(Exception objException)
			{
				objException.printStackTrace();
				System.out.println("bRowDeleted for szQueryDeleteFromACM: " + bRowDeleted);

				 
				//Very IMPORTANT.
				//There should be a better way to log error information.


				
				//System.out.println("Error: Could not delete row from ac_access_control_mgr table with id: " + objACMrgRow.acm_ac_st_id);
				return false;
			}				
			
			String szQueryDeleteFromHI = "delete from ac_hide_info where hinfo_id = ?";
			Object [] objParamsDeleteFromHI = new Object[] {
					objACMrgRow.acm_ac_st_id
					};
			
			try
			{
				bRowDeleted = new SmartCampusDAO().update(szQueryDeleteFromHI, objParamsDeleteFromHI);
			}
			catch(Exception objException)
			{
				objException.printStackTrace();
				//System.out.println("bRowDeleted for szQueryDeleteFromHI: " + bRowDeleted);
				
				
				//Very IMPORTANT.
				//There should be a better way to log error information.


				
				//System.out.println("Error: Could not delete row from ac_hide_info table with id: " + objACMrgRow.acm_ac_st_id);
				return false;
			}				

			String szQueryDeleteFromAR = "delete from ac_access_restrictions where att_id = ?";
			Object [] objParamsDeleteFromAR = new Object[] {
					objACMrgRow.acm_ac_st_id
					};
			
			try
			{
				bRowDeleted = new SmartCampusDAO().update(szQueryDeleteFromAR, objParamsDeleteFromAR);
			}
			catch(Exception objException)
			{
				objException.printStackTrace();
				//System.out.println("bRowDeleted for szQueryDeleteFromAR: " + bRowDeleted);
				
				

				//Very IMPORTANT.
				//There should be a better way to log error information.


				
				
				//System.out.println("Error: Could not delete row from ac_access_restrictions table with id: " + objACMrgRow.acm_ac_st_id);
				return false;
			}
			return false;
		}
		//System.out.println("bRowInserted for szQueryInsertIntoATT: " + bRow_Inserted);
		
		return true;
	}
	
	public boolean DeleteAC_St(Long _AccessControl_St_ID)
	{
		int	bRowDeleted = 0;
		long AC_St_ID = _AccessControl_St_ID.longValue();

		try
		{
			String szQueryDeleteFromACM = "delete from ac_access_control_mgr where acm_ac_st_id = ?";
			Object [] objParamsDeleteFromACM = new Object[] {
					AC_St_ID
					};
			
			bRowDeleted = new SmartCampusDAO().update(szQueryDeleteFromACM, objParamsDeleteFromACM);
			if(bRowDeleted != 1)
			{

				 
				 //Very IMPORTANT.
				 //There should be a better way to log error information.


				
				//System.out.println("Error: Could not delete row from ac_access_control_mgr table with id: " + AC_St_ID);
				
				//Do not return try to delete rows in other table.
			}
		}
		catch(Exception _e)
		{

			 
			 //Very IMPORTANT.
			 //There should be a better way to log error information.


			_e.printStackTrace();

			
			//System.out.println("Error: Could not delete row from ac_access_control_mgr table with id: " + AC_St_ID);
			
			//Do not return try to delete rows in other table.

		}
		
		try
		{
			String szQueryDeleteFromHI = "delete from ac_hide_info where hinfo_id = ?";
			Object [] objParamsDeleteFromHI = new Object[] {
					AC_St_ID
					};
			
			bRowDeleted = new SmartCampusDAO().update(szQueryDeleteFromHI, objParamsDeleteFromHI);
			if(bRowDeleted != 1)
			{
				 //Very IMPORTANT.
				 //There should be a better way to log error information.


				
				//System.out.println("Error: Could not delete row from ac_hide_info table with id: " + AC_St_ID);

				//Do not return try to delete rows in other table.
			}				
		}
		catch(Exception _e)
		{

			 
			 //Very IMPORTANT.
			 //There should be a better way to log error information.


			_e.printStackTrace();

			
			//System.out.println("Error: Could not delete row from ac_hide_info table with id: " + AC_St_ID);
			
			//Do not return try to delete rows in other table.

		}

		try
		{
			String szQueryDeleteFromAR = "delete from ac_access_restrictions where ar_id = ?";
			Object [] objParamsDeleteFromAR = new Object[] {
					AC_St_ID
					};
			
			bRowDeleted = new SmartCampusDAO().update(szQueryDeleteFromAR, objParamsDeleteFromAR);
			if(bRowDeleted != 1)
			{


				 //Very IMPORTANT.
				 //There should be a better way to log error information.


				
				
				//System.out.println("Error: Could not delete row from ac_access_restrictions table with id: " + AC_St_ID);

				//Do not return try to delete rows in other table.
			}
		}
		catch(Exception _e)
		{

			 
			 //Very IMPORTANT.
			 //There should be a better way to log error information.


			_e.printStackTrace();

			
			//System.out.println("Error: Could not delete row from ac_access_restrictions table with id: " + AC_St_ID);
			
			//Do not return try to delete rows in other table.

		}

		try
		{
			String szQueryDeleteFromATT = "delete from ac_action_to_take where att_id = ?";
			Object [] objParamsDeleteFromATT = new Object[] {
					AC_St_ID
					};
			
			bRowDeleted = new SmartCampusDAO().update(szQueryDeleteFromATT, objParamsDeleteFromATT);
			if(bRowDeleted != 1)
			{


				 //Very IMPORTANT.
				 //There should be a better way to log error information.


				
				
				//System.out.println("Error: Could not delete row from ac_action_to_take table with id: " + AC_St_ID);

				//Do not return try to delete rows in other table.
			}				
		}
		catch(Exception _e)
		{

			 
			 //Very IMPORTANT.
			 //There should be a better way to log error information.


			_e.printStackTrace();

			
			//System.out.println("Error: Could not delete row from ac_action_to_take table with id: " + AC_St_ID);
			
			//Do not return try to delete rows in other table.

		}

		return true;
	}
	
	
	public boolean UpdateAC_St(KVMAccessControlMgrRow objKVM_ACMrgRow, KVMHideInformationRow objKVM_HI_Row, KVMAccessRestrictionsRow objKVM_AR_Row, KVMActionToTakeRow objKVM_ATT_Row)
	{
		
		
		
		/*
		 * Very IMPORTANT.
		 * Database Savepoint should happen now so that it is easy to rollback.
		 * or we can delete the rows with the AccessControlStatementIDs from all the tables relating to Access Control input module.  
		 */
		
		
		
		AccessControlMgrRow objACMrgRow = new AccessControlMgrRow(objKVM_ACMrgRow);
		HideInformationRow objHI_Row = new HideInformationRow(objKVM_HI_Row);
		AccessRestrictionsRow objAR_Row = new AccessRestrictionsRow(objKVM_AR_Row);
		ActionToTakeRow objATT_Row = new ActionToTakeRow(objKVM_ATT_Row);

		
		String szQueryUpdateACM = "update ac_access_control_mgr set acm_pe_id = ?, acm_se_id = ?, acm_st_enabled =?, acm_access_allowed = ? where acm_ac_st_id = ?";
		String szQueryUpdateHI = "update ac_hide_info set hinfo_location = ?, hinfo_time_based_events = ?, hinfo_loc_based_events = ?, hinfo_all_events = ?, hinfo_user_profile_name = ? where hinfo_id = ?";
		String szQueryUpdateAR = "update ac_access_restrictions set ar_loc_based = ?, ar_loc_longitude = ?, ar_loc_latitute = ?, ar_loc_height = ?, ar_time_based = ?, ar_time_start_time = ?, ar_time_end_time = ?, ar_time_date = ?, ar_co_loc_based = ?, ar_co_loc_user_id = ?, ar_co_loc_distance = ?, ar_time_date_of_the_month = ?, ar_time_month_of_the_year = ?, ar_time_frequency = ?, ar_time_day_of_the_week  = ? where ar_id = ?";
		String szQueryUpdateATT = "update ac_action_to_take set att_type_primary_msg = ?, att_typ_secondary_msg = ?, att_type_sys_msg = ?, att_primary_msg = ?, att_secondary_msg = ?, att_sys_msg = ? where att_id = ?";
		
		Object [] objParamsToUpdateACM = new Object[] {
				objACMrgRow.acm_pe_id, 
				objACMrgRow.acm_se_id,
				objACMrgRow.acm_st_enabled,
				objACMrgRow.acm_access_allowed, 
				objACMrgRow.acm_ac_st_id
				};
		
		Object [] objParamsToUpdateHI = new Object[] {
				objHI_Row.hinfo_location,
				objHI_Row.hinfo_time_based_events,
				objHI_Row.hinfo_loc_based_events,
				objHI_Row.hinfo_all_events,
				objHI_Row.hinfo_user_profile_name,
				objACMrgRow.acm_ac_st_id			
				};

		Object [] objParamsToUpdateAR = new Object[] {
				objAR_Row.ar_loc_based,
				objAR_Row.ar_loc_longitude,
				objAR_Row.ar_loc_latitute,
				objAR_Row.ar_loc_height,
				objAR_Row.ar_time_based,
				objAR_Row.ar_time_start_time,
				objAR_Row.ar_time_end_time,
				objAR_Row.ar_time_date,
				objAR_Row.ar_co_loc_based,
				objAR_Row.ar_co_loc_user_id,
				objAR_Row.ar_co_loc_distance,
				objAR_Row.ar_time_date_of_the_month,
				objAR_Row.ar_time_month_of_the_year,
				objAR_Row.ar_time_frequency,
				objAR_Row.ar_time_day_of_the_week,
				objACMrgRow.acm_ac_st_id
				};
		
		Object [] objParamsToUpdateATT = new Object[] {
				objATT_Row.att_type_primary_msg,
				objATT_Row.att_typ_secondary_msg,
				objATT_Row.att_type_sys_msg,
				objATT_Row.att_primary_msg,
				objATT_Row.att_secondary_msg,
				objATT_Row.att_sys_msg,
				objACMrgRow.acm_ac_st_id
				};
		
		int	bRowUpdated = 0;
		
		try
		{
			bRowUpdated = new SmartCampusDAO().update(szQueryUpdateACM, objParamsToUpdateACM);
			/*
			 * Very IMPORTANT.
			 * If savepoint option is taken into consideration then will have to rollback the database else do nothing.
			 */
		}
		catch(Exception _e)
		{
			System.out.println("bRowUpdated for szQueryUpdateACM: " + bRowUpdated);
			_e.printStackTrace();
			
			return false;
		}
		//System.out.println("bRowUpdated for szQueryUpdateACM: " + bRowUpdated);

		
		try
		{
			bRowUpdated = new SmartCampusDAO().update(szQueryUpdateHI, objParamsToUpdateHI);
			
			/*
			 * Very IMPORTANT.
			 * If savepoint option is taken into consideration then will have to rollback the database else we will have to delete the row corresponding to the Statement ID from all the tables.
			 */
		}
		catch(Exception _e)
		{
			_e.printStackTrace();
		
			return false;
		}
		//System.out.println("bRowUpdated for szQueryUpdateHI: " + bRowUpdated);
			
		
		try
		{
			bRowUpdated = new SmartCampusDAO().update(szQueryUpdateAR, objParamsToUpdateAR);
			/*
			 * Very IMPORTANT.
			 * If savepoint option is taken into consideration then will have to rollback the database else we will have to delete the row corresponding to the Statement ID from all the tables.
			 */
		}
		catch(Exception _e)
		{
			_e.printStackTrace();
			
			return false;
		}
		//System.out.println("bRowUpdate for szQueryUpdateAR: " + bRowUpdated);


		try
		{
			bRowUpdated = new SmartCampusDAO().update(szQueryUpdateATT, objParamsToUpdateATT);

			/*
			 * Very IMPORTANT.
			 * If savepoint option is taken into consideration then will have to rollback the database else we will have to delete the row corresponding to the Statement ID from all the tables.
			 */
		}
		catch(Exception _e)
		{
			_e.printStackTrace();
		
			return false;
		}
		//System.out.println("bRowUpdated for szQueryUpdateATT: " + bRowUpdated);

		return true;
	}
	
	public Vector <KVMStatementData> GetAC_StRows(Long _obj_PE_id)
	{
		Vector <KVMStatementData> objVecStData = new Vector <KVMStatementData>();
		
		String szQueryToGetUsersAllAC_StRows = "select acm_ac_st_id, acm_pe_id, acm_se_id, acm_st_enabled, acm_access_allowed from ac_access_control_mgr where (acm_pe_id = ?)";
		Object [] objParamsForGetUsersAllAC_Rows = new Object[]{_obj_PE_id};
		
		AccessControlMgrRow [] objArrACM_Rows; 
		int nRowCount = 0;
		
		try
		{
			objArrACM_Rows = (AccessControlMgrRow[]) new SmartCampusDAO().query(szQueryToGetUsersAllAC_StRows,objParamsForGetUsersAllAC_Rows,AccessControlMgrRow.class );
		}
		catch(Exception _e)
		{
			_e.printStackTrace();
			
			return null;
		}
		
		nRowCount = objArrACM_Rows.length;
		//System.out.println("nRowCount: " + nRowCount);
		
		for(int nStRow = 0; nStRow < nRowCount; nStRow ++)
		{
			//System.out.println("\n\nChecking for i= " + nStRow);
			//System.out.println("acm_ac_st_id: " + objArrACM_Rows[nStRow].acm_ac_st_id);
			
			KVMStatementData objStData = new KVMStatementData();		
			objStData.objKVM_ACM_Row = ConvertFromACM_ToKVM_ACM(objArrACM_Rows[nStRow]);
			
			//Get Hide Info row corresponding to AccessControlMgr.acm_ac_st_id
			String szQueryToGetHI_Row = "select hinfo_id, hinfo_location, hinfo_time_based_events, hinfo_loc_based_events, hinfo_all_events, hinfo_user_profile_name from ac_hide_info where hinfo_id = ?";
			Object [] objParamsForGetHI_Row = new Object[] {new Long(objArrACM_Rows[nStRow].acm_ac_st_id)};
			HideInformationRow[] objArrHI_Row = null; 

			//Always we will get only one row.
			try
			{
				objArrHI_Row = (HideInformationRow[]) new SmartCampusDAO().query(szQueryToGetHI_Row,objParamsForGetHI_Row,HideInformationRow.class );
				
				//There is always one for of HideInfo for corresponding AccessControlMgr.acm_ac_st_id.
				objStData.objKVM_HI_Row = ConvertFromHI_ToKVM_HI(objArrHI_Row[0]);				
			}
			catch(Exception _e)
			{
				_e.printStackTrace();
				
				return null;
			}
			
			//Get AccessRestriction row corresponding to AccessControlMgr.acm_ac_st_id
			String szQueryToGetAR_Row = "select ar_id, ar_loc_based, ar_loc_longitude, ar_loc_latitute, ar_loc_height, ar_time_based, ar_time_start_time, ar_time_end_time, ar_time_date, ar_co_loc_based, ar_co_loc_user_id, ar_co_loc_distance, ar_time_date_of_the_month, ar_time_month_of_the_year, ar_time_frequency, ar_time_day_of_the_week from ac_access_restrictions where ar_id = ?";
			Object [] objParamsForGetAR_Row = new Object[] {new Long(objArrACM_Rows[nStRow].acm_ac_st_id)};
			AccessRestrictionsRow[] objArrAR_Row = null; 

			//Always we will get only one row.
			try
			{
				objArrAR_Row = (AccessRestrictionsRow[]) new SmartCampusDAO().query(szQueryToGetAR_Row,objParamsForGetAR_Row,AccessRestrictionsRow.class );
				
				//There is always one for of HideInfo for corresponding AccessControlMgr.acm_ac_st_id.
				objStData.objKVM_AR_Row = ConvertFromAR_ToKVM_AR((objArrAR_Row[0]));				
			}
			catch(Exception _e)
			{
				_e.printStackTrace();
				
				return null;
			}
			
			String szQueryToGetATT_Row = "select att_id, att_type_primary_msg, att_typ_secondary_msg, att_type_sys_msg, att_primary_msg, att_secondary_msg, att_sys_msg from ac_action_to_take where att_id = ?";
			Object [] objParamsForGetATT_Row = new Object[] {new Long(objArrACM_Rows[nStRow].acm_ac_st_id)};
			
			try
			{
				ActionToTakeRow[] objArrATT_Row = (ActionToTakeRow[]) new SmartCampusDAO().query(szQueryToGetATT_Row,objParamsForGetATT_Row,ActionToTakeRow.class );
				//There is always one for of HideInfo for corresponding AccessControlMgr.acm_ac_st_id.
				objStData.objKVM_ATT_Row = ConvertFromATT_ToKVM_ATT((objArrATT_Row[0]));				
			}
			catch(Exception _e)
			{
				_e.printStackTrace();
				
				return null;
			}
			
			objVecStData.add(objStData);
		}	

		return objVecStData;
	}
	
	KVMAccessControlMgrRow ConvertFromACM_ToKVM_ACM(AccessControlMgrRow _objACM_Row)
	{
		if(_objACM_Row == null)
		{
			return null;
		}
		
		KVMAccessControlMgrRow objKVM_ACM_Row = new KVMAccessControlMgrRow();
		
		objKVM_ACM_Row.setAcm_ac_st_id(_objACM_Row.acm_ac_st_id);
		objKVM_ACM_Row.setAcm_pe_id(_objACM_Row.acm_pe_id);
		objKVM_ACM_Row.setAcm_se_id(_objACM_Row.acm_se_id);
		objKVM_ACM_Row.setAcm_st_enabled(_objACM_Row.acm_st_enabled); 
		objKVM_ACM_Row.setAcm_access_allowed(_objACM_Row.acm_access_allowed); 
		
		return objKVM_ACM_Row;
	}
	
	KVMHideInformationRow ConvertFromHI_ToKVM_HI(HideInformationRow _objHI_Row)
	{
		if(_objHI_Row == null)
		{
			return null;
		}
		
		KVMHideInformationRow objKVM_HI_Row = new KVMHideInformationRow();
		
		objKVM_HI_Row.setHinfo_id(_objHI_Row.hinfo_id);
		objKVM_HI_Row.setHinfo_loc_based_events(_objHI_Row.hinfo_loc_based_events);
		objKVM_HI_Row.setHinfo_location(_objHI_Row.hinfo_location);
		objKVM_HI_Row.setHinfo_all_events(_objHI_Row.hinfo_all_events);
		objKVM_HI_Row.setHinfo_time_based_events(_objHI_Row.hinfo_time_based_events);
		objKVM_HI_Row.setHinfo_user_profile_name(_objHI_Row.hinfo_user_profile_name);	
		
		return objKVM_HI_Row;
	}

	KVMAccessRestrictionsRow ConvertFromAR_ToKVM_AR(AccessRestrictionsRow _objAR_Row)
	{
		if(_objAR_Row == null)
		{
			return null;
		}
		
		KVMAccessRestrictionsRow objKVM_AR_Row = new KVMAccessRestrictionsRow();
		objKVM_AR_Row.setAr_id(_objAR_Row.ar_id);
		objKVM_AR_Row.setAr_co_loc_based(_objAR_Row.ar_co_loc_based);
		objKVM_AR_Row.setAr_co_loc_distance(_objAR_Row.ar_co_loc_distance);
		objKVM_AR_Row.setAr_co_loc_user_id(_objAR_Row.ar_co_loc_user_id);
		objKVM_AR_Row.setAr_loc_based(_objAR_Row.ar_loc_based);
		objKVM_AR_Row.setAr_loc_height(new Double(_objAR_Row.ar_loc_height).toString());
		objKVM_AR_Row.setAr_loc_latitute(new Double(_objAR_Row.ar_loc_latitute).toString());
		objKVM_AR_Row.setAr_loc_longitude(new Double(_objAR_Row.ar_loc_longitude).toString());
		objKVM_AR_Row.setAr_time_based(_objAR_Row.ar_time_based);
		
		if(_objAR_Row.ar_time_date != null)
		{
			objKVM_AR_Row.setAr_time_date(_objAR_Row.ar_time_date.getTime());
		}

		if(_objAR_Row.ar_time_start_time != null)
		{
			objKVM_AR_Row.setAr_time_start_time(_objAR_Row.ar_time_start_time.getTime());
		}
		
		if(_objAR_Row.ar_time_end_time != null)
		{
			objKVM_AR_Row.setAr_time_end_time(_objAR_Row.ar_time_end_time.getTime());
		}
		
		objKVM_AR_Row.setAr_time_date_of_the_month(_objAR_Row.ar_time_date_of_the_month);
		objKVM_AR_Row.setAr_time_day_of_the_week(_objAR_Row.ar_time_day_of_the_week);
		
		if(_objAR_Row.ar_time_frequency != null)
		{
			objKVM_AR_Row.setAr_time_frequency(_objAR_Row.ar_time_frequency);
		}
		
		objKVM_AR_Row.setAr_time_month_of_the_year(_objAR_Row.ar_time_month_of_the_year);
		
		return objKVM_AR_Row;
	}
	
	KVMActionToTakeRow ConvertFromATT_ToKVM_ATT(ActionToTakeRow _objATT_Row)
	{
		if(_objATT_Row == null)
		{
			return null;
		}
		
		KVMActionToTakeRow objKVM_ATT_Row = new KVMActionToTakeRow();
		
		objKVM_ATT_Row.setAtt_id(_objATT_Row.att_id);
		objKVM_ATT_Row.setAtt_type_primary_msg(_objATT_Row.att_type_primary_msg);
		objKVM_ATT_Row.setAtt_typ_secondary_msg(_objATT_Row.att_typ_secondary_msg);
		objKVM_ATT_Row.setAtt_type_sys_msg(_objATT_Row.att_type_sys_msg);
		
		if( _objATT_Row.att_primary_msg != null)
		{
			objKVM_ATT_Row.setAtt_primary_msg(_objATT_Row.att_primary_msg);
		}
		
		if(_objATT_Row.att_secondary_msg != null)
		{
			objKVM_ATT_Row.setAtt_secondary_msg(_objATT_Row.att_secondary_msg);
		}
		
		if(_objATT_Row.att_sys_msg != null)
		{
			objKVM_ATT_Row.setAtt_sys_msg(_objATT_Row.att_sys_msg);
		}
		
		return objKVM_ATT_Row;
	}
	
}
