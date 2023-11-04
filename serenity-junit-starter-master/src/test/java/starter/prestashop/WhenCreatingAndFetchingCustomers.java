package starter.prestashop;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import starter.actions.PrestaShopAddressesActions;
import starter.actions.PrestaShopCustomersActions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SerenityJUnit5Extension.class)
public class WhenCreatingAndFetchingCustomers {

    PrestaShopCustomersActions prestaApi;
    
    @Test
    public void createAndFetchCustomers() {
         prestaApi.givenUserCanCreateNewCustomer();
        prestaApi.whenUserCanRetriveCustomerWithId();
       prestaApi.thenIGotTheCustomerAsAresult();
    
}}