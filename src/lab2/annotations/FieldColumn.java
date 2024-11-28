package lab2.annotations;

import java.lang.annotation.*;

/**
 * Represents a mapping between a field in a class and a column in a database table.
 * Used by the {@SQLQueryGenerator} to generate SQL queries.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FieldColumn {

  String field();

  String column();
}
