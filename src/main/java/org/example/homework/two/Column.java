package org.example.homework.two;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//данная аннотация означает, что можно работать с механизмом рефлексии и получить доступ к созданным аннотациям
// во время рантайма
@Retention(RetentionPolicy.RUNTIME)
// данная аннотация крепится к полю
@Target(ElementType.FIELD)
public @interface Column {

    //получение наименования аннотации
    String name();
    boolean primaryKey() default false;

}
