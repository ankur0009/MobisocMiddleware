
package serviceFunctionality.socialStateLearning.Matching.Sub;

import java.util.LinkedList;

import serviceFunctionality.socialStateLearning.Matching.Util.ElementMatch;
import serviceFunctionality.socialStateLearning.Matching.Util.GenerateScore;
import serviceFunctionality.socialStateLearning.Matching.Util.MatchInterface;
import serviceFunctionality.socialStateLearning.Matching.Util.ProfInterface;

import dataObjects.socialStateLearning.Matching.GeoTemporalCoPresense;
import dataObjects.socialStateLearning.Matching.GeoTemporalPresense;
import dataObjects.socialStateLearning.Matching.SocialNetworkMutualFriends;



public class ImplementedGeo implements MatchInterface {

	private LinkedList<MatchInterface> subClasses;
	private LinkedList<ElementMatch> elements;

	public static final String NAME = "geotemporal";
	public static final String PRINT_NAME = "Geo-Temporal";
	
	private String generatefunction = GenerateScore.GENERATE_LOCAL;

	public double weight;

	public ProfInterface profIn;

	public ImplementedGeo(ProfInterface profIn) {
		super();
		this.profIn = profIn;
		weight = 1.0;
		subClasses = new LinkedList<MatchInterface>();
		elements = new LinkedList<ElementMatch>();
		elements.add(new GeoTemporalCoPresense((profIn)));
		elements.add(new GeoTemporalPresense((profIn)));
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
		return SUB_MATCH_TYPE_GEO_TEMPORAL_ID;
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

// How many times User A crossed User B

// How much time have they been located together (proximity)( are they always
// located together at certain place, time or both => attendance to same event,
// or taking same course looking for patterns in traces)

// Visit to same place @ different times (places change context with time, but
// not always, this could still used, esp. if we could add contextual
// information to the place, e.g. tennis courts, although I have seen people
// playing cricket there).

