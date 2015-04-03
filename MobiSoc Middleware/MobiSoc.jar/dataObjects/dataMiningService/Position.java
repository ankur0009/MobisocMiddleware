package dataObjects.dataMiningService;
/**
 * 
 * @author Mavrck
 * 
 * Stores the current user position. Position consists of two parts
 * the coordinates (Latitude, Longitude , Height) and the time 
 *
 */

import java.sql.Timestamp;


public class Position {

	private Coordinates posCoordinates;
	private Timestamp posTime;
	
	/**
	 * gives time object for the position    
	 *  
	 * @param none
	 * @return time for a position  
	 * @throws None
	 */
	public Timestamp getTime()
	{
		return posTime;
	}

	/**
	 * sets time for the position    
	 *  
	 * @param T
	 *  	Time for a position
	 * @return None  
	 * @throws None
	 */
	public void setTime(Timestamp T)
	{
		posTime = T;
	}

	/**
	 * gives coordinates object for the position    
	 *  
	 * @param none
	 * @return coordinates for a position  
	 * @throws None
	 */
	public Coordinates getCoordinates()
	{
		return posCoordinates;
	}
	

	/**
	 * sets coordinates for the position    
	 *  
	 * @param C
	 *  	Coordinates for a position
	 * @return None  
	 * @throws None
	 */
	public void setCoordinates(Coordinates C)
	{
		posCoordinates = C;
	}
}
