package com.viettel.ocs.runnable;

import java.util.Set;

import com.viettel.ocs.cache.CacheWrapper;
import com.viettel.ocs.cache.EhcacheWrapper;
import com.viettel.ocs.helper.CompareStepOneAndStepTwoHelper;

import net.sf.ehcache.CacheManager;

public class CompareTmpFnSrvRunnable implements Runnable {

	private CacheWrapper<String, String> viettelTmpFnSrv;
	
	public CompareTmpFnSrvRunnable(){
		CacheManager cacheManager = CacheManager.getInstance();
		viettelTmpFnSrv = new EhcacheWrapper<String, String>("viettelTmpFnSrv", cacheManager);
	}
	
	@Override
	public void run() {		
		Set<String> tmpFnSrvs = CompareStepOneAndStepTwoHelper.compareTmpFnSrv();
		for (String s : tmpFnSrvs) {
			viettelTmpFnSrv.put(s, s);
		}
	}

}
