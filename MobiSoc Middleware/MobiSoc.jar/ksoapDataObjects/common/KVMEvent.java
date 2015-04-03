package ksoapDataObjects.common;

import org.kobjects.serialization.ElementType;
import org.kobjects.serialization.KvmSerializable;
import org.kobjects.serialization.PropertyInfo;
import java.util.Vector;
/**
 * KVMEvent class
 * NOTE: must implement org.kobjects.serialization.KvmSerializable
 * @author Ankur
 *
 */

public class KVMEvent implements KvmSerializable {
	private Long eventId;
	private Long registrationTime;
	private String type;
	private String description;
	private Long sourceUser;
	private Vector targetUsers;
	private String serviceName;
	private String serviceApi;
	private KVMCBTrigger cbTrigger;
	private Boolean isOnLocationConstraintsList; //utility variable 

	public KVMEvent(){
	}	
	
	public Long getEventId()
	{
		return eventId;
	}
	
	public void setEventId(Long eventId)
	{
		this.eventId = eventId;
	}

	public Long getRegistrationTime()
	{
		return registrationTime;
	}
	
	public void setRegistrationTime(Long registrationTime)
	{
		this.registrationTime = registrationTime;
	}
	
	public String getType()
	{
		return type;
	}
	
	public void setType(String Type)
	{
		this.type = Type;
	}

	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String Description)
	{
		this.description = Description;
	}

	public Long getSourceUser()
	{
		return sourceUser;
	}
	
	public void setSourceUser(Long sourceUser)
	{
		this.sourceUser = sourceUser;
	}
	
	public Vector getTargetUsers()
	{
		return targetUsers;
	}
	
	public void setTargetUsers(Vector targetUsers)
	{
		this.targetUsers = targetUsers;
	}
	
	
	public String getServiceName()
	{
		return serviceName;
	}
	
	public void setServiceName(String ServiceName)
	{
		this.serviceName = ServiceName;
	}
	
	public String getServiceApi()
	{
		return serviceApi;
	}
	
	public void setServiceApi(String ServiceApi)
	{
		this.serviceApi = ServiceApi;
	}
	
	public KVMCBTrigger getCBTrigger()
	{
		return cbTrigger;
	}
	
	public void setCBTrigger(KVMCBTrigger KVMCBTrigger)
	{
		this.cbTrigger = KVMCBTrigger;
	}
		
	public Boolean getIsOnLocationConstraintsList()
	{
		return isOnLocationConstraintsList;
	}
	
	public void setIsOnLocationConstraintsList(Boolean isOnLocationConstraintsList)
	{
		this.isOnLocationConstraintsList = isOnLocationConstraintsList;
	}
	

	//	 Additional code required for KSOAP serialization
	/**
	 * All memeber values to be serialized by KSOAP should be defined in this array
	 * Used by KSOAP Implementation methods
	 */
	private static PropertyInfo[] PI_PROP_ARRAY = {
			new PropertyInfo("eventId", ElementType.LONG_CLASS),
			new PropertyInfo("registrationTime", ElementType.LONG_CLASS),
			new PropertyInfo("type", ElementType.STRING_CLASS),
			new PropertyInfo("description", ElementType.STRING_CLASS),
			new PropertyInfo("sourceUser", ElementType.LONG_CLASS),
			new PropertyInfo("targetUsers", ElementType.VECTOR_CLASS),
			new PropertyInfo("serviceName", ElementType.STRING_CLASS),
			new PropertyInfo("serviceApi", ElementType.STRING_CLASS),
			new PropertyInfo("cbTrigger", ElementType.OBJECT_CLASS),
			new PropertyInfo("isOnLocationConstraintsList", ElementType.BOOLEAN_CLASS)};			

	/**
	 * Get property by parameter order
	 * Needed for KSOAP serialization 
	 */
	public Object getProperty(int param) {
		switch (param) {
		case 0:
			return getEventId();
		case 1:
			return getRegistrationTime();
		case 2:
			return getType();
		case 3:
			return getDescription();
		case 4:
			return getSourceUser();
		case 5:
			return getTargetUsers();
		case 6:
			return getServiceName();
		case 7:
			return getServiceApi();
		case 8:
			return getCBTrigger();
		case 9:
			return getIsOnLocationConstraintsList();
		default:
			return null;
		}
	}

	/**
	 * Set property by parameter order
	 * Needed for KSOAP serialization 
	 */
	public void setProperty(int param, Object obj) {
		switch (param) {
		case 0:
			setEventId((Long) obj);
			return;
		case 1:
			 setRegistrationTime((Long) obj);
			 return;
		case 2:
			 setType((String) obj);
			 return;
		case 3:
			 setDescription((String) obj);
			 return;
		case 4:
			 setSourceUser((Long) obj);
			 return;
		case 5:
			 setTargetUsers((Vector) obj);
			 return;
		case 6:
			 setServiceName((String) obj);
			 return;
		case 7:
			 setServiceApi((String) obj);
			 return;
		case 8:
			 setCBTrigger((KVMCBTrigger) obj);
			 return;
		case 9:
			 setIsOnLocationConstraintsList((Boolean) obj);
			 return;
		}
	}
	
	/**
	 * Needed for KSOAP serialization 
	 */
	public int getPropertyCount() {
		return PI_PROP_ARRAY.length;
	}

	/**
	 * Needed for KSOAP serialization 
	 */
	public void getPropertyInfo(int param,
			org.kobjects.serialization.PropertyInfo propertyInfo) {
		propertyInfo.name = PI_PROP_ARRAY[param].name;
		propertyInfo.nonpermanent = PI_PROP_ARRAY[param].nonpermanent;
		propertyInfo.copy(PI_PROP_ARRAY[param]);
	}

	
}
