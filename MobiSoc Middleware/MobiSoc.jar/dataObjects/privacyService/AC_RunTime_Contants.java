package dataObjects.privacyService;

/**
 * AC_RunTime_Contants class
 * NOTE: The class has all the Constants used in Access Control module.
 * @author NeerajR.
 *
 */

public class AC_RunTime_Contants {
	
	public static final int ACCESS_INFO_LOC = 1;
	public static final int ACCESS_INFO_TIME_BASED_EVENTS = 2;
	public static final int ACCESS_INFO_LOC_BASED_EVENTS = 3;
	public static final int ACCESS_INFO_ALL_EVENTS = 4;
	public static final int ACCESS_INFO_USER_PROFILE = 5;
	
	/*
	 * Currently hard coded small value for the variation distance from the actual location specifed. 
	 */
	public static final long VARIATION_DISTANCE = 20;
	
	public static final int LOCATION_FRESHNESS = 180000; //user location data older than this time is not considered when checking location/colocation based constraints
	
	public static final String STR_ONE_TIME = "onetime";
	public static final String STR_EVERYDAY = "everyday";
	public static final String STR_ONCE_A_WEEK = "onceaweek";
	public static final String STR_ONCE_A_MONTH = "onceamonth";
	public static final String STR_ONCE_A_YEAR = "onceayear";
}
