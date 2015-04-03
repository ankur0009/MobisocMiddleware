
package serviceFunctionality.socialStateLearning.Matching.Match;

import java.util.LinkedList;

import serviceFunctionality.socialStateLearning.Matching.Sub.BasicProfileMatch;
import serviceFunctionality.socialStateLearning.Matching.Sub.GeoTemporalMatch;
import serviceFunctionality.socialStateLearning.Matching.Sub.SocialNetworkMatch;
import serviceFunctionality.socialStateLearning.Matching.Sub.UserInterestMatch;
import serviceFunctionality.socialStateLearning.Matching.Util.ElementMatch;
import serviceFunctionality.socialStateLearning.Matching.Util.GenerateScore;
import serviceFunctionality.socialStateLearning.Matching.Util.MatchInterface;
import serviceFunctionality.socialStateLearning.Matching.Util.ProfInterface;
import serviceFunctionality.socialStateLearning.Matching.Util.ProfileManager;


public class Other implements MatchInterface {

	public LinkedList<MatchInterface> subClasses;
	private LinkedList<ElementMatch> elements;

	private Long userid;

	private Long friendid;
	
	private Integer score=null;

	public static final String NAME = "other";

	public static final String PRINT_NAME = "Other";

	private String generatefunction = GenerateScore.GENERATE_LOCAL;

	public double weight;

	public Other(Long userid, Long friendid) {
		super();
		this.userid = userid;
		this.friendid = friendid;
		ProfInterface profIn = new ProfileManager(userid, friendid);
		weight = 1.0;
		subClasses = new LinkedList<MatchInterface>();
		elements = new LinkedList<ElementMatch>();

		// subClasses.add(new BasicProfileMatch(profIn));
		// subClasses.add(basicPro);
		subClasses.add(new GeoTemporalMatch(profIn));
		subClasses.add(new SocialNetworkMatch(profIn));
		subClasses.add(new UserInterestMatch(profIn));
		subClasses.add(new BasicProfileMatch(profIn));

		// subClasses.get(0).getSubMatchclasses()[0].getElementMatches()[0].setWeight(0.5);

	}

	public int generateScore() {

		if (generatefunction.equalsIgnoreCase(GenerateScore.GENERATE_DB)) {
			score=GenerateScore.generateScoreFromDB(subClasses, elements);
			return score;
		}
		else if (generatefunction.equalsIgnoreCase(GenerateScore.GENERATE_LOCAL)) {
				score=GenerateScore.generateScore(subClasses, elements);
				return score;
		}
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

	public Long getFriendid() {
		return friendid;
	}

	public void setFriendid(Long friendid) {
		this.friendid = friendid;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public ElementMatch[] getElementMatches() {

		return elements.toArray(new ElementMatch[elements.size()]);
	}

	public String getPrintName() {
		return PRINT_NAME;
	}

	public long getUidFriend() {
		return userid;
	}

	public long getUidUser() {
		// TODO Auto-generated method stub
		return friendid;
	}

	public void setGenerateScore(String GENERATE_SCORE_FUNCTION) {
		generatefunction = GENERATE_SCORE_FUNCTION;

	}

	public long getMatchId() {
		
		return MATCH_OTHER_ID;
	}
	public int getScore() {
		if(score==null)
			score=generateScore();
		return score;
	}
	public void addElementMatch(ElementMatch el) {
		elements.add(el);
		
	}

	public void removeAllElementMatches() {
		elements=new LinkedList<ElementMatch>();
		
	}
}
