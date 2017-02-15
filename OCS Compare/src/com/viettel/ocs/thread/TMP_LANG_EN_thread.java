package com.viettel.ocs.thread;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import com.viettel.ocs.bean.XmlResultObj;
import com.viettel.ocs.cache.CacheWrapper;
import com.viettel.ocs.cache.EhcacheWrapper;
import com.viettel.ocs.util.Constant;
import com.viettel.ocs.util.XmlUtil;

import net.sf.ehcache.CacheManager;



public class TMP_LANG_EN_thread implements Runnable {
	

	
	private String msisdn;
	private String defaultMsg;
	private XmlUtil util =null;
	private List<HashMap<String, String>> dataMap =new ArrayList<>();
	private String dataResult = "";

	private CacheWrapper<String, String> tmpLangEn;

	public TMP_LANG_EN_thread(String msisdn,String defaultMsg) {
		
		this.msisdn = msisdn;
		this.defaultMsg = defaultMsg;
		
		CacheManager cacheManager = CacheManager.getInstance();
		tmpLangEn = new EhcacheWrapper<String, String>("tmpLangEn", cacheManager);
	}

	@Override
	public void run() {
		
	
		String parentTag  = null;
		List<String> allFindTag = null;
		String findTag = null;
        try {
        	
        	Properties prop = new Properties();
			InputStream input = null;
			try {
				input = this.getClass().getResourceAsStream("/config.properties");
				prop.load(input);
				findTag = prop.getProperty("TMP_LANG_EN_FIND_TAG");
				allFindTag =new ArrayList<>();
				allFindTag.add(findTag);
				parentTag =  prop.getProperty("TMP_LANG_EN_PARENT_TAG");
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (input != null) {
					try {
						input.close();
						prop.clone();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        if(allFindTag==null ||parentTag==null){
        	System.err.println("khong thay cau hinh");
        	return;
        }
        
        try {	
        	this.util  = new XmlUtil(msisdn, defaultMsg, null, parentTag, allFindTag);
        	XmlResultObj obj =  this.util.execute(Constant.CUSTOMER_SERVICE);
        	if(obj.getStatus()!= 0){
        		//ghi vao cache
        	}else{
        		dataMap =  obj.getDataMap();
        		System.err.println("dataMap :"+dataMap);
        		
        		if(this.dataMap==null){
            		dataResult = "NULL";
            	}else{
            		dataMap = obj.getDataMap();
            		for(HashMap<String, String> hm : dataMap){
            	
            			if(hm.containsKey(findTag)){
            				String value = hm.get(findTag);
            				if(value.trim().equals(Constant.TMP_LANG_EN_)){
            					tmpLangEn.put(msisdn, msisdn);
            					//dataResult = dataResult.concat("\r\n").concat(this.msisdn);					
            				}
            			}
            		}
            	}
        	}
        	
        	
        	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        //System.err.println("dataResult : "+dataResult);

	}
	
	//Getter - Setter
	public List<HashMap<String, String>> getDataMap() {
		return dataMap;
	}
	public String getDataResult() {
		if(dataResult!=null) return dataResult.trim();
		return dataResult;
	}

}
