package dataObjects.socialStateLearning;
import core.data.dto.CoreDto;

/**
 * this is used to get nearby places from the database
 * @author Maverick
 *
 */
public class nearby_place extends CoreDto{
	private static final long serialVersionUID = 1L;
	public long place_id;
	public double distance_to_place;
	
	public nearby_place()
	  {
		  super();
	  }
	
	public nearby_place(long place_id, double distance_to_place)
	{
		this();
		this.place_id = place_id;
		this.distance_to_place = distance_to_place;
	}
	  
}
