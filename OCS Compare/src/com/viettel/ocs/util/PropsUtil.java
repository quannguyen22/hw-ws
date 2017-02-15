/*
 * Created on Feb 13, 2017
 *
 * Copyright (C) 2017 by Viettel Network Company. All rights reserved
 */
package com.viettel.ocs.util;

import java.io.IOException;
import java.util.Properties;

/**
 * Ho tro doc cac config cua chuong trinh tu file config.properties
 * 
 * @author Nguyen Hai Ha (hanh45@viettel.com.vn)
 * @since Feb 13, 2017
 * @version 1.0.0
 */
public class PropsUtil {
	private static PropsUtil instance = null;
	private static Properties properties = null;

	protected PropsUtil() throws IOException {
		properties = new Properties();
		properties.load(PropsUtil.class.getClassLoader().getResourceAsStream("config.properties"));
	}

	public static PropsUtil getInstance() {
		if (instance == null) {
			try {
				instance = new PropsUtil();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	public String getValue(String key) {
		return properties.getProperty(key);
	}
}
