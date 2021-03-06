package dataObjects.common;

import core.data.dto.CoreDto;
import java.sql.Timestamp;

public class ed_cb_trigger extends CoreDto {

	private static final long serialVersionUID = 1L;
	/* Define *public*  members with names matching columns in locationinfo_userPlaces table */
	public long event_id;
	public Timestamp start_time;
	public Timestamp end_time;
	public String weekday;
	public Timestamp date;
	public String frequency;
	public Timestamp last_fire;
	public boolean time_constraints;
	public boolean colocation_constraints;
	public long source_user;
	public int  distance_to_users;
	public boolean location_constraints;
	public int distance_to_places;
	
	public ed_cb_trigger() {
			super();
		}

	public ed_cb_trigger (long event_id, Timestamp start_time, Timestamp end_time, String weekday, Timestamp date, String frequency, Timestamp last_fire, boolean time_constraints, boolean colocation_constraints, long source_user, int distance_to_users, boolean location_constraints,int distance_to_places)
	    {
			this();
			this.event_id =event_id;
			this.start_time = start_time;
			this.end_time = end_time;
			this.weekday = weekday ;
			this.date = date;
			this.frequency = frequency;
			this.last_fire = last_fire;
			this.time_constraints = time_constraints;
			this.colocation_constraints = colocation_constraints;
			this.source_user = source_user;
			this.distance_to_users = distance_to_users;
			this.location_constraints = location_constraints;
			this.distance_to_places = distance_to_places;
		}
}
