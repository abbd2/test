package com.board.icia.exception;
//공용 예외클래스                 //throws, try~catch생략가능                         

public class CommonException extends RuntimeException{
	
	public CommonException(String msg) {
		super(msg);  //getMassage();
	}
}