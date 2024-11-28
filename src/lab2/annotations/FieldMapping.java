package lab2.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Represents the mapping between a class and a database table,
 * including mappings for its fields and columns.
 * Used by the {@SQLQueryGenerator}.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FieldMapping {

  FieldColumn[] fieldToColumn();
}