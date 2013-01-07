package alma.fr.basecomponents;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.google.inject.BindingAnnotation;


@Retention(RetentionPolicy.RUNTIME)
@BindingAnnotation
@Target({ElementType.PARAMETER})
public @interface Basebase {

}
