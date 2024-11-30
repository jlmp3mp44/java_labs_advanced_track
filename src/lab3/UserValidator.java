package lab3;

import java.lang.reflect.Field;
import lab2.annotations.MaxValue;
import lab2.annotations.MinValue;
import lab2.annotations.NotNull;
import lab2.annotations.StringLength;

/**
 * Validates the fields of a User object. It checks if the fields meet certain conditions, such as
 * non-null values and valid age.
 */
public class UserValidator {

  public void validate(Object obj) throws Exception {
    Field[] fields = obj.getClass().getDeclaredFields();
    for (Field field : fields) {
      field.setAccessible(true);

      // Перевірка @NotNull
      if (field.isAnnotationPresent(NotNull.class)) {
        if (field.get(obj) == null) {
          throw new RuntimeException(field.getName() + " cannot be null.");
        }
      }

      // Перевірка @StringLength
      if (field.isAnnotationPresent(StringLength.class)) {
        StringLength annotation = field.getAnnotation(StringLength.class);
        Object value = field.get(obj);
        if (value != null && value.toString().length() > annotation.maxLength()) {
          throw new RuntimeException(
              field.getName() + " exceeds max length of " + annotation.maxLength());
        }
      }

      // Перевірка @MinValue
      if (field.isAnnotationPresent(MinValue.class)) {
        MinValue annotation = field.getAnnotation(MinValue.class);
        Object value = field.get(obj);
        if (value instanceof Number && ((Number) value).doubleValue() < annotation.value()) {
          throw new RuntimeException(
              field.getName() + " is less than the minimum value of " + annotation.value());
        }
      }

      // Перевірка @MaxValue
      if (field.isAnnotationPresent(MaxValue.class)) {
        MaxValue annotation = field.getAnnotation(MaxValue.class);
        Object value = field.get(obj);
        if (value instanceof Number && ((Number) value).doubleValue() > annotation.value()) {
          throw new RuntimeException(
              field.getName() + " exceeds the maximum value of " + annotation.value());
        }
      }
    }
  }
}
