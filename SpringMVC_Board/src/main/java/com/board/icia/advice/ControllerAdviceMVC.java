package com.board.icia.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.icia.exception.CommonException;

import lombok.extern.log4j.Log4j;

@ControllerAdvice
@Log4j
public class ControllerAdviceMVC {
	// boardlist?pageNum=a 라고 입력시 예외를 처리한다. 자바 정의 NumberFormatException 이 예외처리함.
	// boardlist?pageNum=-1 라고 입력시 예외를 처리한다. 프로그래머 정의 CommonException 이 예외처리함
	@ExceptionHandler(NumberFormatException.class)   //모든 예외를 충고(처리)하고 싶다면 Exception으로 할것
	public String except(NumberFormatException ex, RedirectAttributes attr) {
		log.error("NumberFormatException...." + ex.getMessage());
		attr.addFlashAttribute("exMsg",ex.getMessage()); //ss영역-->1번 사용후--ss영역삭제
		return "redirect:/"; //로그인 페이지(home.jsp)
	}
	
//에러페이지로 이동	
//	@ExceptionHandler(Exception.class)
//	public String except(Exception ex, Model model) {
//		log.error("Exception...." + ex.getMessage());
//		model.addAttribute("exception", ex);
//		log.error(model);
//		return "error/error_pageNum";
//	}

	// boardlist?pageNum=a 라고 입력시 예외를 처리한다. 
	//스프링의 DefaultHandlerExceptionResolver가
	// 받아서 자바 정의 NumberFormatException 이 예외처리함.
	// boardlist?pageNum=-1 라고 입력시 예외를 처리한다. 프로그래머 정의 CommonException 이 예외처리함
		@ExceptionHandler(CommonException.class)
		public String except(CommonException ex, RedirectAttributes attr) {
			attr.addFlashAttribute("exMsg",ex.getMessage());
			return "redirect:/"; //로그인 페이지(home.jsp)
		}

}
