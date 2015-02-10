package CryptoToolAlgo;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class TripleDesAlgoCBC extends ParentAlgo {
	byte[] ivBytes = new byte[]{0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

	IvParameterSpec ivectorSpecv = new IvParameterSpec(ivBytes);
	
	public String encrypt(String keyHex, String plaintextHex) throws Exception{
		//If 3Des Key is 32, make 48 padding
		if(keyHex.length() == 32) {
			keyHex = keyHex + keyHex.substring(0, 16);
		}
		
	    SecretKey key = new SecretKeySpec(DatatypeConverter.parseHexBinary(keyHex), "DESede");
    	Cipher cipher = Cipher.getInstance("DESede/CBC/NoPadding");
    	cipher.init(Cipher.ENCRYPT_MODE, key, ivectorSpecv);
    	byte[] result = cipher.doFinal(DatatypeConverter.parseHexBinary(plaintextHex));
    	return DatatypeConverter.printHexBinary(result);
	}

	public String decrypt(String keyHex, String plaintextHex) throws Exception{
		//If 3Des Key is 32, make 48 padding
		if(keyHex.length() == 32) {
			keyHex = keyHex + keyHex.substring(0, 16);
		}
		
	    SecretKey key = new SecretKeySpec(DatatypeConverter.parseHexBinary(keyHex), "DESede");
    	Cipher cipher = Cipher.getInstance("DESede/CBC/NoPadding");
    	cipher.init(Cipher.DECRYPT_MODE, key, ivectorSpecv);
    	byte[] result = cipher.doFinal(DatatypeConverter.parseHexBinary(plaintextHex));
    	return DatatypeConverter.printHexBinary(result);
	}		
}