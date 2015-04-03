package serviceFunctionality.socialStateLearning.Matching.BasicProfileMatch;

import java.util.LinkedList;

import serviceFunctionality.socialStateLearning.Matching.Util.ElementMatch;
import serviceFunctionality.socialStateLearning.Matching.Util.GenerateScore;
import serviceFunctionality.socialStateLearning.Matching.Util.MatchInterface;
import serviceFunctionality.socialStateLearning.Matching.Util.ProfInterface;


/**
 * @author ken Match on profiles data , similar interests , hobbies, activities
 *         mentioned in user profile and preferences.
 */
public class EmptyMatchInterface implements MatchInterface {

	private LinkedList<MatchInterface> subClasses;

	private LinkedList<ElementMatch> elements;

	public String NAME = "";

	public String PRINT_NAME = "";
	
	private String generatefunction = GenerateScore.GENERATE_LOCAL;

	public double weight;

	public ProfInterface profIn;

	public EmptyMatchInterface(ProfInterface profIn) {
		super();
		this.profIn = profIn;
		weight = 1.0;
		subClasses = new LinkedList<MatchInterface>();
		elements = new LinkedList<ElementMatch>();

	}

	public void addElement(ElementMatch el) {
		elements.add(el);
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
		return (MatchInterface[]) subClasses
				.toArray(new MatchInterface[subClasses.size()]);
	}

	public double getWeight() {

		return weight;
	}

	public void setWeight(double weight) {

		this.weight = weight;
	}

	public ElementMatch[] getElementMatches() {

		return (ElementMatch[]) elements.toArray(new ElementMatch[elements
				.size()]);
	}

	public String getPrintName() {

		return PRINT_NAME;
	}

	public void setName(String name) {
		NAME = name;
	}

	public void setPrintName(String Printname) {
		PRINT_NAME = Printname;
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
		// TODO Auto-generated method stub
		return SUB_MATCH_TYPE_CUSTOM_ID;
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
