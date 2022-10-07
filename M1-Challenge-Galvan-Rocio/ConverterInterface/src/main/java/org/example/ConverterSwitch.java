package org.example;

// worked together in class with JB & Alla and referenced that code

public class ConverterSwitch implements Converter {
    public String convertMonth(int monthNumber) {
        switch (monthNumber) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                return "That number doesn't correspond to a month, enter a number between 1 & 12";
        }
    }

//    lines 36-38 were automatically generated using IntelliJ
    @Override
    public String convertDay(int dayNumber) {
        switch (dayNumber) {
            case 1:
                return "Sunday, although some might argue that the 1st day should be Monday";
            case 2:
                return "Monday";
            case 3:
                return "Tuesday";
            case 4:
                return "Wednesday";
            case 5:
                return "Thursday";
            case 6:
                return "Friday";
            case 7:
                return "Saturday, although some might argue that the 7th day should be Sunday";
            default:
                return "That number doesn't correspond to a day of the week, enter a number between 1 & 7";
        }
    }

}


