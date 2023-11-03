package dataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	
	private Properties properties;
	private static ConfigReader configReader;
	
	private ConfigReader() {
		BufferedReader reader;
	    	String propertyFilePath = "C:\\Users\\engre\\OneDrive\\Documents\\GitHub\\prestashop\\serenity-junit-starter-master\\Configs\\Configuration.properties";
	        try {
	            reader = new BufferedReader(new FileReader(propertyFilePath));
	            properties = new Properties();
	            try {
	                properties.load(reader);
	                reader.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
	        }		
	}

    public static ConfigReader getInstance( ) {
    	if(configReader == null) {
    		configReader = new ConfigReader();
    	}
        return configReader; 
    }
    public String getBaseUrl() {
        String baseUrl = properties.getProperty("base_Url");
        if(baseUrl != null) return baseUrl;
        else throw new RuntimeException("base_Url not specified in the Configuration.properties file.");
    }
    public String getAuthorization() {
        String authorization = properties.getProperty("authorization");
        if(authorization != null) return authorization;
        else throw new RuntimeException("authorization value not specified in the Configuration.properties file.");
    }
    public String getcookie() {
        String cookie = properties.getProperty("authorization");
        if(cookie != null) return cookie;
        else throw new RuntimeException("cookie value not specified in the Configuration.properties file.");
    }
}
