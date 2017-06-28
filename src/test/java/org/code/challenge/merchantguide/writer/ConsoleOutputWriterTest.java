package org.code.challenge.merchantguide.writer;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.code.challenge.merchantguide.writer.ConsoleOutputWriter;
import org.code.challenge.merchantguide.writer.OutputWriter;
import org.junit.Before;
import org.junit.Test;

public class ConsoleOutputWriterTest {

	private OutputWriter outputWriter;

	private ByteArrayOutputStream outContent;

	@Before
	public void setUp() {
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		outputWriter = new ConsoleOutputWriter();
	}

	@SuppressWarnings("restriction")
	@Test
	public void testWriteLine_ValidLine() {
		outputWriter.writeLine("This is valid line");
		assertEquals("This is valid line" + java.security.AccessController.doPrivileged(new sun.security.action.GetPropertyAction("line.separator")), outContent.toString());
	}

}
