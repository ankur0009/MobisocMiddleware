package ksoapDataObjects.common;
/*
 * Class:   KVMGroupData
 *
 */


public class KVMGroupData implements  org.kobjects.serialization.KvmSerializable {
     public java.lang.Long groupid;

     public java.lang.String name;

     public java.lang.Long ownerid;

     public java.lang.String type;

     public java.lang.Long creationtime;

     public java.lang.String description;

     org.kobjects.serialization.PropertyInfo KSOAP_groupid= new org.kobjects.serialization.PropertyInfo("groupid",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_type= new org.kobjects.serialization.PropertyInfo("type",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_description= new org.kobjects.serialization.PropertyInfo("description",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_creationtime= new org.kobjects.serialization.PropertyInfo("creationtime",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_ownerid= new org.kobjects.serialization.PropertyInfo("ownerid",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_name= new org.kobjects.serialization.PropertyInfo("name",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo[] KSOAP_PROP_ARRAY= {KSOAP_groupid,KSOAP_type,KSOAP_description,KSOAP_creationtime,KSOAP_ownerid,KSOAP_name};

     public java.lang.Long getCreationtime() {
          return creationtime;
     }

     public void setCreationtime(java.lang.Long creationtime) {
          this.creationtime = creationtime;
     }

     public java.lang.String getDescription() {
          return description;
     }

     public void setDescription(java.lang.String description) {
          this.description = description;
     }

     public java.lang.Long getGroupid() {
          return groupid;
     }

     public void setGroupid(java.lang.Long groupid) {
          this.groupid = groupid;
     }

     public java.lang.String getName() {
          return name;
     }

     public void setName(java.lang.String name) {
          this.name = name;
     }

     public java.lang.Long getOwnerid() {
          return ownerid;
     }

     public void setOwnerid(java.lang.Long ownerid) {
          this.ownerid = ownerid;
     }

     public java.lang.String getType() {
          return type;
     }

     public void setType(java.lang.String type) {
          this.type = type;
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
groupid = (java.lang.Long) obj;
}
 else if (param == 1) {
type = (String) obj;
}
 else if (param == 2) {
description = (String) obj;
}
 else if (param == 3) {
creationtime = (java.lang.Long) obj;
}
 else if (param == 4) {
ownerid = (java.lang.Long) obj;
}
 else if (param == 5) {
name = (String) obj;
}
     }

     public java.lang.Object getProperty(int param) {
          if (param == 0 ) {
return groupid;
}
 else if (param == 1) {
return type;
}
 else if (param == 2) {
return description;
}
 else if (param == 3) {
return creationtime;
}
 else if (param == 4) {
return ownerid;
}
 else if (param == 5) {
return name;
}
 else { 
 return null; 
 }
     }

} // end KVMGroupData
