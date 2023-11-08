package starter.prestashop;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import starter.actions.PrestaShopAddressesActions;
import starter.actions.prestaShopUpdateAddressActions;

import javax.xml.bind.JAXBException;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SerenityJUnit5Extension.class)
public class WhenUpdatingAddress {

    prestaShopUpdateAddressActions prestaUpdateApi;
    
    @Test
    public void updateExistingAddress() throws JAXBException {
         prestaUpdateApi.givenUserCanUpdateAnExsitingAddress(114);
         prestaUpdateApi.thenIGotTheUpdatedAddressAsResult();
    
}}