package com.cnu.iqas.utils;

import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {
	public static final String JWSDIR ="jwsdir";
	public static final String JWSVERSION = "jwsversion";
	private static Properties properties = new Properties();
	static{
		try {
			properties.load(PropertyUtils.class.getClassLoader().getResourceAsStream("wordnet.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 根据key值读取值
	 * @param key
	 * @return
	 */
	public static String get(String key){		
		return (String)properties.get(key);
	}

}
