package com.example;

import java.util.Scanner;

public class largestNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the first number: ");
        int a = scanner.nextInt();
        System.out.println("Enter the second number: ");
        int b = scanner.nextInt();
        System.out.println("Enter the third number: ");
        int c = scanner.nextInt();
        if(a>=b && a>=c) {
            System.out.println("A is grater");
        }
        else if(b>=a && b>=c ){
                System.out.println("B is grater");
        }
        else {
                System.out.println("C is grater");
        }


    }
}
