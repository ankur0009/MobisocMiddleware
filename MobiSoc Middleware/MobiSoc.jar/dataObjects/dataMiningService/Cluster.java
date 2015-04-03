package dataObjects.dataMiningService;
import java.util.*;
import java.sql.Timestamp;

/**
 *   Creates a cluster of user position and provides the following methods
 *   isEmpty --> check if cluster is empty
 *   clearCluster --> clears the cluster
 *   getCenter -->  gives coordinates for centre of the cluster
 *   addPosition --> Adds a position to the cluster
 *   durationOfCluster --> returns duration of cluster in seconds 
*/

public class Cluster {

	//stores positions
	private ArrayList clst = new ArrayList();
	
	
	/**
	 * Gives the size of the cluster. ie the number of points in it
	 * @param none
	 * @return number of points in cluster 
	 * @throws none
	 * 
	 */
	public int size()
	{
		return clst.size();
	}
	
	
	/**
	 * Gives the start time of the cluster
	 * @param none
	 * @return timestamp for start time
	 * @throws none
	 */
	public Timestamp getStartTime()
	{
		return ((Position)clst.get(0)).getTime();
	}
	
	/**
	 * Gives the end time of the cluster
	 * @param none
	 * @return timestamp for end time
	 * @throws none
	 */
	public Timestamp getEndTime()
	{
		return ((Position)clst.get(clst.size()-1)).getTime();
	}
	
	/**
	 * Gives duration of cluster in seconds. Duration is the difference
	 * in seoncds between the first position and the last position in 
	 * the cluster  
	 *  
	 * @param none
	 * @return duration in seconds 
	 * @throws none
	 */	
	public long durationOfCluster()
	{
		Timestamp startTime, endTime;
		
		startTime = ((Position)clst.get(0)).getTime();
		endTime = ((Position)clst.get(clst.size()-1)).getTime();
		
		System.out.println("Duration of Cluster is: " + ((endTime.getTime() - startTime.getTime())/1000));
		return ((endTime.getTime() - startTime.getTime())/1000);	
	}
	
	/**
	 * Add a point to the cluster  
	 *  
	 * @param positionToAdd
	 *            the user position
	 * @return None 
	 * @throws java.lang.NullPointerException
	 *             if the parameter is null
	 */
	public void addPosition(Position positionToAdd)
	{
		clst.add(positionToAdd);
	}
	
	/**
	 * Add a cluster to the existing cluster  
	 *  
	 * @param cluster to add to the existing cluster	 *       
	 * @return None 
	 * @throws java.lang.NullPointerException
	 *             if the parameter is null
	 */
	public void addCluster(Cluster clusterToAdd)
	{
		for(int i=0;i<clusterToAdd.size();i++)
		{
			this.addPosition((Position)clusterToAdd.clst.get(i));
		}
	}
	
	/**
	 * Clears the cluster  
	 *  
	 * @param None
	 * @return None 
	 * @throws java.lang.NullPointerException
	 *             if the parameter is null
	 */
	public void clearCluster()
	{
		clst.clear();
	}
	
	 
	/**
	 * Check if cluster is empty  
	 *  
	 * @param None
	 * @return Ture if cluster is empty  
	 * @throws None
	 */
	public boolean isEmpty()
	{
		return clst.isEmpty();
	}
	
	
	/**
	 * Calculates center of the cluster. The center is the average
	 * longitude and latitude of all positions in the cluster  
	 *  
	 * @param None
	 * @return centre point of cluster  
	 * @throws None
	 */
	public Coordinates getCenter()
	{
		//total floor, latitude and longitude of all points
		double totalLat = 0.0;
		double totalLon = 0.0;
		double totalHeight = 0; 
		int maxFloor=0;
		int []floorCount = new int[]{0,0,0,0,0,0,0,0,0,0}; //this is used to count the number of reading for each floor
		
		//Calculate the sum of all logitudes and latitudes of all points. the average of these values give the centre point
		//to calculate the floor, find the floor reading the occurs the maximum number of times. set that reading as the floor
		for(int i=0; i < clst.size(); i++)
		{
			totalLat = totalLat + (((Position)clst.get(i)).getCoordinates()).getLatitude();
			totalLon = totalLon + (((Position)clst.get(i)).getCoordinates()).getLongitude();
			totalHeight = totalHeight + (((Position)clst.get(i)).getCoordinates()).getAltitude();
			floorCount[(((Position)clst.get(i)).getCoordinates()).getFloor()]++;
		}

		
		//find the floor with the maximum count
		for(int j=1;j<floorCount.length;j++)
		{
			if (floorCount[j] > floorCount[maxFloor])
			{
				maxFloor = j;
			}
		}
		
		//the latitude and longitude of the center points are given by their average
		//the height is calculate the same way for now. this will not work good in practice and has to be changed
		//the floor is the floor with the maximum count		
		Coordinates centerPoint  = new Coordinates(totalLat/clst.size(), totalLon/clst.size(),Math.round(totalHeight/clst.size()),maxFloor);
		
		return centerPoint;
	}
	
	
	/**
	 *auxiliary function displays the contents of the cluster.
	 *time and location
	 *primarily used for testing
	 * 
	 */
	public void showCluster()
	{
		for(int i=0; i < clst.size(); i++)
		{
			System.out.print((((Position)clst.get(i)).getCoordinates()).getLatitude() + "  ") ;
			System.out.print((((Position)clst.get(i)).getCoordinates()).getLongitude() + "  ");
			System.out.println((((Position)clst.get(i)).getTime()).getTime());
		}
			
	}

	
	
}
