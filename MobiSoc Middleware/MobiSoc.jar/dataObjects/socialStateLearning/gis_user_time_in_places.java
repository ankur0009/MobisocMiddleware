package dataObjects.socialStateLearning;
import core.data.dto.CoreDto;
import java.sql.Timestamp;


public class gis_user_time_in_places extends CoreDto {

	private static final long serialVersionUID = 1L;
	/* Define *public*  members with names matching columns in locationinfo_userPlaces table */
	public long userid;
	public long placeid;
	public Timestamp starttime;
	public Timestamp endtime;
	public long group_user_id;
	public long visit_number;
	
	public gis_user_time_in_places() {
		super();
	}

	public gis_user_time_in_places (long userid, long placeid, Timestamp starttime, Timestamp endtime, long group_user_id, long visit_number)
    {
		this();
		this.userid = userid;
		this.placeid = placeid;
		this.starttime = starttime;
		this.endtime = endtime;
		this.group_user_id = group_user_id;
		this.visit_number = visit_number;
	}
}
