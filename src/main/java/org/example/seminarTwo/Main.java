package org.example.seminarTwo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    static MyClass myClass = new MyClass();
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException,
            InstantiationException, IllegalAccessException {

        Class<?> car = Class.forName("org.example.seminarTwo.Car");

        Constructor<?>[] constructor = car.getConstructors();

        Object gaz = constructor[0].newInstance("GAZ");
        System.out.println(gaz);

        //массив полей объекта

        Field[] fields = gaz.getClass().getFields();
        int tmp = fields[fields.length - 1].getInt(gaz);
        fields[fields.length - 1].setInt(gaz,tmp * 2);
        System.out.println(gaz);

        //получаем все методы, которые есть в классе
        Method[] methods = gaz.getClass().getDeclaredMethods();
        for(int i = 0; i < methods.length; i++){
            System.out.println(methods[i]);
        }

        //MyClass operations:

        int number = myClass.getNumber();
        String name = null;
        printData(myClass);
        try {

            Field field = myClass.getClass().getDeclaredField("name");
            field.setAccessible(true);
            field.set(myClass,(String) "new value");
            name = (String) field.get(myClass);
        }catch (NoSuchFieldException | IllegalAccessException e){

            e.printStackTrace();
        }

        printData(myClass);

        MyClass newClass = null;
        try {
            Class clazz = Class.forName(MyClass.class.getName());
            myClass = (MyClass) clazz.newInstance();
        }catch (ClassNotFoundException | InstantiationException | IllegalAccessException e){

            e.printStackTrace();
        }
    }

    public static void printData(Object myClass){
        try {

            Method method = myClass.getClass().getDeclaredMethod("printData");
            method.setAccessible(true);
            method.invoke(myClass);
        }catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e){

            e.printStackTrace();
        }
    }
}
