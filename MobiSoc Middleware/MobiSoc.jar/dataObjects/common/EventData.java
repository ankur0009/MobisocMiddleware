package dataObjects.common;


import java.io.Serializable;
import java.util.Date;

public class EventData implements Serializable {

	private static final long serialVersionUID = 872511121093309180L;

	private long eventId;

	private Date registrationTime;

	private String type;

	private String description;

	private long sourceUser;

	private long[] targetUsers;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public Date getRegistrationTime() {
		return registrationTime;
	}

	public void setRegistrationTime(Date registrationTime) {
		this.registrationTime = registrationTime;
	}

	public long getSourceUser() {
		return sourceUser;
	}

	public void setSourceUser(long sourceUser) {
		this.sourceUser = sourceUser;
	}

	public long[] getTargetUsers() {
		return targetUsers;
	}

	public void setTargetUsers(long[] targetUsers) {
		this.targetUsers = targetUsers;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public EventData() {
	}

	public EventData(long eventId, Date registrationTime, String type, String description, long sourceUser, long[] targetUsers) {
		this();
		
		this.eventId = eventId;
		this.registrationTime = registrationTime;
		this.type = type;
		this.description = description;
		this.sourceUser = sourceUser;
		this.targetUsers = targetUsers;
	}
	

}