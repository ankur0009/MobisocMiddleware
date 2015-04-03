package ksoapDataObjects.common;
/*
 * Class:   KVMLocationData
 *
 */
import java.util.Vector;

public class KVMLocationData implements org.kobjects.serialization.KvmSerializable {

	org.kobjects.serialization.PropertyInfo KSOAP_accessPointInfo= new org.kobjects.serialization.PropertyInfo("accessPointInfo",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_cnHt= new org.kobjects.serialization.PropertyInfo("cnHt",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_cnLon= new org.kobjects.serialization.PropertyInfo("cnLon",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_fpFl= new org.kobjects.serialization.PropertyInfo("fpFl",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_fpLat= new org.kobjects.serialization.PropertyInfo("fpLat",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_userId= new org.kobjects.serialization.PropertyInfo("userId",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_cnLat= new org.kobjects.serialization.PropertyInfo("cnLat",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_fpLon= new org.kobjects.serialization.PropertyInfo("fpLon",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_currentTime= new org.kobjects.serialization.PropertyInfo("currentTime",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_cnFl= new org.kobjects.serialization.PropertyInfo("cnFl",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_fpHt= new org.kobjects.serialization.PropertyInfo("fpHt",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo[] KSOAP_PROP_ARRAY= {KSOAP_accessPointInfo,KSOAP_cnHt,KSOAP_cnLon,KSOAP_fpFl,KSOAP_fpLat,KSOAP_userId,KSOAP_cnLat,KSOAP_fpLon,KSOAP_currentTime,KSOAP_cnFl,KSOAP_fpHt};

     private java.lang.String fpLat;

     private java.lang.String fpLon;

     private java.lang.String fpHt;

     private java.lang.Integer fpFl;

     private java.lang.String cnLat;

     private java.lang.String cnLon;

     private java.lang.String cnHt;

     private java.lang.Integer cnFl;

     private java.lang.Long userId;

     private java.lang.Long currentTime;

     private java.util.Vector accessPointInfo;

     public java.util.Vector getAccessPointInfo() {
          return accessPointInfo;
     }

     public void setAccessPointInfo(java.util.Vector accessPointInfo) {
          this.accessPointInfo = accessPointInfo;
     }

     public java.lang.Integer getCnFl() {
          return cnFl;
     }

     public void setCnFl(java.lang.Integer cnFl) {
          this.cnFl = cnFl;
     }

     public java.lang.String getCnHt() {
          return cnHt;
     }

     public void setCnHt(java.lang.String cnHt) {
          this.cnHt = cnHt;
     }

     public java.lang.String getCnLat() {
          return cnLat;
     }

     public void setCnLat(java.lang.String cnLat) {
          this.cnLat = cnLat;
     }

     public java.lang.String getCnLon() {
          return cnLon;
     }

     public void setCnLon(java.lang.String cnLon) {
          this.cnLon = cnLon;
     }

     public java.lang.Long getCurrentTime() {
          return currentTime;
     }

     public void setCurrentTime(java.lang.Long currentTime) {
          this.currentTime = currentTime;
     }

     public java.lang.Integer getFpFl() {
          return fpFl;
     }

     public void setFpFl(java.lang.Integer fpFl) {
          this.fpFl = fpFl;
     }

     public java.lang.String getFpHt() {
          return fpHt;
     }

     public void setFpHt(java.lang.String fpHt) {
          this.fpHt = fpHt;
     }

     public java.lang.String getFpLat() {
          return fpLat;
     }

     public void setFpLat(java.lang.String fpLat) {
          this.fpLat = fpLat;
     }

     public java.lang.String getFpLon() {
          return fpLon;
     }

     public void setFpLon(java.lang.String fpLon) {
          this.fpLon = fpLon;
     }

     public java.lang.Long getUserId() {
          return userId;
     }

     public void setUserId(java.lang.Long userId) {
          this.userId = userId;
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
accessPointInfo = (java.util.Vector) obj;
}
 else if (param == 1) {
cnHt = (String) obj;
}
 else if (param == 2) {
cnLon = (String) obj;
}
 else if (param == 3) {
fpFl = (java.lang.Integer) obj;
}
 else if (param == 4) {
fpLat = (String) obj;
}
 else if (param == 5) {
userId = (java.lang.Long) obj;
}
 else if (param == 6) {
cnLat = (String) obj;
}
 else if (param == 7) {
fpLon = (String) obj;
}
 else if (param == 8) {
currentTime = (java.lang.Long) obj;
}
 else if (param == 9) {
cnFl = (java.lang.Integer) obj;
}
 else if (param == 10) {
fpHt = (String) obj;
}
     }

     public java.lang.Object getProperty(int param) {
          if (param == 0 ) {
return accessPointInfo;
}
 else if (param == 1) {
return cnHt;
}
 else if (param == 2) {
return cnLon;
}
 else if (param == 3) {
return fpFl;
}
 else if (param == 4) {
return fpLat;
}
 else if (param == 5) {
return userId;
}
 else if (param == 6) {
return cnLat;
}
 else if (param == 7) {
return fpLon;
}
 else if (param == 8) {
return currentTime;
}
 else if (param == 9) {
return cnFl;
}
 else if (param == 10) {
return fpHt;
}
 else { 
 return null; 
 }
     }

} // end KVMLocationData
