package ksoapDataObjects.privacyService;

/*
 * Class:   KVMAccessControlMgrRow
 *
 */

/**
 * KVMAccessControlMgrRow class
 * NOTE: Class generated through KVM tool. This class is corresponding to AccessControlMgrRow.
 * @author NeerajR.
 *
 */

public class KVMAccessControlMgrRow implements org.kobjects.serialization.KvmSerializable {
	 public java.lang.Long acm_ac_st_id;

     public java.lang.Long acm_pe_id;

     public java.lang.Long acm_se_id;

     public java.lang.Boolean acm_st_enabled;

     public java.lang.Boolean acm_access_allowed;

     org.kobjects.serialization.PropertyInfo KSOAP_acm_se_id= new org.kobjects.serialization.PropertyInfo("acm_se_id",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_acm_pe_id= new org.kobjects.serialization.PropertyInfo("acm_pe_id",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_acm_st_enabled= new org.kobjects.serialization.PropertyInfo("acm_st_enabled",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_acm_access_allowed= new org.kobjects.serialization.PropertyInfo("acm_access_allowed",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_acm_ac_st_id= new org.kobjects.serialization.PropertyInfo("acm_ac_st_id",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo[] KSOAP_PROP_ARRAY= {KSOAP_acm_se_id,KSOAP_acm_pe_id,KSOAP_acm_st_enabled,KSOAP_acm_access_allowed,KSOAP_acm_ac_st_id};

     public java.lang.Long getAcm_ac_st_id() {
          return acm_ac_st_id;
     }

     public void setAcm_ac_st_id(java.lang.Long acm_ac_st_id) {
          this.acm_ac_st_id = acm_ac_st_id;
     }

     public java.lang.Boolean getAcm_access_allowed() {
          return acm_access_allowed;
     }

     public void setAcm_access_allowed(java.lang.Boolean acm_access_allowed) {
          this.acm_access_allowed = acm_access_allowed;
     }

     public java.lang.Long getAcm_pe_id() {
          return acm_pe_id;
     }

     public void setAcm_pe_id(java.lang.Long acm_pe_id) {
          this.acm_pe_id = acm_pe_id;
     }

     public java.lang.Long getAcm_se_id() {
          return acm_se_id;
     }

     public void setAcm_se_id(java.lang.Long acm_se_id) {
          this.acm_se_id = acm_se_id;
     }

     public java.lang.Boolean getAcm_st_enabled() {
          return acm_st_enabled;
     }

     public void setAcm_st_enabled(java.lang.Boolean acm_st_enabled) {
          this.acm_st_enabled = acm_st_enabled;
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
acm_se_id = (java.lang.Long) obj;
}
 else if (param == 1) {
acm_pe_id = (java.lang.Long) obj;
}
 else if (param == 2) {
acm_st_enabled = (java.lang.Boolean) obj;
}
 else if (param == 3) {
acm_access_allowed = (java.lang.Boolean) obj;
}
 else if (param == 4) {
acm_ac_st_id = (java.lang.Long) obj;
}
     }

     public java.lang.Object getProperty(int param) {
          if (param == 0 ) {
return acm_se_id;
}
 else if (param == 1) {
return acm_pe_id;
}
 else if (param == 2) {
return acm_st_enabled;
}
 else if (param == 3) {
return acm_access_allowed;
}
 else if (param == 4) {
return acm_ac_st_id;
}
 else { 
 return null; 
 }
     }

} // end KVMAccessControlMgrRow
