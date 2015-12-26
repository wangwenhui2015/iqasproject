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
import com.cnu.iqas.bean.store.UserCommodityRel;
import com.cnu.iqas.bean.user.User;
import com.cnu.iqas.constant.PageViewConstant;
import com.cnu.iqas.service.stroe.CommodityService;
import com.cnu.iqas.service.stroe.CommodityTypeService;
import com.cnu.iqas.service.stroe.StoreService;
import com.cnu.iqas.service.stroe.UserCommodityRelService;
import com.cnu.iqas.service.user.UserService;
import com.cnu.iqas.utils.JsonTool;
import com.hp.hpl.jena.sparql.pfunction.library.str;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
* @author 周亮 
* @version 创建时间：2015年12月22日 上午11:30:53
* 类说明  商店服务类，功能有：
* 1.根据商品类型型等级来查看该商品类型下商品，及用户的金币和已购买该类型下商品的信息
* 2.用户购买某个商品
* 
*/
@Controller
@RequestMapping(value="/mobile/store/")
public class MStoreController {
	/**
	 * 商品类型服务接口
	 */
	private CommodityTypeService commodityTypeService;
	/**
	 * 商品服务接口
	 */
	private CommodityService commodityService;
	/**
	 * 用户服务接口
	 */
	private UserService userService;
	/**
	 * 商店服务接口
	 */
	private StoreService storeService;
	/**
	 * 用户购买商品记录服务接口
	 */
	private UserCommodityRelService userCommodityRelService;
	
	/**
	 * 获取商店的商品信息及用户的金币数
	 * @param userName 用户名
	 * @param password 密码
	 * @return
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value="getStore")
	public ModelAndView getCommoditysOfType(String userName,String password){

		ModelAndView mv =new ModelAndView(PageViewConstant.JSON);
		//此次操作返回的json
		JSONObject jsonObject =new JSONObject();
		//此次操作结果描述
		MyStatus status = new MyStatus();
		/**
		 * 存放：金币总数，当前可查看商品类型下购买商品种数,封装在json中
		 */
		JSONObject jsonDes=new JSONObject();
		//构造的商品集合信息json格式数组
		JSONArray jsonArrayCommoditys= new JSONArray();
		
		try{
		//1.根据用户名密码查询用户是否存在
		User user = userService.validate(userName, password);
		if( user !=null){
			jsonDes.put("coinCount", user.getAllCoins());//金币总数
			//当前可查看商品类型下购买商品种数，在下面封装
			
			//2.获得用户商品类型等级
			Integer storeGrade =user.getStoreGrade();
			//3.根据商品类型等级查询商品类型
			CommodityType type =commodityTypeService.findByGrade(storeGrade);
			//商品类型下所有商品
			List<Commodity> commoditys =null;
			
			if( type !=null){
				//7.查看用户在该商品类型下已购买的所有商品信息
				List<UserCommodityRel> userCommodityRels= userCommodityRelService.findUserCommodityRels(user.getUserId(),type.getId());
				int diffierentCommoditySpecices=userCommodityRels.size();
				//当前可查看商品类型下购买商品种数
				jsonDes.put("spieces",diffierentCommoditySpecices); //当前可查看商品类型下购买商品种数
				
				//4.构造查询条件
				String wherejpql = "o.typeid=? and o.visible=?";
				List<Object> queryParams  = new ArrayList<Object>();
				queryParams.add(type.getId());
				queryParams.add(true);
				//5.构造排序条件
				LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
				orderby.put("createTime", "desc");
				//6.根据条件查询商品
				commoditys= commodityService.getAllData(wherejpql, queryParams.toArray(), orderby);
				//8.构造返回的商品信息json数组，数组中数据为：{商品id,商品购买数量，商品金币数，商品图片保存路径}
				
				for( Commodity comm : commoditys){
					//存放一个商品的json数据
					JSONObject jsonCom = new JSONObject();
					jsonCom.put("id", comm.getId()); //id
					jsonCom.put("coinCount", comm.getCoinCount());//该商品金币数
					jsonCom.put("savePath", comm.getSavePath());//图片保存路径
					//获取用购买该商品的数量
					int buyCount =getBuyCount(userCommodityRels,comm);
					jsonCom.put("buyCount", buyCount); //购买该商品数量
					jsonArrayCommoditys.add(jsonCom);
				}
			}
		
		}else{
				status.setMessage("用户名或密码不正确！");
				status.setStatus(-1);
			}
		} catch (Exception e) {
			status.setStatus(0);
			status.setMessage("未知异常");
			e.printStackTrace();
		}finally{
			/**
			 * 将此次操作描述,用户金币数、已买商品种数，商品数组，添加到json中
			 */
			jsonObject.put("userDes", jsonDes);
			JsonTool.putJsonObject(jsonObject, jsonArrayCommoditys, status);
			 mv.addObject("json", jsonObject.toString());
			 return mv;
		}
		
	}
	
	/**
	 * 用户购买商品
	 * @param userName 用户名
	 * @param password 密码
	 * @param id 购买商品的id
	 * @return
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value="buyCommodity")
	public ModelAndView buyCommodity(String userName,String password,String id){
		/**
		 * 购买成功后，用户金币数减；用户商品关系表中添加一条记录，或者刷新该条记录的购买个数。这些操作在事务中完成
		 */
		ModelAndView mv =new ModelAndView(PageViewConstant.JSON);
		//此次操作返回的json
		JSONObject jsonObject =new JSONObject();
		//此次操作结果描述
		MyStatus status = new MyStatus();
		try {
			//1.根据用户名密码查询用户是否存在
			User user = userService.validate(userName, password);
			if( user !=null){
				if( id!=null && !"".equals(id.trim())){
					//1.查询商品
					 Commodity com =	commodityService.find(id);
					 if( com!=null){
						//2.商品金币数
						int coinValue= com.getCoinCount();
						//3.商品类型
						String typeid=com.getTypeid();
						//4.用户当前拥有的金币数
						int ownCoins = user.getAllCoins();
						//5.用户拥有金币数大于商品价格可以买
						if( ownCoins>=coinValue){
							//6。用户金币减少
							user.setAllCoins(ownCoins-coinValue);
							//7.查看购买记录是否存在，存在则在数量加1
							UserCommodityRel ucRel=	userCommodityRelService.findUserCommodityRel(user.getUserId(), com.getId());
							//购买记录不存在
							if( ucRel==null){
								//7.生成一条购买记录
								ucRel = new UserCommodityRel();
								ucRel.setCoId(id);
								ucRel.setTypeId(typeid);
								ucRel.setUserId(user.getUserId());
								ucRel.setCount(1);
							}else{
								//数量加1
								ucRel.setCount(ucRel.getCount()+1);
							}
							//刷新用户和保存购买记录
							storeService.updateUserAndCommodity(user,ucRel);
							
						}else{
							status.setStatus(3);
							status.setMessage("您的金币数不足!");
						}
					 }else{
						 status.setStatus(2);
						 status.setMessage("该商品不存在!");
					 }
				}else{
					status.setMessage("商品id参数有误！");
					status.setStatus(4);
				}
			}else{
				status.setMessage("用户名或密码不正确！");
				status.setStatus(-1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			status.setStatus(0);   
			status.setMessage("未知异常啊！");
		}finally{
			JsonTool.putStatusJson(status, jsonObject);
			mv.addObject("json", jsonObject.toString());
			return mv;
		}
		
	}
	/**
	 * 获取用户购买某商品的数量,如果某商品不在用户购买的商品之内则返回0
	 * @param userCommodityRels  用户已购买的商品
	 * @param comm  要判断的商品
	 * @return ,
	 */
	private int getBuyCount(List<UserCommodityRel> userCommodityRels, Commodity comm){
		for(UserCommodityRel rel : userCommodityRels){
			if( rel.getCoId().equals(comm.getId()))
			{
				return rel.getCount();
			}
		}
		return 0;
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
	public UserService getUserService() {
		return userService;
	}
	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public StoreService getStoreService() {
		return storeService;
	}
	@Resource
	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}

	public UserCommodityRelService getUserCommodityRelService() {
		return userCommodityRelService;
	}
	@Resource
	public void setUserCommodityRelService(UserCommodityRelService userCommodityRelService) {
		this.userCommodityRelService = userCommodityRelService;
	}
	
}
