package starter.actions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.serenitybdd.core.steps.UIInteractions;
import starter.prestashop.SwaggerRegisterObjects;


import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matchers;

import dataProvider.ConfigReader;

import static net.serenitybdd.rest.SerenityRest.*;


public class SwaggerRegisterActions extends UIInteractions {
	@Given("Guest can register")
	public int givenGuestCanCreateNewAccount() {
		
		SwaggerRegisterObjects sr =new SwaggerRegisterObjects( "eve.holt@reqres.in","pistol");

		int newId = given()
	                .baseUri("https://reqres.in")
	                .basePath("/api/register")
	                .body(sr, ObjectMapperType.GSON)
	                .accept(ContentType.JSON)
	                .contentType(ContentType.JSON).post().getBody().as(SwaggerRegisterObjects.class, ObjectMapperType.GSON).getId();
		 System.out.println(newId);
		 return newId;
	    }

	   @When("I ask for a user using ID: {0}")
	    public void whenIAskForAUserWithId(int id) {
	        when().get("/" + id);
	    }

	    @Then("I get the user as result")
	    public void thenISeeUserAsResult() {
	        then().body("first_name", Matchers.equalTo("eve"));
	    }

		
		
		
	}
   
	
	
	


