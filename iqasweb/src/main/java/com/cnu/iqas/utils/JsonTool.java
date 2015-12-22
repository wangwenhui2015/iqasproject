/**
 * 
 */
package com.cnu.iqas.utils;

import java.util.List;

import com.cnu.iqas.bean.base.MyStatus;
import com.cnu.iqas.bean.iword.WordResource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;


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
	
	/**
	 * 将集合元素封装成json格式数据
	 * @param list  要封装的数组集合
	 * @param config  类在转换成json数据时遵循的一些配置规则
	 * @param status  状态标识
	 * @return
	 * 返回数据类似如下格式：data中的内容就是list集合中的内容，count表示data中的条数，也就是list集合中数据数
	 * {
		 *  status:1,
		 *  message:"ok",
		 *  result:{
		 *   count:2,
		 *   data:[
		 *    
		 *		{id:"2353sdkfhosdf",name:boat.jpg,type=1,savepath:"http://172.19.68.77:8080/zhushou/images/logo.jpg"},
		 *      {id:"2353sdkfhosdf",name:boat.jpg,type=1,savepath:"http://172.19.68.77:8080/zhushou/images/logo.jpg"},
		 *      
		 *   ]
		 *  }
		 * }
	 */
	public static <T> String createJson(List<T> list,JsonConfig config,MyStatus status){
	
		//整个json
		JSONObject jsonObject = new JSONObject();
		//result json
		JSONObject resultObject = new JSONObject();
		//数组json
		JSONArray jsonArray = new JSONArray();
		
		int count =0;
		if( list !=null ){
			for( T entity :list )
			{
				JSONObject entityJson;
				if( config ==null)
					 entityJson = JSONObject.fromObject(entity);
				else
				    entityJson = JSONObject.fromObject(entity, config);
				jsonArray.add(entityJson);
				count++;
			}
		}
		resultObject.put("count", count);
		resultObject.put("data", jsonArray);
		jsonObject.put("result", resultObject);
	 
		JsonTool.putStatusJson(status, jsonObject);
		return jsonObject.toString();
	}
	

	/**
	 * 
	  * 将集合元素封装成json格式数据
	 * @param list  要封装的数组集合
	 * @param status  状态标识
	 * @return
	 * 返回数据类似如下格式：data中的内容就是list集合中的内容，count表示data中的条数，也就是list集合中数据数
	 * {
		 *  status:1,
		 *  message:"ok",
		 *  result:{
		 *   count:2,
		 *   data:[
		 *    
		 *		{id:"2353sdkfhosdf",name:boat.jpg,type=1,savepath:"http://172.19.68.77:8080/zhushou/images/logo.jpg"},
		 *      {id:"2353sdkfhosdf",name:boat.jpg,type=1,savepath:"http://172.19.68.77:8080/zhushou/images/logo.jpg"},
		 *      
		 *   ]
		 *  }
		 * }
	 */
	public static <T> String createJson(List<T> list,MyStatus status){
		return JsonTool.createJson( list,null,status);
	}
}
