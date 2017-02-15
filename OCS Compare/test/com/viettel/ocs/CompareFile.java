package com.viettel.ocs;

import java.util.SortedSet;

import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;
import com.viettel.ocs.util.FileUtil;

public class CompareFile {

	public static void main(String[] args) {
		String filePath1 = "C:/Users/SMSC/Desktop/Hanh45/File/compare/step1/tmp_batch.txt";
		String filePath2 = "C:/Users/SMSC/Desktop/Hanh45/File/compare/step2/tmp_batch.txt";
		
		FileUtil fileUtil = new FileUtil();
		
		SortedSet<String> msisdnInfo1 = fileUtil.getAllMsisdnInfo(filePath1);
		SortedSet<String> msisdnInfo2 = fileUtil.getAllMsisdnInfo(filePath2);
		
		System.out.println("msisdnInfo1: " + msisdnInfo1.size());
		System.out.println("msisdnInfo2: " + msisdnInfo2.size());
		
		SetView<String> intersection = Sets.intersection(msisdnInfo1, msisdnInfo2);
		
		System.out.println("intersection: " + intersection.size());
		
		SetView<String> union = Sets.union(msisdnInfo1, msisdnInfo2);
		
		System.out.println("union: " + union.size());
		
		SetView<String> difference = Sets.difference(msisdnInfo1, msisdnInfo2);
		
		System.out.println("difference: " + difference.size());
//		System.out.println("difference: " + difference.toString());
	}

}
