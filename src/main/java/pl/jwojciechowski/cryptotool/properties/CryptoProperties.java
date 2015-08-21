package pl.jwojciechowski.cryptotool.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CryptoProperties {
	Properties props = new Properties();
	InputStream input = null;
	String valueProperty = null;

	public String getCryptoProperty(String keyProperty) throws IOException {
		input = new FileInputStream("config.properties");
		props.load(input);
		valueProperty = props.getProperty(keyProperty);
		input.close();
		return valueProperty;
	}
}
