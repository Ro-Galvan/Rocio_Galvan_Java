package org.example;

import java.util.Scanner;

// Used activity UserIO from class as a guide along with the code worked on together with JB & Alla in class

public class ConverterApplication {
    public static void main(String[] args) {

        Scanner myScanner = new Scanner(System.in);
//        Get Months using If
        System.out.println("Select a number between 1-12, this number corresponds to the month of the year");
        Converter convertIf = new ConverterIf();
        int returnVal = Integer.parseInt(myScanner.nextLine());
        System.out.println(convertIf.convertMonth(returnVal));

//        Get Days using Switch
        System.out.println("Select a number between 1-7, this number corresponds to the day of the week");
        Converter convertSwitch = new ConverterSwitch();
        int returnVal2 = Integer.parseInt(myScanner.nextLine());
        System.out.println(convertSwitch.convertDay(returnVal2));

    }


}






