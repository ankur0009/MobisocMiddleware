package dataObjects.dataMiningService;
import core.data.dto.CoreDto;
import java.sql.Timestamp;


public class locationinfo_time_in_places extends CoreDto {

	private static final long serialVersionUID = 1L;
	/* Define *public*  members with names matching columns in locationinfo_userPlaces table */
	public long userid;
	public long placeid;
	public Timestamp starttime;
	public Timestamp endtime;
	
	public locationinfo_time_in_places() {
		super();
	}

	public locationinfo_time_in_places (long userid, long placeid, Timestamp starttime, Timestamp endtime)
    {
		this();
		this.userid = userid;
		this.placeid = placeid;
		this.starttime = starttime;
		this.endtime = endtime;
	}
}
