package dataObjects.socialStateLearning;

import java.sql.Timestamp;

import org.postgis.Geometry;
import org.postgis.PGgeometry;

import core.data.dto.CoreDto;

public class gis_location extends CoreDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3514519425782444299L;
	public long user_id;
	public PGgeometry location;
	public Timestamp time;
	public String mode;
	public float height;
	public int num_visible_ap;
	public int num_known_ap;
	public int floor;
	
	public Double distance;
	
	
	
	
	
	  
	
	/**
	 * 
	 */
	public gis_location() {
		super();
		// TODO Auto-generated constructor stub
	}
}
