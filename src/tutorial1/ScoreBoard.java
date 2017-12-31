package tutorial1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScoreBoard {
	private int maxEntries;
	private int numEntries; // number of actual entries
	private GameEntry[] board; // array of game entries (name and scores)

	public ScoreBoard(int capacity) {
		this.maxEntries = capacity;
		this.numEntries = 0;
		this.board = new GameEntry[maxEntries];
	}

	/** Attempts to add a new score to the collection (if it is high enough). */
	public void add(GameEntry e) {
		if (maxEntries <= numEntries) {
			int position = numEntries-1;
			while (position >= 0 && e.getScore() > board[position].getScore()) {
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
			while (position >= 0 && e.getScore() > board[position].getScore()) {
				board[position+1] = board[position];
				position--;
			}
			position++;
			
			board[position] = e;
			this.numEntries += 1;
		}
	}

	/** Attempts to remove an existing score from the collection */
	public GameEntry remove(int i) throws IndexOutOfBoundsException {
		return new GameEntry("", 1);
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
		ScoreBoard scoreBoard = new ScoreBoard(10);

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
				
				GameEntry entry = new GameEntry(name, score);
				
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
