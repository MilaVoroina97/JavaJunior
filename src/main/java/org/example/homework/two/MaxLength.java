package org.example.homework.two;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для проверки объекта по соответствию длины его полей
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MaxLength {

    int value() default Integer.MAX_VALUE;
    String message() default "Field length exceeds maximum";
}
