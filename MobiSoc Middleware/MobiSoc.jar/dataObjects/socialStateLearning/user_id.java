package dataObjects.socialStateLearning;
import core.data.dto.CoreDto;

public class user_id extends CoreDto{

	private static final long serialVersionUID = 1L;
	public long user_id;
	
	public user_id()
	  {
		  super();
	  }
	
	public user_id(long user_id)
	{
		this();
		this.user_id = user_id;
	}
}
