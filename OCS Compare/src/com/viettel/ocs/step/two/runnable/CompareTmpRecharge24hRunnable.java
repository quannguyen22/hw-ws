package com.viettel.ocs.runnable;

import java.util.Set;

import com.viettel.ocs.cache.CacheWrapper;
import com.viettel.ocs.cache.EhcacheWrapper;
import com.viettel.ocs.helper.CompareStepOneAndStepTwoHelper;

import net.sf.ehcache.CacheManager;

public class CompareTmpRecharge24hRunnable implements Runnable {

	private CacheWrapper<String, String> viettelTmpRecharge24h;
	
	public CompareTmpRecharge24hRunnable(){
		CacheManager cacheManager = CacheManager.getInstance();
		viettelTmpRecharge24h = new EhcacheWrapper<String, String>("viettelTmpRecharge24h", cacheManager);
	}
	
	@Override
	public void run() {		
		Set<String> tmpRecharge24h = CompareStepOneAndStepTwoHelper.compareTmpRecharge24h();
		for (String s : tmpRecharge24h) {
			viettelTmpRecharge24h.put(s, s);
		}
	}

}
