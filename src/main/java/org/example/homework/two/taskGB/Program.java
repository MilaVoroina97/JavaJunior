package org.example.homework.two.taskGB;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Program {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException {


        List<Animal> animals = new ArrayList<>();
        Dog dog1 = new Dog("Buddy", 5, true);
        Cat cat1 = new Cat("Kitty", 3, true);
        Cat cat2 = new Cat("Tom", 4, false);
        Dog dog2 = new Dog("Rax",2,false);
        Cat cat3 = new Cat("Twix",1, true);

        animals.add(dog1);
        animals.add(cat1);
        animals.add(cat2);
        animals.add(dog2);
        animals.add(cat3);

        Class<?> animalClass;
        for (Animal animal : animals){
            if(animal.getClass().equals(Cat.class)){
                animalClass = Class.forName("org.example.homework.two.taskGB.Cat");
            }else {
                animalClass = Class.forName("org.example.homework.two.taskGB.Dog");
            }

            Constructor[] constructors = animalClass.getConstructors();
            Arrays.stream(constructors)
                    .map(Constructor::getParameterCount)
                    .forEach(System.out::println);

            Field[] superClassField = animalClass.getSuperclass().getDeclaredFields();
            Field[] classField = animalClass.getDeclaredFields();
            Field[] allField = new Field[superClassField.length + classField.length];
            Arrays.setAll(allField, i ->
                    i < classField.length ? classField[i] : superClassField[i - classField.length]);
            for (Field field : allField){
                field.setAccessible(true);
                System.out.println("Field : " + field.getName() + ", value: " + field.get(animal));
            }

            Method[] methods = animalClass.getDeclaredMethods();
            for(Method method : methods){

                System.out.println("Method : " + method.getName()
                        + ", Return type : " + method.getReturnType()
                + ", Parametrs types : " + Arrays.toString(method.getParameterTypes()));
            }

            Method makeSound = Arrays.stream(methods)
                    .filter(method -> Modifier.isPublic(method.getModifiers())
                    && method.getName().equals("makeSound"))
                    .findFirst()
                    .orElse(null);
            if(makeSound != null){
                try {
                    makeSound.invoke(animal);
                }catch (IllegalAccessException | InvocationTargetException e){
                    System.out.println(e.getMessage());
                }
            }
        }


    }
}
