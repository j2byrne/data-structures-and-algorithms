package ScoreBoard;

//public class GameEntry {

//}

public class GameEntryGeneric<K, V extends Comparable<? super V>> {
	/* name of the person earning this score */
	protected K name;
	
	/* the score value */
	protected V score;

	/* Constructor to create a game entry */
	public GameEntryGeneric(K name, V score) {
		// TODO
		this.name = name;
		this.score = score;
	}

	/* Retrieves the name field */
	public K getName() {
		// TODO
		return this.name;
	}

	/* Retrieves the score field */
	public V getScore() {
		// TODO
		
		return this.score;
	}

	/*
	 * Sets the name field
	 * 
	 * @param {String}
	 *            name - Given a person's name
	 */
	public void setName(K name) {
		// TODO
		this.name = name;
	}

	/*
	 * Sets the score field
	 * 
	 * @param {int}
	 *            score - Given the score the person got
	 */
	public void setScore(V score) {
		// TODO
		this.score = score;
	}

	/* Returns a string representation of this entry */
	public String toString() {
		// TODO
		
		return "GameEntry[" + this.name + ", " + this.score + "]";
	}
}

