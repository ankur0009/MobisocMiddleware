package ksoapDataObjects.socialStateLearning;
import org.kobjects.serialization.ElementType;
import org.kobjects.serialization.KvmSerializable;
import org.kobjects.serialization.PropertyInfo;

/**
 * KVMCoordinates class
 * NOTE: must implement org.kobjects.serialization.KvmSerializable
 * @author Arun
 *
 */
public class KVMCoordinates implements KvmSerializable {
	public KVMCoordinates() {
	}

	private String lat;

	private String lon;

	private String alt;

	// END KSOAP changes......................................................


	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	
	// Additional code required for KSOAP serialization
	/**
	 * All memeber values to be serialized by KSOAP should be defined in this array
	 * Used by KSOAP Implementation methods
	 */
	private static PropertyInfo[] PI_PROP_ARRAY = {
			new PropertyInfo("lat", ElementType.STRING_CLASS),
			new PropertyInfo("lon", ElementType.STRING_CLASS),
			new PropertyInfo("alt", ElementType.STRING_CLASS)};

	/**
	 * Get property by parameter order
	 * Needed for KSOAP serialization 
	 */
	public Object getProperty(int param) {
		switch (param) {
		case 0:
			return getLat();
		case 1:
			return getLon();
		case 2:
			return getAlt();
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
			setLat((String) obj);
			return;
		case 1:
			setLon((String) obj);
			return;
		case 2:
			setAlt((String) obj);
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
