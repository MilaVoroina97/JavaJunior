package org.example.homework.two.taskGB;

import lombok.ToString;

@ToString
public class Dog extends Animal{

    private boolean canSayWoof;
    public Dog(String name, int age, boolean canSayWoof) {
        super(name, age);
        this.canSayWoof = canSayWoof;
    }


    @Override
    public void makeSound() {
        if(canSayWoof){
            System.out.println("This dog can say 'Woooof'!");
        }
    }
}
