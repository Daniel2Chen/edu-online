package com.huainian.eduonline.controller.api;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.huainian.eduonline.bean.entity.VideoOrder;
import com.huainian.eduonline.service.VideoOrderService;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
	
	@Autowired
	private VideoOrderService videoOrderService;
	
	@GetMapping("/add")
	public void saveOrder(@RequestParam(value="video_id",required=true) Integer videoId,
			HttpServletResponse response,
			HttpServletRequest request) throws Exception{
		VideoOrder videoOrder = new VideoOrder();
		videoOrder.setVideoId(videoId);
		videoOrder.setIp("218.87.100.10");
		videoOrder.setUserId(1);
		String codeUrl = videoOrderService.saveOrder(videoOrder);
		if (null == codeUrl) {
			throw new NullPointerException();
		}
		try {
			//生成二维码配置
			Map<EncodeHintType, Object> hints = new HashMap<>();
			//设置纠错等级
			hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
			//编码类型
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			//生成二维码
			BitMatrix bitMatrix = new MultiFormatWriter().encode(codeUrl, BarcodeFormat.QR_CODE, 400, 400,hints);
			//通过io流写出
			OutputStream outputStream = response.getOutputStream();
			MatrixToImageWriter.writeToStream(bitMatrix, "png", outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
