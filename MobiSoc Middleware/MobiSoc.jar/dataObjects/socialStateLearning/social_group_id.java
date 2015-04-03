package dataObjects.socialStateLearning;
import core.data.dto.CoreDto;

public class social_group_id extends CoreDto{

	private static final long serialVersionUID = 1L;
	public long group_id;
	
	public social_group_id()
	  {
		  super();
	  }
	
	public social_group_id(long group_id)
	{
		this();
		this.group_id = group_id;
	}
}
