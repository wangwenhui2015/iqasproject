package com.cnu.iqas.controller.mobile.store;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.cnu.iqas.bean.base.DateJsonValueProcessor;
import com.cnu.iqas.bean.base.MyStatus;
import com.cnu.iqas.bean.base.PageView;
import com.cnu.iqas.bean.base.QueryResult;
import com.cnu.iqas.bean.store.Commodity;
import com.cnu.iqas.bean.store.CommodityType;
import com.cnu.iqas.constant.PageViewConstant;
import com.cnu.iqas.formbean.store.StoreForm;
import com.cnu.iqas.service.stroe.CommodityService;
import com.cnu.iqas.service.stroe.CommodityTypeService;
import com.cnu.iqas.utils.JsonTool;

import net.sf.json.JsonConfig;

/**
* @author 周亮 
* @version 创建时间：2015年12月22日 上午11:30:53
* 类说明  商店服务类
*/
@Controller
@RequestMapping(value="/mobile/store")
public class MStoreController   implements ServletContextAware{
	 //应用对象
	 private ServletContext servletContext;
	/**
	 * 商品类型服务接口
	 */
	private CommodityTypeService commodityTypeService;
	/**
	 * 商品服务接口
	 */
	private CommodityService commodityService;
	/**
	 * 根据用户的等级给出不同类型的商品
	 * @param grade
	 * @return
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value="getCommoditysOfType")
	public ModelAndView getCommoditysOfType(StoreForm formbean,HttpServletRequest request){
		ModelAndView mv =new ModelAndView(PageViewConstant.JSON);
		//状态
		MyStatus status = new MyStatus();
		//查看类型
		CommodityType type =commodityTypeService.findByGrade(formbean.getGrade());
		//构造页面类
		PageView<Commodity> pv = new PageView<Commodity>(formbean.getMaxresult(), formbean.getPage());
		
		//配置
		JsonConfig comConfig = null;
		try {
			if( type!= null){
				//构造查询条件
				String wherejpql = "o.typeid=? and o.visible=?";
				List<Object> queryParams  = new ArrayList<Object>();
				queryParams.add(type.getId());
				queryParams.add(true);
				//构造排序条件
				LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
				orderby.put("createTime", "desc");
				//查询
				QueryResult<Commodity> query= commodityService.getScrollData(pv.getFirstResult(),pv.getMaxresult(),wherejpql, queryParams.toArray(), orderby);
				
				//设置到页面类中
				pv.setQueryResult(query);
				//配置
				comConfig = new JsonConfig();
				comConfig.registerJsonValueProcessor(Date.class,new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
				//排除属性,创建时间、可见性、商品所属类型、商品图片的大小、商品图片的后缀名
				comConfig.setExcludes(new String[]{"createTime","visible","typeid","picSize","ext"});
			}else{
				status.setMessage("商品类型等级不存在!");
				status.setStatus(2);
			}
		} catch (Exception e) {
			status.setStatus(0);
			e.printStackTrace();
		}finally{
			
			 String jsonString= JsonTool.createJson(pv.getRecords(),comConfig, status);
			 mv.addObject("json", jsonString);
			 return mv;
		}
		
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
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		this.servletContext = servletContext;
		
	}
}
