package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Utils {

	static public String getProperty(String filePath, String propertyName) throws Exception {
		
		Properties properties = new Properties();
		try {
			FileInputStream fis = new FileInputStream(filePath);
			try {
				properties.load(fis);
				
				String value = properties.getProperty(propertyName);
				return value;
				
			} catch (IOException e) {
				e.printStackTrace();
				
				throw new Exception("Unable to load the file input stream "+e.getMessage());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Failed to locate the at given path "+e.getMessage());
		}
	}
}
