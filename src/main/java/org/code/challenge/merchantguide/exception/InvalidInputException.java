package org.code.challenge.merchantguide.exception;

/**
 * Represent a exception situation where input is invalid
 * @see RuntimeException
 * @author sr.praneeth@gmail.com
 *
 */
public class InvalidInputException extends RuntimeException {

	private static final long serialVersionUID = 2144123413757938949L;

	/**
	 * 
	 * @param message
	 */
	public InvalidInputException(String message) {
		super(message);
	}

	/**
	 * 
	 * @param exception
	 */
	public InvalidInputException(Exception exception) {
		super(exception);
	}

}
