package com.cnu.iqas.constant;
/**
* @author 周亮 
* @version 创建时间：2016年1月8日 上午10:24:57
* 类说明 :json格式数据全局状态常量标识
*/
public class StatusConstant {
	/**
	 * 全局返回状态标识
	 */
	/**
	 * 未知异常
	 */
	public static int UNKONWN_EXECPTION=0;
	/**
	 * 正确
	 */
	public static int OK = 1;
	
	
	
	/**
	 * 参数有误
	 */
	public static int PARAM_ERROR = 2001;
	
	/*用户状态信息*/
	/**
	 * 用户已存在
	 */
	public static int USER_EXIST =2002;
	/**
	 * 用户名或者密码有误
	 */
	public static int USER_NAME_OR_PASSWORD_ERROR =2003;
	/**
	 * 用户金币不足
	 */
	public static int USER_COINS_NOT_ENOUGH=5004;

	/* 商店状态信息*/
	/**
	 * 商店商品不存在
	 */
	public static int STORE_UNEXIST_COMMODITY=5005;
	/**
	 * 购买商品不在你可以购买的商品类型之内,即你当前不能购买该类型下的商品!
	 */
	public static int STORE_OVER_COMMODITY_TYPE=5006;
	

}
