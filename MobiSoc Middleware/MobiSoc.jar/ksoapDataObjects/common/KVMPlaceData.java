package ksoapDataObjects.common;
/*
 * Class:   PlaceData
 *
 */
import java.util.Vector;

/**
 * thiss class stores basic data pertaining to a place
 */
public class KVMPlaceData implements org.kobjects.serialization.KvmSerializable {
     org.kobjects.serialization.PropertyInfo KSOAP_place_id= new org.kobjects.serialization.PropertyInfo("place_id",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_type= new org.kobjects.serialization.PropertyInfo("type",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_parent_id= new org.kobjects.serialization.PropertyInfo("parent_id",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_location= new org.kobjects.serialization.PropertyInfo("location",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_name= new org.kobjects.serialization.PropertyInfo("name",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo[] KSOAP_PROP_ARRAY= {KSOAP_place_id,KSOAP_type,KSOAP_parent_id,KSOAP_location,KSOAP_name};

     private java.lang.Long place_id;

     private java.lang.String name;

     private java.lang.Long parent_id;

     private java.lang.String type;

     private java.util.Vector location;

     public java.util.Vector getLocation() {
          return location;
     }

     public void setLocation(java.util.Vector location) {
          this.location = location;
     }

     public java.lang.String getName() {
          return name;
     }

     public void setName(java.lang.String name) {
          this.name = name;
     }

     public java.lang.Long getParent_id() {
          return parent_id;
     }

     public void setParent_id(java.lang.Long parent_id) {
          this.parent_id = parent_id;
     }

     public java.lang.Long getPlace_id() {
          return place_id;
     }

     public void setPlace_id(java.lang.Long place_id) {
          this.place_id = place_id;
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
place_id = (java.lang.Long) obj;
}
 else if (param == 1) {
type = (String) obj;
}
 else if (param == 2) {
parent_id = (java.lang.Long) obj;
}
 else if (param == 3) {
location = (java.util.Vector) obj;
}
 else if (param == 4) {
name = (String) obj;
}
     }

     public java.lang.Object getProperty(int param) {
          if (param == 0 ) {
return place_id;
}
 else if (param == 1) {
return type;
}
 else if (param == 2) {
return parent_id;
}
 else if (param == 3) {
return location;
}
 else if (param == 4) {
return name;
}
 else { 
 return null; 
 }
     }

} // end PlaceData
