package starter.actions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.serenitybdd.core.steps.UIInteractions;
import starter.prestashop.PrestaShopObjects;


import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matchers;

import dataProvider.ConfigReader;

import static net.serenitybdd.rest.SerenityRest.*;

public class PrestaShopApiActions extends UIInteractions {
	int newAddressId;
	@Given("User can create new Address")
	public void givenUserCanCreateNewAddress() {
        ConfigReader configReader = ConfigReader.getInstance();

		String baseUrl=configReader.getBaseUrl();
		String authorization=configReader.getAuthorization();
		String cookie=configReader.getcookie();

		
        String requestBody = ("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n<prestashop xmlns:xlink=\"http://www.w3.org/1999/xlink\">\r\n    <address>\r\n        <id></id>\r\n        <id_customer></id_customer>\r\n        <id_manufacturer></id_manufacturer>\r\n        <id_supplier></id_supplier>\r\n        <id_warehouse></id_warehouse>\r\n        <id_country>20</id_country>\r\n        <id_state>7</id_state>\r\n        <alias>mrs</alias>\r\n        <company>appzlogic</company>\r\n        <lastname>Hamed</lastname>\r\n        <firstname>Rewa</firstname>\r\n        <vat_number>1234</vat_number>\r\n        <address1>123 Green street</address1>\r\n        <address2>Apt.6</address2>\r\n        <postcode>1234</postcode>\r\n        <city>Benton</city>\r\n        <other>Create new Address</other>\r\n        <phone>1234567</phone>\r\n        <phone_mobile>23456789</phone_mobile>\r\n        <dni></dni>\r\n        <deleted></deleted>\r\n        <date_add></date_add>\r\n        <date_upd></date_upd>\r\n    </address>\r\n</prestashop>")
				.toString();

		Response response= given()
				 .baseUri(baseUrl)
	             .basePath("/api/addresses/")
	             .header("output_format","JSON")
				.header("Authorization",authorization)
				.header("Cookie",cookie)
                .body(requestBody).post();
        System.out.println(response.getStatusCode()); 
    	JsonPath jsonPathEvaluator = response.jsonPath();

        newAddressId=jsonPathEvaluator.getInt("address.id");
        System.out.println(newAddressId);
        
    }

	@When("User can retrive a spacific address with id {0}")

	public void whenUserCanRetriveAddressWithId(int id) {
		when().get("/" + newAddressId);
			
    
	}

   

   
  // TODO add then flow 
	@Then("I got the address as a result with id{0}")
	public void thenIGotTheAddressAsAresult() {
Response respnse=	then().statusCode(200).body("address.id", equalTo(newAddressId))
.body("address.firstname", equalTo("Rewa")).extract().response();
		
		System.out.println(respnse.getStatusCode());

    } 
   
}














