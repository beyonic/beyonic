package com.rampgreen.encryption.integration;

import static org.junit.Assert.*;

import org.junit.Test;

import com.beyonic.client.Payment.PaymentClient;
import com.beyonic.client.Payment.PaymentClientImpl;

public class BeyonicPaymentIntegrationAcceptanceTest{
	
	String expectedKey = "!@#$%%$#@!)(*&^^&*()";
	public static final String URL = "https://demo.strongauth.com:8181";
	public static final String DOMAIN_ID = "13";
	public static final String ENCRYPTION_USER_NAME = "encryptonly";
	public static final String DECRYPTION_USER_NAME = "decryptonly";
	public static final String PASSWORD = "RA#P0nline";
	public static String TOKEN="";
	
	
	@Test
	public void shouldBeAbleToSendTheKeyToKeyManager() {
//		PaymentClient keyManagerClient = new PaymentClientImpl(URL, DOMAIN_ID, ENCRYPTION_USER_NAME, PASSWORD);
//		TOKEN = keyManagerClient.setKey(expectedKey);
//		System.out.println("The token is: "+TOKEN);
//		assertNotNull(TOKEN);
//		assertFalse("".equals(TOKEN));

	}
	
	@Test
	public void shouldBeAbleToGetTheEncryptionKeyFromStrongKey() {
	//	String keyFetched = getTheEncryptionKeyFromTheKeyManager();
	//	mountRequiredFilesystemsWithTransparentOnTheFlyEncryption(keyFetched);
	}

	private void mountRequiredFilesystemsWithTransparentOnTheFlyEncryption(
			String keyFetched) {
		// TODO Auto-generated method stub
		
	}

//	private String getTheEncryptionKeyFromTheKeyManager() {
//		PaymentClient keyManagerClient = new PaymentClientImpl(URL, DOMAIN_ID, DECRYPTION_USER_NAME, PASSWORD);
//		String keyFetched = keyManagerClient.getKey(TOKEN);
//		assertEquals(expectedKey, keyFetched);
//		System.out.println("The key that has been fetched is: "+ keyFetched);
//		return keyFetched;
//	}

//	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return expectedKey;
	}

}
