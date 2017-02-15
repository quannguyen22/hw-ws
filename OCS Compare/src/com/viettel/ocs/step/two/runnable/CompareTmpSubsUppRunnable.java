package com.viettel.ocs.runnable;

import java.util.Set;

import com.viettel.ocs.cache.CacheWrapper;
import com.viettel.ocs.cache.EhcacheWrapper;
import com.viettel.ocs.helper.CompareStepOneAndStepTwoHelper;

import net.sf.ehcache.CacheManager;

public class CompareTmpSubsUppRunnable implements Runnable {

	private CacheWrapper<String, String> viettelTmpSubsUpp;
	
	public CompareTmpSubsUppRunnable(){
		CacheManager cacheManager = CacheManager.getInstance();
		viettelTmpSubsUpp = new EhcacheWrapper<String, String>("viettelTmpSubsUpp", cacheManager);
	}
	
	@Override
	public void run() {		
		Set<String> tmpSubsUpp = CompareStepOneAndStepTwoHelper.compareTmpSubsUpp();	
		for (String s : tmpSubsUpp) {
			viettelTmpSubsUpp.put(s, s);
		}
	}

}
