package com.roydon;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 实现一个cache，包括LRU算法和在x秒后过期
 * 需要实现一个Cache接口，其中包含get、put和remove方法。
 *
 * @param <K>
 * @param <V>
 */
public class LRUCacheWithExpiration<K, V> {
    // 缓存的最大容量
    private final int capacity;
    // 缓存项的过期时间（秒）
    private final long expirationTimeInSeconds;
    // 使用LinkedHashMap实现LRU缓存，accessOrder设置为true以启用LRU顺序
    private final LinkedHashMap<K, CacheEntry<V>> cacheMap;
    // 定时任务执行器，用于清理过期的缓存项
    private final ScheduledExecutorService expirationExecutor;

    // 内部类CacheEntry，用于存储缓存值和过期时间
    private static class CacheEntry<V> {
        V value;
        long expirationTime;

        CacheEntry(V value, long expirationTime) {
            this.value = value;
            this.expirationTime = expirationTime;
        }
    }

    // 构造函数
    public LRUCacheWithExpiration(int capacity, long expirationTimeInSeconds) {
        this.capacity = capacity;
        this.expirationTimeInSeconds = expirationTimeInSeconds;
        // 初始化LRU缓存，设置accessOrder为true，以便按访问顺序进行LRU操作
        this.cacheMap = new LinkedHashMap<K, CacheEntry<V>>(capacity, 0.75f, true) {
            // 重写removeEldestEntry方法，当Map大小超过指定容量时，删除最老的元素
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, CacheEntry<V>> eldest) {
                return size() > LRUCacheWithExpiration.this.capacity;
            }
        };
        // 初始化定时任务执行器
        this.expirationExecutor = Executors.newSingleThreadScheduledExecutor();
        // 安排定时任务，每隔expirationTimeInSeconds秒执行一次清理过期缓存项的操作
        this.expirationExecutor.scheduleAtFixedRate(this::evictExpiredEntries, expirationTimeInSeconds, expirationTimeInSeconds, TimeUnit.SECONDS);
    }

    // 获取缓存项
    public synchronized V get(K key) {
        CacheEntry<V> entry = cacheMap.get(key);
        if (entry == null || isExpired(entry.expirationTime)) {
            // 缓存项不存在或已过期，返回null
            return null;
        }
        // 更新缓存项的访问顺序（移到尾部，表示最近访问）
        cacheMap.remove(key);
        cacheMap.put(key, entry);
        // 返回缓存值
        return entry.value;
    }

    // 添加或更新缓存项
    public synchronized void put(K key, V value) {
        // 创建新的缓存项，设置过期时间
        CacheEntry<V> newEntry = new CacheEntry<>(value, System.currentTimeMillis() + (expirationTimeInSeconds * 1000));
        // 将新的缓存项添加到LRU缓存中，如果缓存已满，则会自动删除最老的元素
        cacheMap.put(key, newEntry);
    }

    // 清理过期缓存项的方法
    private void evictExpiredEntries() {
        // 遍历缓存，移除已过期的缓存项
        cacheMap.entrySet().removeIf(entry -> isExpired(entry.getValue().expirationTime));
    }

    // 判断缓存项是否过期
    private boolean isExpired(long expirationTime) {
        return expirationTime <= System.currentTimeMillis();
    }

    // 关闭缓存，停止定时任务执行器
    public void close() {
        expirationExecutor.shutdown();
        try {
            if (!expirationExecutor.awaitTermination(60, TimeUnit.SECONDS)) {
                expirationExecutor.shutdownNow();
            }
        } catch (InterruptedException e) {
            expirationExecutor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
