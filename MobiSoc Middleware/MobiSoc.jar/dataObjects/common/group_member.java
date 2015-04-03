package dataObjects.common;
import java.sql.Timestamp;
import core.data.dto.CoreDto;

public class group_member extends CoreDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public long groupid;
	public long memberid;
	public long accessid;
	public String status;
	public Timestamp joindate;
	
	public group_member()
	{
		super();
	}
	
	public group_member(long groupid, long memberid, long accessid, String status, Timestamp joindate) {
		this();
		this.groupid = groupid;
		this.memberid = memberid;
		this.accessid = accessid;
		this.status = status;
		this.joindate = joindate;
	}
	
	
}

