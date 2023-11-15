package dataProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

import DataHandlingTests.CustomObject;

public class XMLDataManager {

    private String filePath;

    public XMLDataManager(String filePath) {
        this.filePath = filePath;
    }

    // Read XML data from file
    public Document readXMLFile() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(new File(filePath));
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Retrieve XML data
    public Node retrieveValuesOfXMLData(String xpathExpression) {
        try {
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();
            return (Node) xPath.compile(xpathExpression).evaluate(readXMLFile(), XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
            return null;
        }
    }
    
 // Retrieve XML data as Node based on XPath expression
    public Node retrieveXMLDataAsXMLFormat (String xpathExpression) {
        try {
            Document document = readXMLFile();
            if (document != null) {
                XPathFactory xPathFactory = XPathFactory.newInstance();
                XPath xPath = xPathFactory.newXPath();
                return (Node) xPath.compile(xpathExpression).evaluate(document, XPathConstants.NODE);
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    

    // Search for a specific key-value pair
    public boolean searchXMLData(String key, String value) {
        try {
            XPathFactory xPathFactory = XPathFactory.newInstance();
            javax.xml.xpath.XPath xPath = xPathFactory.newXPath();
            String xpathExpression = String.format("//*[%s='%s']", key, value);
            System.out.println("Constructed XPath: " + xpathExpression);
            return (boolean) xPath.compile(xpathExpression).evaluate(readXMLFile(), XPathConstants.BOOLEAN);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
            return false;
        }
    }

   
    

    // Method to add a CustomObject to the XML file  with overwriting existing data
    public void addCustomObjectToXML(CustomObject customObject, String filePath) {
        try {
            // Create an XmlMapper instance
            XmlMapper xmlMapper = new XmlMapper();

            // Read the existing XML file into a CustomObject array (if needed)
            // For simplicity, let's assume there's an existing array of CustomObjects
            // Here we create a new array containing the provided customObject
            CustomObject[] existingObjects = { customObject };

            // Append the new CustomObject to the existing array
            // (In a real scenario, you would append this to your existing XML structure)
            xmlMapper.writeValue(new FileWriter(filePath), existingObjects);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    

    // Method to add a CustomObject to the XML file without overwriting existing data
    public void appendCustomObjectToXML(CustomObject customObject, String filePath) {
        try {
            // Create an XmlMapper instance
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);

            // Read existing XML data from the file into a List
            List<CustomObject> existingObjects = new ArrayList<>();
            File file = new File(filePath);
            if (file.exists()) {
                CustomObject[] objectsArray = xmlMapper.readValue(file, CustomObject[].class);
                existingObjects.addAll(Arrays.asList(objectsArray));
            }

            // Append the new CustomObject to the existing list
            existingObjects.add(customObject);

            // Write the updated list back to the file
            xmlMapper.writeValue(file, existingObjects);
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
    
    
    

    // Append data to XML file
    public void appendXMLData(String parentXPath, String newNodeName, String newNodeValue) {
        try {
            Document document = readXMLFile();
            XPathFactory xPathFactory = XPathFactory.newInstance();
            javax.xml.xpath.XPath xPath = xPathFactory.newXPath();
            Node parentNode = (Node) xPath.compile(parentXPath).evaluate(document, XPathConstants.NODE);

            if (parentNode != null) {
                Element newNode = document.createElement(newNodeName);
                newNode.appendChild(document.createTextNode(newNodeValue));
                parentNode.appendChild(newNode);

                saveXMLToFile(document);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    // Parse XML data into a Java object

    public CustomObject parseXMLData(String xmlString) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(xmlString)));

            Element rootElement = document.getDocumentElement();
            NodeList personNodes = rootElement.getElementsByTagName("person");

            if (personNodes.getLength() > 0) {
                Node personNode = personNodes.item(0);
                if (personNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element personElement = (Element) personNode;

                    String name = getElementValue(personElement, "name");
                    int age = Integer.parseInt(getElementValue(personElement, "age"));
                    String city = getElementValue(personElement, "city");

                    return new CustomObject(name, age, city);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getElementValue(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return null;
    }

    // Create a new XML file
    public void createXMLFile() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            Element rootElement = document.createElement("root");
            document.appendChild(rootElement);

            saveXMLToFile(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Delete XML file
    public void deleteXMLFile() {
        try {
            File file = new File(filePath);
            if (file.exists() && file.delete()) {
                System.out.println("XML file deleted: " + filePath);
            } else {
                System.out.println("Failed to delete XML file: " + filePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    // Save XML document to file
    private void saveXMLToFile(Document document) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);
            System.out.println("XML data saved to file: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
