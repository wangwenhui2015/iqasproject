package com.cnu.iqas.service.stroe.impl;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cnu.iqas.bean.base.QueryResult;
import com.cnu.iqas.bean.store.Commodity;
import com.cnu.iqas.dao.store.CommodityDao;
import com.cnu.iqas.service.stroe.CommodityService;

/**
* @author 周亮 
* @version 创建时间：2015年12月21日 下午6:35:32
* 类说明
*/
@Service("commodityService")
public class CommodityServiceImpl implements CommodityService {

	private CommodityDao commodityDao;
	@Override
	public QueryResult<Commodity> getScrollData(String wherejpql, Object[] queryParams,
			LinkedHashMap<String, String> orderby) {
		// TODO Auto-generated method stub
		
		return commodityDao.getScrollData(wherejpql, queryParams, orderby);
	}
	public CommodityDao getCommodityDao() {
		return commodityDao;
	}
	@Resource
	public void setCommodityDao(CommodityDao commodityDao) {
		this.commodityDao = commodityDao;
	}

}
