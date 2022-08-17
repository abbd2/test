package com.board.icia.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.icia.bean.Member;
import com.board.icia.service.MemberMM;

import lombok.extern.log4j.Log4j;


@Log4j
@Controller
public class MemberController {
	
	//private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberMM mm;
	
	ModelAndView mav;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		//logger.info("Welcome home! The client locale is {}.", locale);
		//@Log4j Test
//		ModelAndView mav=new ModelAndView();
//		mav.addObject("msg","MVC_Board");
//		mav.setViewName("home");
//		log.info("mav="+mav);
//		log.info(mav);
		return "home";
	}
	@GetMapping(value = "/joinFrm")
	public String joinFrm() {
		log.info("회원가입창으로 이동");
		return "joinFrm";  
	}
	@PostMapping(value = "/memberJoin")
	public ModelAndView memberJoin(@ModelAttribute Member mb) {
//		log.info("id="+mb.getM_id());
//		log.info("name="+mb.getM_name());
		mav=mm.memberJoin(mb);
		return mav;  
	}
	@PostMapping(value = "/access")
	public ModelAndView access(Member mb, HttpSession session, RedirectAttributes attr) {
		ModelAndView mav=mm.access(mb, session, attr);
		return mav;
	}
	@PostMapping(value="/logout")
	public String logout(HttpSession session) {
		session.invalidate(); //세션 무효화
		return "redirect:/";  //home.jsp이동
	}
	
	
}
