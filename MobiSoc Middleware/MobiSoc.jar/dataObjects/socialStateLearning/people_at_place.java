package dataObjects.socialStateLearning;
import core.data.dto.CoreDto;

public class people_at_place extends CoreDto{

	private static final long serialVersionUID = 1L;
	public long user_id;
	
	public people_at_place()
	  {
		  super();
	  }
	
	public people_at_place(long user_id)
	{
		this();
		this.user_id = user_id;
	}
}
