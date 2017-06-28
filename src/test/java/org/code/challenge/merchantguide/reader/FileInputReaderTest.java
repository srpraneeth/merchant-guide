package org.code.challenge.merchantguide.reader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.stream.IntStream;

import org.code.challenge.merchantguide.exception.InvalidInputException;
import org.junit.Before;
import org.junit.Test;

public class FileInputReaderTest {

	private InputReader inputReader;

	@Before
	public void setUp() {
		inputReader = new FileInputReader("src/test/resources/reader/input1.txt");
	}

	@Test
	public void testReadLines_ValidFile() {
		final List<String> lines = inputReader.readLines();
		IntStream.range(1, 3).forEach(i -> assertEquals("Line " + i + " in the File", lines.get(i - 1)));
	}

	@Test(expected = InvalidInputException.class)
	public void testReadLines_InvalidFile() {
		inputReader = new FileInputReader("src/test/resources/reader/invalid.txt");
		inputReader.readLines();
	}

	@Test
	public void testReadLines_EmptyInputFile() {
		inputReader = new FileInputReader("src/test/resources/reader/input2.txt");
		List<String> lines = inputReader.readLines();
		assertTrue(lines.isEmpty());
	}

}
