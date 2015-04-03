package dataObjects.common;

import java.util.*;

public class TBTriggerList {
	private ArrayList TBTriggers;
	
	public TBTriggerList()
	{
		TBTriggers = new ArrayList();
	}
	
	/**
	 * Note: ArrayList indices start at 0
	 * @return returns the number of existing counters
	 * 
	 */
	
	//returns the number of timers in the arraylist
	public int timerCount()
	{
		return TBTriggers.size();
	}
	
	//add timer ot the arraylist
	public void addTimer(Timer X)
	{
		TBTriggers.add(X);
	}
	
	/**
	 * 
	 * @param X
	 * @return timer at position X
	 */
	public Timer getTimer(int X)
	{
		return (Timer)TBTriggers.get(X); 
	}
	
	
	/**
	 * 
	 * @param X
	 * 		remove timer at position X
	 * @return
	 *      timer that was remove from the list
	 */
	public Timer removeTimer(int X)
	{
		return (Timer)TBTriggers.remove(X);
	}
}
