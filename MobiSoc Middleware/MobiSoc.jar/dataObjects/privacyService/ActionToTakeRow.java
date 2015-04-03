package dataObjects.privacyService;

import ksoapDataObjects.privacyService.KVMActionToTakeRow;
import core.data.dto.CoreDto;

/**
 * ActionToTakeRow class
 * NOTE: This class holds the row for ac_action_to_take table.
 * @author NeerajR.
 *
 */

public class ActionToTakeRow extends CoreDto {

	private static final long serialVersionUID = 4L;

	/* Define *public*  members with names matching columns in ac_access_restrictions table */
	public long att_id;
	public boolean att_type_primary_msg;
	public boolean att_typ_secondary_msg;
	public boolean att_type_sys_msg;
	public String att_primary_msg;
	public String att_secondary_msg;
	public String att_sys_msg;
	
	public ActionToTakeRow() {
		super();
	}

	public ActionToTakeRow(long att_id, boolean att_type_primary_msg, boolean att_typ_secondary_msg, boolean att_type_sys_msg, String att_primary_msg, String att_secondary_msg, String att_sys_msg)
    {
		this();
		this.att_id = att_id;
		this.att_type_primary_msg = att_type_primary_msg;
		this.att_typ_secondary_msg = att_typ_secondary_msg;
		this.att_type_sys_msg = att_type_sys_msg;
		this.att_primary_msg = att_primary_msg;
		this.att_secondary_msg = att_secondary_msg;
		this.att_sys_msg = att_sys_msg;
	}

	public ActionToTakeRow(KVMActionToTakeRow objKVM_ATT_Row)
    {
		this();
		if(objKVM_ATT_Row != null)
		{			
			if(objKVM_ATT_Row.att_id != null)
			{			
				this.att_id = objKVM_ATT_Row.att_id;
			}
			if(objKVM_ATT_Row.att_type_primary_msg != null)
			{			
				this.att_type_primary_msg = objKVM_ATT_Row.att_type_primary_msg;
			}
			if(objKVM_ATT_Row.att_typ_secondary_msg != null)
			{			
				this.att_typ_secondary_msg = objKVM_ATT_Row.att_typ_secondary_msg;
			}
			if(objKVM_ATT_Row.att_type_sys_msg != null)
			{			
				this.att_type_sys_msg = objKVM_ATT_Row.att_type_sys_msg;
			}
			if(objKVM_ATT_Row.att_primary_msg != null)
			{			
				this.att_primary_msg = objKVM_ATT_Row.att_primary_msg;
			}
			if(objKVM_ATT_Row.att_secondary_msg != null)
			{			
				this.att_secondary_msg = objKVM_ATT_Row.att_secondary_msg;
			}
			if(objKVM_ATT_Row.att_sys_msg != null)
			{			
				this.att_sys_msg = objKVM_ATT_Row.att_sys_msg;
			}
		}
	}
}
