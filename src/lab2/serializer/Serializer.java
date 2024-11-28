package lab2.serializer;

import lab2.annotations.FieldColumn;
import lab2.annotations.FieldMapping;

import java.lang.reflect.Field;

/**
 * Provides methods to serialize and deserialize objects to and from JSON format.
 */
public class Serializer {

  public String toJSON(Object obj) {
    StringBuilder json = new StringBuilder("{");
    Class<?> clazz = obj.getClass();

    if (clazz.isAnnotationPresent(FieldMapping.class)) {
      FieldMapping mapping = clazz.getAnnotation(FieldMapping.class);
      for (FieldColumn fieldColumn : mapping.fieldToColumn()) {
        try {
          Field field = clazz.getDeclaredField(fieldColumn.field());
          field.setAccessible(true);
          Object value = field.get(obj);
          json.append("\"").append(fieldColumn.column()).append("\": ")
              .append(value instanceof String ? "\"" + value + "\"" : value)
              .append(", ");
        } catch (NoSuchFieldException | IllegalAccessException e) {
          e.printStackTrace();
        }
      }
      // Видалити останню кому
      if (json.length() > 1) {
        json.setLength(json.length() - 2);
      }
    }

    json.append("}");
    return json.toString();
  }

  public String toXML(Object obj) {
    StringBuilder xml = new StringBuilder("<Object>");
    Class<?> clazz = obj.getClass();

    if (clazz.isAnnotationPresent(FieldMapping.class)) {
      FieldMapping mapping = clazz.getAnnotation(FieldMapping.class);
      for (FieldColumn fieldColumn : mapping.fieldToColumn()) {
        try {
          Field field = clazz.getDeclaredField(fieldColumn.field());
          field.setAccessible(true);
          Object value = field.get(obj);
          xml.append("<").append(fieldColumn.column()).append(">")
              .append(value)
              .append("</").append(fieldColumn.column()).append(">");
        } catch (NoSuchFieldException | IllegalAccessException e) {
          e.printStackTrace();
        }
      }
    }

    xml.append("</Object>");
    return xml.toString();
  }
}
