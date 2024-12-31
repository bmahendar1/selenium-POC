package config.initialization;

import java.io.File;

public enum DataFiles {

	;
	public static String CONFIG_FILE_PATH= new File("src/test/resources/config.properties").getAbsolutePath();
	public static String CONFIG_QA_FILE_PATH= new File("src/test/resources/config-qa.properties").getAbsolutePath();
	public static String DATA_FILE_PATH= new File("data.properties").getAbsolutePath();
	public static String BULK_DATA_FILE_PATH= new File("src/test/resources/bulkDataXLSX.xlsx").getAbsolutePath();
	//InputStream inputStream = getClass().getClassLoader().getResourceAsStream("bulkDataXLSX.xlsx");
}
