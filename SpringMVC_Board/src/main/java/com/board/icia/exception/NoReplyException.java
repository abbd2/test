package com.board.icia.exception;

public class NoReplyException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public NoReplyException(String msg) {
		super(msg);  //getMassage();
	}
}
