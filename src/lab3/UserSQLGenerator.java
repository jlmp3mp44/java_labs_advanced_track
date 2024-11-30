package lab3;

import java.lang.reflect.Field;
import lab3.models.User;

/**
 * Generates SQL queries for CRUD operations (Create, Read, Update, Delete) on a User object.
 */
public class UserSQLGenerator {

  // Генерація SQL-запиту для додавання нового користувача
  public String create(User user) {
    StringBuilder query = new StringBuilder("INSERT INTO User (");
    StringBuilder values = new StringBuilder("VALUES (");

    Field[] fields = user.getClass().getDeclaredFields();
    boolean firstField = true;

    // Проходимо по всіх полях класу User
    for (Field field : fields) {
      field.setAccessible(true); // Доступ до приватних полів

      if (!firstField) {
        query.append(", ");
        values.append(", ");
      }
      firstField = false;

      try {
        String columnName = field.getName(); // Використовуємо ім'я поля як назву колонки таблиці
        query.append(columnName);
        Object value = field.get(user); // Отримуємо значення поля
        values.append("'").append(value).append("'"); // Додаємо значення у VALUES
      } catch (IllegalAccessException e) {
        throw new RuntimeException("Error accessing field: " + field.getName(), e);
      }
    }

    // Завершуємо побудову запиту
    query.append(") ").append(values).append(");");
    return query.toString(); // Повертаємо готовий SQL-запит
  }

  // Генерація SQL-запиту для оновлення запису користувача
  public String update(User user) {
    StringBuilder query = new StringBuilder("UPDATE User SET ");

    Field[] fields = user.getClass().getDeclaredFields();
    boolean firstField = true;

    for (Field field : fields) {
      field.setAccessible(true); // Доступ до приватних полів

      if (!firstField) {
        query.append(", ");
      }
      firstField = false;

      try {
        String columnName = field.getName();
        Object value = field.get(user);
        query.append(columnName).append(" = '").append(value).append("'");
      } catch (IllegalAccessException e) {
        throw new RuntimeException("Error accessing field: " + field.getName(), e);
      }
    }

    // Додаємо умову WHERE для оновлення за id
    query.append(" WHERE id = ").append(user.getUserId()).append(";");
    return query.toString();
  }

  // Генерація SQL-запиту для видалення користувача
  public String delete(User user) {
    return "DELETE FROM User WHERE id = " + user.getUserId() + ";";
  }

  // Генерація SQL-запиту для читання (отримання) користувача
  public String read(User user) {
    return "SELECT * FROM User WHERE id = " + user.getUserId() + ";";
  }
}
