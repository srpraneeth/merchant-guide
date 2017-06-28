package org.code.challenge.merchantguide.utils;

import org.code.challenge.merchantguide.exception.InvalidDataException;

/**
 * Utility to convert the RomanNumber given in String format for example MCMXLIV to Integer format 1944
 * 		Throws InvalidDataException when Invalid Roman Number is passed
 * 
 * @author sr.praneeth@gmail.com
 * 
 */
public class RomanNumberToIntegerConverter {
	
	private RomanNumberRuleValidator romanNumberRuleValidator;
	
	public RomanNumberToIntegerConverter() {
		romanNumberRuleValidator = new RomanNumberRuleValidator();
	}
	
	/**
	 * Converts the Roman Number to Integer
	 * @param romanNumber
	 * @return
	 * @throws InvalidDataException
	 */
	public Integer convertRomanToInteger(String romanNumber) {
		return isValidRomanNumber(romanNumber) ? 
				convertRomanToIntegerInternal(romanNumber) : 
					throwInvalidDataException("RomanNumber " + romanNumber + " is empty/invalid"); 
	}

	/**
	 * Helper method to throw InvalidDataException
	 *  
	 * @param message
	 * @return
	 */
	private Integer throwInvalidDataException(String message) {
		 throw new InvalidDataException(message);
	}

	/**
	 * Checks if the given String is a valid RomanNumber
	 * 
	 * @param romanNumber
	 * @return
	 */
	private Boolean isValidRomanNumber(String romanNumber) {
		return romanNumberRuleValidator.validate(romanNumber);
	}

	/**
	 * Converts the RomanNumber String into Integer Number
	 * 
	 * @param romanNumber
	 * @return
	 * @throws InvalidDataException
	 */
	private Integer convertRomanToIntegerInternal(String romanNumber) {
		Integer intValue = 0;
		final String romanNumeralCleaned = romanNumber.trim().toUpperCase();
		Integer previousNumber = 0;
		/*
		 *  Reverse traverse the string chars and for each char calculate its base value
		 */
		for (Integer x = romanNumeralCleaned.length() - 1; x >= 0; x--) {
			char charToConvert = romanNumeralCleaned.charAt(x);
			
			switch (charToConvert) {
				case 'M':
	                intValue = compareWithLastNumber(1000, previousNumber, intValue);
	                previousNumber = 1000;
	                break;
	
	            case 'D':
	                intValue = compareWithLastNumber(500, previousNumber, intValue);
	                previousNumber = 500;
	                break;
	
	            case 'C':
	                intValue = compareWithLastNumber(100, previousNumber, intValue);
	                previousNumber = 100;
	                break;
	
	            case 'L':
	                intValue = compareWithLastNumber(50, previousNumber, intValue);
	                previousNumber = 50;
	                break;
	
	            case 'X':
	                intValue = compareWithLastNumber(10, previousNumber, intValue);
	                previousNumber = 10;
	                break;
	
	            case 'V':
	                intValue = compareWithLastNumber(5, previousNumber, intValue);
	                previousNumber = 5;
	                break;
	
	            case 'I':
	                intValue = compareWithLastNumber(1, previousNumber, intValue);
	                previousNumber = 1;
	                break;
	                
	            default:
	            	throw new InvalidDataException("Invalid Roman Number char " + charToConvert + " in " + romanNumber);
			}
		}
		return intValue;
	}

	/**
	 * Calculate Base values with Last Number in the Traverse and appropriately calculate the base value
	 * 
	 * @param decimal
	 * @param lastNumber
	 * @param lastDecimal
	 * @return
	 */
	public int compareWithLastNumber(Integer decimal, Integer lastNumber, Integer lastDecimal) {
		if (lastNumber > decimal) {
			return lastDecimal - decimal;
		} else {
			return lastDecimal + decimal;
		}
	}

}
