package services;
import dataObjects.dataMiningService.*;
import dataObjects.socialStateLearning.social_group_id;

/**
 * Things To Do:
 *   ** Take into consideration Floor information when performing clustering for place extraction
 *   ** When we have trace taken at different time, we might take them as
 *   ** one single trace. Fix code to take care of this.
 */

import java.sql.*;
import org.postgis.Geometry;
import org.postgis.PGgeometry;
import org.postgis.Point;
import edu.njit.sc.core.dao.SmartCampusDAO;


/**
 * This service provides the API for (Data Mining) as described in the MobiSoc paper/documentation.
 * Due to restrictions imposed by KSOAP on the type of dataobects that can be exchanged, for ceratin methods
 * we provide two versions of the API. One that works with KSOAP and the other one works generally. If we do
 * not provide a specific KSOAP version, then the method works with KSOAP as well as in the general case.
 * The parameters that guide the data mining algorithms can be changed in the class definition. The parameters 
 * are preset to optimal values. Details on how to set these paprameters can be obtained from the Mobisoc and GPI
 * journal papers.
 * @author Maverick
 */
public class DataMiningService {
		
		/**
		 * Gloabal variables/presets for extracting places. These can be changed to obtain different performance from
		 * the algorithm  
		 */
		private int timeForSignificantCluster; //time in seconds for a cluster to be considered significant
		private int distanceBetweenClusters; //minimum distance in meters for two clusters to be considered separate
		private int segmentSize; //segment size for clustering
		private int distanceForNewPlace; //if distance between extracted place and the place closest to it is greater than this value, then it is a new place
		private int maxTimeDifferenceBetweenTraces; //(in milliseconds)if two consecutive traces exceed this, then they are at different times
		
		
		/**
		 * Gloabal variables/presets for extracting co-present users. These can be changed to obtain different performance 
		 * from the algorithm 
		 */
		private String timeForSignificantMainCluster; //postgres style time duration for determining if a user cluster is significant to be considered for a group place
		private String timeForSignificantCopresenceCluster; //postgres style time duration for determiming if a group cl;uster is significant
		private int  maximumDistanceForUserCopresence; //if the distance between two users is greater than this then they are in different places
		
		
		/**
		 * Gloabal variables/presets for extracting groups and places. These can be changed to obtain different performance 
		 * from the algorithm 
		 */
		private static int numberOfUsers = 1000; //maximum number of users expected for the system
		private long[][] DGM; //discovered group members. the first column is the user and the second column marks if the
							  //if the user is processed or not
		private long[] NGC; //new group connections
		private int[][] CPG; //co-presence graph
		private double RCP; //group member frequency. this is the percentage of co-presence required for a user
						   //to be considered a group member
		private double MVC; //the number of user visits should exceed this number
							//for the place to be considered for group place extraction
		private double EVF; //estimated group memeber visit frequency
							//this is the estimated frequency with which a 
							//group member visits a group place
							//this is usd to calculate the extimated group meetings
		private int MI; //maximum number of iterations  to discover more group members
		private int dgmCounter;//counter to keep track where to insert element in DGM
		private int ngcCounter;//counter to keep track of where to insert element in NGC
		
		
		public DataMiningService()
		{
			//set parameters for place extraction
			timeForSignificantCluster = 500;
			distanceBetweenClusters = 30;
			segmentSize = 10;
			distanceForNewPlace = 15;
			maxTimeDifferenceBetweenTraces = 300000;
			
			//set parameters for copresence extraction
			timeForSignificantMainCluster = "00:15:00"; //postgres style time duration for determining if a user cluster is significant to be considered for a group place
			timeForSignificantCopresenceCluster= "00:15:00"; //postgres style time duration for determiming if a group cluster is significant
			maximumDistanceForUserCopresence = 15; //if the distance between two users is greater than this then they are in different places
			
			//set parameters for groups/places extraction. these can be changed to get different performance from the algorithm
			RCP = 0.2;
			MI = 3;
			EVF = 0.5;
		}

		
		/**
		 * *************************************************************************************************************************
		 * *************************************************************************************************************************
		 * ******************************************Data Mining API Starts Here***********************************************
		 * *************************************************************************************************************************
		 * *************************************************************************************************************************
		 */
		
		/**
		 * extractGroupsAndPlaces
		 * 
		 * PGM (Discovered Group Members) - An array of longs with size [1000][2]. First column is userid. Second column is whether the user has been processed (1) or not (0)
		 * NGC (New Group Connections) - An array of longs with size [1000]. This contains the newly discovered group connections. 
		 * CPG - An array of integers with size [1000][1000]. Each entry is either zero or one. A one at the position [X,Y] represents a co-presence 
		 * connection between users at position X and Y in PGM.  (Not this represents a graph)
		 * 
		 * 
		 * This function performs the following functionality 
		 *  ** Start wih the given user X and place P
		 *  ** Find users that were frequently co-present (determined by GMF) with this user at the given place between the given time duration. Add these users to NGC.
		 *  		** Get the visit count for X at place P between the starttime and endtime
		 *  		** Get the visit count for all other users at place P (co-present with X) between starttime and endtime
		 *  		** Figure out which users are frequently co-present (determined by GMF). Add these users to NGC  
		 *  ** Add users from NGC to PGM, if they are not already present
		 *  ** Add edges from X to all users from NGC in CPG (an edge is represented by 1)
		 *  ** Mark X as processed in PGM
		 *  ** Search PGM to find an uprocessed user. If found repeat the function with the new user.
		 *  
		 * 		 
		 * ************************ Important Note ***********************************
		 * ***************************************************************************
		 * 
		 * Before you use this API. 
		 * 		Make sure that you have extracted significant places for all the users 
		 * 		using the method "extractPlaces" for at least the same time frame as 
		 * 		used for this API. 
		 * 
		 * 		Also make sure that you have extracted co-present users for all the users 
		 * 		using the the method "extract copresent users" for at least the same time
		 * 		frame as used for this API  
		 * 
		 * ******************************************************************************
		 * ******************************************************************************
		 */
		
		
		/**
		 * This function is used to extract groups and group places for a given user and a 
		 * given place between a given time frame. The group/place extraction process in 
		 * described in the GPI journal paper. Make sure that you have extracted significant 
		 * places for all the users, using the method "extractPlaces" for at least the same 
		 * time frame as used for this API. Also make sure that you have extracted co-present 
		 * users for all the users, using the the method "extract copresent users" for at least 
		 * the same time frame as used for this API
		 * 
		 * @param userId
		 * 		User Id for the user whose groups/places are to be discovered
		 * @param placeId
		 *      Place Id for the user whose groups/places are to be discovered 
		 * @param startTime
		 * 		Start time for search in milliseconds (converted from timestamp object)
		 * @param endTime
		 * 		End time for search in milliseconds (converted from timestamp object)
		 * @author Maverick
		 */
		public void extractGroupsAndPlaces(Long userId, Long placeId, Long startTime, Long endTime)
		{
			long newSocialGroupId = -1; //to fetch the id for the newly identified social group
			long nextUser = -1; //next user to call the function with
			int iterationCount = 1;//to keep track of the number of iterations
			
			//these arrays store the results of grop extraction 
			DGM = new long[numberOfUsers][2];
			dgmCounter = 0;
			NGC = new long[numberOfUsers];
			ngcCounter = 0;
			CPG = new int[numberOfUsers][numberOfUsers];
			//initilize CPG to zeros
			for(int i=0; i<numberOfUsers; i++)
			{
				for(int j=0; j<numberOfUsers; j++)
				{
					CPG[i][j] = 0;
				}
			}
			
			//make the first call to group memebrs identification. mo
			//more calls are made if group members are discovered
			//the maximum number of calls is given by global constant MI
			identifyGroupMembers(userId, placeId, new Timestamp(startTime), new Timestamp(endTime));//call the method to identify group members once
			
			while(iterationCount < MI)
			{
				//search DGM to find an unprocessed user. if found repeat the method with this user
				for(int n=0; n<dgmCounter; n++)
				{
					if(DGM[n][1] ==0)
					{
						nextUser = DGM[n][0];
						break;
					}	
				}
				//If an unporcessed user is found. Repeat the method with this user
				if(nextUser != -1)
				{
					extractGroupsAndPlaces(new Long(nextUser), placeId, startTime, endTime);
					nextUser = -1;
					iterationCount ++;
				}
				else
				{
					break;
				}
			}
			
			
			//if group members are discovered, then insert the new group along with group members
			if(dgmCounter > 0)
			{
					/** 
					 * Insert the newly discovered group in the database. The name of the group is current timestamp to make it unique 
					 */
					String groupName = new Long(System.currentTimeMillis()).toString();
					// Array of parameter values (primtives should wrapped in objects)
					Object[] params =  new Object[] {
						groupName,	
						placeId
					};
					
					String insertGroup="INSERT INTO facebook_group(name,location) " +
									   "VALUES (?,?)";
					
					try{
						//Invoke update method on DAO to perform insert
						int rowsUpdated = new SmartCampusDAO().update(insertGroup,params);
					}catch(Exception e)
					{
						System.out.print("Error while inserting newly discovered group");
					}
								
					
					/**
					 * Find the group id for the new group to insert group members
					 */
					params = new Object[] {
							groupName
							};
					
					String query = "SELECT distinct gid as group_id " +
								   "FROM facebook_group " +
					               "WHERE name=? ";       
					
					try{
						social_group_id[] newSocialGroupIdDB = (social_group_id[]) new SmartCampusDAO().query(query,params,social_group_id.class);
						
						if(newSocialGroupIdDB.length > 0)
						{
							newSocialGroupId = newSocialGroupIdDB[0].group_id; 
						}
					}catch(Exception e)
					{
						System.out.println("Error while fetching new social group id");
						e.printStackTrace();
					}
					
			
					
					/**
					 * Insert the group members for the newly identified group
					 */
					for(int i =0; i<dgmCounter; i++)
					{
						params =  new Object[] {
							newSocialGroupId,	
							new Long(DGM[i][0])
						};
						
						String insertGroupMembers="INSERT INTO facebook_group_membership(gid,uid) " +
										   		  "VALUES (?,?)";
						
						try{
							//Invoke update method on DAO to perform insert
							int rowsUpdated = new SmartCampusDAO().update(insertGroupMembers,params);
						}catch(Exception e)
						{
							System.out.print("Error while inserting newly discovered group members");
						}
					}
			}
			
			
			/**
			 * ****************************************************
			 * ****************************************************
			 * *****************For Testing************************
			 * ****************************************************
			 * ****************************************************
			 * ****************************************************
			 */
			//			for(int p=0;p<pgmCounter; p++)
			//			{
			//				System.out.println(DGM[p][0]);
			//			}
			//			
			//			
			//			System.out.println("----------------------------------");
			//			System.out.println("Finished processing user" + userId);
			//			System.out.println("----------------------------------");
			//			
			/*******************************************************
			 * *****************************************************
			 * ******************For Testing************************
			 * *****************************************************
			 * *****************************************************
			 */
		}
		
		
		/**
		 * This function finds out all the users that were copresent with a given user between
		 * a given time frame. The copresent users are then stored in the database along with 
		 * the time when they were co-present. Before you use this API, make sure that you have 
		 * extracted significant places for all the users using the method "extractPlaces" for 
		 * at least the same time frame as used for this API.  
		 * @param userId
		 * 		User Id for the user for whom we find co-present users
		 * @param startTime
		 * 		Start time for search in milliseconds (converted from timestamp object)
		 * @param endTime
		 * 		End time for search in milliseconds (converted from timestamp object) 
		 */
		public void extractCopresentUsers(Long userId, Long startTime, Long endTime)
		{			
			Object[] params =  new Object[] 
			       {
					userId, 
					new Timestamp(startTime), 
					new Timestamp(endTime), 
					userId, 
					new Timestamp(startTime), 
					new Timestamp(endTime), 
					userId, 
					new Timestamp(startTime), 
					new Timestamp(endTime),
					userId, 
					new Timestamp(startTime), 
					new Timestamp(endTime)
			       };
			
			//object containing query parameters
			String query = "INSERT INTO gis_user_time_in_places " +
							"( " +
							"SELECT utp1.userid, utp1.placeid, utp1.starttime, utp1.endtime, utp2.userid, utp1.visit_number " +
							"FROM gis_user_time_in_places utp1, gis_user_time_in_places utp2, gis_user_places up1, gis_user_places up2 " +
							"WHERE utp1.placeid = up1.placeid " +
							"AND utp1.userid = up1.userid " +
							"AND utp1.group_user_id = -1 " +
							"AND utp1.userid = ? " +
							"AND utp2.placeid = up2.placeid " +
							"AND utp2.userid = up2.userid " +
							"AND utp2.group_user_id = -1 " + 
							"AND utp1.userid <> utp2.userid " +
							"AND distance_spheroid(centroid(up1.location), centroid(up2.location),'SPHEROID[\"WGS_1984\",6378137,298.257223563]')<" + maximumDistanceForUserCopresence + " " + 
						  	"AND utp1.endtime - utp2.starttime > '" + timeForSignificantCopresenceCluster + "' "  +
						  	"AND utp2.endtime - utp1.starttime > '" + timeForSignificantCopresenceCluster + "' "  +
						  	"AND utp1.endtime - utp1.starttime > '" + timeForSignificantMainCluster + "' "  +
						  	"AND utp1.starttime >? " +
						  	"AND utp1.endtime <? " +
							"AND utp1.starttime >= utp2.starttime " +
							"AND utp1.endtime <= utp2.endtime " +
					
							"UNION " +
							
							"SELECT utp1.userid, utp1.placeid, utp2.starttime, utp2.endtime, utp2.userid, utp1.visit_number " +
							"FROM gis_user_time_in_places utp1, gis_user_time_in_places utp2, gis_user_places up1, gis_user_places up2 " +
							"WHERE utp1.placeid = up1.placeid " +
							"AND utp1.userid = up1.userid " +
							"AND utp1.group_user_id = -1 " +
							"AND utp1.userid = ? " +
							"AND utp2.placeid = up2.placeid " +
							"AND utp2.userid = up2.userid " +
							"AND utp2.group_user_id = -1 " + 
							"AND utp1.userid <> utp2.userid " +
							"AND distance_spheroid(centroid(up1.location), centroid(up2.location),'SPHEROID[\"WGS_1984\",6378137,298.257223563]')<" + maximumDistanceForUserCopresence + " " + 
						  	"AND utp1.endtime - utp2.starttime > '" + timeForSignificantCopresenceCluster + "' "  +
						  	"AND utp2.endtime - utp1.starttime > '" + timeForSignificantCopresenceCluster + "' "  +
						  	"AND utp1.endtime - utp1.starttime > '" + timeForSignificantMainCluster + "' "  +
						  	"AND utp1.starttime >? " +
						  	"AND utp1.endtime <? " +
						  	"AND utp1.starttime <= utp2.starttime " +
						  	"AND utp1.endtime >= utp2.endtime " +
					
						  	"UNION " + 
					
						  	"SELECT utp1.userid, utp1.placeid, utp1.starttime, utp2.endtime, utp2.userid, utp1.visit_number " +
						  	"FROM gis_user_time_in_places utp1, gis_user_time_in_places utp2, gis_user_places up1, gis_user_places up2 " +
							"WHERE utp1.placeid = up1.placeid " +
							"AND utp1.userid = up1.userid " +
							"AND utp1.group_user_id = -1 " +
							"AND utp1.userid = ? " +
							"AND utp2.placeid = up2.placeid " +
							"AND utp2.userid = up2.userid " +
							"AND utp2.group_user_id = -1 " + 
							"AND utp1.userid <> utp2.userid " +
							"AND distance_spheroid(centroid(up1.location), centroid(up2.location),'SPHEROID[\"WGS_1984\",6378137,298.257223563]')<" + maximumDistanceForUserCopresence + " " + 
						  	"AND utp1.endtime - utp2.starttime > '" + timeForSignificantCopresenceCluster + "' "  +
						  	"AND utp2.endtime - utp1.starttime > '" + timeForSignificantCopresenceCluster + "' "  +
						  	"AND utp1.endtime - utp1.starttime > '" + timeForSignificantMainCluster + "' "  +
						  	"AND utp1.starttime >? " +
						  	"AND utp1.endtime <? " +
						  	"AND utp1.starttime >= utp2.starttime " +
						  	"AND utp1.endtime >= utp2.endtime " +
						  	
						  	"UNION " + 
						  	
						  	"SELECT utp1.userid, utp1.placeid, utp2.starttime, utp1.endtime, utp2.userid, utp1.visit_number " +
						  	"FROM gis_user_time_in_places utp1, gis_user_time_in_places utp2, gis_user_places up1, gis_user_places up2 " +
							"WHERE utp1.placeid = up1.placeid " +
							"AND utp1.userid = up1.userid " +
							"AND utp1.group_user_id = -1 " +
							"AND utp1.userid = ? " +
							"AND utp2.placeid = up2.placeid " +
							"AND utp2.userid = up2.userid " +
							"AND utp2.group_user_id = -1 " + 
							"AND utp1.userid <> utp2.userid " +
							"AND distance_spheroid(centroid(up1.location), centroid(up2.location),'SPHEROID[\"WGS_1984\",6378137,298.257223563]')<" + maximumDistanceForUserCopresence + " " + 
						  	"AND utp1.endtime - utp2.starttime > '" + timeForSignificantCopresenceCluster + "' "  +
						  	"AND utp2.endtime - utp1.starttime > '" + timeForSignificantCopresenceCluster + "' "  +
						  	"AND utp1.endtime - utp1.starttime > '" + timeForSignificantMainCluster + "' "  +
						  	"AND utp1.starttime >? " +
						  	"AND utp1.endtime <? " +
						  	"AND utp1.starttime <= utp2.starttime " +
						  	"AND utp1.endtime <= utp2.endtime " +
						  	
						  	")";
			
			System.out.println(query);
			
			try{
				//Invoke update method on DAO to perform insert
				int rowsUpdated = new SmartCampusDAO().update(query, params);
			}catch(Exception e)
			{
				System.out.println("Error while extracting user copresence");
				e.printStackTrace();
			}
			
		}
		
		/**
		 * This API is used to extract significant places for a user in a given time frame. 
		 * 
		 * @param userId
		 * 		User Id for the user for whom we find co-present users
		 * @param startTime
		 * 		Start time for search in milliseconds (converted from timestamp object)
		 * @param endTime
		 * 		End time for search in milliseconds (converted from timestamp object) 
		 * @param mode
		 *      This is the mode used for storing raw location data. this can be "Centroid"
		 *      or "Fingerprinting" 
		 */
		public void extractPlaces(Long userId, Long startTime, Long endTime, String mode)
		{
			Coordinates C; //variables used to store user position obtained from the database
			Position P; //variables used to store user position obtained from the database
			Object[] params =  new Object[] {userId, new Timestamp(startTime), new Timestamp(endTime), mode}; //object containing query parameters
			Cluster [] traceSegments; //array of trace segments
			
			//get location traces and cluster them
			try{
				String locationSelect="Select * FROM locationinfo_location where userid=? and time >? and time<? and mode=? order by time";
				//Log.put("demoSELECT", locationSelect);

				// Execute fofQuery using SmartCampusDAO and get array of location traces
				locationinfo_location[] locinfo = (locationinfo_location[]) new SmartCampusDAO().query(locationSelect,params,locationinfo_location.class ); 
				//Log.put(locationSelect,locinfo);
						
				//Create an array of segments. Each segment is a cluster of points. Each segment contains "segmentSize" number of location trace points
				traceSegments = new Cluster[((locinfo.length/segmentSize) + 1)*2];
				for(int i=0;i<traceSegments.length;i++)
				{
					traceSegments[i] = new Cluster();
				}
				
				int j=0; //counts the segment number
				int k=1;//counts poistion within the segment. should not exceed segment size
				
				//get position information for the user and feed to the clustering method
				//we are ingoring the first poistion in the calculations. this is just for convinience.
				//will not make a big difference in the final calculations
				for(int i = 1; i <= locinfo.length - 1; i++)
				{
					P = new Position();
					P.setTime(locinfo[i].time);
					C = new Coordinates(locinfo[i].lat,locinfo[i].lon,locinfo[i].height, locinfo[i].floor);
					P.setCoordinates(C);
					
					System.out.println(P.getTime());
					
					//move to the next segments if the previous segment is full. or if the traces are from a different time
					if(k > segmentSize || ((locinfo[i].time.getTime() - locinfo[i-1].time.getTime()) > maxTimeDifferenceBetweenTraces))
					{
						k=1;
						j++;
					}
					
					traceSegments[j].addPosition(P);
					
					k++; //increment k , since a postion is added to segmn
					
					/*add the position to appropriate segments
					if(i==0)
						traceSegments[i].addPosition(P);
					else
						traceSegments[i/segmentSize].addPosition(P);
					*/
				}
				//start clustering the segments
				clusterSegments(traceSegments, userId);
					
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}
		
		
		
		

		
		
		/**
		 * *************************************************************************************************************************
		 * *************************************************************************************************************************
		 * ******************************************Groups/Places Extraction Utility Functions Start Here**********************************
		 * *************************************************************************************************************************
		 * *************************************************************************************************************************
		 */
		
		private void identifyGroupMembers(Long userId, Long placeId, Timestamp startTime, Timestamp endTime)
		{
			float mainUserVisitCount, tempOtherUsersVisitCount;
			int mainUserLocation, tempOtherUsersLocation;
			ngcCounter = 0; //reset the NGC counter
			double EGM; //this store the estimated number of group meetings
			
			try{
				
				//get the position in PGM for userId. this is used later
				mainUserLocation = getUserLocation(userId);
				
				//get the visit count for userId at place placeId between the starttime and endtime
				Object[] params =  new Object[] {
						userId,  
						startTime,
						endTime,
						placeId
				};
				
				String query= "SELECT count(*) AS field " +
							  "FROM gis_user_time_in_places utp, gis_user_places up, gis_space sp " +
							  "WHERE utp.group_user_id=-1 " +
							  "AND utp.userid=? " +
							  "AND utp.starttime>? " +
							  "AND utp.endtime<? " +
							  "AND sp.placeid=? " +
							  "AND contains (sp.location, up.location) " +
							  "AND up.placeid = utp.placeid ";
				
				select_single_long[] dbMainUserVisitCount = (select_single_long[]) new SmartCampusDAO().query(query,params,select_single_long.class); 
				mainUserVisitCount = dbMainUserVisitCount[0].field;
				
				
				//do this processing only if user visit count is greater than MVC
				if(mainUserVisitCount > MVC)
				{
					//estime the number of group meetings
					EGM = mainUserVisitCount/MVC;
					
					query = "SELECT group_user_id AS userid, count(*) AS visitcount " +
							"FROM gis_user_time_in_places utp, gis_user_places up, gis_space sp " +
							"WHERE utp.group_user_id <> -1 " +
							"AND utp.userid =? " +
							"AND utp.starttime>? " +
							"AND utp.endtime<? " +
							"AND sp.placeid=? " +
							"AND contains (sp.location, up.location)" +
							"AND up.placeid = utp.placeid " +
							"GROUP BY utp.group_user_id";
					
					all_users_and_visit_count[] dbAllUsersVisitCount = (all_users_and_visit_count[]) new SmartCampusDAO().query(query,params,all_users_and_visit_count.class );
					 
					//check which users satisfy RCP and add to NGC. we ad initially to NGC since we dont know if they are already in PGM
					for(int i=0; i<dbAllUsersVisitCount.length ; i++)
					{
						tempOtherUsersVisitCount = dbAllUsersVisitCount[i].visitcount;
						if((tempOtherUsersVisitCount/EGM) >= RCP)
						{
							NGC[ngcCounter] =  dbAllUsersVisitCount[i].userid;
							ngcCounter++;
						}
					}
					
					//add users from NGC to PGM (if they are not present in PGM)
					for(int k=0; k<ngcCounter; k++)
					{
						if(getUserLocation(NGC[k]) == -1)
						{
							DGM[dgmCounter][0] = NGC[k];
							DGM[dgmCounter][1] = 0;
							dgmCounter++;
						}
					}
					
					//add edges from userId to all other users from NGC in CPG
					//for all other users in NGC, get thier position and add edge in CPG
					//CPG can be used for analysis later
					for(int m=0; m<ngcCounter; m++)
					{
						tempOtherUsersLocation = getUserLocation(NGC[m]);	
						CPG[mainUserLocation][tempOtherUsersLocation] = 1;
						CPG[tempOtherUsersLocation][mainUserLocation] = 1;
					}
				}
				
				
				//mark userId as processed in PGM
				DGM[mainUserLocation][1] = 1;
				//increment the iterationCount
											 
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		
		/**  
		 *  Given a userid, this method returns the location for a user from PGM. Returns -1 if user not found.
		 * @param userId
		 */
		private int getUserLocation(long userId)
		{
			for(int i=0; i<numberOfUsers; i++)
			{
				if(DGM[i][0] == userId)
				{
					return i;
				}
			}
			return -1;
		}
		
		
		/**
		 * *************************************************************************************************************************
		 * *************************************************************************************************************************
		 * ******************************************Place Extraction Utility Functions Start Here**********************************
		 * *************************************************************************************************************************
		 * *************************************************************************************************************************
		 */
				
		/**
		 * Performs clustering on the segments of user location    
		 *  
		 * @param an array of segments of user position
		 * @return cluster object when one is created  
		 * @throws None
		 */
		private void clusterSegments(Cluster [] traceSegments, Long userId)
		{
			Cluster currentCluster = traceSegments[0];//start adding sements to the cluster
			boolean closeCluster = false; //checks if cluster is to be closed
			int i= 1; //counter
			int nextSegmentToAdd; //check if current segment is processed 
			
			//there could be some empty segments at the end. check if we've reached an empty segment
			while((i<traceSegments.length) && (traceSegments[i].size() != 0))
			{
				//if the new segment is on the same floor and is close to the current cluster add it to the cluster, otherwise close the cluster
				/**
				 * floor calculation is disabled for now
				 * (currentCluster.getCenter().floorDifference(traceSegments[i].getCenter()) == 0)
				 */
				if((currentCluster.getCenter().groundDistance(traceSegments[i].getCenter()) < distanceBetweenClusters))
				{
					currentCluster.addCluster(traceSegments[i]);
					nextSegmentToAdd = -1; //next segment to be added only if cluster is closed
					
					//if it is the last segment, close the cluster. And if the time difference between this and the next segment is greater than "maxTimeDifferenceBetweenTraces" then also close it  
					if(i == (traceSegments.length -1))
					{
						closeCluster = true;
						nextSegmentToAdd = -1; //end of segments. don't need to add next segment
					}	
					else if (traceSegments[i+1].size() == 0)
					{
						closeCluster = true;
						nextSegmentToAdd = -1; //end of segments. don't need to add next segment
					}
					else if(traceSegments[i+1].getStartTime().getTime() - traceSegments[i].getEndTime().getTime() > maxTimeDifferenceBetweenTraces)
					{
						closeCluster = true;
						nextSegmentToAdd = i+1; // add next segment , since this is processed
					}
				}
				else
				{
					closeCluster = true;
					nextSegmentToAdd = i; //add this segment, since it wasnt processed
				}
				
				//check if time duration of cluster is significant, identify it as a place
				if(closeCluster == true)
				{
					if (currentCluster.durationOfCluster() > timeForSignificantCluster)
					{
						System.out.println("Found a Place");
						System.out.println("The coordinates are: Latitude =" + currentCluster.getCenter().getLatitude() + "  Longitude =" + currentCluster.getCenter().getLongitude());
						storePlace(currentCluster, userId);
					}
					currentCluster.clearCluster();
					//add next segment to cluster
					if(nextSegmentToAdd != -1)
					{
						currentCluster = traceSegments[nextSegmentToAdd];
						i = nextSegmentToAdd; //increment i appropriately
					}
					closeCluster = false;
				}
				//increment counter
				i++;
			}
		}
		
		
		/**
		 * Stores user places in the database. A place extracted from 
		 * user traces is compared with the places already stored for 
		 * the user. Then using a complex algorithm the place is either
		 * stored as a new place. Or merged with an existing place
		 * 
		 * @param newPlace, userId
		 * 			cluster representing the new place. User Id of 
		 * 			the user for whom the place is extracted
		 * @reurns none
		 * @throws IOException
		 */
		private void storePlace(Cluster newPlace, Long userId)
		{
			//vaiables to find closest place to the newPlace
			Coordinates tempPlace = new Coordinates(0.0,0.0,0.0,0);
			Coordinates closestPlace =  new Coordinates(0.0,0.0,0.0,0);
			int closestPlaceIndex = 0; //index in result set for closest place
			long maxPlaceId = -1; //this is used to store max exisitng place id for a user (obtained from db)
			String getMaxPlaceId;
			PGgeometry tempPGGeometry;
        	Geometry tempGeometry;
        	Point tempPoint;
			
			
			int rowsUpdated;//for databse logging
			Object[] params1 =  new Object[] {userId}; //object containing query parameters
			
			try{
				String placeSelect="Select * FROM gis_user_places WHERE userid=?";
				//Log.put("demoSELECT", placeSelect);

				// Execute Query using SmartCampusDAO and get array of user places
				gis_user_places[] userPlaces = (gis_user_places[]) new SmartCampusDAO().query(placeSelect,params1,gis_user_places.class ); 
				//Log.put(locationSelect,locinfo);
				
				
				//find the place closest to this place. and get max placeid
				for(int i= 0; i< userPlaces.length; i++){

					tempPGGeometry = userPlaces[i].location;
	            	tempGeometry = tempPGGeometry.getGeometry();
	            	tempPoint = tempGeometry.getFirstPoint();
	            	
	            	
	            	tempPlace.setLatitude(tempPoint.getY());
					tempPlace.setLongitude(tempPoint.getX());
					//tempPlace.setAltitude(tempPoint.getZ());
					tempPlace.setFloor(userPlaces[i].floor);
					
					
					//find the closest place. We only look at places on the same floor
					if(newPlace.getCenter().floorDifference(tempPlace) == 0)
					{
						if(newPlace.getCenter().groundDistance(tempPlace) < newPlace.getCenter().groundDistance(closestPlace))
						{
							closestPlaceIndex = i;
							//closestPlace.setAltitude(userPlaces[i].height);
							closestPlace.setLatitude(tempPlace.getLatitude());
							closestPlace.setLongitude(tempPlace.getLongitude());
							closestPlace.setFloor(tempPlace.getFloor());
						}
					}
				}
				
				
				
			//---------------------------------------------------------------------------
			//Perform operation based on distance to the closest place
			//---------------------------------------------------------------------------
				/**
				 * This section is used to get the maximum place id for a user. 
				 * 
				 * We will use a cheap trick here because of issues with our DAO.
				 * 
				 * Basically if max(placeid) is null then the next query throws an error.
				 * Therefore we catch that and set maxplaceid to = 0
				 * Else we set it to the value obtained from the db
				 */
				
				select_max[] maxPlaceIdDB = null; //this stores maximum place id from database
				try{
						//get the maximum place id from the database, the new place id should be one more
						getMaxPlaceId ="SELECT max(placeid) as max FROM gis_user_places WHERE userid=?";
						// Execute Query using SmartCampusDAO and get array of user places
						maxPlaceIdDB = (select_max[]) new SmartCampusDAO().query(getMaxPlaceId,params1,select_max.class ); 
				}catch(Exception e)
				{
					maxPlaceId = 0;
				}
				
				//now check if max place id was set in the catch block. if not then set it to max place id obtained from database 
				if(maxPlaceId !=0)
				{
					if(maxPlaceIdDB.length != 0)
					{
						maxPlaceId = maxPlaceIdDB[0].max;  
					}
				}
				
				//if distance for closest place is greater the "distanceForNewPlace", insert a new place
				if(newPlace.getCenter().groundDistance(closestPlace) > distanceForNewPlace)
				{
					insertNewPlace(newPlace,userId, new Long(maxPlaceId + 1));
				}		
				//if distance to closest places is less than "distanceForSamePlace", then merge wih closest place
				else
				{
					mergePlaces(newPlace, userPlaces[closestPlaceIndex], userId);
				}
												
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		/**
		 * Function to merge an new place with an existing place in database
		 * 
		 * @param newPlace, existingPlace, userId
		 * @return none
		 * @throws IOException, DAOException
		 * 
		 */
		private void mergePlaces(Cluster newPlace, gis_user_places existingPlace, Long userId)
		{
			int rowsUpdated;
			
			try{
				//****************************************************************************
				//Update the location for closest place. Perform weighted mean with new values
				//****************************************************************************
						PGgeometry existingPlacePGGeometry = existingPlace.location;
						Geometry existingPlaceGeometry = existingPlacePGGeometry.getGeometry();
						Point existingPlaceTempPoint = existingPlaceGeometry.getFirstPoint();
				
						int numberOfNewPoints = newPlace.size();
						int numberOfOldPoints = existingPlace.number_of_points;
												
						double newLat = ((newPlace.getCenter().getLatitude() * numberOfNewPoints) + (existingPlaceTempPoint.getY() * numberOfOldPoints)) / (numberOfNewPoints + numberOfOldPoints);
						double newLon = ((newPlace.getCenter().getLongitude() * numberOfNewPoints) + (existingPlaceTempPoint.getX() * numberOfOldPoints)) / (numberOfNewPoints + numberOfOldPoints);
										
						PGgeometry newPlacePoint = new PGgeometry();
						newPlacePoint.setType("POINT");
						newPlacePoint.setGeometry(PGgeometry.geomFromString("POINT ("
								+ new Double(newLon) + " " + new Double(newLat) + ")"));
						
						//Array of parameter values (primtives should wrapped in objects)
						Object[] params4 =  new Object[] {
							newPlacePoint.getGeometry(),
							new Integer(numberOfNewPoints + numberOfOldPoints),
							userId,
							new Long(existingPlace.placeid)
						};
						
						//Update table user_places
						String placesUpdate="UPDATE gis_user_places SET location=GeomFromText(?), number_of_points =?  WHERE userid=? and placeid=?";
						//Log.put("demoUPDATE", placesUpdate);
						
						// Invoke update method on DAO to perform insert
						rowsUpdated = new SmartCampusDAO().update(placesUpdate, params4);
						//Log.put("rowsUpdate", "="+rowsUpdated);
					
				
				//******************************************************
			    //Insert time for the new cluster
				//******************************************************
						int thisVisitNumber = -1; //visit number at the same place
						
						//Get the last visit number to the place and increment it
						Object[] params5 =  new Object[] {userId, new Long (existingPlace.placeid)};
						
						
						/**
						 * This section is used to get the last visit number for a user. 
						 * 
						 * We will use a cheap trick here because of issues with our DAO.
						 * 
						 * Basically if max(visit_number) is null then the next query throws an error.
						 * Therefore we catch that and set visit number to = 0
						 * Else we set visit number to the one obtained from the db
						 */
						Database_Count[] lastVisitNumber= null;
						
						try{
							String lastVisitNumberSelect="SELECT max(visit_number) as count FROM gis_user_time_in_places WHERE userid=? and placeid =?";
							//Log.put("demoSELECT", locationSelect);

							// Execute fofQuery using SmartCampusDAO and get array of location traces
							lastVisitNumber = (Database_Count[]) new SmartCampusDAO().query(lastVisitNumberSelect,params5,Database_Count.class ); 
							//Log.put(locationSelect,locinfo);							
						}catch(Exception e)
						{
							thisVisitNumber = 1;
						}

						//set the correct visit number for the new visit. 
						if(thisVisitNumber != 1)
						{
							if(lastVisitNumber.length != 0)
							{
								thisVisitNumber = lastVisitNumber[0].count + 1;
							}
						}
						
						String timeInsert2="INSERT INTO gis_user_time_in_places(userid, placeid, starttime, endtime, group_user_id, visit_number) VALUES (?,?,?,?,?,?)";
						//Log.put("demoINSERT", placeInsert);
						
						// Array of parameter values (primtives should wrapped in objects)
						Object[] params6 =  new Object[] {
							userId,  
							new Long (existingPlace.placeid),
							newPlace.getStartTime(),
							newPlace.getEndTime(),
							new Long(-1),
							new Long(thisVisitNumber)
						};
						
						//Invoke update method on DAO to perform insert
						rowsUpdated = new SmartCampusDAO().update(timeInsert2, params6);
			
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		/**
		 * Function to insert new place in the databse
		 * @param  newPlace, userid , placeid
		 * 		cluster representing the new place, user for whom place is store and 
		 * 		a unique id for the new place
		 * @return none
		 * @throws IOException, DAOException
		 */
		private void insertNewPlace(Cluster newPlace, Long userId, Long placeId)
		{
			int rowsUpdated;
			
			try{
			//********************************************************
			//Insert A new Place in locationinfo_userPlaces
			//******************************************************
					PGgeometry newPlacePoint = new PGgeometry();
					newPlacePoint.setType("POINT");
					newPlacePoint.setGeometry(PGgeometry.geomFromString("POINT ("
							+ new Double(newPlace.getCenter().getLongitude()) + " " + new Double(newPlace.getCenter().getLatitude()) + ")"));
				
					String placeInsert="INSERT INTO gis_user_places (userid, placeid, userlabel, location, height, floor, number_of_points) VALUES(?,?,?,GeomFromText(?),?,?,?)";
					//Log.put("demoINSERT", placeInsert);
					
					// Array of parameter values (primtives should wrapped in objects)
					Object[] params2 =  new Object[] {
						userId,  
						placeId,
						"No Label",
						newPlacePoint.getGeometry(),
						new Double(newPlace.getCenter().getAltitude()),
						new Integer(newPlace.getCenter().getFloor()),
						new Integer(newPlace.size())
					};
					
					//Log.put("demoINSERT.params", params);
					// Invoke update method on DAO to perform insert
					rowsUpdated = new SmartCampusDAO().update(placeInsert, params2);
					//Log.put("rowsUpdated", "="+rowsUpdated);
						
			//******************************************************
		    //Insert time for the new place
			//******************************************************
					String timeInsert="INSERT INTO gis_user_time_in_places(userid, placeid, starttime, endtime, group_user_id, visit_number) VALUES (?,?,?,?,?,?)";
					//Log.put("demoINSERT", placeInsert);
					
					// Array of parameter values (primtives should wrapped in objects)
					Object[] params3 =  new Object[] {
						userId,  
						placeId,
						newPlace.getStartTime(),
						newPlace.getEndTime(),
						new Long(-1),
						new Long(1)
					};
					
					//Log.put("demoINSERT.params", params);
					// Invoke update method on DAO to perform insert
					rowsUpdated = new SmartCampusDAO().update(timeInsert, params3);
					//Log.put("rowsUpdated", "="+rowsUpdated);
			
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		

		/**
		 * *************************************************************************************************************************
		 * *************************************************************************************************************************
		 * ******************************************Test Functions Start Here**********************************
		 * *************************************************************************************************************************
		 * *************************************************************************************************************************
		 */
		
//		
//		/**
//		 * Driver Function for the testing the service
//		 * @param args
//		 */
//		public static void main(String[] args){
//				DataMiningService p  = new DataMiningService();
//				
//				//get the userId, Mode and TimeDuration for which the process is to be run
//				Long userId = new Long(1248);
//				Timestamp startTime = Timestamp.valueOf("2007-07-01 00:00:00");
//				Timestamp endTime = Timestamp.valueOf("2007-07-10 00:00:00");
//				String mode = "Centroid";
//				
//				
//				//extract places and store them in the database
//				p.extractPlaces(userId, startTime, endTime, mode);
////				p.extractCopresentUsers(userId, startTime, endTime);
//			}
}
