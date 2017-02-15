package com.viettel.ocs.manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.viettel.ocs.thread.TMP_LANG_EN_thread;



public class TMP_LANG_EN_manager extends Thread {

	public List<String> msdnList = new ArrayList<String>();

	public TMP_LANG_EN_manager(List<String> msdnList) {
		if (msdnList != null)
			this.msdnList.addAll(msdnList);
	}

	public void run() {

		String defaultMessageFilePath = null;
		String defaultMsg = null;
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = this.getClass().getResourceAsStream("/config.properties");
			prop.load(input);
			defaultMessageFilePath = prop.getProperty("TMP_LANG_EN_MESSAGE_FILE_PATH");
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
		
		if(defaultMessageFilePath==null){
			System.out.println("Khoong tim thay cau hinh duong dan den file QueryCustomerInfo.txt");
			return;
		}
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(defaultMessageFilePath), "UTF-8"));

			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			defaultMsg = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
		File file = new File(defaultMessageFilePath);
		if(!file.exists()){
			System.out.println("File QueryCustomerInfo.txt khong ton tai");
			return;
		}
		

		ExecutorService executor = null;
		try {

			executor = Executors.newFixedThreadPool(500);
			List<Callable<Object>> tasks = new ArrayList<>();
			List<TMP_LANG_EN_thread> threads = new ArrayList<>();
			TMP_LANG_EN_thread thread = null;
			for (String msdn : this.msdnList) {
				thread = new TMP_LANG_EN_thread(msdn, defaultMsg);
				threads.add(thread);
				tasks.add(Executors.callable(thread));
			}

			try {
				executor.invokeAll(tasks);
			} catch (InterruptedException e) {

				e.printStackTrace();

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (executor != null) {
				executor.shutdown();
			}
		}

	}
	
	

}
