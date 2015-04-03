package dataObjects.dataCollection;
import core.data.dto.CoreDto;

/**
 * This class is used to get user id from DB
 * @author Maverick
 */
public class userid extends CoreDto{

	private static final long serialVersionUID = 1L;
	public long userid;
	
	public userid()
	  {
		  super();
	  }
	
	public userid(long userid)
	{
		this();
		this.userid = userid;
	}
}
