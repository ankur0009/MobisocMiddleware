package dataObjects.common;

import java.sql.Timestamp;
import java.util.*;


public class CBTrigger {
	private String frequency;
	private Timestamp lastFire;
	private boolean timeConstraints; //true if trigger has time constraints
	private Timestamp startTime;
	private Timestamp endTime;
	private Timestamp date;
	private String weekDay;
	private boolean colocationConstraints; //true if trigger has colocation constraints
	private long sourceUser;
	private ArrayList targetUsers;
	private int distanceToUsers;
	private boolean locationConstraints; //true if trigger has location constriants 
	private ArrayList listOfPlaces;
	private int distanceToPlaces;
	
	
	/**
	 * 
	 * Constructor with no parameters. Initializes everything
	 * to null or garbage values. Mostly used for testing
	 *
	 */
	public CBTrigger()
	{
		frequency = null;
		lastFire = null;
		timeConstraints = false; 
		startTime = null;
		endTime = null;
		date = null;
		weekDay = null;
		colocationConstraints = false;
		sourceUser = -1;
		targetUsers = null;
		distanceToUsers = -1;
		locationConstraints = false;
		listOfPlaces = null;
		distanceToPlaces = -1;
		;
	}
	
	//constructor
	public CBTrigger(String Xfrequency, Timestamp XlastFire, boolean XtimeConstaints, Timestamp XstartTime, Timestamp XendTime, Timestamp Xdate, String XweekDay, 
			  boolean XcolocationConstraints, long XsourceUser, ArrayList XtargetUsers, 
			  boolean XlocationConstraints, int XdistanceToUsers, ArrayList XlistOfPlaces, int XdistanceToPlaces)
	{
		frequency = Xfrequency;
		lastFire = XlastFire;
		timeConstraints = XtimeConstaints; 
		startTime = XstartTime;
		endTime = XendTime;
		date = Xdate;
		weekDay = XweekDay;
		colocationConstraints = XcolocationConstraints;
		sourceUser = XsourceUser;
		targetUsers = XtargetUsers;
		distanceToUsers = XdistanceToUsers;
		locationConstraints = XlocationConstraints;
		listOfPlaces = XlistOfPlaces;
		distanceToPlaces = XdistanceToPlaces;
	}
	
	//functions to access and return the basic varables
	public Timestamp getLastFire()
	{
		return lastFire;
	}
	public void setLastFire(Timestamp XlastFire)
	{
		lastFire = XlastFire;
	}
	
	public String getFrequency()
	{
		return frequency;
	}
	
	public void setFrequency(String XFrequency)
	{
		frequency = XFrequency;
	}
	
	public boolean getTimeConstraints()
	{
		return timeConstraints;
	}
	
	public void setTimeConstraints(boolean XtimeConstraints)
	{
		timeConstraints = XtimeConstraints;
	}
	
	public Timestamp getStartTime()
	{
		return startTime;
	}
	
	public void setStartTime(Timestamp XstartTime)
	{
		startTime = XstartTime;
	}
	
	public Timestamp getEndTime()
	{
		return endTime;
	}
	
	public void setEndTime(Timestamp XendTime)
	{
		endTime = XendTime;
	}
	
	public Timestamp getDate()
	{
		return date;
	}
	
	public void setDate(Timestamp Xdate)
	{
		date = Xdate;
	}
	
	public String getweekDay()
	{
		return weekDay;
	}
	
	public void setWeekDay(String XweekDay)
	{
		weekDay = XweekDay;
	}
	
	
	
	public boolean getColocationConstraints()
	{
		return colocationConstraints;
	}
	
	public void setColocationConstraints(boolean XcolocationConstraints)
	{
		colocationConstraints = XcolocationConstraints;
	}
	
	
	
	
	
	public long getSourceUser()
	{
		return sourceUser;
	}
	
	public void setSourceUser(long XSourceUser)
	{
		sourceUser = XSourceUser;
	}
	
	public ArrayList getTargetUsers()
	{
		return targetUsers;
	}
	
	public void setTargetUsers(ArrayList XTargetUsers)
	{
		targetUsers = XTargetUsers;
	}
	
	public boolean getLocationConstraints()
	{
		return locationConstraints;
	}
	
	public void setLocationConstraints(boolean XlocationConstraints)
	{
		locationConstraints = XlocationConstraints;
	}
	
	
	public ArrayList getListOfPlaces()
	{
		return listOfPlaces;
	}
	
	public void setListOfPlaces(ArrayList XlistOfPlaces)
	{
		listOfPlaces = XlistOfPlaces;
	}

	public int getDistanceToPlaces()
	{
		return distanceToPlaces;
	}
	
	public void setDistanceToPlaces(int XdistanceToPlaces)
	{
		distanceToPlaces = XdistanceToPlaces;
	}
	
	public int getDistanceToUsers()
	{
		return distanceToUsers;
	}
	
	public void setDistanceToUsers(int XdistanceToUsers)
	{
		distanceToUsers = XdistanceToUsers;
	}
}
