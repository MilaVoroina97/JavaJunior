package org.example.homework.two;

/**
 * Аннотация для проверки нахождения полей класса в определенном диапозоне
 */

public @interface Range {

    int min() default Integer.MIN_VALUE;
    int max() default Integer.MAX_VALUE;
    String message() default "Field value is out of range";
}
