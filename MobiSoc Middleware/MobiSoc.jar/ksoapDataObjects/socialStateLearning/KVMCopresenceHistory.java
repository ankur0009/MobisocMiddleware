package ksoapDataObjects.socialStateLearning;
/*
 * Class:   CopresenceHistory
 *
 */



public class KVMCopresenceHistory implements  org.kobjects.serialization.KvmSerializable {
     org.kobjects.serialization.PropertyInfo KSOAP_starttime= new org.kobjects.serialization.PropertyInfo("starttime",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_placeid= new org.kobjects.serialization.PropertyInfo("placeid",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_endtime= new org.kobjects.serialization.PropertyInfo("endtime",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo[] KSOAP_PROP_ARRAY= {KSOAP_starttime,KSOAP_placeid,KSOAP_endtime};

     private java.lang.Long placeid;

     private java.lang.Long starttime;

     private java.lang.Long endtime;

     public java.lang.Long getEndtime() {
          return endtime;
     }

     public void setEndtime(java.lang.Long endtime) {
          this.endtime = endtime;
     }

     public Long getPlaceid() {
          return placeid;
     }

     public void setPlaceid(Long placeid) {
          this.placeid = placeid;
     }

     public java.lang.Long getStarttime() {
          return starttime;
     }

     public void setStarttime(java.lang.Long starttime) {
          this.starttime = starttime;
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
starttime = (java.lang.Long) obj;
}
 else if (param == 1) {
placeid = (java.lang.Long) obj;
}
 else if (param == 2) {
endtime = (java.lang.Long) obj;
}
     }

     public java.lang.Object getProperty(int param) {
          if (param == 0 ) {
return starttime;
}
 else if (param == 1) {
return placeid;
}
 else if (param == 2) {
return endtime;
}
 else { 
 return null; 
 }
     }

} // end CopresenceHistory
