package dataObjects.dataCollection;

import core.data.dto.CoreDto;

/**
 * This class is used to get event name from DB
 * @author Maverick
 */
public class event_name extends CoreDto{
	private static final long serialVersionUID = 1L;
	public String name;
	
	public event_name()
	  {
		  super();
	  }
	
	public event_name(String name)
	{
		this();
		this.name = name;
	}
	  
}