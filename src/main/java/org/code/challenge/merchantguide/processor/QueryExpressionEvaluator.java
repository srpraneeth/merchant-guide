package org.code.challenge.merchantguide.processor;

import java.util.List;
import java.util.stream.Collectors;

import org.code.challenge.merchantguide.constants.RomanSymbol;
import org.code.challenge.merchantguide.utils.RomanNumberToIntegerConverter;
import org.code.challenge.merchantguide.writer.OutputWriter;

/**
 * Processor Class having logic for parsing, evaluating the Metals and their Credits
 * 
 * @author sr.praneeth@gmail.com
 *
 */
public class QueryExpressionEvaluator {

	private RomanSymbolProcessor romanSymbolProcessor;
	private RomanNumberToIntegerConverter romanNumberToIntegerConverter;
	private MetalCreditsProcessor metalCreditsProcessor;
	private OutputWriter outputWriter;

	public QueryExpressionEvaluator(RomanSymbolProcessor romanSymbolProcessor,
			RomanNumberToIntegerConverter romanNumberToIntegerConverter, MetalCreditsProcessor metalCreditsProcessor, OutputWriter outputWriter) {
		this.romanSymbolProcessor = romanSymbolProcessor;
		this.romanNumberToIntegerConverter = romanNumberToIntegerConverter;
		this.metalCreditsProcessor = metalCreditsProcessor;
		this.outputWriter = outputWriter;
	}

	/**
	 * Evaluates the QueryExpression from the Input line
	 * 
	 * @param words
	 */
	public void evaluateExpression(List<String> words) {
		try {
			if (isGalaticNumberQuery(words)) {
				evaluateGalaticNumberQuery(words);
			} else if (isMetalCreditsQuery(words)) {
				evaluateMetalCreditsQuery(words);
			} else {
				outputInvalidInputMessage();
			}
		} catch (IllegalArgumentException e) {
			outputInvalidInputMessage();
		}
	}
	
	/**
	 * Check Whether the given input line is a GalaticNumberQuery
	 * 
	 * @param words
	 * @return
	 */
	private boolean isGalaticNumberQuery(List<String> words) {
		return "is".equalsIgnoreCase(words.get(2));
	}
	
	/**
	 * Check Whether the given input line is a MetalCreditsQuery
	 * 
	 * @param words
	 * @return
	 */
	private boolean isMetalCreditsQuery(List<String> words) {
		return "Credits".equalsIgnoreCase(words.get(2)) && "is".equalsIgnoreCase(words.get(3));
	}

	/**
	 * Default error message Output
	 * 
	 */
	private void outputInvalidInputMessage() {
		outputWriter.writeLine("I have no idea what you are talking about");
	}

	/**
	 * Evaluates the given GalaticNumberQuery
	 * 
	 * @param words
	 */
	private void evaluateGalaticNumberQuery(List<String> words) {
		outputWriter.writeLine(
				getGalaticNumberFromGalaticNumberQuery(words) + " is "
				+ getNumberFromGalaticNumber(words));
	}
	
	/**
	 * Gets the Galatic Numbers From the Given GalaticNumberQuery
	 * 
	 * @param words
	 * @return
	 */
	private String getGalaticNumberFromGalaticNumberQuery(List<String> words) {
		return words.stream().skip(3).limit(words.size() - 4).collect(Collectors.joining(" "));
	}
	
	/**
	 * Get the Integer Number from the Galatic Numbers passed
	 * 
	 * @param words
	 * @return
	 */
	private Integer getNumberFromGalaticNumber(List<String> words) {
		return romanNumberToIntegerConverter.convertRomanToInteger(
				words.stream().skip(3).limit(words.size() - 4)
					.map(romanSymbolProcessor::getRomanSymbolForGalaticSymbol)
					.map(RomanSymbol::name)
					.collect(Collectors.joining())
				);
	}
	
	/**
	 * Evaluates the MetalCreditsExpression
	 * 
	 * @param words
	 */
	private void evaluateMetalCreditsQuery(List<String> words) {
		outputWriter.writeLine(
				getGalaticNumberFromMetalCreditQuery(words) + " " + getMetalFromMetalCreditQuery(words) +
				" is " + getMetalCredits(words) + " Credits"
		);
	}
	
	/**
	 * Get the GalaticNumber from the MetalCreditsExpression
	 * 
	 * @param words
	 * @return
	 */
	private String getGalaticNumberFromMetalCreditQuery(List<String> words) {
		return words.stream().skip(4).limit(words.size() - 6).collect(Collectors.joining(" "));
	}
	
	/**
	 * Get the Metal from the MetalCreditsQuery
	 * 
	 * @param words
	 * @return
	 */
	private String getMetalFromMetalCreditQuery(List<String> words) {
		return words.get(words.size() - 2);
	}

	/**
	 * Get the Credits for the Metal from the MetalCreditsQuery
	 * 
	 * @param words
	 * @return
	 */
	private Integer getMetalCredits(List<String> words) {
		final String metal = getMetalFromMetalCreditQuery(words);
		final Double creditsPerMetalUnit = metalCreditsProcessor.getMetalCreditsForMetal(metal);
		final Integer noOfMetals = romanNumberToIntegerConverter.convertRomanToInteger(getRomanNumberFromMetalCreditsQuery(words));
		return (int) (creditsPerMetalUnit * noOfMetals);
	}
	
	/**
	 * Get the RomanNumber of Metals count from MetalCreditsQuery
	 * 
	 * @param words
	 * @return
	 */
	private String getRomanNumberFromMetalCreditsQuery(List<String> words) {
		return words.stream().skip(4).limit(words.size() - 6)
				.map(eachGalacticWord -> romanSymbolProcessor.getRomanSymbolForGalaticSymbol(eachGalacticWord).name())
				.collect(Collectors.joining());
	}
	
	/**
	 * Checks whether the input line is a QueryExpression
	 * 
	 * @param words
	 * @return
	 */
	public boolean isQueryExpression(List<String> words) {
		return words.size() >= 5 && "how".equalsIgnoreCase(words.get(0)) && "?".equals(words.get(words.size() - 1));
	}

}
