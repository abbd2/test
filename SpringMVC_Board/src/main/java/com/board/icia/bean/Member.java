package com.board.icia.bean;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
//@Alias("Member")
public class Member {
	private String m_id;
	private String m_pwd;
	private String m_name;
	private String m_birth;
	private String m_addr;
	private String m_phone;
	
	private int m_point;  //포인트
	private String m_grade; //실버, 골드
}
