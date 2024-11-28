package lab2.validator;

import lab2.validator.ValidatorDemo;

/**
 * Provides validation methods without using reflection.
 * Manually validates the fields of an object.
 */
public class NonReflectionValidator {

  // Метод для перевірки валідності об'єкта без використання рефлексії
  public boolean validate(ValidatorDemo obj) {
    boolean isValid = true;
    if (obj.getName() == null) {
      System.out.println("Field name cannot be null.");
      isValid = false;
    }

    if (obj.getAge() < 18) {
      System.out.println("Field age must be at least 18.");
      isValid = false;
    }

    if (obj.getAge() > 100) {
      System.out.println("Field age exceeds maximum value of 100.");
      isValid = false;
    }

    return isValid;
  }
}
