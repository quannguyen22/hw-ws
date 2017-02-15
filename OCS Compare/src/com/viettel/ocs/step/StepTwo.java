package com.viettel.ocs.step;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.commons.lang3.StringUtils;

import com.viettel.ocs.runnable.CompareTmpAcmMRunnable;
import com.viettel.ocs.runnable.CompareTmpBatchRunnable;
import com.viettel.ocs.runnable.CompareTmpBlacklistSubsRunnable;
import com.viettel.ocs.runnable.CompareTmpFnNbrRunnable;
import com.viettel.ocs.runnable.CompareTmpFnSrvRunnable;
import com.viettel.ocs.runnable.CompareTmpLangEnRunnable;
import com.viettel.ocs.runnable.CompareTmpOcsCdrHisRunnable;
import com.viettel.ocs.runnable.CompareTmpRecharge24hRunnable;
import com.viettel.ocs.runnable.CompareTmpSubsUppRunnable;
import com.viettel.ocs.runnable.CompareTmpVtCutRunnable;
import com.viettel.ocs.runnable.CompareTmpVtCutSeaRunnable;
import com.viettel.ocs.util.Constant;
import com.viettel.ocs.util.FileUtil;
import com.viettel.ocs.util.PropsUtil;

public class StepTwo {

	public StepTwo(){
		// Danh dau cho chuong trinh biet luu data toi thu muc buoc 2
		Constant.STEP = "2";					
	}
	
	public void start(){

		System.out.println("------------------------------------------------------------------------------------ ");
		System.out.println("");
		System.out.println("Danh sach cac webservice se su dung: ");
		System.out.println("  - QueryAccumulationUsage:    " + StringUtils.trimToEmpty( PropsUtil.getInstance().getValue("query.accumulation.usage") ));
		System.out.println("  - QueryBalance:              " + StringUtils.trimToEmpty( PropsUtil.getInstance().getValue("query.balance")));
		System.out.println("  - QueryCustomerInfo:         " + StringUtils.trimToEmpty( PropsUtil.getInstance().getValue("query.customer.info")));
		System.out.println("  - QueryFreeUnit:             " + StringUtils.trimToEmpty( PropsUtil.getInstance().getValue("query.free.unit")));
		System.out.println("  - QueryLoanLog:              " + StringUtils.trimToEmpty( PropsUtil.getInstance().getValue("query.loan.log")));
		System.out.println("  - QueryOfferingInstProperty: " + StringUtils.trimToEmpty( PropsUtil.getInstance().getValue("query.offering.inst.property")));
		System.out.println("  - QueryRechargeLog:          " + StringUtils.trimToEmpty( PropsUtil.getInstance().getValue("query.recharge.log")));
		System.out.println("  - QuerySubLifeCycle:         " + StringUtils.trimToEmpty( PropsUtil.getInstance().getValue("query.sub.lifecycle")));
		System.out.println("  - QuerySubStatusHis:         " + StringUtils.trimToEmpty( PropsUtil.getInstance().getValue("query.sub.status.his")));
		System.out.println("");		
		System.out.println("Thu muc chua file msisdn dau vao: " + StringUtils.trimToEmpty( PropsUtil.getInstance().getValue("msisdn.path")));
		System.out.println("");
		System.out.println("------------------------------------------------------------------------------------ ");
		System.out.println("");
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Ban chac chan muon chay chuong trinh voi cac tham so tren [Y/n]: ");
		String confirm = scanner.nextLine();
		confirm = StringUtils.trimToEmpty(confirm);
		
		if (StringUtils.isEmpty(confirm) || StringUtils.equals(confirm.toLowerCase(), "y")){
			System.out.println("");
			System.out.println("Bat dau thuc thi buoc 2!");
			System.out.println("");
			
//			ProgressBar pb = new ProgressBar("OCSTool", 10, ProgressBarStyle.ASCII);
//			pb.start(); // the progress bar starts timing
//			pb.setExtraMessage("Excuting ...");
			
			// Xu ly doc danh sach so dien thoai.
			String msisdnPath = StringUtils.trimToEmpty( PropsUtil.getInstance().getValue("msisdn.path"));
			final File folder = new File(msisdnPath);
			
			if (folder.exists() && folder.isDirectory()) {
				List<String> msisdnFiles = FileUtil.listFilesForFolder(folder);
				
				// Neu co danh sach file msisdn thi thuc hien.
				if (!msisdnFiles.isEmpty()){
					// Xu ly doc file de lay danh sach msisdn
					// Loc trung msisdn trong buoc xu ly nay
					Map<String, String> msisdnMap = new HashMap<String, String>();
					
					String filePath = "";
					for (int i = 0; i < msisdnFiles.size(); i++){
//						pb.setExtraMessage("Dang xu ly du lieu msisdn dau vao...");

						filePath = msisdnFiles.get(i);
						msisdnMap = FileUtil.getAllMsisdn(filePath, msisdnMap);
					}
					
					// Danh sach msisdn da duoc loc trung.
					// Dau vao cho class truy van thong tin tu webservice.
					List<String> msisdns = new ArrayList<String>(msisdnMap.values());
					Collections.sort(msisdns);
					
//					pb.maxHint(msisdns.size());
					// Truyen ca tham so progress bar toi thread xu ly truy van webservice
					
					
					/**
					 * Xu ly so sanh 2 file ket qua de duoc 1 file ket qua cac thong tin khong thay doi trong ca 2 lan.
					 */
					ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
					executor.execute(new CompareTmpAcmMRunnable());
					executor.execute(new CompareTmpBatchRunnable());
					executor.execute(new CompareTmpBlacklistSubsRunnable());
					executor.execute(new CompareTmpFnNbrRunnable());
					executor.execute(new CompareTmpFnSrvRunnable());
					executor.execute(new CompareTmpLangEnRunnable());
					executor.execute(new CompareTmpOcsCdrHisRunnable());
					executor.execute(new CompareTmpRecharge24hRunnable());
					executor.execute(new CompareTmpSubsUppRunnable());
					executor.execute(new CompareTmpVtCutRunnable());
					executor.execute(new CompareTmpVtCutSeaRunnable());
					
					executor.shutdown();
					
//					for (int k = 0; k < msisdns.size(); k++){
////						System.out.println(msisdns.get(k));
////						safeSleep();
//						pb.step();
//						pb.setExtraMessage("Excuting ...");
//					}
						
					
				} else {
					System.out.println("Khong co file chua du lieu msisdn trong thu muc: " + msisdnPath);
				} 
			} else {
				System.out.println("Thu muc chua file msisdn khong ton tai! " + msisdnPath);
			}
						
//			for (int i = 1; i<= 10; i++){
//				safeSleep();
//				pb.step();
//				pb.setExtraMessage("Excuting ...");
//			}

//			pb.setExtraMessage("Completed!");
//			pb.stop();
		} else {
			System.out.println("Dung chuong trinh theo yeu cau nguoi dung!");
		}
	}
}
