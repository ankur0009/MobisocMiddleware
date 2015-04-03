package serviceFunctionality.socialStateLearning.Matching.Util;

import serviceFunctionality.socialStateLearning.Matching.Match.ActivityPartnerMatch;
import serviceFunctionality.socialStateLearning.Matching.Match.ChatMatch;
import serviceFunctionality.socialStateLearning.Matching.Match.FriendshipMatch;
import serviceFunctionality.socialStateLearning.Matching.Match.HangoutMatch;
import serviceFunctionality.socialStateLearning.Matching.Match.Match;
import serviceFunctionality.socialStateLearning.Matching.Match.Other;
import serviceFunctionality.socialStateLearning.Matching.Match.RomanticMatch;

public class MatchFactory {

	public static MatchInterface getMatchInterface (int match_id,long uid,long fid){
		
		MatchInterface match=null;
		switch (match_id) {
		case MatchInterface.MATCH_TYPE_ROMANTIC_MATCH_ID:
			match=new RomanticMatch(uid,fid);			
			break;
		case MatchInterface.MATCH_TYPE_FRIENDSHIP_ID:
			match=new FriendshipMatch(uid,fid);			
			break;
		case MatchInterface.MATCH_TYPE_HANGOUT_ID:
			match=new HangoutMatch(uid,fid);			
			break;
		case MatchInterface.MATCH_TYPE_CHAT_ID:
			match=new ChatMatch(uid,fid);			
			break;
		case MatchInterface.MATCH_TYPE_ACTIVITY_PARTNER_ID:
			match=new ActivityPartnerMatch(uid,fid);			
			break;
		case MatchInterface.MATCH_OTHER_ID:
			match=new Other(uid,fid);			
			break;

		default:
			match=new Match(uid,fid);
			break;
		}
		
		return match;
	}
}
