package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
    public Map<K, V> getContent() {
        return this.map;
    }

    @Override
    public void remove(K key) {
        map.remove(key);
    }

    @Override
    public MyIDictionary<K, V> deepCopy() {
        MyIDictionary<K, V> copy =  new MyDictionary<K, V>();
        for ( K key : map.keySet() ) {
            copy.put(key, map.get(key));
        }
        return copy;
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
