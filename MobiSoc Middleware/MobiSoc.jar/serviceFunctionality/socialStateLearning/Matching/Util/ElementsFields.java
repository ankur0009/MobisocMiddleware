package serviceFunctionality.socialStateLearning.Matching.Util;

public class ElementsFields {
	String type = "";
	String classname = "";
	String PRINT_NAME = "";
	String DESCRIPTION = "";

	/**
	 * @param type
	 * @param classname
	 * @param print_name
	 * @param description
	 */
	public ElementsFields(String type, String classname, String print_name,
			String description) {
		super();
		this.type = type;
		this.classname = classname;
		PRINT_NAME = print_name;
		DESCRIPTION = description;
	}
}
