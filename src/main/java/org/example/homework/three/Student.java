package org.example.homework.three;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlValue;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Base64;

@ToString
@Getter
@Setter
@XmlRootElement(name = "student")
@JsonIgnoreProperties(value = {""})
public class Student implements Externalizable {

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "age")
    private int age;

    @XmlJavaTypeAdapter(GPAXmlAdapter.class)
    private double GPA;

    public Student(String name, int age, double GPA) {
        this.name = name;
        this.age = age;
        this.GPA = GPA;
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
        this.GPA = 0.0;
    }

    public Student(){}

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.name);
        out.writeInt(this.age);

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

        name = (String) in.readObject();
        age = in.readInt();
    }

}
