package dataObjects.socialStateLearning;

import java.sql.Timestamp;
import core.data.dto.CoreDto;

public class social_group extends CoreDto{
	  
	  private static final long serialVersionUID = 1L;
	  public long gid;
	  public String name;
	  public String description;
	  public String group_type;
	  public String recent_news;
	  public long owner;
	  public Timestamp update_time;
	  public long location;
	  public String website;
	  
	  public social_group()
		  {
			  super();
		  }
}
