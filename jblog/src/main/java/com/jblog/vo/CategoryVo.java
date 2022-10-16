package com.jblog.vo;

import lombok.Data;

@Data//lombok을 위한 선언 (위치중요)
public class CategoryVo {
	private int cateNo;
	private Long userNo;
	private String cateName;
	private String description;
	private String regDate;
	private int countPost;
}
