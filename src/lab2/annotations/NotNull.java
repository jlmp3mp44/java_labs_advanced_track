package lab2.annotations;

import java.lang.annotation.*;

/**
 * Indicates that a field must not be null.
 * Used for validation purposes in the {@link lab2.validator.Validator}.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface NotNull {

}
