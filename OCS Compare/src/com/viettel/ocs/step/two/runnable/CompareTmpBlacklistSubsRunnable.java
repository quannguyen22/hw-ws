package com.viettel.ocs.step.two.runnable;

import java.util.Set;

import com.viettel.ocs.cache.CacheWrapper;
import com.viettel.ocs.cache.EhcacheWrapper;
import com.viettel.ocs.helper.CompareStepOneAndStepTwoHelper;

import net.sf.ehcache.CacheManager;

public class CompareTmpBlacklistSubsRunnable implements Runnable {

	private CacheWrapper<String, String> viettelTmpBlackListSubs;
	
	public CompareTmpBlacklistSubsRunnable(){
		CacheManager cacheManager = CacheManager.getInstance();
		viettelTmpBlackListSubs = new EhcacheWrapper<String, String>("viettelTmpBlackListSubs", cacheManager);
	}
	
	@Override
	public void run() {		
		Set<String> tmpBlacklistSubs = CompareStepOneAndStepTwoHelper.compareTmpBlacklistSubs();
		for (String s : tmpBlacklistSubs) {
			viettelTmpBlackListSubs.put(s, s);
		}
	}

}
