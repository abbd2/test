package com.board.icia.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.board.icia.bean.Member;
import com.board.icia.service.BoardMM;

import lombok.NonNull;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class BoardController {

	@Autowired
	private BoardMM bm;

	ModelAndView mav;

	@GetMapping(value = "/boardList")
	//public ModelAndView boardList(@RequestParam String m_id, @RequestParam String m_name
	//		                     ,@RequestParam String m_point,	@RequestParam String m_grade) {
	public ModelAndView boardList(Member mb, @Nullable Integer pageNum,HttpSession session) {
		//System.out.println("mb="+mb);		
		mav=new ModelAndView();
		mav = bm.getBoardList(pageNum, session); //null or 페이지번호(1,2,3...)
		//파라미터(Member mb)에 인자가 전달시에 저장 
		if(mb.getM_id()!=null) {
		 	mav.addObject("mb",mb);
		} 	
//		mav.addObject("m_id", mb.getM_id());
//		mav.addObject("m_name", mb.getM_name());
//		mav.addObject("m_point",mb.getM_point());
//		mav.addObject("m_grade", mb.getM_grade());
		mav.setViewName("boardList");
		return mav;
	}
	@GetMapping(value = "/contents")
	public ModelAndView getContents(@NonNull Integer bNum) {  //lombok.NonNull사용할것.
		mav=bm.getContents(bNum);
		return mav;  
	}
}
