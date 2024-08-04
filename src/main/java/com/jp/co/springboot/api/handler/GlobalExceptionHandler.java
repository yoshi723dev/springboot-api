package com.jp.co.springboot.api.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jp.co.springboot.api.client.external.response.CommonErrorResponse;
import com.jp.co.springboot.api.exception.ApiCommonException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Exceptionがthrowされた場合のエラーレスポンス返却.
     * 
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    public final CommonErrorResponse handleGeneralExceptions(Exception ex, WebRequest request) {
        CommonErrorResponse response = new CommonErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return response;
    }
    
    /**
     * ApiCommonExceptionがthrowされた場合のエラーレスポンス返却.
     * 
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(ApiCommonException.class)
    public final CommonErrorResponse handleApiCommonExceptions(ApiCommonException ex, WebRequest request) {
        CommonErrorResponse response = new CommonErrorResponse(ex.getHttpStatus(), ex.getMessage());
        return response;
    }
}