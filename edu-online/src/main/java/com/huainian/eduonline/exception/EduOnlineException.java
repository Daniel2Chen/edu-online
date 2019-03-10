package com.huainian.eduonline.exception;

import lombok.Data;

@Data
public class EduOnlineException extends RuntimeException {

		public  EduOnlineException(int code,String msg) {
			super(msg);
			this.code = code;
			this.message = message;
		}
		private int code;
		private String message;
}
