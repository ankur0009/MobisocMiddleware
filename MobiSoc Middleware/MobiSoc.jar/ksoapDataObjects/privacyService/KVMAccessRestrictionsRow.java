package ksoapDataObjects.privacyService;

/*
 * Class:   KVMAccessRestrictionsRow
 *
 */
/**
 * KVMAccessRestrictionsRow class
 * NOTE: Class generated through KVM tool. This class is corresponding to AccessRestrictionsRow.
 * @author NeerajR.
 *
 */

public class KVMAccessRestrictionsRow implements org.kobjects.serialization.KvmSerializable {
	 
     public java.lang.Long ar_id;

     public java.lang.Boolean ar_loc_based;

     public java.lang.String ar_loc_longitude;

     public java.lang.String ar_loc_latitute;

     public java.lang.String ar_loc_height;

     public java.lang.Boolean ar_time_based;

     public java.lang.Long ar_time_start_time;

     public java.lang.Long ar_time_end_time;

     public java.lang.Long ar_time_date;

     public java.lang.Long ar_time_date_of_the_month;

     public java.lang.Long ar_time_month_of_the_year;

     public java.lang.String ar_time_frequency;

     public java.lang.Long ar_time_day_of_the_week;

     public java.lang.Boolean ar_co_loc_based;

     public java.lang.Long ar_co_loc_user_id;

     public java.lang.Long ar_co_loc_distance;

     org.kobjects.serialization.PropertyInfo KSOAP_ar_time_month_of_the_year= new org.kobjects.serialization.PropertyInfo("ar_time_month_of_the_year",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_ar_time_date= new org.kobjects.serialization.PropertyInfo("ar_time_date",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_ar_id= new org.kobjects.serialization.PropertyInfo("ar_id",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_ar_loc_longitude= new org.kobjects.serialization.PropertyInfo("ar_loc_longitude",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_ar_loc_height= new org.kobjects.serialization.PropertyInfo("ar_loc_height",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_ar_time_date_of_the_month= new org.kobjects.serialization.PropertyInfo("ar_time_date_of_the_month",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_ar_time_frequency= new org.kobjects.serialization.PropertyInfo("ar_time_frequency",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_ar_time_start_time= new org.kobjects.serialization.PropertyInfo("ar_time_start_time",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_ar_co_loc_distance= new org.kobjects.serialization.PropertyInfo("ar_co_loc_distance",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_ar_co_loc_based= new org.kobjects.serialization.PropertyInfo("ar_co_loc_based",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_ar_co_loc_user_id= new org.kobjects.serialization.PropertyInfo("ar_co_loc_user_id",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_ar_loc_latitute= new org.kobjects.serialization.PropertyInfo("ar_loc_latitute",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_ar_time_based= new org.kobjects.serialization.PropertyInfo("ar_time_based",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_ar_time_day_of_the_week= new org.kobjects.serialization.PropertyInfo("ar_time_day_of_the_week",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_ar_loc_based= new org.kobjects.serialization.PropertyInfo("ar_loc_based",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_ar_time_end_time= new org.kobjects.serialization.PropertyInfo("ar_time_end_time",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo[] KSOAP_PROP_ARRAY= {KSOAP_ar_time_month_of_the_year,KSOAP_ar_time_date,KSOAP_ar_id,KSOAP_ar_loc_longitude,KSOAP_ar_loc_height,KSOAP_ar_time_date_of_the_month,KSOAP_ar_time_frequency,KSOAP_ar_time_start_time,KSOAP_ar_co_loc_distance,KSOAP_ar_co_loc_based,KSOAP_ar_co_loc_user_id,KSOAP_ar_loc_latitute,KSOAP_ar_time_based,KSOAP_ar_time_day_of_the_week,KSOAP_ar_loc_based,KSOAP_ar_time_end_time};

     public java.lang.Boolean getAr_co_loc_based() {
          return ar_co_loc_based;
     }

     public void setAr_co_loc_based(java.lang.Boolean ar_co_loc_based) {
          this.ar_co_loc_based = ar_co_loc_based;
     }

     public java.lang.Long getAr_co_loc_distance() {
          return ar_co_loc_distance;
     }

     public void setAr_co_loc_distance(java.lang.Long ar_co_loc_distance) {
          this.ar_co_loc_distance = ar_co_loc_distance;
     }

     public java.lang.Long getAr_co_loc_user_id() {
          return ar_co_loc_user_id;
     }

     public void setAr_co_loc_user_id(java.lang.Long ar_co_loc_user_id) {
          this.ar_co_loc_user_id = ar_co_loc_user_id;
     }

     public java.lang.Long getAr_id() {
          return ar_id;
     }

     public void setAr_id(java.lang.Long ar_id) {
          this.ar_id = ar_id;
     }

     public java.lang.Boolean getAr_loc_based() {
          return ar_loc_based;
     }

     public void setAr_loc_based(java.lang.Boolean ar_loc_based) {
          this.ar_loc_based = ar_loc_based;
     }

     public java.lang.String getAr_loc_height() {
          return ar_loc_height;
     }

     public void setAr_loc_height(java.lang.String ar_loc_height) {
          this.ar_loc_height = ar_loc_height;
     }

     public java.lang.String getAr_loc_latitute() {
          return ar_loc_latitute;
     }

     public void setAr_loc_latitute(java.lang.String ar_loc_latitute) {
          this.ar_loc_latitute = ar_loc_latitute;
     }

     public java.lang.String getAr_loc_longitude() {
          return ar_loc_longitude;
     }

     public void setAr_loc_longitude(java.lang.String ar_loc_longitude) {
          this.ar_loc_longitude = ar_loc_longitude;
     }

     public java.lang.Boolean getAr_time_based() {
          return ar_time_based;
     }

     public void setAr_time_based(java.lang.Boolean ar_time_based) {
          this.ar_time_based = ar_time_based;
     }

     public java.lang.Long getAr_time_date() {
          return ar_time_date;
     }

     public void setAr_time_date(java.lang.Long ar_time_date) {
          this.ar_time_date = ar_time_date;
     }

     public java.lang.Long getAr_time_date_of_the_month() {
          return ar_time_date_of_the_month;
     }

     public void setAr_time_date_of_the_month(java.lang.Long ar_time_date_of_the_month) {
          this.ar_time_date_of_the_month = ar_time_date_of_the_month;
     }

     public java.lang.Long getAr_time_day_of_the_week() {
          return ar_time_day_of_the_week;
     }

     public void setAr_time_day_of_the_week(java.lang.Long ar_time_day_of_the_week) {
          this.ar_time_day_of_the_week = ar_time_day_of_the_week;
     }

     public java.lang.Long getAr_time_end_time() {
          return ar_time_end_time;
     }

     public void setAr_time_end_time(java.lang.Long ar_time_end_time) {
          this.ar_time_end_time = ar_time_end_time;
     }

     public java.lang.String getAr_time_frequency() {
          return ar_time_frequency;
     }

     public void setAr_time_frequency(java.lang.String ar_time_frequency) {
          this.ar_time_frequency = ar_time_frequency;
     }

     public java.lang.Long getAr_time_month_of_the_year() {
          return ar_time_month_of_the_year;
     }

     public void setAr_time_month_of_the_year(java.lang.Long ar_time_month_of_the_year) {
          this.ar_time_month_of_the_year = ar_time_month_of_the_year;
     }

     public java.lang.Long getAr_time_start_time() {
          return ar_time_start_time;
     }

     public void setAr_time_start_time(java.lang.Long ar_time_start_time) {
          this.ar_time_start_time = ar_time_start_time;
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
ar_time_month_of_the_year = (java.lang.Long) obj;
}
 else if (param == 1) {
ar_time_date = (java.lang.Long) obj;
}
 else if (param == 2) {
ar_id = (java.lang.Long) obj;
}
 else if (param == 3) {
ar_loc_longitude = (String) obj;
}
 else if (param == 4) {
ar_loc_height = (String) obj;
}
 else if (param == 5) {
ar_time_date_of_the_month = (java.lang.Long) obj;
}
 else if (param == 6) {
ar_time_frequency = (String) obj;
}
 else if (param == 7) {
ar_time_start_time = (java.lang.Long) obj;
}
 else if (param == 8) {
ar_co_loc_distance = (java.lang.Long) obj;
}
 else if (param == 9) {
ar_co_loc_based = (java.lang.Boolean) obj;
}
 else if (param == 10) {
ar_co_loc_user_id = (java.lang.Long) obj;
}
 else if (param == 11) {
ar_loc_latitute = (String) obj;
}
 else if (param == 12) {
ar_time_based = (java.lang.Boolean) obj;
}
 else if (param == 13) {
ar_time_day_of_the_week = (java.lang.Long) obj;
}
 else if (param == 14) {
ar_loc_based = (java.lang.Boolean) obj;
}
 else if (param == 15) {
ar_time_end_time = (java.lang.Long) obj;
}
     }

     public java.lang.Object getProperty(int param) {
          if (param == 0 ) {
return ar_time_month_of_the_year;
}
 else if (param == 1) {
return ar_time_date;
}
 else if (param == 2) {
return ar_id;
}
 else if (param == 3) {
return ar_loc_longitude;
}
 else if (param == 4) {
return ar_loc_height;
}
 else if (param == 5) {
return ar_time_date_of_the_month;
}
 else if (param == 6) {
return ar_time_frequency;
}
 else if (param == 7) {
return ar_time_start_time;
}
 else if (param == 8) {
return ar_co_loc_distance;
}
 else if (param == 9) {
return ar_co_loc_based;
}
 else if (param == 10) {
return ar_co_loc_user_id;
}
 else if (param == 11) {
return ar_loc_latitute;
}
 else if (param == 12) {
return ar_time_based;
}
 else if (param == 13) {
return ar_time_day_of_the_week;
}
 else if (param == 14) {
return ar_loc_based;
}
 else if (param == 15) {
return ar_time_end_time;
}
 else { 
 return null; 
 }
     }

} // end KVMAccessRestrictionsRow
