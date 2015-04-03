package serviceFunctionality.socialStateLearning.Matching.Util;

import java.util.Iterator;
import java.util.LinkedList;


public class GenerateScore {

	public static final String GENERATE_LOCAL="generateScore";
	public static final String GENERATE_DB="generateScoreFromDB";
	/*
	 * Based on 2*NumCommon/num A + num B
	 */
	public static int generateScoreFromDB(
			LinkedList<MatchInterface> subClasses,
			LinkedList<ElementMatch> elements) {
		double total_weight = 0;
		int score = 0;

		ElementMatch el;
		MatchInterface mat;
		Iterator<ElementMatch> iterel;
		Iterator<MatchInterface> itermatch;

		iterel = elements.iterator();
		while (iterel.hasNext()) {
			el = iterel.next();
			total_weight += el.getWeight();
		}
		itermatch = subClasses.iterator();
		while (itermatch.hasNext()) {
			mat = itermatch.next();
			total_weight += mat.getWeight();
		}
		double temp;
		double elmlocalscore = 0.0;

		iterel = elements.iterator();
		while (iterel.hasNext()) {
			el = iterel.next();
			temp = (el.getWeight() / total_weight)
					* 2000
					* el.getDbnumMatchedEntries()
					/ (el.getDbnumEntrySecondUser() + el
							.getDbnumEntryFirstUser());

			if (temp > 0 && temp < 1001) {
				score += temp;
			}
		}
		
		itermatch = subClasses.iterator();
		
		while (itermatch.hasNext()) {
			mat = itermatch.next();
			score += (mat.getWeight() / total_weight) * mat.generateScore();
		}
		return score;
	}

	/*
	 * Based on 2*NumCommon/num A + num B
	 */
	public static int generateScore(LinkedList<MatchInterface> subClasses,
			LinkedList<ElementMatch> elements) {
		double total_weight = 0;
		int score = 0;

		ElementMatch el;
		MatchInterface mat;
		Iterator<ElementMatch> iterel;
		Iterator<MatchInterface> itermatch;

		iterel = elements.iterator();
		while (iterel.hasNext()) {
			el = iterel.next();
			total_weight += el.getWeight();
		}
		itermatch = subClasses.iterator();
		while (itermatch.hasNext()) {
			mat = itermatch.next();
			total_weight += mat.getWeight();
		}
		double temp;
		double elmlocalscore = 0.0;

		iterel = elements.iterator();
		while (iterel.hasNext()) {
			el = iterel.next();
			temp = (el.getWeight() / total_weight) * 2000
					* el.numMatchedEntries()
					/ (el.numEntryFirstUser() + el.numEntrySecondUser());

			try {
				elmlocalscore = 2000 * el.numMatchedEntries()
						/ (el.numEntryFirstUser() + el.numEntrySecondUser());
			} catch (ArithmeticException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}

			if (temp >= 0 && temp <= 1000) {
				score += temp;
				el.setScore(elmlocalscore);
			} else {
				el.setScore(0);
			}
		}
		itermatch = subClasses.iterator();
		while (itermatch.hasNext()) {
			mat = itermatch.next();
			score += (mat.getWeight() / total_weight) * mat.generateScore();
		}
		return score;
	}

	// 2. Set A intersect B/Set A U Set B

	// 3. based on absosulte commonset, e.g if we have 5 friends in common then
	// score is 1.

}
