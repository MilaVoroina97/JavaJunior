package org.example.homework.four.controller;

public class FacadeInstance {

    private static IFacade facadeInstance;
    private static final Object MONITOR = new Object();

    public FacadeInstance(){
        super();
    }

    public static IFacade getFacadeInstance(){
        synchronized (MONITOR){
            if(facadeInstance == null){
                facadeInstance = new Facade();
            }
        }
        return facadeInstance;
    }
}
