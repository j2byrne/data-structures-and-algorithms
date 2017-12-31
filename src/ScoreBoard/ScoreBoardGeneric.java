package ScoreBoard;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScoreBoardGeneric<K, V extends Comparable<? super V>> {
	private int maxEntries;
	private int numEntries; // number of actual entries
	private GameEntryGeneric<K, V>[] board; // array of game entries (name and scores)

	@SuppressWarnings("unchecked")
	public ScoreBoardGeneric(int capacity) {
		this.maxEntries = capacity;
		this.numEntries = 0;
		this.board = (GameEntryGeneric<K, V>[]) new GameEntryGeneric[maxEntries];
		//this.board = new GameEntryGeneric<K, V>[maxEntries];
	}

	/** Attempts to add a new score to the collection (if it is high enough). */
	public void add(GameEntryGeneric<K, V> e) {
		if (maxEntries <= numEntries) {
			int position = numEntries-1;
			while (position >= 0 && e.getScore().compareTo(board[position].getScore()) > 0) {
				if (position != maxEntries-1) {
					board[position+1] = board[position];
				}
				position--;
			}
			if (position != maxEntries -1) {
				position++;
			}
			
			board[position] = e;
		} else if (numEntries == 0) {
			board[0] = e;
			this.numEntries += 1;
		} else {
			int position = numEntries-1;
			while (position >= 0 && e.getScore().compareTo(board[position].getScore()) > 0) {
				board[position+1] = board[position];
				position--;
			}
			position++;
			
			board[position] = e;
			this.numEntries += 1;
		}
	}

	/** Attempts to remove an existing score from the collection */
	public GameEntryGeneric<K, V> remove(int i) throws IndexOutOfBoundsException {
		if (i >= numEntries) {
			throw new RuntimeException("index does not exist");
		}
		
		numEntries--; // decrement the number of entries
		GameEntryGeneric<K, V> removedEntry = board[i]; // make a copy of the entry being removed
		int curr = i;
		// loop through array moving elements into place to remove element
		while (curr < numEntries) {
			board[curr] = board[curr+1];
		}
		
		return removedEntry; // return the removed entry
	}
	
	public String toString() {
		String str = "Number\tName\tScore";
		
		for (int i = 0; i < this.numEntries; i++) {
			str += "\n" + (i+1) + "\t" + board[i].getName() + "\t" + board[i].getScore();
		}
		return str;
	}

	public static void main(String[] args) throws FileNotFoundException {
		// create a new scoreboard
		ScoreBoardGeneric<String, Integer> scoreBoard = new ScoreBoardGeneric<String, Integer>(10);

		Scanner scanner = null;
		
		try {
			File file = new File("src/scores.txt");
			scanner = new Scanner(file);
			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				Scanner lineReader = new Scanner(line);
				lineReader.useDelimiter(",\\s?+"); // comma followed by any number of spaces
				
				String name = "";
				int score = 0;
				
				if (lineReader.hasNext()) {
					name = lineReader.next();
				}
				if (lineReader.hasNextInt()) {
					score = lineReader.nextInt();
				}
				
				GameEntryGeneric<String, Integer> entry = new GameEntryGeneric<String, Integer>(name, score);
				
				scoreBoard.add(entry);
				
				lineReader.close();
			}
		}
		finally {
			scanner.close();
		}
		
		System.out.println(scoreBoard.toString());
	}

}
