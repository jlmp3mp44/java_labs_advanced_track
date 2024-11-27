package lab2.sqlQueryGenerator;

import java.lang.reflect.Field;

public class SQLQueryGenerator {

  // Метод для генерації SQL-запиту на створення (CREATE)
  public String create(Object obj) {
    StringBuilder query = new StringBuilder("INSERT INTO ");
    String tableName = obj.getClass().getSimpleName().toLowerCase(); // Ім'я таблиці відповідає імені класу
    query.append(tableName).append(" (");

    StringBuilder values = new StringBuilder("VALUES (");
    Field[] fields = obj.getClass().getDeclaredFields(); // Отримуємо всі поля класу
    for (int i = 0; i < fields.length; i++) {
      fields[i].setAccessible(true); // Робимо поле доступним для читання
      try {
        Object value = fields[i].get(obj); // Отримуємо значення поля
        query.append(fields[i].getName());
        values.append("'").append(value).append("'");
        if (i < fields.length - 1) {
          query.append(", ");
          values.append(", ");
        }
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    }

    query.append(") ").append(values).append(");");
    return query.toString();
  }

  // Метод для генерації SQL-запиту на читання (READ)
  public String read(Object obj) {
    StringBuilder query = new StringBuilder("SELECT * FROM ");
    String tableName = obj.getClass().getSimpleName().toLowerCase(); // Ім'я таблиці відповідає імені класу
    query.append(tableName).append(" WHERE ");

    Field[] fields = obj.getClass().getDeclaredFields();
    for (int i = 0; i < fields.length; i++) {
      fields[i].setAccessible(true);
      try {
        Object value = fields[i].get(obj);
        query.append(fields[i].getName()).append(" = '").append(value).append("'");
        if (i < fields.length - 1) {
          query.append(" AND ");
        }
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    }

    query.append(";");
    return query.toString();
  }

  // Метод для генерації SQL-запиту на оновлення (UPDATE)
  public String update(Object obj) {
    StringBuilder query = new StringBuilder("UPDATE ");
    String tableName = obj.getClass().getSimpleName().toLowerCase(); // Ім'я таблиці відповідає імені класу
    query.append(tableName).append(" SET ");

    Field[] fields = obj.getClass().getDeclaredFields();
    for (int i = 0; i < fields.length; i++) {
      fields[i].setAccessible(true);
      try {
        Object value = fields[i].get(obj);
        query.append(fields[i].getName()).append(" = '").append(value).append("'");
        if (i < fields.length - 1) {
          query.append(", ");
        }
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    }

    query.append(" WHERE id = ?;"); // Використовуємо ? для параметра id
    return query.toString();
  }

  // Метод для генерації SQL-запиту на видалення (DELETE)
  public String delete(Object obj) {
    StringBuilder query = new StringBuilder("DELETE FROM ");
    String tableName = obj.getClass().getSimpleName().toLowerCase(); // Ім'я таблиці відповідає імені класу
    query.append(tableName).append(" WHERE id = ?;");
    return query.toString();
  }
}
