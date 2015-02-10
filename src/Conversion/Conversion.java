package Conversion;

import java.util.Random;

public class Conversion {
	
	public String getRandomHexString(int numchars){
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        while(sb.length() < numchars){
            sb.append(Integer.toHexString(r.nextInt()));
        }

        return sb.toString().substring(0, numchars).toUpperCase();
    }	
}
