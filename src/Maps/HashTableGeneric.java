package Maps;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashTableGeneric<K, V> implements Maps<K, V> {
	private static final int SIZE = (int) Math.pow(2, Short.SIZE); // short selected instead of integer due to OutOfMemoryException
	private HashEntry<K, V>[] table;
	private int size;
	
	@SuppressWarnings("unchecked")
	HashTableGeneric() {
		size = 0;
		table = new HashEntry[SIZE];
		//table = (HashEntry[]) new Object[SIZE];
		for (int i = 0; i < SIZE; i++) {
			table[i] = null;
		}
	}

	private int hashCode(String key) {
		int hash = 0;
		for (int i = 0; i < key.length(); i++) {
			hash = (41 * hash) + key.charAt(i);
		}
		return (hash % (SIZE/2)) + SIZE/2;
	}
	

	public V get(K key) {
		int hash = hashCode(String.valueOf(key));
		if (table[hash] == null)
		{
			return null;
		}
		return table[hash].getValue();
	}

	public V put(K key, V value) {
		int hash = hashCode(String.valueOf(key));
		if (table[hash] == null) {
			this.size++;
			table[hash] = (HashEntry<K, V>) new HashEntry<K, V>(key, value);
			return null;
		} else {
			V oldValue = (V) table[hash].getValue();
			table[hash] = new HashEntry<K, V>(key, value);
			return oldValue;
		}
	}

	public V remove(K key) {
		int hash = hashCode(String.valueOf(key));
		V value = table[hash].getValue();
		if (table[hash] != null) {
			this.size--;
		}
		table[hash] = null;
		return value;
	}

	public Iterator<K> keySet() {
        return new KeyIterator();
    }
	
    private class KeyIterator implements Iterator<K> {
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

        public K next() {
            if(this.hasNext()) {
                K current = table[cursor].getKey();
                cursor ++;
                return current;
            }
            throw new NoSuchElementException();
        }
    }
	
	public Iterator<HashEntry<K, V>> entrySet() {
        return new EntryIterator();
    }
	
    private class EntryIterator implements Iterator<HashEntry<K, V>> {
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

        public HashEntry<K, V> next() {
            if(this.hasNext()) {
                HashEntry<K, V> current = table[cursor];
                cursor ++;
                return current;
            }
            throw new NoSuchElementException();
        }
    }
	
	public Iterator<V> valueSet() {
        return new ValueIterator();
    }
	
    private class ValueIterator implements Iterator<V> {
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

        public V next() {
            if(this.hasNext()) {
                V current = table[cursor].getValue();
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
}
