package dataObjects.common;
import java.sql.Timestamp;
import core.data.dto.CoreDto;

public class group_group extends CoreDto{
	/**
	 * 
	 */
	  private static final long serialVersionUID = 1L;
	  public long groupid; 
	  public String name;
	  public long ownerid;
	  public String type;
	  public Timestamp creationtime;
	  public String description;
	  
	
	  public group_group()
	  {
		  super();
	  }
	  
	  public group_group(long groupid, String name, long ownerid, String type, Timestamp creationtime, String description) 
	  {
		this();
		this.groupid = groupid;
		this.name = name;
		this.ownerid = ownerid;
		this.type = type;
		this.creationtime = creationtime;
		this.description = description;
	}
}
