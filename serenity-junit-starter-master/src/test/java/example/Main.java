package example;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class Main {
    public static void main(String[] args) {
        try {
            // Create a Family instance with a nested Person
            PrestaShop presta = new PrestaShop();
            Address address = new Address("John", 30);
            presta.setAddress(address);

            // Create a JAXB context for the Family class
            JAXBContext context = JAXBContext.newInstance(PrestaShop.class);

            // Create a marshaller
            Marshaller marshaller = context.createMarshaller();

            // Marshal the Family object to XML and print it
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(presta, System.out);

            String s= presta.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}