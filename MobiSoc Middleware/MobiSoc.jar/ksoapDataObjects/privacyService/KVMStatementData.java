package ksoapDataObjects.privacyService;
/*
 * Class:   KVMStatementData
 *
 */


/**
 * StatementData class
 * NOTE: This class holds row of each table (present for access control module) corresponding to an access_control_statement_id.
 */
public class KVMStatementData implements  org.kobjects.serialization.KvmSerializable {
     public KVMAccessControlMgrRow objKVM_ACM_Row;

     public KVMAccessRestrictionsRow objKVM_AR_Row;

     public KVMActionToTakeRow objKVM_ATT_Row;

     public KVMHideInformationRow objKVM_HI_Row;

     public KVMAccessControlMgrRow getObjKVM_ACM_Row() {
 		return objKVM_ACM_Row;
 	}

 	public void setObjKVM_ACM_Row(KVMAccessControlMgrRow objKVM_ACM_Row) {
 		this.objKVM_ACM_Row = objKVM_ACM_Row;
 	}

 	public KVMAccessRestrictionsRow getObjKVM_AR_Row() {
 		return objKVM_AR_Row;
 	}

 	public void setObjKVM_AR_Row(KVMAccessRestrictionsRow objKVM_AR_Row) {
 		this.objKVM_AR_Row = objKVM_AR_Row;
 	}

 	public KVMActionToTakeRow getObjKVM_ATT_Row() {
 		return objKVM_ATT_Row;
 	}

 	public void setObjKVM_ATT_Row(KVMActionToTakeRow objKVM_ATT_Row) {
 		this.objKVM_ATT_Row = objKVM_ATT_Row;
 	}

 	public KVMHideInformationRow getObjKVM_HI_Row() {
 		return objKVM_HI_Row;
 	}

 	public void setObjKVM_HI_Row(KVMHideInformationRow objKVM_HI_Row) {
 		this.objKVM_HI_Row = objKVM_HI_Row;
 	}

 	
     org.kobjects.serialization.PropertyInfo KSOAP_objKVM_ACM_Row= new org.kobjects.serialization.PropertyInfo("objKVM_ACM_Row",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_objKVM_AR_Row= new org.kobjects.serialization.PropertyInfo("objKVM_AR_Row",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_objKVM_HI_Row= new org.kobjects.serialization.PropertyInfo("objKVM_HI_Row",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_objKVM_ATT_Row= new org.kobjects.serialization.PropertyInfo("objKVM_ATT_Row",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo[] KSOAP_PROP_ARRAY= {KSOAP_objKVM_ACM_Row,KSOAP_objKVM_AR_Row,KSOAP_objKVM_HI_Row,KSOAP_objKVM_ATT_Row};

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
objKVM_ACM_Row = (KVMAccessControlMgrRow) obj;
}
 else if (param == 1) {
objKVM_AR_Row = (KVMAccessRestrictionsRow) obj;
}
 else if (param == 2) {
objKVM_HI_Row = (KVMHideInformationRow) obj;
}
 else if (param == 3) {
objKVM_ATT_Row = (KVMActionToTakeRow) obj;
}
     }

     public java.lang.Object getProperty(int param) {
          if (param == 0 ) {
return objKVM_ACM_Row;
}
 else if (param == 1) {
return objKVM_AR_Row;
}
 else if (param == 2) {
return objKVM_HI_Row;
}
 else if (param == 3) {
return objKVM_ATT_Row;
}
 else { 
 return null; 
 }
     }

	
} // end KVMStatementData
