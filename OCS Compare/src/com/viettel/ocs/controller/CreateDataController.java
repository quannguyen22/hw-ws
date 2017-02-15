package com.viettel.ocs.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.viettel.ocs.manager.TMP_LANG_EN_manager;
import com.viettel.ocs.manager.TMP_RECHARGE_24H_manager;

public class CreateDataController {
	
	private List<String> msdns =new ArrayList<>();
	
	public CreateDataController(List<String> msdns){
		this.msdns.addAll(msdns);
	}
	
	public void execute(){
		//TMP_LANG_EN_manager tMP_LANG_EN_manager =new TMP_LANG_EN_manager(msdns);
		TMP_RECHARGE_24H_manager tmp_RECHARGE_24H_manager  =new TMP_RECHARGE_24H_manager(msdns);
		
		//tMP_LANG_EN_manager.start();
		tmp_RECHARGE_24H_manager.start();
		
		try {
			//tMP_LANG_EN_manager.join();
			tmp_RECHARGE_24H_manager.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		List<String> listMsdn =new ArrayList<>();
		
		System.err.println( (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
		for(int i=1 ;i<=1;i++){
			//listMsdn.add("960150729");
			listMsdn.add("974341648");
			//listMsdn.add("1645326125");
		}
		
		CreateDataController createDataController =new CreateDataController(listMsdn);
		createDataController.execute();
		System.err.println( (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
		
	}
	
	

}
