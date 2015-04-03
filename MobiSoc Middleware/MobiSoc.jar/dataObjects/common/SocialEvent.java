package dataObjects.common;
import java.sql.Timestamp;

public class SocialEvent {
	 private long eid;
	 private String name;
	 private String host;
	 private String description;
	 private String  event_type;
	 private long owner;
	 private Timestamp start_time;
	 private Timestamp end_time;
	 private Timestamp update_time;
	 private long  location;

	 //constructor for setting default values
	 public SocialEvent()
	 {
		 eid = -1;
		 name = "";
		 host = "";
		 description = "";
		 event_type = "";
		 owner = -1;
		 start_time = new Timestamp(0);
		 end_time = new Timestamp(0);
		 update_time = new Timestamp(0);
		 location = -1;
	 }
	 
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getEid() {
		return eid;
	}
	public void setEid(long eid) {
		this.eid = eid;
	}
	public Timestamp getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Timestamp end_time) {
		this.end_time = end_time;
	}
	public String getEvent_type() {
		return event_type;
	}
	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public long getLocation() {
		return location;
	}
	public void setLocation(long location) {
		this.location = location;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getOwner() {
		return owner;
	}
	public void setOwner(long owner) {
		this.owner = owner;
	}
	public Timestamp getStart_time() {
		return start_time;
	}
	public void setStart_time(Timestamp start_time) {
		this.start_time = start_time;
	}
	public Timestamp getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}

	 
	 
}
