package starter.prestashop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import starter.actions.SwaggerRegisterActions;
import starter.actions.prestaShopUpdateAddressActions;

@ExtendWith(SerenityJUnit5Extension.class)
public class WhenRegisterSwagger {
	 SwaggerRegisterActions requreReg;
	    
	    @Test
	    public void createNewAccount() {
	         int id=requreReg.givenGuestCanCreateNewAccount();
	        String email= requreReg.whenIAskForAUserWithId(id);
	         requreReg.thenISeeUserAsResult(email);
	    
	}
	

}
