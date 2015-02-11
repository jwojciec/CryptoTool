package CryptoToolAlgo;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class AesAlgo implements ParentAlgo {
	byte[] ivBytes = new byte[] { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
	IvParameterSpec ivectorSpecv = new IvParameterSpec(ivBytes);

	public String encrypt(String keyHex, String plaintextHex, String mode) throws Exception {
		SecretKey key = new SecretKeySpec(DatatypeConverter.parseHexBinary(keyHex), "AES");
		if (mode.equals("ECB")) {
			Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] result = cipher.doFinal(DatatypeConverter.parseHexBinary(plaintextHex));
			return DatatypeConverter.printHexBinary(result);
		} else if (mode.equals("CBC")) {
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			cipher.init(Cipher.ENCRYPT_MODE, key, ivectorSpecv);
			byte[] result = cipher.doFinal(DatatypeConverter.parseHexBinary(plaintextHex));
			return DatatypeConverter.printHexBinary(result);
		} else {
			throw new Exception("Error, unknown ciphering mode. Only ECB & CBC are allowed");
		}
	}

	public String decrypt(String keyHex, String plaintextHex, String mode) throws Exception {
		SecretKey key = new SecretKeySpec(DatatypeConverter.parseHexBinary(keyHex), "AES");
		if (mode.equals("ECB")) {
			Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] result = cipher.doFinal(DatatypeConverter.parseHexBinary(plaintextHex));
			return DatatypeConverter.printHexBinary(result);
		} else if (mode.equals("CBC")) {
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			cipher.init(Cipher.DECRYPT_MODE, key, ivectorSpecv);
			byte[] result = cipher.doFinal(DatatypeConverter.parseHexBinary(plaintextHex));
			return DatatypeConverter.printHexBinary(result);
		} else {
			throw new Exception("Error, unknown ciphering mode. Only ECB & CBC are allowed");
		}
	}
}
