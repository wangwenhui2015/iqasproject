/**
 * 
 */
package com.cnu.iqas.utils;

import com.cnu.iqas.bean.base.MyStatus;

import net.sf.json.JSONObject;


/**
 * @author zhouliang
 * @2015年4月8日
 */
public class JsonTool {

	/**
	 * 将json数据状态加入到JSONObject中
	 * @param status	json数据状态描述类
	 * @param object	JSONObject
	 */
	public static void putStatusJson(MyStatus status,JSONObject object)
	{
		if(object !=null || status !=null)
		{
			object.put("status", status.getStatus());
			object.put("message", status.getMessage());
		}
	}
}
