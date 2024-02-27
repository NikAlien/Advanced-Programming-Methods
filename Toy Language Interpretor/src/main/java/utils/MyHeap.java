package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyHeap<K, V> implements MyIHeap<K, V> {
    private Map<K,V> map;
    static int lastAddress;

    public MyHeap(){
        this.map = new HashMap<>();
        lastAddress = 0;
    }
    @Override
    public V lookup(K key) {
        return map.get(key);
    }
    @Override
    public boolean isDefined(K key) {
        return map.get(key)!=null;
    }
    @Override
    public void put(K key, V value) {
        map.put(key, value);
    }
    @Override
    public void update(K key, V value) {
        map.put(key, value);
    }
    @Override
    public void setContent(Map<K, V> newHeap) {
        this.map.clear();
        this.map.putAll(newHeap);
    }
    @Override
    public Map<K, V> getContent() {
        return this.map;
    }
    @Override
    public int nextAvailablePosition() {
        lastAddress += 1;
        return lastAddress;
    }

    @Override
    public String toString() {
        Set<K> keySet = map.keySet();
        ArrayList<K> listOfKeys = new ArrayList<K>(keySet);
        String expectedOutput = "";
        for(K key : listOfKeys)
        {
            expectedOutput += key;
            expectedOutput += " -> ";
            expectedOutput += map.get(key);
            expectedOutput += "\n";
        }
        return expectedOutput;
    }
}
