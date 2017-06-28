package org.code.challenge.merchantguide.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Numbers are formed by combining symbols together and adding the values. For
 * example, MMVI is 1000 + 1000 + 5 + 1 = 2006. Generally, symbols are placed in
 * order of value, starting with the largest values. When smaller values precede
 * larger values, the smaller values are subtracted from the larger values, and
 * the result is added to the total. For example MCMXLIV = 1000 + (1000 − 100) +
 * (50 − 10) + (5 − 1) = 1944.
 * 
 * The symbols "I", "X", "C", and "M" can be repeated three times in succession,
 * but no more. (They may appear four times if the third and fourth are
 * separated by a smaller value, such as XXXIX.) "D", "L", and "V" can never be
 * repeated.
 * 
 * "I" can be subtracted from "V" and "X" only. "X" can be subtracted from "L"
 * and "C" only. "C" can be subtracted from "D" and "M" only. "V", "L", and "D"
 * can never be subtracted.
 * 
 * Only one small-value symbol may be subtracted from any large-value symbol.
 * 
 * A number written in (16) Arabic numerals can be broken into digits. For
 * example, 1903 is composed of 1, 9, 0, and 3. To write the Roman numeral, each
 * of the non-zero digits should be treated separately. Inthe above example,
 * 1,000 = M, 900 = CM, and 3 = III. Therefore, 1903 = MCMIII.
 * 
 * @author sr.praneeth@gmail.com
 *
 */
public class RomanNumberRuleValidatorTest {

	private RomanNumberRuleValidator validator;

	@Before
	public void setUp() {
		validator = new RomanNumberRuleValidator();
	}

	/*
	 * @Test public void testValidateIXCMRule3TimesSuccessionRule(){
	 * assertFalse(validator.validateIXCMRule3TimesSuccession("MMMM"));
	 * assertFalse(validator.validateIXCMRule3TimesSuccession("CCCCC"));
	 * assertFalse(validator.validateIXCMRule3TimesSuccession("XXXX"));
	 * assertFalse(validator.validateIXCMRule3TimesSuccession("IIII")); }
	 * 
	 * @Test public void testValidateIXCMRule3TimesSuccessionRule_(){
	 * assertTrue(validator.validateIXCMRule3TimesSuccession("MMM"));
	 * assertTrue(validator.validateIXCMRule3TimesSuccession("CCC"));
	 * assertTrue(validator.validateIXCMRule3TimesSuccession("XXX"));
	 * assertTrue(validator.validateIXCMRule3TimesSuccession("III")); }
	 * 
	 * @Test public void testValidateDLVNotRepeatable(){
	 * assertFalse(validator.validateDLVNeverRepeated("DD"));
	 * assertFalse(validator.validateDLVNeverRepeated("LL"));
	 * assertFalse(validator.validateDLVNeverRepeated("VV")); }
	 * 
	 * @Test public void testValidateIOnlyBeforeVX(){
	 * assertFalse(validator.validateISubtractedFromVXOnly("IL"));
	 * assertFalse(validator.validateISubtractedFromVXOnly("IC"));
	 * assertFalse(validator.validateISubtractedFromVXOnly("IM"));
	 * assertFalse(validator.validateISubtractedFromVXOnly("ID")); }
	 * 
	 * @Test public void testValidateXOnlyBeforeLC(){
	 * assertFalse(validator.validateXSubtractedFromLCOnly("XD"));
	 * assertFalse(validator.validateXSubtractedFromLCOnly("XM")); }
	 */

	@Test
	public void testValidateRule_EmptyInputs() {
		assertFalse(validator.validate(null));
		assertFalse(validator.validate(""));
	}
	
	@Test
	public void testValidateIXCMRule3TimesSuccessionRule() {
		assertFalse(validator.validate("MMMM"));
		assertFalse(validator.validate("CCCCC"));
		assertFalse(validator.validate("XXXX"));
		assertFalse(validator.validate("IIII"));
	}

	@Test
	public void testValidateIXCMRule3TimesSuccessionRule_() {
		assertTrue(validator.validate("MMM"));
		assertTrue(validator.validate("CCC"));
		assertTrue(validator.validate("XXX"));
		assertTrue(validator.validate("III"));
	}

	@Test
	public void testValidateDLVNotRepeatable() {
		assertFalse(validator.validate("DD"));
		assertFalse(validator.validate("LL"));
		assertFalse(validator.validate("VV"));
	}

	@Test
	public void testValidateIOnlyBeforeVX() {
		assertFalse(validator.validate("IL"));
		assertFalse(validator.validate("IC"));
		assertFalse(validator.validate("IM"));
		assertFalse(validator.validate("ID"));
	}

	@Test
	public void testValidateXOnlyBeforeLC() {
		assertFalse(validator.validate("XD"));
		assertFalse(validator.validate("XM"));
	}

	@Test
	public void testValidateVLDCanNeverBeSubstracted() {
		assertFalse(validator.validate("VX"));
		assertFalse(validator.validate("LC"));
		assertFalse(validator.validate("DM"));
	}

	@Test
	public void testValidateOnlyOneSmallValCanBeSubstracted() {
		assertFalse(validator.validate("IIV"));
		assertFalse(validator.validate("XXL"));
		assertFalse(validator.validate("XXC"));
		assertFalse(validator.validate("CCM"));
	}

}
