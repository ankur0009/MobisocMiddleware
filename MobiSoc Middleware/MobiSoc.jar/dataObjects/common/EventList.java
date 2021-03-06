package dataObjects.common;

/**
 * 
 * @author Maverick
 * 
 * Main list, where events are stored or removed as they arrive
 */


import java.util.*;

public class EventList {

	private Hashtable events;
	
	//constructor
	public EventList()
	{
		events = new Hashtable();
	}
	
	/**
	 * 
	 * @param X
	 * @return 
	 *   returns an enumersted list of events
	 */
	public Enumeration getEventList()
	{
		return events.elements();
	}
	
	
	//add event to the hashtable
	public void add_event(Event X)
	{
		Long ID = new Long(X.getEventId());
		events.put(ID, X);
	}
	
	//remove event
	public void remove_event(long id)
	{
		Long ID = new Long(id);
		events.remove(ID);
	}
	
	/**
	 * 
	 * @return
	 *   number of event in the list
	 */
	public int getEventCount()
	{
		return events.size();
	}
	
	
	//retrieve event
	public Event get_event(long id)
	{
		Long ID = new Long(id);
		return (Event)events.get(ID);
	}
}
