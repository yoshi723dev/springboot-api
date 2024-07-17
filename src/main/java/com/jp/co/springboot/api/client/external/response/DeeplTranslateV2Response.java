package com.jp.co.springboot.api.client.external.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DeeplTranslateV2Response {
	
	@JsonProperty("translations")
	private Translations[] translations;
	
	@Data
	public static class Translations {
		@JsonProperty("detected_source_language")
		private String detectedSourceLanguage;
		
		@JsonProperty("text")
		private String text;
	}
}
