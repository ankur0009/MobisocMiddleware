package dataObjects.common;

import java.sql.Timestamp;

public class User {

	private Long gid;			//facebook_group	
	private Integer fid;			//facebook Friends			
	private Integer uid;			
	private String about_me;
	private String activities;
	//public String affiliations;
	/*
	 * Notes on each of the children: o type takes the following values: + 1:
	 * college network + 2: high school network + 3: work network + 4: geography
	 * network o year may be blank, depending on the network type o name is the
	 * name of the network o key is a unique identifier for the network. The
	 * user-to-network relation may be stored.
	 */
	private String birthday;
	private String books;
	//public String current_location;
	/*
	 * Notes on each of the children: o city is user-entered, and may be blank o
	 * state_or_region is a well-defind two-letter American state or Canadian
	 * province abbreviation, and may be blank o country is well-defined, and
	 * may be blank.
	 */
	//public String education_history;
	/*
	 * Notes on each of the children: o year is a four-digit year, and may be
	 * blank o name is the name of the school, and is user-specified o key is a
	 * unique identifier for the school network. The user-to-network relation
	 * may be stored. This may be blank also.
	 */
	private String first_name;
	private String has_added_app;
	//public String hometown_location;
	/*
	 * Notes on each of the children: o city is user-entered, and may be blank o
	 * state_or_region is a well-defind two-letter American state or Canadian
	 * province abbreviation, and may be blank o country is well-defined, and
	 * may be blank
	 */
	//public String hs_info;
	/*
	 * Notes on each of the children: o hs1_name is well-defined, and may be
	 * left blank o hs2_name is well-defined, and may be left blank, though may
	 * not have information if is blank. o year is a four-digit year, or may be
	 * blank o hs1_key is a unique key representing that school, and is not
	 * blank if and only if hs1_name is not blank. o hs2_key is a unique key
	 * representing that school, and is not blank if and only if hs2_name is not
	 * blank.
	 */
	private String interests;
	private String is_app_user;
	private String last_name;
	private String meeting_for;
	private String meeting_sex;
	private String movies;
	private String music;
	private String name;
	private String notes_count;
	private String pic;
	private String pic_big;
	private String pic_small;
	private String pic_square;
	private String political;
	private String profile_update_time;
	private String quotes;
	private String relationship_status;
	private String religion;
	private String significant_other_id;
	private String sex;
	private String status;
	private String timezone;
	private String tv;
	private String wall_count;
	private String work_history;
	private Timestamp lastupdated;
	
	//constructor for setting default values  
	public User()
	{
		gid = new Long(-1);			//facebook_group	
		fid = new Integer(-1);			//facebook Friends			
		uid = new Integer(-1);;			
		about_me = "";
		activities = "";
		birthday = "";
		books = "";
		first_name = "";
		has_added_app = "";
		interests = "";
		is_app_user = "";
		last_name = "";
		meeting_for = "";
		meeting_sex = "";
		movies = "";
		music = "";
		name = "";
		notes_count = "";
		pic = "";
		pic_big = "";
		pic_small = "";
		pic_square = "";
		political = "";
		profile_update_time = "";
		quotes = "";
		relationship_status = "";
		religion = "";
		significant_other_id = "";
		sex = "";
		status = "";
		timezone = "";
		tv = "";
		wall_count = "";
		work_history = "";
		lastupdated = new Timestamp(0);
	}
	
	
	public String getAbout_me() {
		return about_me;
	}
	public void setAbout_me(String about_me) {
		this.about_me = about_me;
	}
	public String getActivities() {
		return activities;
	}
	public void setActivities(String activities) {
		this.activities = activities;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getBooks() {
		return books;
	}
	public void setBooks(String books) {
		this.books = books;
	}
	public Integer getFid() {
		return fid;
	}
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public Long getGid() {
		return gid;
	}
	public void setGid(Long gid) {
		this.gid = gid;
	}
	public String getHas_added_app() {
		return has_added_app;
	}
	public void setHas_added_app(String has_added_app) {
		this.has_added_app = has_added_app;
	}
	public String getInterests() {
		return interests;
	}
	public void setInterests(String interests) {
		this.interests = interests;
	}
	public String getIs_app_user() {
		return is_app_user;
	}
	public void setIs_app_user(String is_app_user) {
		this.is_app_user = is_app_user;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public Timestamp getLastupdated() {
		return lastupdated;
	}
	public void setLastupdated(Timestamp lastupdated) {
		this.lastupdated = lastupdated;
	}
	public String getMeeting_for() {
		return meeting_for;
	}
	public void setMeeting_for(String meeting_for) {
		this.meeting_for = meeting_for;
	}
	public String getMeeting_sex() {
		return meeting_sex;
	}
	public void setMeeting_sex(String meeting_sex) {
		this.meeting_sex = meeting_sex;
	}
	public String getMovies() {
		return movies;
	}
	public void setMovies(String movies) {
		this.movies = movies;
	}
	public String getMusic() {
		return music;
	}
	public void setMusic(String music) {
		this.music = music;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNotes_count() {
		return notes_count;
	}
	public void setNotes_count(String notes_count) {
		this.notes_count = notes_count;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getPic_big() {
		return pic_big;
	}
	public void setPic_big(String pic_big) {
		this.pic_big = pic_big;
	}
	public String getPic_small() {
		return pic_small;
	}
	public void setPic_small(String pic_small) {
		this.pic_small = pic_small;
	}
	public String getPic_square() {
		return pic_square;
	}
	public void setPic_square(String pic_square) {
		this.pic_square = pic_square;
	}
	public String getPolitical() {
		return political;
	}
	public void setPolitical(String political) {
		this.political = political;
	}
	public String getProfile_update_time() {
		return profile_update_time;
	}
	public void setProfile_update_time(String profile_update_time) {
		this.profile_update_time = profile_update_time;
	}
	public String getQuotes() {
		return quotes;
	}
	public void setQuotes(String quotes) {
		this.quotes = quotes;
	}
	public String getRelationship_status() {
		return relationship_status;
	}
	public void setRelationship_status(String relationship_status) {
		this.relationship_status = relationship_status;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getSignificant_other_id() {
		return significant_other_id;
	}
	public void setSignificant_other_id(String significant_other_id) {
		this.significant_other_id = significant_other_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTimezone() {
		return timezone;
	}
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	public String getTv() {
		return tv;
	}
	public void setTv(String tv) {
		this.tv = tv;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getWall_count() {
		return wall_count;
	}
	public void setWall_count(String wall_count) {
		this.wall_count = wall_count;
	}
	public String getWork_history() {
		return work_history;
	}
	public void setWork_history(String work_history) {
		this.work_history = work_history;
	}
	
	
	
}
