package pl.jwojciechowski.cryptotool.cryptoalgos;

import junit.framework.TestCase;

public class CryptoAlgorithmTest extends TestCase {

	public void testDesAlgo() {
		try {
			CryptoAlgorithm cryptoAlgorithm = new CryptoAlgorithm();
			cryptoAlgorithm.setKeyHex("30BF6F8CC6298E9B");
			cryptoAlgorithm.setPlaintextHex("6BCBE7257CDB59E0");
			cryptoAlgorithm.setAlgo_mode("DES");
			cryptoAlgorithm.setEcbCbcMode("ECB");
			assertEquals("C4BC678E047E9CC9", cryptoAlgorithm.encrypt());
			assertEquals("EF70C1A28EC9D50E", cryptoAlgorithm.decrypt());
			
			cryptoAlgorithm.setEcbCbcMode("CBC");
			assertEquals("C4BC678E047E9CC9", cryptoAlgorithm.encrypt());
			assertEquals("EF70C1A28EC9D50E", cryptoAlgorithm.decrypt());	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testTripleDesAlgo() {
		try {
			CryptoAlgorithm cryptoAlgorithm = new CryptoAlgorithm();
			cryptoAlgorithm.setKeyHex("A7EC75819FF4CFF9D31F2DA16FD9155A");
			cryptoAlgorithm.setPlaintextHex("31FA2F68C1E5D927B71E96D23874C14F");
			cryptoAlgorithm.setAlgo_mode("TripleDES");
			cryptoAlgorithm.setEcbCbcMode("ECB");
			assertEquals("9019D71BFDCB3F7E9F43F6AB76F57003", cryptoAlgorithm.encrypt());
			assertEquals("23C55A7CB872E63E92C0ECD69089F95C", cryptoAlgorithm.decrypt());
			
			cryptoAlgorithm.setEcbCbcMode("CBC");
			assertEquals("9019D71BFDCB3F7ECEA109EAA09C735E", cryptoAlgorithm.encrypt());
			assertEquals("23C55A7CB872E63EA33AC3BE516C207B", cryptoAlgorithm.decrypt());	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testAesAlgo() {
		try {
			CryptoAlgorithm cryptoAlgorithm = new CryptoAlgorithm();
			cryptoAlgorithm.setKeyHex("772E06C8A355B3BA2AD62E769F211CE1649FFDEE831D5686C63B52805380B5CA");
			cryptoAlgorithm.setPlaintextHex("CFCF2A4A7332F4C5E0F1D805AED5569B83248C0EAC12B6B5E20816903D5F00D1");
			cryptoAlgorithm.setAlgo_mode("AES");
			cryptoAlgorithm.setEcbCbcMode("ECB");
			assertEquals("4CD6249A521C08D3841B4AEB0CF0A45B24172477C504B1DC6F3A57BF045AD82D", cryptoAlgorithm.encrypt());
			assertEquals("68A7F2A2C409C30CB60FA9B01D9A53BBE2EC581952BA196CACAE7220CBB4FDA5", cryptoAlgorithm.decrypt());
			
			cryptoAlgorithm.setEcbCbcMode("CBC");
			assertEquals("4CD6249A521C08D3841B4AEB0CF0A45B8BEFF01263A11705879D63237D2E8A1E", cryptoAlgorithm.encrypt());
			assertEquals("68A7F2A2C409C30CB60FA9B01D9A53BB2D2372532188EDA94C5FAA256561AB3E", cryptoAlgorithm.decrypt());	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

}
