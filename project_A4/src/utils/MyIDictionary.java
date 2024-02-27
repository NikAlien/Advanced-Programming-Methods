package utils;

import java.util.Map;

public interface MyIDictionary<K,V> {
    V lookup(K key);
    boolean isDefined(K key);
    void put(K key,V value);
    void update(K key, V value);
    Map<K, V> getContent();
    void remove(K key);
}
