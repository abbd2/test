package com.board.icia.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.board.icia.bean.Board;
import com.board.icia.bean.Reply;

import lombok.NonNull;

public interface IBoardDao {

	List<Board> getBoardList(Integer pageNum);

	@Select("SELECT COUNT(*) FROM BLIST")
	int getBoardCount();

	Board getContents(@NonNull Integer bNum);

	List<Reply> getReplyList(int r_bnum);	
	
	
	boolean replyInsert(Reply r);
	
	

	

}
