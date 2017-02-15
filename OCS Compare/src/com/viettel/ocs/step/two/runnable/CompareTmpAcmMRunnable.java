package com.viettel.ocs.step.two.runnable;

import java.util.Set;

import com.viettel.ocs.cache.CacheWrapper;
import com.viettel.ocs.cache.EhcacheWrapper;
import com.viettel.ocs.helper.CompareStepOneAndStepTwoHelper;

import net.sf.ehcache.CacheManager;

public class CompareTmpAcmMRunnable implements Runnable {

	private CacheWrapper<String, String> viettelTmpAcmM;
	
	public CompareTmpAcmMRunnable(){
		CacheManager cacheManager = CacheManager.getInstance();
		viettelTmpAcmM = new EhcacheWrapper<String, String>("viettelTmpAcmM", cacheManager);
	}
	
	@Override
	public void run() {
		System.out.println("Xu ly so sanh file TmpAcmM");
		
		Set<String> tmpAcmMs = CompareStepOneAndStepTwoHelper.compareTmpAcmM();
		for (String s : tmpAcmMs) {
			viettelTmpAcmM.put(s, s);
		}
	}

}
