package dataObjects.privacyService;

import java.sql.Timestamp;

import core.data.dto.CoreDto;

/**
 * AccessControlStRows class
 * NOTE: This class holds a row for access control module (i.e. a row each from ac_access_control_mgr, ac_access_restrictions, ac_action_to_take and ac_hide_info table).
 * This class was written so as we can use a single database query and get row appropriate to statement_id from all the above listed tables.
 * @author NeerajR.
 *
 */

public class AccessControlStRow extends CoreDto {
	private static final long serialVersionUID = 1L;

	/* Define *public*  members with names matching columns in ac_access_control_statements table */
	public long acm_ac_st_id;
	public long acm_pe_id;
	public long acm_se_id;
	public boolean acm_st_enabled;
	public boolean acm_access_allowed;
	
	public boolean hinfo_location;
	public boolean hinfo_time_based_events;
	public boolean hinfo_loc_based_events;
	public boolean hinfo_all_events;
	public boolean hinfo_user_profile_name ;
	
	public boolean ar_loc_based;
	public double ar_loc_longitude;
	public double ar_loc_latitute;
	public double ar_loc_height;
	public boolean ar_time_based;
	public Timestamp ar_time_start_time;
	public Timestamp ar_time_end_time;
	public Timestamp ar_time_date;
	public long ar_time_date_of_the_month;
	public long ar_time_month_of_the_year;
	public String ar_time_frequency;
	public long ar_time_day_of_the_week;
	public boolean ar_co_loc_based;
	public long ar_co_loc_user_id;
	public long ar_co_loc_distance;
	
	public boolean att_type_primary_msg;
	public boolean att_typ_secondary_msg;
	public boolean att_type_sys_msg;
	public String att_primary_msg;
	public String att_secondary_msg;
	public String att_sys_msg;
	
	public AccessControlStRow() {
		super();
	}

	public AccessControlStRow(long acm_ac_st_id, long acm_pe_id, long acm_se_id, boolean acm_st_enabled, boolean acm_access_allowed, boolean hinfo_location, boolean hinfo_time_based_events, boolean hinfo_loc_based_events, boolean hinfo_all_events, boolean hinfo_user_profile_name, boolean ar_loc_based, double ar_loc_longitude, double ar_loc_latitute, double ar_loc_height, boolean ar_time_based, Timestamp ar_time_start_time, Timestamp ar_time_end_time, Timestamp ar_time_date, long ar_time_date_of_the_month, long ar_time_month_of_the_year, String ar_time_frequency, long ar_time_day_of_the_week, boolean ar_co_loc_based, long ar_co_loc_user_id, long ar_co_loc_distance, boolean att_type_primary_msg, boolean att_typ_secondary_msg, boolean att_type_sys_msg, String att_primary_msg, String att_secondary_msg, String att_sys_msg)
    {
		this();
		this.acm_ac_st_id = acm_ac_st_id;
		this.acm_pe_id = acm_pe_id;
		this.acm_se_id = acm_se_id;
		this.acm_st_enabled = acm_st_enabled;
		this.acm_access_allowed = acm_access_allowed;
		
		this.hinfo_location = hinfo_location;
		this.hinfo_time_based_events = hinfo_time_based_events;
		this.hinfo_loc_based_events = hinfo_loc_based_events;
		this.hinfo_all_events = hinfo_all_events;
		this.hinfo_user_profile_name = hinfo_user_profile_name;
		
		this.ar_loc_based = ar_loc_based;
		this.ar_loc_longitude = ar_loc_longitude;
		this.ar_loc_latitute = ar_loc_latitute;
		this.ar_loc_height = ar_loc_height;
		this.ar_time_based = ar_time_based;
		this.ar_time_start_time = ar_time_start_time;
		this.ar_time_end_time = ar_time_end_time;
		this.ar_time_date = ar_time_date;
		this.ar_time_date_of_the_month = ar_time_date_of_the_month;
		this.ar_time_month_of_the_year = ar_time_month_of_the_year;
		this.ar_time_frequency = ar_time_frequency;
		this.ar_time_day_of_the_week = ar_time_day_of_the_week;		
		this.ar_co_loc_based = ar_co_loc_based;
		this.ar_co_loc_user_id = ar_co_loc_user_id;
		this.ar_co_loc_distance = ar_co_loc_distance;

		this.att_type_primary_msg = att_type_primary_msg;
		this.att_typ_secondary_msg = att_typ_secondary_msg;
		this.att_type_sys_msg = att_type_sys_msg;
		this.att_primary_msg = att_primary_msg;
		this.att_secondary_msg = att_secondary_msg;
		this.att_sys_msg = att_sys_msg;
    }
}
