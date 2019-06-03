package com.test.map;

import java.io.Serializable;
import java.util.Map;

public class SimpleEntry<K, V> implements Map.Entry, Serializable {
    private final K key;
    private V value;

    public SimpleEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public SimpleEntry(Map.Entry<? extends K, ? extends V> entry) {
        this.key = entry.getKey();
        this.value = entry.getValue();
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }


    @SuppressWarnings("unchecked")
    public Object setValue(Object value) {
        V oldValue = this.value;
        this.value = (V) value;
        return oldValue;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if(obj.getClass() == SimpleEntry.class) {
            SimpleEntry se = (SimpleEntry) obj;
            return se.getKey().equals(getKey());
        }
        return false;
    }

    public int hashCode() {
        return key == null ? 0:key.hashCode();
    }

    public String toString() {
        return key + "=" + value;
    }
}
