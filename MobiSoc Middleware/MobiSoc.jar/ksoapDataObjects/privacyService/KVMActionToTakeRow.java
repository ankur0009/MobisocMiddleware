package ksoapDataObjects.privacyService;

/*
 * Class:   KVMActionToTakeRow
 *
 */
/**
 * KVMActionToTakeRow class
 * NOTE: Class generated through KVM tool. This class is corresponding to ActionToTakeRow.
 * @author NeerajR.
 *
 */

public class KVMActionToTakeRow implements org.kobjects.serialization.KvmSerializable {

	 public java.lang.Long att_id;

     public java.lang.Boolean att_type_primary_msg;

     public java.lang.Boolean att_typ_secondary_msg;

     public java.lang.Boolean att_type_sys_msg;

     public java.lang.String att_primary_msg;

     public java.lang.String att_secondary_msg;

     public java.lang.String att_sys_msg;

     org.kobjects.serialization.PropertyInfo KSOAP_att_sys_msg= new org.kobjects.serialization.PropertyInfo("att_sys_msg",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_att_typ_secondary_msg= new org.kobjects.serialization.PropertyInfo("att_typ_secondary_msg",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_att_primary_msg= new org.kobjects.serialization.PropertyInfo("att_primary_msg",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_att_secondary_msg= new org.kobjects.serialization.PropertyInfo("att_secondary_msg",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_att_type_primary_msg= new org.kobjects.serialization.PropertyInfo("att_type_primary_msg",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_att_type_sys_msg= new org.kobjects.serialization.PropertyInfo("att_type_sys_msg",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_att_id= new org.kobjects.serialization.PropertyInfo("att_id",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo[] KSOAP_PROP_ARRAY= {KSOAP_att_sys_msg,KSOAP_att_typ_secondary_msg,KSOAP_att_primary_msg,KSOAP_att_secondary_msg,KSOAP_att_type_primary_msg,KSOAP_att_type_sys_msg,KSOAP_att_id};

     public java.lang.Long getAtt_id() {
          return att_id;
     }

     public void setAtt_id(java.lang.Long att_id) {
          this.att_id = att_id;
     }

     public java.lang.String getAtt_primary_msg() {
          return att_primary_msg;
     }

     public void setAtt_primary_msg(java.lang.String att_primary_msg) {
          this.att_primary_msg = att_primary_msg;
     }

     public java.lang.String getAtt_secondary_msg() {
          return att_secondary_msg;
     }

     public void setAtt_secondary_msg(java.lang.String att_secondary_msg) {
          this.att_secondary_msg = att_secondary_msg;
     }

     public java.lang.String getAtt_sys_msg() {
          return att_sys_msg;
     }

     public void setAtt_sys_msg(java.lang.String att_sys_msg) {
          this.att_sys_msg = att_sys_msg;
     }

     public java.lang.Boolean getAtt_typ_secondary_msg() {
          return att_typ_secondary_msg;
     }

     public void setAtt_typ_secondary_msg(java.lang.Boolean att_typ_secondary_msg) {
          this.att_typ_secondary_msg = att_typ_secondary_msg;
     }

     public java.lang.Boolean getAtt_type_primary_msg() {
          return att_type_primary_msg;
     }

     public void setAtt_type_primary_msg(java.lang.Boolean att_type_primary_msg) {
          this.att_type_primary_msg = att_type_primary_msg;
     }

     public java.lang.Boolean getAtt_type_sys_msg() {
          return att_type_sys_msg;
     }

     public void setAtt_type_sys_msg(java.lang.Boolean att_type_sys_msg) {
          this.att_type_sys_msg = att_type_sys_msg;
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
att_sys_msg = (String) obj;
}
 else if (param == 1) {
att_typ_secondary_msg = (java.lang.Boolean) obj;
}
 else if (param == 2) {
att_primary_msg = (String) obj;
}
 else if (param == 3) {
att_secondary_msg = (String) obj;
}
 else if (param == 4) {
att_type_primary_msg = (java.lang.Boolean) obj;
}
 else if (param == 5) {
att_type_sys_msg = (java.lang.Boolean) obj;
}
 else if (param == 6) {
att_id = (java.lang.Long) obj;
}
     }

     public java.lang.Object getProperty(int param) {
          if (param == 0 ) {
return att_sys_msg;
}
 else if (param == 1) {
return att_typ_secondary_msg;
}
 else if (param == 2) {
return att_primary_msg;
}
 else if (param == 3) {
return att_secondary_msg;
}
 else if (param == 4) {
return att_type_primary_msg;
}
 else if (param == 5) {
return att_type_sys_msg;
}
 else if (param == 6) {
return att_id;
}
 else { 
 return null; 
 }
     }

} // end KVMActionToTakeRow
