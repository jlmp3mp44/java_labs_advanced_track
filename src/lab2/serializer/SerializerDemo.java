package lab2.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// Клас для демонстрації серіалізації
@XmlRootElement // Для JAXB
public class SerializerDemo {
  private String name;
  private int age;

  // Конструктор
  public SerializerDemo(String name, int age) {
    this.name = name;
    this.age = age;
  }

  // Без аргументів для JAXB
  public SerializerDemo() {
  }

  @XmlElement
  public String getName() {
    return name;
  }

  @XmlElement
  public int getAge() {
    return age;
  }

  // Метод для демонстрації
  public static void runDemo() {
    SerializerDemo obj = new SerializerDemo("John", 25);

    // JSON серіалізація
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String jsonSerialization = gson.toJson(obj);
    System.out.println("JSON Serialization: " + jsonSerialization);

    // XML серіалізація
    try {
      JAXBContext context = JAXBContext.newInstance(SerializerDemo.class);
      Marshaller marshaller = context.createMarshaller();
      marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

      StringWriter xmlWriter = new StringWriter();
      marshaller.marshal(obj, xmlWriter);
      String xmlSerialization = xmlWriter.toString();
      System.out.println("XML Serialization: " + xmlSerialization);
    } catch (JAXBException e) {
      e.printStackTrace();
    }
  }
}
