package com.cnu.iqas.service.stroe;

import java.util.LinkedHashMap;

import com.cnu.iqas.bean.base.QueryResult;
import com.cnu.iqas.bean.store.Commodity;

/**
* @author 周亮 
* @version 创建时间：2015年12月21日 下午6:35:19
* 类说明
*/
public interface CommodityService {

	/**
	 * 根据条件查询所有数据,根据条件排序
	 * @param wherejpql 查询条件  "o.email=? and o.account=?"
	 * @param queryParams 查询条件占位符对应的参数值，
	 * @param orderby 排序条件  Key为属性,Value为asc/desc
	 * @return
	 */
	public QueryResult<Commodity> getScrollData(String wherejpql, Object[] queryParams, LinkedHashMap<String, String> orderby);

}
