package com.cnu.iqas.utils;

import java.security.MessageDigest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.servlet.ModelAndView;

import com.cnu.iqas.bean.base.MyStatus;

import net.sf.json.JSONObject;

public class WebUtils {
	//16进制字符
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	/*将表单类中的信息拷贝到对应的类中 */
	public static void copyBean(Object dest,Object src){
		try {
			// 通过BeanUtils类调用方法将表单类中的信息拷贝到对应的类中 ，拷贝过程中只支持8中基本数据类型
			BeanUtils.copyProperties(dest, src);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 根据信息返回json数据
	 * @param scode  状态码
	 * @param message 状态码说明
	 * @param jsonObejct 返回总的json类
	 * @param resultObject jsonObejct中的result数据
	 * @param mv  视图
	 * @return
	 */
	public static ModelAndView beforeReturn(int scode,String message,JSONObject jsonObejct,JSONObject resultObject,ModelAndView mv){
		//状态对象
		MyStatus status =new MyStatus(scode,message);
		JsonTool.putStatusJson(status, jsonObejct);
		jsonObejct.put("result", resultObject);
		mv.addObject("json", jsonObejct.toString());
		return mv;
	}
	
	/**
	 * url路径中的MD5编码，以防出现“=”号
	 * @param origin 源数据
	 * @return MD5数据，不含有"="可以放在连接路径中以防产生错误参数
	 */
	public static String MD5Encode(String origin) {
		String resultString = null;

		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString
					.getBytes()));
		} catch (Exception ex) {

		}
		return resultString;
	}
	/**
	 * 转换字节数组为16进制字串
	 * @param b
	 *            字节数组
	 * @return 16进制字串
	 */

	public static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}
}
