import java.util.*;
import java.io.*;

public class Hash<K,V>{
    public static class Pair<K,V>{
        private K _key;
        private V _value;

        public K getKey(){return this._key;}
        public V getValue(){return  this._value;}

        public void setKey(K key){this._key = key;}
        public void setValue(V value){this._value = value;}

        public Pair(K key, V value) {
            this._key = key;
            this._value = value;
        }
    }

    private ArrayList<LinkedList<Pair<K, V>>> table;
    private int SIZE;

    public Hash() {
        table = new ArrayList<>();

        for (int i=0; i<20; i++) {
            table.add(new LinkedList<>());
        }

        SIZE = 0;
    }

    private LinkedList<Pair<K, V>> getList(K key) {
        int index = Math.abs(key.hashCode() % table.size());
        return table.get(index);
    }

    public V put(K key, V value) {
        LinkedList<Pair<K, V>> list = getList(key);

        V old = null;

        if (containsKey(key)) {
            for (Pair pair: list) {
                if (pair.getKey().equals(key)) {
                    old = (V) pair.getValue();
                    pair.setValue(value);
                }
            }
        } else {
            list.add(new Pair<>(key, value));
            SIZE++;
        }

        return old;
    }

    public V remove(K key) {

        V removedValue = null;

        if (containsKey(key)) {
            LinkedList<Pair<K, V>> list = getList(key);
            V value = get(key);

            int index = list.indexOf(new Pair<>(key, value));

            removedValue = list.get(index).getValue();
            list.remove(index);

            SIZE--;
        }

        return removedValue;
    }

    public V get(K key) {
        LinkedList<Pair<K, V>> list = getList( key);
        for (Pair pair: list) {
            if (pair.getKey().equals(key))
                return (V) pair.getValue();
        }

        return null;
    }

    public boolean containsKey(K key) {
        LinkedList<Pair<K, V>> list = getList(key);
        for (Pair pair: list)
            if (pair.getKey().equals(key))
                return true;

        return false;
    }
}