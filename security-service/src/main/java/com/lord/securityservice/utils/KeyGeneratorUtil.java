package com.lord.securityservice.utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class KeyGeneratorUtil {
	
	public static KeyPair generateRsaKey()throws NoSuchAlgorithmException {
		KeyPair keyPair;
		
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(2048);
			keyPair = keyPairGenerator.generateKeyPair();
			return keyPair;
		}catch(NoSuchAlgorithmException e) {
			throw new NoSuchAlgorithmException(e.getMessage());
		}
		
	}

}
