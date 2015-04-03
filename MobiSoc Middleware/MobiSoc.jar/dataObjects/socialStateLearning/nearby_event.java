package dataObjects.socialStateLearning;
import core.data.dto.CoreDto;

/**
 * this is used to get nearby events from the database
 * @author Maverick
 *
 */
public class nearby_event extends CoreDto{
	private static final long serialVersionUID = 1L;
	public long event_id;
	public double distance_to_event;
	
	public nearby_event()
	  {
		  super();
	  }
	
	public nearby_event(long event_id, double distance_to_event)
	{
		this();
		this.event_id = event_id;
		this.distance_to_event = distance_to_event;
	}
	  
}
