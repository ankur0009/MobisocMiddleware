package dataObjects.socialStateLearning;
import core.data.dto.CoreDto;
import java.sql.Timestamp;


public class copresence_history extends CoreDto {

	private static final long serialVersionUID = 1L;
	/* Define *public*  members with names matching columns in locationinfo_userPlaces table */
	public long placeid;
	public Timestamp starttime;
	public Timestamp endtime;
	
	public copresence_history() {
		super();
	}

	public copresence_history (long placeid, Timestamp starttime, Timestamp endtime)
    {
		this();
		this.placeid = placeid;
		this.starttime = starttime;
		this.endtime = endtime;
	}
}
