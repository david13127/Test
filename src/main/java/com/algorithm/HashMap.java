package com.algorithm;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2021年02月12日 17:18
 * @version V1.0
 */
public class HashMap<K, V> {
	// 定义一个默认的空间，存多少个数据
	private static final int DEFAULT_SIZE = 1 << 4; // 2左移4位，8
	private Entry<K, V>[] data; // hash表
	private int capacity; // 扩容
	private int size; // 大小

	public HashMap() {
		this(DEFAULT_SIZE);
	}

	public HashMap(int capacity) {
		if (capacity < 0) {
			capacity = DEFAULT_SIZE;
		}
		this.capacity = capacity;
		this.data = new Entry[capacity];
	}

	private void put(K key, V value) {
		if (key == null) {
			return;
		}
		int hash = hash(key);
		// 扩容
		//		if (size >= 0.75 * capacity) {
		//
		//		}
		Entry<K, V> entry = new Entry<>(key, value, null);
		Entry<K, V> hashM = data[hash];
		while (hashM != null) {
			if (hashM.key.equals(key)) { // 一样的key，覆盖
				hashM.value = value;
				return;
			} else {
				hashM = hashM.next;
			}
		}
		entry.next = data[hash];
		data[hash] = entry;
		size++;
	}

	public V get(K key) {
		int hash = hash(key);
		Entry<K, V> entry = data[hash];
		while (entry != null) {
			if (entry.key.equals(key)) {
				return entry.value;
			} else {
				entry = entry.next;
			}
		}
		return null;
	}

	private int hash(K key) {
		int h = 0;
		if (key != null) {
			h = key.hashCode() ^ (h >>> 16);
		}
		return h % capacity;
	}

	public static void main(String[] args) {
		HashMap<String, String> map = new HashMap<>();
		map.put("1", "a");
		map.put("2", "b");
		map.put("3", "c");
		System.out.println("1: " + map.get("1"));
		System.out.println("5: " + map.get("5"));
	}
}

class Entry<K, V> {
	K key;
	V value;
	Entry<K, V> next;

	public Entry(K key, V value, Entry<K, V> next) {
		this.key = key;
		this.value = value;
		this.next = next;
	}
}