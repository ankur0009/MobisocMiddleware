package ksoapDataObjects.common;
/*
 * Class:   SocialGroup
 *
 */

public class KVMSocialGroup implements  org.kobjects.serialization.KvmSerializable {
     org.kobjects.serialization.PropertyInfo KSOAP_recent_news= new org.kobjects.serialization.PropertyInfo("recent_news",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_update_time= new org.kobjects.serialization.PropertyInfo("update_time",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_owner= new org.kobjects.serialization.PropertyInfo("owner",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_description= new org.kobjects.serialization.PropertyInfo("description",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_gid= new org.kobjects.serialization.PropertyInfo("gid",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_group_type= new org.kobjects.serialization.PropertyInfo("group_type",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_location= new org.kobjects.serialization.PropertyInfo("location",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_name= new org.kobjects.serialization.PropertyInfo("name",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_website= new org.kobjects.serialization.PropertyInfo("website",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo[] KSOAP_PROP_ARRAY= {KSOAP_recent_news,KSOAP_update_time,KSOAP_owner,KSOAP_description,KSOAP_gid,KSOAP_group_type,KSOAP_location,KSOAP_name,KSOAP_website};

     private java.lang.Long gid;

     private java.lang.String name;

     private java.lang.String description;

     private java.lang.String group_type;

     private java.lang.String recent_news;

     private java.lang.Long owner;

     private java.lang.Long update_time;

     private java.lang.Long location;

     private java.lang.String website;

     public java.lang.String getDescription() {
          return description;
     }

     public void setDescription(java.lang.String description) {
          this.description = description;
     }

     public java.lang.Long getGid() {
          return gid;
     }

     public void setGid(java.lang.Long gid) {
          this.gid = gid;
     }

     public java.lang.String getGroup_type() {
          return group_type;
     }

     public void setGroup_type(java.lang.String group_type) {
          this.group_type = group_type;
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

     public java.lang.String getRecent_news() {
          return recent_news;
     }

     public void setRecent_news(java.lang.String recent_news) {
          this.recent_news = recent_news;
     }

     public java.lang.Long getUpdate_time() {
          return update_time;
     }

     public void setUpdate_time(java.lang.Long update_time) {
          this.update_time = update_time;
     }

     public java.lang.String getWebsite() {
          return website;
     }

     public void setWebsite(java.lang.String website) {
          this.website = website;
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
recent_news = (String) obj;
}
 else if (param == 1) {
update_time = (java.lang.Long) obj;
}
 else if (param == 2) {
owner = (java.lang.Long) obj;
}
 else if (param == 3) {
description = (String) obj;
}
 else if (param == 4) {
gid = (java.lang.Long) obj;
}
 else if (param == 5) {
group_type = (String) obj;
}
 else if (param == 6) {
location = (java.lang.Long) obj;
}
 else if (param == 7) {
name = (String) obj;
}
 else if (param == 8) {
website = (String) obj;
}
     }

     public java.lang.Object getProperty(int param) {
          if (param == 0 ) {
return recent_news;
}
 else if (param == 1) {
return update_time;
}
 else if (param == 2) {
return owner;
}
 else if (param == 3) {
return description;
}
 else if (param == 4) {
return gid;
}
 else if (param == 5) {
return group_type;
}
 else if (param == 6) {
return location;
}
 else if (param == 7) {
return name;
}
 else if (param == 8) {
return website;
}
 else { 
 return null; 
 }
     }

} // end SocialGroup
