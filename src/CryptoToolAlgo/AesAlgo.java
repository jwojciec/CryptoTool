package CryptoToolAlgo;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class AesAlgo extends ParentAlgo{
	
	public String encrypt(String keyHex, String plaintextHex) throws Exception{
	    SecretKey key = new SecretKeySpec(DatatypeConverter.parseHexBinary(keyHex), "AES");
    	Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
    	cipher.init(Cipher.ENCRYPT_MODE, key);
    	byte[] result = cipher.doFinal(DatatypeConverter.parseHexBinary(plaintextHex));
    	return DatatypeConverter.printHexBinary(result);
	}

	public String decrypt(String keyHex, String plaintextHex) throws Exception{
	    SecretKey key = new SecretKeySpec(DatatypeConverter.parseHexBinary(keyHex), "AES");
    	Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
    	cipher.init(Cipher.DECRYPT_MODE, key);
    	byte[] result = cipher.doFinal(DatatypeConverter.parseHexBinary(plaintextHex));
    	return DatatypeConverter.printHexBinary(result);
	}

}
