package dataProvider;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

public class XMLMarshaller {
    public static String marshalToXml(Object object) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = context.createMarshaller();

        // Convert the object to XML
        StringWriter writer = new StringWriter();
        marshaller.marshal(object, writer);
//System.out.println(writer.toString());
        return writer.toString();
    }
}
