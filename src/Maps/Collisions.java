package Maps;
/*
class HashEntry {
	private int key;
	private int value;
	
	HashEntry(int key, int value) {
		this.key = key;
		this.value = value;
	}
	
	public int getKey() {
		return key;
	}
	
	public int getValue() {
		return value;
	}
}



import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Collisions {
	private static final int TABLE_SIZE = 257;
	private int collisionNum = 0;
	private int a;
	private boolean[] entries;
	
	Collisions(int a) {
		this.a = a;
		entries = new boolean[TABLE_SIZE];
		for (int i = 0; i < TABLE_SIZE; i++) {
			entries[i] = false;
		}
	}
	
	Collisions(int size, int a) {
		this.a = a;
		entries = new boolean[size];
		for (int i = 0; i < size; i++) {
			entries[i] = false;
		}
	}
	
	public int getCollisionNum() {
		return collisionNum;
	}
	
	public byte hashCode(String word) {
		byte code = 0;
		System.out.println(code);
		System.out.println("wordLength: " + word.length());
		for (int i = 0; i < word.length(); i++) {
			code += word.charAt(i) + (int) Math.pow(a, i);
			System.out.println("Test: " + code);
		}
		System.out.println("T: " + code);
		return code;
	}
	
	public void readFile(FileReader text) {
		Scanner in = new Scanner(text);
		
		while (in.hasNext()) {
			String str = in.next().toLowerCase().replaceAll("\\W", "");
			byte code = hashCode(str);
			System.out.println(code);
			if (entries[code] == false) {
				entries[code] = true;
			} else {
				this.collisionNum++;
			}
		}
		in.close();
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Collisions collisions = new Collisions(41);
		
		FileReader input = new FileReader("src/sample_text.txt");
		
		collisions.readFile(input);
		
		System.out.println("number of collisions: " + collisions.getCollisionNum());
	}
}

*/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

public class Collisions {
	public static void polynomiala(int a) throws FileNotFoundException {
		FileReader input = new FileReader("src/words.txt");
		Scanner in = new Scanner(input);
		int collisions = 0;
		
		HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();
		
		while (in.hasNext()) {
			String str = in.next();
			
			int hash = 0;
			for (int i = 0; i < str.length(); i++) {
				hash = a * hash + str.charAt(i);
			}
			
			if (hmap.get(hash) != null) {
				hmap.put(hash, hmap.get(hash) + 1);
				collisions += 1;
			} else {
				hmap.put(hash, 0);
			}
		}
		in.close();
		
		System.out.println("number of collisions for a = " + a + ": " + collisions);
	}

	public static void polynomialCyclic(int s) throws FileNotFoundException {
		FileReader input = new FileReader("src/words.txt");
		Scanner in = new Scanner(input);
		int collisions = 0;

		HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();
		
		while (in.hasNext()) {
			String str = in.next();
			
			int hash = 0;
			for (int i = 0; i < str.length(); i++) {
				hash = (hash << s) | (hash >>> 25);
				hash += (int) str.charAt(i); 
			}
			
			if (hmap.get(hash) != null) {
				hmap.put(hash, hmap.get(hash) + 1);
				collisions += 1;
			} else {
				hmap.put(hash, 0);
			}
		}
		in.close();
		
		System.out.println("number of collisions for cyclic shift with a shift value of 7: " + collisions);
	}
	
	public static void polynomialOld() throws FileNotFoundException {
		FileReader input = new FileReader("src/words.txt");
		Scanner in = new Scanner(input);
		int collisions = 0;

		HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();
		
		while (in.hasNext()) {
			String str = in.next();
			
			int hash = 0;
			int skip = Math.max(1, str.length() / 8);
			for (int i = 0; i < str.length(); i += skip) {
				hash = (hash * 37) + str.charAt(i);
			}
			
			if (hmap.get(hash) != null) {
				hmap.put(hash, hmap.get(hash) + 1);
				collisions += 1;
			} else {
				hmap.put(hash, 0);
			}
		}
		in.close();
		
		System.out.println("number of collisions: " + collisions);
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		polynomiala(41); // 4
		polynomiala(17); // 387
		polynomialCyclic(7); // 290
		polynomialOld(); // 147
	}
}
