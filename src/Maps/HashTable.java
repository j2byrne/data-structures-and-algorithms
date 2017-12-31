package Maps;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class HashTable implements Maps<String, Integer> {
	private static final int SIZE = Integer.SIZE;
	private HashEntry<String, Integer>[] table;
	private int size;
	
	@SuppressWarnings("unchecked")
	HashTable() {
		size = 0;
		table = (HashEntry<String, Integer>[]) new Object[SIZE];
		for (int i = 0; i < SIZE; i++) {
			table[i] = null;
		}
	}

	private int hashCode(String key) {
		int hash = 0;
		for (int i = 0; i < key.length(); i++) {
			hash = (41 * hash) + key.charAt(i);
		}
		return hash;
	}
	
	public Integer get(String key) {
		int hash = hashCode(key);
		return table[hash].getValue();
	}

	public Integer put(String key, Integer value) {
		int hash = hashCode(key);
		if (table[hash] == null) {
			this.size++;
			table[hash] = new HashEntry<String, Integer>(key, value);
			return (Integer) 0;
		} else {
			int oldValue = table[hash].getValue();
			table[hash] = new HashEntry<String, Integer>(key, value);
			return oldValue;
		}
	}

	public Integer remove(String key) {
		int hash = hashCode(key);
		int value = table[hash].getValue();
		if (table[hash] != null) {
			this.size--;
		}
		table[hash] = null;
		return value;
	}

	public Iterator<String> keySet() {
        return new KeyIterator();
    }
	
    private class KeyIterator implements Iterator<String> {
        private int cursor;

        public KeyIterator() {
            this.cursor = 0;
        }

        public boolean hasNext() {
            //return this.cursor < SIZE;
        		for (; cursor < SIZE; cursor++) {
        			if (table[cursor] != null) {
        				return true;
        			}
        		}
        		
        		return false;
        }

        public String next() {
            if(this.hasNext()) {
                String current = table[cursor].getKey();
                cursor ++;
                return current;
            }
            throw new NoSuchElementException();
        }
    }
	
	public Iterator<HashEntry<String, Integer>> entrySet() {
        return new EntryIterator();
    }
	
    private class EntryIterator implements Iterator<HashEntry<String, Integer>> {
        private int cursor;

        public EntryIterator() {
            this.cursor = 0;
        }

        public boolean hasNext() {
            //return this.cursor < SIZE;
        		for (; cursor < SIZE; cursor++) {
        			if (table[cursor] != null) {
        				return true;
        			}
        		}
        		
        		return false;
        }

        public HashEntry<String, Integer> next() {
            if(this.hasNext()) {
                HashEntry<String, Integer> current = table[cursor];
                cursor ++;
                return current;
            }
            throw new NoSuchElementException();
        }
    }
	
	public Iterator<Integer> valueSet() {
        return new ValueIterator();
    }
	
    private class ValueIterator implements Iterator<Integer> {
        private int cursor;

        public ValueIterator() {
            this.cursor = 0;
        }

        public boolean hasNext() {
            //return this.cursor < SIZE;
        		for (; cursor < SIZE; cursor++) {
        			if (table[cursor] != null) {
        				return true;
        			}
        		}
        		
        		return false;
        }

        public Integer next() {
            if(this.hasNext()) {
                int current = table[cursor].getValue();
                cursor ++;
                return current;
            }
            throw new NoSuchElementException();
        }
    }

	public int size() {
		return this.size;
	}

	public boolean empty() {
		return this.size == 0;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		FileReader input = new FileReader("src/words.txt");
		Scanner in = new Scanner(input);
		int collisions = 0;
		
		HashTableGeneric<Integer, Integer> hmap = new HashTableGeneric<Integer, Integer>();
		
		while (in.hasNext()) {
			String str = in.next();
			
			int hash = 0;
			for (int i = 0; i < str.length(); i++) {
				hash = 41 * hash + str.charAt(i);
			}
			
			if (hmap.get(hash) != null) {
				hmap.put(hash, hmap.get(hash) + 1);
				collisions += 1;
			} else {
				hmap.put(hash, 0);
			}
		}
		in.close();
		
		System.out.println("number of collisions for a = " + 41 + ": " + collisions);
	}
}
