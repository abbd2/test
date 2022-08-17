package com.board.icia.dao;


import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Repository;

import com.board.icia.bean.Member;

public interface IMemberDao {

	boolean access(Member mb);

	boolean memberJoin(Member mb);

	String getSecurityPwd(String m_id); 
	
	Member getMemberInfo(String m_id);

	@Select("SELECT * FROM MINFO")  
	List<Member> getMemberList();

}
