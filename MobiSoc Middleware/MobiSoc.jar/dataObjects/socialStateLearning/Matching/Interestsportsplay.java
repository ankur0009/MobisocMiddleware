package dataObjects.socialStateLearning.Matching;
import serviceFunctionality.socialStateLearning.Matching.Util.ElementMatch;
import serviceFunctionality.socialStateLearning.Matching.Util.ProfInterface;
public class Interestsportsplay implements ElementMatch {
	public static final String NAME = "sportsplay";
	public static final String PRINT_NAME = "Sports I Play";
	public static final String DESCRIPTION = "Do we like to Play the Same Sports?";
	public static final String TYPE = "Interest";
	public int numEntryFirstUser = 0;
	public int numEntrySecondUser = 0;
	public int numEntryMatched = 0;
	public double dbscore = 0;
	private double score;
	public double weight;
	ProfInterface profIn;

 public Interestsportsplay() {
       super();
 }
	public Interestsportsplay(ProfInterface profIn) {
		super();
		this.profIn = profIn;
		this.setWeight(1.0);
		this.setScore(0);
	}
	public String getName() {
		return NAME;
	}
	public int numEntryFirstUser() {
		return profIn.numOfsportsplayUser();
	}
	public int numEntrySecondUser() {
		return profIn.numOfsportsplayFriend();
	}
	public int numMatchedEntries() {
		return profIn.numsportsplay();
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
		return ElementMatch.SPORTSPLAY_ID;
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
