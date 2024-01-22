package org.example.seminarTwo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.Callable;

public class ProxyClasses {

    public static void main(String[] args) {

        InvocationHandler invocationHandler = new MyProxy(5);
        // создаем массив всех интерфейсов, которые нам нужно имплементировать
        Class[] classes = new Class[]{Comparable.class, Callable.class};
        // создаем новый объект прокси - тот самый объект, который будет реализовывать необходимые
        // интерфейсы
        Object proxy = Proxy.newProxyInstance(null, classes,invocationHandler);
        proxy.getClass();
        ((Comparable) proxy).compareTo(5);


    }

    static class MyProxy implements InvocationHandler{

        Object target;

        public MyProxy(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            //здесь можно также писать любой код, к примеру проверять залогинился ли пользователь

            System.out.println(args);
            return method.invoke(target,args);
        }
    }
}
