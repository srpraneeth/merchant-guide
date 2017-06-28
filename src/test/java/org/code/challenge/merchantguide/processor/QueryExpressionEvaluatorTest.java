package org.code.challenge.merchantguide.processor;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;

import java.util.Arrays;

import org.code.challenge.merchantguide.exception.InvalidDataException;
import org.code.challenge.merchantguide.utils.RomanNumberToIntegerConverter;
import org.code.challenge.merchantguide.writer.OutputWriter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class QueryExpressionEvaluatorTest {

	private QueryExpressionEvaluator queryExpressionEvaluator;
	private RomanSymbolProcessor romanSymbolProcessor;
	private RomanNumberToIntegerConverter romanNumberToIntegerConverter;
	private MetalCreditsProcessor metalCreditsProcessor;
	private OutputWriter outputWriter;

	@Before
	public void setUp() {
		romanSymbolProcessor = new RomanSymbolProcessor();
		romanNumberToIntegerConverter = new RomanNumberToIntegerConverter();
		metalCreditsProcessor = new MetalCreditsProcessor(romanSymbolProcessor, romanNumberToIntegerConverter);
		outputWriter = Mockito.mock(OutputWriter.class);
		queryExpressionEvaluator = new QueryExpressionEvaluator(romanSymbolProcessor, romanNumberToIntegerConverter,
				metalCreditsProcessor, outputWriter);
	}

	@Test
	public void testIsQueryExpression_ValidGalaticNumberQuery() {
		assertTrue(queryExpressionEvaluator
				.isQueryExpression(Arrays.asList("how much is pish tegj glob glob ?".split(" "))));
	}

	@Test
	public void testIsQueryExpression_ValidMetalCreditsQuery() {
		assertTrue(queryExpressionEvaluator
				.isQueryExpression(Arrays.asList("how many Credits is glob prok Silver ?".split(" "))));
	}

	@Test
	public void testIsQueryExpression_InValidMetalCreditsQuery() {
		assertFalse(queryExpressionEvaluator
				.isQueryExpression(Arrays.asList("how many Credits is glob prok Silver".split(" "))));
	}

	@Test
	public void testIsQueryExpression_InValidGalaticNumberQuery() {
		assertFalse(
				queryExpressionEvaluator.isQueryExpression(Arrays.asList("much is pish tegj glob glob ?".split(" "))));
	}

	@Test
	public void testIsQueryExpression_EmptyGalaticNumberQuerySize() {
		assertFalse(queryExpressionEvaluator.isQueryExpression(Arrays.asList("how ?".split(" "))));
	}

	@Test
	public void testIsQueryExpression_EmptyMetalCreditsQuerySize() {
		assertTrue(queryExpressionEvaluator.isQueryExpression(Arrays.asList("how much many Credits is ?".split(" "))));
	}

	@Test
	public void testEvaluateExpression_ValidInputGalaticNumericQuery() {
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("glob is I".split(" ")));
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("prok is V".split(" ")));
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("pish is X".split(" ")));
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("tegj is L".split(" ")));
		queryExpressionEvaluator.evaluateExpression(Arrays.asList("how much is pish tegj glob glob ?".split(" ")));
		Mockito.verify(outputWriter, times(1)).writeLine("pish tegj glob glob is 42");
	}
	
	@Test
	public void testEvaluateExpression_ValidInputGalaticNumericQuerySingleGalaticNumeral() {
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("glob is I".split(" ")));
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("prok is V".split(" ")));
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("pish is X".split(" ")));
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("tegj is L".split(" ")));
		queryExpressionEvaluator.evaluateExpression(Arrays.asList("how much is pish ?".split(" ")));
		Mockito.verify(outputWriter, times(1)).writeLine("pish is 10");
	}
	
	@Test
	public void testEvaluateExpression_ValidInputGalaticNumericQueryMoreGalaticNumeral() {
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("glob is I".split(" ")));
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("prok is V".split(" ")));
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("pish is X".split(" ")));
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("tegj is L".split(" ")));
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("pull is C".split(" ")));
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("dell is D".split(" ")));
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("help is M".split(" ")));
		queryExpressionEvaluator.evaluateExpression(Arrays.asList("how much is help help help dell pull pish pish glob pish ?".split(" ")));
		Mockito.verify(outputWriter, times(1)).writeLine("help help help dell pull pish pish glob pish is 3629");
	}
	
	@Test(expected=InvalidDataException.class)
	public void testEvaluateExpression_InvalidGalaticNumeral() {
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("glob is I".split(" ")));
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("prok is V".split(" ")));
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("pish is X".split(" ")));
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("tegj is L".split(" ")));
		queryExpressionEvaluator.evaluateExpression(Arrays.asList("how much is pish tegj glob glob glob pish prok pish ?".split(" ")));
	}

	@Test(expected = InvalidDataException.class)
	public void testEvaluateExpression_InValidInputGalaticNumeric() {
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("glob is I".split(" ")));
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("prok is V".split(" ")));
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("pish is X".split(" ")));
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("tegj is L".split(" ")));
		queryExpressionEvaluator.evaluateExpression(Arrays.asList("how much is AAAA tegj glob glob ?".split(" ")));
	}

	@Test(expected = InvalidDataException.class)
	public void testEvaluateExpression_EmptyGalaticNumeric() {
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("glob is I".split(" ")));
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("prok is V".split(" ")));
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("pish is X".split(" ")));
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("tegj is L".split(" ")));
		queryExpressionEvaluator.evaluateExpression(Arrays.asList("how much is ?".split(" ")));
	}

	@Test
	public void testEvaluateExpression_ValidInputMetalCreditsQuery() {
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("glob is I".split(" ")));
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("prok is V".split(" ")));
		metalCreditsProcessor.parseMetalCredits(Arrays.asList("glob glob Silver is 34 Credits".split(" ")));
		queryExpressionEvaluator.evaluateExpression(Arrays.asList("how many Credits is glob prok Silver ?".split(" ")));
		Mockito.verify(outputWriter, times(1)).writeLine("glob prok Silver is " + ((34/2) * 4) +" Credits");
	}
	
	@Test
	public void testEvaluateExpression_ValidInputMetalCreditsQueryPerUnitInput() {
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("glob is I".split(" ")));
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("prok is V".split(" ")));
		metalCreditsProcessor.parseMetalCredits(Arrays.asList("glob Silver is 34 Credits".split(" ")));
		queryExpressionEvaluator.evaluateExpression(Arrays.asList("how many Credits is glob prok Silver ?".split(" ")));
		Mockito.verify(outputWriter, times(1)).writeLine("glob prok Silver is " + 34 * 4 + " Credits");
	}
	
	@Test
	public void testEvaluateExpression_ValidInputMetalCreditsQueryPerUnitMetal() {
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("glob is I".split(" ")));
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("prok is V".split(" ")));
		metalCreditsProcessor.parseMetalCredits(Arrays.asList("glob Silver is 34 Credits".split(" ")));
		queryExpressionEvaluator.evaluateExpression(Arrays.asList("how many Credits is glob Silver ?".split(" ")));
		Mockito.verify(outputWriter, times(1)).writeLine("glob Silver is " + 34 * 1 + " Credits");
	}

	@Test
	public void testEvaluateExpression_EmptyInputMetalCreditsQuery() {
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("glob is I".split(" ")));
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("prok is V".split(" ")));
		metalCreditsProcessor.parseMetalCredits(Arrays.asList("glob glob Silver is 34 Credits".split(" ")));
		queryExpressionEvaluator.evaluateExpression(Arrays.asList("how many Credits is ?".split(" ")));
		Mockito.verify(outputWriter, times(1)).writeLine("I have no idea what you are talking about");
	}
	
	@Test(expected=InvalidDataException.class)
	public void testEvaluateExpression_EmptyInputMetalCreditsNoMetal() {
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("glob is I".split(" ")));
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("prok is V".split(" ")));
		metalCreditsProcessor.parseMetalCredits(Arrays.asList("glob Silver is 34 Credits".split(" ")));
		queryExpressionEvaluator.evaluateExpression(Arrays.asList("how many Credits is Silver ?".split(" ")));
	}

	@Test(expected = InvalidDataException.class)
	public void testEvaluateExpression_InvalidInputMetalName() {
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("glob is I".split(" ")));
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("prok is V".split(" ")));
		metalCreditsProcessor.parseMetalCredits(Arrays.asList("glob glob Silver is 34 Credits".split(" ")));
		queryExpressionEvaluator.evaluateExpression(Arrays.asList("how many Credits is glob prok Copper ?".split(" ")));
	}

	@Test(expected = InvalidDataException.class)
	public void testEvaluateExpression_InvalidInputGalaticSymbol() {
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("glob is I".split(" ")));
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("prok is V".split(" ")));
		metalCreditsProcessor.parseMetalCredits(Arrays.asList("glob glob Silver is 34 Credits".split(" ")));
		queryExpressionEvaluator.evaluateExpression(Arrays.asList("how many Credits is blue blue Silver ?".split(" ")));
	}

	@Test
	public void testEvaluateExpression_ImproperQueryInput() {
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("glob is I".split(" ")));
		romanSymbolProcessor.parseRomanSymbols(Arrays.asList("prok is V".split(" ")));
		metalCreditsProcessor.parseMetalCredits(Arrays.asList("glob glob Silver is 34 Credits".split(" ")));
		queryExpressionEvaluator.evaluateExpression(
				Arrays.asList("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?".split(" ")));
		Mockito.verify(outputWriter, times(1)).writeLine("I have no idea what you are talking about");
	}

}
