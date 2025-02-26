package com.example;

import java.util.Scanner;

public class OddNumbers {
    public static void main(String[] args) {
        System.out.println("Enter the number: ");
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();

        if (num % 2 != 0){
            System.out.println("Entered number is Odd number.");

        }
        else{
            System.out.println("Entered number is Even number.");
        }
    }
}
