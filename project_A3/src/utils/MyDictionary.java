package utils;

import model.stmt.IStmt;

import java.util.*;

public class MyDictionary<K,V> implements MyIDictionary<K,V>{

    private Map<K,V> map;

    public MyDictionary(){
        map = new HashMap<>();
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
    public void remove(K key) {
        map.remove(key);
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
