package dataObjects.common;

import core.data.dto.CoreDto;
import java.sql.Timestamp;

public class ed_place_list extends CoreDto {

	private static final long serialVersionUID = 1L;
	/* Define *public*  members with names matching columns in locationinfo_userPlaces table */
	
	public long event_id;
	public long place_id;
	public double lat;
	public double lon;
	public float ht;
	
	  
	public ed_place_list() {
			super();
		}

	public ed_place_list (long event_id, long place_id, 
			double lat, double lon, float ht)
	    {
			this();
			this.event_id =event_id;
			this.place_id = place_id;
			this.lat = lat;
			this.lon = lon;
			this.ht = ht;
		}
}
