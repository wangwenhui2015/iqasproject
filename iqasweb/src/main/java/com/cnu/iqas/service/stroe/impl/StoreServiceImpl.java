package com.cnu.iqas.service.stroe.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cnu.iqas.bean.store.Commodity;
import com.cnu.iqas.bean.store.CommodityType;
import com.cnu.iqas.dao.store.CommodityDao;
import com.cnu.iqas.dao.store.CommodityTypeDao;
import com.cnu.iqas.service.stroe.StoreService;

/**
* @author 周亮 
* @version 创建时间：2015年12月22日 上午11:15:09
* 类说明 服务中的方法具有事务属性
*/
@Service("storeService")
@Transactional
public class StoreServiceImpl implements StoreService {

	private CommodityDao commodityDao;
	private CommodityTypeDao commodityTypeDao;
	/**
	 * 保存商品，并更新商品类型表的商品数量
	 * @param commodity
	 * @param type
	 * @return
	 */
	public void saveCommodityAndUpdateType(Commodity commodity,CommodityType type){
		commodityTypeDao.update(type);
		commodityDao.save(commodity);
		
	}
	
	
	public CommodityDao getCommodityDao() {
		return commodityDao;
	}
	@Resource
	public void setCommodityDao(CommodityDao commodityDao) {
		this.commodityDao = commodityDao;
	}
	public CommodityTypeDao getCommodityTypeDao() {
		return commodityTypeDao;
	}
	@Resource
	public void setCommodityTypeDao(CommodityTypeDao commodityTypeDao) {
		this.commodityTypeDao = commodityTypeDao;
	}
	
}
