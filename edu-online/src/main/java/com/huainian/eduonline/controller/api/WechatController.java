package com.huainian.eduonline.controller.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.huainian.eduonline.bean.entity.User;
import com.huainian.eduonline.bean.entity.VideoOrder;
import com.huainian.eduonline.config.WechatConfig;
import com.huainian.eduonline.service.UserService;
import com.huainian.eduonline.service.VideoOrderService;
import com.huainian.eduonline.utils.JWTUtils;
import com.huainian.eduonline.utils.JsonData;
import com.huainian.eduonline.utils.WXPayUtils;


@RestController
@RequestMapping("/api/v1/wechat")
public class WechatController {

    @Autowired
    private WechatConfig weChatConfig;
    @Autowired
    private UserService UserService;
    @Autowired
    private VideoOrderService videoOrderService;
    /**
     * 拼装微信扫一扫登录url
     * @return
     */
    @GetMapping("loginByWechat")
    @ResponseBody
    public JsonData loginByWechat(@RequestParam(value = "access_page",required = true)String accessPage) throws UnsupportedEncodingException {

        String redirectUrl = weChatConfig.getOpenRedirectUrl(); //获取开放平台重定向地址

        String callbackUrl = URLEncoder.encode(redirectUrl, "UTF-8"); //进行编码

        String qrcodeUrl = String.format(weChatConfig.getOpenQrcodeUrl(),weChatConfig.getOpenAppId(),callbackUrl,accessPage);

        return JsonData.builderSuccess().data(qrcodeUrl);
    }
    @GetMapping("/user/callback")
    public void saveUser(@RequestParam(value="code") String code,@RequestParam(value="state") String state,HttpServletResponse response) throws UnsupportedEncodingException, IOException {
    	
    	User loginUser = UserService.saveWechatUser(code);
    	if (loginUser != null && loginUser.getOpenid() != null) {
			String token = JWTUtils.geneJsonWebToken(loginUser);
			// state 当前用户的页面地址，需要拼接 http://  这样才不会站内跳转

            response.sendRedirect(state+"?token="+token+"&head_img="+loginUser.getHeadImg()+"&name="+URLEncoder.encode(loginUser.getName(),"UTF-8"));
		}
	}
    
    @RequestMapping("/order/callback")
    public void orderCallback(HttpServletResponse response,HttpServletRequest request) throws Exception{
		
    	InputStream is = request.getInputStream();
    	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
    	StringBuffer sb = new StringBuffer();
    	String line;
    	while ((line=bufferedReader.readLine())!=null) {
			sb.append(line);
			
		}
    	//xml转map
    	Map<String, String> orderCallbackMap = WXPayUtils.xmlToMap(sb.toString());
    	if (orderCallbackMap != null) {
			//验证签名是否正确
    		SortedMap<String, String> callback = WXPayUtils.toSortedMap(orderCallbackMap);
    		if (WXPayUtils.isCorrectSign(callback, weChatConfig.getKey())) {
    			//判断是否支付成功
    			if ("SUCCESS".equals(callback.get("result_code"))) {
					//修改待支付订单状态
    				 String outTradeNo = callback.get("out_trade_no"); 
    				 VideoOrder videoOrder = videoOrderService.findOrderByOutTradeNo(outTradeNo);
    				 if (null !=  videoOrder  && videoOrder.getState() == 0) {//判断订单存在并处于待支付状态
    					 VideoOrder videoOrder1 = new VideoOrder();
    					 videoOrder1.setOpenid(callback.get("openid"));
    					 videoOrder1.setOutTradeNo(outTradeNo);
    					 videoOrder1.setNotifyTime(new Date());
    					 videoOrder1.setState(1);
    					 int rows = videoOrderService.updateOrderByOutTradeNo(videoOrder1);
    					 if (rows >0) {//修改订单状态成功,通知微信订单修改成功
							response.setContentType("text/xml");
							response.getWriter().println("success");
						}
						
					}
				}
			}
    		//业务处理失败
    		response.setContentType("text/xml");
			response.getWriter().println("fail");
		}
	}
}
