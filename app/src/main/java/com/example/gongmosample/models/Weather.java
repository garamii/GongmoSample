
package com.example.gongmosample.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * TODO JSON 파싱을 위한 준비
 */
@JsonIgnoreProperties(ignoreUnknown = true)     // 매칭 되는 필드가 없을 때 무시
public class Weather {

    public String CULTURE_NM;
    public String START_DT;
    public String END_DT;

//
//    @JsonProperty("temp")       // 속성과 변수명이 같을 경우 생략 가능
//    public String temp;
//
//    @JsonProperty("description")
//    public String description;

}
