package org.example;

import java.math.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to calc");

        while (true){
            System.out.println("Print new expression or (exit)");
            String inputString = input.nextLine();

            if (inputString.equals("exit")) {
                break;
            }

            try {
                Solver slv = new Solver();
                BigInteger answer = slv.solve(inputString);
                if (answer != null) {
                    System.out.println(answer);
                } else {
                    System.out.println("Wrong input");
                }
            } catch (Exception exp) {
                System.out.println("Something broke");
            }

        }

        System.out.println("Have a nice day");
    }
}
