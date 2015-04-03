package dataObjects.socialStateLearning;

public class NearbyEvent {
	private long eventId; //id of nearby event
	private double distanceToEvent; //distance to nearby event
	
	public double getDistanceToEvent() {
		return distanceToEvent;
	}
	
	public void setDistanceToEvent(double distanceToEvent) {
		this.distanceToEvent = distanceToEvent;
	}
	
	public long getEventId() {
		return eventId;
	}
	
	public void setEventId(long eventId) {
		this.eventId = eventId;
	}
}
