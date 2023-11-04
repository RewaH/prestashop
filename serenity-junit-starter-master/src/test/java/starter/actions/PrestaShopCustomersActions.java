package starter.actions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.serenitybdd.core.steps.UIInteractions;
import starter.pageObjects.AddressesObjects;
import starter.pageObjects.SwaggerRegisterObjects;

import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matchers;

import dataProvider.ConfigReader;

import static net.serenitybdd.rest.SerenityRest.*;

public class PrestaShopCustomersActions extends UIInteractions {
	private int newCustomerId;
	private String customerfirstName;
	@Given("User can create new Customer")
	
	public void givenUserCanCreateNewCustomer() {
        ConfigReader configReader = ConfigReader.getInstance();

		String baseUrl=configReader.getBaseUrl();
		String authorization=configReader.getAuthorization();
		String cookie=configReader.getcookie();

		
        String requestBody = ("<prestashop xmlns:xlink=\"http://www.w3.org/1999/xlink\">\n  <customer>\n    <passwd>123456</passwd>\n    <lastname>HAMED</lastname>\n    <firstname>REnew</firstname>\n    <email>RHnew@gmail.com</email>\n    <active>1</active>\n  </customer>\n</prestashop>")
				.toString();

		Response response= given()
				 .baseUri(baseUrl)
	             .basePath("/api/customers/")
	             .header("output_format","JSON")
				.header("Authorization",authorization)
				.header("Cookie",cookie)
                .body(requestBody).post();
        System.out.println(response.getStatusCode()); 
    	JsonPath jsonPathEvaluator = response.jsonPath();
        newCustomerId=jsonPathEvaluator.getInt("customer.id");
        customerfirstName=jsonPathEvaluator.getString("customer.firstname");
        
        System.out.println(newCustomerId);
//        newId=response.getBody().as(AddressesObjects.class,ObjectMapperType.GSON).getId();
//    	System.out.println("this is newId "+newId);
   }

	@When("User can retrive a spacific customer with id {0}")

	public void whenUserCanRetriveCustomerWithId() {
		when().get("/" + newCustomerId);
			
    
	}


   
  // TODO add then flow 
	@Then("I got the customer as a result with id{0}")
	public void thenIGotTheCustomerAsAresult() {
Response respnse=	then().statusCode(200).body("customer.id", equalTo(newCustomerId))
.body("customer.firstname", equalTo(customerfirstName)).extract().response();
		
		System.out.println(respnse.getStatusCode());

    } 
   
}














