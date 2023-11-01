package starter.prestashop;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import starter.actions.PrestaShopApiActions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SerenityJUnit5Extension.class)
public class WhenCreatingAndFetchingAddresses {

    PrestaShopApiActions prestaApi;
    
    @Test
    public void createAndFetchAddress() {
         prestaApi.givenUserCanCreateNewAddress();
        prestaApi.whenUserCanRetriveAddressWithId(113);
       prestaApi.thenIGotTheAddressAsAresult();
    
}}