package com.jblog.vo;

import lombok.Data;

@Data
public class BlogVo {	
private Long userNo; //식별번호
private String blogTitle; //블로그제목
private String logoFile; //블로그이미지경로
}
