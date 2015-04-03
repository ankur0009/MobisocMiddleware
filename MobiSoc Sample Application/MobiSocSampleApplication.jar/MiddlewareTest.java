import ksoapDataObjects.*;
import org.ksoap.ClassMap;
import org.ksoap.SoapObject;
import org.ksoap.transport.HttpTransport;


public class MiddlewareTest {

	public static void main(String args[])
	{
			//create mapping for kvm class
			ClassMap classMap = new ClassMap ();
	        classMap.addMapping("soapservice", "KVMUser", new KVMUser().getClass());         
		    	        	    	        	        
	        
			SoapObject rpc = new SoapObject("soapservice","getUserFirstName");
			rpc.addProperty("userId", new Long(32));
			
			HttpTransport tx = new HttpTransport ("http://128.235.34.145:8080/MobiSoc/MobiSocController?service=SampleApplicationService", "getUserFirstName");
			tx.setClassMap (classMap);
			   
			try{
				//Call the service. If events are received, then communicate them to the tranzact client
				String ret = (String)tx.call(rpc);	
				System.out.print(ret);
				
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
	        
	}
	
}
