package lab2.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import lab2.annotations.FieldColumn;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FieldMapping {
  FieldColumn[] fieldToColumn();
}