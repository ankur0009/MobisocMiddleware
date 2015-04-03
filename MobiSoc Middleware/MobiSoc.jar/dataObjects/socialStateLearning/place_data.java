package dataObjects.socialStateLearning;

import java.util.Vector;
import org.postgis.PGgeometry;
import dataObjects.common.Coordinates;
import core.data.dto.CoreDto;

public class place_data extends CoreDto{

	private static final long serialVersionUID = -3514519425782444299L;
	public long space_id;
	public String name;
	public long parent_id;
	public PGgeometry location;
	public String type;

	public place_data()
	{
		super();
	}
}
