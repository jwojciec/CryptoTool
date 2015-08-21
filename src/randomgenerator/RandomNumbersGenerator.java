package randomgenerator;

import java.util.Random;

public class RandomNumbersGenerator {

	public String generateHex(int numchars) {
		Random rand = new Random();
		StringBuffer strBuffer = new StringBuffer();
		while (strBuffer.length() < numchars) {
			strBuffer.append(Integer.toHexString(rand.nextInt()));
		}

		return strBuffer.toString().substring(0, numchars).toUpperCase();
	}
}
