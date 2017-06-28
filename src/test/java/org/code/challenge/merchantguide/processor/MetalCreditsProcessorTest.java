package org.code.challenge.merchantguide.processor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.code.challenge.merchantguide.exception.InvalidDataException;
import org.code.challenge.merchantguide.utils.RomanNumberToIntegerConverter;
import org.junit.Before;
import org.junit.Test;

public class MetalCreditsProcessorTest {

	private MetalCreditsProcessor metalCreditsProcessor;
	private RomanSymbolProcessor romanSymbolProcessor;

	@Before
	public void setUp() {
		romanSymbolProcessor = new RomanSymbolProcessor();
		metalCreditsProcessor = new MetalCreditsProcessor(romanSymbolProcessor, new RomanNumberToIntegerConverter());
	}

	@Test
	public void testIsMetalCreditsExpression_ValidInput() {
		assertTrue(metalCreditsProcessor
				.isMetalCreditsExpression(Arrays.asList("glob glob Silver is 34 Credits".split(" "))));
	}

	@Test
	public void testIsMetalCreditsExpression_InValidCreditsNumber() {
		assertFalse(metalCreditsProcessor
				.isMetalCreditsExpression(Arrays.asList("glob glob Silver is ABC Credits".split(" "))));
	}

	@Test
	public void testIsMetalCreditsExpression_EmptyInput() {
		assertFalse(metalCreditsProcessor.isMetalCreditsExpression(Arrays.asList("".split(" "))));
	}

	@Test
	public void testIsMetalCreditsExpression_InValidIdentifierIS() {
		assertFalse(metalCreditsProcessor
				.isMetalCreditsExpression(Arrays.asList("glob glob Silver ABC 34 Credits".split(" "))));
	}

	@Test
	public void testIsMetalCreditsExpression_InValidIdentifierCredits() {
		assertFalse(
				metalCreditsProcessor.isMetalCreditsExpression(Arrays.asList("glob glob Silver is 34 ABC".split(" "))));
	}

	@Test
	public void testParseMetalCredits_ValidInput() {
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("glob is I".split(" ")));
		metalCreditsProcessor.parseMetalCredits(Arrays.asList("glob glob Silver is 34 Credits".split(" ")));
		assertEquals(17, metalCreditsProcessor.getMetalCreditsForMetal("Silver").doubleValue(), 0.1);
	}

	@Test(expected = InvalidDataException.class)
	public void testParseMetalCredits_InValidGalaticInputBlue() {
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("glob is I".split(" ")));
		metalCreditsProcessor.parseMetalCredits(Arrays.asList("glob blue Silver is 34 Credits".split(" ")));
	}

	@Test(expected = InvalidDataException.class)
	public void testParseMetalCredits_InValidNonNumericCreditInput() {
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("glob is I".split(" ")));
		metalCreditsProcessor.parseMetalCredits(Arrays.asList("glob glob Silver is ABC Credits".split(" ")));
	}

	@Test(expected = InvalidDataException.class)
	public void testParseMetalCredits_InValidGalaticInput() {
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("glob is I".split(" ")));
		metalCreditsProcessor.parseMetalCredits(Arrays.asList("Silver is ABC Credits".split(" ")));
	}

	@Test(expected = InvalidDataException.class)
	public void testParseMetalCredits_InValidIdentifierCredits() {
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("glob is I".split(" ")));
		metalCreditsProcessor.parseMetalCredits(Arrays.asList("glob glob Silver is 34".split(" ")));
	}

	@Test
	public void testParseMetalCredits_InValidIdentifierGivenAnyMetalName() {
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("glob is I".split(" ")));
		metalCreditsProcessor.parseMetalCredits(Arrays.asList("glob glob Copper is 34 Credits".split(" ")));
		assertEquals(17, metalCreditsProcessor.getMetalCreditsForMetal("Copper").doubleValue(), 0.1);
	}

	@Test
	public void testgetMetalCreditsForMetal_ValidInput() {
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("glob is I".split(" ")));
		metalCreditsProcessor.parseMetalCredits(Arrays.asList("glob glob Silver is 34 Credits".split(" ")));
		assertEquals(17, metalCreditsProcessor.getMetalCreditsForMetal("Silver").doubleValue(), 0.1);
	}

	@Test(expected = InvalidDataException.class)
	public void testgetMetalCreditsForMetal_NonExistingMetalInput() {
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("glob is I".split(" ")));
		metalCreditsProcessor.parseMetalCredits(Arrays.asList("glob glob Silver is 34 Credits".split(" ")));
		metalCreditsProcessor.getMetalCreditsForMetal("Copper");
	}

}
