package dataObjects.socialStateLearning.Matching;
import serviceFunctionality.socialStateLearning.Matching.Util.ElementMatch;
import serviceFunctionality.socialStateLearning.Matching.Util.ProfInterface;
public class PhysicalHeight implements ElementMatch {
	public static final String NAME = "height";
	public static final String PRINT_NAME = "Height";
	public static final String DESCRIPTION = "Her/His Height";
	public static final String TYPE = "Physical";
	public int numEntryFirstUser = 0;
	public int numEntrySecondUser = 0;
	public int numEntryMatched = 0;
	public double dbscore = 0;
	private double score;
	public double weight;
	ProfInterface profIn;

 public PhysicalHeight() {
       super();
 }
	public PhysicalHeight(ProfInterface profIn) {
		super();
		this.profIn = profIn;
		this.setWeight(1.0);
		this.setScore(0);
	}
	public String getName() {
		return NAME;
	}
	public int numEntryFirstUser() {
		return profIn.numOfHeightUser();
	}
	public int numEntrySecondUser() {
		return profIn.numOfHeightFriend();
	}
	public int numMatchedEntries() {
		return profIn.numHeight();
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
		return ElementMatch.HEIGHT_ID;
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
