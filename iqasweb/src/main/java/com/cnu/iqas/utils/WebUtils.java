package com.cnu.iqas.utils;

import org.apache.commons.beanutils.BeanUtils;

public class WebUtils {
	
	/*将表单类中的信息拷贝到对应的类中 */
	public static void copyBean(Object dest,Object src){
		try {
			// 通过BeanUtils类调用方法将表单类中的信息拷贝到对应的类中 ，拷贝过程中只支持8中基本数据类型
			BeanUtils.copyProperties(dest, src);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
