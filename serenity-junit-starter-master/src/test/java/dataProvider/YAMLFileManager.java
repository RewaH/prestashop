package dataProvider;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.constructor.SafeConstructor;

import com.fasterxml.jackson.databind.ObjectMapper;

import DataHandlingTests.CustomObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

public class YAMLFileManager {
    private String filePath;

    public YAMLFileManager(String filePath) {
        this.filePath = filePath;
    }
    
    //////////////////////////////////////////////////////////////////////
    public Map<String, Object> readYAMLFile() {
        try {
            Yaml yaml = new Yaml();
            try (InputStream inputStream = new FileInputStream(filePath)) {
                return yaml.load(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
  //////////////////////////////////////////////////////////////////////
    public Map<String, Object> retrieveYAMLDataByKey(String key) {
        Map<String, Object> existingData = readYAMLFile();
        if (existingData != null) {
            if (existingData.containsKey(key)) {
                return (Map<String, Object>) existingData.get(key);
            }
        }
        return null;
    }
    //////////////////////////////////////////////////////////////////////

    public boolean searchYAMLData(String key, String value) {
        Map<String, Object> existingData = readYAMLFile();
        if (existingData != null) {
            for (Map.Entry<String, Object> entry : existingData.entrySet()) {
                if (entry.getValue() instanceof Map) {
                    // Check if the key exists in the nested map
                    Map<String, Object> nestedMap = (Map<String, Object>) entry.getValue();
                    if (nestedMap.containsKey(key) && nestedMap.get(key).toString().equals(value)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //////////////////////////////////////////////////////////////////////

    public void appendYAMLData(String key, String subKey, String value) {
        Map<String, Object> existingData = readYAMLFile();
        if (existingData != null && existingData.containsKey(key)) {
            Map<String, Object> yamlData = (Map<String, Object>) existingData.get(key);
            yamlData.put(subKey, value);
            addDataToYAMLFile(key, yamlData);
        }
    }

    //////////////////////////////////////////////////////////////////////
// to map  the data from Map<String, Object> 
    public void reformatYAMLData(String key, Map<String, Object> newData) {
        Map<String, Object> existingData = readYAMLFile();
        if (existingData != null && existingData.containsKey(key)) {
            existingData.put(key, newData);
            addDataToYAMLFile(key, newData);
        }
    }
    //////////////////////////////////////////////////////////////////////
 // to map  the data from CustomObject class 
     public void reformatYAMLData(String key, CustomObject newData) {
    	 Map<String, Object> existingData = readYAMLFile();
    	    if (existingData != null && existingData.containsKey(key)) {
    	        // Convert the custom object to a Map
    	        Map<String, Object> newDataMap = customObjectToMap(newData);

    	        // Update the data in the YAML file under the specified key
    	        existingData.put(key, newDataMap);
                addDataToYAMLFile(key, newDataMap);

    	        
    	    }
    	}
    //////////////////////////////////////////////////////////////////////
    // add Data to yaml file
    public void addDataToYAMLFile(String key, Map<String, Object> yamlData) {
        try {
            Yaml yaml = new Yaml();
            Map<String, Object> existingData = readYAMLFile();

            if (existingData == null) {
                existingData = yamlData;
            } else {
                existingData.put(key, yamlData);
            }

            DumperOptions options = new DumperOptions();
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            yaml = new Yaml(options);
            
            try (Writer writer = new FileWriter(filePath)) {
                yaml.dump(existingData, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ////////////////////////////////////////////////////
    }
    public void createAndAddToYAMLFile(String key, CustomObject yamlData) {
        try {
            Map<String, Object> existingData = readYAMLFile();

            if (existingData == null) {
                existingData = new LinkedHashMap<>();
            }

	        Map<String, Object> newDataMap = customObjectToMap(yamlData);

            // Add or update the data under the specified key
            existingData.put(key, newDataMap);

            DumperOptions options = new DumperOptions();
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            Yaml yaml = new Yaml(options);

            try (Writer writer = new FileWriter(filePath)) {
                yaml.dump(existingData, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    
    
    
    private Map<String, Object> customObjectToMap(CustomObject yamlData) {
        Map<String, Object> newDataMap = new LinkedHashMap<>();
        newDataMap.put("name", yamlData.getName());
        newDataMap.put("age", yamlData.getAge());
        newDataMap.put("city", yamlData.getCity());
        newDataMap.put("gender", yamlData.getGender());

        return newDataMap;
    }
    
    public <T> T parseYAMLData(String key, Class<T> clazz) {
        Map<String, Object> existingData = readYAMLFile();
        if (existingData != null && existingData.containsKey(key)) {
            Object data = existingData.get(key);
            
            SafeConstructor constructor = new SafeConstructor();
            
            Yaml yaml = new Yaml(constructor);
            String dataString = yaml.dump(data);
            return new Yaml().loadAs(dataString, clazz);
        }
        return null;
    }
    /////////////////////////////////////////////////////////////////// 
    public void createYamlFile(String filePath) {
        try {
            // Create a new file
            File file = new File(filePath);

//            // Check if the file already exists, and delete it if needed
//            if (file.exists()) {
//                file.delete();
//            }

            // Create a FileWriter to write the JSON data to the file
            FileWriter fileWriter = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
    

