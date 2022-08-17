package com.board.icia.exception;

public class DbException extends RuntimeException {
	public DbException() {
		super("스프링 RuntimeException(선택)예외만 인정합니다.");
	}
}
