package ksoapDataObjects.common;
import org.kobjects.serialization.ElementType;
import org.kobjects.serialization.KvmSerializable;
import org.kobjects.serialization.PropertyInfo;

/**
 * KVMAccessPointInfo class
 * NOTE: must implement org.kobjects.serialization.KvmSerializable
 * @author Ankur
 *
 */
public class KVMAccessPointInfo implements KvmSerializable {
	public KVMAccessPointInfo() {
	}

	private String ssId;

	private String macId;

	private String signalStrength;
	
	private String isPointKnown; // "yes" if point is known , "no" otherwise

	// END KSOAP changes......................................................


	public String getSsId() {
		return ssId;
	}

	public void setSsId(String ssId) {
		this.ssId = ssId;
	}

	public String getMacId() {
		return macId;
	}

	public void setMacId(String macId) {
		this.macId = macId;
	}

	public String getSignalStrength() {
		return signalStrength;
	}

	public void setSignalStrength(String signalStrength) {
		this.signalStrength = signalStrength;
	}

	public String getIsPointKnown() {
		return isPointKnown;
	}

	public void setIsPointKnown(String isPointKnown) {
		this.isPointKnown = isPointKnown;
	}
	
	// Additional code required for KSOAP serialization
	/**
	 * All memeber values to be serialized by KSOAP should be defined in this array
	 * Used by KSOAP Implementation methods
	 */
	private static PropertyInfo[] PI_PROP_ARRAY = {
			new PropertyInfo("ssId", ElementType.STRING_CLASS),
			new PropertyInfo("macId", ElementType.STRING_CLASS),
			new PropertyInfo("signalStrength", ElementType.STRING_CLASS),
			new PropertyInfo("isPointKnown", ElementType.STRING_CLASS)};


	/**
	 * Get property by parameter order
	 * Needed for KSOAP serialization 
	 */
	public Object getProperty(int param) {
		switch (param) {
		case 0:
			return getSsId();
		case 1:
			return getMacId();
		case 2:
			return getSignalStrength();
		case 3:
			return getIsPointKnown();
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
			setSsId((String) obj);
			return;
		case 1:
			setMacId((String) obj);
			return;
		case 2:
			setSignalStrength((String) obj);
			return;
		case 3:
			setIsPointKnown((String) obj);
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
