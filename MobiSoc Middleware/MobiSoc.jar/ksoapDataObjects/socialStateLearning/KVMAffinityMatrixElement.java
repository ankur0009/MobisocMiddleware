package ksoapDataObjects.socialStateLearning;
/*
 * Class:   AffinityMatrixElement
 *
 */


public class KVMAffinityMatrixElement implements java.io.Serializable, org.kobjects.serialization.KvmSerializable {
     org.kobjects.serialization.PropertyInfo KSOAP_element= new org.kobjects.serialization.PropertyInfo("element",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_score= new org.kobjects.serialization.PropertyInfo("score",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo[] KSOAP_PROP_ARRAY= {KSOAP_element,KSOAP_score};

     private java.lang.String element;

     private java.lang.String score;

     public java.lang.String getElement() {
          return element;
     }

     public void setElement(java.lang.String element) {
          this.element = element;
     }

     public java.lang.String getScore() {
          return score;
     }

     public void setScore(java.lang.String score) {
          this.score = score;
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
element = (String) obj;
}
 else if (param == 1) {
score = (String) obj;
}
     }

     public java.lang.Object getProperty(int param) {
          if (param == 0 ) {
return new String(element);
}
 else if (param == 1) {
return new String(score);
}
 else { 
 return null; 
 }
     }

} // end AffinityMatrixElement
