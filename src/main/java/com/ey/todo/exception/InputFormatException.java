package com.ey.todo.exception;

public class InputFormatException extends ExceptionHandler {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InputFormatException(String message, String path) {
        super("Please enter a valid Input", message, path);
    }

    public InputFormatException(String path, NumberFormatException cause) {
        super("Please enter a valid Input", "Please check the entered Input", path, cause);
    }

}
