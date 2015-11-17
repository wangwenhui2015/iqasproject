package com.cnu.iqas.controller.mobile.user;

import java.util.Date;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cnu.iqas.bean.base.DateJsonValueProcessor;
import com.cnu.iqas.bean.base.MyStatus;
import com.cnu.iqas.bean.user.User;
import com.cnu.iqas.formbean.user.UserForm;
import com.cnu.iqas.service.user.UserService;
import com.cnu.iqas.utils.JsonTool;
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

	private UserService userService;
	/**
	 * 
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
		int scode =MyStatus.OK;//结果
		String message ="ok";//结果说明
		//总的json对象
		JSONObject jsonObejct = new JSONObject();
		
		//------------------以下为业务逻辑，此处可以采用面向切面编程
		try{
			//参数校验
			if(!bindingResult.hasErrors()){
				System.out.println(bindingResult.hasErrors()+":"+bindingResult.getFieldValue("username")+"："+bindingResult.getFieldValue("password"));
				//检查账号是否存在
				if( null==userService.find(formbean.getUsername()))
				{
					//注册
					User u = new User();
					WebUtils.copyBean(u, formbean);
					userService.save(u);
				}else{
					scode = MyStatus.USEREXIS;
					message ="用户已存在!";
				}
				
			}else{
				scode = MyStatus.PARAMERROR;
				message=bindingResult.getFieldError("username").getDefaultMessage()+" : "+bindingResult.getFieldError("password").getDefaultMessage();//获取参数错误信息
				
			}
		}catch(Exception e ){
			scode = MyStatus.PARAMERROR;
			message = e.getMessage();
		}finally{
			//-------------------
			//状态对象
			MyStatus status =new MyStatus(scode,message);
			if(jsonObejct !=null || status !=null)
			{
				jsonObejct.put("status", status.getStatus());
				jsonObejct.put("message", status.getMessage());
			}
			mv.addObject("json", jsonObejct.toString());
			return mv;
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
		System.out.println("dddddd");
		int scode =MyStatus.OK;//结果
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
			//参数校验
			if(formbean.validateNameAndPass()){
				//System.out.println(bindingResult.hasErrors()+":"+bindingResult.getFieldValue("username")+"："+bindingResult.getFieldValue("password"));
				//检查账号是否存在
				User user = userService.find(formbean.getUsername());
			
				if( null==user || !user.getPassword().equals(formbean.getPassword()))
				{
					scode = MyStatus.PARAMERROR;
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
			}else{
				scode = MyStatus.PARAMERROR;
				message ="用户名或者密码有误!";
			}
		}catch(Exception e ){
			scode = MyStatus.PARAMERROR;
			message = e.getMessage();
		}finally{
			//-------------------
			//状态对象
			MyStatus status =new MyStatus(scode,message);
			
			JsonTool.putStatusJson(status, jsonObejct);
			jsonObejct.put("result", resultObject);
			mv.addObject("json", jsonObejct.toString());
			return mv;
		}
	}
	/**
	 * public String register()
	{
		//总的json对象
		JSONObject jsonObject = new JSONObject();
		//状态对象
		MyStatus status =new MyStatus();
		try {
			if( null != buyerService.find(formbean.getAccount()))
			{
				status.setStatus(0);
				status.setMessage("账号已存在!");
			}
			else{
				Buyer buyer = new Buyer();
				WebUtils.copyBean(buyer, formbean);
				buyer.setPicturepath(WebUtils.getZhuLogo());
				buyerService.save(buyer);
				//为用户初始化一个好友分类
				BuyerType type = new BuyerType();
				type.setName("好友");
				type.setIsdefault(true);//设为默认分类，不可删除
				type.setBuyer(buyer); //设置关系，有type一端维护关系
				//为用户初始化一个文件夹
				MyFile myFile = new MyFile();
				myFile.setFileid(UUID.randomUUID().toString());
				myFile.setIsfile(false);//设置为文件夹
				myFile.setIsdefault(true);//设置为默认文件夹
				myFile.setName("Code文件夹");
				myFile.setBuyer(buyer);
				
				//保存
				buyerTypeService.save(type);
				myFileService.save(myFile);
				//设置用户的默认文件夹和默认好友分类
				buyer.setDeftype(type.getTypeid());
				buyer.setDeffile(myFile.getFileid());
				buyerService.update(buyer);
			}
		} catch (Exception e) {
			e.printStackTrace();
			status.setStatus(0);
			status.setMessage("failure");
		}
		JsonTool.putStatusJson(status, jsonObject);
		ServletActionContext.getRequest().setAttribute("json", jsonObject.toString());
		return "json";
	}
	 */
	public UserService getUserService() {
		return userService;
	}
	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
}
