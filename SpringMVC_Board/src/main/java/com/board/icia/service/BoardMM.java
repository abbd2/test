package com.board.icia.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.board.icia.bean.Board;
import com.board.icia.bean.Member;
import com.board.icia.bean.Reply;
import com.board.icia.dao.IBoardDao;
import com.board.icia.dao.IMemberDao;
import com.board.icia.exception.CommonException;
import com.board.icia.exception.NoReplyException;
import com.board.icia.userClass.Paging;

import lombok.NonNull;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class BoardMM {
	@Autowired
	private IBoardDao bDao;

	@Autowired
	private IMemberDao mDao;

	ModelAndView mav;

	public ModelAndView getBoardList(Integer pageNum, HttpSession session) {
		log.info("pageNum=" + pageNum);
		mav = new ModelAndView();
		String view = null;
		List<Board> bList = null;
		// pageNum=(pageNum==null)?1:pageNum;
		if (pageNum == null) {
			pageNum = 1; //예외발생위해 -1
		}
		//@ControllerAdvice활용 예외처리
		if(pageNum<=0) {
			throw new CommonException("잘못된 페이지번호 입니다.");
		}
		bList = bDao.getBoardList(pageNum);
		if (bList != null && bList.size() != 0) {
			mav.addObject("bList", bList); // jstl제어
			// mav.addObject("bList2", new Gson().toJson(bList)); //js or jQ제어
			mav.addObject("paging", getPaging(pageNum));
			// 로그인 없이 새로운 페이지 요청시 회원정보를 출력하기 위해
			if (session.getAttribute("id") != null) {
				mav.addObject("mb", mDao.getMemberInfo(session.getAttribute("id").toString()));
			}

			view = "boardList";
		} else {
			view = "home"; // ???
		}
		mav.setViewName(view);
		return mav;
	}

	private String getPaging(Integer pageNum) {
		int maxNum = bDao.getBoardCount();
		int listCount = 10; // 페이지당 글의 개수
		int pageCount = 2; // 그룹당 페이지 개수
		String boardName = "boardList"; // url
		Paging paging = new Paging(maxNum, pageNum, listCount, pageCount, boardName);
		return paging.makeHtmlPaging();
	}

	public ModelAndView getContents(@NonNull Integer bNum) {
		mav = new ModelAndView();
		String view = null;
		Board board = bDao.getContents(bNum);// 글상세내용 가져옴

		mav.addObject("board", board); // jstl
		// mav.addObject("board",new Gson().toJson(board)); //js or jQ

		List<Reply> rList = bDao.getReplyList(bNum);
		mav.addObject("rList", rList);

		view = "boardContentsAjax"; // jsp
		mav.setViewName(view);
		return mav;
	}

	public List<Reply> replyInsert(Reply r) {
		List<Reply> rList = null;
		if (bDao.replyInsert(r)) {
			rList = bDao.getReplyList(r.getR_bnum());
		}
		return rList;
	}
	
	public Map<String, List<?>> replyInsert2(Reply r) {
		Map<String, List<?>> rMap = null;   //<?> <Object> 차이
		if (bDao.replyInsert(r)) {
			List<Reply> rList = bDao.getReplyList(r.getR_bnum());
			List<Member> mList = mDao.getMemberList();
			
			rMap = new HashMap<>();
			rMap.put("rList", rList);
			rMap.put("mList", mList);
			//rMap.put("msg", "OK~~~");  //에러
			//System.out.println("rMap=" + rMap);
		} else {
			rMap = null;
		}
		return rMap;
	}

//	public List<Reply> replyInsert3(Reply r) {
//		List<Reply> rList = null;
//		//if (bDao.replyInsert(r)) {
//		if(false) {
//			rList = bDao.getReplyList(r.getR_bnum());
//			if(rList==null) {
//				//@ControllerAdvice 타기 위해
//				throw new NoReplyException("댓글이 존재하지 않습니다.");
//			}
//		} else {
//			//@ControllerAdvice 타기 위해
//			throw new NoReplyException("댓글 쓰기 실패입니다.");
//		}
//		return rList;
//	}
	
	 
}
