package com.huainian.eduonline.service;

import com.huainian.eduonline.bean.entity.VideoOrder;

public interface VideoOrderService {
	
	/**
	 * @Title: saveOrder      
	 * @param videoOrder
	 * @return           
	 * @throws
	 * @Description:下单接口
	 */
	String saveOrder(VideoOrder videoOrder) throws Exception;
	
	/**
	 * 
	 * @Title: findOrderByOutTradeNo      
	 * @param outTradeNo
	 * @return           
	 * @throws
	 * @Description:根据流水号查询订单
	 */
	VideoOrder findOrderByOutTradeNo(String outTradeNo);
	
	/**
	 * 
	 * @Title: updateOrderByOutTradeNo      
	 * @param videoOrder
	 * @return           
	 * @throws
	 * @Description: 根据流水号修改订单
	 */
	int updateOrderByOutTradeNo(VideoOrder videoOrder);

}
