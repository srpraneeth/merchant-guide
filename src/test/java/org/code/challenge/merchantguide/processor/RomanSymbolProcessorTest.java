package org.code.challenge.merchantguide.processor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.code.challenge.merchantguide.constants.RomanSymbol;
import org.code.challenge.merchantguide.exception.InvalidDataException;
import org.junit.Before;
import org.junit.Test;

public class RomanSymbolProcessorTest {

	private RomanSymbolProcessor romanSymbolProcessor;

	@Before
	public void setUp() {
		romanSymbolProcessor = new RomanSymbolProcessor();
	}

	@Test
	public void testParseRomanSymbols_ValidInput() {
		List<String> words = Arrays.asList("glob is I".split(" "));
		romanSymbolProcessor.parseRomanSymbols(words);
		assertEquals(RomanSymbol.I, romanSymbolProcessor.getRomanSymbolForGalaticSymbol("glob"));
	}

	@Test(expected = InvalidDataException.class)
	public void testParseRomanSymbols_InValidRomanSymbol() {
		List<String> words = Arrays.asList("glob is B".split(" "));
		romanSymbolProcessor.parseRomanSymbols(words);
	}

	@Test(expected = InvalidDataException.class)
	public void testParseRomanSymbols_EmptyRomanSymbol() {
		List<String> words = Arrays.asList("glob is".split(" "));
		romanSymbolProcessor.parseRomanSymbols(words);
	}

	@Test
	public void testIsRomanSymbolExpression_ValidInput() {
		List<String> words = Arrays.asList("glob is I".split(" "));
		assertTrue(romanSymbolProcessor.isRomanSymbolExpression(words));
	}

	@Test
	public void testIsRomanSymbolExpression_InvalidInput() {
		List<String> words = Arrays.asList("AAA BB CCCC".split(" "));
		assertFalse(romanSymbolProcessor.isRomanSymbolExpression(words));
	}

	@Test
	public void testIsRomanSymbolExpression_ValidInputInvalidRomanSymbol() {
		List<String> words = Arrays.asList("AAA is CCCC".split(" "));
		assertTrue(romanSymbolProcessor.isRomanSymbolExpression(words));
	}

	@Test
	public void testIsRomanSymbolExpression_InCompleteInput() {
		List<String> words = Arrays.asList("glob is".split(" "));
		assertFalse(romanSymbolProcessor.isRomanSymbolExpression(words));
	}

	@Test
	public void testIsRomanSymbolExpression_ValidInputINWrongOrder() {
		List<String> words = Arrays.asList("is glob I".split(" "));
		assertFalse(romanSymbolProcessor.isRomanSymbolExpression(words));
	}

	@Test
	public void testgetRomanSymbolForGalaticSymbol_ValidGalaticInput() {
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("glob is I".split(" ")));
		assertEquals(RomanSymbol.I, romanSymbolProcessor.getRomanSymbolForGalaticSymbol("glob"));
	}

	@Test(expected = InvalidDataException.class)
	public void testgetRomanSymbolForGalaticSymbol_InValidGalaticInput() {
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("glob is I".split(" ")));
		romanSymbolProcessor.getRomanSymbolForGalaticSymbol("BBB");
	}

	@Test(expected = InvalidDataException.class)
	public void testgetRomanSymbolForGalaticSymbol_GalaticInputNotExisting() {
		romanSymbolProcessor.getRomanSymbolForGalaticSymbol("glob");
	}

}
