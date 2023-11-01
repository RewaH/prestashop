package starter.prestashop;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SerenityJUnit5Extension.class)
public class WhenFetchingAlreadyAvailablePet {

    Long newPetId = null;
    PetApiActions petApi;

    @Test
    public void fetchAlreadyAvailablePet() {
         petApi.givenUserCanCreateNewAddress();
        petApi.whenUserCanRetriveAddressWithId(113);
       //.thenISeeKittyAsResult();
    
}}