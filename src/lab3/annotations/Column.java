package lab3.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Represents a column in a database table or other structure. It stores information about the
 * column's name and data type.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {

  String name();
}
