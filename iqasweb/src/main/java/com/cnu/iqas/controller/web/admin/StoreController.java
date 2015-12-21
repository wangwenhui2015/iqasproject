package com.cnu.iqas.controller.web.admin;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cnu.iqas.bean.base.PageView;
import com.cnu.iqas.bean.base.QueryResult;
import com.cnu.iqas.bean.store.Commodity;
import com.cnu.iqas.bean.store.CommodityType;
import com.cnu.iqas.constant.PageViewConstant;
import com.cnu.iqas.formbean.BaseForm;
import com.cnu.iqas.formbean.store.StoreForm;
import com.cnu.iqas.service.stroe.CommodityService;
import com.cnu.iqas.service.stroe.CommodityTypeService;

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
@RequestMapping(value="/admin/control/store/")
public class StoreController {
	/**
	 * 商品类型服务接口
	 */
	private CommodityTypeService commodityTypeService;
	/**
	 * 商品服务接口
	 */
	private CommodityService commodityService;
	

	/**
	 * 分页获取某个商品类型下的所有商品
	 * @param grade 商品类型id
	 * @return
	 */
	@RequestMapping(value="getCommoditysOfType")
	public ModelAndView getCommoditysOfType(StoreForm formbean){

		ModelAndView mv = new ModelAndView(PageViewConstant.MESSAGE);
		//构造页面类
		PageView<Commodity> pv = new PageView<Commodity>(formbean.getMaxresult(), formbean.getPage());
		//构造查询条件
		String wherejpql = "o.typeid=?";
		List<Object> queryParams  = new ArrayList<Object>();
		queryParams.add(formbean.getId());
		//构造排序条件
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("createTime", "desc");
		//查询
		QueryResult<Commodity> query= commodityService.getScrollData(wherejpql, queryParams.toArray(), orderby);
		//设置到页面类中
		pv.setQueryResult(query);
		
		mv.addObject("pageView", pv);

		CommodityType type = commodityTypeService.find(formbean.getId());
		mv.addObject("type", type);
		return mv;
	}
	
	/**
	 * 为某个商品类型添加商品
	 * @param formbean 存放商品信息表单
	 * @param file  商品图片文件
	 * @return
	 */
	@RequestMapping(value="addCommodity")
	public ModelAndView addCommodityForType(StoreForm formbean,CommonsMultipartFile  file){
		
		return null;
	}
	
	
	/**
	 * 添加商品界面
	 * @return
	 */
	@RequestMapping(value="addCommodityUI")
	public ModelAndView addCommodityUI(){
		
		return null;
	}
	
	
	@RequestMapping(value="storeUI")
	public String storeUI()
	{
		return "admincenter/store/addcommoditytype";
	}
	/**
	 * 添加商品类型界面
	 * @return
	 */
	@RequestMapping(value="addTypeUI")
	public String addCommodityTypeUI(){
		
		return PageViewConstant.COMMODITYTYPE_ADD_UI;
	}
	
	/**
	 * 添加商品类型 
	 * @param name 商品类型名
	 * @return
	 */
	@RequestMapping(value="addType")
	public ModelAndView addCommodityType(StoreForm formbean){
		
		ModelAndView mv = new ModelAndView(PageViewConstant.MESSAGE);
		
		if( BaseForm.validate(formbean.getName())){
			if( commodityTypeService.findByName(formbean.getName())==null){
				CommodityType type = new CommodityType();
				type.setName(formbean.getName());
				type.setGrade(formbean.getGrade());
				commodityTypeService.save(type);
				mv.addObject("message", "添加成功");
				
			}else{
				mv.addObject("message", "商品类型已存在!");
			}
		}else{
			mv.addObject("message", "请填写商品类型名称");
		}
		
		mv.addObject("urladdress", "admin/control/store/addTypeUI.html");
		return mv;
	}
	/**
	 * 分页查看所有商品类型
	 * @return
	 */
	@RequestMapping(value="listtype")
	public ModelAndView listType(StoreForm formbean){
		ModelAndView mv = new ModelAndView(PageViewConstant.COMMODITYTYPE_LIST);
		//建立页面类，并初始化每页最大页数
		PageView<CommodityType> pv = new PageView<CommodityType>(formbean.getMaxresult(),formbean.getPage());
		//查询结果按时间降序排列
		LinkedHashMap<String, String> order = new LinkedHashMap<String, String>();
		order.put("createTime", "desc");
		//查询
		QueryResult<CommodityType> query= commodityTypeService.getScrollData(pv.getFirstResult(), pv.getMaxresult(),order);
		//查询结果存到页面类中
		pv.setQueryResult(query);
		//页面类存放到request中
		mv.addObject("pageView", pv);
		return mv;
	}
	/**
	 * 根据商品类型id使商品类型无效
	 * @param id
	 * @return
	 */
	@RequestMapping(value="disableType")
	public String disableType(String id){

		commodityTypeService.makeVisible(id,false);
	
		return "redirect:/admin/control/store/listtype.html";
	}
	
	/**
	 * 根据商品类型id使商品类型有效
	 * @param id
	 * @return
	 */
	@RequestMapping(value="enableType")
	public String enableType(String id){
		commodityTypeService.makeVisible(id,true);
		
		return "redirect:/admin/control/store/listtype.html";
	}

	public CommodityTypeService getCommodityTypeService() {
		return commodityTypeService;
	}
	@Resource
	public void setCommodityTypeService(CommodityTypeService commodityTypeService) {
		this.commodityTypeService = commodityTypeService;
	}

	public CommodityService getCommodityService() {
		return commodityService;
	}

	@Resource
	public void setCommodityService(CommodityService commodityService) {
		this.commodityService = commodityService;
	}
	
	
}
