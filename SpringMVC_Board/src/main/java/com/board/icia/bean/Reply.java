package com.board.icia.bean;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain=true)
@Data
//@Alias("Reply")
public class Reply {
	private int r_bnum; //원글 번호(FK)
	private int r_num; //댓글 번호(PK)
	private String r_contents; //댓글
	private String r_id; // 작성자(FK)
	//jackson Timestamp와 서로 호환되지 않음
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Timestamp r_date;     
	//String r_date ;  //오라클 to_char(날짜데이터, 'YYYY/MM/DD, HI24:MI:SS')
}
