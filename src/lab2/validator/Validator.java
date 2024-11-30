package lab2.validator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import lab2.annotations.MaxValue;
import lab2.annotations.MinValue;
import lab2.annotations.NotNull;
import lab2.annotations.StringLength;

/**
 * Validates the fields of an object using custom annotations. Checks whether the annotated fields
 * meet the specified constraints such as non-null values, minimum and maximum numeric values, and
 * string length limits.
 */
public class Validator {

  // Метод для перевірки валідності об'єкта
  public boolean validate(Object obj) {
    List<String> errorMessages = new ArrayList<>();

    // Отримуємо всі поля класу
    Field[] fields = obj.getClass().getDeclaredFields();
    for (Field field : fields) {
      field.setAccessible(true);

      // Перевіряємо наявність анотацій для валідації
      if (field.isAnnotationPresent(NotNull.class)) {
        try {
          Object value = field.get(obj);
          if (value == null) {
            errorMessages.add("Field " + field.getName() + " cannot be null.");
          }
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      }

      // Перевірка на MaxValue
      if (field.isAnnotationPresent(MaxValue.class)) {
        MaxValue maxValueAnnotation = field.getAnnotation(MaxValue.class);
        try {
          Object value = field.get(obj);
          if (value instanceof Integer) {
            int intValue = (Integer) value;
            if (intValue > maxValueAnnotation.value()) {
              errorMessages.add("Field " + field.getName() + " exceeds max value.");
            }
          }
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      }

      // Перевірка на MinValue
      if (field.isAnnotationPresent(MinValue.class)) {
        MinValue minValueAnnotation = field.getAnnotation(MinValue.class);
        try {
          Object value = field.get(obj);
          if (value instanceof Integer) {
            int intValue = (Integer) value;
            if (intValue < minValueAnnotation.value()) {
              errorMessages.add("Field " + field.getName() + " is less than min value.");
            }
          }
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      }

      // Перевірка на StringLength
      if (field.isAnnotationPresent(StringLength.class)) {
        StringLength stringLengthAnnotation = field.getAnnotation(StringLength.class);
        try {
          Object value = field.get(obj);
          if (value instanceof String) {
            String strValue = (String) value;
            if (strValue.length() > stringLengthAnnotation.maxLength()) {
              errorMessages.add("Field " + field.getName() + " exceeds max length.");
            }
            if (strValue.length() < stringLengthAnnotation.min()) {
              errorMessages.add("Field " + field.getName() + " is less than min length.");
            }
          }
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      }
    }

    // Якщо є помилки валідації, повертаємо false
    if (!errorMessages.isEmpty()) {
      errorMessages.forEach(System.out::println);
      return false;
    }

    return true;
  }
}
