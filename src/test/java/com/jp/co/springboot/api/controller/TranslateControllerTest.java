package com.jp.co.springboot.api.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.jp.co.springboot.api.client.external.DeeplApiClient;
import com.jp.co.springboot.api.client.external.request.DeeplTranslateV2Request;
import com.jp.co.springboot.api.client.external.response.DeeplTranslateV2Response;
import com.jp.co.springboot.api.client.external.response.DeeplTranslateV2Response.Translations;
import com.jp.co.springboot.api.common.ErrorMessage;
import com.jp.co.springboot.api.exception.ApiCommonException;

@WebMvcTest(TranslateController.class)
public class TranslateControllerTest {
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private DeeplApiClient deeplApiClient;
    
    @Test
    public void translate_success01() throws Exception {
        DeeplTranslateV2Response mockResponse = new DeeplTranslateV2Response();
        String translatedText = "Test";
        Translations translation = new Translations();
        translation.setText(translatedText);
        mockResponse.setTranslations(new Translations[] {translation});
        when(deeplApiClient.callTranslateV2(any(DeeplTranslateV2Request.class))).thenReturn(mockResponse);
        
        mockMvc.perform(MockMvcRequestBuilders.get("/").param("message", new String[] {"てすと"}))
                .andExpect(status().isOk())
                .andExpect(content().string("Test<br>"));
    }
    
    @Test
    public void translate_success02() throws Exception {
        DeeplTranslateV2Response mockResponse = new DeeplTranslateV2Response();
        String translatedText1 = "Test";
        Translations translation1 = new Translations();
        translation1.setText(translatedText1);
        String translatedText2 = "Hello";
        Translations translation2 = new Translations();
        translation2.setText(translatedText2);
        mockResponse.setTranslations(new Translations[] {translation1, translation2});
        when(deeplApiClient.callTranslateV2(any(DeeplTranslateV2Request.class))).thenReturn(mockResponse);
        
        mockMvc.perform(MockMvcRequestBuilders.get("/").param("message", new String[] {"てすと","こんにちは"}))
                .andExpect(status().isOk())
                .andExpect(content().string("Test<br>Hello<br>"));
    }
    
    @Test
    public void translate_error01() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"status\":400,\"message\":\"No message entered.\"}"));
    }

    @Test
    public void translate_error02() throws Exception {
        when(deeplApiClient.callTranslateV2(any(DeeplTranslateV2Request.class)))
        	.thenThrow(new ApiCommonException(400, ErrorMessage.ERR_API_E0001));
        
        mockMvc.perform(MockMvcRequestBuilders.get("/").param("message", new String[] {"てすと"}))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"status\":400,\"message\":\"API Parameter Error.\"}"));
    }

    @Test
    public void translate_error03() throws Exception {
        when(deeplApiClient.callTranslateV2(any(DeeplTranslateV2Request.class)))
        	.thenThrow(new ApiCommonException(503, ErrorMessage.ERR_API_E0002));
        
        mockMvc.perform(MockMvcRequestBuilders.get("/").param("message", new String[] {"てすと"}))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"status\":503,\"message\":\"API Timeout Error.\"}"));
    }
    
    @Test
    public void translate_error04() throws Exception {
        when(deeplApiClient.callTranslateV2(any(DeeplTranslateV2Request.class)))
        	.thenThrow(new ApiCommonException(500, ErrorMessage.ERR_API_E0099));
        
        mockMvc.perform(MockMvcRequestBuilders.get("/").param("message", new String[] {"てすと"}))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"status\":500,\"message\":\"API Error.\"}"));
    }
}
