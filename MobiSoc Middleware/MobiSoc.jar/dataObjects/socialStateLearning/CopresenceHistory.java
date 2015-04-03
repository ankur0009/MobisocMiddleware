package dataObjects.socialStateLearning;

import java.sql.Timestamp;

public class CopresenceHistory {
	private long placeid;
	private Timestamp starttime;
	private Timestamp endtime;
	
	public Timestamp getEndtime() {
		return endtime;
	}
	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}
	public long getPlaceid() {
		return placeid;
	}
	public void setPlaceid(long placeid) {
		this.placeid = placeid;
	}
	public Timestamp getStarttime() {
		return starttime;
	}
	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}
}
