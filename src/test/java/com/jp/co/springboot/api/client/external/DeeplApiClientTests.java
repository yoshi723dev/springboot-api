package com.jp.co.springboot.api.client.external;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.reactive.function.client.WebClient;

import com.jp.co.springboot.api.client.external.request.DeeplTranslateV2Request;
import com.jp.co.springboot.api.client.external.response.DeeplTranslateV2Response;
import com.jp.co.springboot.api.client.external.response.DeeplTranslateV2Response.Translations;

@SpringBootTest
public class DeeplApiClientTests {

    @MockBean
    private DeeplApiClient deeplApiClient;
    
    @Mock
    private WebClient webClient;

    @Test
    public void callTranslateV2_success01() {
    	try {
	        String translatedText = "Hello";
	        DeeplTranslateV2Response mockResponse = new DeeplTranslateV2Response();
	        Translations translation = new Translations();
	        translation.setText(translatedText);
	        mockResponse.setTranslations(new Translations[] {translation});
	        
	        when(deeplApiClient.callTranslateV2(any(DeeplTranslateV2Request.class))).thenReturn(mockResponse);
	
	        DeeplTranslateV2Request request = new DeeplTranslateV2Request();
	    	request.setText(new String[]{"こんにちは"});
	    	request.setTargetLang("EN");
	    	DeeplTranslateV2Response response = deeplApiClient.callTranslateV2(request);
	        
	        assertEquals(response.getTranslations().length, 1);
	        assertEquals(response.getTranslations()[0].getText(), translatedText);
    	} catch (Exception e) {
    		fail(e.getMessage());
    	}
    }
    
    @Test
    public void callTranslateV2_success02() {
    	try {
	        String translatedText1 = "Hello";
	        String translatedText2 = "Test";
	        DeeplTranslateV2Response mockResponse = new DeeplTranslateV2Response();
	        Translations translation1 = new Translations();
	        translation1.setText(translatedText1);
	        Translations translation2 = new Translations();
	        translation2.setText(translatedText2);
	        mockResponse.setTranslations(new Translations[] {translation1, translation2});
	        
	        when(deeplApiClient.callTranslateV2(any(DeeplTranslateV2Request.class))).thenReturn(mockResponse);
	
	        DeeplTranslateV2Request request = new DeeplTranslateV2Request();
	    	request.setText(new String[]{"こんにちは", "てすと"});
	    	request.setTargetLang("EN");
	    	DeeplTranslateV2Response response = deeplApiClient.callTranslateV2(request);
	        
	        assertEquals(response.getTranslations().length, 2);
	        assertEquals(response.getTranslations()[0].getText(), translatedText1);
	        assertEquals(response.getTranslations()[1].getText(), translatedText2);
    	} catch (Exception e) {
    		fail(e.getMessage());
    	}
    }
    
    //@Ignore
    //@Test
    //todo webclientからのexception throw
    public void callTranslateV2_error01() {
    	try {
	        when(webClient.post()).thenThrow(new RuntimeException(new Exception()));
	        //doThrow(new RuntimeException(new Exception())).when(webClient).post();
	
	        DeeplTranslateV2Request request = new DeeplTranslateV2Request();
	    	request.setText(new String[]{"こんにちは"});
	    	request.setTargetLang("EN");
	    	DeeplTranslateV2Response response = deeplApiClient.callTranslateV2(request);
	    	fail("no error");
    	} catch (Exception e) {
	        assertEquals(e.getMessage(), "API Error");
    	}
    }
}
