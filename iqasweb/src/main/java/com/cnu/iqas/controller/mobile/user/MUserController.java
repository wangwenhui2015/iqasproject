package com.cnu.iqas.controller.mobile.user;

import java.util.Date;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cnu.iqas.bean.base.DateJsonValueProcessor;
import com.cnu.iqas.bean.user.User;
import com.cnu.iqas.constant.StatusConstant;
import com.cnu.iqas.controller.web.admin.StoreController;
import com.cnu.iqas.formbean.user.UserForm;
import com.cnu.iqas.service.user.UserService;
import com.cnu.iqas.utils.WebUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
* @author 周亮 
* @version 创建时间：2015年11月7日 下午4:45:16
* 类说明
*/
@Controller
@RequestMapping(value="/mobile/user")
public class MUserController {
	private Logger logger = LogManager.getLogger(MUserController.class);
	private UserService userService;
	/**
	 * @param formbean  用户注册表单类，用于接受前端穿过来的参数
	 * @param bindingResult 存放校验结果
	 * @return json格式数据
	 * {
		  "status": 2,
		  "message": "用户名不能为空 : 密码长度为5~15"
		}
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value="register")
	public ModelAndView register(@Valid UserForm formbean,BindingResult bindingResult){
		
		ModelAndView mv = new ModelAndView("share/json");
		
		System.out.println(formbean.getPassword()+":"+formbean.getUserName()+":"+formbean.getSex()+":"+formbean.getGrade());
		
		int scode =StatusConstant.OK;//结果
		String message ="ok";//结果说明
		//总的json对象
		JSONObject jsonObejct = new JSONObject();
		
		//------------------以下为业务逻辑，此处可以采用面向切面编程
		try{
			//参数校验
			if(!bindingResult.hasErrors()){
				//检查用户名是否存在
				if( null==userService.findByName(formbean.getUserName()))
				{
					//注册
					User u = new User();
					WebUtils.copyBean(u, formbean);
					userService.save(u);
				}else{
					scode = StatusConstant.USER_EXIST;
					message ="用户名已存在!";
				}
			}else{
				scode = StatusConstant.PARAM_ERROR;
				message="注册信息有误!";
			}
		}catch(Exception e ){
			scode = StatusConstant.PARAM_ERROR;
			message = e.getMessage();
		}finally{
			//-------------------返回视图
			return WebUtils.beforeReturn(scode, message, jsonObejct, null, mv);
		}
	}
	
	
	/**
	 * 
	 * @param formbean
	 * @return
	 * {
	 *  status:1,    1:成功，0失败
	 *  message:"ok",原因
	 *  result:{
	 *  count:1,
	 *     data:[
	 *     	{account:"zhangsan",password:"123",gender:"男",picturePath:"http://172.19.68.77:8080/zhushou/images/logo.jpg"}
	 * 		
	 * 	]
	 * 	}
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value="login")
	public ModelAndView login(UserForm formbean){
		ModelAndView mv = new ModelAndView("share/json");

		System.out.println(formbean.getUserName()+formbean.getPassword());
		int scode =StatusConstant.OK;//结果
		String message ="ok";//结果说明
		//总的json对象
		JSONObject jsonObejct = new JSONObject();
		//result对象
		JSONObject resultObject = new JSONObject();
		//用户对象
		JSONObject userObject ;
		//用户对象配置类
		JsonConfig userConfig = new JsonConfig();
		//------------------以下为业务逻辑，此处可以采用面向切面编程
		try{
				//System.out.println(bindingResult.hasErrors()+":"+bindingResult.getFieldValue("username")+"："+bindingResult.getFieldValue("password"));
				//检查账号是否存在
				//User user = userService.find(formbean.getUsername());
				User user= userService.validate(formbean.getUserName(), formbean.getPassword());
				if( null==user )
				{
					scode = StatusConstant.USER_NAME_OR_PASSWORD_ERROR;
					message ="用户名或者密码有误!";
				}else{
					//过滤一些属性
					userConfig.setExcludes(new String[]{});
					userConfig.registerJsonValueProcessor(Date.class,new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
					
					userObject= JSONObject.fromObject(user, userConfig);
						
					JSONArray usersArray = new JSONArray();
					usersArray.add(userObject);
					
					resultObject.put("count", 1);
					resultObject.put("data", usersArray);
				}
			
		}catch(Exception e ){
			scode = StatusConstant.UNKONWN_EXECPTION;
			message ="未知异常";
			logger.error(e.getMessage());
			e.printStackTrace();
		}finally{
			//-------------------返回视图
			return WebUtils.beforeReturn(scode, message, jsonObejct, resultObject, mv);
		}
	}
	/**
	 * 根据用户名获取用户信息
	 * @param userName
	 * @return
	 */
	@RequestMapping(value="getUser")
	public ModelAndView getUser(String  userName){
		ModelAndView mv = new ModelAndView("share/json");

		int scode =StatusConstant.OK;//结果
		String message ="ok";//结果说明
		//总的json对象
		JSONObject jsonObejct = new JSONObject();
		//result对象
		JSONObject resultObject = new JSONObject();
		//用户对象
		JSONObject userObject ;
		//用户对象配置类
		JsonConfig userConfig = new JsonConfig();
		//------------------以下为业务逻辑，此处可以采用面向切面编程
		try{
				//System.out.println(bindingResult.hasErrors()+":"+bindingResult.getFieldValue("username")+"："+bindingResult.getFieldValue("password"));
				//检查账号是否存在
				//User user = userService.find(formbean.getUsername());
				User user= userService.findByName(userName);
				if( null==user )
				{
					scode = StatusConstant.USER_NAME_OR_PASSWORD_ERROR;
					message ="用户名不存在!";
				}else{
					//过滤一些属性
					userConfig.setExcludes(new String[]{});
					userConfig.registerJsonValueProcessor(Date.class,new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
					
					userObject= JSONObject.fromObject(user, userConfig);
						
					JSONArray usersArray = new JSONArray();
					usersArray.add(userObject);
					
					resultObject.put("count", 1);
					resultObject.put("data", usersArray);
				}
			
		}catch(Exception e ){
			scode = StatusConstant.UNKONWN_EXECPTION;
			message ="未知异常";
			logger.error(e.getMessage());
			e.printStackTrace();
		}finally{
			//-------------------返回视图
			return WebUtils.beforeReturn(scode, message, jsonObejct, resultObject, mv);
		}
	}
	public UserService getUserService() {
		return userService;
	}
	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
}
