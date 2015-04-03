package dataObjects.socialStateLearning;
import java.sql.Timestamp;
import core.data.dto.CoreDto;

public class facebook_user extends CoreDto{

	
	/**
	 * 
	 */
	public facebook_user() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Long gid;			//facebook_group	
	public Integer fid;			//facebook Friends			
	public Integer uid;			
	public String about_me;
	public String activities;
	//public String affiliations;
	/*
	 * Notes on each of the children: o type takes the following values: + 1:
	 * college network + 2: high school network + 3: work network + 4: geography
	 * network o year may be blank, depending on the network type o name is the
	 * name of the network o key is a unique identifier for the network. The
	 * user-to-network relation may be stored.
	 */
	public String birthday;
	public String books;
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
	public String first_name;
	public String has_added_app;
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
	public String interests;
	public String is_app_user;
	public String last_name;
	public String meeting_for;
	public String meeting_sex;
	public String movies;
	public String music;
	public String name;
	public String notes_count;
	public String pic;
	public String pic_big;
	public String pic_small;
	public String pic_square;
	public String political;
	public String profile_update_time;
	public String quotes;
	public String relationship_status;
	public String religion;
	public String significant_other_id;
	public String sex;
	public String status;
	public String timezone;
	public String tv;
	public String wall_count;
	public String work_history;
	public Timestamp lastupdated;
}
