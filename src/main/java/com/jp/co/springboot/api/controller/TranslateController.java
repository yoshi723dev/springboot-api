package com.jp.co.springboot.api.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jp.co.springboot.api.client.external.DeeplApiClient;
import com.jp.co.springboot.api.client.external.request.DeeplTranslateV2Request;
import com.jp.co.springboot.api.client.external.response.DeeplTranslateV2Response;
import com.jp.co.springboot.api.common.ErrorMessage;

@RestController
public class TranslateController {
    
    @Autowired
    private DeeplApiClient client;

    @GetMapping(value="/")
    public String translate(@RequestParam(name = "message", required = false) String[] message) {
        if (message == null) {
            return ErrorMessage.ERR_E0001;
        }
        
        DeeplTranslateV2Response response = new DeeplTranslateV2Response();
        DeeplTranslateV2Request request = new DeeplTranslateV2Request();
        request.setText(message);
        request.setTargetLang("EN");
        try {
            response = this.client.callTranslateV2(request);
        } catch (Exception e) {
            return e.getMessage();
        }
        
        String result = "";
        for (int i=0; i<response.getTranslations().length; i++) {
            result = result + response.getTranslations()[i].getText() + "<br>";
        }
        
        return result;
    }
}
