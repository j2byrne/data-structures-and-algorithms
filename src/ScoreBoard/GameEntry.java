package ScoreBoard;

//public class GameEntry {

//}

public class GameEntry {
	/* name of the person earning this score */
	protected String name;
	
	/* the score value */
	protected int score;

	/* Constructor to create a game entry */
	public GameEntry(String name, int score) {
		// TODO
		this.name = name;
		this.score = score;
	}

	/* Retrieves the name field */
	public String getName() {
		// TODO
		return this.name;
	}

	/* Retrieves the score field */
	public int getScore() {
		// TODO
		
		return this.score;
	}

	/*
	 * Sets the name field
	 * 
	 * @param {String}
	 *            name - Given a person's name
	 */
	public void setName(String name) {
		// TODO
		this.name = name;
	}

	/*
	 * Sets the score field
	 * 
	 * @param {int}
	 *            score - Given the score the person got
	 */
	public void setScore(int score) {
		// TODO
		this.score = score;
	}

	/* Returns a string representation of this entry */
	public String toString() {
		// TODO
		
		return "GameEntry[" + this.name + ", " + this.score + "]";
	}
}
