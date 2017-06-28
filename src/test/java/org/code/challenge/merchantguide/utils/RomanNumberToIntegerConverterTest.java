package org.code.challenge.merchantguide.utils;

import static org.junit.Assert.assertEquals;

import org.code.challenge.merchantguide.exception.InvalidDataException;
import org.junit.Before;
import org.junit.Test;

public class RomanNumberToIntegerConverterTest {

	private RomanNumberToIntegerConverter converter;

	@Before
	public void setUp() {
		converter = new RomanNumberToIntegerConverter();
	}

	@Test(expected = InvalidDataException.class)
	public void testConvertRomanToInteger_InvalidInput() {
		converter.convertRomanToInteger(null);
	}

	@Test(expected = InvalidDataException.class)
	public void testConvertRomanToInteger_EmptyInput() {
		converter.convertRomanToInteger("");
	}

	@Test
	public void testConvertRomanToInteger_MAddM() {
		assertEquals(2006, converter.convertRomanToInteger("MMVI").intValue());
	}

	@Test
	public void testConvertRomanToInteger_CSubM() {
		assertEquals(1944, converter.convertRomanToInteger("MCMXLIV").intValue());
	}

	@Test
	public void testConvertRomanToInteger_XRepeat3Times() {
		assertEquals(39, converter.convertRomanToInteger("XXXIX").intValue());
	}

	@Test
	public void testConvertRomanToInteger_ISubFromX() {
		assertEquals(19, converter.convertRomanToInteger("XIX").intValue());
	}

	@Test
	public void testConvertRomanToInteger_ISubFromV() {
		assertEquals(4, converter.convertRomanToInteger("IV").intValue());
	}

	@Test
	public void testConvertRomanToInteger_CSubMAndAddM() {
		assertEquals(1900, converter.convertRomanToInteger("MCM").intValue());
	}

	@Test
	public void testConvertRomanToInteger_CSubMAndAddX() {
		assertEquals(910, converter.convertRomanToInteger("CMX").intValue());
	}

	@Test
	public void testConvertRomanToInteger_2MAddAndCSubM() {
		assertEquals(2900, converter.convertRomanToInteger("MMCM").intValue());
	}

	@Test
	public void testConvertRomanToInteger_CSubMAndMMAndIII() {
		assertEquals(1903, converter.convertRomanToInteger("MCMIII").intValue());
	}

}
