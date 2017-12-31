package Maps;

import java.util.HashMap;
import java.util.Iterator;

public class Set<K, V> {
	private HashMap<K,V> hmap;
	
	public Set() {
		hmap = new HashMap<K, V>();
	}
	
	public void add(K e) {
		hmap.put(e, null);
	}
	
	public boolean contains(K e) {
		return hmap.containsKey(e);
	}
	
	public void remove(K e) {
		hmap.remove(e);
	}
	
	@SuppressWarnings("unchecked")
	public Iterator<K> iterator() {
		return (Iterator<K>) hmap.keySet();
	}
}
