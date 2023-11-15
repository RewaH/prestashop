package DataHandlingTests;
import java.io.IOException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import java.io.FileWriter;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import dataProvider.XMLDataManager;
import dataProvider.XMLMarshaller;


public class ManageDataIntoXMLFile {
	static String filePath ="Utils\\file.xml";
    public static void main(String[] args) throws IOException {

    	//readFromXMLFile();
    	//retrieveValueOfXmlData();
    	retrieveXmlDataAsXMLFormat();
    	//searchXmlData();
    	//addXmlDataToXmlFile();
    
    	//appendDataToXmlFile();
    
    	//parseXmlDataToJavaObject();
    	//createXmlFile();
    	//deleteXmlFile();
    
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // Read XML data from XML file
    public static void readFromXMLFile() {
        XMLDataManager xmlDataManager = new XMLDataManager(filePath);
        Document xmlDocument = xmlDataManager.readXMLFile();
        System.out.println(XMLMarshaller.convertDocumentToString(xmlDocument));

//		 
//	      String body=(XMLMarshaller.convertDocumentToString(xmlDocument));
//
//		 System.out.println(body);

    }
    ///////////////////////////////////////////////////////////////////////////
    // Retrieve XML data from XML file using XPath
    public static void retrieveValueOfXmlData() {
        XMLDataManager xmlDataManager = new XMLDataManager(filePath);
        System.out.println("Retrieve XML data using XPath:");

        Node retrievedNode = xmlDataManager.retrieveValuesOfXMLData("/root/person[1]/name");
        if (retrievedNode != null) {
            System.out.println("Retrieved Node Value: " + retrievedNode.getTextContent());
        }
    }

    
    public static void  retrieveXmlDataAsXMLFormat() {
        XMLDataManager xmlDataManager = new XMLDataManager("Utils\\createNewAddressBody.xml");
        XMLMarshaller xmlMarshaller =new XMLMarshaller();
        Node xmlNode = xmlDataManager.retrieveXMLDataAsXMLFormat("/prestashop");
        if (xmlNode != null) {
            // Print XML node content
        	
            System.out.println(xmlMarshaller.nodeToString(xmlNode));
        }
    }
    
    
 
    
    ///////////////////////////////////////////////////////////////////////////
    // Search for a specific key-value pair

    public static void searchXmlData() {
        XMLDataManager xmlDataManager = new XMLDataManager(filePath);
        boolean searchResult = xmlDataManager.searchXMLData("name", "Alice Smith");
        System.out.println("Search Result: " + searchResult);
    }
    
    
    
   
    	
    	


  
    ///////////////////////////////////////////////////////////////////////////
    // Add data to XML file
    public static void addXmlDataToXmlFile() {

    	 // Create a sample CustomObject instance
        CustomObject customObject = new CustomObject();
        customObject.setName("Rewaa Hamed");
        customObject.setAge(30);
        customObject.setCity("Bentonville");

        // Path to your XML file

        // Create an instance of XMLDataManager
        XMLDataManager xmlDataManager = new XMLDataManager(filePath);

        // Add the CustomObject instance to the XML file
        

       // xmlDataManager.addCustomObjectToXML(customObject, filePath);

        xmlDataManager.appendCustomObjectToXML(customObject, filePath);
    }

    ///////////////////////////////////////////////////////////////////////////
    // append data to XML file
    public static void appendDataToXmlFile() {
        XMLDataManager xmlDataManager = new XMLDataManager(filePath);

        xmlDataManager.appendXMLData("/root/person[last()]", "newAttribute", "123");

    
    
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // parse xml data to java object
    public static void parseXmlDataToJavaObject() {
    	 String xmlData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                 "<root>\n" +
                 "    <person>\n" +
                 "        <name>John Doe</name>\n" +
                 "        <age>25</age>\n" +
                 "        <city>New York</city>\n" +
                 "    </person>\n" +
                 "</root>";
    	  XMLDataManager xmlDataManager = new XMLDataManager(filePath);
          CustomObject customObject = xmlDataManager.parseXMLData(xmlData);

          if (customObject != null) {
              System.out.println("Parsed CustomObject:");
              System.out.println("Name: " + customObject.getName());
              System.out.println("Age: " + customObject.getAge());
              System.out.println("City: " + customObject.getCity());
          } else {
              System.out.println("Failed to parse XML data.");
          }
      }
    
    
    ///////////////////////////////////////////////////////////////////////////
    // parse xml data to java object
    public static void createXmlFile() {
  	  XMLDataManager xmlDataManager = new XMLDataManager("Utils\\newFile.xml");

  	  xmlDataManager.createXMLFile();
  	  
    }
   
    ///////////////////////////////////////////////////////////////////////////
    // parse xml data to java object
    public static void deleteXmlFile() {
  	  XMLDataManager xmlDataManager = new XMLDataManager("Utils\\newFile.xml");

  	  xmlDataManager.deleteXMLFile();
  	  
    }
    
}
