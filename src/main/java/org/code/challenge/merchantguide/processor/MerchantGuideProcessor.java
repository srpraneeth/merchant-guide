package org.code.challenge.merchantguide.processor;

import java.util.Arrays;
import java.util.List;

import org.code.challenge.merchantguide.exception.InvalidDataException;
import org.code.challenge.merchantguide.reader.FileInputReader;
import org.code.challenge.merchantguide.reader.InputReader;
import org.code.challenge.merchantguide.utils.RomanNumberToIntegerConverter;
import org.code.challenge.merchantguide.writer.ConsoleOutputWriter;
import org.code.challenge.merchantguide.writer.OutputWriter;

/**
 * Processor Class having logic for processing the input lines
 * 
 * @author sr.praneeth@gmail.com
 *
 */
public class MerchantGuideProcessor {

	private InputReader inputReader;
	private OutputWriter outputWriter;
	private RomanSymbolProcessor romanSymbolProcessor;
	private MetalCreditsProcessor metalCreditsProcessor;
	private RomanNumberToIntegerConverter romanNumberToIntegerConverter;
	private QueryExpressionEvaluator queryExpressionEvaluator;

	public MerchantGuideProcessor(String filePath) {
		inputReader = new FileInputReader(filePath);
		outputWriter = new ConsoleOutputWriter();
		romanSymbolProcessor = new RomanSymbolProcessor();
		romanNumberToIntegerConverter = new RomanNumberToIntegerConverter();
		metalCreditsProcessor = new MetalCreditsProcessor(romanSymbolProcessor, romanNumberToIntegerConverter);
		queryExpressionEvaluator = new QueryExpressionEvaluator(romanSymbolProcessor, romanNumberToIntegerConverter,
				metalCreditsProcessor, outputWriter);
	}

	/**
	 * 1. Read the input 
	 * 2. Sanitize the Inputs (Triming) 
	 * 3. For each line from input split line into words 
	 * 4. Check the processors to match the type of input
	 * 5. Delegate the parsing to appropriate processor
	 * 6. Invalid input non matching to any processor, default output
	 * 
	 * InvalidDataException is thrown when an invalid RomanSymbol, Metal, GalaticSymbol
	 * For example 
	 * 		tegj is Z - Z is invalid Roman Symbol
	 * 		how many Credits is glob prok Copper ?	- Copper is invalid metal, since copper was not passed in credits parsing input
	 * 		how many Credits is glob dugg Gold ?	- dugg is invalid GalaticSymbol since dugg GalaticSymbol was not passed in parsing RomanSymbols
	 * 
	 * For these invalid inputs the program response as "I have no idea what you are talking about"
	 *  	 
	 */
	public void process() {
		final List<String> inputLines = inputReader.readLines();
		inputLines.stream().map(this::sanitize).forEach(this::processEachInput);
	}

	/**
	 * Sanitize the input data
	 * 
	 * @param line
	 * @return
	 */
	private String sanitize(String line) {
		return line.trim();
	}

	/**
	 * Process Each line input
	 * 		Checks if the line is one among RomanSymbolExpression, MetalCreditsExpression, QueryExpression 
	 * 				and delegate the processing to appropriate processor
	 * 		The default case where the input is none of the above the default error message is printed
	 * @param line
	 */
	private void processEachInput(String line) {
		try{
			final List<String> words = Arrays.asList(line.split(" "));
			if (romanSymbolProcessor.isRomanSymbolExpression(words)) {
				romanSymbolProcessor.parseRomanSymbols(words);
			} else if (metalCreditsProcessor.isMetalCreditsExpression(words)) {
				metalCreditsProcessor.parseMetalCredits(words);
			} else if (queryExpressionEvaluator.isQueryExpression(words)) {
				queryExpressionEvaluator.evaluateExpression(words);
			} else {
				outputDefaultMessage();
			}
		} catch(InvalidDataException e){
			outputDefaultMessage();
		}
	}

	/**
	 * Default error message Output
	 */
	private void outputDefaultMessage() {
		outputWriter.writeLine("I have no idea what you are talking about");
	}

}
