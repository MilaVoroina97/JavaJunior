package org.example.seminarThree.taskFromSeminar.taskOne;

import lombok.ToString;

import java.io.Serializable;
@ToString
public class UserData implements Serializable {

    private String name;
    private int age;
    transient String password;

    public UserData(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
