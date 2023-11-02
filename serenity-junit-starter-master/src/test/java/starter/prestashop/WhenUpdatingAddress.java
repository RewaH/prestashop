package starter.prestashop;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import starter.actions.PrestaShopApiActions;
import starter.actions.prestaShopUpdateAddressActions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SerenityJUnit5Extension.class)
public class WhenUpdatingAddress {

    prestaShopUpdateAddressActions prestaUpdateApi;
    
    @Test
    public void updateExistingAddress() {
         prestaUpdateApi.givenUserCanUpdateAnExsitingAddress(114);
         prestaUpdateApi.thenIGotTheUpdatedAddressAsResult();
    
}}