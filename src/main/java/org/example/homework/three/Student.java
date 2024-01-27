package org.example.homework.three;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import java.io.Serializable;


@ToString
@Getter
@Setter
@XmlRootElement(name = "student")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Student implements Serializable {

    @JsonProperty("name")
    private String name;

    @JsonProperty("age")
    private int age;

    @Encrypt
    @JsonProperty("GPA")
    @JsonSerialize(using = EncryptedJsonSerializer.class)
    @JsonDeserialize(using = EncryptedJsonDeserializer.class)
    @XmlElement
    private double GPA;

    public Student(String name, int age, double GPA) {
        this.name = name;
        this.age = age;
        this.GPA = GPA;
    }
    public Student(){}


}
