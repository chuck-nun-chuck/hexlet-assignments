package exercise.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

// BEGIN
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Inspect {
}
// END
