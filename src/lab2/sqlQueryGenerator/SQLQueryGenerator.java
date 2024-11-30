package lab2.sqlQueryGenerator;

import java.lang.reflect.Field;
import lab3.annotations.Column;
import lab3.annotations.Table;

/**
 * Generates SQL queries for CRUD operations based on annotated fields and table mappings.
 */
public class SQLQueryGenerator {

  // Метод для генерації SQL-запиту на створення (CREATE)
  public String create(Object obj) {
    StringBuilder query = new StringBuilder("INSERT INTO ");
    String tableName = getTableName(obj); // Отримуємо назву таблиці
    query.append(tableName).append(" (");

    StringBuilder values = new StringBuilder("VALUES (");
    Field[] fields = obj.getClass().getDeclaredFields();
    boolean firstField = true;

    for (Field field : fields) {
      field.setAccessible(true);
      Column column = field.getAnnotation(Column.class);
      if (column != null) {
        if (!firstField) {
          query.append(", ");
          values.append(", ");
        }
        firstField = false;

        query.append(column.name());
        try {
          Object value = field.get(obj);
          values.append("'").append(value).append("'");
        } catch (IllegalAccessException e) {
          throw new RuntimeException("Cannot access field value: " + field.getName(), e);
        }
      }
    }

    query.append(") ").append(values).append(");");
    return query.toString();
  }

  // Метод для генерації SQL-запиту на читання (READ)
  public String read(Object obj) {
    StringBuilder query = new StringBuilder("SELECT * FROM ");
    String tableName = getTableName(obj);
    query.append(tableName).append(" WHERE ");

    Field[] fields = obj.getClass().getDeclaredFields();
    boolean firstCondition = true;

    for (Field field : fields) {
      field.setAccessible(true);
      Column column = field.getAnnotation(Column.class);
      if (column != null) {
        if (!firstCondition) {
          query.append(" AND ");
        }
        firstCondition = false;

        try {
          Object value = field.get(obj);
          query.append(column.name()).append(" = '").append(value).append("'");
        } catch (IllegalAccessException e) {
          throw new RuntimeException("Cannot access field value: " + field.getName(), e);
        }
      }
    }

    query.append(";");
    return query.toString();
  }

  // Метод для генерації SQL-запиту на оновлення (UPDATE)
  public String update(Object obj) {
    StringBuilder query = new StringBuilder("UPDATE ");
    String tableName = getTableName(obj);
    query.append(tableName).append(" SET ");

    Field[] fields = obj.getClass().getDeclaredFields();
    boolean firstField = true;

    for (Field field : fields) {
      field.setAccessible(true);
      Column column = field.getAnnotation(Column.class);
      if (column != null) {
        if (!firstField) {
          query.append(", ");
        }
        firstField = false;

        try {
          Object value = field.get(obj);
          query.append(column.name()).append(" = '").append(value).append("'");
        } catch (IllegalAccessException e) {
          throw new RuntimeException("Cannot access field value: " + field.getName(), e);
        }
      }
    }

    query.append(" WHERE id = ?;");
    return query.toString();
  }

  // Метод для генерації SQL-запиту на видалення (DELETE)
  public String delete(Object obj) {
    StringBuilder query = new StringBuilder("DELETE FROM ");
    String tableName = getTableName(obj);
    query.append(tableName).append(" WHERE id = ?;");
    return query.toString();
  }

  // Метод для отримання назви таблиці через анотацію
  private String getTableName(Object obj) {
    Table table = obj.getClass().getAnnotation(Table.class);
    if (table == null) {
      throw new RuntimeException(
          "Class " + obj.getClass().getName() + " is not annotated with @Table.");
    }
    return table.name();
  }
}
