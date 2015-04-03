package dataObjects.common;


import java.sql.Timestamp;
import java.io.Serializable;



public class UserLocationTime implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Timestamp time;
	private String mode;
	private Coordinates location;
	private int numberOfAP;
	private int numberOfKnownAP;
	private int floor;
	
	public UserLocationTime()
	{
		;
	}
	
	public UserLocationTime(Timestamp XTime, Coordinates XLocation)
	{
		time = XTime;
		location = XLocation;
	}
	//functions to get and set the values
	public Coordinates getLocation()
	{
		return location;
	}
	
	public void setLocation(Coordinates XLocation)
	{
		location = XLocation;
	}
	
	public void setMode(String Xmode)
	{
		mode = Xmode;
	}
	
	public String getMode()
	{
		return mode;
	}
	
	
	public Timestamp getTime()
	{
		return time;
	}
	
	public void setTime(Timestamp XTime)
	{
		time = XTime;
	}
	
	public void setNumberOfAP(int XnumberOfAP)
	{
		numberOfAP = XnumberOfAP;
	}
	
	public int getNumberOfAP()
	{
		return numberOfAP;
	}
	
	public int getNumberOfKnownAP()
	{
		return numberOfKnownAP; 
	}
	public void setNumberOfKnownAP(int XnumberOfKnownAP)
	{
		numberOfKnownAP = XnumberOfKnownAP;
	}
	
	public int getFloor()
	{
		return floor; 
	}
	public void setFloor(int Xfloor)
	{
		floor = Xfloor;
	}
	
}
