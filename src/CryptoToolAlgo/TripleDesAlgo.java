package CryptoToolAlgo;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class TripleDesAlgo implements ParentAlgo {

	public String encrypt(String keyHex, String plaintextHex, String mode) throws Exception {
		// If 3Des Key is 32, make 48 padding
		if (keyHex.length() == 32) {
			keyHex = keyHex + keyHex.substring(0, 16);
		}

		SecretKey key = new SecretKeySpec(
				DatatypeConverter.parseHexBinary(keyHex), "DESede");

		if (mode.equals("ECB")) {
			Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] result = cipher.doFinal(DatatypeConverter.parseHexBinary(plaintextHex));
			return DatatypeConverter.printHexBinary(result);
		} else if (mode.equals("CBC")) {
			Cipher cipher = Cipher.getInstance("DESede/CBC/NoPadding");
			cipher.init(Cipher.ENCRYPT_MODE, key, ivectorSpecv);
			byte[] result = cipher.doFinal(DatatypeConverter.parseHexBinary(plaintextHex));
			return DatatypeConverter.printHexBinary(result);
		} else {
			throw new Exception("Error, unknown ciphering mode. Only ECB & CBC are allowed");
		}
	}

	public String decrypt(String keyHex, String plaintextHex, String mode) throws Exception {
		// If 3Des Key is 32, make 48 padding
		if (keyHex.length() == 32) {
			keyHex = keyHex + keyHex.substring(0, 16);
		}

		SecretKey key = new SecretKeySpec(DatatypeConverter.parseHexBinary(keyHex), "DESede");

		if (mode.equals("ECB")) {
			Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] result = cipher.doFinal(DatatypeConverter.parseHexBinary(plaintextHex));
			return DatatypeConverter.printHexBinary(result);
		} else if (mode.equals("CBC")) {
			Cipher cipher = Cipher.getInstance("DESede/CBC/NoPadding");
			cipher.init(Cipher.DECRYPT_MODE, key, ivectorSpecv);
			byte[] result = cipher.doFinal(DatatypeConverter.parseHexBinary(plaintextHex));
			return DatatypeConverter.printHexBinary(result);
		} else {
			throw new Exception("Error, unknown ciphering mode. Only ECB & CBC are allowed");
		}
	}
}
