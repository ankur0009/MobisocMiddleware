package ksoapDataObjects.socialStateLearning;
/*
 * Class:   NearbyEvent
 *
 */


public class KVMNearbyEvent implements org.kobjects.serialization.KvmSerializable {
     org.kobjects.serialization.PropertyInfo KSOAP_eventId= new org.kobjects.serialization.PropertyInfo("eventId",org.kobjects.serialization.PropertyInfo.LONG_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_distanceToEvent= new org.kobjects.serialization.PropertyInfo("distanceToEvent",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo[] KSOAP_PROP_ARRAY= {KSOAP_eventId,KSOAP_distanceToEvent};

     private long eventId;

     private java.lang.String distanceToEvent;

     public java.lang.String getDistanceToEvent() {
          return distanceToEvent;
     }

     public void setDistanceToEvent(java.lang.String distanceToEvent) {
          this.distanceToEvent = distanceToEvent;
     }

     public long getEventId() {
          return eventId;
     }

     public void setEventId(long eventId) {
          this.eventId = eventId;
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
eventId = ((Long) obj).longValue();
}
 else if (param == 1) {
distanceToEvent = (String) obj;
}
     }

     public java.lang.Object getProperty(int param) {
          if (param == 0 ) {
return new Long(eventId);
}
 else if (param == 1) {
return new String(distanceToEvent);
}
 else { 
 return null; 
 }
     }

} // end NearbyEvent
