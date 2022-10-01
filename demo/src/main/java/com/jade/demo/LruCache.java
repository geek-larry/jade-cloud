package com.jade.demo;

import java.util.LinkedHashMap;
import java.util.Map;

public class LruCache<K, V> extends LinkedHashMap<K, V> {
    /**
     * 缓存最大容量
     */
    private final int maxSize;

    public LruCache(int initialCapacity, int maxSize) {
        // accessOrder必须为true
        super(initialCapacity, 0.75f, true);
        this.maxSize = maxSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        // 当键值对个数超过最大容量时，返回true，触发删除操作
        return size() > maxSize;
    }

    public static void main(String[] args) {
        LruCache<String, String> cache = new LruCache<>(8, 8);
        cache.put("1", "a");
        cache.put("2", "b");
        cache.put("3", "c");
        cache.put("4", "d");
        cache.put("5", "e");
        cache.put("6", "f");
        cache.put("7", "g");
        cache.put("8", "h");
        cache.put("9", "i");
        System.out.println(cache);
    }

}
