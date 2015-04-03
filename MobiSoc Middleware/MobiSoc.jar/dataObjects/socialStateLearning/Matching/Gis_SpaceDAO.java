package dataObjects.socialStateLearning.Matching;

import java.math.BigDecimal;

import org.postgis.PGgeometry;

import core.data.dto.CoreDto;

public class Gis_SpaceDAO extends CoreDto{

	public PGgeometry location;

	public Integer space_id;

	public String name;

	public Integer parent_id;

	public String type;
	
	public Long count;
	
	public BigDecimal sum;
	
	

	/**
	 * 
	 */
	public Gis_SpaceDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
