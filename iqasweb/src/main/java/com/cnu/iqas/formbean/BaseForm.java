package com.cnu.iqas.formbean;

import java.util.HashMap;
import java.util.Map;

public class BaseForm {
	/*错误集合*/
	private Map<String,String> errors = new HashMap<String,String>();
	
	/**
	 * 检查字符串是否有效
	 * @param str
	 * @return true：有效，
	 */
	public static boolean validate(String str)
	{
		if( null !=str && !"".equals(str.trim()))
				return true;
		else {
			return false;
		}
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}
	
}
