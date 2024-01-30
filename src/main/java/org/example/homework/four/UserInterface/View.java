package org.example.homework.four.UserInterface;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class View {

    Scanner in;

    public View() {
        in = new Scanner(System.in, StandardCharsets.UTF_8);
    }

    public int inputDuration() {
        System.out.print("Enter duration of the course: ");
        return in.nextInt();
    }

    public String inputTitle() {
        System.out.print("Enter the name of course: ");
        return in.next();
    }

    public int inputID() {
        System.out.print("Enter ID: ");
        return in.nextInt();
    }

    public int menu() {
        System.out.println("1 - Insert new data");
        System.out.println("2 - Read the data");
        System.out.println("3 - Change the name of course");
        System.out.println("4 - Change the duration of course");
        System.out.println("5 - Delete the course");
        System.out.println("0 - Exit");
        System.out.print("Enter number of your choice: ");
        return in.nextInt();
    }
}
