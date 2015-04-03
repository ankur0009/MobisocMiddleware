package dataObjects.common;
import core.data.dto.CoreDto;

/**
 * This is a generic class used to get any kind of ID from from the
 * database 
 * @author Maverick
 */
public class select_max extends CoreDto {
	private static final long serialVersionUID = 1L;
	public long max;
	
	public select_max()
	{
		super();
	}
	
	public select_max(long x_max)
	{
		this();
		max = x_max;
	}
}
