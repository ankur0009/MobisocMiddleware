

package serviceFunctionality.socialStateLearning.Matching.Sub;

import java.util.LinkedList;

import serviceFunctionality.socialStateLearning.Matching.Util.ElementMatch;
import serviceFunctionality.socialStateLearning.Matching.Util.GenerateScore;
import serviceFunctionality.socialStateLearning.Matching.Util.MatchInterface;
import serviceFunctionality.socialStateLearning.Matching.Util.ProfInterface;

import dataObjects.socialStateLearning.Matching.InterestDrink;
import dataObjects.socialStateLearning.Matching.Interestsportsplay;
import dataObjects.socialStateLearning.Matching.Interestsportswatch;


public class ImplementedInterest implements MatchInterface {

	private LinkedList<MatchInterface> subClasses;
	private LinkedList<ElementMatch> elements;

	public static final String NAME = "userinterestmatch";
	public static final String PRINT_NAME = "Interests";
	private String generatefunction = GenerateScore.GENERATE_LOCAL;

	public double weight;

	public ProfInterface profIn;

	public ImplementedInterest(ProfInterface profIn) {
		super();
		this.profIn = profIn;
		weight = 1.0;
		subClasses = new LinkedList<MatchInterface>();
		elements = new LinkedList<ElementMatch>();
	
		elements.add(new InterestDrink((profIn)));
		elements.add(new Interestsportsplay((profIn)));
		elements.add(new Interestsportswatch((profIn)));
		
	}

	public int generateScore() {

		if (generatefunction.equalsIgnoreCase(GenerateScore.GENERATE_DB))
			return GenerateScore.generateScoreFromDB(subClasses, elements);
		else if (generatefunction
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

	}

	public long getMatchId() {

		return SUB_MATCH_TYPE_USER_INTEREST_ID;
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

// Explicit user defined interests , or users interest to be match along certain
// attribute.

// web usage data, e.g. number of views on B's profile. Even mining wiki web
// usage of user A and B.

