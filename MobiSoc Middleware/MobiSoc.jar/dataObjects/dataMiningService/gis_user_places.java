package dataObjects.dataMiningService;
import core.data.dto.CoreDto;
import org.postgis.PGgeometry;

public class gis_user_places extends CoreDto {

	private static final long serialVersionUID = 1L;
	/* Define *public*  members with names matching columns in locationinfo_userPlaces table */
	public long userid;
	public long placeid;
	public String userlabel;
	public PGgeometry location;
	public double height;
	public int floor; 
	public int number_of_points; 
	
	  
	public gis_user_places() {
		super();
	}

	public gis_user_places (long userid, long placeid, String userlabel, PGgeometry location, double height, int floor, int number_of_points)
    {
		this();
		this.userid = userid;
		this.placeid = placeid;
		this.userlabel = userlabel;
		this.location = location;
		this.height = height;
		this.floor = floor;
		this.number_of_points = number_of_points;
	}
}