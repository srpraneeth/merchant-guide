package org.code.challenge.merchantguide.reader;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.code.challenge.merchantguide.exception.InvalidInputException;

/**
 * InputReader implementation for reading the input from files
 * @see InputReader
 * {@link InputReader}
 * {@inheritDoc}
 * 
 * @author sr.praneeth@gmail.com
 *
 */
public class FileInputReader implements InputReader {

	private String file = null;

	public FileInputReader(String filePath) {
		this.file = filePath;
	}

	/**
	 * Read the input file and return it as a list of lines
	 * @throws InvalidInputException for invalid file
	 * 
	 */
	@Override
	public List<String> readLines() {
		try (Stream<String> lines = Files.lines(Paths.get(file), Charset.defaultCharset())) {
			return lines.collect(Collectors.toList());
		} catch (IOException e) {
			throw new InvalidInputException(e);
		}
	}

}
