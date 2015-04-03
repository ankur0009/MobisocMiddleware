package dataObjects.dataMiningService;
import core.data.dto.CoreDto;


public class locationinfo_user_places extends CoreDto {

	private static final long serialVersionUID = 1L;
	/* Define *public*  members with names matching columns in locationinfo_userPlaces table */
	public long userid;
	public long placeid;
	public String userlabel;
	public double lon;
	public double lat;
	public double height;
	public int floor; 
	public int number_of_points; 
	
	public locationinfo_user_places() {
		super();
	}

	public locationinfo_user_places (long userid, long placeid, String userlabel, double lon, double lat, double height, int floor, int number_of_points)
    {
		this();
		this.userid = userid;
		this.placeid = placeid;
		this.userlabel = userlabel;
		this.lon = lon;
		this.lat = lat;
		this.height = height;
		this.floor = floor;
		this.number_of_points = number_of_points;
	}
}