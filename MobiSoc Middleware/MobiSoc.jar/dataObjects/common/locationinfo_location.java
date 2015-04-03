package dataObjects.common;

import core.data.dto.CoreDto;
import java.sql.Timestamp;


public class locationinfo_location extends CoreDto {

	private static final long serialVersionUID = 1L;

	/* Define *public*  members with names matching columns in fof_user table */
	public long userid;
	public Timestamp time;
	public String  mode;
	public double lon;
	public double lat;
	public double height;
	public int numberofap ;
	public int numberofknownap;
	public int floor;
	
	public locationinfo_location() {
		super();
	}

	public locationinfo_location (long userid, Timestamp time, String  mode, double lon, double lat, double height, int numberofap, int numberofknownap, int floor)
    {
		this();
		this.userid = userid;
		this.time = time;
		this.mode = mode;
		this.lon = lon;
		this.lat = lat;
		this.height = height;
		this.numberofap = numberofap;
		this.numberofknownap = numberofknownap;
		this.floor = floor;
	}
}
