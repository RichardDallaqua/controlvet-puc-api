package controlvet.rest.api.helper.csvmanager;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CsvInterface {

    public Classes colClass() default Classes.STRING;

    public int columnDecimals() default 2;

    public String property() default "";

    public String header() default "";

}