package services;

import globalData.ConnectionParameters;
import ksoapDataObjects.common.KVMUser;
import org.ksoap.ClassMap;
import org.ksoap.SoapObject;
import org.ksoap.transport.HttpTransportSE;

/**
 * This is a sample application service with only a single (simple) method. This can serve
 * as a template for writing actual services. This application service connects to the 
 * middleware via KSOAP calls. Alternately the service can communicate directly by obtaining 
 * a handle for the middleware services. KSOAP based commnunication is required if the 
 * midddleware services are distributed over several machines. Once a new service is implemented, 
 * it is important to register the service with the KSOAP servlet (The class "MobiSocController" 
 * in the default package). The Mobisoc journal paper has a description about application 
 * services and the middleware architecture.  
 * @author Maverick
 */
public class SampleApplicationService {

	private ClassMap classMap; //map for the ksoap data object
	private String connectString; //connection string to connect ot the server 
	
	/**
	 * The contructor sets the connection paprameters to connect to the server for SOAP calls.
	 * It also sets the classmap for SOAP based communication. 
	 */
	public SampleApplicationService()
	{
		/**
		 * get the connection string
		 */
		connectString = ConnectionParameters.connectString;
		/**
		 * set classmap for KSOAP based communication
		 */
		classMap = new ClassMap ();
        classMap.addMapping("soapservice", "KVMUser", new KVMUser().getClass());
	}
	
	
	/**
	 * This method returns and prints the user first name for an existing user
	 * @param userId
	 * 		User Id for the user whose name is to be fetched 
	 * @return
	 * 		First name for the user or "" if not found
	 */
	public String getUserFirstName(Long userId)
	{	    
		SoapObject rpc = new SoapObject("soapservice","getProfileInfo");
		rpc.addProperty("userId", new Long(1234));
		
		HttpTransportSE tx = new HttpTransportSE ("http://128.235.34.145:8080/MobiSoc/MobiSocController?service=SocialStateLearningService", "getProfileInfo");
		tx.setClassMap (classMap);
		   
		try{
			//Call the service. If events are received, then communicate them to the tranzact client
			KVMUser ret = (KVMUser)tx.call(rpc);
			System.out.print(ret.getFirst_name());
			return ret.getFirst_name();
		}catch(Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}
}
