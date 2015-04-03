package dataObjects.socialStateLearning.Matching;
import serviceFunctionality.socialStateLearning.Matching.Util.ElementMatch;
import serviceFunctionality.socialStateLearning.Matching.Util.ProfInterface;
public class GeoTemporalCrissCross implements ElementMatch {
	public static final String NAME = "crisscross";
	public static final String PRINT_NAME = "Familiar Stranger";
	public static final String DESCRIPTION = "Do I see Her/Him Often.";
	public static final String TYPE = "GeoTemporal";
	public int numEntryFirstUser = 0;
	public int numEntrySecondUser = 0;
	public int numEntryMatched = 0;
	public double dbscore = 0;
	private double score;
	public double weight;
	ProfInterface profIn;

 public GeoTemporalCrissCross() {
       super();
 }
	public GeoTemporalCrissCross(ProfInterface profIn) {
		super();
		this.profIn = profIn;
		this.setWeight(1.0);
		this.setScore(0);
	}
	public String getName() {
		return NAME;
	}
	public int numEntryFirstUser() {
		return profIn.numOfCrissCrossUser();
	}
	public int numEntrySecondUser() {
		return profIn.numOfCrissCrossFriend();
	}
	public int numMatchedEntries() {
		return profIn.numCrissCross();
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public int getId() {
		return ElementMatch.CRISSCROSS_ID;
	}
	public String getPrintName() {
		return PRINT_NAME;
	}
	public String getType() {
		return TYPE;
	}
	public String getDescription() {
		// TODO Auto-generated method stub
		return DESCRIPTION;
	}
	public double getDbScore() {
		return dbscore;
	}
	public int getDbnumEntryFirstUser() {
		return numEntryFirstUser;
	}
	public int getDbnumEntrySecondUser() {
		return numEntrySecondUser;
	}
	public int getDbnumMatchedEntries() {
		return numEntryMatched;
	}
	public void setDbScore(double score) {
		dbscore = score;
	}
	public void setDbnumEntryFirstUser(int a) {
		numEntryFirstUser = a;
	}
	public void setDbnumEntrySecondUser(int a) {
		numEntrySecondUser = a;
	}
	public void setDbnumMatchedEntries(int a) {
		numEntryMatched = a;
	}
}
