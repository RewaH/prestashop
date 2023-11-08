package DataHandlingTests;
import java.io.IOException;
import java.util.List;
import com.google.gson.JsonObject;
import dataProvider.JSONFileManager;

public class ManageDataFromJSONFile {
	static String filePath ="Utils\\file.json";
    public static void main(String[] args) throws IOException {

    	//readJsonFile();
    	//retrieveJsonObject();
    	//searchForDataByKey();
    	//addDataToJsonFile();
    	//appendData();
    	//reformatData();
    	//parseJsonDatatoJavaObject();
    	//convertJsonToXml();
    
    	//createFile();
    	deleteFile();
    }
    
    
    
    public static void readJsonFile() {
    	JSONFileManager jsonFileManager = new JSONFileManager(filePath);
    	JsonObject retrievedData = jsonFileManager.readData();
        System.out.println(retrievedData);

    }
    
 /////////////////////////////////////////////////////   
    
    public static void retrieveJsonObject() {
    	JSONFileManager jsonFileManager = new JSONFileManager(filePath);
    	JsonObject retrievedData = jsonFileManager.retrieveData("person1");
        System.out.println(retrievedData);

    }
/////////////////////////////////////////////////////   
    
    public static void searchForDataByKey() {
        JSONFileManager jsonFileManager = new JSONFileManager(filePath);
        List<String> searchResults = jsonFileManager.searchData("person");
        System.out.println("Search results: " + searchResults);

}
/////////////////////////////////////////////////////   
    
public static void addDataToJsonFile() {
	//CreatejsonObject
	JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("name", "Rewaa");
    jsonObject.addProperty("age", 30);
    jsonObject.addProperty("city", "Bentonville");
    
	String key ="person4";

JSONFileManager jsonFileManager = new JSONFileManager(filePath);
jsonFileManager.addData(key, jsonObject);
JsonObject retrievedData=jsonFileManager.retrieveData(key);
System.out.println(retrievedData);

}
///////////////////////////////////////////////////////////////////

//append data then use search for value method to ensure it is appended
public static void appendData() {
	JSONFileManager jsonFileManager = new JSONFileManager(filePath);
	String key ="person1";
	String subKey="gender";
	String newValue = "Alexandria"; 

    jsonFileManager.appendData(key, subKey, newValue);
    
    List<String> searchResults = jsonFileManager.searchDataByValue(newValue);
    for (String result : searchResults) {
        System.out.println("Value '" + newValue + "' found in object: " + result);
    }    


}

/////////////////////////////////////////////////////////////////////////////////

//reformat the data which means add new key and new value to the object
public static void reformatData() {
	JSONFileManager jsonFileManager = new JSONFileManager(filePath);
	        String key ="person4";
			String subKey="gender";
			String keyValue="female";
  jsonFileManager.addKeyValueToData(key,subKey, keyValue);
  JsonObject retrievedData=jsonFileManager.retrieveData(key);
  System.out.println(retrievedData);

}

//////////////////////////////////////////////////////////////////////
////Parse a JSON object into a Java object
public static void parseJsonDatatoJavaObject() {
    String key ="person4";

JSONFileManager jsonFileManager = new JSONFileManager(filePath); // Provide the correct file path
CustomObject parsedObject = jsonFileManager.parseData(key,CustomObject.class );

if (parsedObject != null) {
    System.out.println("Successfully parsed the JSON object:");
    System.out.println("Name: " + parsedObject.getName());
    System.out.println("Age: " + parsedObject.getAge());
    System.out.println("city: " + parsedObject.getCity());
    System.out.println("gender: " + parsedObject.getGender());

    // Add more properties as needed
} else {
    System.out.println("Failed to parse the JSON object.");
}
}


//////////////////////////////////////////////////////////////////////
////Parse a JSON object into XML
public static void convertJsonToXml() {
	JSONFileManager jsonFileManager = new JSONFileManager(filePath); 

	String xmlData = jsonFileManager.convertJSONToXML();

	if (xmlData != null) {
	    System.out.println("Converted XML Data:");
	    System.out.println(xmlData);
	} else {
	    System.out.println("Failed to read JSON data from the file or convert it to XML.");
	}
}
	

//////////////////////////////////////////////////////////////////////
////Create json file and insert data
public static void createFile() {
	JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("name", "Rewaa");
    jsonObject.addProperty("age", 30);
    jsonObject.addProperty("city", "Bentonville");
    
	String key ="person4";

JSONFileManager jsonFileManager = new JSONFileManager("Utils\\newfile.json");
//String jsonData = "{\"name\":\"John\",\"age\":30,\"city\":\"New York\"}";
//jsonFileManager.createJSONFile("Utils\\newfile.json", jsonData);
 //jsonFileManager.addData(key, jsonObject);
 jsonFileManager.createJSONFile("Utils\\newfile.json", jsonObject.toString());

//JsonObject retrievedData=jsonFileManager.retrieveData(key);
//System.out.println(retrievedData);
//  

	
	
	
}


//////////////////////////////////////////////////////////////////////
////Create json file and insert data
public static void deleteFile() {
	JSONFileManager jsonFileManager = new JSONFileManager("Utils\\newfile.json"); // Provide the file path

	jsonFileManager.deleteJSONFile("Utils\\newfile.json");
	
	
	
	
	
}


}