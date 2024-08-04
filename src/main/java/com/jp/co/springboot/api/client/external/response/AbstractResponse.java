package com.jp.co.springboot.api.client.external.response;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AbstractResponse {
    @JsonProperty("status")
    private int status;
    
    public AbstractResponse() {
        this.status = HttpStatus.OK.value();
    }
}
