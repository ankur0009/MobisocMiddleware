package ksoapDataObjects.common;
/*
 * Class:   SocialEvent
 *
 */

public class KVMSocialEvent implements  org.kobjects.serialization.KvmSerializable {
     org.kobjects.serialization.PropertyInfo KSOAP_event_type= new org.kobjects.serialization.PropertyInfo("KSOAP_event_type",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_end_time= new org.kobjects.serialization.PropertyInfo("end_time",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_owner= new org.kobjects.serialization.PropertyInfo("KSOAP_owner",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_description= new org.kobjects.serialization.PropertyInfo("description",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_eid= new org.kobjects.serialization.PropertyInfo("eid",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_location= new org.kobjects.serialization.PropertyInfo("location",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_update_time= new org.kobjects.serialization.PropertyInfo("KSOAP_update_time",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_start_time= new org.kobjects.serialization.PropertyInfo("start_time",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_name= new org.kobjects.serialization.PropertyInfo("name",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_host= new org.kobjects.serialization.PropertyInfo("KSOAP_host",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo[] KSOAP_PROP_ARRAY= {KSOAP_update_time,KSOAP_owner,KSOAP_event_type,KSOAP_end_time,KSOAP_host,KSOAP_description,KSOAP_eid,KSOAP_location,KSOAP_start_time,KSOAP_name,};

     private java.lang.Long eid;

     private java.lang.String name;

     private java.lang.String host;

     private java.lang.String description;

     private java.lang.String event_type;

     private java.lang.Long owner;

     private java.lang.Long start_time;

     private java.lang.Long end_time;

     private java.lang.Long update_time;

     private java.lang.Long location;

     public java.lang.String getDescription() {
          return description;
     }

     public void setDescription(java.lang.String description) {
          this.description = description;
     }

     public java.lang.Long getEid() {
          return eid;
     }

     public void setEid(java.lang.Long eid) {
          this.eid = eid;
     }

     public java.lang.Long getEnd_time() {
          return end_time;
     }

     public void setEnd_time(java.lang.Long end_time) {
          this.end_time = end_time;
     }

     public java.lang.String getEvent_type() {
          return event_type;
     }

     public void setEvent_type(java.lang.String event_type) {
          this.event_type = event_type;
     }

     public java.lang.String getHost() {
          return host;
     }

     public void setHost(java.lang.String host) {
          this.host = host;
     }

     public java.lang.Long getLocation() {
          return location;
     }

     public void setLocation(java.lang.Long location) {
          this.location = location;
     }

     public java.lang.String getName() {
          return name;
     }

     public void setName(java.lang.String name) {
          this.name = name;
     }

     public java.lang.Long getOwner() {
          return owner;
     }

     public void setOwner(java.lang.Long owner) {
          this.owner = owner;
     }

     public java.lang.Long getStart_time() {
          return start_time;
     }

     public void setStart_time(java.lang.Long start_time) {
          this.start_time = start_time;
     }

     public java.lang.Long getUpdate_time() {
          return update_time;
     }

     public void setUpdate_time(java.lang.Long update_time) {
          this.update_time = update_time;
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
update_time = (java.lang.Long) obj;
}
 else if (param == 1) {
owner = (java.lang.Long) obj;
}
 else if (param == 2) {
event_type = (String) obj;
}
 else if (param == 3) {
end_time = (java.lang.Long) obj;
}
 else if (param == 4) {
host = (String) obj;
}
 else if (param == 5) {
description = (String) obj;
}
 else if (param == 6) {
eid = (java.lang.Long) obj;
}
 else if (param == 7) {
location = (java.lang.Long) obj;
}
 else if (param == 8) {
start_time = (java.lang.Long) obj;
}
 else if (param == 9) {
name = (String) obj;
}
     }

     public java.lang.Object getProperty(int param) {
          if (param == 0 ) {
return update_time;
}
 else if (param == 1) {
return owner;
}
 else if (param == 2) {
return event_type;
}
 else if (param == 3) {
return end_time;
}
 else if (param == 4) {
return host;
}
 else if (param == 5) {
return description;
}
 else if (param == 6) {
return eid;
}
 else if (param == 7) {
return location;
}
 else if (param == 8) {
return start_time;
}
 else if (param == 9) {
return name;
}
 else { 
 return null; 
 }
     }

} // end SocialEvent
