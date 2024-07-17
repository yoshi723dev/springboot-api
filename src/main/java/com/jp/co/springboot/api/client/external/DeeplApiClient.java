package com.jp.co.springboot.api.client.external;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.jp.co.springboot.api.client.external.request.DeeplTranslateV2Request;
import com.jp.co.springboot.api.client.external.response.DeeplTranslateV2Response;

import reactor.core.publisher.Mono;

@Service
@EnableAutoConfiguration
public class DeeplApiClient {

    @Value("${deepl.api.baseurl}")
    private String baseUrl;
    
    @Value("${deepl.api.key}")
    private String apiKey;
    
    @Value("${deepl.api.authsuffix}")
    private String authSuffix;
    
    @Value("${deepl.translate.path}")
    private String translatePath;
	
	public DeeplTranslateV2Response callTranslateV2(DeeplTranslateV2Request request) throws Exception {
		WebClient client = null;
		DeeplTranslateV2Response response = new DeeplTranslateV2Response();
		
		try {
			client = WebClient.builder().baseUrl(this.baseUrl).build();
			response = client.post()
				.uri(this.translatePath)
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", this.authSuffix + this.apiKey)
				.body(Mono.just(request), DeeplTranslateV2Request.class)
				.retrieve()
				.bodyToMono(DeeplTranslateV2Response.class)
				.block();
		} catch(Exception e) {
			throw new Exception("API Error");
		}
		return response;
	}
	

}