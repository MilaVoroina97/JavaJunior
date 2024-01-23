package org.example.homework.two.taskGB;


import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Создайте абстрактный класс "Animal" с полями "name" и "age".
 * Реализуйте два класса-наследника от "Animal" (например, "Dog" и "Cat") с уникальными полями и методами.
 * Создайте массив объектов типа "Animal" и с использованием Reflection API выполните следующие действия:
 * Выведите на экран информацию о каждом объекте.
 * Вызовите метод "makeSound()" у каждого объекта, если такой метод присутствует.
 */

public class Main {

    public static void main(String[] args) {

        Animal[] animals1 = new Animal[3];
        animals1[0] = new Dog("Buddy", 5, true);
        animals1[1] = new Cat("Kitty", 3, true);
        animals1[2] = new Cat("Tom", 4, false);

        Dog dog1 = new Dog("Buddy", 5, true);
        Cat cat1 = new Cat("Kitty", 3, true);
        Cat cat2 = new Cat("Tom", 4, false);
        Dog dog2 = new Dog("Rax",2,false);
        Cat cat3 = new Cat("Twix",1, true);

        Animal[] animals = (Animal[]) Array.newInstance(Animal.class,5);
        Array.set(animals,0,dog1);
        Array.set(animals,1,cat1);
        Array.set(animals,2,cat2);
        Array.set(animals,3,dog2);
        Array.set(animals,4,cat3);

        for(Animal animal : animals1){
            Class<?> clazz = animal.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for(Field field : fields){

                field.setAccessible(true);
                String fieldName = field.getName();
                Object fieldValue;
                try {
                    fieldValue = field.get(animal);
                }catch (IllegalAccessException e){
                    fieldValue = "Field is null";
                }

                System.out.println(fieldName + " : " + fieldValue);
            }

            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods){
                if(method.getName().equals("makeSound") && method.getParameterCount() == 0){
                    try {
                        method.invoke(animal);
                    }catch (IllegalAccessException | InvocationTargetException e){
                        System.out.println(e.getCause());
                    }
                }
            }

            System.out.println();
        }
    }
}
