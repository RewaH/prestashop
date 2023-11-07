package example;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class Main {
    public static void main(String[] args) {
        try {
            // Create a Family instance with a nested Person
            Family family = new Family();
            Person person = new Person("John", 30);
            family.setPerson(person);

            // Create a JAXB context for the Family class
            JAXBContext context = JAXBContext.newInstance(Family.class);

            // Create a marshaller
            Marshaller marshaller = context.createMarshaller();

            // Marshal the Family object to XML and print it
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(family, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}