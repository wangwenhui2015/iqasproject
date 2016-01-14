package com.cnu.iqas.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * 包含项目中各个资源的key值和通过key值获取资源相对路径的方法。
 * @author dell
 * 
 */
public class PropertyUtils {
	
	/**
	 * 项目文件系统根目录文件夹存放的路径
	 */
	public static final String IQASWEB_FILE_SYSTEM_DIR="fileSystemRoot";
	/**
	 * 项目文件系统的根目录文件夹名称
	 */
	public static final String IQASWEB_FILE_SYSTEM_NAME="ifilesystem";
	
	/**
	 * 项目的log图片存放路径
	 */
	public static final String LOG ="log";
	/**
	 * wordnet路径
	 */
	public static final String JWSDIR ="jwsdir";
	/**
	 * wordnet版本号
	 */
	public static final String JWSVERSION = "jwsversion";
	/**
	 * 单词图片资源路径
	 */
	public static final String WORD_IMAGE_DIR ="word.imagesavedir";
	/**
	 * 单词视频资源路径
	 */
	public static final String WORD_VIDEO_DIR = "word.videosavedir";
	/**
	 * 单词声音资源路径
	 */
	public static final String WORD_VOICE_DIR ="word.voicesavedir";
	/**
	 * 单词绘本资源路径
	 */
	public static final String WORD_PICTUREBOOK_DIR = "word.picturebooksavedir";
	
	/**
	 * 项目一：商品图片保存相对路径
	 */
	public static final String FIRST_COMMODITY_PIC="first.commodity.picturesavedir";
	
	
	
	private static Properties properties = new Properties();
	static{
		try {
			properties.load(PropertyUtils.class.getClassLoader().getResourceAsStream("savepath.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 根据key值读取savepath.properties文件中配置的值
	 * @param key
	 * @return
	 */
	public static String get(String key){		
		return (String) properties.get(key);
	}
	/**
	 * 整个项目文件系统的跟文件夹名称
	 * @return
	 */
	private static String getFileSystemFileName(){
		return (String) properties.get(PropertyUtils.IQASWEB_FILE_SYSTEM_NAME);
	}
	/**
	 * 根据PropertiesUtils中常量的值获取资源保存路径，该路径由整个项目文件系统的根文件夹名称和资源key值对应的相对路径构成。
	 * @param key
	 * @return
	 */
	public static String getFileSaveDir(String key){
		return  getFileSystemFileName()+properties.getProperty(key);
	}
	
}
