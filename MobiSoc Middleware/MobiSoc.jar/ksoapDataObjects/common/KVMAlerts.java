package ksoapDataObjects.common;
import java.util.Vector;

import org.kobjects.serialization.ElementType;
import org.kobjects.serialization.KvmSerializable;
import org.kobjects.serialization.PropertyInfo;

/**
 * Alerts DTO class, used primarily as a wrapper for EvenData list
 * NOTE must implement org.kobjects.serialization.KvmSerializable
 * @author Arun
 *
 */
public class KVMAlerts implements KvmSerializable {
	public KVMAlerts() {
	}
	/**
	 * Requested user's ID
	 */	
	private Long userId;

	/**
	 * Vector hold EventData objects
	 */
	private Vector events;

	/**
	 * Returns true if there are any events
	 * Used for testing boolean value with KSOAP
	 * 
	 */
	private Boolean hasEvents;
	


	public Vector getEvents() {
		if (events == null) {
			events = new Vector();
		}
		return events;
	}

	public void setEvents(Vector events) {
		this.events = events;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Boolean getHasEvents() {
		return hasEvents;
	}

	public void setHasEvents(Boolean hasEvents) {
		this.hasEvents = hasEvents;
	}

	
	// Additional code required for KSOAP serialization
	/**
	 * All memeber values to be serialized by KSOAP should be defined in this array
	 * Used by KSOAP Implementation methods
	 */
	private static PropertyInfo[] PI_PROP_ARRAY = {
			new PropertyInfo("userId", ElementType.LONG_CLASS),
			new PropertyInfo("events", ElementType.VECTOR_CLASS),
			new PropertyInfo("hasEvents", ElementType.BOOLEAN_CLASS) };

	/**
	 * Get property by parameter order
	 * Needed for KSOAP serialization 
	 */
	public Object getProperty(int param) {
		switch (param) {
		case 0:
			return getUserId();
		case 1:
			return getEvents();
		case 2:
			return getHasEvents();
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
			setUserId((Long) obj);
			return;
		case 1:
			setEvents((Vector) obj);
			return;
		case 2:
			setHasEvents((Boolean) obj);
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
