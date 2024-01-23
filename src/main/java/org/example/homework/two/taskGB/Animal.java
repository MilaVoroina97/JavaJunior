package org.example.homework.two.taskGB;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class Animal {

    private String name;
    private int age;

    public abstract void makeSound();
}
