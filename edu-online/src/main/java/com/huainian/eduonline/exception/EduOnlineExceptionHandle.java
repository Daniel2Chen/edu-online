package com.huainian.eduonline.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.huainian.eduonline.utils.JsonData;

@RestControllerAdvice
public class EduOnlineExceptionHandle {
		
		@ExceptionHandler(value=Exception.class)
		public JsonData handle(Exception e) {
			if (e instanceof EduOnlineException) {
				EduOnlineException eduOnlineException = (EduOnlineException)e;
				return JsonData.builderException("服务器开小差了。。。");
			}
			return JsonData.builderException(e.getMessage());
		}
}
