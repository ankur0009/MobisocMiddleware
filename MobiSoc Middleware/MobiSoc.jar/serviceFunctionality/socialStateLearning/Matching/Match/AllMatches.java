package serviceFunctionality.socialStateLearning.Matching.Match;

import java.util.LinkedList;

import serviceFunctionality.socialStateLearning.Matching.Util.ElementMatch;
import serviceFunctionality.socialStateLearning.Matching.Util.GenerateScore;
import serviceFunctionality.socialStateLearning.Matching.Util.MatchInterface;


public class AllMatches implements MatchInterface {

	private LinkedList<MatchInterface> subClasses;
	private LinkedList<ElementMatch> elements;

	private Long userid;

	private Long friendid;
	
	private Integer score=null;

	public static final String NAME = "match";

	public static final String PRINT_NAME = "Match Total";

	private String generatefunction = GenerateScore.GENERATE_LOCAL;
	
	public double weight;

	public AllMatches(Long userid, Long friendid) {
		super();
		this.userid = userid;
		this.friendid = friendid;		
		weight = 1.0;
		subClasses = new LinkedList<MatchInterface>();
		elements = new LinkedList<ElementMatch>();
		subClasses.add(new RomanticMatch(userid,friendid));
		subClasses.add(new ActivityPartnerMatch(userid,friendid));
		subClasses.add(new HangoutMatch(userid,friendid));
		subClasses.add(new FriendshipMatch(userid,friendid));
		subClasses.add(new ChatMatch(userid,friendid));
		subClasses.add(new Other(userid,friendid));
		

		// subClasses.get(0).getSubMatchclasses()[0].getElementMatches()[0].setWeight(0.5);

	}

	public int generateScore() {

		if (generatefunction.equalsIgnoreCase(GenerateScore.GENERATE_DB)) {
			return GenerateScore.generateScoreFromDB(subClasses, elements);
		}
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

		return friendid;
	}

	public void setGenerateScore(String GENERATE_SCORE_FUNCTION) {
		generatefunction = GENERATE_SCORE_FUNCTION;
		for (int i = 0; i < subClasses.size(); i++) {
			subClasses.get(i).setGenerateScore(GENERATE_SCORE_FUNCTION);
		}

	}

	public long getMatchId() {		
		return MATCH_TYPE_ITER_ID;
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
