package com.board.icia.bean;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
//@Alias("Board")
public class Board {   //BLIST VIEW
	private int b_num;
	private String b_title;
	private String b_contents;
	private String b_id;
	private String m_name;    //회원이름  
	private Timestamp b_date;     //년월일 시분초       
	private int b_views; 
}

