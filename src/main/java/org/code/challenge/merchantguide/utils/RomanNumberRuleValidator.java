package org.code.challenge.merchantguide.utils;

/**
 * Utility to Validate Input string to be a valid RomanNumber
 * 
 * @author sr.praneeth@gmail.com
 *
 */
public class RomanNumberRuleValidator {
	
	public final static String REG_EXP = "^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";

	/**
	 * Validates the input accross Roman number rules
	 * 
	 * @see RomanNumberRuleValidatorTest for Rules
	 * @param input
	 * @return
	 */
	public Boolean validate(String input) {
		return input != null && !input.isEmpty() ? input.matches(REG_EXP) : false;
	}

}
