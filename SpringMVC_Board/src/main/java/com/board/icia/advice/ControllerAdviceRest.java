package com.board.icia.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.board.icia.exception.IdCheckException;
import com.board.icia.exception.NoReplyException;

@RestControllerAdvice
public class ControllerAdviceRest {
	// 한글 깨짐 방지
	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/plain;charset=utf-8");
		return headers;
	}

	// 아이디 중복 체크 예외
	// ex.getMessage()가 한글일때 getHeaders() 호출할것.
	@ExceptionHandler(IdCheckException.class)
	public ResponseEntity<String> idDupExceptionHandler(IdCheckException ex) {
		System.out.println("idCheckException Advice");
		return new ResponseEntity<String>(ex.getMessage(), getHeaders(), HttpStatus.EXPECTATION_FAILED); // 417 error
	}
	// 댓글 리스트 예외
	@ExceptionHandler(NoReplyException.class)
	public ResponseEntity<?> replyExceptionHandler(NoReplyException ex) {
		System.out.println("NoReplyException Advice");
		return ResponseEntity.status(HttpStatus.CONFLICT).headers(getHeaders()).body(ex.getMessage()); // 409 error
	}

}
