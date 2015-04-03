package dataObjects.socialStateLearning;

public class NearbyPlace {
	private long placeId; //id of nearby place
	private double distanceToPlace; //distance to nearby place
	
	public double getDistanceToPlace() {
		return distanceToPlace;
	}
	
	public void setDistanceToPlace(double distanceToPlace) {
		this.distanceToPlace = distanceToPlace;
	}
	
	public long getPlaceId() {
		return placeId;
	}
	
	public void setPlaceId(long placeId) {
		this.placeId = placeId;
	}
}
