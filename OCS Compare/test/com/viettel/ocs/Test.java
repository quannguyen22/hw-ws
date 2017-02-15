package com.viettel.ocs;

import com.viettel.ocs.util.TestConstant;

public class Test {

	public static void main(String[] args) {
		System.out.println(TestConstant.step);
		
		TestConstant.step = "2";
		
		System.out.println(TestConstant.step);
	}

}
