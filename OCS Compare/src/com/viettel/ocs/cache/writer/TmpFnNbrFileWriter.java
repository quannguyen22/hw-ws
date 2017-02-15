package com.viettel.ocs.cache.writer;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;

import com.viettel.ocs.cache.CacheWrapper;
import com.viettel.ocs.cache.EhcacheWrapper;

import net.sf.ehcache.CacheEntry;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.writer.CacheWriter;
import net.sf.ehcache.writer.writebehind.operations.SingleOperationType;

public class TmpFnNbrFileWriter implements CacheWriter {
	private final File file;
	private volatile OutputStream out;
	private volatile boolean write = true;

	private CacheWrapper<String, String> tmpFnNbr;

	public TmpFnNbrFileWriter(final File file) {
		this.file = file;

		// Khoi tao cache de get value tu key
		// Get gia tri tu doi tuong element dang bi sai vi thua chu t o dau dong.
		CacheManager cacheManager = CacheManager.getInstance();
		this.tmpFnNbr = new EhcacheWrapper<String, String>("tmpFnNbr", cacheManager);
	}

	public CacheWriter clone(final Ehcache ehcache) throws CloneNotSupportedException {
		throw new CloneNotSupportedException("CsvWriter cannot be cloned!");
	}

	public void init() {
		try {
			if (file.createNewFile()) {
				out = new BufferedOutputStream(new FileOutputStream(file));
			} else {
				out = new BufferedOutputStream(new FileOutputStream(file, true));
			}
		} catch (IOException e) {
			throw new CacheException("Couldn't create file " + file, e);
		}
	}

	public void dispose() throws CacheException {
		try {
			if (out != null) {
				out.close();
			}
		} catch (IOException e) {
			throw new CacheException("Couldn't close file " + file, e);
		}
	}

	public synchronized void write(final Element element) throws CacheException {

		if (!write) {
			return;
		}

		// Lay gia tri cua doi tuong tu cache
		String key = StringUtils.trimToEmpty((String) element.getObjectKey());
		String value = StringUtils.trimToEmpty(tmpFnNbr.get(key));

		// Kiem tra xem co can ghi du lieu khong.
		if (StringUtils.isNotEmpty(value)) {
			try {
				out.write(value.getBytes());
				out.write("\n".getBytes());

				out.flush();
			} catch (IOException e) {
				throw new CacheException("Couldn't flush to file " + file);
			}
		}

		// Xoa du lieu tren cache
		tmpFnNbr.delete(key);
	}

	public synchronized void writeAll(final Collection<Element> elements) throws CacheException {
		for (Element element : elements) {
			write(element);
		}
	}

	public synchronized void delete(final CacheEntry cacheEntry) throws CacheException {
		// noop
	}

	public synchronized void deleteAll(final Collection<CacheEntry> cacheEntries) throws CacheException {
		// noop
	}

	@Override
	public void throwAway(Element arg0, SingleOperationType arg1, RuntimeException arg2) {
		// TODO Auto-generated method stub

	}
}
