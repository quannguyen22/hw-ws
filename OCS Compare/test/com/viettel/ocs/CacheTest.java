package com.viettel.ocs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.viettel.ocs.cache.CacheWrapper;
import com.viettel.ocs.cache.EhcacheWrapper;
import com.viettel.ocs.util.Constant;

import net.sf.ehcache.CacheManager;

public class CacheTest {
	private static final Logger logger = LoggerFactory.getLogger(CacheTest.class);
	
	public static void main(String[] args) {
//		Constant.STEP = "2";
		
		CacheManager cacheManager = CacheManager.getInstance();
		CacheWrapper<String, String> tmpBatch = new EhcacheWrapper<String, String>("tmpBatch", cacheManager);
		CacheWrapper<String, String> tmpAcmM = new EhcacheWrapper<String, String>("tmpAcmM", cacheManager);
		CacheWrapper<String, String> tmpSubsUpp = new EhcacheWrapper<String, String>("tmpSubsUpp", cacheManager);
		CacheWrapper<String, String> tmpVtCut = new EhcacheWrapper<String, String>("tmpVtCut", cacheManager);
		CacheWrapper<String, String> tmpFnNbr = new EhcacheWrapper<String, String>("tmpFnNbr", cacheManager);
		CacheWrapper<String, String> tmpOcsCdrHis = new EhcacheWrapper<String, String>("tmpOcsCdrHis", cacheManager);
		CacheWrapper<String, String> tmpFnSrv = new EhcacheWrapper<String, String>("tmpFnSrv", cacheManager);
		CacheWrapper<String, String> tmpBlackListSubs = new EhcacheWrapper<String, String>("tmpBlackListSubs", cacheManager);
		CacheWrapper<String, String> tmpRecharge24h = new EhcacheWrapper<String, String>("tmpRecharge24h", cacheManager);
		CacheWrapper<String, String> tmpLangEn = new EhcacheWrapper<String, String>("tmpLangEn", cacheManager);
		CacheWrapper<String, String> tmpVtCutSea = new EhcacheWrapper<String, String>("tmpVtCutSea", cacheManager);
		
		CacheWrapper<String, String> viettelTmpBatch = new EhcacheWrapper<String, String>("viettelTmpBatch", cacheManager);
		
		long startTime = System.currentTimeMillis();
		
		System.out.println(Constant.STEP);
		
		for (long i = 100000000; i< 100100000; i++){
			viettelTmpBatch.put(String.valueOf(i), "value tmpAcmM | value tmpAcmM | value tmpAcmM | value tmpAcmM | value tmpAcmM | value tmpAcmM | value tmpAcmM | value tmpAcmM " + i);
//			tmpAcmM.put(String.valueOf(i), "value tmpAcmM" + i);
//			tmpSubsUpp.put(String.valueOf(i), "value tmpSubsUpp" + i);
//			tmpVtCut.put(String.valueOf(i), "value tmpVtCut" + i);
//			tmpFnNbr.put(String.valueOf(i), "value tmpFnNbr" + i);
//			tmpOcsCdrHis.put(String.valueOf(i), "value tmpOcsCdrHis" + i);
//			tmpFnSrv.put(String.valueOf(i), "value tmpFnSrv" + i);
//			tmpBlackListSubs.put(String.valueOf(i), "value tmpBlackListSubs" + i);
//			tmpRecharge24h.put(String.valueOf(i), "value tmpRecharge24h" + i);
//			tmpLangEn.put(String.valueOf(i), "value tmpLangEn" + i);
//			tmpVtCutSea.put(String.valueOf(i), "value tmpVtCutSea" + i);
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("Thoi gian chay: " + (endTime - startTime) + " ms");

//		logger.info("test logger");
	}

}
