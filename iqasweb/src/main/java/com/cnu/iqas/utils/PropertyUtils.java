package com.cnu.iqas.utils;

import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {
	
	/**
	 * 项目的log图片存放路径
	 */
	public static final String LOG ="log";
	
	public static final String JWSDIR ="jwsdir";
	public static final String JWSVERSION = "jwsversion";
	/**
	 * 商品图片保存相对路径
	 */
	public static final String COMMODITY_PIC="commodity.picturesavedir";
	
	
	private static Properties properties = new Properties();
	static{
		try {
			properties.load(PropertyUtils.class.getClassLoader().getResourceAsStream("savepath.properties"));
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
