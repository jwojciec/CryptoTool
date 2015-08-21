package cryptoalgos;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import properties.CryptoProperties;

public class CryptoAlgorithm {
	CryptoProperties cryptoProperties = new CryptoProperties();
	Cipher cipher;

	private String keyHex;
	private String plaintextHex;
	private String ecb_cbc_mode;
	private String algo_mode;
	private String cipherTransformation;

	public String encrypt() throws Exception {
		this.keyHex = TripleDesPadding.tDesPadding(keyHex, algo_mode);
		SecretKey key = new SecretKeySpec(DatatypeConverter.parseHexBinary(keyHex), algo_mode);
		cipherTransformation = cryptoProperties.getCryptoProperty(algo_mode + "_" + ecb_cbc_mode);
		cipher = Cipher.getInstance(cipherTransformation);

		if (ecb_cbc_mode.equals("ECB")) {
			cipher.init(Cipher.ENCRYPT_MODE, key);
		} else if (ecb_cbc_mode.equals("CBC")) {
			String initVectorKey = algo_mode + "_InitialVector";
			String initVectorValue = cryptoProperties.getCryptoProperty(initVectorKey);
			byte[] ivBytes = DatatypeConverter.parseHexBinary(initVectorValue);
			IvParameterSpec initVectorSpec = new IvParameterSpec(ivBytes);
			cipher.init(Cipher.ENCRYPT_MODE, key, initVectorSpec);
		} else
			throw new Exception("Wrong ciphering mode selected. Only ECB or CBC allowed!");

		byte[] result = cipher.doFinal(DatatypeConverter.parseHexBinary(plaintextHex));
		return DatatypeConverter.printHexBinary(result);
	}

	public String decrypt() throws Exception {
		this.keyHex = TripleDesPadding.tDesPadding(keyHex, algo_mode);
		SecretKey key = new SecretKeySpec(DatatypeConverter.parseHexBinary(keyHex), algo_mode);
		cipherTransformation = cryptoProperties.getCryptoProperty(algo_mode + "_" + ecb_cbc_mode);
		cipher = Cipher.getInstance(cipherTransformation);

		if (ecb_cbc_mode.equals("ECB")) {
			cipher.init(Cipher.DECRYPT_MODE, key);
		} else if (ecb_cbc_mode.equals("CBC")) {
			String initVectorKey = algo_mode + "_InitialVector";
			String initVectorValue = cryptoProperties.getCryptoProperty(initVectorKey);
			byte[] ivBytes = DatatypeConverter.parseHexBinary(initVectorValue);
			IvParameterSpec initVectorSpec = new IvParameterSpec(ivBytes);
			cipher.init(Cipher.DECRYPT_MODE, key, initVectorSpec);
		} else
			throw new Exception("Wrong deciphering mode selected. Only ECB or CBC allowed!");

		byte[] result = cipher.doFinal(DatatypeConverter.parseHexBinary(plaintextHex));
		return DatatypeConverter.printHexBinary(result);
	}

	public void setKeyHex(String keyHex) {
		this.keyHex = keyHex;
	}

	public void setPlaintextHex(String plaintextHex) {
		this.plaintextHex = plaintextHex;
	}

	public void setEcbCbcMode(String ecb_cbc_mode) {
		this.ecb_cbc_mode = ecb_cbc_mode;
	}

	public void setAlgo_mode(String algo_mode) {
		this.algo_mode = algo_mode;
	}

}
