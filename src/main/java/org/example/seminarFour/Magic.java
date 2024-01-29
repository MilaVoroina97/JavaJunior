package org.example.seminarFour;

import org.example.homework.two.Column;
import org.example.homework.two.Table;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity //описываем объект - сущность: объект, созданный на основе этого класса, будет использоваться Hibernate при
//взаимодействии с базой данных.
@Table(name="Magic") // связываем класс с определенной схемой и таблицей
public class Magic {

    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //strategy for key creation
    @Column(name = "id")
    private int id;

    @Column(name="name") //connect field to DB column
    private String name;

    @Column(name="damage")
    private int damage;

    @Column(name="attBonus")
    private int attBonus;

    @Column(name="armor")
    private int armor;

    public Magic(){}


    public Magic(String name, int damage, int attBonus,int armor) {
        this.name = name;
        this.damage = damage;
        this.attBonus = attBonus;
        this.armor = armor;
    }

    public int getIdMagic() {
        return id;
    }

    public void setIdMagic(int idMagic) {
        this.id = idMagic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getAttBonus() {
        return attBonus;
    }

    public void setAttBonus(int attBonus) {
        this.attBonus = attBonus;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    @Override
    public String toString() {
        return "Magic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", damage=" + damage +
                ", attBonus=" + attBonus +
                ", armor=" + armor +
                '}';
    }
}
