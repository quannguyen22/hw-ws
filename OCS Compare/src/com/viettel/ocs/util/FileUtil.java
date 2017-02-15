package com.viettel.ocs.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

public class FileUtil {

	/**
	 * Lay danh sach tat cac cac file trong thu muc.
	 * 
	 * @param folder
	 * @return
	 */
	public static List<String> listFilesForFolder(final File folder) {
		List<String> msisdns = new ArrayList<String>();
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				msisdns.add(fileEntry.getPath());
				System.out.println(fileEntry.getPath());
			}
		}

		return msisdns;
	}

	/**
	 * Ham doc noi dung cua file chua msisdn
	 * 
	 * @param filePath
	 * @return
	 */
	public static Map<String, String> getAllMsisdn(String filePath, Map<String, String> msisdns) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filePath));
			String line = br.readLine();

			while (line != null) {
				msisdns.put(StringUtils.trimToEmpty(line), StringUtils.trimToEmpty(line));
				line = br.readLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(br);
		}

		return msisdns;
	}

	/**
	 * Ham doc noi dung cua file chua thong tin chi tiet cua tung msisdn
	 * 
	 * @param filePath
	 * @return
	 */
	public SortedSet<String> getAllMsisdnInfo(String filePath) {
		BufferedReader br = null;
//		Set<String> msisdnInfo = new HashSet<String>();
		
		SortedSet<String> msisdnInfo=new TreeSet<String>();
		
		try {
			br = new BufferedReader(new FileReader(filePath));
			String line = br.readLine();

			while (line != null) {
				msisdnInfo.add(StringUtils.trimToEmpty(line));
				line = br.readLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(br);
		}

		return msisdnInfo;
	}
	
	
}
