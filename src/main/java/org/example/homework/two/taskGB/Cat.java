package org.example.homework.two.taskGB;

import lombok.ToString;

@ToString
public class Cat extends Animal{

    private boolean canSayMeow;
    public Cat(String name, int age,boolean canSayMeow) {
        super(name, age);
        this.canSayMeow = canSayMeow;
    }

    @Override
    public void makeSound() {
        if(canSayMeow){
            System.out.println("This cat can say 'Meeeeoow'!");
        }
    }
}
