package ksoapDataObjects.privacyService;

/*
 * Class:   KVMReturnAccessInfo
 *
 */
/**
 * KVMReturnAccessInfo class
 * NOTE: Class generated through KVM tool. This class is corresponding to ReturnAccessInfo.
 * @author NeerajR.
 *
 */

public class KVMReturnAccessInfo implements org.kobjects.serialization.KvmSerializable {
	 
	 public java.lang.Boolean IsAccessAllowed;

     public java.lang.Boolean ShouldPrimaryReceiveMsg;

     public java.lang.Boolean ShouldSecondaryReceiveMsg;

     public java.lang.Boolean ShouldSystemMsgGetLogged;

     public java.lang.String MessageToPrimary;

     public java.lang.String MessageToSecondary;

     public java.lang.String MessageSystemShouldLog;

     org.kobjects.serialization.PropertyInfo KSOAP_MessageToSecondary= new org.kobjects.serialization.PropertyInfo("MessageToSecondary",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_ShouldSecondaryReceiveMsg= new org.kobjects.serialization.PropertyInfo("ShouldSecondaryReceiveMsg",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_ShouldSystemMsgGetLogged= new org.kobjects.serialization.PropertyInfo("ShouldSystemMsgGetLogged",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_MessageSystemShouldLog= new org.kobjects.serialization.PropertyInfo("MessageSystemShouldLog",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_MessageToPrimary= new org.kobjects.serialization.PropertyInfo("MessageToPrimary",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_IsAccessAllowed= new org.kobjects.serialization.PropertyInfo("IsAccessAllowed",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_ShouldPrimaryReceiveMsg= new org.kobjects.serialization.PropertyInfo("ShouldPrimaryReceiveMsg",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo[] KSOAP_PROP_ARRAY= {KSOAP_MessageToSecondary,KSOAP_ShouldSecondaryReceiveMsg,KSOAP_ShouldSystemMsgGetLogged,KSOAP_MessageSystemShouldLog,KSOAP_MessageToPrimary,KSOAP_IsAccessAllowed,KSOAP_ShouldPrimaryReceiveMsg};

     public java.lang.Boolean getIsAccessAllowed() {
          return IsAccessAllowed;
     }

     public void setIsAccessAllowed(java.lang.Boolean isAccessAllowed) {
          this.IsAccessAllowed = isAccessAllowed;
     }

     public java.lang.String getMessageSystemShouldLog() {
          return MessageSystemShouldLog;
     }

     public void setMessageSystemShouldLog(java.lang.String messageSystemShouldLog) {
          this.MessageSystemShouldLog = messageSystemShouldLog;
     }

     public java.lang.String getMessageToPrimary() {
          return MessageToPrimary;
     }

     public void setMessageToPrimary(java.lang.String messageToPrimary) {
          this.MessageToPrimary = messageToPrimary;
     }

     public java.lang.String getMessageToSecondary() {
          return MessageToSecondary;
     }

     public void setMessageToSecondary(java.lang.String messageToSecondary) {
          this.MessageToSecondary = messageToSecondary;
     }

     public java.lang.Boolean getShouldPrimaryReceiveMsg() {
          return ShouldPrimaryReceiveMsg;
     }

     public void setShouldPrimaryReceiveMsg(java.lang.Boolean shouldPrimaryReceiveMsg) {
          this.ShouldPrimaryReceiveMsg = shouldPrimaryReceiveMsg;
     }

     public java.lang.Boolean getShouldSecondaryReceiveMsg() {
          return ShouldSecondaryReceiveMsg;
     }

     public void setShouldSecondaryReceiveMsg(java.lang.Boolean shouldSecondaryReceiveMsg) {
          this.ShouldSecondaryReceiveMsg = shouldSecondaryReceiveMsg;
     }

     public java.lang.Boolean getShouldSystemMsgGetLogged() {
          return ShouldSystemMsgGetLogged;
     }

     public void setShouldSystemMsgGetLogged(java.lang.Boolean shouldSystemMsgGetLogged) {
          this.ShouldSystemMsgGetLogged = shouldSystemMsgGetLogged;
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
MessageToSecondary = (String) obj;
}
 else if (param == 1) {
ShouldSecondaryReceiveMsg = (java.lang.Boolean) obj;
}
 else if (param == 2) {
ShouldSystemMsgGetLogged = (java.lang.Boolean) obj;
}
 else if (param == 3) {
MessageSystemShouldLog = (String) obj;
}
 else if (param == 4) {
MessageToPrimary = (String) obj;
}
 else if (param == 5) {
IsAccessAllowed = (java.lang.Boolean) obj;
}
 else if (param == 6) {
ShouldPrimaryReceiveMsg = (java.lang.Boolean) obj;
}
     }

     public java.lang.Object getProperty(int param) {
          if (param == 0 ) {
return MessageToSecondary;
}
 else if (param == 1) {
return ShouldSecondaryReceiveMsg;
}
 else if (param == 2) {
return ShouldSystemMsgGetLogged;
}
 else if (param == 3) {
return MessageSystemShouldLog;
}
 else if (param == 4) {
return MessageToPrimary;
}
 else if (param == 5) {
return IsAccessAllowed;
}
 else if (param == 6) {
return ShouldPrimaryReceiveMsg;
}
 else { 
 return null; 
 }
     }

} // end KVMReturnAccessInfo
