package lab2.validator;

import lab2.annotations.MaxValue;
import lab2.annotations.MinValue;
import lab2.annotations.NotNull;
import lab2.annotations.StringLength;
import lab2.validator.Validator;

/**
 * A demonstration class for validating objects using custom annotations. This class ensures that
 * the fields of an object comply with specified constraints, such as non-null values, value ranges,
 * and string length restrictions.
 */
public class ValidatorDemo {

  @NotNull
  private String name;

  @MinValue(18)
  @MaxValue(100)
  private int age;

  @StringLength(min = 5, maxLength = 10)
  private String address;

  public ValidatorDemo(String name, int age, String address) {
    this.name = name;
    this.age = age;
    this.address = address;
  }

  public static void runDemo() {
    // Створюємо об'єкт для валідації
    ValidatorDemo obj = new ValidatorDemo(null, 25, "123 Street");

    // Створюємо валідатор
    Validator validator = new Validator();

    // Виведення результату валідації
    if (validator.validate(obj)) {
      System.out.println("Object is valid");
    } else {
      System.out.println("Object is invalid");
      // Додатково можна вивести які саме поля не пройшли валідацію
      System.out.println("Перевірка полів:");
      if (obj.name == null) {
        System.out.println("  - Поле 'name' не може бути порожнім.");
      }
      if (obj.age < 18 || obj.age > 100) {
        System.out.println("  - Поле 'age' має бути в діапазоні від 18 до 100.");
      }
      if (obj.address.length() < 5 || obj.address.length() > 10) {
        System.out.println("  - Поле 'address' має містити від 5 до 10 символів.");
      }
    }
  }

  // Геттери для полів
  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public String getAddress() {
    return address;
  }
}
