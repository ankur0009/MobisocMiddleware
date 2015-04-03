package ksoapDataObjects.common;
import java.util.Vector;

import org.kobjects.serialization.ElementType;
import org.kobjects.serialization.KvmSerializable;
import org.kobjects.serialization.PropertyInfo;

/**
 * EvenData DTO class
 * NOTE: must implement org.kobjects.serialization.KvmSerializable
 * @author Arun
 *
 */
public class KVMEventData implements KvmSerializable {
	public KVMEventData() {
	}

	private Long fireTime;

	private Long eventId;

	private Long registrationTime;

	private String type;

	private String description;

	private Long sourceUser;

	private Vector targetUsers;



	// END KSOAP changes......................................................


	public Long getFireTime() {
		return fireTime;
	}

	public void setFireTime(Long fireTime) {
		this.fireTime = fireTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public Long getRegistrationTime() {
		return registrationTime;
	}

	public void setRegistrationTime(Long registrationTime) {
		this.registrationTime = registrationTime;
	}

	public Long getSourceUser() {
		return sourceUser;
	}

	public void setSourceUser(Long sourceUser) {
		this.sourceUser = sourceUser;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Vector getTargetUsers() {
		if (targetUsers == null)
			targetUsers = new Vector();
		return targetUsers;
	}

	public void setTargetUsers(Vector targetUsers) {

		this.targetUsers = targetUsers;
	}

	
	// Additional code required for KSOAP serialization
	/**
	 * All memeber values to be serialized by KSOAP should be defined in this array
	 * Used by KSOAP Implementation methods
	 */
	private static PropertyInfo[] PI_PROP_ARRAY = {
			new PropertyInfo("fireTime", ElementType.LONG_CLASS),
			new PropertyInfo("eventId", ElementType.LONG_CLASS),
			new PropertyInfo("registrationTime", ElementType.LONG_CLASS),
			new PropertyInfo("type", ElementType.STRING_CLASS),
			new PropertyInfo("description", ElementType.STRING_CLASS),
			new PropertyInfo("sourceUser", ElementType.STRING_CLASS),
			new PropertyInfo("targetUsers", ElementType.VECTOR_CLASS) };

	/**
	 * Get property by parameter order
	 * Needed for KSOAP serialization 
	 */
	public Object getProperty(int param) {
		switch (param) {
		case 0:
			return getFireTime();
		case 1:
			return getEventId();
		case 2:
			return getRegistrationTime();
		case 3:
			return getType();
		case 4:
			return getDescription();
		case 5:
			return getSourceUser();
		case 6:
			return getTargetUsers();
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
			setFireTime((Long) obj);
			return;
		case 1:
			setEventId((Long) obj);
			return;
		case 2:
			setRegistrationTime((Long) obj);
			return;
		case 3:
			setType((String) obj);
			return;
		case 4:
			setDescription((String) obj);
			return;
		case 5:
			setSourceUser((Long) obj);
			return;
		case 6:
			setTargetUsers((Vector) obj);
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
