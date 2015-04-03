import java.sql.Timestamp;
import java.util.HashMap;

import javax.servlet.*;
import javax.servlet.http.*;
import org.ksoap.ClassMap;

import core.data.dao.CoreDaoException;
import edu.njit.sc.core.dao.SmartCampusDAO;

import services.*;


import ksoapDataObjects.socialStateLearning.*;
import ksoapDataObjects.privacyService.KVMAccessControlMgrRow;
import ksoapDataObjects.privacyService.KVMAccessRestrictionsRow;
import ksoapDataObjects.privacyService.KVMActionToTakeRow;
import ksoapDataObjects.privacyService.KVMReturnAccessInfo;
import ksoapDataObjects.privacyService.KVMHideInformationRow;
import ksoapDataObjects.privacyService.KVMStatementData;
import ksoapDataObjects.common.KVMAccessPointInfo;
import ksoapDataObjects.common.KVMAlerts;
import ksoapDataObjects.common.KVMCBTrigger;
import ksoapDataObjects.common.KVMCoordinates;
import ksoapDataObjects.common.KVMEvent;
import ksoapDataObjects.common.KVMEventData;
import ksoapDataObjects.common.KVMGroupData;
import ksoapDataObjects.common.KVMLocationData;
import ksoapDataObjects.common.KVMPlaceData;
import ksoapDataObjects.common.KVMSocialGroup;
import ksoapDataObjects.common.KVMUser;
import ksoapDataObjects.common.KVMSocialEvent;



public class MobiSocController extends org.ksoap.servlet.SoapServlet {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HashMap<String, Object> instanceMap = new HashMap<String, Object>();
		
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
  
        ClassMap classMap = getClassMap();
        classMap.addMapping("soapservice", "KVMAccessPointInfo", new KVMAccessPointInfo().getClass());
        classMap.addMapping("soapservice", "KVMAffinityMatrixElement", new KVMAffinityMatrixElement().getClass()); 
        classMap.addMapping("soapservice", "KVMAlerts", new KVMAlerts().getClass());        
        classMap.addMapping("soapservice", "KVMAccessControlMgrRow", new KVMAccessControlMgrRow().getClass());
        classMap.addMapping("soapservice", "KVMAccessRestrictionsRow", new KVMAccessRestrictionsRow().getClass());
        classMap.addMapping("soapservice", "KVMActionToTakeRow", new KVMActionToTakeRow().getClass());
        classMap.addMapping("soapservice", "KVMCoordinates", new KVMCoordinates().getClass());
        classMap.addMapping("soapservice", "KVMCBTrigger", new KVMCBTrigger().getClass());
        classMap.addMapping("soapservice", "KVMCopresenceHistory", new KVMCopresenceHistory().getClass());
        classMap.addMapping("soapservice", "KVMEvent", new KVMEvent().getClass());
        classMap.addMapping("soapservice", "KVMEventData", new KVMEventData().getClass());
        classMap.addMapping("soapservice", "KVMGroupData", new KVMGroupData().getClass());
        classMap.addMapping("soapservice", "KVMHideInformationRow", new KVMHideInformationRow().getClass());
        classMap.addMapping("soapservice", "KVMLocationData", new KVMLocationData().getClass());
        classMap.addMapping("soapservice", "KVMNearbyEvent", new KVMNearbyEvent().getClass());
        classMap.addMapping("soapservice", "KVMNearbyPlace", new KVMNearbyPlace().getClass());        
        classMap.addMapping("soapservice", "KVMPlaceData", new KVMPlaceData().getClass());
        classMap.addMapping("soapservice", "KVMReturnAccessInfo", new KVMReturnAccessInfo().getClass());
        classMap.addMapping("soapservice", "KVMSocialEvent", new KVMSocialEvent().getClass());
        classMap.addMapping("soapservice", "KVMSocialGroup", new KVMSocialGroup().getClass());
        classMap.addMapping("soapservice", "KVMStatementData", new KVMStatementData().getClass());
        classMap.addMapping("soapservice", "KVMUser", new KVMUser().getClass());        
        classMap.addMapping("soapservice", "KVMUserPlaceHistory", new KVMUserPlaceHistory().getClass());
        
        
        /** 
         * Register services with the ksoap servlet
         */
        //register privacy service
        PrivacyService priSrv = new PrivacyService();
        instanceMap.put("PrivacyService", priSrv);
        
        //register event service
        EventService eventSrv = new EventService();
        instanceMap.put("EventService", eventSrv);
        
        //register data collection service
        DataCollectionService dcSrv = new DataCollectionService();
        instanceMap.put("DataCollectionService", dcSrv);
        
        //register social state larning service
        SocialStateLearningService sslSrv = new SocialStateLearningService();
        instanceMap.put("SocialStateLearningService", sslSrv);
        
        //register data mining service
        DataMiningService dmSrv = new DataMiningService();
        instanceMap.put("DataMiningService", dmSrv);
        
        //register sample application service
        SampleApplicationService sapSrv = new SampleApplicationService();
        instanceMap.put("SampleApplicationService", sapSrv);
           
    }
    
    public void doPost (HttpServletRequest req, HttpServletResponse res)
        throws ServletException, java.io.IOException {
    		
    		System.out.println("SERVICE=" + req.getParameter("service"));
            
            //insert the service name and time in the database
    		Object[] params = new Object[]{
    				req.getParameter("service"),
    				new Timestamp(System.currentTimeMillis())
    				};
    		String insertData = "INSERT INTO evaluation_all_services_call_count(service_name, call_time) VALUES(?,?)"; 
    		try {
    			int rowsUpdated = new SmartCampusDAO().update(insertData, params);
    		} catch (CoreDaoException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		
    		
            
            super.doPost(req,res);
    }
    
    protected Object getInstance (HttpServletRequest req) {
        Object result = instanceMap.get(req.getParameter("service") );
        return result;
    }  
}
