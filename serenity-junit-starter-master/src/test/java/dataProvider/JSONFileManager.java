package dataProvider;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class JSONFileManager {
    private String filePath;
    private Gson gson;

    public JSONFileManager(String filePath) {
        this.filePath = filePath;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }
/////////////////////////////////////////////////////////////////////

    // Read the entire JSON file
    public JsonObject readData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            JsonParser parser = new JsonParser();
            return parser.parse(reader).getAsJsonObject();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
/////////////////////////////////////////////////////////////////////

    // Retrieve a specific JSON object by key
    public JsonObject retrieveData(String key) {
        JsonObject data = readData();
        if (data != null && data.has(key)) {
            return data.get(key).getAsJsonObject();
        }
        return null;
    }
    
    
//////////////////////////////////////////////////////////////
    // Search for a JSON object by key
    public List<String> searchData(String searchKey) {
        JsonObject data = readData();
        List<String> results = new ArrayList<>();

        if (data != null) {
            for (String key : data.keySet()) {
                if (key.contains(searchKey)) {
                    results.add(key);
                }
            }
        }
        return results;
    }
/////////////////////////////////////////////////////////////////////
 // Search for data by value
    public List<String> searchDataByValue(String searchValue) {
        JsonObject data = readData();
        List<String> results = new ArrayList<>();

        if (data != null) {
            for (Entry<String, JsonElement> entry : data.entrySet()) {
                JsonObject jsonObject = entry.getValue().getAsJsonObject();
                for (Entry<String, JsonElement> property : jsonObject.entrySet()) {
                    if (property.getValue().isJsonPrimitive()) {
                        String value = property.getValue().getAsString();
                        if (value.equals(searchValue)) {
                            results.add(entry.getKey()); // Add the key where the value was found
                            break; // No need to continue searching in this object
                        }
                    }
                }
            }
        }
        return results;
    }

////////////////////////////////////////////////////////////////////////
    // Add a new JSON object
    public boolean addData(String key, JsonObject newData) {
        JsonObject data = readData();
        if (data != null) {
            data.add(key, newData);
            return writeData(data);
        }
        return false;
    }
/////////////////////////////////////////////////////////////////////

    // Append data to an existing JSON object
    public boolean appendData(String key, String subKey, String appendedData) {
        JsonObject data = readData();
        if (data != null && data.has(key)) {
            JsonObject existingObject = data.get(key).getAsJsonObject();
            existingObject.addProperty(subKey, appendedData);
            return writeData(data);
        }
        return false;
    }

 // Reformat a specific JSON object by adding a new key-value pair
 // Add a new key-value pair to a specific JSON object
    public boolean addKeyValueToData(String key, String newKey, String newValue) {
        JsonObject data = readData();
        if (data != null && data.has(key)) {
            JsonObject jsonObject = data.get(key).getAsJsonObject();
            jsonObject.addProperty(newKey, newValue);
            return writeData(data);
        }
        return false;
    }
    
    
///////////////////////////////////////////////////////////////////////////
//// Parse a JSON object into a Java object
    public <T> T parseData(String key, Class<T> clazz) {
        JsonObject data = retrieveData(key);
        if (data != null) {
            return gson.fromJson(data, clazz);
        }
        return null;
    }


    
    public String convertJSONToXML() {
    	 try {
             // Create a Jackson ObjectMapper for JSON
             ObjectMapper jsonMapper = new ObjectMapper();

             // Read JSON data from the file
             JsonNode jsonNode = jsonMapper.readTree(new File(filePath));

             // Create an XmlMapper for XML conversion
             XmlMapper xmlMapper = new XmlMapper();

             // Convert JSON data to XML
             return xmlMapper.writeValueAsString(jsonNode);
         } catch (IOException e) {
             e.printStackTrace();
             return null;
         }
     }
    //////////////////////////////////////////////////////////////////////
    // Create a new file

    public void createJSONFile(String filePath, String jsonData) {
        try {
            // Create a new file
            File file = new File(filePath);

//            // Check if the file already exists, and delete it if needed
//            if (file.exists()) {
//                file.delete();
//            }

            // Create a FileWriter to write the JSON data to the file
            FileWriter fileWriter = new FileWriter(file);

            // Write the JSON data to the file
            fileWriter.write(jsonData);

            // Close the FileWriter
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /////////////////////////////////////////////////////////////////////////////
    // delete a File 
    public void deleteJSONFile(String filePath) {
        try {
            File file = new File(filePath);

            // Check if the file exists and delete it
            if (file.exists()) {
                file.delete();
                System.out.println("File deleted successfully.");
            } else {
                System.out.println("File does not exist.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    
 ////////////////////////////////////////////////////////////////////////////////
    
    // Write JSON data back to the file
   //**********************************//
    private boolean writeData(JsonObject data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(gson.toJson(data));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    }


    

