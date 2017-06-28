package org.code.challenge.merchantguide.exception;

/**
 * Represent a exception situation where data is invalid
 * @see RuntimeException
 * @author sr.praneeth@gmail.com
 *
 */
public class InvalidDataException extends RuntimeException {

	private static final long serialVersionUID = 2144123413757938949L;

	/**
	 * 
	 * @param message
	 */
	public InvalidDataException(String message) {
		super(message);
	}

	/**
	 * 
	 * @param exception
	 */
	public InvalidDataException(Exception exception) {
		super(exception);
	}

}
