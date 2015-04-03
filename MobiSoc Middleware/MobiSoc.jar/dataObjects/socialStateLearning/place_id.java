package dataObjects.socialStateLearning;
import core.data.dto.CoreDto;

public class place_id extends CoreDto{

	private static final long serialVersionUID = 1L;
	public long place_id;
	
	public place_id()
	  {
		  super();
	  }
	
	public place_id(long place_id)
	{
		this();
		this.place_id = place_id;
	}
}
