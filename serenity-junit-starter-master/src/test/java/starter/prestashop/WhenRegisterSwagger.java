package starter.prestashop;

import org.junit.jupiter.api.Test;

import starter.actions.SwaggerRegisterActions;
import starter.actions.prestaShopUpdateAddressActions;

public class WhenRegisterSwagger {
	 SwaggerRegisterActions requreReg;
	    
	    @Test
	    public void createNewAccount() {
	         int id=requreReg.givenGuestCanCreateNewAccount();
	        String email= requreReg.whenIAskForAUserWithId(id);
	         requreReg.thenISeeUserAsResult(email);
	    
	}
	

}
