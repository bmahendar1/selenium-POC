package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
	
	public static List<String[]> readCSV(String filePath) throws IOException {
		
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line.split(","));
            }
        }
        return data;
    }
	
	public static boolean isFileDownloaded(String downloadPath, String fileName) {
		
		File dir = new File(downloadPath);
		
		File[] dirContent = dir.listFiles();
		
		for(File file: dirContent) {
//			System.out.println(file.getName());
			if(file.getName().equals(fileName) && !fileName.endsWith(".crdownload")) {
				return true;
			}
		}
		
		return false;
	}
	
	public static void sleep(long millis) {
		
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static <T extends Comparable<T>> boolean isSortedAsc(List<T> items) {
		
		for(int i=0; i<items.size() - 1; i++) {
			if(!(items.get(i).compareTo(items.get(i+1)) < 0)) {
				return false;
			}
		}
		return true;
	}
	
	public static <T extends Comparable<T>> boolean isSortedDesc(List<T> items) {
		
		for(int i=0; i<items.size() - 1; i++) {
			if(!(items.get(i).compareTo(items.get(i+1)) > 0)) {
				return false;
			}
		}
		return true;
	}
}
