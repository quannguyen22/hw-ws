package com.viettel.ocs.step.two.runnable;

import java.util.Set;

import com.viettel.ocs.cache.CacheWrapper;
import com.viettel.ocs.cache.EhcacheWrapper;
import com.viettel.ocs.helper.CompareStepOneAndStepTwoHelper;

import net.sf.ehcache.CacheManager;

public class CompareTmpFnNbrRunnable implements Runnable {

	private CacheWrapper<String, String> viettelTmpFnNbr;
	
	public CompareTmpFnNbrRunnable(){
		CacheManager cacheManager = CacheManager.getInstance();
		viettelTmpFnNbr = new EhcacheWrapper<String, String>("viettelTmpFnNbr", cacheManager);
	}
	
	@Override
	public void run() {
		Set<String> tmpFnNbrs = CompareStepOneAndStepTwoHelper.compareTmpFnNbr();
		for (String s : tmpFnNbrs) {
			viettelTmpFnNbr.put(s, s);
		}
	}

}
