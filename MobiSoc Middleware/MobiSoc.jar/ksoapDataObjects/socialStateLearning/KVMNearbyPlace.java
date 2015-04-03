package ksoapDataObjects.socialStateLearning;
/*
 * Class:   NearbyPlace
 *
 */


public class KVMNearbyPlace implements  org.kobjects.serialization.KvmSerializable {
     org.kobjects.serialization.PropertyInfo KSOAP_distanceToPlace= new org.kobjects.serialization.PropertyInfo("distanceToPlace",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_placeId= new org.kobjects.serialization.PropertyInfo("placeId",org.kobjects.serialization.PropertyInfo.LONG_CLASS);

     org.kobjects.serialization.PropertyInfo[] KSOAP_PROP_ARRAY= {KSOAP_distanceToPlace,KSOAP_placeId};

     private long placeId;

     private java.lang.String distanceToPlace;

     public java.lang.String getDistanceToPlace() {
          return distanceToPlace;
     }

     public void setDistanceToPlace(java.lang.String distanceToPlace) {
          this.distanceToPlace = distanceToPlace;
     }

     public long getPlaceId() {
          return placeId;
     }

     public void setPlaceId(long placeId) {
          this.placeId = placeId;
     }

     public void getPropertyInfo(int param, org.kobjects.serialization.PropertyInfo propertyInfo) {
          propertyInfo.name = KSOAP_PROP_ARRAY[param].name;
propertyInfo.nonpermanent = KSOAP_PROP_ARRAY[param].nonpermanent;
propertyInfo.copy(KSOAP_PROP_ARRAY[param]);
     }

     public int getPropertyCount() {
          return KSOAP_PROP_ARRAY.length;
     }

     public void setProperty(int param, java.lang.Object obj) {
          if (param == 0 ) {
distanceToPlace = (String) obj;
}
 else if (param == 1) {
placeId = ((Long) obj).longValue();
}
     }

     public java.lang.Object getProperty(int param) {
          if (param == 0 ) {
return new String(distanceToPlace);
}
 else if (param == 1) {
return new Long(placeId);
}
 else { 
 return null; 
 }
     }

} // end NearbyPlace
