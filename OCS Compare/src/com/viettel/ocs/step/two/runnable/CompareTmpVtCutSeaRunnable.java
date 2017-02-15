package com.viettel.ocs.step.two.runnable;

import java.util.Set;

import com.viettel.ocs.cache.CacheWrapper;
import com.viettel.ocs.cache.EhcacheWrapper;
import com.viettel.ocs.helper.CompareStepOneAndStepTwoHelper;

import net.sf.ehcache.CacheManager;

public class CompareTmpVtCutSeaRunnable implements Runnable {

	private CacheWrapper<String, String> viettelTmpVtCutSea;
	
	public CompareTmpVtCutSeaRunnable(){
		CacheManager cacheManager = CacheManager.getInstance();
		viettelTmpVtCutSea = new EhcacheWrapper<String, String>("viettelTmpVtCutSea", cacheManager);
	}
	
	@Override
	public void run() {
		Set<String> tmpVtCutSea = CompareStepOneAndStepTwoHelper.compareTmpVtCutSea();
		for (String s : tmpVtCutSea) {
			viettelTmpVtCutSea.put(s, s);
		}
	}

}
