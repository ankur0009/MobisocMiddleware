package dataObjects.common;
import java.util.Vector;

/**
 * thiss class stores basic data pertaining to a place
 * @author Maverick
 *
 */
public class PlaceData {
	
	private long place_id;
	private String name;
	private long parent_id;
	private String type;
	private Vector<Coordinates> location; //this is a vector of points (coordinates) that represents a polygon that defines the location  
	
	public PlaceData()
	{
		place_id = -1;
		name = "";
		parent_id = -1;
		type = "";
		location = new Vector<Coordinates>();
	}
	
	public Vector<Coordinates> getLocation() {
		return location;
	}
	public void setLocation(Vector<Coordinates> location) {
		this.location = location;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getParent_id() {
		return parent_id;
	}
	public void setParent_id(long parent_id) {
		this.parent_id = parent_id;
	}
	public long getPlace_id() {
		return place_id;
	}
	public void setPlace_id(long place_id) {
		this.place_id = place_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
