package com.board.icia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.board.icia.exception.IdCheckException;
import com.board.icia.service.MemberMM;


@Controller // @ResponseBody 생략가능
@RequestMapping(value = "/member")
public class MemberRestController {
	@Autowired
	private MemberMM mm;
	
	//"application/json;charset=utf-8"
	//"text/plain;charset=utf-8"
	                              //리턴값의 형식지정과 한글깨짐 방지
	@GetMapping(value="/userid", produces="text/plain;charset=utf-8")  
	public ResponseEntity<String> idAvailable(String m_id){
//		ResponseEntity<?> result=null;
//		if(mm.idAvailable(m_id)) {
//			result=ResponseEntity.ok().body("사용가능한 아이디");
//		}else { //서버와 통신이 성공이라도 필요한 것만 success로 콜백할 수 있다.
//			result=ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("사용불가 아이디");
//		}
//		return result;
		//@RestControllerAdvice 를 이용해서 예외처리
		return ResponseEntity.ok(mm.idAvailable(m_id));
	}
}
