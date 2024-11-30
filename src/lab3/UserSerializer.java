package lab3;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import lab3.models.User;

/**
 * Serializes a User object to different formats (JSON and XML).
 */
public class UserSerializer {

  public String toJSON(User user) throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.writeValueAsString(user);
  }

  public String toXML(User user) throws Exception {
    JAXBContext context = JAXBContext.newInstance(User.class);
    Marshaller marshaller = context.createMarshaller();
    java.io.StringWriter writer = new java.io.StringWriter();
    marshaller.marshal(user, writer);
    return writer.toString();
  }
}
