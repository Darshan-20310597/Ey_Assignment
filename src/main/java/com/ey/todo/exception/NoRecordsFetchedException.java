package com.ey.todo.exception;

public class NoRecordsFetchedException extends ExceptionHandler {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoRecordsFetchedException(String message, String path) {
        super("No records in the Database for the given Input value", message, path);
    }

    public NoRecordsFetchedException(String path, NumberFormatException cause) {
        super("No records in the Database for the given Input value", "No records in the Database for the given Input value", path, cause);
    } 

}
