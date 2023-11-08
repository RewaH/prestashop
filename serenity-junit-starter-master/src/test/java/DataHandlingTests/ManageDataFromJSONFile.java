package DataHandlingTests;
import java.io.IOException;
import java.util.List;
import com.google.gson.JsonObject;
import dataProvider.JSONFileManager;

public class ManageDataFromJSONFile {
    public static void main(String[] args) throws IOException {

    	//readJsonFile();
    	//retrieveJsonObject();
    	//searchForDataByKey();
    	//addDataToJsonFile();
    	appendData();
    }
    
    
    
    public static void readJsonFile() {
    	JSONFileManager jsonFileManager = new JSONFileManager("Utils\\file.json");
    	JsonObject retrievedData = jsonFileManager.readData();
        System.out.println(retrievedData);

    }
    
 /////////////////////////////////////////////////////   
    
    public static void retrieveJsonObject() {
    	JSONFileManager jsonFileManager = new JSONFileManager("Utils\\file.json");
    	JsonObject retrievedData = jsonFileManager.retrieveData("person1");
        System.out.println(retrievedData);

    }
/////////////////////////////////////////////////////   
    
    public static void searchForDataByKey() {
        JSONFileManager jsonFileManager = new JSONFileManager("Utils\\file.json");
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
    
JSONFileManager jsonFileManager = new JSONFileManager("Utils\\file.json");
jsonFileManager.addData("person4", jsonObject);
JsonObject retrievedData=jsonFileManager.retrieveData("person4");
System.out.println(retrievedData);

}
///////////////////////////////////////////////////////////////////

//append data then use search for value method to ensure it is appended
public static void appendData() {
	JSONFileManager jsonFileManager = new JSONFileManager("Utils\\file.json");
    String newValue = "Alexandria"; 

    jsonFileManager.appendData("person1", "city", newValue);
    
    List<String> searchResults = jsonFileManager.searchDataByValue(newValue);
    for (String result : searchResults) {
        System.out.println("Value '" + newValue + "' found in object: " + result);
    }    


}

}
