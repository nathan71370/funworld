package fr.azrodorza.commands;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(METHOD)
public @interface CommandMapper {
    String cmd();

    String sub() default "";

    String permission() default "";

    boolean canBeExecutedByConsole() default false;
}
