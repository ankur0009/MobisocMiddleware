package dataObjects.dataCollection;

import core.data.dto.CoreDto;

/**
 * This class is used to get group name from DB
 * @author Maverick
 */
public class group_name extends CoreDto{
	private static final long serialVersionUID = 1L;
	public String name;
	
	public group_name()
	  {
		  super();
	  }
	
	public group_name(String name)
	{
		this();
		this.name = name;
	}
	  
}