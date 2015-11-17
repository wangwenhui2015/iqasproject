package com.cnu.iqas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//该类负责访问某个界面
@RequestMapping(value="/base")
public class BaseController {
	/**
	 * mv.setViewName("redirect:/myforward.html?page=front/main");
	 * @param urladdress
	 * @return
	 */
	@RequestMapping(value="/myforward")
	public String forward(String page){
		return page;
	}
}
