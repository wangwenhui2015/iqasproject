package com.cnu.iqas.controller.web.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.cnu.iqas.bean.user.User;
import com.cnu.iqas.formbean.BaseForm;
import com.cnu.iqas.formbean.user.UserForm;
import com.cnu.iqas.service.user.UserService;
import com.cnu.iqas.utils.WebUtils;

@Controller
@RequestMapping(value = "/user")  //在类前面定义，则将url和类绑定。
@SessionAttributes({"user"})   //将ModelMap中属性名字为user的放入session中。这样，request和session中都有了。
public class UserController {
	
	private UserService userService;
	
	
	/*转发登录界面*/
	@RequestMapping(value = "/loginUI") //负责处理/login.html的请求
    public String loginUI() {
        return "front/login";
    }
	/*登录*/
	@RequestMapping(value="/login")
	
	public ModelAndView login( UserForm formbean){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("front/login");  //登录视图
		//1.校验表单数据
		if( !BaseForm.validate(formbean.getUsername()) || !BaseForm.validate(formbean.getPassword()) ){
			mv.addObject("error", "请填写用户名和密码!");
		}else{
		 //2.检查用户是否存在
			User u =userService.find(formbean.getUsername());
			
			if( null == u || !u.getPassword().equalsIgnoreCase(formbean.getPassword())){
					mv.addObject("error", "用户名或密码有误!");
			}else{
				//将user信息保存到session中
				mv.addObject("user", u);
				//重定向到主界面front/main.jsp,先访问myforward方法，由该方法在访问main.jsp，因为jsp页面放置在WEB-INF下面，直接访问不了
				mv.setViewName("redirect:/base/myforward.html?urladdress=front/main");
				//mv.setViewName("redirect:front/main");//主页视图
			}
		}
		return mv;
	}
	
	//注册转发界面
	@RequestMapping(value = "/registerUI") 
    public String registerUI() {
        return "front/register";
    }
	//注册
	@RequestMapping(value="/register")
	public ModelAndView register(UserForm formbean){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("front/register");  //注册视图
		//1.校验表单数据是否正确
		if( !BaseForm.validate(formbean.getUsername()) || !BaseForm.validate(formbean.getPassword()) ){
			mv.addObject("error", "请填写用户名和密码!");
		}else{
			//2.查看用户是否已存在
			User user =userService.find(formbean.getUsername());
			if( user != null)
				mv.addObject("error", "用户名已被注册!");
			else{
				//将表单中的数据复制到user中
				user = new User();
				WebUtils.copyBean(user, formbean);
				//保存到数据库中
				userService.save(user);
				//返回页面,此处应该防止表单重复提交
				mv.setViewName("share/message");
				mv.addObject("message", "注册成功!");
				mv.addObject("urladdress", "/user/loginUI");
			}
		}
		
		return mv;
	}
	
	
	public UserService getUserService() {
		return userService;
	}
	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	

}
