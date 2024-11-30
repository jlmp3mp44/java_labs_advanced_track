package lab3;

import java.util.Scanner;
import lab3.models.User;

/**
 * The main entry point of the application. It demonstrates the functionality of generating SQL
 * queries, serializing objects, and validating data based on the student's ID.
 */
public class Main {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Введіть номер залікової книжки: ");
    int studentID = scanner.nextInt();

    int C3 = studentID % 3;
    System.out.println("C3: " + C3);

    switch (C3) {
      case 0:
        System.out.println("Вибрано завдання: Генерація SQL-запитів.");
        UserSQLGenerator sqlGenerator = new UserSQLGenerator();
        User userForSQL = new User("Alice", 25, "alice@example.com", "1");
        System.out.println("SQL Create Query: ");
        System.out.println(sqlGenerator.create(userForSQL));  // Генерація запиту на створення
        System.out.println("SQL Update Query: ");
        System.out.println(sqlGenerator.update(userForSQL));  // Генерація запиту на оновлення
        System.out.println("SQL Delete Query: ");
        System.out.println(sqlGenerator.delete(userForSQL));  // Генерація запиту на видалення
        System.out.println("SQL Read Query: ");
        System.out.println(sqlGenerator.read(userForSQL));  // Генерація запиту на читання
        break;

      case 1: // Серіалізація об'єктів
        System.out.println("Вибрано завдання: Серіалізація об'єктів.");
        UserSerializer serializer = new UserSerializer();
        User userForSerialization = new User("Bob", 30, "bob@example.com", "1");
        try {
          System.out.println("JSON Representation: ");
          System.out.println(serializer.toJSON(userForSerialization)); // Серіалізація в JSON
          System.out.println("XML Representation: ");
          System.out.println(serializer.toXML(userForSerialization)); // Серіалізація в XML
        } catch (Exception e) {
          System.out.println("Serialization failed: " + e.getMessage());
        }
        break;

      case 2: // Валідація полів об'єктів
        System.out.println("Вибрано завдання: Валідація полів об'єктів.");
        UserValidator validator = new UserValidator();
        User userForValidation = new User(null, 17, "invalid@example.com", "1");
        try {
          validator.validate(userForValidation);  // Валідація
          System.out.println("Validation passed!");
        } catch (Exception e) {
          System.out.println("Validation failed: " + e.getMessage());
        }
        break;

      default:
        System.out.println("Невідомий варіант завдання.");
    }

    scanner.close();
  }
}
