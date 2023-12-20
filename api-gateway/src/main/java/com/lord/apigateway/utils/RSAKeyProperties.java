package com.lord.apigateway.utils;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.stereotype.Component;

@Component
public class RSAKeyProperties {
	
	private RSAPublicKey publicKey;
	private RSAPrivateKey privateKey;
	
	public RSAKeyProperties()throws NoSuchAlgorithmException {
		
			KeyPair pair = KeyGeneratorUtil.generateRsaKey();
			this.publicKey = (RSAPublicKey) pair.getPublic();
			this.privateKey = (RSAPrivateKey) pair.getPrivate();
		
	}
	public RSAPublicKey getPublicKey() {
		return this.publicKey;
	}
	public void setPublicKey(RSAPublicKey publicKey) {
		this.publicKey = publicKey;
	}
	public RSAPrivateKey getPrivateKey() {
		return this.privateKey;
	}
	public void setPrivateKey(RSAPrivateKey privateKey) {
		this.privateKey = privateKey;
	}

}
