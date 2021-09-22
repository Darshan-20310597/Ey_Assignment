package com.ey.todo.entity;

import lombok.Getter;

@Getter
public class ErrorResponse {
	
	private String status;
	private String error;
	private String path;
	
	public ErrorResponse(String status, String error, String path) {
		this.status = status;
		this.error = error;
		this.path = path;
	}

}
