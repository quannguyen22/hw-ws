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



public class TMP_RECHARGE_24H_thread implements Runnable {
	

	
	private String msisdn;
	private String defaultMsg;
	private XmlUtil util =null;
	private List<HashMap<String, String>> dataMap =new ArrayList<>();
	private String dataResult = "";

	private CacheWrapper<String, String> tmpRecharge24h;

	public TMP_RECHARGE_24H_thread(String msisdn,String defaultMsg) {
		
		this.msisdn = msisdn;
		this.defaultMsg = defaultMsg;
		
		CacheManager cacheManager = CacheManager.getInstance();
		tmpRecharge24h = new EhcacheWrapper<String, String>("tmpRecharge24h", cacheManager);
	}

	@Override
	public void run() {
		
	
		String parentTag  = null;
		List<String> allFindTag = null;
		String findTagTXT = null;
        try {
        	
        	Properties prop = new Properties();
			InputStream input = null;
			try {
				input = this.getClass().getResourceAsStream("/config.properties");
				prop.load(input);
				findTagTXT = prop.getProperty("TMP_RECHARGE_24H_FIND_TAG");
				allFindTag = Arrays.asList(findTagTXT.split(";"));
				parentTag =  prop.getProperty("TMP_RECHARGE_24H_PARENT_TAG");
				
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
        	this.util  = new XmlUtil(msisdn, defaultMsg, "QueryRechargeLog", parentTag, allFindTag);
        	XmlResultObj obj =  this.util.execute(Constant.RECHARGE_SERVICE);
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
            			
            			StringBuilder line =new StringBuilder();
            			line.append(this.msisdn);
            			for(String tagName : allFindTag ){
            				if(hm.containsKey(tagName)){
            					line.append("|").append(hm.get(tagName));
            				}else{
            					line.append("|");
            				}
            			}
            			line.append("|");
            			tmpRecharge24h.put(line.toString(), line.toString());
            		
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
