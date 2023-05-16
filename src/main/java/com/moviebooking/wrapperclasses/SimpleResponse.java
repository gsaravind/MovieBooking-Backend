package com.moviebooking.wrapperclasses;

public class SimpleResponse {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public SimpleResponse(String message) {
		super();
		this.message = message;
	}

	public SimpleResponse() {
	}

	@Override
	public String toString() {
		return "SimpleResponse [message=" + message + "]";
	}
}
