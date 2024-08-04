package com.jp.co.springboot.api.exception;

import lombok.Data;

@Data
public class ApiCommonException extends RuntimeException  {
	
	private static final long serialVersionUID = 1L;
	
	private int httpStatus;
	
	public ApiCommonException(int httpStatus, String errorCode) {
		super(errorCode);
		this.httpStatus = httpStatus;
	}

}
