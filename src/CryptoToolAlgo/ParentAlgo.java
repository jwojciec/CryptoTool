package CryptoToolAlgo;

public class ParentAlgo {
	String key = "";
	String value = "";
	String result = "";
	
	public String encrypt(String key, String value) throws Exception{
		this.key = key;
		this.value = value;
		result = "1234567890";
		return result;
	}
		
	public String decrypt(String key, String value) throws Exception{
			this.key = key;
			this.value = value;
			result = "0987654321";
			return result;		
	}

}
