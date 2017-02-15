package com.viettel.ocs.cache;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

public class EhcacheWrapper<K, V> implements CacheWrapper<K, V> {
	private final String cacheName;
	private final CacheManager cacheManager;

	public EhcacheWrapper(final String cacheName, final CacheManager cacheManager) {
		this.cacheName = cacheName;
		this.cacheManager = cacheManager;
	}

	public void put(final K key, final V value) {
		getCache().putWithWriter(new Element(key, value));
	}

	@SuppressWarnings("unchecked")
	public V get(final K key) {
		Element element = getCache().get(key);
		if (element != null) {
			return (V) element.getObjectValue();
		}
		return null;
	}

	public Ehcache getCache() {
		return cacheManager.getEhcache(cacheName);
	}

	@Override
	public void delete(K key) {
		if (getCache().isKeyInCache(key))
			getCache().remove(key);
	}
}