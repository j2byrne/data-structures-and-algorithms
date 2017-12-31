package Maps;

public class HashEntry<K, V> extends Object {
	private K key;
	private V value;
	
	HashEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }
	
	public K getKey() {
		return key;
	}
	
	public V getValue() {
		return value;
	}
}
