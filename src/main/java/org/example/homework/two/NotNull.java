package org.example.homework.two;


/**
 * Аннотация для проверки, не указывает ли поле класса на null
 */

public @interface NotNull {

    String message() default "Field cannot be null";
}
