package com.cnu.iqas.service.stroe;

import com.cnu.iqas.bean.store.Commodity;
import com.cnu.iqas.bean.store.CommodityType;

/**
* @author 周亮 
* @version 创建时间：2015年12月22日 上午11:14:50
* 类说明
*/
public interface StoreService {
	public void saveCommodityAndUpdateType(Commodity commodity,CommodityType type);
}
