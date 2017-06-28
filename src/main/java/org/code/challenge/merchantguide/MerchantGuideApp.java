package org.code.challenge.merchantguide;

import org.code.challenge.merchantguide.exception.InvalidInputException;
import org.code.challenge.merchantguide.processor.MerchantGuideProcessor;

/**
 * Main Class Merchant Guide Application
 * 
 * @author sr.praneeth@gmail.com
 * @see README.MD
 *
 */
public class MerchantGuideApp {

	/**
	 * Main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		validateArgs(args);
		try {
			new MerchantGuideProcessor(args[0]).process();
		} catch (InvalidInputException e) {
			System.err.println("Error occured processing. " + e.getMessage());
		}
	}

	private static void validateArgs(String[] args) {
		if (args.length == 0) {
			System.out.println("Pass the input file as first program argument. ");
			System.out.println("Sample usage : `java -cp merchant-guide.jar org.code.challenge.merchantguide.MerchantGuideApp [inputFile]`");
			System.exit(0);
		}
	}

}
