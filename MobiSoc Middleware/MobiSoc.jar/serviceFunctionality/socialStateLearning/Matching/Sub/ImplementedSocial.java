package serviceFunctionality.socialStateLearning.Matching.Sub;

import java.util.LinkedList;

import serviceFunctionality.socialStateLearning.Matching.Util.ElementMatch;
import serviceFunctionality.socialStateLearning.Matching.Util.GenerateScore;
import serviceFunctionality.socialStateLearning.Matching.Util.MatchInterface;
import serviceFunctionality.socialStateLearning.Matching.Util.ProfInterface;

import dataObjects.socialStateLearning.Matching.SocialNetworkCommonGroup;
import dataObjects.socialStateLearning.Matching.SocialNetworkMutualFriends;


public class ImplementedSocial implements MatchInterface {

	private LinkedList<MatchInterface> subClasses;
	private LinkedList<ElementMatch> elements;

	public static final String NAME = "socialnetworkmatch";

	public static final String PRINT_NAME = "Social Network";
	
	private String generatefunction = GenerateScore.GENERATE_LOCAL;

	public double weight;
	public ProfInterface profIn;

	public ImplementedSocial(ProfInterface profIn) {
		super();
		this.profIn = profIn;
		weight = 1.0;
		subClasses = new LinkedList<MatchInterface>();
		elements = new LinkedList<ElementMatch>();
		elements.add(new SocialNetworkMutualFriends((profIn)));
		elements.add(new SocialNetworkCommonGroup((profIn)));		

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
		// TODO Auto-generated method stub
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
		
		return SUB_MATCH_TYPE_SOCIAL_NETWORK_ID;
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

// 1. How many friends does User A have in common with B?? absolute as well as
// conditional probability

// 2. User B's geo-temporal match score with people in social network in A.

// 3. User B's Communication with people in User A's Social network

// 4. Membership to common groups (Explicit-system generated)

