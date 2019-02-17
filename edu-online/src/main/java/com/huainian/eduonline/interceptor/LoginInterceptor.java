package com.huainian.eduonline.interceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.google.gson.Gson;
import com.huainian.eduonline.utils.JWTUtils;
import com.huainian.eduonline.utils.JsonData;

import io.jsonwebtoken.Claims;

public class LoginInterceptor implements HandlerInterceptor{
	
	private static final Gson gson = new Gson();
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String token = request.getHeader("token");
		if (token == null) {
			token = request.getParameter("token");
		}
		if (token != null) {
			Claims claims = JWTUtils.checkJWT(token);
			Integer userId = (Integer)claims.get("id");
			String userName = (String)claims.get("name");
			request.setAttribute("userId", userId);
			request.setAttribute("userName", userName);
			return true;
		}
		sendJsonMessage(response, JsonData.builderFail().data("未登录，请登录！"));
		return false;
	}
	
	private void sendJsonMessage(HttpServletResponse response,Object object) throws IOException {
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter printWriter = response.getWriter();
		printWriter.write(gson.toJson(object));
		printWriter.close();
		response.flushBuffer();
	}
}
