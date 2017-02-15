package com.viettel.ocs.step.two.runnable;

import java.util.Set;

import com.viettel.ocs.cache.CacheWrapper;
import com.viettel.ocs.cache.EhcacheWrapper;
import com.viettel.ocs.helper.CompareStepOneAndStepTwoHelper;

import net.sf.ehcache.CacheManager;

public class CompareTmpVtCutRunnable implements Runnable {

	private CacheWrapper<String, String> viettelTmpVtCut;
	
	public CompareTmpVtCutRunnable(){
		CacheManager cacheManager = CacheManager.getInstance();
		viettelTmpVtCut = new EhcacheWrapper<String, String>("viettelTmpVtCut", cacheManager);
	}
	
	@Override
	public void run() {
		Set<String> tmpVtCut = CompareStepOneAndStepTwoHelper.compareTmpVtCut();
		for (String s : tmpVtCut) {
			viettelTmpVtCut.put(s, s);
		}
	}

}
