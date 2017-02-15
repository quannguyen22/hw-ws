package com.viettel.ocs.cache.writer;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.writer.CacheWriter;
import net.sf.ehcache.writer.CacheWriterFactory;

import java.io.File;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import com.viettel.ocs.util.Constant;
import com.viettel.ocs.util.PropsUtil;

/**
 * @author Alex Snaps
 */
public class ViettelTmpRecharge24hFileWriterFactory extends CacheWriterFactory {

	private static final String PATH = StringUtils.trimToEmpty( PropsUtil.getInstance().getValue("msisdn.info.1.intersection.2.path"));

	@Override
	public CacheWriter createCacheWriter(final Ehcache ehcache, final Properties properties) {
		return new ViettelTmpRecharge24hFileWriter(new File(PATH + "/" + Constant.VIETTEL_TMP_RECHARGE_24H + ".txt"));
	}
}