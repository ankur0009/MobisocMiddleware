package serviceFunctionality.socialStateLearning.Matching.Util;

import dataObjects.socialStateLearning.Matching.BasicCountry;
import dataObjects.socialStateLearning.Matching.BasicHomeTown;
import dataObjects.socialStateLearning.Matching.BasicPolitical;
import dataObjects.socialStateLearning.Matching.BasicReligion;
import dataObjects.socialStateLearning.Matching.GeoTemporalCoPresense;
import dataObjects.socialStateLearning.Matching.GeoTemporalCrissCross;
import dataObjects.socialStateLearning.Matching.GeoTemporalPresense;
import dataObjects.socialStateLearning.Matching.InterestCuisine;
import dataObjects.socialStateLearning.Matching.InterestDrink;
import dataObjects.socialStateLearning.Matching.Interestsportsplay;
import dataObjects.socialStateLearning.Matching.Interestsportswatch;
import dataObjects.socialStateLearning.Matching.PhysicalEyeColor;
import dataObjects.socialStateLearning.Matching.PhysicalGender;
import dataObjects.socialStateLearning.Matching.PhysicalHeight;
import dataObjects.socialStateLearning.Matching.SocialNetworkCoPresenseFriends;
import dataObjects.socialStateLearning.Matching.SocialNetworkCommonGroup;
import dataObjects.socialStateLearning.Matching.SocialNetworkCommunicationFriends;
import dataObjects.socialStateLearning.Matching.SocialNetworkFriendsKnowsFriends;
import dataObjects.socialStateLearning.Matching.SocialNetworkMutualFriends;

public class ElementFactory {

public static ElementMatch getElementMatchInterface (String elemname){
		
		
		if(elemname.equalsIgnoreCase("mutualfriends")){

		      return new SocialNetworkMutualFriends();
		}
		if(elemname.equalsIgnoreCase("commongroup")){

		      return new SocialNetworkCommonGroup();
		}
		if(elemname.equalsIgnoreCase("communicationfriends")){

		      return new SocialNetworkCommunicationFriends();
		}
		if(elemname.equalsIgnoreCase("friendsknowsfriends")){

		      return new SocialNetworkFriendsKnowsFriends();
		}
		if(elemname.equalsIgnoreCase("copresensefriends")){

		      return new SocialNetworkCoPresenseFriends();
		}
		if(elemname.equalsIgnoreCase("crisscross")){

		      return new GeoTemporalCrissCross();
		}
		if(elemname.equalsIgnoreCase("presense")){

		      return new GeoTemporalPresense();
		}
		if(elemname.equalsIgnoreCase("copresense")){

		      return new GeoTemporalCoPresense();
		}
		if(elemname.equalsIgnoreCase("country")){

		      return new BasicCountry();
		}
		if(elemname.equalsIgnoreCase("hometown")){

		      return new BasicHomeTown();
		}
		if(elemname.equalsIgnoreCase("political")){

		      return new BasicPolitical();
		}
		if(elemname.equalsIgnoreCase("religion")){

		      return new BasicReligion();
		}
		if(elemname.equalsIgnoreCase("cuisine")){

		      return new InterestCuisine();
		}
		if(elemname.equalsIgnoreCase("drink")){

		      return new InterestDrink();
		}
		if(elemname.equalsIgnoreCase("sportsplay")){

		      return new Interestsportsplay();
		}
		if(elemname.equalsIgnoreCase("sportswatch")){

		      return new Interestsportswatch();
		}
		if(elemname.equalsIgnoreCase("height")){

		      return new PhysicalHeight();
		}
		if(elemname.equalsIgnoreCase("eyecolor")){

		      return new PhysicalEyeColor();
		}
		if(elemname.equalsIgnoreCase("gender")){

		      return new PhysicalGender();
		}
		
		return null;
	}
}
