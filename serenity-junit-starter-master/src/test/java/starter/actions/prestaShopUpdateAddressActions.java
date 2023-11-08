package starter.actions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import net.serenitybdd.core.steps.UIInteractions;
import starter.pageObjects.AddressesObjects;
import starter.pageObjects.PrestaShopObjects;
import starter.pageObjects.SwaggerRegisterObjects;

import static org.hamcrest.Matchers.equalTo;

import javax.xml.bind.JAXBException;

import org.hamcrest.Matchers;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import dataProvider.ConfigReader;
import dataProvider.XMLMarshaller;
import dataProvider.XMLRequestGenerator;

import static net.serenitybdd.rest.SerenityRest.*;

public class prestaShopUpdateAddressActions extends UIInteractions  {
	
	@Given("User can updat an Address")
	public void givenUserCanUpdateAnExsitingAddress(int id) throws JAXBException {
		ConfigReader configReader = ConfigReader.getInstance();

		String baseUrl=configReader.getBaseUrl();
		String authorization=configReader.getAuthorization();
		String cookie=configReader.getcookie();

		PrestaShopObjects preObj=new PrestaShopObjects();
		AddressesObjects addObj=new AddressesObjects();
		addObj.setId(114);
		addObj.setIdCountry(30);
		addObj.setAlias("mrs");
		addObj.setLastName("Hamd");
		addObj.setFirstName("Rewaa");
		addObj.setAddress1("123 Green house");
		addObj.setCity("Bentonville");
		preObj.setAddress(addObj);
	String xmlRequest=XMLMarshaller.marshalToXml(preObj);
	String requestBody =(xmlRequest);	

//		XmlMapper xmlMapper = new XmlMapper();
//		String xmlBody = xmlMapper.writeValueAsString(addObj);
//		String requestBody =(xmlBody);	

		
		

//		XMLRequestGenerator xmlRequestGenerator=new XMLRequestGenerator();
//		String requestBody=xmlRequestGenerator.createXml();
//		System.out.println("The request is" +requestBody);

		
		
//		String requestBody = ("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n<prestashop xmlns:xlink=\"http://www.w3.org/1999/xlink\">\r\n    <address>\r\n        <id>114</id>\r\n        <id_customer></id_customer>\r\n        <id_manufacturer></id_manufacturer>\r\n        <id_supplier></id_supplier>\r\n        <id_warehouse></id_warehouse>\r\n        <id_country>30</id_country>\r\n        <id_state>7</id_state>\r\n        <alias>mr</alias>\r\n        <company>RED</company>\r\n        <lastname>Hamed</lastname>\r\n        <firstname>Rewaa</firstname>\r\n        <vat_number>1234</vat_number>\r\n        <address1>123 Green street</address1>\r\n        <address2>Apt.6</address2>\r\n        <postcode>1234</postcode>\r\n        <city>Benton</city>\r\n        <other>Create new Address</other>\r\n        <phone>1234567</phone>\r\n        <phone_mobile>23456789</phone_mobile>\r\n        <dni></dni>\r\n        <deleted></deleted>\r\n        <date_add></date_add>\r\n        <date_upd></date_upd>\r\n    </address>\r\n</prestashop>")
//				.toString();
	System.out.println("The request is" +xmlRequest);

		Response response= given()
				 .baseUri(baseUrl)
	             .basePath("/api/addresses/"+id+"/")
				.header("output_format", "JSON")
				.header("Authorization", authorization)
				.header("Cookie",cookie)
                .body(requestBody).put();
        System.out.println(response); 
        
        
    }

 
	@Then("I got the updated address as a result")
	public void thenIGotTheUpdatedAddressAsResult() {
		
Response respnse=then().statusCode(200)
.body("address.id_country", equalTo("30"))
.body("address.firstname", equalTo("Rewaa")).extract().response();
		
		System.out.println(respnse);
    }
	
	
   
}




