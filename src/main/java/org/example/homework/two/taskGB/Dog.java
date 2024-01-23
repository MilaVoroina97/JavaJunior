package org.example.homework.two.taskGB;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
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
