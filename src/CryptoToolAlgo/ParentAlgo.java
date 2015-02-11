package CryptoToolAlgo;

import javax.crypto.spec.IvParameterSpec;

public interface ParentAlgo {
	byte[] ivBytes = new byte[] { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
	IvParameterSpec ivectorSpecv = new IvParameterSpec(ivBytes);

	public String encrypt(String key, String value, String mode) throws Exception;

	public String decrypt(String key, String value, String mode) throws Exception;

}
