package dataObjects.socialStateLearning.Matching;
import serviceFunctionality.socialStateLearning.Matching.Util.ElementMatch;
import serviceFunctionality.socialStateLearning.Matching.Util.ProfInterface;
public class BasicPolitical implements ElementMatch {
	public static final String NAME = "political";
	public static final String PRINT_NAME = "Political View";
	public static final String DESCRIPTION = "Her/His Political View";
	public static final String TYPE = "Basic";
	public int numEntryFirstUser = 0;
	public int numEntrySecondUser = 0;
	public int numEntryMatched = 0;
	public double dbscore = 0;
	private double score;
	public double weight;
	ProfInterface profIn;

 public BasicPolitical() {
       super();
 }
	public BasicPolitical(ProfInterface profIn) {
		super();
		this.profIn = profIn;
		this.setWeight(1.0);
		this.setScore(0);
	}
	public String getName() {
		return NAME;
	}
	public int numEntryFirstUser() {
		return profIn.numOfPoliticalUser();
	}
	public int numEntrySecondUser() {
		return profIn.numOfPoliticalFriend();
	}
	public int numMatchedEntries() {
		return profIn.numPolitical();
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
		return ElementMatch.POLITICAL_ID;
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
