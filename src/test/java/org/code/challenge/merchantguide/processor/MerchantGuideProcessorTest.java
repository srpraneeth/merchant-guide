package org.code.challenge.merchantguide.processor;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.Arrays;

import org.code.challenge.merchantguide.constants.RomanSymbol;
import org.code.challenge.merchantguide.reader.InputReader;
import org.code.challenge.merchantguide.writer.OutputWriter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class MerchantGuideProcessorTest {
	
	private MerchantGuideProcessor merchantGuideProcessor;
	private OutputWriter outputWriter;
	private InputReader inputReader;
	private RomanSymbolProcessor romanSymbolProcessor;
	private MetalCreditsProcessor metalCreditsProcessor;
	private QueryExpressionEvaluator queryExpressionEvaluator;
	
	@Before
	public void setUp() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		merchantGuideProcessor = new MerchantGuideProcessor("");
		outputWriter = Mockito.mock(OutputWriter.class);
		inputReader = Mockito.mock(InputReader.class);
		Field inputField = MerchantGuideProcessor.class.getDeclaredField("inputReader");
		inputField.setAccessible(true);
		inputField.set(merchantGuideProcessor, inputReader);
		Field outputField = MerchantGuideProcessor.class.getDeclaredField("outputWriter");
		outputField.setAccessible(true);
		outputField.set(merchantGuideProcessor, outputWriter);
		Field romanSymbolProcessorField = MerchantGuideProcessor.class.getDeclaredField("romanSymbolProcessor");
		romanSymbolProcessorField.setAccessible(true);
		romanSymbolProcessor = (RomanSymbolProcessor) romanSymbolProcessorField.get(merchantGuideProcessor);
		Field metalCreditsProcessorField = MerchantGuideProcessor.class.getDeclaredField("metalCreditsProcessor");
		metalCreditsProcessorField.setAccessible(true);
		metalCreditsProcessor = (MetalCreditsProcessor) metalCreditsProcessorField.get(merchantGuideProcessor);
		Field queryExpressionEvaluatorField = MerchantGuideProcessor.class.getDeclaredField("queryExpressionEvaluator");
		queryExpressionEvaluatorField.setAccessible(true);
		queryExpressionEvaluator = (QueryExpressionEvaluator) queryExpressionEvaluatorField.get(merchantGuideProcessor);
		
		Field outputWriterField = QueryExpressionEvaluator.class.getDeclaredField("outputWriter");
		outputWriterField.setAccessible(true);
		outputWriterField.set(queryExpressionEvaluator, outputWriter);
		
	}
	
	@Test
	public void testProcess_ParseRomanSymbolsExpression(){
		when(inputReader.readLines()).thenReturn(Arrays.asList("glob is I\nprok is V".split("\n")));
		merchantGuideProcessor.process();
		assertEquals(RomanSymbol.I, romanSymbolProcessor.getRomanSymbolForGalaticSymbol("glob"));
		assertEquals(RomanSymbol.V, romanSymbolProcessor.getRomanSymbolForGalaticSymbol("prok"));
	}
	
	@Test
	public void testProcess_MetalCreditsExpression(){
		when(inputReader.readLines()).thenReturn(Arrays.asList("glob is I\nprok is V\nglob prok Gold is 57800 Credits".split("\n")));
		merchantGuideProcessor.process();
		assertEquals((57800/4), metalCreditsProcessor.getMetalCreditsForMetal("Gold").doubleValue(), 0.1);
	}
	
	@Test
	public void testProcess_InvalidQuery(){
		when(inputReader.readLines()).thenReturn(Arrays.asList("glob is I\nprok is V\nhow much wood could a woodchuck chuck if a woodchuck could chuck wood ?".split("\n")));
		merchantGuideProcessor.process();
		verify(outputWriter, times(1)).writeLine("I have no idea what you are talking about");
	}
	
	@Test
	public void testProcess_GalaticNumberQuery(){
		when(inputReader.readLines()).thenReturn(Arrays.asList("glob is I\nprok is V\npish is X\ntegj is L\nhow much is pish tegj glob glob ?".split("\n")));
		merchantGuideProcessor.process();
		verify(outputWriter, times(1)).writeLine("pish tegj glob glob is 42");
	}
	
	@Test
	public void testProcess_MetalCreditQuery(){
		when(inputReader.readLines()).thenReturn(Arrays.asList("glob is I\nprok is V\nglob prok Gold is 57800 Credits\nhow many Credits is glob prok Gold ?".split("\n")));
		merchantGuideProcessor.process();
		verify(outputWriter, times(1)).writeLine("glob prok Gold is 57800 Credits");
	}

}
