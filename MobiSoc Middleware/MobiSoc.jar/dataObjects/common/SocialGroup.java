package dataObjects.common;
import java.sql.*;

public class SocialGroup {
	
	  private long gid;
	  private String name;
	  private String description;
	  private String group_type;
	  private String recent_news;
	  private long owner; 
	  private Timestamp update_time;
	  private long location; 
	  private String website;
	 
	//constructor for setting default values  
	public SocialGroup()
	{
		gid = -1;
		name = "";
		description = "";
		group_type = "";
		recent_news = "";
		owner = -1;
		update_time = new Timestamp(0);
		location = -1;
		website = "";
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getGid() {
		return gid;
	}
	public void setGid(long gid) {
		this.gid = gid;
	}
	public String getGroup_type() {
		return group_type;
	}
	public void setGroup_type(String group_type) {
		this.group_type = group_type;
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
	public String getRecent_news() {
		return recent_news;
	}
	public void setRecent_news(String recent_news) {
		this.recent_news = recent_news;
	}
	public Timestamp getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	  
	  

}
