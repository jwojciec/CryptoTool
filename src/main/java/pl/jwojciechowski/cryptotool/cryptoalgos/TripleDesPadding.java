package pl.jwojciechowski.cryptotool.cryptoalgos;

public class TripleDesPadding {
	public static String tDesPadding(String keyHex, String algo) {
		if ((keyHex.length() == 32) && (algo == "TripleDES")) {
			keyHex = keyHex + keyHex.substring(0, 16);
			return keyHex;
		} else
			return keyHex;
	}
}
