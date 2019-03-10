package com.huainian.eduonline.service.impl;

import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.huainian.eduonline.bean.entity.User;
import com.huainian.eduonline.bean.entity.Video;
import com.huainian.eduonline.bean.entity.VideoOrder;
import com.huainian.eduonline.config.WechatConfig;
import com.huainian.eduonline.mapper.UserMapper;
import com.huainian.eduonline.mapper.VideoMapper;
import com.huainian.eduonline.mapper.VideoOrderMapper;
import com.huainian.eduonline.service.VideoOrderService;
import com.huainian.eduonline.utils.CommonUtils;
import com.huainian.eduonline.utils.HttpUtils;
import com.huainian.eduonline.utils.WXPayUtils;
@Service
public class VideoOrderServiceImpl implements VideoOrderService {
	
	@Autowired
	private VideoMapper videoMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired 
	private  VideoOrderMapper VideoOrderMapper;
	@Autowired
	private WechatConfig weChatConfig;
	
	private static final  Logger dataLogger  = LoggerFactory.getLogger("dataLogger");
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public String saveOrder(VideoOrder videoOrder1) throws Exception {
		
		dataLogger.info("module=video_order`api=save`user_id={}`video_id={}",videoOrder1.getUserId(),videoOrder1.getVideoId());
		//查找视频
		Video video = videoMapper.getVideoById(videoOrder1.getVideoId());
		//查找用户
		User user = userMapper.getUserById(videoOrder1.getUserId());
		//生成订单
		VideoOrder videoOrder = new VideoOrder();
        videoOrder.setTotalFee(10);
        videoOrder.setVideoImg(video.getCoverImg());
        videoOrder.setVideoTitle(video.getTitle());
        videoOrder.setCreateTime(new Date());
        videoOrder.setVideoId(video.getId());
        videoOrder.setState(0);
        videoOrder.setUserId(user.getId());
        videoOrder.setHeadImg(user.getHeadImg());
        videoOrder.setNickname(user.getName());

        videoOrder.setDel(0);
        videoOrder.setIp(videoOrder1.getIp());
        videoOrder.setOutTradeNo(CommonUtils.getUUID());
        VideoOrderMapper.insert(videoOrder);
		//获取codeUrl
		String codeUrl = unifiedOrder(videoOrder);
		return codeUrl;
	}

	@Override
	public VideoOrder findOrderByOutTradeNo(String outTradeNo) {
		
		return VideoOrderMapper.findByOutTradeNo(outTradeNo);
	}

	@Override
	public int updateOrderByOutTradeNo(VideoOrder videoOrder) {
		
		return VideoOrderMapper.updateVideoOderByOutTradeNo(videoOrder);
	}
	
	private String unifiedOrder(VideoOrder videoOrder) throws Exception{
		//生成签名
		SortedMap<String, String> params = new TreeMap<>();
		params.put("appid",weChatConfig.getAppId());
        params.put("mch_id", weChatConfig.getMchId());
        params.put("nonce_str",CommonUtils.getUUID());
        params.put("body",videoOrder.getVideoTitle());
        params.put("out_trade_no",videoOrder.getOutTradeNo());
        params.put("total_fee",videoOrder.getTotalFee().toString());
        params.put("spbill_create_ip",videoOrder.getIp());
        params.put("notify_url",weChatConfig.getPayCallbackUrl());
        params.put("trade_type","NATIVE");
        String sign = WXPayUtils.createSign(params, weChatConfig.getKey());
        params.put("sign", sign);
        //map转xml
		String payXml = WXPayUtils.mapToXml(params);
		//同一下单
		String orderXml = HttpUtils.doPost(weChatConfig.getUNIFIED_ORDER_URL(), payXml,400);
		if (null != orderXml) {
			//xml转map
			Map<String, String> unifiedOrderMap = WXPayUtils.xmlToMap(orderXml);
			if (null  != unifiedOrderMap) {
				return unifiedOrderMap.get("code_url");
			}
		}
		return null;
	}
	

}
