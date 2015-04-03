package serviceFunctionality.socialStateLearning.Matching.Facebook;

public class FacebookProfileWrapper {

	
	
	/**
	 * 
	 */
	public FacebookProfileWrapper() {
		super();
		// TODO Auto-generated constructor stub
	}
	String uid;
	String about_me;
	String activities;
//	String affiliations;
	/*
	 * Notes on each of the children: o type takes the following values: + 1:
	 * college network + 2: high school network + 3: work network + 4: geography
	 * network o year may be blank, depending on the network type o name is the
	 * name of the network o key is a unique identifier for the network. The
	 * user-to-network relation may be stored.
	 */
	String birthday;
	String books;
//	String current_location;
	/*
	 * Notes on each of the children: o city is user-entered, and may be blank o
	 * state_or_region is a well-defind two-letter American state or Canadian
	 * province abbreviation, and may be blank o country is well-defined, and
	 * may be blank.
	 */
//	String education_history;
	/*
	 * Notes on each of the children: o year is a four-digit year, and may be
	 * blank o name is the name of the school, and is user-specified o key is a
	 * unique identifier for the school network. The user-to-network relation
	 * may be stored. This may be blank also.
	 */
	String first_name;
	String has_added_app;
//  String hometown_location;
	/*
	 * Notes on each of the children: o city is user-entered, and may be blank o
	 * state_or_region is a well-defind two-letter American state or Canadian
	 * province abbreviation, and may be blank o country is well-defined, and
	 * may be blank
	 */
	//String hs_info;
	/*
	 * Notes on each of the children: o hs1_name is well-defined, and may be
	 * left blank o hs2_name is well-defined, and may be left blank, though may
	 * not have information if is blank. o year is a four-digit year, or may be
	 * blank o hs1_key is a unique key representing that school, and is not
	 * blank if and only if hs1_name is not blank. o hs2_key is a unique key
	 * representing that school, and is not blank if and only if hs2_name is not
	 * blank.
	 */
	String interests;
	String is_app_user;
	String last_name;
	String meeting_for;
	String meeting_sex;
	String movies;
	String music;
	String name;
	String notes_count;
	String pic;
	String pic_big;
	String pic_small;
	String pic_square;
	String political;
	String profile_update_time;
	String quotes;
	String relationship_status;
	String religion;
	String significant_other_id;
	String sex;
	String status;
	String timezone;
	String tv;
	String wall_count;
	//String work_history;	
	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	/**
	 * @return the about_me
	 */
	public String getAbout_me() {
		return about_me;
	}
	/**
	 * @param about_me the about_me to set
	 */
	public void setAbout_me(String about_me) {
		this.about_me = about_me;
	}
	/**
	 * @return the activities
	 */
	public String getActivities() {
		return activities;
	}
	/**
	 * @param activities the activities to set
	 */
	public void setActivities(String activities) {
		this.activities = activities;
	}
	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}
	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	/**
	 * @return the books
	 */
	public String getBooks() {
		return books;
	}
	/**
	 * @param books the books to set
	 */
	public void setBooks(String books) {
		this.books = books;
	}
	/**
	 * @return the first_name
	 */
	public String getFirst_name() {
		return first_name;
	}
	/**
	 * @param first_name the first_name to set
	 */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	/**
	 * @return the has_added_app
	 */
	public String getHas_added_app() {
		return has_added_app;
	}
	/**
	 * @param has_added_app the has_added_app to set
	 */
	public void setHas_added_app(String has_added_app) {
		this.has_added_app = has_added_app;
	}
	/**
	 * @return the interests
	 */
	public String getInterests() {
		return interests;
	}
	/**
	 * @param interests the interests to set
	 */
	public void setInterests(String interests) {
		this.interests = interests;
	}
	/**
	 * @return the is_app_user
	 */
	public String getIs_app_user() {
		return is_app_user;
	}
	/**
	 * @param is_app_user the is_app_user to set
	 */
	public void setIs_app_user(String is_app_user) {
		this.is_app_user = is_app_user;
	}
	/**
	 * @return the last_name
	 */
	public String getLast_name() {
		return last_name;
	}
	/**
	 * @param last_name the last_name to set
	 */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	/**
	 * @return the meeting_for
	 */
	public String getMeeting_for() {
		return meeting_for;
	}
	/**
	 * @param meeting_for the meeting_for to set
	 */
	public void setMeeting_for(String meeting_for) {
		this.meeting_for = meeting_for;
	}
	/**
	 * @return the meeting_sex
	 */
	public String getMeeting_sex() {
		return meeting_sex;
	}
	/**
	 * @param meeting_sex the meeting_sex to set
	 */
	public void setMeeting_sex(String meeting_sex) {
		this.meeting_sex = meeting_sex;
	}
	/**
	 * @return the movies
	 */
	public String getMovies() {
		return movies;
	}
	/**
	 * @param movies the movies to set
	 */
	public void setMovies(String movies) {
		this.movies = movies;
	}
	/**
	 * @return the music
	 */
	public String getMusic() {
		return music;
	}
	/**
	 * @param music the music to set
	 */
	public void setMusic(String music) {
		this.music = music;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the notes_count
	 */
	public String getNotes_count() {
		return notes_count;
	}
	/**
	 * @param notes_count the notes_count to set
	 */
	public void setNotes_count(String notes_count) {
		this.notes_count = notes_count;
	}
	/**
	 * @return the pic
	 */
	public String getPic() {
		return pic;
	}
	/**
	 * @param pic the pic to set
	 */
	public void setPic(String pic) {
		this.pic = pic;
	}
	/**
	 * @return the pic_big
	 */
	public String getPic_big() {
		return pic_big;
	}
	/**
	 * @param pic_big the pic_big to set
	 */
	public void setPic_big(String pic_big) {
		this.pic_big = pic_big;
	}
	/**
	 * @return the pic_small
	 */
	public String getPic_small() {
		return pic_small;
	}
	/**
	 * @param pic_small the pic_small to set
	 */
	public void setPic_small(String pic_small) {
		this.pic_small = pic_small;
	}
	/**
	 * @return the pic_square
	 */
	public String getPic_square() {
		return pic_square;
	}
	/**
	 * @param pic_square the pic_square to set
	 */
	public void setPic_square(String pic_square) {
		this.pic_square = pic_square;
	}
	/**
	 * @return the political
	 */
	public String getPolitical() {
		return political;
	}
	/**
	 * @param political the political to set
	 */
	public void setPolitical(String political) {
		this.political = political;
	}
	/**
	 * @return the profile_update_time
	 */
	public String getProfile_update_time() {
		return profile_update_time;
	}
	/**
	 * @param profile_update_time the profile_update_time to set
	 */
	public void setProfile_update_time(String profile_update_time) {
		this.profile_update_time = profile_update_time;
	}
	/**
	 * @return the quotes
	 */
	public String getQuotes() {
		return quotes;
	}
	/**
	 * @param quotes the quotes to set
	 */
	public void setQuotes(String quotes) {
		this.quotes = quotes;
	}
	/**
	 * @return the relationship_status
	 */
	public String getRelationship_status() {
		return relationship_status;
	}
	/**
	 * @param relationship_status the relationship_status to set
	 */
	public void setRelationship_status(String relationship_status) {
		this.relationship_status = relationship_status;
	}
	/**
	 * @return the religion
	 */
	public String getReligion() {
		return religion;
	}
	/**
	 * @param religion the religion to set
	 */
	public void setReligion(String religion) {
		this.religion = religion;
	}
	/**
	 * @return the significant_other_id
	 */
	public String getSignificant_other_id() {
		return significant_other_id;
	}
	/**
	 * @param significant_other_id the significant_other_id to set
	 */
	public void setSignificant_other_id(String significant_other_id) {
		this.significant_other_id = significant_other_id;
	}
	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the timezone
	 */
	public String getTimezone() {
		return timezone;
	}
	/**
	 * @param timezone the timezone to set
	 */
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	/**
	 * @return the tv
	 */
	public String getTv() {
		return tv;
	}
	/**
	 * @param tv the tv to set
	 */
	public void setTv(String tv) {
		this.tv = tv;
	}
	/**
	 * @return the wall_count
	 */
	public String getWall_count() {
		return wall_count;
	}
	/**
	 * @param wall_count the wall_count to set
	 */
	public void setWall_count(String wall_count) {
		this.wall_count = wall_count;
	}
	
	/*
	 * Notes on each of the children: o location is user-entered, and has a
	 * similar format to current_location and hometown_location above o
	 * company_name is user-entered, and does not necessarily correspond to a
	 * Facebook work network o description is user-entered, and may be blank o
	 * position is user-entered, and may be blank o start_date is of the form
	 * YYYY-MM, YYYY, or MM. It may be blank o end_date is of the form YYYY-MM,
	 * YYYY, or MM. It may be blank.
	 */
	
	
	
	}
