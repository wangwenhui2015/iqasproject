package com.cnu.iqas.controller.web.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.cnu.iqas.bean.admin.Admin;
import com.cnu.iqas.formbean.admin.AdminForm;
import com.cnu.iqas.service.admin.AdminService;

/**
* @author 周亮 
* @version 创建时间：2015年11月16日 上午10:52:52
* 类说明 管理员控制类，包含功能：
* 1.登录
* 2.退出
*/
@Controller
@RequestMapping(value="/admin")
//@SessionAttributes({"admin"})   //将ModelMap中属性名字为admin的放入session中。这样，request和session中都有了。
public class AdminController {
	
	private AdminService adminService;
	
	
	/*管理员登录转发界面*/
	@RequestMapping(value = "/loginUI") 
	public String adminloginUI(){
		return "admincenter/login";
	}
	@RequestMapping(value="/control/main")
	public String adminmain(){
		
		return "admincenter/main";
	}
	/*登录*/
	@RequestMapping(value="/login")
	public ModelAndView login( @Valid AdminForm formbean,BindingResult bindingResult,HttpServletRequest request){
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admincenter/login");  //登录视图
		//1.校验表单数据
		if(!bindingResult.hasErrors()){
			//mv.addObject("error", "请填写账号和密码!");
			formbean.getErrors().put("result",  "请填写账号和密码!");
			mv.addObject("formbean", formbean);
		}else{
		 //2.检查用户是否存在
			Admin admin= adminService.validate(formbean.getAccount(), formbean.getPassword());
			if( null == admin ){
				formbean.getErrors().put("result",  "账号或密码有误!");
			    mv.addObject("formbean", formbean);
			}else{
				//将admin信息保存到session中
				//mv.addObject("admin", admin);
				request.getSession().setAttribute("admin", admin);
				//重定向到主界面front/main.jsp,先访问myforward方法，由该方法在访问main.jsp，因为jsp页面放置在WEB-INF下面，直接访问不了
				//mv.setViewName("redirect:/base/myforward.html?page=admincenter/main");
				mv.setViewName("redirect:/admin/control/main.html");
				//mv.setViewName("redirect:front/main");//主页视图
			}
		}
		return mv;
	}
	@RequestMapping(value="/exit")
	public String exit(HttpServletRequest request){
		request.getSession().removeAttribute("admin");
		request.getSession().invalidate();

		return "redirect:/admin/loginUI.html";
	}
	public AdminService getAdminService() {
		return adminService;
	}
	@Resource
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
}
