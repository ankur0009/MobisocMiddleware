package dataObjects.privacyService;

import core.data.dto.CoreDto;
import ksoapDataObjects.privacyService.KVMHideInformationRow;

/**
 * HideInformationRow class
 * NOTE: This class holds the row for ac_hide_info table.
 * @author NeerajR.
 *
 */

public class HideInformationRow extends CoreDto {
	private static final long serialVersionUID = 2L;

	/* Define *public*  members with names matching columns in fof_user table */
	public long hinfo_id;
	public boolean hinfo_location;
	public boolean hinfo_time_based_events;
	public boolean hinfo_loc_based_events;
	public boolean hinfo_all_events;
	public boolean hinfo_user_profile_name;
	
	public HideInformationRow() {
		super();
	}

	public HideInformationRow(long hinfo_id, boolean hinfo_location, boolean hinfo_time_based_events, boolean hinfo_loc_based_events, boolean hinfo_all_events, boolean hinfo_user_profile_name)
    {
		this();
		this.hinfo_id = hinfo_id;
		this.hinfo_location = hinfo_location;
		this.hinfo_time_based_events = hinfo_time_based_events;
		this.hinfo_loc_based_events = hinfo_loc_based_events;
		this.hinfo_all_events = hinfo_all_events;
		this.hinfo_user_profile_name = hinfo_user_profile_name;
	}

	public HideInformationRow(KVMHideInformationRow objKVM_HI_Row)
    {
		this();
		if(objKVM_HI_Row != null)
		{
			if(objKVM_HI_Row.hinfo_id != null)
			{
				this.hinfo_id = objKVM_HI_Row.hinfo_id;
			}
			if(objKVM_HI_Row.hinfo_location != null)
			{
				this.hinfo_location = objKVM_HI_Row.hinfo_location;
			}
			if(objKVM_HI_Row.hinfo_time_based_events != null)
			{
				this.hinfo_time_based_events = objKVM_HI_Row.hinfo_time_based_events;
			}
			if(objKVM_HI_Row.hinfo_loc_based_events != null)
			{
				this.hinfo_loc_based_events = objKVM_HI_Row.hinfo_loc_based_events;
			}
			if(objKVM_HI_Row.hinfo_all_events != null)
			{
				this.hinfo_all_events = objKVM_HI_Row.hinfo_all_events;
			}
			if(objKVM_HI_Row.hinfo_user_profile_name != null)
			{
				this.hinfo_user_profile_name = objKVM_HI_Row.hinfo_user_profile_name;
			}
		}
	}
}
