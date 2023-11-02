package starter.actions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import net.serenitybdd.core.steps.UIInteractions;

import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matchers;

import static net.serenitybdd.rest.SerenityRest.*;

public class prestaShopUpdateAddressActions extends UIInteractions  {
	
	@Given("User can updat an Address")
	public void givenUserCanUpdateAnExsitingAddress(int id) {
		


		
		

		String requestBody = ("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n<prestashop xmlns:xlink=\"http://www.w3.org/1999/xlink\">\r\n    <address>\r\n        <id>114</id>\r\n        <id_customer></id_customer>\r\n        <id_manufacturer></id_manufacturer>\r\n        <id_supplier></id_supplier>\r\n        <id_warehouse></id_warehouse>\r\n        <id_country>30</id_country>\r\n        <id_state>7</id_state>\r\n        <alias>mr</alias>\r\n        <company>RED</company>\r\n        <lastname>Hamed</lastname>\r\n        <firstname>Rewaa</firstname>\r\n        <vat_number>1234</vat_number>\r\n        <address1>123 Green street</address1>\r\n        <address2>Apt.6</address2>\r\n        <postcode>1234</postcode>\r\n        <city>Benton</city>\r\n        <other>Create new Address</other>\r\n        <phone>1234567</phone>\r\n        <phone_mobile>23456789</phone_mobile>\r\n        <dni></dni>\r\n        <deleted></deleted>\r\n        <date_add></date_add>\r\n        <date_upd></date_upd>\r\n    </address>\r\n</prestashop>")
				.toString();

		String response= given()
				 .baseUri("http://157.245.137.70")
	             .basePath("/api/addresses/"+id+"/")
				.header("Content-Type", "text/plain")
				.header("Authorization", "Basic NjZENlROUzgzNFQ3QUZTTUxaMUlSTkdJWUM1SU1CWDM6")
				.header("Cookie",
						"PrestaShop-0ffc98a015b67482f8ca6d906509b40a=def5020005f1a739c3221419927faa7838f81e02ad41b6bd4ad9984acd237613f4c9181dbd818baf13a6b3e22d803ed9447d27ff963f872a2893497be1fb25e83f6015ef89d82617f79c78ec4b1a4f956d2681000b1854aca53bcc49136fa58cdef178f279d78ca3d40acf169124f65ba7c6d88ea333d8cf9b5745e0450cbce264619efe79e774be155583d2c4d05269dcc37828d0f2b1d2052e5eaff4e41e940f31")
               
                .body(requestBody).put().getBody().asString();
        System.out.println(response); 
        
        
    }

	

   

   
  
	@Then("I got the updated address as a result")
	public void thenIGotTheUpdatedAddressAsResult() {
		
Response respnse=then().statusCode(200)
.body("prestashop.address.id_country", equalTo("30"))
.body("prestashop.address.firstname", equalTo("Rewaa")).extract().response();
		
		System.out.println(respnse);
    }
	
	
   
}



