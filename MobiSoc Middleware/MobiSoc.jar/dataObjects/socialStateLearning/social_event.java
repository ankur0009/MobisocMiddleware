package dataObjects.socialStateLearning;
import java.sql.Timestamp;
import core.data.dto.CoreDto;

public class social_event extends CoreDto{
	private static final long serialVersionUID = 1L;
     public long eid;
	 public String name;
	 public String host;
	 public String description;
	 public String  event_type;
	 public long owner;
	 public Timestamp start_time;
	 public Timestamp end_time;
	 public Timestamp update_time;
	 public long  location;
	 
	 public social_event()
	 {
		 super();
	 }
}
