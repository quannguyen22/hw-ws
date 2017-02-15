package com.viettel.ocs.runnable;

import java.util.Set;

import com.viettel.ocs.cache.CacheWrapper;
import com.viettel.ocs.cache.EhcacheWrapper;
import com.viettel.ocs.helper.CompareStepOneAndStepTwoHelper;

import net.sf.ehcache.CacheManager;

public class CompareTmpOcsCdrHisRunnable implements Runnable {

	private CacheWrapper<String, String> viettelTmpOcsCdrHis;
	
	public CompareTmpOcsCdrHisRunnable(){
		CacheManager cacheManager = CacheManager.getInstance();
		viettelTmpOcsCdrHis = new EhcacheWrapper<String, String>("viettelTmpOcsCdrHis", cacheManager);
	}
	
	@Override
	public void run() {
		Set<String> tmpOcsCdrHis = CompareStepOneAndStepTwoHelper.compareTmpOcsCdrHis();
		for (String s : tmpOcsCdrHis) {
			viettelTmpOcsCdrHis.put(s, s);
		}
	}

}
