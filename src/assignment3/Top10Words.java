package assignment3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Top10Words {
	private static HashMap<String, Integer> hmap = new HashMap<String, Integer>();
	
	/*
	 * find the top 10 most frequent words
	 */
	public static void top10Words() throws FileNotFoundException {
		readWords(); // call the method
		
		ArrayList<String> top10 = new ArrayList<String>();
		
		for (Entry<String, Integer> entry : hmap.entrySet()) {
		    String key = entry.getKey();
		    Integer val = entry.getValue();
		    
		    
		    if (top10.size() <= 0) { // if there are 0 entries in top 10
		    		top10.add(0, key); // add word to top ten
		    } else if (top10.size() < 10) {  // if there are less than 10 entries in top 10
		    		// traverse through top ten finding its position based on the frequency then add it to the array list if it is not the value with the lowest frequency
		    		boolean added = false;
		    		for (int i = 0; i < top10.size() && added == false; i++) {
		    			if (val > hmap.get(top10.get(i))) {
		    				top10.add(i, key);
		    				added = true;
		    			}
		    		}
		    		if (added = false) // if it is the smallest value out of all of them add to the end
		    			top10.add(key);
		    } else { // if there are more than 10 elements
		    		if (val > hmap.get(top10.get(9))) { // check if frequency is greater than the smallest frequency
		    			top10.remove(9); //remove last element
		    			
		    			// traverse through top ten finding its position based on the frequency then add it to the array list if it is not the value with the lowest frequency
		    			boolean added = false;
			    		for (int i = 0; i < top10.size() && added == false; i++) {
			    			if (val > hmap.get(top10.get(i))) {
			    				top10.add(i, key);
			    				added = true;
			    			}
			    		}
			    		if (added = false) // if it is the smallest value out of all of them add to the end
			    			top10.add(key);
		    		}
		    }
		}
		
		for (String s : top10) {
	    		System.out.println("Word: '" + s + "'   Frequency: " + hmap.get(s));
	    	}
		
	}
	
	/*
	 * read words from file and put them into the hashmap
	 */
	public static void readWords() throws FileNotFoundException {
		FileReader input = new FileReader("src/sample_text.txt");
		Scanner in = new Scanner(input);
		
		while (in.hasNext()) {
			String str = in.next().toLowerCase().replaceAll("\\W", "");
			if (hmap.get(str) == null)
				hmap.put(str, 0);
			hmap.put(str, hmap.get(str)+1);
		}
		in.close();
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		top10Words();
	}
}
