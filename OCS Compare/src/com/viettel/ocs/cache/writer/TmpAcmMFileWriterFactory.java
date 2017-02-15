package com.viettel.ocs.cache.writer;

import java.io.File;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import com.viettel.ocs.util.Constant;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.writer.CacheWriter;
import net.sf.ehcache.writer.CacheWriterFactory;

/**
 * @author Alex Snaps
 */
public class TmpAcmMFileWriterFactory extends CacheWriterFactory {

//	private static final String PATH_STEP_1 = StringUtils.trimToEmpty( PropsUtil.getInstance().getValue("msisdn.info.1.path"));
//	private static final String PATH_STEP_2 = StringUtils.trimToEmpty( PropsUtil.getInstance().getValue("msisdn.info.2.path"));

	@Override
	public CacheWriter createCacheWriter(final Ehcache ehcache, final Properties properties) {		
		if (StringUtils.equals(Constant.STEP, "2"))
			return new TmpAcmMFileWriter(new File(Constant.PATH_STEP_2 + "/" + Constant.TMP_ACM_M + ".txt"));
		
		return new TmpAcmMFileWriter(new File(Constant.PATH_STEP_1 + "/" + Constant.TMP_ACM_M + ".txt"));
	}
}