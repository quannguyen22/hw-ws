/*
 * Created on Feb 13, 2017
 *
 * Copyright (C) 2017 by Viettel Network Company. All rights reserved
 */
package com.viettel.ocs;

import java.util.Scanner;

import com.viettel.ocs.step.StepOne;
import com.viettel.ocs.step.StepThree;
import com.viettel.ocs.step.StepTwo;

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
public class Launcher {

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
			
			StepOne stepOne = new StepOne();
			stepOne.start();
			
		} else if (step == 2) {
			
			StepTwo stepTwo = new StepTwo();
			stepTwo.start();
			
		} else if (step == 3) {
			
			StepThree stepThree = new StepThree();
			stepThree.start();
			
		} else {
			System.out.println("Chuong trinh khong ho tro step: " + step + " ==> thoat chuong trinh!!!");
		}
	}

}

