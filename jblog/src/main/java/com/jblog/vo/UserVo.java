package com.jblog.vo;

import lombok.Data;

@Data//lombok을 위한 선언 (위치중요)
public class UserVo {
private Long userNo; //회원식별번호(int는 max=2,147,483,647이므로 회원이 많으면 초과 방지)
private String id; //아이디
private String userName; //회원이름
private String password; //패스워드
private String jaoinDate; //가입일

//로그인  생성자
public UserVo(String id, String password) {
	super();
	this.id = id;
	this.password = password;
}




public UserVo() {
}





//생성자는 lombok으로 


}
