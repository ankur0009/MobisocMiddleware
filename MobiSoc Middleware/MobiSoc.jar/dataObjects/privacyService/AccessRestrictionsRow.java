package dataObjects.privacyService;

import core.data.dto.CoreDto;
import java.sql.Timestamp;

import ksoapDataObjects.privacyService.KVMAccessRestrictionsRow;

/**
 * AccessRestrictionsRow class
 * NOTE: This class holds the row for ac_access_restrictions table.
 * @author NeerajR.
 *
 */

public class AccessRestrictionsRow extends CoreDto {

	private static final long serialVersionUID = 3L;

	/* Define *public*  members with names matching columns in ac_access_restrictions table */
	public long ar_id;
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
	
	public AccessRestrictionsRow() {
		super();
	}

	public AccessRestrictionsRow(long ar_id, boolean ar_loc_based, double ar_loc_longitude, double ar_loc_latitute, double ar_loc_height, boolean ar_time_based, Timestamp ar_time_start_time, Timestamp ar_time_end_time, Timestamp ar_time_date, long ar_time_date_of_the_month, long ar_time_month_of_the_year, String ar_time_frequency, long ar_time_day_of_the_week, boolean ar_co_loc_based, long ar_co_loc_user_id, long ar_co_loc_distance)
    {
		this();
		this.ar_id = ar_id;
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
	}
	
	public AccessRestrictionsRow(KVMAccessRestrictionsRow _KVM_AR_Row)
	{
		this();
		if(_KVM_AR_Row != null)
		{
			if(_KVM_AR_Row.ar_id != null)
			{
				this.ar_id = _KVM_AR_Row.ar_id;
			}
			if(_KVM_AR_Row.ar_loc_based != null)
			{
				this.ar_loc_based = _KVM_AR_Row.ar_loc_based;
			}
			if(_KVM_AR_Row.ar_loc_longitude != null)
			{
				this.ar_loc_longitude = new Double(_KVM_AR_Row.ar_loc_longitude);
			}
			if(_KVM_AR_Row.ar_loc_latitute != null)
			{
				this.ar_loc_latitute = new Double(_KVM_AR_Row.ar_loc_latitute);
			}
			if(_KVM_AR_Row.ar_loc_height != null)
			{
				this.ar_loc_height = new Double(_KVM_AR_Row.ar_loc_height);
			}
			if(_KVM_AR_Row.ar_time_based != null)
			{
				this.ar_time_based = _KVM_AR_Row.ar_time_based;
			}
			if(_KVM_AR_Row.ar_time_start_time != null)
			{
				this.ar_time_start_time = new Timestamp(_KVM_AR_Row.ar_time_start_time);
			}
			if(_KVM_AR_Row.ar_time_end_time != null)
			{
				this.ar_time_end_time = new Timestamp(_KVM_AR_Row.ar_time_end_time);
			}
			if(_KVM_AR_Row.ar_time_date != null)
			{
				this.ar_time_date = new Timestamp(_KVM_AR_Row.ar_time_date);
			}
			if(_KVM_AR_Row.ar_time_date_of_the_month != null)
			{
				this.ar_time_date_of_the_month = _KVM_AR_Row.ar_time_date_of_the_month;
			}
			if(_KVM_AR_Row.ar_time_month_of_the_year != null)
			{
				this.ar_time_month_of_the_year = _KVM_AR_Row.ar_time_month_of_the_year;
			}
			if(_KVM_AR_Row.ar_time_frequency != null)
			{
				this.ar_time_frequency = _KVM_AR_Row.ar_time_frequency;
			}
			if(_KVM_AR_Row.ar_time_day_of_the_week != null)
			{
				this.ar_time_day_of_the_week = _KVM_AR_Row.ar_time_day_of_the_week;		
			}
			if(_KVM_AR_Row.ar_co_loc_based != null)
			{
				this.ar_co_loc_based = _KVM_AR_Row.ar_co_loc_based;
			}
			if(_KVM_AR_Row.ar_co_loc_user_id != null)
			{
				this.ar_co_loc_user_id = _KVM_AR_Row.ar_co_loc_user_id;
			}
			if(_KVM_AR_Row.ar_co_loc_distance != null)
			{
				this.ar_co_loc_distance = _KVM_AR_Row.ar_co_loc_distance;
			}
		}
	}
}
