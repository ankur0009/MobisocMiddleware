package ksoapDataObjects;
/*
 * Class:   User
 *
 */
public class KVMUser implements org.kobjects.serialization.KvmSerializable {
     org.kobjects.serialization.PropertyInfo KSOAP_work_history= new org.kobjects.serialization.PropertyInfo("work_history",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_has_added_app= new org.kobjects.serialization.PropertyInfo("has_added_app",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_pic= new org.kobjects.serialization.PropertyInfo("pic",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_significant_other_id= new org.kobjects.serialization.PropertyInfo("significant_other_id",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_books= new org.kobjects.serialization.PropertyInfo("books",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_profile_update_time= new org.kobjects.serialization.PropertyInfo("profile_update_time",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_wall_count= new org.kobjects.serialization.PropertyInfo("wall_count",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_about_me= new org.kobjects.serialization.PropertyInfo("about_me",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_sex= new org.kobjects.serialization.PropertyInfo("sex",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_activities= new org.kobjects.serialization.PropertyInfo("activities",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_music= new org.kobjects.serialization.PropertyInfo("music",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_pic_small= new org.kobjects.serialization.PropertyInfo("pic_small",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_meeting_sex= new org.kobjects.serialization.PropertyInfo("meeting_sex",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_lastupdated= new org.kobjects.serialization.PropertyInfo("lastupdated",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_name= new org.kobjects.serialization.PropertyInfo("name",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_first_name= new org.kobjects.serialization.PropertyInfo("first_name",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_birthday= new org.kobjects.serialization.PropertyInfo("birthday",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_relationship_status= new org.kobjects.serialization.PropertyInfo("relationship_status",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_meeting_for= new org.kobjects.serialization.PropertyInfo("meeting_for",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_religion= new org.kobjects.serialization.PropertyInfo("religion",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_is_app_user= new org.kobjects.serialization.PropertyInfo("is_app_user",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_uid= new org.kobjects.serialization.PropertyInfo("uid",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_interests= new org.kobjects.serialization.PropertyInfo("interests",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_status= new org.kobjects.serialization.PropertyInfo("status",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_last_name= new org.kobjects.serialization.PropertyInfo("last_name",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_political= new org.kobjects.serialization.PropertyInfo("political",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_movies= new org.kobjects.serialization.PropertyInfo("movies",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_pic_big= new org.kobjects.serialization.PropertyInfo("pic_big",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_tv= new org.kobjects.serialization.PropertyInfo("tv",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_pic_square= new org.kobjects.serialization.PropertyInfo("pic_square",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_timezone= new org.kobjects.serialization.PropertyInfo("timezone",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_quotes= new org.kobjects.serialization.PropertyInfo("quotes",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_gid= new org.kobjects.serialization.PropertyInfo("gid",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_fid= new org.kobjects.serialization.PropertyInfo("fid",org.kobjects.serialization.PropertyInfo.OBJECT_CLASS);

     org.kobjects.serialization.PropertyInfo KSOAP_notes_count= new org.kobjects.serialization.PropertyInfo("notes_count",org.kobjects.serialization.PropertyInfo.STRING_CLASS);

     org.kobjects.serialization.PropertyInfo[] KSOAP_PROP_ARRAY= {KSOAP_work_history,KSOAP_has_added_app,KSOAP_pic,KSOAP_significant_other_id,KSOAP_books,KSOAP_profile_update_time,KSOAP_wall_count,KSOAP_about_me,KSOAP_sex,KSOAP_activities,KSOAP_music,KSOAP_pic_small,KSOAP_meeting_sex,KSOAP_lastupdated,KSOAP_name,KSOAP_first_name,KSOAP_birthday,KSOAP_relationship_status,KSOAP_meeting_for,KSOAP_religion,KSOAP_is_app_user,KSOAP_uid,KSOAP_interests,KSOAP_status,KSOAP_last_name,KSOAP_political,KSOAP_movies,KSOAP_pic_big,KSOAP_tv,KSOAP_pic_square,KSOAP_timezone,KSOAP_quotes,KSOAP_gid,KSOAP_fid,KSOAP_notes_count};

     private java.lang.Long gid;

     private java.lang.Integer fid;

     private java.lang.Integer uid;

     private java.lang.String about_me;

     private java.lang.String activities;

     private java.lang.String birthday;

     private java.lang.String books;

     private java.lang.String first_name;

     private java.lang.String has_added_app;

     private java.lang.String interests;

     private java.lang.String is_app_user;

     private java.lang.String last_name;

     private java.lang.String meeting_for;

     private java.lang.String meeting_sex;

     private java.lang.String movies;

     private java.lang.String music;

     private java.lang.String name;

     private java.lang.String notes_count;

     private java.lang.String pic;

     private java.lang.String pic_big;

     private java.lang.String pic_small;

     private java.lang.String pic_square;

     private java.lang.String political;

     private java.lang.String profile_update_time;

     private java.lang.String quotes;

     private java.lang.String relationship_status;

     private java.lang.String religion;

     private java.lang.String significant_other_id;

     private java.lang.String sex;

     private java.lang.String status;

     private java.lang.String timezone;

     private java.lang.String tv;

     private java.lang.String wall_count;

     private java.lang.String work_history;

     private java.lang.Long lastupdated;

     public java.lang.String getAbout_me() {
          return about_me;
     }

     public void setAbout_me(java.lang.String about_me) {
          this.about_me = about_me;
     }

     public java.lang.String getActivities() {
          return activities;
     }

     public void setActivities(java.lang.String activities) {
          this.activities = activities;
     }

     public java.lang.String getBirthday() {
          return birthday;
     }

     public void setBirthday(java.lang.String birthday) {
          this.birthday = birthday;
     }

     public java.lang.String getBooks() {
          return books;
     }

     public void setBooks(java.lang.String books) {
          this.books = books;
     }

     public java.lang.Integer getFid() {
          return fid;
     }

     public void setFid(java.lang.Integer fid) {
          this.fid = fid;
     }

     public java.lang.String getFirst_name() {
          return first_name;
     }

     public void setFirst_name(java.lang.String first_name) {
          this.first_name = first_name;
     }

     public java.lang.Long getGid() {
          return gid;
     }

     public void setGid(java.lang.Long gid) {
          this.gid = gid;
     }

     public java.lang.String getHas_added_app() {
          return has_added_app;
     }

     public void setHas_added_app(java.lang.String has_added_app) {
          this.has_added_app = has_added_app;
     }

     public java.lang.String getInterests() {
          return interests;
     }

     public void setInterests(java.lang.String interests) {
          this.interests = interests;
     }

     public java.lang.String getIs_app_user() {
          return is_app_user;
     }

     public void setIs_app_user(java.lang.String is_app_user) {
          this.is_app_user = is_app_user;
     }

     public java.lang.String getLast_name() {
          return last_name;
     }

     public void setLast_name(java.lang.String last_name) {
          this.last_name = last_name;
     }

     public java.lang.Long getLastupdated() {
          return lastupdated;
     }

     public void setLastupdated(java.lang.Long lastupdated) {
          this.lastupdated = lastupdated;
     }

     public java.lang.String getMeeting_for() {
          return meeting_for;
     }

     public void setMeeting_for(java.lang.String meeting_for) {
          this.meeting_for = meeting_for;
     }

     public java.lang.String getMeeting_sex() {
          return meeting_sex;
     }

     public void setMeeting_sex(java.lang.String meeting_sex) {
          this.meeting_sex = meeting_sex;
     }

     public java.lang.String getMovies() {
          return movies;
     }

     public void setMovies(java.lang.String movies) {
          this.movies = movies;
     }

     public java.lang.String getMusic() {
          return music;
     }

     public void setMusic(java.lang.String music) {
          this.music = music;
     }

     public java.lang.String getName() {
          return name;
     }

     public void setName(java.lang.String name) {
          this.name = name;
     }

     public java.lang.String getNotes_count() {
          return notes_count;
     }

     public void setNotes_count(java.lang.String notes_count) {
          this.notes_count = notes_count;
     }

     public java.lang.String getPic() {
          return pic;
     }

     public void setPic(java.lang.String pic) {
          this.pic = pic;
     }

     public java.lang.String getPic_big() {
          return pic_big;
     }

     public void setPic_big(java.lang.String pic_big) {
          this.pic_big = pic_big;
     }

     public java.lang.String getPic_small() {
          return pic_small;
     }

     public void setPic_small(java.lang.String pic_small) {
          this.pic_small = pic_small;
     }

     public java.lang.String getPic_square() {
          return pic_square;
     }

     public void setPic_square(java.lang.String pic_square) {
          this.pic_square = pic_square;
     }

     public java.lang.String getPolitical() {
          return political;
     }

     public void setPolitical(java.lang.String political) {
          this.political = political;
     }

     public java.lang.String getProfile_update_time() {
          return profile_update_time;
     }

     public void setProfile_update_time(java.lang.String profile_update_time) {
          this.profile_update_time = profile_update_time;
     }

     public java.lang.String getQuotes() {
          return quotes;
     }

     public void setQuotes(java.lang.String quotes) {
          this.quotes = quotes;
     }

     public java.lang.String getRelationship_status() {
          return relationship_status;
     }

     public void setRelationship_status(java.lang.String relationship_status) {
          this.relationship_status = relationship_status;
     }

     public java.lang.String getReligion() {
          return religion;
     }

     public void setReligion(java.lang.String religion) {
          this.religion = religion;
     }

     public java.lang.String getSex() {
          return sex;
     }

     public void setSex(java.lang.String sex) {
          this.sex = sex;
     }

     public java.lang.String getSignificant_other_id() {
          return significant_other_id;
     }

     public void setSignificant_other_id(java.lang.String significant_other_id) {
          this.significant_other_id = significant_other_id;
     }

     public java.lang.String getStatus() {
          return status;
     }

     public void setStatus(java.lang.String status) {
          this.status = status;
     }

     public java.lang.String getTimezone() {
          return timezone;
     }

     public void setTimezone(java.lang.String timezone) {
          this.timezone = timezone;
     }

     public java.lang.String getTv() {
          return tv;
     }

     public void setTv(java.lang.String tv) {
          this.tv = tv;
     }

     public java.lang.Integer getUid() {
          return uid;
     }

     public void setUid(java.lang.Integer uid) {
          this.uid = uid;
     }

     public java.lang.String getWall_count() {
          return wall_count;
     }

     public void setWall_count(java.lang.String wall_count) {
          this.wall_count = wall_count;
     }

     public java.lang.String getWork_history() {
          return work_history;
     }

     public void setWork_history(java.lang.String work_history) {
          this.work_history = work_history;
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
work_history = (String) obj;
}
 else if (param == 1) {
has_added_app = (String) obj;
}
 else if (param == 2) {
pic = (String) obj;
}
 else if (param == 3) {
significant_other_id = (String) obj;
}
 else if (param == 4) {
books = (String) obj;
}
 else if (param == 5) {
profile_update_time = (String) obj;
}
 else if (param == 6) {
wall_count = (String) obj;
}
 else if (param == 7) {
about_me = (String) obj;
}
 else if (param == 8) {
sex = (String) obj;
}
 else if (param == 9) {
activities = (String) obj;
}
 else if (param == 10) {
music = (String) obj;
}
 else if (param == 11) {
pic_small = (String) obj;
}
 else if (param == 12) {
meeting_sex = (String) obj;
}
 else if (param == 13) {
lastupdated = (java.lang.Long) obj;
}
 else if (param == 14) {
name = (String) obj;
}
 else if (param == 15) {
first_name = (String) obj;
}
 else if (param == 16) {
birthday = (String) obj;
}
 else if (param == 17) {
relationship_status = (String) obj;
}
 else if (param == 18) {
meeting_for = (String) obj;
}
 else if (param == 19) {
religion = (String) obj;
}
 else if (param == 20) {
is_app_user = (String) obj;
}
 else if (param == 21) {
uid = (java.lang.Integer) obj;
}
 else if (param == 22) {
interests = (String) obj;
}
 else if (param == 23) {
status = (String) obj;
}
 else if (param == 24) {
last_name = (String) obj;
}
 else if (param == 25) {
political = (String) obj;
}
 else if (param == 26) {
movies = (String) obj;
}
 else if (param == 27) {
pic_big = (String) obj;
}
 else if (param == 28) {
tv = (String) obj;
}
 else if (param == 29) {
pic_square = (String) obj;
}
 else if (param == 30) {
timezone = (String) obj;
}
 else if (param == 31) {
quotes = (String) obj;
}
 else if (param == 32) {
gid = (java.lang.Long) obj;
}
 else if (param == 33) {
fid = (java.lang.Integer) obj;
}
 else if (param == 34) {
notes_count = (String) obj;
}
     }

     public java.lang.Object getProperty(int param) {
          if (param == 0 ) {
return work_history;
}
 else if (param == 1) {
return has_added_app;
}
 else if (param == 2) {
return pic;
}
 else if (param == 3) {
return significant_other_id;
}
 else if (param == 4) {
return books;
}
 else if (param == 5) {
return profile_update_time;
}
 else if (param == 6) {
return wall_count;
}
 else if (param == 7) {
return about_me;
}
 else if (param == 8) {
return sex;
}
 else if (param == 9) {
return activities;
}
 else if (param == 10) {
return music;
}
 else if (param == 11) {
return pic_small;
}
 else if (param == 12) {
return meeting_sex;
}
 else if (param == 13) {
return lastupdated;
}
 else if (param == 14) {
return name;
}
 else if (param == 15) {
return first_name;
}
 else if (param == 16) {
return birthday;
}
 else if (param == 17) {
return relationship_status;
}
 else if (param == 18) {
return meeting_for;
}
 else if (param == 19) {
return religion;
}
 else if (param == 20) {
return is_app_user;
}
 else if (param == 21) {
return uid;
}
 else if (param == 22) {
return interests;
}
 else if (param == 23) {
return status;
}
 else if (param == 24) {
return last_name;
}
 else if (param == 25) {
return political;
}
 else if (param == 26) {
return movies;
}
 else if (param == 27) {
return pic_big;
}
 else if (param == 28) {
return tv;
}
 else if (param == 29) {
return pic_square;
}
 else if (param == 30) {
return timezone;
}
 else if (param == 31) {
return quotes;
}
 else if (param == 32) {
return gid;
}
 else if (param == 33) {
return fid;
}
 else if (param == 34) {
return notes_count;
}
 else { 
 return null; 
 }
     }

} // end User
