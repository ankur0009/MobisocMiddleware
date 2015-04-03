package serviceFunctionality.socialStateLearning.Matching.Sub;

import java.util.LinkedList;

import serviceFunctionality.socialStateLearning.Matching.Util.ElementMatch;
import serviceFunctionality.socialStateLearning.Matching.Util.GenerateScore;
import serviceFunctionality.socialStateLearning.Matching.Util.MatchInterface;
import serviceFunctionality.socialStateLearning.Matching.Util.ProfInterface;

import dataObjects.socialStateLearning.Matching.BasicCountry;
import dataObjects.socialStateLearning.Matching.BasicHomeTown;
import dataObjects.socialStateLearning.Matching.BasicPolitical;
import dataObjects.socialStateLearning.Matching.BasicReligion;



public class BasicProfileMatch implements MatchInterface {

	private LinkedList<MatchInterface> subClasses;
	private LinkedList<ElementMatch> elements;

	public static final String NAME = "basicprofile";

	public static final String PRINT_NAME = "Basic Profile";

	private String generatefunction = GenerateScore.GENERATE_LOCAL;

	public double weight;

	public ProfInterface profIn;

	public BasicProfileMatch(ProfInterface profIn) {
		super();
		weight = 1.0;
		this.profIn = profIn;
		subClasses = new LinkedList<MatchInterface>();
		elements = new LinkedList<ElementMatch>();

		elements.add(new BasicCountry((profIn)));
		elements.add(new BasicHomeTown((profIn)));
		elements.add(new BasicPolitical((profIn)));
		elements.add(new BasicReligion((profIn)));		

	}

	public void addMatchInterFace(MatchInterface mi) {
		subClasses.add(mi);

	}

	public int generateScore() {

		if (generatefunction.equalsIgnoreCase(GenerateScore.GENERATE_DB)) {
			return GenerateScore.generateScoreFromDB(subClasses, elements);
		} else if (generatefunction
				.equalsIgnoreCase(GenerateScore.GENERATE_LOCAL))
			return GenerateScore.generateScore(subClasses, elements);
		else
			return 0; // return GenerateScore.generateScore(subClasses,
		// elements);
	}

	public String getName() {
		return NAME;
	}

	public MatchInterface[] getSubMatchclasses() {
		return subClasses.toArray(new MatchInterface[subClasses.size()]);
	}

	public double getWeight() {

		return weight;
	}

	public void setWeight(double weight) {

		this.weight = weight;
	}

	public ElementMatch[] getElementMatches() {

		return elements.toArray(new ElementMatch[elements.size()]);
	}

	public String getPrintName() {

		return PRINT_NAME;
	}

	public long getUidFriend() {
		return profIn.getUserId2();
	}

	public long getUidUser() {

		return profIn.getUserId1();
	}

	public void setGenerateScore(String GENERATE_SCORE_FUNCTION) {
		generatefunction = GENERATE_SCORE_FUNCTION;
		for (int i = 0; i < subClasses.size(); i++) {
			subClasses.get(i).setGenerateScore(GENERATE_SCORE_FUNCTION);
		}

	}

	public long getMatchId() {
		return SUB_MATCH_TYPE_BASIC_PROFILE_ID;
	}

	public int getScore() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void addElementMatch(ElementMatch el) {
		elements.add(el);
		
	}

	public void removeAllElementMatches() {
		elements=new LinkedList<ElementMatch>();
		
	}
}

// Match on profiles data , similar interests , hobbies, activities mentioned in
// user profile and preferences.

// Calculating reputations along different vectors, use ratings to calculate
// reputations.
// asking other user to rate various things about this user (collaborative
// filtering) e.g. Hotness, how
// social the user is etc....

// Add interests to profiles by mining attendance to events.

