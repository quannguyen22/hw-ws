/*
 * Created on Feb 13, 2017
 *
 * Copyright (C) 2017 by Viettel Network Company. All rights reserved
 */
package com.viettel.ocs;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import com.viettel.ocs.step.StepOne;
import com.viettel.ocs.util.Constant;
import com.viettel.ocs.util.FileUtil;
import com.viettel.ocs.util.PropsUtil;

import me.tongfei.progressbar.ProgressBar;
import me.tongfei.progressbar.ProgressBarStyle;

/**
 * Class main cua chuong trinh.
 * 
 * Tham so:
 * 1: Chay buoc 1 cua chuong trinh
 * 		Tham so yeu cau: 
 * 			Danh sach msisdn
 * 			Duong dan luu file ket qua, config trong file config.properties
 * 2: Chay buoc 2 cua chuong trinh
 * 		Tham so yeu cau:
 * 			Duong dan luu file ket qua cua buoc 1
 * 			Duong dan luu file ket qua sau khi phan tich
 * 3: Chay buoc 3 cua chuong trinh
 * 		Tham so yeu cau:
 * 			Duong dan luu file ket qua cua buoc 2
 * 			Danh sach 11 file ket qua can so sanh
 * 
 * @author Nguyen Hai Ha (hanh45@viettel.com.vn)
 * @since Feb 13, 2017
 * @version 1.0.0
 */
public class Launcher2 {

	private static int step = 0;
	
	/**
	 * Ham xu ly chinh
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("##############################################################");
		System.out.println("#                                                            #");
		System.out.println("#      Nhap so 1 de chay buoc 1 cua chuong trinh.            #");
		System.out.println("#         Tham so buoc 1:                                    #");
		System.out.println("#         - Danh sach msisdn                                 #");
		System.out.println("#         - Danh sach 9 webservice dung truy van du lieu     #");
		System.out.println("#      --------------------------------------------------    #");
		System.out.println("#      Nhap so 2 de chay buoc 2 cua chuong trinh.            #");
		System.out.println("#         Tham so dau vao cua buoc 2:                        #");
		System.out.println("#         - Ket qua cua buoc 1                               #");
		System.out.println("#         - Duong dan thu muc luu ket qua so sanh            #");
		System.out.println("#      --------------------------------------------------    #");
		System.out.println("#      Nhap so 3 de chay buoc 3 cua chuong trinh.            #");
		System.out.println("#         Tham so dau vao cua buoc 3:                        #");
		System.out.println("#         - Ket qua cua buoc 2                               #");
		System.out.println("#         - Danh sach 11 file ket qua can so sanh            #");
		System.out.println("#                                                            #");
		System.out.println("#     Ctrl+C de thoat chuong trinh!                          #");
		System.out.println("##############################################################");
		System.out.println("");

		Scanner scanner = new Scanner(System.in);
		System.out.print("Ban muon chuong trinh thuc hien buoc so may [1,2,3]: ");
		step = scanner.nextInt();
		
		if (step == 1) {			
			// Danh dau cho chuong trinh biet luu data toi thu muc buoc 2
			Constant.STEP = "1";
			
			StepOne stepOne = new StepOne();
			stepOne.start();
			
//			stepOne();
		} else if (step == 2) {
			// Danh dau cho chuong trinh biet luu data toi thu muc buoc 2
			Constant.STEP = "2";
			
			stepTwo();
		} else if (step == 3) {
			stepThree();
		} else {
			System.out.println("Chuong trinh khong ho tro step: " + step);
		}
	}

	private static void stepOne() {		
//		System.out.println("------------------------------------------------------------------------------------ ");
//		System.out.println("");
//		System.out.println("Danh sach cac webservice se su dung: ");
//		System.out.println("  - QueryAccumulationUsage:    " + StringUtils.trimToEmpty( PropsUtil.getInstance().getValue("query.accumulation.usage") ));
//		System.out.println("  - QueryBalance:              " + StringUtils.trimToEmpty( PropsUtil.getInstance().getValue("query.balance")));
//		System.out.println("  - QueryCustomerInfo:         " + StringUtils.trimToEmpty( PropsUtil.getInstance().getValue("query.customer.info")));
//		System.out.println("  - QueryFreeUnit:             " + StringUtils.trimToEmpty( PropsUtil.getInstance().getValue("query.free.unit")));
//		System.out.println("  - QueryLoanLog:              " + StringUtils.trimToEmpty( PropsUtil.getInstance().getValue("query.loan.log")));
//		System.out.println("  - QueryOfferingInstProperty: " + StringUtils.trimToEmpty( PropsUtil.getInstance().getValue("query.offering.inst.property")));
//		System.out.println("  - QueryRechargeLog:          " + StringUtils.trimToEmpty( PropsUtil.getInstance().getValue("query.recharge.log")));
//		System.out.println("  - QuerySubLifeCycle:         " + StringUtils.trimToEmpty( PropsUtil.getInstance().getValue("query.sub.lifecycle")));
//		System.out.println("  - QuerySubStatusHis:         " + StringUtils.trimToEmpty( PropsUtil.getInstance().getValue("query.sub.status.his")));
//		System.out.println("");		
//		System.out.println("Thu muc chua file msisdn dau vao: " + StringUtils.trimToEmpty( PropsUtil.getInstance().getValue("msisdn.path")));
//		System.out.println("");
//		System.out.println("------------------------------------------------------------------------------------ ");
//		System.out.println("");
//		
//		Scanner scanner = new Scanner(System.in);
//		System.out.print("Ban chac chan muon chay chuong trinh voi cac tham so tren [Y/n]: ");
//		String confirm = scanner.nextLine();
//		confirm = StringUtils.trimToEmpty(confirm);
//		
//		if (StringUtils.isEmpty(confirm) || StringUtils.equals(confirm.toLowerCase(), "y")){
//			System.out.println("");
//			System.out.println("Bat dau thuc thi buoc 1!");
//			System.out.println("");
//			
////			ProgressBar pb = new ProgressBar("OCSTool", 10, ProgressBarStyle.ASCII);
////			pb.start(); // the progress bar starts timing
////			pb.setExtraMessage("Excuting ...");
//			
//			// Xu ly doc danh sach so dien thoai.
//			String msisdnPath = StringUtils.trimToEmpty( PropsUtil.getInstance().getValue("msisdn.path"));
//			final File folder = new File(msisdnPath);
//			
//			if (folder.exists() && folder.isDirectory()) {
//				List<String> msisdnFiles = FileUtil.listFilesForFolder(folder);
//				
//				// Neu co danh sach file msisdn thi thuc hien.
//				if (!msisdnFiles.isEmpty()){
//					// Xu ly doc file de lay danh sach msisdn
//					// Loc trung msisdn trong buoc xu ly nay
//					Map<String, String> msisdnMap = new HashMap<String, String>();
//					
//					String filePath = "";
//					for (int i = 0; i < msisdnFiles.size(); i++){
////						pb.setExtraMessage("Dang xu ly du lieu msisdn dau vao...");
//
//						filePath = msisdnFiles.get(i);
//						msisdnMap = FileUtil.getAllMsisdn(filePath, msisdnMap);
//					}
//					
//					// Danh sach msisdn da duoc loc trung.
//					// Dau vao cho class truy van thong tin tu webservice.
//					List<String> msisdns = new ArrayList<String>(msisdnMap.values());
//					Collections.sort(msisdns);
//					
////					pb.maxHint(msisdns.size());
//					// Truyen ca tham so progress bar toi thread xu ly truy van webservice
//					
////					for (int k = 0; k < msisdns.size(); k++){
//////						System.out.println(msisdns.get(k));
//////						safeSleep();
////						pb.step();
////						pb.setExtraMessage("Excuting ...");
////					}
//						
//					
//				} else {
//					System.out.println("Khong co file chua du lieu msisdn trong thu muc: " + msisdnPath);
//				} 
//			} else {
//				System.out.println("Thu muc chua file msisdn khong ton tai! " + msisdnPath);
//			}
//						
////			for (int i = 1; i<= 10; i++){
////				safeSleep();
////				pb.step();
////				pb.setExtraMessage("Excuting ...");
////			}
//
////			pb.setExtraMessage("Completed!");
////			pb.stop();
//		} else {
//			System.out.println("Dung chuong trinh theo yeu cau nguoi dung!");
//		}
		
	}

	private static void stepTwo() {
		System.out.println("Chay buoc 2!");
	}

	private static void stepThree() {
		System.out.println("Chay buoc 3!");
	}
	
	private static void safeSleep(){
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

