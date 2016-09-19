package org.warp.services.navigation;

import org.jasypt.util.password.BasicPasswordEncryptor;

public class CryptoServiceImpl implements CryptoService {
	
	public String encrpytPassword(String passwordString){
		BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
		String encryptedPassword = passwordEncryptor.encryptPassword(passwordString);
		return encryptedPassword;
	};
	
	public boolean checkPassword (String passwordKeyedIn, String encryptedPassword){
		BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
		return passwordEncryptor.checkPassword(passwordKeyedIn, encryptedPassword);
	}
	

}
