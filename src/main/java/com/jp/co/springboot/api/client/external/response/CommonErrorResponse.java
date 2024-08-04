package com.jp.co.springboot.api.client.external.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CommonErrorResponse {
    @JsonProperty("status")
    private int status;

    @JsonProperty("message")
    private String     message;
    
    public CommonErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
