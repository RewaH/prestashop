package dataProvider;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamResult;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import starter.pageObjects.AddressesObjects;

	@XmlRootElement
	public class XMLRequestGenerator {
String doc;
    public  String createXml() {
        try {
            // Create a new XML document
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            // Create the root element
            Element prestashopElement = document.createElement("prestashop");
            prestashopElement.setAttribute("xmlns:xlink", "http://www.w3.org/1999/xlink");
            document.appendChild(prestashopElement);

            // Create the "address" element
            Element prestashop = document.createElement("prestashop");
            Element addressElement = document.createElement("address");
            prestashop.appendChild(prestashop);
            prestashop.appendChild(addressElement);
            // Create child elements and set their values
            addressElement.appendChild(createElementWithTextContent(document, "id", "114"));
            addressElement.appendChild(createElementWithTextContent(document, "id_country", "30"));
            addressElement.appendChild(createElementWithTextContent(document, "alias", "mrs"));
            addressElement.appendChild(createElementWithTextContent(document, "lastname", "Hamd"));
            addressElement.appendChild(createElementWithTextContent(document, "firstname", "Rewa"));
            addressElement.appendChild(createElementWithTextContent(document, "address1", "123 Green street"));
            addressElement.appendChild(createElementWithTextContent(document, "city", "Benton"));

            // Print the XML document
            System.out.println(transformDocumentToString(document));
         doc= transformDocumentToString(document);
           
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
		
       return doc;
    }

    // Helper method to create an element with text content
    private static Element createElementWithTextContent(Document document, String tagName, String textContent) {
        Element element = document.createElement(tagName);
        element.appendChild(document.createTextNode(textContent));
        return element;
    }

    // Helper method to convert the Document to a String
    private static String transformDocumentToString(Document document) {
        try {
            javax.xml.transform.TransformerFactory transformerFactory = javax.xml.transform.TransformerFactory.newInstance();
            javax.xml.transform.Transformer transformer = transformerFactory.newTransformer();
            javax.xml.transform.dom.DOMSource source = new javax.xml.transform.dom.DOMSource(document);
            java.io.StringWriter writer = new java.io.StringWriter();
            javax.xml.transform.stream.StreamResult result = new javax.xml.transform.stream.StreamResult(writer);
            transformer.transform(source, result);
            return writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
