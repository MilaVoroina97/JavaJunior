package org.example.homework.three;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Student implements Externalizable {

    private String name;
    private int age;

    @JsonIgnore
    private transient double GPA;

    public Student(String name, int age, double GPA) {
        this.name = name;
        this.age = age;
        this.GPA = GPA;
    }

    public Student(String name, int age){
        this.name = name;
        this.age = age;
        this.GPA = 0.0;

    }
    public Student(){}

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.name);
        out.writeInt(this.age);
        out.writeObject(this.encryptString(Double.toString(this.GPA)));

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

        name = (String) in.readObject();
        age = (int) in.readObject();
        GPA = Double.parseDouble(this.decryptString((String) in.readObject()));
    }
    private String decryptString(String data){
        String decrypted = new String(Base64.getDecoder().decode(data));
        System.out.println(decrypted);
        return decrypted;
    }

    private String encryptString(String data){
        String encrypted = Base64.getEncoder().encodeToString(data.getBytes());
        System.out.println(encrypted);
        return encrypted;
    }
}
