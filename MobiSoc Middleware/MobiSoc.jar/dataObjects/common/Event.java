package dataObjects.common;

import java.sql.Timestamp;
import java.util.*;

import java.io.Serializable;



public class Event implements Serializable {
	private long eventId;
	private Timestamp registrationTime;
	private String type;
	private String description;
	private long sourceUser;
	private ArrayList targetUsers;
	private String serviceName;
	private String serviceApi;
	private CBTrigger cbTrigger;
	
	//utility variables
	private boolean isOnLocationConstraintsList; 
	
	/**
	 * 
	 * Constructor with no parameters. Mostly used for testing
	 *
	 */
	public Event()
	{
		eventId = -1;
		type = null;
		description = null;
		sourceUser = -1;
		targetUsers = null;
		serviceName = null;
		serviceApi = null;
		cbTrigger = null;
	}
	
	/**
	 * 
	 * Note registration time is usually not set when creating the 
	 * event. Event registration time is set later, when event is 
	 * registered with the system
	 * 
	 * @param XeventId
	 * @param Xtype
	 * @param Xdescription
	 * @param XsourceUser
	 * @param XtargetUsers
	 * @param XserviceName
	 * @param XserviceApi
	 * @param XcbTrigger
	 * 
	 * 
	 */
	public Event(long XeventId, String Xtype, String Xdescription, long XsourceUser, 
			ArrayList XtargetUsers, String XserviceName, String XserviceApi, CBTrigger XcbTrigger)
	{
		eventId = XeventId;
		type = Xtype;
		description = Xdescription;
		sourceUser = XsourceUser;
		targetUsers = XtargetUsers;
		serviceName = XserviceName;
		serviceApi = XserviceApi;
		cbTrigger = XcbTrigger; 
	}
	
	public long getEventId()
	{
		return eventId;
	}
	
	public void setEventId(long XeventId)
	{
		eventId = XeventId;
	}

	public Timestamp getRegistrationTime()
	{
		return registrationTime;
	}
	
	public void setRegistrationTime(Timestamp XregistrationTime)
	{
		registrationTime = XregistrationTime;
	}
	
	public String getType()
	{
		return type;
	}
	
	public void setType(String XType)
	{
		type = XType;
	}

	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String XDescription)
	{
		description = XDescription;
	}

	public long getSourceUser()
	{
		return sourceUser;
	}
	
	public void setSourceUser(long XsourceUser)
	{
		sourceUser = XsourceUser;
	}
	
	public ArrayList getTargetUsers()
	{
		return targetUsers;
	}
	
	public void setTargetUsers(ArrayList XtargetUsers)
	{
		targetUsers = XtargetUsers;
	}
	
	
	public String getServiceName()
	{
		return serviceName;
	}
	
	public void setServiceName(String XServiceName)
	{
		serviceName = XServiceName;
	}
	
	public String getServiceApi()
	{
		return serviceApi;
	}
	
	public void setServiceApi(String XServiceApi)
	{
		serviceApi = XServiceApi;
	}
	
	public CBTrigger getCBTrigger()
	{
		return cbTrigger;
	}
	
	public void setCBTrigger(CBTrigger XCBTrigger)
	{
		cbTrigger = XCBTrigger;
	}
	
	
	public boolean getIsOnLocationConstraintsList()
	{
		return isOnLocationConstraintsList;
	}
	
	public void setIsOnLocationConstraintsList(boolean XisOnLocationConstraintsList)
	{
		isOnLocationConstraintsList = XisOnLocationConstraintsList;
	}
	
}
