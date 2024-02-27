package utils;

public interface MyIDictionary<K,V> {
    V lookup(K key);
    boolean isDefined(K key);
    void put(K key,V value);
    void update(K key, V value);
    void remove(K key);
}
