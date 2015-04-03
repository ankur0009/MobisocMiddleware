package ksoapDataObjects.socialStateLearning;
/*
 * Class:   UserPlaceHistory
 *
 */
public class KVMUserPlaceHistory implements  org.kobjects.serialization.KvmSerializable {
     org.kobjects.serialization.PropertyInfo KSOAP_startTime= new org.kobjects.serialization.PropertyInfo("startTime",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_placeId= new org.kobjects.serialization.PropertyInfo("placeId",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_endTime= new org.kobjects.serialization.PropertyInfo("endTime",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo[] KSOAP_PROP_ARRAY= {KSOAP_startTime,KSOAP_placeId,KSOAP_endTime};

     private java.lang.Long placeId;

     private java.lang.Long startTime;

     private java.lang.Long endTime;

     public java.lang.Long getEndTime() {
          return endTime;
     }

     public void setEndTime(java.lang.Long endTime) {
          this.endTime = endTime;
     }

     public java.lang.Long getPlaceId() {
          return placeId;
     }

     public void setPlaceId(java.lang.Long placeId) {
          this.placeId = placeId;
     }

     public java.lang.Long getStartTime() {
          return startTime;
     }

     public void setStartTime(java.lang.Long startTime) {
          this.startTime = startTime;
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
startTime = (java.lang.Long) obj;
}
 else if (param == 1) {
placeId = (java.lang.Long) obj;
}
 else if (param == 2) {
endTime = (java.lang.Long) obj;
}
     }

     public java.lang.Object getProperty(int param) {
          if (param == 0 ) {
return startTime;
}
 else if (param == 1) {
return placeId;
}
 else if (param == 2) {
return endTime;
}
 else { 
 return null; 
 }
     }

} // end UserPlaceHistory
