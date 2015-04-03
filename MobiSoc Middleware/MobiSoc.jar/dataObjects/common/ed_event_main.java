package dataObjects.common;

import core.data.dto.CoreDto;
import java.sql.Timestamp;

public class ed_event_main extends CoreDto {

	private static final long serialVersionUID = 1L;
	/* Define *public*  members with names matching columns in locationinfo_userPlaces table */
	public long event_id;
	public Timestamp registration_time;
	public String event_type;
	public String description;
	public long source_user_id;
	public String service_api;
	public String service_name;
	
	public ed_event_main() {
			super();
		}

	public ed_event_main (long event_id, Timestamp registration_time, String event_type, String description, long source_user_id, String service_api, String service_name)
	    {
			this();
			this.event_id =event_id;
			this.registration_time = registration_time;
			this.event_type = event_type;
			this.description = description;
			this.source_user_id = source_user_id;
			this.service_api = service_api;
			this.service_name = service_name;
		}
	}
