package utils;

import java.util.Map;

public interface MyIHeap<K, V> {
    V lookup(K key);
    boolean isDefined(K key);
    void put(K key,V value);
    void update(K key, V value);
    void setContent(Map<K, V> newHeap);
    Map<K, V> getContent();
    int nextAvailablePosition();
}
