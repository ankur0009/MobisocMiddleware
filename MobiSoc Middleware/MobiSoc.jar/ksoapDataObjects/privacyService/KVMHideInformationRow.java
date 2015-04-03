package ksoapDataObjects.privacyService;

/*
 * Class:   KVMHideInformationRow
 *
 */

/**
 * KVMHideInformationRow class
 * NOTE: Class generated through KVM tool. This class is corresponding to HideInformationRow.
 * @author NeerajR.
 *
 */

public class KVMHideInformationRow implements org.kobjects.serialization.KvmSerializable {
	 
     public java.lang.Long hinfo_id;

     public java.lang.Boolean hinfo_location;

     public java.lang.Boolean hinfo_time_based_events;

     public java.lang.Boolean hinfo_loc_based_events;

     public java.lang.Boolean hinfo_all_events;

     public java.lang.Boolean hinfo_user_profile_name;

     org.kobjects.serialization.PropertyInfo KSOAP_hinfo_id= new org.kobjects.serialization.PropertyInfo("hinfo_id",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_hinfo_user_profile_name= new org.kobjects.serialization.PropertyInfo("hinfo_user_profile_name",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_hinfo_location= new org.kobjects.serialization.PropertyInfo("hinfo_location",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_hinfo_loc_based_events= new org.kobjects.serialization.PropertyInfo("hinfo_loc_based_events",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_hinfo_time_based_events= new org.kobjects.serialization.PropertyInfo("hinfo_time_based_events",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_hinfo_all_events= new org.kobjects.serialization.PropertyInfo("hinfo_all_events",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo[] KSOAP_PROP_ARRAY= {KSOAP_hinfo_id,KSOAP_hinfo_user_profile_name,KSOAP_hinfo_location,KSOAP_hinfo_loc_based_events,KSOAP_hinfo_time_based_events,KSOAP_hinfo_all_events};

     public java.lang.Long getHinfo_id() {
          return hinfo_id;
     }

     public void setHinfo_id(java.lang.Long hinfo_id) {
          this.hinfo_id = hinfo_id;
     }

     public java.lang.Boolean getHinfo_loc_based_events() {
          return hinfo_loc_based_events;
     }

     public void setHinfo_loc_based_events(java.lang.Boolean hinfo_loc_based_events) {
          this.hinfo_loc_based_events = hinfo_loc_based_events;
     }

     public java.lang.Boolean getHinfo_location() {
          return hinfo_location;
     }

     public void setHinfo_location(java.lang.Boolean hinfo_location) {
          this.hinfo_location = hinfo_location;
     }

     public java.lang.Boolean gethinfo_all_events() {
          return hinfo_all_events;
     }

     public void setHinfo_all_events(java.lang.Boolean hinfo_all_events) {
          this.hinfo_all_events = hinfo_all_events;
     }

     public java.lang.Boolean getHinfo_time_based_events() {
          return hinfo_time_based_events;
     }

     public void setHinfo_time_based_events(java.lang.Boolean hinfo_time_based_events) {
          this.hinfo_time_based_events = hinfo_time_based_events;
     }

     public java.lang.Boolean getHinfo_user_profile_name() {
          return hinfo_user_profile_name;
     }

     public void setHinfo_user_profile_name(java.lang.Boolean hinfo_user_profile_name) {
          this.hinfo_user_profile_name = hinfo_user_profile_name;
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
hinfo_id = (java.lang.Long) obj;
}
 else if (param == 1) {
hinfo_user_profile_name = (java.lang.Boolean) obj;
}
 else if (param == 2) {
hinfo_location = (java.lang.Boolean) obj;
}
 else if (param == 3) {
hinfo_loc_based_events = (java.lang.Boolean) obj;
}
 else if (param == 4) {
hinfo_time_based_events = (java.lang.Boolean) obj;
}
 else if (param == 5) {
hinfo_all_events = (java.lang.Boolean) obj;
}
     }

     public java.lang.Object getProperty(int param) {
          if (param == 0 ) {
return hinfo_id;
}
 else if (param == 1) {
return hinfo_user_profile_name;
}
 else if (param == 2) {
return hinfo_location;
}
 else if (param == 3) {
return hinfo_loc_based_events;
}
 else if (param == 4) {
return hinfo_time_based_events;
}
 else if (param == 5) {
return hinfo_all_events;
}
 else { 
 return null; 
 }
     }

} // end KVMHideInformationRow
