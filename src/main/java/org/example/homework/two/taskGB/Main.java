package org.example.homework.two.taskGB;


import java.lang.reflect.*;
import java.util.Arrays;

/**
 * Создайте абстрактный класс "Animal" с полями "name" и "age".
 * Реализуйте два класса-наследника от "Animal" (например, "Dog" и "Cat") с уникальными полями и методами.
 * Создайте массив объектов типа "Animal" и с использованием Reflection API выполните следующие действия:
 * Выведите на экран информацию о каждом объекте.
 * Вызовите метод "makeSound()" у каждого объекта, если такой метод присутствует.
 */

public class Main {

    public static void main(String... args) {

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

        for (Animal animal : animals) {
            // Get the class of the animal
            Class<?> clazz = animal.getClass();

            // Print the common fields from the Animal superclass
            Field[] fields = clazz.getSuperclass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object fieldValue;
                try {
                    fieldValue = field.get(animal);
                } catch (IllegalAccessException e) {
                    fieldValue = "Field is null";
                }
                System.out.println(fieldName + " : " + fieldValue.toString());
            }

            // Print the fields specific to Dog and Cat subclasses
            Field[] specificFields = clazz.getDeclaredFields();
            for (Field specificField : specificFields) {
                specificField.setAccessible(true);
                String fieldName = specificField.getName();
                Object fieldValue;
                try {
                    fieldValue = specificField.get(animal);
                } catch (IllegalAccessException e) {
                    fieldValue = "Field is null";
                }
                System.out.println(fieldName + " : " + fieldValue.toString());
            }

            // Invoke the makeSound method
            Method[] methods = clazz.getDeclaredMethods();
            Method makeSoundMethod = Arrays.stream(methods)
                    .filter(method -> Modifier.isPublic(method.getModifiers())
                            && method.getName().equals("makeSound"))
                    .findFirst()
                    .orElse(null);
            if (makeSoundMethod != null) {
                try {
                    makeSoundMethod.invoke(animal);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    System.out.println(e.getCause());
                }
            }

            System.out.println();
        }


    }
}

