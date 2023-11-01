package starter.petstore;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import net.serenitybdd.core.steps.UIInteractions;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;

public class PetApiActions extends UIInteractions {

    @Given("Kitty is available in the pet store")
    public Long givenKittyIsAvailableInPetStore() {
		return null;
              
    }

    @When("I ask for a pet using Kitty's ID: {0}")
    public void whenIAskForAPetWithId(Long id) {
        
    }

    @Then("I get Kitty as result")
    public void thenISeeKittyAsResult() {
       
    }
}
