package com.brinkmcd.app.ws.shared;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class Utils {

	private final Random RANDOM = new SecureRandom();
	private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	/*
	 * Public wrapper to generate random userId
	 * Returns a string of given length of random characters from ALPHABET
	 */
	public String generateUserId(int length) {
		return generateRandomString(length);
	}

	/*
	 * Private implementation to generate the random string
	 */
	private String generateRandomString(int length) {
		StringBuilder returnValue = new StringBuilder(length);

		for(int i = 0; i < length; i++) {
			returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}
		
		return new String(returnValue);
	}
}
