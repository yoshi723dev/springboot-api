package com.jp.co.springboot.api.client.external.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DeeplTranslateV2Request {
    
    @JsonProperty("text")
    private String[] text;
    
    @JsonProperty("source_lang")
    private String sourceLang;
    
    @JsonProperty("target_lang")
    private String targetLang;
    
    @JsonProperty("context")
    private String context;
    
    @JsonProperty("split_sentences")
    private String splitSentences;
    
    @JsonProperty("preserve_formatting")
    private String preserveFormatting;
    
    @JsonProperty("formality")
    private String formality;
    
    @JsonProperty("glossary_id")
    private String glossaryId;
    
    @JsonProperty("tag_handling")
    private String tagHandling;
    
    @JsonProperty("outline_detection")
    private String outlineDetection;
    
    @JsonProperty("non_splitting_tags")
    private String nonSplittingTags;
    
    @JsonProperty("splitting_tags")
    private String splittingTags;
    
    @JsonProperty("ignore_tags")
    private String ignoreTags;

}
