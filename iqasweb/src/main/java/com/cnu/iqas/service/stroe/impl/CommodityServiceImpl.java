package com.cnu.iqas.service.stroe.impl;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

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
	@Override
	public void save(Commodity entity) {
		commodityDao.save(entity);
		
	}
	@Override
	public void update(Commodity entity) {
		// TODO Auto-generated method stub
		commodityDao.update(entity);
	}
	@Override
	public void delete(Serializable... entityids) {
		// TODO Auto-generated method stub
		commodityDao.delete(entityids);
	}
	@Override
	public Commodity find(Serializable entityId) {
		// TODO Auto-generated method stub
		return commodityDao.find(entityId);
	}
	@Override
	public Commodity findByName(String name) {
		// TODO Auto-generated method stub
		return commodityDao.find("o.name=?", name);
	}
	@Override
	public QueryResult<Commodity> getScrollData(int firstindex, int maxresult, String wherejpql, Object[] queryParams,
			LinkedHashMap<String, String> orderby) {
		// TODO Auto-generated method stub
		return commodityDao.getScrollData(firstindex, maxresult, wherejpql, queryParams,orderby);
	}
	@Override
	public List<Commodity> getAllData(String wherejpql, Object[] queryParams, LinkedHashMap<String, String> orderby) {
		// TODO Auto-generated method stub
		return commodityDao.getAllData(wherejpql, queryParams, orderby);
	}

}
