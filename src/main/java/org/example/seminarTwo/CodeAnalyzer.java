package org.example.seminarTwo;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class CodeAnalyzer {

    /**
     * Этот метод должен:
     * определить, какого класса объект ему передали и вывести название класса в консоль;
     * определить названия всех полей этого класса, включая приватные, и вывести их в консоль;
     * определить названия всех методов этого класса, включая приватные, и вывести их в консоль
     */

    public static void analyzeClass(Object o){

        Class<?> clazz = o.getClass();

        System.out.println("Имя класса: " + clazz);
        System.out.println("Поля класса: " + Arrays.toString(clazz.getDeclaredFields()));
        System.out.println("Родительский класс: " + clazz.getSuperclass());
        System.out.println("Методы класса: " + Arrays.toString(clazz.getDeclaredMethods()));
        System.out.println("Конструкторы класса: " + Arrays.toString(clazz.getConstructors()));

    }

    public static Cat createCat() throws IOException, IllegalAccessException,
            InstantiationException, ClassNotFoundException{

        Class<?> clazz = null;
        Cat cat = null;
        try {

            clazz = Class.forName("org.example.seminarTwo.Cat");
            Class[] catClassParams = {String.class,int.class};
            cat = (Cat) clazz.getConstructor(catClassParams).newInstance("Barsik", 6);
        }catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                NoSuchMethodException | InvocationTargetException e){
            e.printStackTrace();
        }

        return cat;
    }

    public static Cat changeCatObject(){
        Class<?> clazz = null;
        Cat cat = null;
        try{
            clazz = Class.forName("org.example.seminarTwo.Cat");
            cat = (Cat) clazz.newInstance();
            cat.setName("Barsik");

            Field age= clazz.getDeclaredField("age");
            age.setAccessible(true);
            age.set(cat,6);

        }catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        return cat;
    }

    public static void invokeSayMeowMethod(){
        Class<?> clazz = null;
        Cat cat = null;
        try {
            cat = new Cat("Barsik", 6);
            clazz = Class.forName(Cat.class.getName());
            Method sayMeow = clazz.getDeclaredMethod("sayMeow");
            sayMeow.setAccessible(true);
            sayMeow.invoke(cat);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }



    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        analyzeClass(new Cat("Barsik", 6));
        Cat cat = createCat();
        System.out.println(cat);
    }

}
