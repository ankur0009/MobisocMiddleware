package serviceFunctionality.socialStateLearning.Matching.Util;

public interface ElementMatch {
	public static final int SPORTSWATCH_ID = 1000;
	public static final int SPORTSPLAY_ID = 1001;
	public static final int RELIGION_ID = 1002;
	public static final int STATE_ID = 1003;
	public static final int HOMETOWN_ID = 1004;
	public static final int CRISSCROSS_ID = 1005;
	public static final int COPRESENSE_ID = 1006;
	public static final int PRESENSE_ID = 1007;
	public static final int DRINK_ID = 1008;
	public static final int CUISINE_ID = 1009;
	public static final int FRIENDSKNOWSFRIENDS_ID = 1011;
	public static final int MUTUALFRIENDS_ID = 1012;
	public static final int COMMONGROUP_ID = 1013;
	public static final int COPRESENSEFRIENDS_ID = 1014;
	public static final int COMMUNICATIONFRIENDS_ID = 1015;
	public static final int POLITICAL_ID = 1016;
	public static final int COUNTRY_ID = 1017;
	public static final int EYECOLOR_ID = 1018;
	public static final int HEIGHT_ID = 1019;
	public static final int GENDER_ID = 1020;
	
	public static final String SOCIAL_NETWORKING_TYPE = "SocialNetwork";
	public static final String GEO_TEMPORAL_TYPE = "GeoTemporal";
	public static final String BASIC_TYPE = "Basic";
	public static final String INTEREST_TYPE = "Interest";
	public static final String PHYSICAL_TYPE = "Physical";
	public abstract String getName();

	public abstract int numEntryFirstUser();

	public abstract int numEntrySecondUser();

	public abstract int numMatchedEntries();

	public abstract void setScore(double score);

	public abstract double getWeight();

	public abstract void setWeight(double weight);

	public abstract double getScore();

	public abstract String getPrintName();

	public abstract int getId();

	public abstract String getDescription();

	public abstract int getDbnumEntryFirstUser();

	public abstract int getDbnumEntrySecondUser();

	public abstract int getDbnumMatchedEntries();

	public abstract void setDbnumEntryFirstUser(int a);

	public abstract void setDbnumEntrySecondUser(int a);

	public abstract void setDbnumMatchedEntries(int a);

	public abstract double getDbScore();

	public abstract void setDbScore(double score);
	
	public String getType();

}
