package services;
import java.util.Vector;

import edu.njit.sc.core.dao.SmartCampusDAO;

import ksoapDataObjects.privacyService.KVMHideInformationRow;

import serviceFunctionality.privacyService.AccessControlModule;
import serviceFunctionality.privacyService.AddModDelAC_St;

import ksoapDataObjects.privacyService.KVMAccessControlMgrRow;
import ksoapDataObjects.privacyService.KVMAccessRestrictionsRow;
import ksoapDataObjects.privacyService.KVMActionToTakeRow;
import ksoapDataObjects.privacyService.KVMReturnAccessInfo;
import ksoapDataObjects.privacyService.KVMStatementData;

import java.sql.Timestamp;

import core.data.dao.CoreDaoException;


 /**
 * This service provides the API for (Privacy Service) as described in the MobiSoc paper/documentation.
 * This class only provides KSOAP compatible versions of it's API. Regular versions will be added soon
 * @author NeerajR.
 */
public class PrivacyService {

	
	public KVMReturnAccessInfo checkPrivacyConstraints(Long _obj_pe_id, Long _obj_se_id, Integer _obj_access_reason)
	{
		Object [] params;//for database operations
		String insertData;//for database operations
		int rowsUpdated;//for database operations
		
		long before = System.currentTimeMillis();
		AccessControlModule objACM = new AccessControlModule();
		//initialize the computation times to zero
		objACM.timeToGetPrivacyStatement = 0;
		objACM.timeToGetUserLocation = 0;
		KVMReturnAccessInfo ret = (KVMReturnAccessInfo)objACM.checkIfAccessAllowed(_obj_pe_id, _obj_se_id, _obj_access_reason);
		long after = System.currentTimeMillis();
		
		//print out the time taken for the privacy computation
		System.out.println("TIME TAKEN BY PRIVACY SERVICE TO CHECK IF ACCESS IS ALLOWED: " + (after-before));
		System.out.println("");
		
		//insert the time taken for the privacy computatoion in the database
		params = new Object[]{
				new Long(after-before),
				new Long(objACM.timeToGetPrivacyStatement),
				new Long(objACM.timeToGetUserLocation),
				new Timestamp(System.currentTimeMillis())
				};
		insertData = "INSERT INTO evaluation_privacy_service_call_time(total_call_computation_cost, cost_for_retrieving_statement, cost_for_retrieving_user_location, call_time) VALUES(?,?,?,?)"; 
		try {
			rowsUpdated = new SmartCampusDAO().update(insertData, params);
		} catch (CoreDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret;
	}
	
	public boolean AddAC_St(KVMAccessControlMgrRow objKVM_ACMrgRow, KVMHideInformationRow objKVM_HI_Row, KVMAccessRestrictionsRow objKVM_AR_Row, KVMActionToTakeRow objKVM_ATT_Row)
	{
		AddModDelAC_St objAddModDelAC_St = new AddModDelAC_St();
		return(objAddModDelAC_St.AddAC_St(objKVM_ACMrgRow, objKVM_HI_Row, objKVM_AR_Row, objKVM_ATT_Row));
	}
	
	public boolean DeleteAC_St(Long AccessControl_St_ID)
	{
		AddModDelAC_St objAddModDelAC_St = new AddModDelAC_St();
		return(objAddModDelAC_St.DeleteAC_St(AccessControl_St_ID));
	}
	
	public boolean UpdateAC_St(KVMAccessControlMgrRow objKVM_ACMrgRow, KVMHideInformationRow objKVM_HI_Row, KVMAccessRestrictionsRow objKVM_AR_Row, KVMActionToTakeRow objKVM_ATT_Row)
	{
		AddModDelAC_St objAddModDelAC_St = new AddModDelAC_St();
		return(objAddModDelAC_St.UpdateAC_St(objKVM_ACMrgRow, objKVM_HI_Row, objKVM_AR_Row, objKVM_ATT_Row));
	}

	public Vector <KVMStatementData> GetAC_StRows(Long _obj_pe_id)
	{
		AddModDelAC_St objAddModDelAC_St = new AddModDelAC_St();
		return(objAddModDelAC_St.GetAC_StRows(_obj_pe_id));
	}
}
