package serviceFunctionality.socialStateLearning.Matching.Util;

/**
 * @author ken
 * 
 */



public interface MatchInterface {

	public static final int MATCH_TYPE_ITER_ID = 1;
	
	public static final int MATCH_TYPE_ROMANTIC_MATCH_ID = 10000;
	public static final int MATCH_TYPE_ACTIVITY_PARTNER_ID = 10001;
	public static final int MATCH_TYPE_HANGOUT_ID = 10002;
	public static final int MATCH_TYPE_FRIENDSHIP_ID = 10003;
	public static final int MATCH_TYPE_HELP_ME_ID = 10004;	
	public static final int MATCH_TYPE_CHAT_ID = 10005;
	public static final int MATCH_TYPE_SURPRISE_ME_ID = 10006;	
	public static final int MATCH_TYPE_OPEN_TO_ID = 10007;	
	public static final int MATCH_TYPE_ALL_ID = 10008;
	public static final int MATCH_OTHER_ID = 10008;
	
	public static final int MATCH_TYPE_CUSTOM_ID = 10100;

	public static final int SUB_MATCH_TYPE_BASIC_PROFILE_ID = 20001;
	public static final int SUB_MATCH_TYPE_GEO_TEMPORAL_ID = 20002;
	public static final int SUB_MATCH_TYPE_SOCIAL_NETWORK_ID = 20003;
	public static final int SUB_MATCH_TYPE_USER_INTEREST_ID = 20004;
	public static final int SUB_MATCH_TYPE_PHYSICAL_ID = 20005;
	
	public static final int SUB_MATCH_TYPE_CUSTOM_ID = 20200;
	
	
	
	/**
	 * @param userid
	 * @param friendid
	 * @return Match Score Between 1-1000 using default weights (if no weights
	 *         are set) or using current weight setting.
	 */
	public abstract int generateScore();

	public abstract String getName();

	public abstract MatchInterface[] getSubMatchclasses();

	public abstract ElementMatch[] getElementMatches();

	public abstract void addElementMatch(ElementMatch el);
	
	public abstract void removeAllElementMatches();

	public abstract double getWeight();

	public abstract void setWeight(double weight);

	public abstract String getPrintName();

	public abstract long getUidUser();

	public abstract long getUidFriend();
	
	public abstract long getMatchId();

	public abstract void setGenerateScore(String GENERATE_SCORE_FUNCTION);
	
	public abstract int getScore();
}