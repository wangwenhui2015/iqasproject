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
	 * 用户不存在
	 */
	public static int USER_NOT_EXIST =2003;
	/**
	 * 用户名或者密码有误
	 */
	public static int USER_NAME_OR_PASSWORD_ERROR =2004;
	/**
	 * 用户名不存在
	 */
	public static int USER_NAME_ERROR =2005;
	/**
	 * 用户金币不足
	 */
	public static int USER_COINS_NOT_ENOUGH=2006;

	/**
	 * 好友不存在
	 */
	public static int FRIEND_NOT_EXIST =2007;
	/**
	 * 好友已经存在
	 */
	public static int FRIEND_HAVE_EXIST =2008;
	/**
	 * 好友请求无效
	 */
	public static int FRIEND_REQUEST_INVAIN=2009;

	
	/* 商店状态信息*/
	/**
	 * 商店商品不存在
	 */
	public static int STORE_UNEXIST_COMMODITY=3001;
	/**
	 * 购买商品不在你可以购买的商品类型之内,即你当前不能购买该类型下的商品!
	 */
	public static int STORE_OVER_COMMODITY_TYPE=3002;
	


	/**
	 * 知识本体库中没有此主题
	 */
	public static int NOUMENON_NO_THEME =4001;
	/**
	 * 知识本体库中没有此单词
	 */
	public static int NOUMENON_NO_WORD =4002;
	
	
	/**
	 * 单词资源不存在
	 */
	public static int WORD_RESOURCE_NO_EXIST=5001;
	
}
