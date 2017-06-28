package org.code.challenge.merchantguide.writer;

import java.io.PrintStream;

/**
 * OutputWriter implementation for reading the input from files
 * @see OutputWriter
 * {@link OutputWriter}
 * {@inheritDoc}
 * 
 * @author sr.praneeth@gmail.com
 *
 */
public class ConsoleOutputWriter implements OutputWriter {

	private PrintStream out;

	public ConsoleOutputWriter() {
		out = System.out;
	}

	@Override
	public void writeLine(String line) {
		out.println(line);
	}

}
