package org.example.homework.four.model;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import lombok.*;
import org.example.homework.two.Table;
import org.hibernate.annotations.Type;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "schooldb")
public class Course extends AbstractEntity{

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private double duration;

    @Column(nullable = false,unique = true)
    private String registrationNumber;

    @Column(nullable = false)
    @Type(value = org.example.homework.four.model.CustomDoubleArrayType.class)
    private Double[] raiting = new Double[3];

    public Course(){}


}
