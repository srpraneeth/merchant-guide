package org.code.challenge.merchantguide.processor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.code.challenge.merchantguide.constants.RomanSymbol;
import org.code.challenge.merchantguide.exception.InvalidDataException;

/**
 * Processor Class having logic for parsing, evaluating the Roman Symbols and GalaticSymbols
 * 
 * @author sr.praneeth@gmail.com
 *
 */
public class RomanSymbolProcessor {

	private Map<String, RomanSymbol> galaticRomanSymbolMap;

	public RomanSymbolProcessor() {
		galaticRomanSymbolMap = new HashMap<>();
	}

	/**
	 * Get RomanSymbol for a given Galatic Symbol, 
	 * 		Throws an InvalidDataException when galaticSymbol is not present
	 * @param galaticSymbol
	 * @return
	 */
	public RomanSymbol getRomanSymbolForGalaticSymbol(String galaticSymbol){
		return galaticRomanSymbolMap.containsKey(galaticSymbol) ? 
				galaticRomanSymbolMap.get(galaticSymbol) : 
					throwInvalidDataException("Invalid Galatic Value");
	}
	
	/**
	 * Helper Method to Throw InvalidDataException
	 * @param message
	 * @return
	 */
	private RomanSymbol throwInvalidDataException(String message) {
		throw new InvalidDataException(message);
	}
	
	private String throwInvalidDataExceptionForEmpty(String message) {
		throw new InvalidDataException(message);
	}

	/**
	 * Parse the given RomanSymbolExpression
	 * 
	 * @param words
	 */
	public void parseRomanSymbols(List<String> words) {
		final String romanSymbol = words.size() >= 3 ? words.get(2) : throwInvalidDataExceptionForEmpty("No Roman Value");
		galaticRomanSymbolMap.put(words.get(0), 
			isRomanSymbolMapContains(romanSymbol) ? RomanSymbol.valueOf(romanSymbol) : throwInvalidDataException("Invalid Roman Value")
		);
	}

	/**
	 * Check if String passed is valid RomanSymbol
	 * 
	 * @param romanSymbol
	 * @return
	 */
	private boolean isRomanSymbolMapContains(final String romanSymbol) {
		return Arrays.asList(RomanSymbol.values()).stream().map(RomanSymbol::toString).collect(Collectors.toList()).contains(romanSymbol);
	}
	
	/**
	 * Check if the Given Input is a Roman Symbol Expression
	 * 
	 * @param words
	 * @return
	 */
	public boolean isRomanSymbolExpression(List<String> words) {
		return words.size() == 3 && "is".equalsIgnoreCase(words.get(1));
	}

}
