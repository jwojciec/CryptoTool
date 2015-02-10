package CryptoToolAlgo;

import javax.crypto.spec.IvParameterSpec;

public class ParentAlgo {
	byte[] ivBytes = new byte[] { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
	IvParameterSpec ivectorSpecv = new IvParameterSpec(ivBytes);
	String key = "";
	String value = "";
	String result = "";

	public String encrypt(String key, String value, String mode) throws Exception {
		return "";
	}

	public String decrypt(String key, String value, String mode) throws Exception {
		return "";
	}

}
