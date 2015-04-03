package dataObjects.socialStateLearning;
import core.data.dto.CoreDto;

public class social_event_id extends CoreDto{

	private static final long serialVersionUID = 1L;
	public long event_id;
	
	public social_event_id()
	  {
		  super();
	  }
	
	public social_event_id(long event_id)
	{
		this();
		this.event_id = event_id;
	}
}
