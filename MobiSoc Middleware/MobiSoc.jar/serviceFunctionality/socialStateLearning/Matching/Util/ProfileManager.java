package serviceFunctionality.socialStateLearning.Matching.Util;

import serviceFunctionality.socialStateLearning.Matching.Facebook.FacebookDataBaseQuery;
import serviceFunctionality.socialStateLearning.Matching.Facebook.FacebookPersistance;
import dataObjects.socialStateLearning.Matching.Facebook_profileDAO;


public class ProfileManager implements ProfInterface {

	private Long uid = null;
	private Long fid = null;
	private Facebook_profileDAO prof1;
	private Facebook_profileDAO prof2;
	/**
	 * 
	 */
	public ProfileManager(long userid, long friendid) {
		super();
		this.uid = new Long(userid);
		this.fid = new Long(friendid);
		prof1=FacebookPersistance.getProfile(uid);
		prof2=FacebookPersistance.getProfile(fid);
	}
	

	public long getUserId1() {
		// TODO Auto-generated method stub
		return new Long(uid);
	}

	public long getUserId2() {
		// TODO Auto-generated method stub
		return new Long(fid);
	}

	public int numMutualFriends() {
		try {
			int retval=FacebookDataBaseQuery.getCommonFriends(uid, fid).length;
			return retval;
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return 0;
	}

	

	public int numOfPoliticalFriend() {		
		return 1;
	}

	public int numOfPoliticalUser() {		
		return 1;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numCommonGroup()
	 */
	public int numCommonGroup() {
		try {
			int retval=FacebookDataBaseQuery.getCommonGroups(uid, fid).length;
			return retval;
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numCommunicationFriends()
	 */
	public int numCommunicationFriends() {
		// TODO Auto-generated method stub
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numCoPresense()
	 */
	public int numCoPresense() {
		try {
			int retval=FacebookDataBaseQuery.getCountCopresense(uid, fid);
			return retval;
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numCoPresenseFriends()
	 */
	public int numCoPresenseFriends() {
		// TODO Auto-generated method stub
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numCountry()
	 */
	public int numCountry() {
		// TODO Auto-generated method stub
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numCrissCross()
	 */
	public int numCrissCross() {
		// TODO Auto-generated method stub
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numCuisine()
	 */
	public int numCuisine() {
		// TODO Auto-generated method stub
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numDrink()
	 */
	public int numDrink() {
		try {
			int retval=FacebookDataBaseQuery.getCommonFacebookElementRatings(uid,fid,ElementMatch.DRINK_ID).length;
			return retval;
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numEyeColor()
	 */
	public int numEyeColor() {
		// TODO Auto-generated method stub
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numFriendsKnowsFriends()
	 */
	public int numFriendsKnowsFriends() {
		// TODO Auto-generated method stub
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numHeight()
	 */
	public int numHeight() {
		// TODO Auto-generated method stub
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numHomeTown()
	 */
	public int numHomeTown() {
		// TODO Auto-generated method stub
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numOfCommonGroupFriend()
	 */
	public int numOfCommonGroupFriend() {
		try {
			int retval=FacebookDataBaseQuery.getGroups(fid).length;
			return retval;
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numOfCommonGroupUser()
	 */
	public int numOfCommonGroupUser() {
		try {
			int retval=FacebookDataBaseQuery.getGroups(uid).length;
			return retval;
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		}
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numOfCommunicationFriendsFriend()
	 */
	public int numOfCommunicationFriendsFriend() {
		// TODO Auto-generated method stub
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numOfCommunicationFriendsUser()
	 */
	public int numOfCommunicationFriendsUser() {
		// TODO Auto-generated method stub
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numOfCoPresenseFriend()
	 */
	public int numOfCoPresenseFriend() {
		try {
			int retval=FacebookDataBaseQuery.getCountOnlineTogether(fid,uid);
			return retval;
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numOfCoPresenseFriendsFriend()
	 */
	public int numOfCoPresenseFriendsFriend() {
		// TODO Auto-generated method stub
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numOfCoPresenseFriendsUser()
	 */
	public int numOfCoPresenseFriendsUser() {
		// TODO Auto-generated method stub
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numOfCoPresenseUser()
	 */
	public int numOfCoPresenseUser() {
		try {
			int retval=FacebookDataBaseQuery.getCountOnlineTogether(uid,fid);
			return retval;
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numOfCountryFriend()
	 */
	public int numOfCountryFriend() {
		// TODO Auto-generated method stub
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numOfCountryUser()
	 */
	public int numOfCountryUser() {
		// TODO Auto-generated method stub
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numOfCrissCrossFriend()
	 */
	public int numOfCrissCrossFriend() {
		// TODO Auto-generated method stub
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numOfCrissCrossUser()
	 */
	public int numOfCrissCrossUser() {
		// TODO Auto-generated method stub
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numOfCuisineFriend()
	 */
	public int numOfCuisineFriend() {
		// TODO Auto-generated method stub
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numOfCuisineUser()
	 */
	public int numOfCuisineUser() {
		// TODO Auto-generated method stub
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numOfDrinkFriend()
	 */
	public int numOfDrinkFriend() {
		try {
			int retval=FacebookDataBaseQuery.getFacebookElementRatings(fid, ElementMatch.DRINK_ID).length;
			return retval;
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numOfDrinkUser()
	 */
	public int numOfDrinkUser() {
		try {
			int retval=FacebookDataBaseQuery.getFacebookElementRatings(uid, ElementMatch.DRINK_ID).length;
			return retval;
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numOfEyeColorFriend()
	 */
	public int numOfEyeColorFriend() {
		// TODO Auto-generated method stub
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numOfEyeColorUser()
	 */
	public int numOfEyeColorUser() {
		// TODO Auto-generated method stub
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numOfFriendsKnowsFriendsFriend()
	 */
	public int numOfFriendsKnowsFriendsFriend() {
		// TODO Auto-generated method stub
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numOfFriendsKnowsFriendsUser()
	 */
	public int numOfFriendsKnowsFriendsUser() {
		// TODO Auto-generated method stub
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numOfHeightFriend()
	 */
	public int numOfHeightFriend() {
		// TODO Auto-generated method stub
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numOfHeightUser()
	 */
	public int numOfHeightUser() {
		// TODO Auto-generated method stub
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numOfHomeTownFriend()
	 */
	public int numOfHomeTownFriend() {
		// TODO Auto-generated method stub
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numOfHomeTownUser()
	 */
	public int numOfHomeTownUser() {
		// TODO Auto-generated method stub
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numOfMutualFriendsFriend()
	 */
	public int numOfMutualFriendsFriend() {
		try {
			int retval=FacebookDataBaseQuery.getFriends(fid).length;
			return retval;
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numOfMutualFriendsUser()
	 */
	public int numOfMutualFriendsUser() {
		try {
			int retval=FacebookDataBaseQuery.getFriends(uid).length;
			return retval;
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		}
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numOfPresenseFriend()
	 */
	public int numOfPresenseFriend() {
		try {
			int retval=FacebookDataBaseQuery.getTotalSpaceCount(fid);
			return retval;
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numOfPresenseUser()
	 */
	public int numOfPresenseUser() {
		try {
			int retval=FacebookDataBaseQuery.getTotalSpaceCount(uid);
			return retval;
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numOfReligionFriend()
	 */
	public int numOfReligionFriend() {
		// TODO Auto-generated method stub
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numOfReligionUser()
	 */
	public int numOfReligionUser() {
		// TODO Auto-generated method stub
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numOfsportsplayFriend()
	 */
	public int numOfsportsplayFriend() {
		try {
			int retval=FacebookDataBaseQuery.getFacebookElementRatings(fid, ElementMatch.SPORTSPLAY_ID).length;
			return retval;
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numOfsportsplayUser()
	 */
	public int numOfsportsplayUser() {
		try {
			int retval=FacebookDataBaseQuery.getFacebookElementRatings(uid, ElementMatch.SPORTSPLAY_ID).length;
			return retval;
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numOfsportswatchFriend()
	 */
	public int numOfsportswatchFriend() {
		try {
			int retval=FacebookDataBaseQuery.getFacebookElementRatings(fid, ElementMatch.SPORTSWATCH_ID).length;
			return retval;
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numOfsportswatchUser()
	 */
	public int numOfsportswatchUser() {
		try {
			int retval=FacebookDataBaseQuery.getFacebookElementRatings(uid, ElementMatch.SPORTSWATCH_ID).length;
			return retval;
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numOfStateFriend()
	 */
	public int numOfStateFriend() {
		// TODO Auto-generated method stub
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numOfStateUser()
	 */
	public int numOfStateUser() {
		// TODO Auto-generated method stub
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numPolitical()
	 */
	public int numPolitical() {
		try {
			if(prof1.political.equalsIgnoreCase(prof2.political))
				return 1;			
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		}
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numPresense()
	 */
	public int numPresense() {
		try {
			int retval=FacebookDataBaseQuery.getTotalSpaceCount(uid, fid);
			return retval;
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numReligion()
	 */
	public int numReligion() {
		// TODO Auto-generated method stub
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numsportsplay()
	 */
	public int numsportsplay() {
		try {
			int retval=FacebookDataBaseQuery.getCommonFacebookElementRatings(uid,fid,ElementMatch.SPORTSPLAY_ID).length;
			return retval;
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numsportswatch()
	 */
	public int numsportswatch() {
		try {
			int retval=FacebookDataBaseQuery.getCommonFacebookElementRatings(uid,fid,ElementMatch.SPORTSWATCH_ID).length;
			return retval;
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return 0;
	}


	/* (non-Javadoc)
	 * @see match.util.ProfInterface#numState()
	 */
	public int numState() {
		// TODO Auto-generated method stub
		return 0;
	}


	public int numGender() {
		// TODO Auto-generated method stub
		return 0;
	}


	public int numOfGenderFriend() {
		// TODO Auto-generated method stub
		return 0;
	}


	public int numOfGenderUser() {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
