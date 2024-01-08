package org.example.homework.first;


import java.util.Arrays;
import java.util.List;

/**
 * Напишите программу, которая использует Stream API для обработки списка чисел.
 * Программа должна вывести на экран среднее значение всех четных чисел в списке.
 */

public class GetAverage {

    public static void main(String[] args) {
        List<Integer> numbers1 = Arrays.asList(1, 2, 5, 16, -1, -2, 0, 32, 3, 5, 8, 23, 4);

        double average = numbers1.stream()
                .filter(number -> number % 2 == 0)
                .mapToInt(n -> n)
                .average()
                .orElse(0);

        if(average != 0){
            System.out.println("The average of numbers in list is " + average);
        }else {
            System.out.println("There is not even numbers in this list");
        }

    }
}
