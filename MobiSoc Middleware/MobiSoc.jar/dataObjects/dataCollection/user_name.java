package dataObjects.dataCollection;

import core.data.dto.CoreDto;

/**
 * This class is used to get user name from DB
 * @author Maverick
 */
public class user_name extends CoreDto{
	private static final long serialVersionUID = 1L;
	public String username;
	
	public user_name()
	  {
		  super();
	  }
	
	public user_name(String username)
	{
		this();
		this.username = username;
	}
	  
}