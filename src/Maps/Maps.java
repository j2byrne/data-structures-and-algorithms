package Maps;

import java.util.Iterator;

public interface Maps<K, V> {
	/*
	 * returns the associated value of the key
	 */
	V get(K key);
	
	/*
	 * inserts the key and the value into into the Map. if the key is already in the map it returns the old value associated with it if not it returns null
	 */
	V put (K key, V value);
	
	/*
	 * if the map has a value with the key given as a parameter, it is removed and the value is returned, if it is not found null is returned
	 */
	V remove (K key);
	
	/*
	 * return an iterative collection of the keys in the map
	 */
	Iterator<K> keySet();
	
	/*
	 * return an iterative collection of the entries in the map
	 */
	Iterator<HashEntry<K, V>> entrySet();
	
	/*
	 * return an iterative collection of the values in the map
	 */
	Iterator<V> valueSet();
	
	/*
	 * returns the number of elements in the Map
	 */
	int size();
	
	/*
	 * returns  the result of a boolean expression of whether there are any elements in the Map
	 */
	boolean empty();
}
