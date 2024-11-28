package lab2.annotations;

import java.lang.annotation.*;

/**
 * Specifies the maximum allowable value for a numeric field.
 * Used for validation purposes in the {@link lab2.validator.Validator}.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MaxValue {

  int value();
}
