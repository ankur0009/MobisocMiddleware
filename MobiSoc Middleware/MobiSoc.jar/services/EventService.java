package services;
import java.sql.Timestamp;
import java.util.*;

import ksoapDataObjects.common.KVMAlerts;
import ksoapDataObjects.common.KVMCBTrigger;
import ksoapDataObjects.common.KVMCoordinates;
import ksoapDataObjects.common.KVMEvent;
import ksoapDataObjects.common.KVMEventData;

import dataObjects.common.CBTrigger;
import dataObjects.common.Coordinates;
import dataObjects.common.Event;
import dataObjects.common.EventList;
import dataObjects.common.UserLocationTime;
import dataObjects.common.ed_cb_trigger;
import dataObjects.common.ed_event_main;
import dataObjects.common.ed_place_list;
import dataObjects.common.ed_user_list;
import dataObjects.common.locationinfo_location;
import edu.njit.sc.core.dao.SmartCampusDAO;


/**
 * This service provides the API for (Event Manager) as described in the MobiSoc paper/documentation.
 * Due to restrictions imposed by KSOAP on the type of dataobects that can be exchanged, for ceratin methods
 * we provide two versions of the API. One that works with KSOAP and the other one works generally. If we do
 * not provide a specific KSOAP version, then the method works with KSOAP as well as in the general case.
 * The parameters that guide event service such as the frequency of checking location constraints can be
 * changed in the class definition. The parameters are preset to optimal values.
 * @author Maverick
 */
public class EventService {

	//Counter for assiginig event id to new events
	private long EventIDCounter;
	private EventList MainEventList, LocationConstraintsList;  
	private Hashtable FiredEventList;
	private static int frequencyForCheckingLocationConstraints = 15000; //number of milliseconds after which location constraints are checked again
	private static int locationFreshness = 180000; //user location data older than this time is not considered when checking location/colocation based constraints

	
	/**
	 * Constructor
	 */
	public EventService()
	{ 
		EventIDCounter = 1;
		MainEventList = new EventList(); //compelete list of events
		LocationConstraintsList = new EventList(); //list of events to be checked for location constraints
		FiredEventList = new Hashtable(); //list of fired events
		
		//retrieve events from the database and regiser them
		//retrieveAndRegisterEvents();
		
		//schedule a timer for checking location constraints. This is run at frequency given by frequencyForCheckingLocationConstraints 
		LocationConstraintsTask checkLocationConstraintsTask = new LocationConstraintsTask();
		Timer checkLocationConstraintsTimer = new Timer();
		checkLocationConstraintsTimer.schedule(checkLocationConstraintsTask, new Timestamp(System.currentTimeMillis() + 10000), frequencyForCheckingLocationConstraints);
	}
	
	
	
	/**
	**************************************************************************************************************************
	**************************************************************************************************************************
	**************************************************************************************************************************
	**************************************************************************************************************************
	**************************************************************************************************************************
	* Event Dispatcher API starts here
	**************************************************************************************************************************
	**************************************************************************************************************************
	**************************************************************************************************************************
	**************************************************************************************************************************
	*/
		
		
		/**
		 * This API registers the given event with the event dispatcher. 
		 * @param X
		 *    Event to be registered
		 * @author Maverick
		 */
		public synchronized void registerEvent(Event X)
		{
			registerEvent(X, true);
		}
		
		
		/**
		 * This is a KSOAP compatible wrapper for the Main API. 
		 * It registers the given event with the event dispatcher.   
		 * @param X
		 *    KVM event to be registered
		 * @author Maverick
		 */
		public synchronized String registerEvent(KVMEvent X)
		{
			Event newEvent = new Event();
			CBTrigger newCBTrigger = new CBTrigger();
			ArrayList<Coordinates> listOfPlaces = new ArrayList<Coordinates>();
			ArrayList<Long> cbTargetUsers = new ArrayList<Long>();
			ArrayList<Long> mainTargetUsers = new ArrayList<Long>();
			 
			/**
			 * This section converts KVM event to an event
			 * 
			 */
			
			try{
				//***********Convert the trigger******************
			
				//Create an arraylist of places for location constraints in CBTrigger
				if (X.getCBTrigger().getListOfPlaces() != null)
				{
					Coordinates newPlace;		
					Vector XlistOfPlaces = X.getCBTrigger().getListOfPlaces();
					for(int i=0; i< XlistOfPlaces.size(); i++)
					{
						KVMCoordinates oldPlace = (KVMCoordinates)XlistOfPlaces.elementAt(i);
						newPlace = new Coordinates(Double.valueOf(oldPlace.getLat()).doubleValue(),Double.valueOf(oldPlace.getLon()).doubleValue(), Float.valueOf(oldPlace.getAlt()).floatValue());
						listOfPlaces.add(newPlace);
					}
				}
				
				if(X.getCBTrigger().getTargetUsers() != null)
				{
					//Create an arraylist of target users for colocation constraints
					Vector XtargetUsers = X.getCBTrigger().getTargetUsers();
					for(int i=0; i< XtargetUsers.size(); i++)
					{
						cbTargetUsers.add((Long)XtargetUsers.elementAt(i));
					}
				}
				
				//Add data into the CBTrigger
				if(X.getCBTrigger().getColocationConstraints() != null)
					newCBTrigger.setColocationConstraints(X.getCBTrigger().getColocationConstraints().booleanValue());
				
				if(X.getCBTrigger().getDate() != null)
					newCBTrigger.setDate(new Timestamp(X.getCBTrigger().getDate().longValue()));
				
				if(X.getCBTrigger().getDistanceToPlaces() != null)
					newCBTrigger.setDistanceToPlaces(X.getCBTrigger().getDistanceToPlaces().intValue());
				
				if(X.getCBTrigger().getDistanceToUsers() != null)
					newCBTrigger.setDistanceToUsers(X.getCBTrigger().getDistanceToUsers().intValue());
				
				if(X.getCBTrigger().getEndTime() != null)
					newCBTrigger.setEndTime(new Timestamp(X.getCBTrigger().getEndTime().longValue()));
				
				if(X.getCBTrigger().getFrequency() != null)
					newCBTrigger.setFrequency(X.getCBTrigger().getFrequency());
				
				if(X.getCBTrigger().getLastFire() !=null)
					newCBTrigger.setLastFire(new Timestamp(X.getCBTrigger().getLastFire().longValue()));
				
				if(X.getCBTrigger().getListOfPlaces() != null)
					newCBTrigger.setListOfPlaces(listOfPlaces);
				
				if(X.getCBTrigger().getLocationConstraints() != null)
					newCBTrigger.setLocationConstraints(X.getCBTrigger().getLocationConstraints().booleanValue());
				
				if(X.getCBTrigger().getSourceUser() != null)
					newCBTrigger.setSourceUser(X.getCBTrigger().getSourceUser().longValue());
				
				if(X.getCBTrigger().getStartTime() != null)
					newCBTrigger.setStartTime(new Timestamp(X.getCBTrigger().getStartTime().longValue()));
				
				if(X.getCBTrigger().getTargetUsers() != null)
					newCBTrigger.setTargetUsers(cbTargetUsers);
				
				if(X.getCBTrigger().getTimeConstraints() != null)
					newCBTrigger.setTimeConstraints(X.getCBTrigger().getTimeConstraints().booleanValue());
				
				if(X.getCBTrigger().getWeekDay() != null)
					newCBTrigger.setWeekDay(X.getCBTrigger().getWeekDay());
				
				
				//**** Convert the main event **********
				
				//getan arraylist of target users
				if(X.getTargetUsers() != null)
				{
					//Create an arraylist of target users for colocation constraints
					Vector XtargetUsers = X.getTargetUsers();
					for(int i=0; i< XtargetUsers.size(); i++)
					{
						mainTargetUsers.add((Long)XtargetUsers.elementAt(i));
					}
				}
					
				
				if(X.getCBTrigger() != null)
					newEvent.setCBTrigger(newCBTrigger);
				
				if(X.getDescription() != null)
					newEvent.setDescription(X.getDescription());
				
				if(X.getEventId() != null)
					newEvent.setEventId(X.getEventId().longValue());
				
				if(X.getIsOnLocationConstraintsList() != null)
					newEvent.setIsOnLocationConstraintsList(X.getIsOnLocationConstraintsList().booleanValue());
				
				if(X.getRegistrationTime() != null)
					newEvent.setRegistrationTime(new Timestamp(X.getRegistrationTime().longValue()));
				
				if(X.getServiceApi() != null)
					newEvent.setServiceApi(X.getServiceApi());
				
				if(X.getServiceName() != null)
					newEvent.setServiceName(X.getServiceName());
				
				if(X.getSourceUser() != null)
					newEvent.setSourceUser(X.getSourceUser().longValue());
				
				if(X.getTargetUsers() != null)
					newEvent.setTargetUsers(mainTargetUsers);
				
				if(X.getType() != null)
					newEvent.setType(X.getType());
				
			
				registerEvent(newEvent, true);
				return "Too Goooood";
			
			}catch(Exception e)
			{
				e.printStackTrace();
				return "Bad";
			}
			
		}
		
		
		/**
		 * This API deletes the event from the event dispatcher. 
		 * @param eventId
		 *    Event Id of the event to be deleted
		 * @author Maverick
		 */
		public synchronized void deleteEvent(Long eventId)
		{
			//remove the event from the database
			//deleteEventFromDatabase(eventId);
		
			//remove it from the main event list, and location constraints list
			MainEventList.remove_event(eventId);
			LocationConstraintsList.remove_event(eventId);	
		}
		
		
		
		/**
		 * Returns an event stored in the event dispatcher. Note that we do not provide
		 * a KSOAP wrapper for this API as it is an auxiliary API. A wrapper can be provided
		 * on demand.
		 * @param eventId
		 * 		Event Id of the event to be returned
		 * @return
		 * 		Event details for the event contained in Event and CBTrigger class
		 * @author Maverick 
		 */
		public Event retrieveEvent(Long eventId)
		{
			try{
				return (Event)MainEventList.get_event(eventId);
			}catch(Exception e)
			{
				System.out.print("Invalid Event ID");
				e.printStackTrace(); 
				return null;
			}
		}
		
		/**
		 * This API returns events stored for a particular user. Note that we do not provide
		 * a KSOAP wrapper for this API as it is an auxiliary API. A wrapper can be provided
		 * on demand.
		 * @param userId
		 * 		User Id for the user whose events are returned
		 * @return
		 * 		ArrayList of user events contained in Event and CBTrigger classes
		 * @author Maverick
		 */
		public ArrayList retieveUserEvents(Long userId)
		{
			ArrayList userEvents = new ArrayList();
			Enumeration allEvents = MainEventList.getEventList();
			Event currentEvent;
			
			while(allEvents.hasMoreElements())
			{
				currentEvent = (Event)allEvents.nextElement();
				if(currentEvent.getSourceUser() == userId)
				{
					userEvents.add(currentEvent);
				}
			}
			
			return userEvents;
		}
		
		
		/**
		 * This API returns an object contaning fired events for the user
		 * @param userId
		 * 		User Id for the user for which alerts are to be fetched
		 * @return
		 * 		An object (KVMAlerts) that contains all the fired events for the user
		 * @author Maverick
		 */
		public KVMAlerts getAlerts(Long userId)
		{
			//Get Alerts object from the fired events list
			KVMAlerts X = (KVMAlerts)(FiredEventList.get(userId));
			//Remove the object from the list
			FiredEventList.remove(userId);
			
			//Return the object
			if(X!= null)
				return X;
			else
				return null;
		}
	
	
		/**
		**************************************************************************************************************************
		**************************************************************************************************************************
		**************************************************************************************************************************
		**************************************************************************************************************************
		**************************************** Event dispatcher API ends here **************************************************
		**************************************************************************************************************************
		**************************************************************************************************************************
		**************************************************************************************************************************
		**************************************************************************************************************************
		*/	
		
		
		/**
		 * @author Maverick
		 * 
		 * Process the time and frequency constrains of the trigger.
		 * This works as follows.
		 * 
		 * We first check the frequency of the trigger. 
		 *  1. If frequency is one time
		 *     		** We assume that event has not been fired before.
		 *     		** Next we check the date for the event. 
		 *     		** Next we check the startTime. If startTime is 
		 *     		   given, we shedule a timer based on the date and 
		 *     		   startTime and put it on the "TBFireTrigger" list.  
		 *     		   If startTime is not given, we schedule the timer 
		 *     		   at 12:00 AM on the given date.
		 *      	** Next we check the endTime, if the endTime is given,
		 *      	   we schedule a timer at the given endTime and the given
		 *      	   date and put it on the "TBStopTrigger" list. Else we 
		 *      	   schedule it at 12:00 AM on the next date.    
		 *  2. If frequency is every day
		 * 			** We first check the date for the last fire. If
		 * 			   not given, then we set the last fire to the current
		 *  		   date. We set the date for next fire as the day 
		 *  		   next to last fire.
		 *  		** Next we check the startTime. If startTime is 
		 *     		   given, we shedule a timer based on the date and 
		 *     		   startTime and put it on the "TBFireTrigger" list.  
		 *     		   If startTime is not given, we schedule the timer 
		 *     		   at 12:00 AM on the given date.
		 *  		** Next we check the endTime, if the endTime is given,
		 *      	   we schedule a timer at the given endTime and the given
		 *      	   date and put it on the "TBStopTrigger" list. Else we 
		 *      	   schedule it at 12:00 AM on the next date. 		     
		 *  3. If frequency is once a week
		 *  		** We check the date for the last fire. 
		 *  				** If not given, we need to compute the date for the 
		 *  				next fire. We check the weekday provided and the 
		 *  				date is the next time we have that day.
		 *  				** Else the date for the next fire is 7 days after the 
		 *  				last fire.
		 *    		** Next we check the startTime. If startTime is 
		 *     		   given, we shedule a timer based on the date and 
		 *     		   startTime and put it on the "TBFireTrigger" list.  
		 *     		   If startTime is not given, we schedule the timer 
		 *     		   at 12:00 AM on the given date.
		 *  		** Next we check the endTime, if the endTime is given,
		 *      	   we schedule a timer at the given endTime and the given
		 *      	   date and put it on the "TBStopTrigger" list. Else we 
		 *      	   schedule it at 12:00 AM on the next date. 	 	  
		 *  4. If frequency is once a year
		 *  		** We first check the year for last fire. If this is given
		 *  		   then the year for last fire is the year after this. Else
		 *  		   it is the current year.
		 *  		** Next we check the date for the event. 
		 *     		** Next we check the startTime. If startTime is 
		 *     		   given, we shedule a timer based on the date and 
		 *     		   startTime and put it on the "TBFireTrigger" list.  
		 *     		   If startTime is not given, we schedule the timer 
		 *     		   at 12:00 AM on the given date.
		 *      	** Next we check the endTime, if the endTime is given,
		 *      	   we schedule a timer at the given endTime and the given
		 *      	   date and put it on the "TBStopTrigger" list. Else we 
		 *      	   schedule it at 12:00 AM on the next date
		 *   						  
		 */
		private void processTimeFrequencyConstraints(Event X)
		{
			String freq, WeekDay;
			GregorianCalendar cal;
			int fireYear, fireMonth, fireDate, fireHour, fireMinutes, fireSeconds;  
			int currYear, currMonth, currDate, currHour, currMinutes, currSeconds; 
			
			Timestamp TimeForFire, TimeForStop, LastFire;
			TBFireTask FireTask; 
			TBStopTask StopTask;
			Timer FireTimer, StopTimer;
				
			//get current year, month, date, hour, minutes, seconds;
			cal  = new  GregorianCalendar();
			currYear = cal.get(Calendar.YEAR);
			currMonth = cal.get(Calendar.MONTH);
			currDate = cal.get(Calendar.DATE);
			currHour = cal.get(Calendar.HOUR_OF_DAY);
			currMinutes = cal.get(Calendar.MINUTE);
			currSeconds = cal.get(Calendar.SECOND);
					
			//get the desired frequency for the event
			freq = X.getCBTrigger().getFrequency();
			
			
			
			//schedule timers based on frequency setting
			if(freq.equals("onetime"))
			{
				//get the date
				if(X.getCBTrigger().getDate() != null)
				{
					cal.setTimeInMillis(X.getCBTrigger().getDate().getTime());
					fireYear = cal.get(Calendar.YEAR);
					fireMonth = cal.get(Calendar.MONTH);
					fireDate = cal.get(Calendar.DATE);
				}
				else
				{
				//else set the trigger for the next date
					fireYear = currYear;
					fireMonth = currMonth;
					fireDate = currDate + 1;
				}
				
				//get the startTime on the given date and the schedule a timer accordingly
				if(X.getCBTrigger().getStartTime() != null)
				{
					cal.setTimeInMillis(X.getCBTrigger().getStartTime().getTime());
					fireHour = cal.get(Calendar.HOUR_OF_DAY);
					fireMinutes = cal.get(Calendar.MINUTE);
					fireSeconds = cal.get(Calendar.SECOND);
				}
				else
				{
					fireHour = 0;
					fireMinutes = 0;
					fireSeconds = 0;
				}
				
			
				
				//create a timer 
				//create time for fire
				cal.set(fireYear, fireMonth, fireDate, fireHour, fireMinutes, fireSeconds);
				TimeForFire = new Timestamp(cal.getTimeInMillis());
				
				
				FireTimer = new Timer();
				FireTask = new TBFireTask(X, FireTimer);			
				FireTimer.schedule(FireTask, TimeForFire);
				
				
				//get the endTime on the given date and then schedule a timer accordingly 
				if(X.getCBTrigger().getEndTime() != null)
				{
					cal.setTimeInMillis(X.getCBTrigger().getEndTime().getTime());
					fireHour = cal.get(Calendar.HOUR_OF_DAY);
					fireMinutes = cal.get(Calendar.MINUTE);
					fireSeconds = cal.get(Calendar.SECOND);
				}
				else
				{
					fireHour = 0;
					fireMinutes = 0;
					fireSeconds = 0;
					//increment the date by one
					fireDate++;
				}

				//create a new timer 
				//create time for stop
				cal.set(fireYear, fireMonth, fireDate, fireHour, fireMinutes, fireSeconds);
				TimeForStop = new Timestamp(cal.getTimeInMillis());
				StopTimer = new Timer();
				StopTask = new TBStopTask(X, StopTimer);
				StopTimer.schedule(StopTask, TimeForStop);
			}
			else if(freq.equals("everyday"))
			{
				//get last fire or current date
				LastFire = X.getCBTrigger().getLastFire();
				if(LastFire != null)
				{
					cal.setTimeInMillis(X.getCBTrigger().getStartTime().getTime());
					fireYear = cal.get(Calendar.YEAR);
					fireMonth = cal.get(Calendar.MONTH);
					fireDate = cal.get(Calendar.DATE) + 1;  // note date is incremented by one 
				}
				else
				{
					fireYear = currYear;
					fireMonth = currMonth;
					fireDate = currDate;
				}
				
				
				//get the startTime on the given date and the schedule a timer accordingly
				if(X.getCBTrigger().getStartTime() != null)
				{
					cal.setTimeInMillis(X.getCBTrigger().getStartTime().getTime());
					fireHour = cal.get(Calendar.HOUR_OF_DAY);
					fireMinutes = cal.get(Calendar.MINUTE);
					fireSeconds = cal.get(Calendar.SECOND);
				}
				else
				{
					fireHour = 0;
					fireMinutes = 0;
					fireSeconds = 0;
				}
				
				//create a new timer 
				//create time for fire
				cal.set(fireYear, fireMonth, fireDate, fireHour, fireMinutes, fireSeconds);
				TimeForFire = new Timestamp(cal.getTimeInMillis());
				System.out.println(TimeForFire);
				
				FireTimer = new Timer();
				FireTask = new TBFireTask(X, FireTimer);			
				FireTimer.schedule(FireTask, TimeForFire);
				
				//get the endTime on the given date and then schedule a timer accordingly 
				if(X.getCBTrigger().getEndTime() != null)
				{
					cal.setTimeInMillis(X.getCBTrigger().getEndTime().getTime());
					fireHour = cal.get(Calendar.HOUR_OF_DAY);
					fireMinutes = cal.get(Calendar.MINUTE);
					fireSeconds = cal.get(Calendar.SECOND);
				}
				else
				{
					fireHour = 0;
					fireMinutes = 0;
					fireSeconds = 0;
					//increment th date by one
					fireDate++;
				}

				//create a new timer 
				//create time for stop
				cal.set(fireYear, fireMonth, fireDate, fireHour, fireMinutes, fireSeconds);
				TimeForStop = new Timestamp(cal.getTimeInMillis());
				StopTimer = new Timer();
				StopTask = new TBStopTask(X, StopTimer);
				StopTimer.schedule(StopTask, TimeForStop);
			}
			
//			else if (freq.equals("onceaweek"))
//			{
//				//get the next fire date
//				LastFire = X.getCBTrigger().getLastFire();
//				WeekDay = X.getCBTrigger().getweekDay();
//			
//		/**This is to be implemented**/		
//		/**		if(LastFire != null)
//				{
//					cal.setTimeInMillis(X.getCBTrigger().getStartTime().getTime());
//					fireYear = cal.get(Calendar.YEAR);
//					fireMonth = cal.get(Calendar.MONTH);
//					fireDate = cal.get(Calendar.DATE) + 7;  // note date is incremented by seven 
//				}
//				else if(WeekDay != null)
//				{
//					cal.set(currYear, currMonth, currDate, currHour, currMinutes, currSeconds);
//					
//					fireYear = currYear;
//					fireMonth = currMonth;
//					fireDate = currDate;
//				}
//		**/		
//				
//			}
					
			else if (freq.equals("onceayear"))
			{
				//get the date for next fire
				LastFire = X.getCBTrigger().getLastFire();
				if(LastFire != null)
				{
					cal.setTimeInMillis(X.getCBTrigger().getStartTime().getTime());
					fireYear = cal.get(Calendar.YEAR) +1 ; //note year is incremented by one
					fireMonth = cal.get(Calendar.MONTH);
					fireDate = cal.get(Calendar.DATE);
				}
				else if(X.getCBTrigger().getDate() != null)
				{
					cal.setTimeInMillis(X.getCBTrigger().getDate().getTime());
					fireYear = cal.get(Calendar.YEAR);
					fireMonth = cal.get(Calendar.MONTH);
					fireDate = cal.get(Calendar.DATE);
				}
				else
				{
					//else set the trigger for the next date
					fireYear = currYear;
					fireMonth = currMonth;
					fireDate = currDate + 1;				
				}			
				
				//get the startTime on the given date and the schedule a timer accordingly
				if(X.getCBTrigger().getStartTime() != null)
				{
					cal.setTimeInMillis(X.getCBTrigger().getStartTime().getTime());
					fireHour = cal.get(Calendar.HOUR_OF_DAY);
					fireMinutes = cal.get(Calendar.MINUTE);
					fireSeconds = cal.get(Calendar.SECOND);
				}
				else
				{
					fireHour = 0;
					fireMinutes = 0;
					fireSeconds = 0;
				}
				
				//create a new timer 
				//create time for fire
				cal.set(fireYear, fireMonth, fireDate, fireHour, fireMinutes, fireSeconds);
				TimeForFire = new Timestamp(cal.getTimeInMillis());
				FireTimer = new Timer();
				FireTask = new TBFireTask(X, FireTimer);			
				FireTimer.schedule(FireTask, TimeForFire);

				
				//get the endTime on the given date and then schedule a timer accordingly 
				if(X.getCBTrigger().getEndTime() != null)
				{
					cal.setTimeInMillis(X.getCBTrigger().getEndTime().getTime());
					fireHour = cal.get(Calendar.HOUR_OF_DAY);
					fireMinutes = cal.get(Calendar.MINUTE);
					fireSeconds = cal.get(Calendar.SECOND);
				}
				else
				{
					fireHour = 0;
					fireMinutes = 0;
					fireSeconds = 0;
					//increment the date by one
					fireDate++;
				}

				//create a new timer 
				//create time for stop
				cal.set(fireYear, fireMonth, fireDate, fireHour, fireMinutes, fireSeconds);
				TimeForStop = new Timestamp(cal.getTimeInMillis());
				StopTimer = new Timer();
				StopTask = new TBStopTask(X, StopTimer);
				StopTimer.schedule(StopTask, TimeForStop);
			}	
		}	
	
		
		
		
	/**
	 * Containts tasks that have to be performed when the time based 
	 * constraints of the trigger are satisfied.
	 * 
	 * Basic Operation --> When the time based trigger is satisfied
	 * 1. Check if the event has location based constraints or colocation based constraints. 
	 *    If yes , put it on the list for location based constriants. And we're done 
	 * 2. Else fire the trigger
	 */
	class TBFireTask extends TimerTask{
		Event event;
		Timer parentTimer;
		
		
		
		//constructor
		public TBFireTask(Event Xevent, Timer XparentTimer)
		{
			event = Xevent;
			parentTimer = XparentTimer;
		}
		public void run()
		{
			System.out.println("Time Task Fired");
			
			//if the event has location constraints, put it on the corrspoding list
			if(event.getCBTrigger().getLocationConstraints() || event.getCBTrigger().getColocationConstraints())
			{
				event.setIsOnLocationConstraintsList(true);
				LocationConstraintsList.add_event(event);
			}
			//else fire the trigger
			else
			{
				fireTrigger(event);	
			}	
			//cancel this timer
			parentTimer.cancel();
		}
	}
	
	/**
	 * @author Maverick
	 *
	 * The run method contains the tasks that need to be performed
	 * when the event trigger reaches the end time.
	 * 
	 * Basic operation --> when the trigger reaches the end time
	 * 		** if the event is on the locationConstraints list, remove it from the list
	 * 		   update position for the event as -1.
	 * 		** if the event is on the colocationConstraints list, remove it from the list
	 * 		   update position for the event as -1.
	 * 		** if the event is on the TBFireTriggerList. we remove the trigger from the list
	 * 		   and update poistion as -1. We cancel the timer
	 * 		** finally we remove the timer from TBStopTriggerList 
	 * 		   and cancel it.
	 */
	class TBStopTask extends TimerTask{
		Event event;
		Timer parentTimer;
		
		//constructor
		public TBStopTask(Event Xevent, Timer XparentTimer)
		{
			event = Xevent;
			parentTimer = XparentTimer;
		}
		
		public void run()
		{	
			//reomve from location constraints list
			if(event.getIsOnLocationConstraintsList())
			{
				LocationConstraintsList.remove_event(event.getEventId());
				event.setIsOnLocationConstraintsList(false);
			}
			
			//cancel this timer
			parentTimer.cancel();
		}	
	}
	
	
	/**
	 * 
	 * This class contains tasks that need to be performed 
	 * when we check for location constraints
	 * 
	 */
	class LocationConstraintsTask extends TimerTask{
			
		//constructor
		public LocationConstraintsTask()
		{
			;
		}
				
		public void run()
		{
			//variables
			Enumeration listOfEvents;
			Event eventToBeChecked; 
			long userId;
			Long targetUserId;
			Coordinates placeToBeChecked, mainUserCoordinates, targetUserCoordinates;
			int distanceToPlaces, distanceToUsers;
			boolean isLocationSatisfied, isColocationSatisfied;
			UserLocationTime mainUserLocation=null,targetUserLocation;
			Object [] params; //for database acccess
			String selectString; //for database access
			
	
			listOfEvents = LocationConstraintsList.getEventList();
			
			System.out.println("Location Task Fired");
			
			//get a list of events to be checked for location based constraints
				//repeat for all elements in the list
			while(listOfEvents.hasMoreElements())
			{
					isLocationSatisfied = false;
					isColocationSatisfied = false;
					
					eventToBeChecked = (Event)listOfEvents.nextElement();
					
					//if the event has location or colocation constraints, then get users location
					if(eventToBeChecked.getCBTrigger().getLocationConstraints() || eventToBeChecked.getCBTrigger().getColocationConstraints())
					{
						try{
								//get the userId whose location has to be checked
								userId = eventToBeChecked.getCBTrigger().getSourceUser();
								
								//get location from database
								//retrieve details for the trigger for this event
								params = new Object[] {new Long(userId),new Long(userId) };
								selectString = "select * from locationinfo_location where userid=? and mode='Centroid' and time = (select max(time) from locationinfo_location where userid=?)";
								locationinfo_location[] mainUserLocationData = (locationinfo_location[]) new SmartCampusDAO().query(selectString,params,locationinfo_location.class );
								
								if(mainUserLocationData.length != 0)
								{	
									mainUserCoordinates = new Coordinates(mainUserLocationData[0].lat, mainUserLocationData[0].lon, new Float(mainUserLocationData[0].height).floatValue());
									mainUserLocation = new UserLocationTime(); 
									mainUserLocation.setFloor(mainUserLocationData[0].floor);
									mainUserLocation.setMode(mainUserLocationData[0].mode);
									mainUserLocation.setNumberOfAP(mainUserLocationData[0].numberofap);
									mainUserLocation.setNumberOfKnownAP(mainUserLocationData[0].numberofknownap);
									mainUserLocation.setTime(mainUserLocationData[0].time);
									mainUserLocation.setLocation(mainUserCoordinates);
									System.out.println("Got Users Location:" + mainUserLocationData[0].lat + mainUserLocationData[0].lon);
								}
								
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
					
					
					//check if location constraints are satissfied for the event
					if(eventToBeChecked.getCBTrigger().getLocationConstraints())
					{
						try{
								distanceToPlaces = eventToBeChecked.getCBTrigger().getDistanceToPlaces();
								
								//check that the location is not very old. Not more that 2 minutes old.
								if(mainUserLocation != null)
								{
									if(mainUserLocation.getTime().after(new Timestamp(System.currentTimeMillis() - locationFreshness)))
									{
										
										//check if user location is close to any place in the list
										for(int i=0;((i< eventToBeChecked.getCBTrigger().getListOfPlaces().size()) && (isLocationSatisfied == false)); i++)
										{
											System.out.println("Going to Check Place Constraints:");
											placeToBeChecked = (Coordinates)eventToBeChecked.getCBTrigger().getListOfPlaces().get(i);
											if(mainUserLocation.getLocation().groundDistance(placeToBeChecked) < distanceToPlaces)
											{
												isLocationSatisfied = true;
											}
										}
									}
								}
						}catch(Exception e)
						{
							e.printStackTrace();
						}
					}
					else
					{
						isLocationSatisfied = true;
					}
					
					
					//check if colocation constraints are satisfied
					if(eventToBeChecked.getCBTrigger().getColocationConstraints())
					{
						try{
								distanceToUsers = eventToBeChecked.getCBTrigger().getDistanceToUsers();
								
								//check that the location is not very old. Not more that 2 minutes old.
								if(mainUserLocation != null)
								{
									if(mainUserLocation.getTime().after(new Timestamp(System.currentTimeMillis() - locationFreshness)))
									{
									
										//check if user location is close to any target user in the list
										for(int i=0;((i< eventToBeChecked.getCBTrigger().getTargetUsers().size()) && (isColocationSatisfied == false)); i++)
										{
											targetUserId = (Long)eventToBeChecked.getCBTrigger().getTargetUsers().get(i);
											//get location for target user
											//get location from database
											params = new Object[] {targetUserId, targetUserId};
											selectString = "select * from locationinfo_location where userid=? and mode='Centroid' and time = (select max(time) from locationinfo_location where userid=?)";
											locationinfo_location[] targetUserLocationData = (locationinfo_location[]) new SmartCampusDAO().query(selectString,params,locationinfo_location.class );
											
											targetUserCoordinates = new Coordinates(targetUserLocationData[0].lat, targetUserLocationData[0].lon, new Float(targetUserLocationData[0].height).floatValue());
											targetUserLocation = new UserLocationTime(); 
											if(targetUserLocationData != null)
											{
												targetUserLocation.setFloor(targetUserLocationData[0].floor);
												targetUserLocation.setMode(targetUserLocationData[0].mode);
												targetUserLocation.setNumberOfAP(targetUserLocationData[0].numberofap);
												targetUserLocation.setNumberOfKnownAP(targetUserLocationData[0].numberofknownap);
												targetUserLocation.setTime(targetUserLocationData[0].time);
												targetUserLocation.setLocation(targetUserCoordinates);
											}			
											//check that the target user location is not very old. Not more that 2 minutes old.
											if(targetUserLocation != null)
											{
												if(targetUserLocation.getTime().after(new Timestamp(System.currentTimeMillis() - locationFreshness)))
												{
												
													if(mainUserLocation.getLocation().groundDistance(targetUserLocation.getLocation()) < distanceToUsers)
													{
														isColocationSatisfied = true;
													}
												}
											}
											
										}//end of for loop
									}
								}
							}catch(Exception e)
							{
								e.printStackTrace();
							}						
					}
					else
					{
						isColocationSatisfied = true;
					}
						
					
					//if both constraints are satisfied, then we reomve the event from the list
					if(isLocationSatisfied && isColocationSatisfied)
					{
						//remove the event from the location constriants list
						LocationConstraintsList.remove_event(eventToBeChecked.getEventId());
						//update position in the list for this event (-1 means its not on the list)
						eventToBeChecked.setIsOnLocationConstraintsList(false);
						
						//fire the trigger
						fireTrigger(eventToBeChecked);	
					}
				}
			}
		}
	

	/**
	 *  This method sends the event to the services
	 *  
	 *  Functionality
	 *  	** We first check the frequency of the event. If this 
	 *  	   is "onetime", then we remove it from the mainevent list
	 *  	   and the database.
	 *  	** Otherwise, we store the current time as the time for last
	 *  	   fire for this event
	 *  	** We next send the event to the services  
	 */
	private void fireTrigger(Event X)
	{
		//Set the time of fire for the event
		X.getCBTrigger().setLastFire(new Timestamp(System.currentTimeMillis()));
		
		//Convert list of target users from arrayList to array
		Vector<Long> targetUsers = new Vector<Long>();
		
		//If there are no target users.....
		if(X.getTargetUsers()!= null)
		{
			for(int i=0;i<X.getTargetUsers().size();i++)
			{
				targetUsers.add((Long)X.getTargetUsers().get(i));
			}
		}
		
		//Create eventdata class to send to other services
		KVMEventData eventData = new KVMEventData();
		eventData.setDescription(X.getDescription());
		eventData.setEventId(X.getEventId());
		eventData.setFireTime(System.currentTimeMillis());
		eventData.setRegistrationTime(X.getRegistrationTime().getTime());
		eventData.setSourceUser(X.getSourceUser());
		eventData.setType(X.getType());
		eventData.setTargetUsers(targetUsers);
	
		
		//Store event data in fired events list
		for(int i=0; i< targetUsers.size(); i++)
		{
			KVMAlerts alert = (KVMAlerts)(FiredEventList.get((Long)targetUsers.get(i)));
			if(alert != null)
			{
				FiredEventList.remove((Long)targetUsers.get(i));
				Vector events = alert.getEvents();
				events.add(eventData);
				alert.setEvents(events);
				FiredEventList.put((Long)targetUsers.get(i), alert);
			}
			else
			{
				alert = new KVMAlerts();
				Vector events = new Vector();
				events.add(eventData);
				alert.setEvents(events);
				FiredEventList.put((Long)targetUsers.get(i), alert);
			}
		}
		
		/**
		 * Old Code, do not remove
		 * 
		//Store event data in fired events list
		KVMAlerts alert = (KVMAlerts)(FiredEventList.get(X.getSourceUser()));
		if(alert != null)
		{
			FiredEventList.remove(X.getSourceUser());
			Vector events = alert.getEvents();
			events.add(eventData);
			alert.setEvents(events);
			FiredEventList.put(X.getSourceUser(), alert);
		}
		else
		{
			alert = new KVMAlerts();
			Vector events = new Vector();
			events.add(eventData);
			alert.setEvents(events);
			FiredEventList.put(X.getSourceUser(), alert);
		}
		*/
		
		//If the frequency of the event was one time, we remove it 
		//from the list. Otherwise we process the constraints again 
		if(X.getCBTrigger().getFrequency().equals("onetime"))
		{
			deleteEvent(X.getEventId());
		}
		else
		{
			processTimeFrequencyConstraints(X);
		}
		
		System.out.print("Event Fired");
	}
	
	
	
	
	
	/**
	 * 
	 * This function is used to retirieve events from the database
	 * and register them with the event dispatcher
	 *
	 */
	private void retrieveAndRegisterEvents()
	{
		Event newEvent;
		CBTrigger newTrigger;
		String selectString;
		Object[] params;
		ArrayList mainTargetUsers, colocationTargetUsers, listOfPlaces;
		Coordinates tempPlace;
		
		try{
			//retrieve all events from the database
			selectString = "select *  from ed_event_main";
			ed_event_main[] mainEventList = (ed_event_main[]) new SmartCampusDAO().query(selectString,ed_event_main.class ); 
			
			for(int i=0;i<mainEventList.length;i++)
			{
				//set data for the event
				newEvent = new Event();
				
				newEvent.setEventId(mainEventList[i].event_id);
				newEvent.setRegistrationTime(mainEventList[i].registration_time);
				newEvent.setType(mainEventList[i].event_type);
				newEvent.setDescription(mainEventList[i].description);
				newEvent.setSourceUser(mainEventList[i].source_user_id);
				newEvent.setServiceApi(mainEventList[i].service_api);
				newEvent.setServiceName(mainEventList[i].service_name);
				
				//retrieve the list of target users for this event
				params = new Object[] {new Long(mainEventList[i].event_id), "MainTargetUsers"};
				selectString = "select *  from ed_user_list where event_id=? and list_type=?";
				ed_user_list[] mainUserList = (ed_user_list[]) new SmartCampusDAO().query(selectString,params,ed_user_list.class );
				mainTargetUsers = new ArrayList();
				
				for(int j=0; j< mainUserList.length; j++)
				{	
					mainTargetUsers.add(new Long(mainUserList[j].user_id));
				}
				
				//add the list of target users to the event
				newEvent.setTargetUsers(mainTargetUsers);
				
				//retrieve details for the trigger for this event
				params = new Object[] {new Long(mainEventList[i].event_id)};
				selectString = "select *  from ed_cb_trigger where event_id=?";
				ed_cb_trigger[] cbTriggerData = (ed_cb_trigger[]) new SmartCampusDAO().query(selectString,params,ed_cb_trigger.class );
				
				newTrigger = new CBTrigger();
				
				if(cbTriggerData.length != 0)
				{
					newTrigger.setStartTime(cbTriggerData[0].start_time);
					newTrigger.setEndTime(cbTriggerData[0].end_time);
					newTrigger.setWeekDay(cbTriggerData[0].weekday);
					newTrigger.setDate(cbTriggerData[0].date);
					newTrigger.setFrequency(cbTriggerData[0].frequency);
					newTrigger.setLastFire(cbTriggerData[0].last_fire);
					newTrigger.setTimeConstraints(cbTriggerData[0].time_constraints);
					newTrigger.setColocationConstraints(cbTriggerData[0].colocation_constraints);
					newTrigger.setSourceUser(cbTriggerData[0].source_user);
					newTrigger.setDistanceToUsers(cbTriggerData[0].distance_to_users);
					newTrigger.setLocationConstraints(cbTriggerData[0].location_constraints);
					newTrigger.setDistanceToPlaces(cbTriggerData[0].distance_to_places);
				}
				
				//retrieve the list of target users for this trigger
				params = new Object[] {new Long(mainEventList[i].event_id), "LocationTargetUsers"};
				selectString = "select *  from ed_user_list where event_id=? and list_type=?";
				ed_user_list[] colocationUserList = (ed_user_list[]) new SmartCampusDAO().query(selectString,params,ed_user_list.class );
				colocationTargetUsers = new ArrayList();
				
				for(int j=0; j< colocationUserList.length; j++)
				{	
					colocationTargetUsers.add(new Long(colocationUserList[j].user_id));
				}
				newTrigger.setTargetUsers(colocationTargetUsers);
				
				//retrieve the list of target places for this trigger
				params = new Object[] {new Long(mainEventList[i].event_id)};
				selectString = "select *  from ed_place_list where event_id=?";
				ed_place_list[] placeListData  = (ed_place_list[]) new SmartCampusDAO().query(selectString,params,ed_place_list.class );
				listOfPlaces = new ArrayList();
				
				for(int k=0; k< placeListData.length; k++)
				{	
					tempPlace = new Coordinates(placeListData[k].lat, placeListData[i].lon, placeListData[i].ht);
					listOfPlaces.add(tempPlace);
				}
				newTrigger.setListOfPlaces(listOfPlaces);
				
				//add the trigger to the event
				newEvent.setCBTrigger(newTrigger);
				
				//register the event
				registerEvent(newEvent, false);
				
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}

	}
	
	
	/**
	 * 
	 * 
	 * @param
	 *   Event to be stored
	 */
	private void storeEventInDatabase(Event X)
	{
		int rowsUpdated; 
		Object[] params;
		String insertData;
		Coordinates placeCoordinates;
		
		//get the trigger object
		CBTrigger Trig = X.getCBTrigger();
		
		//store the event in the database		
		try{
				//store main event
				params = new Object[] {
				new Long(X.getEventId()), 
				X.getRegistrationTime(), 
				X.getType(), 
				X.getDescription(),
				new Long(X.getSourceUser()),
				X.getServiceName(),
				X.getServiceApi()
				};
				  
				insertData= "insert into ed_event_main(event_id, registration_time, event_type, description, source_user_id, service_api, service_name) VALUES(?,?,?,?,?,?,?)";
				//Log.put("demoINSERT", groupPlaceplaceInsert);
				// Invoke update method on DAO to perform insert
				rowsUpdated = new SmartCampusDAO().update(insertData, params);
				//Log.put("rowsUpdated", "="+rowsUpdated);
				
				//store main target user list
				if(X.getTargetUsers() != null)
				{
					for(int i=0;i<X.getTargetUsers().size();i++)
					{
						params = new Object[]{
							new Long(X.getEventId()),
							(Long)X.getTargetUsers().get(i),
							"MainTargetUsers"
						};
						
					   insertData = "insert into ed_user_list (event_id, user_id, list_type) values(?,?,?)"; 
					   rowsUpdated = new SmartCampusDAO().update(insertData, params);	                    
					}
				}
				
				
				//store main CBtrigger
				params = new Object[]{
						new Long(X.getEventId()),
						Trig.getFrequency(),
						Trig.getLastFire(),
						new Boolean(Trig.getTimeConstraints()),
						Trig.getStartTime(),
						Trig.getEndTime(),
						Trig.getDate(),
						Trig.getweekDay(),
						new Boolean(Trig.getColocationConstraints()),
						new Long(Trig.getSourceUser()),
						new Integer(Trig.getDistanceToUsers()),
						new Boolean(Trig.getLocationConstraints()),
						new Integer(Trig.getDistanceToPlaces())
						};
				
				insertData = "insert into ed_cb_trigger(event_id, frequency, last_fire, time_constraints, start_time, end_time, date, weekday, colocation_constraints, source_user, distance_to_users, location_constraints, distance_to_places) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
				rowsUpdated = new SmartCampusDAO().update(insertData, params);
				
				//store location constraints target user list
				if(X.getCBTrigger().getTargetUsers() != null)
				{
					for(int i=0;i<X.getCBTrigger().getTargetUsers().size();i++)
					{
						params = new Object[]{
							new Long(X.getEventId()),
							(Long)X.getCBTrigger().getTargetUsers().get(i),
							"LocationTargetUsers"
						};
						
					   insertData = "insert into ed_user_list (event_id, user_id, list_type) values(?,?,?)"; 
					   rowsUpdated = new SmartCampusDAO().update(insertData, params);	                    
					}
				}
				
				//store location constraints list of places
				if(X.getCBTrigger().getListOfPlaces() != null)
				{
					for(int i=0;i<X.getCBTrigger().getListOfPlaces().size();i++)
					{
						//get the place coordinates from the list of places
						placeCoordinates = (Coordinates)X.getCBTrigger().getListOfPlaces().get(i);
						
						params = new Object[]{
							new Long(X.getEventId()),
	/** This has to be 
	 * 
	 * changed
	 */						new Long(i),
							new Double(placeCoordinates.getLatitude()),
							new Double(placeCoordinates.getLongitude()),
							new Float(placeCoordinates.getAltitude())
						};
						
					   insertData = "insert into ed_place_list (event_id, place_id, lat, lon, ht) values(?,?,?,?,?)"; 
					   rowsUpdated = new SmartCampusDAO().update(insertData, params);	                    
					}
				}		
		}catch(Exception e)
		{
			e.printStackTrace();
		}		
	}
	
	/**
	 * 
	 * This is to remove the given event from the database
	 */
	private void deleteEventFromDatabase(long eventId)
	{
		String query;
		
		Object[] params = new Object[] {new Long(eventId)};
		
		try{
			//remove event from ed_place_list
			query = "delete from ed_place_list where event_id=?";
			new SmartCampusDAO().update(query, params);

			//remove event from ed_user_list
			query = "delete from ed_user_list where event_id=?";
			new SmartCampusDAO().update(query, params);
					
			//remove event from ed_cb_trigger
			query = "delete from ed_cb_trigger where event_id=?";
			new SmartCampusDAO().update(query, params);
			
			//remove event from ed_event_main
			query = "delete from ed_event_main where event_id=?";
			new SmartCampusDAO().update(query, params);
		}catch(Exception e)
		{
			System.out.print("Error while Deleting Object");
			e.printStackTrace();
		}
	}
	
	/**
	 * This is not part of visible API. The visible API is registerEvent
	 * That method call this method with the second parameter set to true
	 **/
	private synchronized void registerEvent(Event X, boolean storeInDatabase)
	{
		//assign an event id to this event. increment counter
		X.setEventId(EventIDCounter);
		EventIDCounter++;
		//set the registration time for the event
		X.setRegistrationTime(new Timestamp(System.currentTimeMillis()));
		
	
		if(storeInDatabase)
		{
			//storeEventInDatabase(X);
		}
		
		//add event to the event list
		MainEventList.add_event(X);
		
		//start processing the constraints
		processTimeFrequencyConstraints(X);
	}

	

	
	
	
	
	/**
	 ******************************************************************************************************************************
	 ******************************************************************************************************************************
	 * *****************************************************************************************************************************
	 * Function for testing
	 * @param args
	 * *****************************************************************************************************************************
	 * *****************************************************************************************************************************
	 * *****************************************************************************************************************************
	 */
	
//	public static void main(String args[])
//	{
//		EventService p = new EventService();
//		
//		//Setup Colocation Trigger
//		//Timestamp startTime = new Timestamp(System.currentTimeMillis()+5000);
//		//Timestamp endTime = new Timestamp(System.currentTimeMillis()+ 60000000);
//		//Timestamp date = new Timestamp(System.currentTimeMillis());
//		
//		//long[] listOfUsers = new long[1];
//		//listOfUsers[0] = 10000005;
//		
//		KVMCBTrigger Trig1 = new KVMCBTrigger();
//		Trig1.setFrequency("onetime");
//		Trig1.setTimeConstraints(new Boolean(true));
//		Trig1.setStartTime(new Long(System.currentTimeMillis()+20000));
//		Trig1.setEndTime(new Long(System.currentTimeMillis()+ 60000000));
//		Trig1.setDate(new Long(System.currentTimeMillis()));
//		//Trig1.setColocationConstraints(true);
//		//Trig1.setDistanceToUsers(15);
//		//Trig1.setSourceUser(1234);
//		//Trig1.setTargetUsers(listOfUsers);
//		
//		//setup the event
//		KVMEvent X;
//		X = new KVMEvent();
//		X.setCBTrigger(Trig1);
//		X.setSourceUser(new Long(1234));
//		//X.setTargetUsers(new long[]{1234});
//		//X.setType("Colocation Event");
//		X.setDescription("Your Friend's Here");
//		
//		
//		//Register the event
//		p.registerEvent(X);
//		
//		while(true)
//		{
//			KVMAlerts G = p.getAlerts(new Long(1234));
//			if(G != null)
//			{
//				Vector V = G.getEvents();
//				KVMEventData D =  (KVMEventData) V.elementAt(0);
//				System.out.println(D.getDescription());
//			}
//			else
//			{
//				System.out.println("No Alerts");
//				try{
//					Thread.sleep(5000);
//				}catch(Exception e)
//				{
//					e.printStackTrace();
//				}
//			}	
//		}
//	}
	
	
	
}
