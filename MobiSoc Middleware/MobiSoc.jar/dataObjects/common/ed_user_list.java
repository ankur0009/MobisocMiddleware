package dataObjects.common;

import core.data.dto.CoreDto;
import java.sql.Timestamp;

public class ed_user_list extends CoreDto {

	private static final long serialVersionUID = 1L;
	/* Define *public*  members with names matching columns in locationinfo_userPlaces table */
	
	public long event_id;
	public long user_id;
	public String list_type;
		
	public ed_user_list() {
			super();
		}

	public ed_user_list (long event_id, long user_id, String list_type)
	    {
			this();
			this.event_id =event_id;
			this.user_id = user_id;
			this.list_type = list_type;
		}
}
