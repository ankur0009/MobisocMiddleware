package serviceFunctionality.socialStateLearning.Matching.Util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;



public class ElementClassGenerator {

	public static final String SOCIAL_NETWORKING_TYPE = "SocialNetwork";
	public static final String GEO_TEMPORAL_TYPE = "GeoTemporal";
	public static final String BASIC_TYPE = "Basic";
	public static final String INTEREST_TYPE = "Interest";
	public static final String PHYSICAL_TYPE = "Physical";

	public static void main(String[] args) {

		ElementsFields elements[] = {
				new ElementsFields(SOCIAL_NETWORKING_TYPE, "MutualFriends",
						"Mutual Friends", "Do We Have Common Friends?"),

				new ElementsFields(SOCIAL_NETWORKING_TYPE, "CommonGroup",
						"Common Group", "Are We Members of The Same Groups?"),
				new ElementsFields(SOCIAL_NETWORKING_TYPE,
						"CommunicationFriends", "Talk To My Friends",
						"Does She/He Communicate With My Friends?"),
				new ElementsFields(SOCIAL_NETWORKING_TYPE,
						"FriendsKnowsFriends",
						"My Friends Knows Her/His Friends",
						"Do my friends knows his/her friends"),
				new ElementsFields(SOCIAL_NETWORKING_TYPE, "CoPresenseFriends",
						"Co Presense With Friends",
						"Does She/He Spend Time in Physical Proximity of My Friends?"),

				new ElementsFields(GEO_TEMPORAL_TYPE, "CrissCross",
						"Familiar Stranger", "Do I see Her/Him Often."),

				new ElementsFields(GEO_TEMPORAL_TYPE, "Presense",
						"Common Places",
						"Do We Spend Time at the Same Places, but maybe at different times?"),

				new ElementsFields(GEO_TEMPORAL_TYPE, "CoPresense",
						"Co-Presense",
						"Do We Spend Time at the Same Places at The Same Time?"),

				new ElementsFields(BASIC_TYPE, "Country",
						"Nationality/Country Of Origin", "Her/His Nationality"),

				new ElementsFields(BASIC_TYPE, "HomeTown", "HomeTown",
						"Her/His HomeTown"),

				new ElementsFields(BASIC_TYPE, "Political", "Political View",
						"Her/His Political View"),

				new ElementsFields(BASIC_TYPE, "Religion", "Religion",
						"Her/His Religion"),

				// new ElementsFields(BASIC_TYPE, "State", "State", ""),

				new ElementsFields(INTEREST_TYPE, "Cuisine", "Cuisine",
						"Do we like similar Food?"),

				new ElementsFields(INTEREST_TYPE, "Drink", "Cocktails I Like",
						"Do we like similar  Drinks?"),

				new ElementsFields(INTEREST_TYPE, "sportsplay",
						"Sports I Play", "Do we like to Play the Same Sports?"),

				new ElementsFields(INTEREST_TYPE, "sportswatch",
						"Sports I Watch",
						"Do we like to Watch the Same Sports?"),

				new ElementsFields(PHYSICAL_TYPE, "Height", "Height",
						"Her/His Height"),

				new ElementsFields(PHYSICAL_TYPE, "EyeColor", "Eye Color",
						"Her/His Eye Color"),
				new ElementsFields(PHYSICAL_TYPE, "Gender", "Gender",
						"Her/His Gender") };

		//createElementFiles(elements);
		// createAddFiles(elements);
		// createImages(elements);
		// createMatchFactory(elements);
		createJsp(elements);

	}

	static void createMatchFactory(ElementsFields[] elements) {
		for (int i = 0; i < elements.length; i++) {

			System.out.println("if(elemname.equalsIgnoreCase(\""+elements[i].classname.toLowerCase()+"\")){\n");
			System.out.println("      return new "+elements[i].type
						+ elements[i].classname+"();\n}");
		}

	}

	static void createImages(ElementsFields elements[]) {

		try {
			BufferedWriter out = new BufferedWriter(
					new FileWriter("images.bat"));
			for (int i = 0; i < elements.length; i++) {
				out
						.write("copy noname.jpg WebContent\\static\\images\\element\\"
								+ elements[i].classname.toLowerCase()
								+ ".jpg\n");
			}
			out.close();

		} catch (Exception e) {
			System.err.println(e);
		}
	}

	static void createAddFiles(ElementsFields elements[]) {

		for (int i = 0; i < elements.length; i++) {
			System.out.println("elements.add(new " + elements[i].type
					+ elements[i].classname + "((profIn)));");
		}
	}

	static void createElementFiles(ElementsFields elements[]) {

		for (int i = 0; i < elements.length; i++) {

			try {
				BufferedWriter out = new BufferedWriter(new FileWriter(
						"src/elements/" + elements[i].type.toLowerCase() + "/"
								+ elements[i].type + elements[i].classname
								+ ".java"));
				
				out.write("package elements." + elements[i].type.toLowerCase()
						+ ";\n" +

						"import match.util.ElementMatch;\n"
						+ "import match.util.ProfInterface;\n" +

						"public class " + elements[i].type
						+ elements[i].classname
						+ " implements ElementMatch {\n"
						+ "	public static final String NAME = \""
						+ elements[i].classname.toLowerCase() + "\";\n" +

						"	public static final String PRINT_NAME = \""
						+ elements[i].PRINT_NAME + "\";\n" +

						"	public static final String DESCRIPTION = \""
						+ elements[i].DESCRIPTION + "\";\n"
						
						+"	public static final String TYPE = \""
						+ elements[i].type + "\";\n"
						
						+ "	public int numEntryFirstUser = 0;\n"
						+ "	public int numEntrySecondUser = 0;\n"
						+ "	public int numEntryMatched = 0;\n"
						+ "	public double dbscore = 0;\n"
						+ "	private double score;\n"
						+ "	public double weight;\n"
						+ "	ProfInterface profIn;\n\n"

						+ " public " + elements[i].type + elements[i].classname
						+ "() {\n" + "       super();\n" + " }\n"

						+ "	public " + elements[i].type + elements[i].classname
						+ "(ProfInterface profIn) {\n" + "		super();\n"
						+ "		this.profIn = profIn;\n"
						+ "		this.setWeight(1.0);\n" + "		this.setScore(0);\n"
						+ "	}\n" +

						"	public String getName() {\n" + "		return NAME;\n"
						+ "	}\n" +

						"	public int numEntryFirstUser() {\n"
						+ "		return profIn.numOf" + elements[i].classname
						+ "User();\n" + "	}\n" +

						"	public int numEntrySecondUser() {\n"
						+ "		return profIn.numOf" + elements[i].classname
						+ "Friend();\n" + "	}\n" +

						"	public int numMatchedEntries() {\n"
						+ "		return profIn.num" + elements[i].classname
						+ "();\n" + "	}\n" +

						"	public double getWeight() {\n" +

						"		return weight;\n" + "	}\n" +

						"	public void setWeight(double weight) {\n" +

						"		this.weight = weight;\n" + "	}\n" +

						"	public double getScore() {\n" + "		return score;\n"
						+ "	}\n" +

						"	public void setScore(double score) {\n"
						+ "		this.score = score;\n" + "	}\n" +

						"	public int getId() {\n" + "		return ElementMatch."
						+ elements[i].classname.toUpperCase() + "_ID;\n"
						+ "	}\n" +

						"	public String getPrintName() {\n"
						+ "		return PRINT_NAME;\n" + "	}\n" +

						"	public String getType() {\n"
						+ "		return TYPE;\n" + "	}\n" +

						"	public String getDescription() {\n"
						+ "		// TODO Auto-generated method stub\n"
						+ "		return DESCRIPTION;\n" + "	}\n" +

						"	public double getDbScore() {\n"
						+ "		return dbscore;\n" + "	}\n" +

						"	public int getDbnumEntryFirstUser() {\n"
						+ "		return numEntryFirstUser;\n" + "	}\n" +

						"	public int getDbnumEntrySecondUser() {\n"
						+ "		return numEntrySecondUser;\n" + "	}\n" +

						"	public int getDbnumMatchedEntries() {\n"
						+ "		return numEntryMatched;\n" + "	}\n" +

						"	public void setDbScore(double score) {\n"
						+ "		dbscore = score;\n" + "	}\n" +

						"	public void setDbnumEntryFirstUser(int a) {\n"
						+ "		numEntryFirstUser = a;\n" + "	}\n" +

						"	public void setDbnumEntrySecondUser(int a) {\n"
						+ "		numEntrySecondUser = a;\n" + "	}\n" +

						"	public void setDbnumMatchedEntries(int a) {\n"
						+ "		numEntryMatched = a;\n" + "	}\n" + "}\n");
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	static void createJsp(ElementsFields elements[]){
		
		for (int i = 0; i < elements.length; i++) {

			try {
				BufferedWriter out = new BufferedWriter(new FileWriter(
						"src/addelements/"+elements[i].classname
								+ ".jsp"));
				out.write("		<%@ page language=\"java\" contentType=\"text/html; charset=ISO-8859-1\"\n"
+"		    pageEncoding=\"ISO-8859-1\"%>\n"
+"		<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n"



+"		<%@page import=\"match.Match\"%>\n"
+"		<%@page import=\"match.util.MatchInterface\"%>\n"
+"		<%@page import=\"match.util.ElementMatch\"%>\n"
+"		<%@page import=\"facebook.match.MatchingFacebookWeb\"%>\n"
+"		<%@page import=\"facebook.match.helper\"%>\n"
+"		<link\n"
+"			href=\"/Matching/static/styles/facebook.global.css?release=20070523\"\n"
+"			type=\"text/css\" rel=\"stylesheet\">\n"

+"		<style type=\"text/css\">\n"
+"		.body {\n"
+"			background: #fff;\n"
+"			font-family: \"lucida grande\", tahoma, verdana, arial, sans-serif;\n"
+"		}\n"

+"		#mct .submit {\n"
+"			margin: 5px 0px;\n"
+"		}\n"

+"		#mct .submit input {\n"
+"			float: right;\n"
+"			margin-right: 8px;\n"
+"		}\n"

+"		#mct table {\n"
+"			margin-top: 10px;\n"
+"		}\n"

+"		#mct table th {\n"
+"			vertical-align: top;\n"
+"			text-align: left;\n"
+"			background-color: #d8dfea;\n"
+"			border-top: 1px solid #3b5998;\n"
+"			border-bottom: 1px solid #000;\n"
+"			font-size: 12px;\n"
+"			color: #3b5998;\n"
+"		}\n"

+"		#mct table td {\n"
+"			vertical-align: top;\n"
+"			text-align: left;\n"
+"			padding: 4px 0px;\n"
+"		}\n"

+"		#mct table .odd td {\n"
+"			background-color: #f6f6f6;\n"
+"		}\n"

+"		#mct table .movie {\n"
+"			width: 216px;\n"
+"			padding-right: 4px;\n"
+"		}\n"

+"		#mct table .movie {\n"
+"			font-weight: bold;\n"
+"		}\n"

+"		#mct table .movie img {\n"
+"			float: left;\n"
+"			margin-right: 4px;\n"
+"		}\n"

+"		#mct table .rating {\n"
+"			width: 250px;\n"
+"		}\n"

+"		#mct table .rating .stars {\n"
+"			float: left;\n"
+"			width: 85px;\n"
+"			height: 21px;\n"
+"			background: url(/Matching/static/images/rating/stars.bg.gif) 0px 0px\n"
+"				no-repeat;\n"
+"			text-align: center;\n"
+"		}\n"

+"		#mct table .rating .stars img {\n"
+"			margin: 1px 0px;\n"
+"			cursor: pointer;\n"
+"			zoom: 1;\n"
+"			vertical-align: middle;\n"
+"		}\n"

+"		#mct table .rating .buttons {\n"
+"			float: left;\n"
+"			font-size: 0px;\n"
+"			line-height: 0px;\n"
+"			margin: 2px 0px 0px 0px;\n"
+"			padding: 0px;\n"
+"		}\n"

+"		#mct table .rating .buttons img {\n"
+"			cursor: pointer;\n"
+"			margin: 0px;\n"
+"			padding: 0px;\n"
+"		}\n"

+"		#mct table .comment {\n"
+"			width: 250px;\n"
+"			text-align: right;\n"
+"			font-size: 13px;\n"
+"		}\n"

+"		#mct table .comment textarea {\n"
+"			height: 50px;\n"
+"			width: 140px;\n"
+"			margin: 0px;\n"
+"			border: 1px solid gray;\n"
+"			overflow: auto;\n"
+"		}\n"
+"		</style>\n"

+"		<script type=\"text/javascript\"\n"
+"			src=\"/Matching/static/scripts/prototype.js\"></script>\n"
+"		<script type=\"text/javascript\"\n"
+"			src=\"/Matching/static/scripts/facebook.global.js\"></script>\n"
+"		<script type=\"text/javascript\"\n"
+"			src=\"/Matching/static/scripts/facebook.mct.js\"></script>\n"




+"		<form id=\"mct\" name=\"mct\"\n"
+"			action=\"<%=MatchingFacebookWeb.SERVER_URL %>/Matching?page=saveelement.jsp\"\n"
+"			method=\"post\">\n"
+"			\n"
+"				<input type=\"hidden\" name=\"fb_sig_expires\" value=\"<%=request.getParameter(\"fb_sig_expires\") %>\" />		\n"
+"				<input type=\"hidden\" name=\"fb_sig_session_key\" value=\"<%=request.getParameter(\"fb_sig_session_key\") %>\" />	\n"
+"				<input type=\"hidden\" name=\"fb_sig_in_iframe\" value=\"<%=request.getParameter(\"fb_sig_in_iframe\") %>\" />	\n"
+"				<input type=\"hidden\" name=\"fb_sig_profile_update_time\" value=\"<%=request.getParameter(\"fb_sig_profile_update_time\") %>\" />		\n"
+"				<input type=\"hidden\" name=\"fb_sig_added\" value=\"<%=request.getParameter(\"fb_sig_added\") %>\" />\n"
+"				<input type=\"hidden\" name=\"fb_sig\" value=\"<%=request.getParameter(\"fb_sig\") %>\" />		\n"
+"				<input type=\"hidden\" name=\"fb_sig_time\" value=\"<%=request.getParameter(\"fb_sig_time\") %>\" />	\n"
+"				<input type=\"hidden\" name=\"fb_sig_user\" value=\"<%=request.getParameter(\"fb_sig_user\") %>\" />	\n"
+"				<input type=\"hidden\" name=\"fb_sig_api_key\" value=\"<%=request.getParameter(\"fb_sig_api_key\") %>\" />\n"
+"				<input type=\"hidden\" name=\"elementname\" value=\""+elements[i].classname.toLowerCase()+"\" />\n"
+"			\n"
+"		<%\n"

+"		%>\n"
+"		<h3>"+elements[i].PRINT_NAME+"</h3>\n"
+"		<%\n"
+"		for (int i = 0; i < helper."+elements[i].classname.toLowerCase()+".length; i++) {\n"
+"		%>\n"




+"		<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\">\n"
+"			<tr>\n"
+"				<th class=\"movie\">Element</th>\n"
+"				<th class=\"rating\">Importance</th>\n"
+"				<th class=\"comment\">Description</th>\n"
+"			</tr>\n"

+"			<%\n"
+"			if (i % 2 == 0) {\n"
+"			%>\n"
+"			<tr class=\"even\">\n"
+"				<%\n"
+"				} else {\n"
+"				%>\n"
+"			\n"
+"			<tr class=\"odd\">\n"
+"				<%\n"
+"				}\n"
+"				%>\n"
+"				<td class=\"movie\"><%=helper."+elements[i].classname.toLowerCase()+"[i]%></td>\n"
+"				<td class=\"rating clearfix\"><input type=\"hidden\"\n"
+"					id=\"score<%=i%>\" name=\"score<%=i%>\"\n"
+"					value=\"-1\" />\n"
+"					<input type=\"hidden\"\n"
+"					id=\"factor<%=i%>\" name=\"factor<%=i%>\"\n"
+"					value=\"<%=helper."+elements[i].classname.toLowerCase()+"[i]%>\" />\n"
+"				<div class=\"stars\"><img\n"
+"					src=\"/Matching/static/images/rating/0.0.gif\"\n"
+"					id=\"stars<%=i%>\" alt=\"Click to Rate\" /></div>\n"
+"				<div class=\"buttons\"><img\n"
+"					src=\"/Matching/static/images/rating/ni.off.gif\" size=\"medium\"\n"
+"					id=\"ni<%=i%>\" alt=\"Not Interested\" />\n"
+"				</div>\n"
+"				<script type=\"text/javascript\">\n"
+"						new RatingWidget('782965088', '<%=i%>', -1, 'FBM');\n"
+"					</script></td>\n"
+"				<td class=\"comment\"><%=helper."+elements[i].classname.toLowerCase()+"[i]%></td>\n"

+"			</tr>\n"


+"			<%\n"
+"			} // END FOR\n"
+"			%>\n"
+"		</table>\n"


+"		<div class=\"submit clearfix\"><input type=\"submit\" name=\"submit\"\n"
+"			value=\"Save &gt;\" /></div>\n"

+"		</form>");
				
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
