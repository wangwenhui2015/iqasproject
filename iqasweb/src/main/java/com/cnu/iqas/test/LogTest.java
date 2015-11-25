package com.cnu.iqas.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cnu.iqas.bean.user.User;

/**
* @author 周亮 
* @version 创建时间：2015年11月10日 下午8:27:21
* 类说明
*/
public class LogTest {
	
	private static final Logger logger= LogManager.getLogger(LogTest.class);
	public static void main(String[] args){
		
		/*User user = new User();
		user.setPassword("1323");
		user.setUsername("zhangsan");
		logger.trace(user.getUsername());
		logger.debug(user.getUsername());
		logger.info(user.getUsername());
		logger.warn(user.getUsername());
		logger.error(user.getUsername());
		logger.fatal(user.getUsername());*/
		String rex="[123456]{1}";
		String rex2="\\d{1}";
		String word = "67";
		System.out.println(word.matches(rex2));
	}
	
	
}
