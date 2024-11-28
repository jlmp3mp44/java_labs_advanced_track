package lab2;

import java.util.Scanner;
import lab2.serializer.SerializerDemo;
import lab2.sqlQueryGenerator.SQLQueryGeneratorDemo;
import lab2.validator.ValidatorDemo;

/**
 * The main class for executing the program.
 * Based on the student ID, it determines which task to execute:
 * SQL query generation, object serialization, or field validation.
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
        SQLQueryGeneratorDemo.runDemo();
        break;
      case 1:
        System.out.println("Вибрано завдання: Серіалізація об'єктів.");
        SerializerDemo.runDemo();
        break;
      case 2:
        System.out.println("Вибрано завдання: Валідація полів об'єктів.");
        ValidatorDemo.runDemo();
        break;
      default:
        System.out.println("Невідомий варіант завдання.");
    }

    scanner.close();
  }
}
