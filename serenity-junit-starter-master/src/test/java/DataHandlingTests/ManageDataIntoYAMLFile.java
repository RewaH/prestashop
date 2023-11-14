package DataHandlingTests;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import dataProvider.YAMLFileManager;

public class ManageDataIntoYAMLFile {
	static String filePath ="Utils\\file.yaml";
    public static void main(String[] args) throws IOException {
    	
    	//readYamlFile();
    	//retrieveDataFromYamlFileByKey();
    	//searchForSpecificKeyValuePair();
    	//appendDataToYAMLFile();
    
    	//reformatData();
    	//parseYamlDataToJavaObject();

    	//createYamlFile();
    	addYamlDataToYamlFile();
    }
    
    
    // Read data from YAML file
    public static void readYamlFile() {
        YAMLFileManager yamlFileManager = new YAMLFileManager(filePath); // Provide the correct file path
        Map<String, Object> yamlData = yamlFileManager.readYAMLFile();
        if (yamlData != null) {
            System.out.println("YAML Data:");
            System.out.println(yamlData);
        } else {
            System.out.println("Failed to read YAML data from the file.");
        }
    }
 //////////////////////////////////////////////////////////////////////
    // Retrieve YAML data by key
    public static void retrieveDataFromYamlFileByKey() {
    	String key="person1";
        YAMLFileManager yamlFileManager = new YAMLFileManager(filePath); // Provide the file path
        Map<String, Object> person1Data = yamlFileManager.retrieveYAMLDataByKey(key);
        if (person1Data != null) {
            System.out.println("YAML Data for "+ key +" is:");
            System.out.println(person1Data);
        } else {
            System.out.println("Key "+ key +" not found.");
        }
    }
    /////////////////////////////////////////////////////////////
    //Search for a specific key-value pair
    public static void searchForSpecificKeyValuePair() {
    	String key="name";
    	String keyValue="Rewaa";
        YAMLFileManager yamlFileManager = new YAMLFileManager(filePath); 
        boolean found = yamlFileManager.searchYAMLData(key, keyValue);
        System.out.println("Search result: " + found);
    
}
    
    /////////////////////////////////////////////////////////////
    // Append data to the YAML file
    public static void appendDataToYAMLFile() {
    	String key="person4";
    	String subKey="gender";
    	String Value="female";
        YAMLFileManager yamlFileManager = new YAMLFileManager(filePath); 
        yamlFileManager.appendYAMLData(key,subKey,Value);

        Map<String, Object> appendedData = yamlFileManager.retrieveYAMLDataByKey(key);
System.out.println(appendedData);
    	
    	
    }
    
    /////////////////////////////////////////////////////////////
    // reformat data to the YAML file + using CustomObject class to set and get the data
    public static void reformatData() {
    	String key="person2";
    	CustomObject reformatedData=new CustomObject();
    	reformatedData.setName("Youssef");
    	reformatedData.setAge(7);
    	reformatedData.setCity("Benton");
     	reformatedData.setGender("male");
    	
    	
        YAMLFileManager yamlFileManager = new YAMLFileManager(filePath); 
	    yamlFileManager.reformatYAMLData(key, reformatedData);

    	 Map<String, Object> newData = new HashMap<>();
//    	    newData.put("name", "jack");
//    	    newData.put("age", 40);
//    	    newData.put("city", "Los Angeles2");
//   	    yamlFileManager.reformatYAMLData(key, reformatedData);	
    }
    
    /////////////////////////////////////////////////////////////
    // reformat data to the YAML file + using CustomObject class to set and get the data
    public static void addYamlDataToYamlFile() {
    	String key="person2";
    	CustomObject reformatedData=new CustomObject();
    	reformatedData.setName("Youssef");
    	reformatedData.setAge(7);
    	reformatedData.setCity("Benton");
     	reformatedData.setGender("male");
    	YAMLFileManager yamlFileManager=new YAMLFileManager("Utils\\newfile.yaml");
    	yamlFileManager.createAndAddToYAMLFile(key, reformatedData);
    	
    	
    	
    }
    
    
    
    
    /////////////////////////////////////////////////////////////////////////
 // Parse YAML data into a Java object (if you have a CustomObject class class representing the data)
    public static void parseYamlDataToJavaObject() {
    	String key="person1";

        YAMLFileManager yamlFileManager = new YAMLFileManager(filePath); 
    
    CustomObject parsedPerson = yamlFileManager.parseYAMLData(key, CustomObject.class);
    if (parsedPerson != null) {
        System.out.println("Parsed Java Object:");
        System.out.println("Name: " + parsedPerson.getName());
        System.out.println("Age: " + parsedPerson.getAge());
        System.out.println("City: " + parsedPerson.getCity());
    } else {
        System.out.println("Failed to parse YAML data into a Java object.");
    }
}
    
    
    /////////////////////////////////////////////////////////////////////////
    public static void createYamlFile() {
        String filePath ="Utils\\newfile.yaml";
        YAMLFileManager yamlFileManager = new YAMLFileManager(filePath); 
        yamlFileManager.createYamlFile(filePath);
    }
    	   
       }
    
    
