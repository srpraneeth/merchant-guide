package org.code.challenge.merchantguide.processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.code.challenge.merchantguide.constants.RomanSymbol;
import org.code.challenge.merchantguide.exception.InvalidDataException;
import org.code.challenge.merchantguide.utils.RomanNumberToIntegerConverter;

/**
 * Processor Class having logic for parsing, evaluating the Metals and their Credits
 * 
 * @author sr.praneeth@gmail.com
 *
 */
public class MetalCreditsProcessor {
	
	private Map<String, Double> metalCreditsMap;
	private RomanSymbolProcessor romanSymbolProcessor;
	private RomanNumberToIntegerConverter romanNumberToIntegerConverter;
	
	public MetalCreditsProcessor(RomanSymbolProcessor romanSymbolProcessor, RomanNumberToIntegerConverter romanNumberToIntegerConverter) {
		metalCreditsMap = new HashMap<>();
		this.romanSymbolProcessor = romanSymbolProcessor;
		this.romanNumberToIntegerConverter = romanNumberToIntegerConverter;
	}

	/**
	 * Parse the Metal and the credits in the MetalCreditsExpression input and create a map entry for Credits per Unit Metal
	 * 
	 * @param words
	 */
	public void parseMetalCredits(List<String> words) {
		final String metalName = words.get(words.size() - 4);
		final String metalsCountInRomanNumber = getRomanNumberForGalaticSymbols(words);
		final Integer metalsCount = romanNumberToIntegerConverter.convertRomanToInteger(metalsCountInRomanNumber);
		final Integer creditsForMetals = getCreditsForMetalFromExpression(words);
		final Double creditsPerUnitMetal = creditsForMetals.doubleValue() / metalsCount;
		metalCreditsMap.put(metalName, creditsPerUnitMetal);
	}

	/**
	 * Get the RomanSymbol for a particular GalaticSymbol
	 * Throws InvalidDataException when invalid GalaticSymbol is passed
	 * 
	 * @see InvalidDataException
	 * 
	 * @param words
	 * @return
	 */
	private String getRomanNumberForGalaticSymbols(List<String> words) {
		return words.stream().limit(words.size() - 4)
				.map(romanSymbolProcessor::getRomanSymbolForGalaticSymbol).map(RomanSymbol::name)
				.collect(Collectors.joining());
	}

	/**
	 * Get the Credits for the Metal in the MetalCreditsExpression
	 * 		Throws InvalidDataException when invalid Credits Number is passed 
	 *  
	 * @param words
	 * @return
	 */
	private Integer getCreditsForMetalFromExpression(List<String> words) {
		final String creditForMetalsString = words.get(words.size() - 2);
		final Integer creditForMetals = isNumeric(creditForMetalsString) ? Integer.valueOf(creditForMetalsString) : throwInvalidDataExceptionForCredits("Invalid Credit Number");
		return creditForMetals;
	}

	/**
	 * Get the Per Unit Credit for a given Metal
	 * 		Throws InvalidDataException when invalid Metal is passed 
	 * 
	 * @param metal
	 * @return
	 */
	public Double getMetalCreditsForMetal(String metal){
		return metalCreditsMap.containsKey(metal) ? 
				metalCreditsMap.get(metal) : 
					throwInvalidDataExceptionForMetals("Invalid Metal Requested for Evaluation");
	}
	
	/**
	 * Helper method to throw InvalidDataException 
	 * 
	 * @param message
	 * @return
	 */
	private Integer throwInvalidDataExceptionForCredits(String message) {
		throw new InvalidDataException(message);
	}
	
	/**
	 * Helper method to throw InvalidDataException
	 * 
	 * @param message
	 * @return
	 */
	private Double throwInvalidDataExceptionForMetals(String message) {
		throw new InvalidDataException(message);
	}

	/**
	 * Checks whether the line input is a MetalCreditsExpression
	 * 
	 * @param words
	 * @return
	 */
	public Boolean isMetalCreditsExpression(List<String> words) {
		return words.size() >= 5 && "is".equalsIgnoreCase(words.get(words.size() - 3)) && "credits".equalsIgnoreCase(words.get(words.size() - 1)) && isNumeric(words.get(words.size() - 2));
	}
	
	/**
	 * Helper method to check whether given String is numeric
	 * 
	 * @param number
	 * @return
	 */
	private static Boolean isNumeric(String number) {
	    for (char c : number.toCharArray()) {
	        if (!Character.isDigit(c)) return false;
	    }
	    return true;
	}

}
