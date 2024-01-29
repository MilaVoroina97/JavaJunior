package org.example.seminarFour;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.homework.two.Column;
import org.example.homework.two.Table;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@Entity //описываем объект - сущность: объект, созданный на основе этого класса, будет использоваться Hibernate при
//взаимодействии с базой данных.
@Table(name = "test.magic") // связываем класс с определенной схемой и таблицей
public class Magic {

    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //strategy for key creation
    private int idMagic;

    @Column(name = "name") //connect field to DB column
    private String name;

    @Column(name = "damage")
    private int damage;

    @Column(name = "attack")
    private int attBonus;

    public Magic(){}

    public Magic(String name, int damage, int attBonus) {
        this.name = name;
        this.damage = damage;
        this.attBonus = attBonus;
    }
}
