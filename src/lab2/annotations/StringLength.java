package lab2.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Specifies the minimum and maximum allowable length for a string field.
 * Used for validation purposes in the {@link lab2.validator.Validator}.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface StringLength {

  int min();

  int max();
}