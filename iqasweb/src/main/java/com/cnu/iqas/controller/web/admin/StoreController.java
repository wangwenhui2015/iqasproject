package com.cnu.iqas.controller.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
* @author 周亮 
* @version 创建时间：2015年12月18日 下午2:17:04
* 类说明 商店控制类，包含功能：
* 1.添加商品类型
* 2.设置商品类型不可见
* 3.设置商品类型可见
* 4.修改商品类型名称
* 5.分页查询所有商品类型
* 6.分页查询某个商品类型下的商品
* 7.为某个商品类型添加商品
* 8.设置商品不可见
* 9.设置商品可见
* 10.修改商品名称
* 11.修改商品价格
*/
@Controller
@RequestMapping(value="/admin/control/store")
public class StoreController {

	
	public ModelAndView list(){
		
		return null;
	}
	
}
