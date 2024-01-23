package org.example.homework.two;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@Table(name = "users")
public class Employee {

    @Column(name = "id",primaryKey = true)
    private UUID id;

    @Column(name = "username")
    private String userName;

    @Column(name = "email")
    private String email;

    public Employee(String userName, String email) {

        this.id = UUID.randomUUID();
        this.userName = userName;
        this.email = email;
    }


}
