package com.viettel.ocs.runnable;

import java.util.Set;

import com.viettel.ocs.cache.CacheWrapper;
import com.viettel.ocs.cache.EhcacheWrapper;
import com.viettel.ocs.helper.CompareStepOneAndStepTwoHelper;

import net.sf.ehcache.CacheManager;

public class CompareTmpBatchRunnable implements Runnable {

	private CacheWrapper<String, String> viettelTmpBatch;

	public CompareTmpBatchRunnable(){
		CacheManager cacheManager = CacheManager.getInstance();
		viettelTmpBatch = new EhcacheWrapper<String, String>("viettelTmpBatch", cacheManager);
	}
	
	@Override
	public void run() {		
		Set<String> tmpBatchs = CompareStepOneAndStepTwoHelper.compareTmpBatch();
		for (String s : tmpBatchs) {
			viettelTmpBatch.put(s, s);
		}
	}

}
