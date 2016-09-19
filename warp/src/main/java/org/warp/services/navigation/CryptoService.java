package org.warp.services.navigation;

public interface CryptoService {
	
	String encrpytPassword(String passwordString);
	
	boolean checkPassword (String passwordKeyedIn, String encryptedPassword);
}
