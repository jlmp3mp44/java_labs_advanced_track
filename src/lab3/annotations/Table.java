package lab3.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Represents a database table containing columns. It stores a list of columns and provides methods
 * to manipulate them.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {

  String name();
}
