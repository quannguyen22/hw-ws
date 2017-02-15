package com.viettel.ocs.step.two.runnable;

import java.util.Set;

import com.viettel.ocs.cache.CacheWrapper;
import com.viettel.ocs.cache.EhcacheWrapper;
import com.viettel.ocs.helper.CompareStepOneAndStepTwoHelper;

import net.sf.ehcache.CacheManager;

public class CompareTmpLangEnRunnable implements Runnable {

	private CacheWrapper<String, String> viettelTmpLangEn;
	
	public CompareTmpLangEnRunnable(){
		CacheManager cacheManager = CacheManager.getInstance();
		viettelTmpLangEn = new EhcacheWrapper<String, String>("viettelTmpLangEn", cacheManager);
	}
	
	@Override
	public void run() {		
		Set<String> tmpLangEns = CompareStepOneAndStepTwoHelper.compareTmpLangEn();		
		for (String s : tmpLangEns) {
			viettelTmpLangEn.put(s, s);
		}
	}

}
