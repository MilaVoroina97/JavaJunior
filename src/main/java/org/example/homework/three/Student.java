package org.example.homework.three;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Externalizable;
import java.io.ObjectInput;
import java.io.ObjectOutput;


@ToString
@Getter
@Setter
@XmlRootElement(name = "student")
@JsonIgnoreProperties(value = {""})
public class Student implements Externalizable {

    @JsonProperty("name")
    private String name;

    @JsonProperty("age")
    private int age;
    @Encrypt
    private double GPA;

    public Student(String name, int age, double GPA) {
        this.name = name;
        this.age = age;
        this.GPA = GPA;
    }


    public Student(){}

    @Override
    public void writeExternal(ObjectOutput out){
        try {
            out.writeObject(this.name);
            out.writeInt(this.age);
            out.writeObject(new GPAXmlAdapter().marshal(this.GPA));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void readExternal(ObjectInput in){


        try {
            this.name = (String) in.readObject();
            this.age = in.readInt();
            this.GPA = new GPAXmlAdapter().unmarshal((String) in.readObject());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
