package com.example.currencywebservice;

import java.util.Scanner;

public class SwissBank {

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        System.out.println("Welcome to SwissBank, a safe heaven for you money ");
        Scanner scanner = new Scanner(System.in);
        boolean finished = true;
        while (finished) {
            System.out.println("Enter 1 to convert to celcius, press 2 to convert to fahrenheit");
            switch (scanner.next()) {
                case "1":
                    finished = false;
                    scanner.nextLine();

            }
        }
    }
}
