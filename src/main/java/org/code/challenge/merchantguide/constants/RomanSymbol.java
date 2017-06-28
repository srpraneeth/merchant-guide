package org.code.challenge.merchantguide.constants;

/**
 * Enum to represent Roman Symbols
 * 
 * @author sr.praneeth@gmail.com
 *
 */
public enum RomanSymbol {

	I(1), V(5), X(10), L(50), C(100), D(500), M(1000);

	private Integer value;

	private RomanSymbol(Integer value) {
		this.value = value;
	}
	
	/**
	 * Get the Value
	 * @return
	 */
	public Integer getValue() {
		return value;
	}

	/**
	 * Get RomanNumber From String
	 * @param charToConvert
	 * @return
	 */
	public static RomanSymbol valueOf(Character charToConvert) {
		return RomanSymbol.valueOf(charToConvert.toString());
	}

}
