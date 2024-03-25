package Map;

import java.util.ArrayList;

public class MyMap<K, V> {
    ArrayList<Pair<K, V>> keyPairs;

    MyMap(){
        keyPairs = new ArrayList<>();
    }
    public void put(K key, V value){
        for(Pair<K, V> p: keyPairs){
            if(p.getKey().equals(key)){
                p.setValue(value);
                return;
            }
        }
        keyPairs.add(new Pair<>(key, value));
    }

    public V get(K key){
        for(Pair<K, V> p: keyPairs){
            if(p.getKey().equals(key))
                return p.getValue();
        }
        return null;
    }

    public V remove(K key){
        for(int i = 0; i < keyPairs.size(); i++){
            if(keyPairs.get(i).getKey().equals(key)){
                V value = keyPairs.get(i).getValue();
                keyPairs.remove(i);
                return value;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keyPairs.size(); i++)
            sb.append(STR."[\{i}] --> < \{keyPairs.get(i).getKey()}, \{keyPairs.get(i).getValue()} >\n");

        return sb.toString();
    }
}
