package com.example.monthandmath.controller;

import com.example.monthandmath.model.Month;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
    public class MonthController {
//    private int currentId = 1;
    //    starter data since no database to work with yet
    public List<Month> monthList = new ArrayList<>(Arrays.asList(
            new Month(1, "January"),
            new Month(2, "February"),
            new Month(3, "March"),
            new Month(4, "April"),
            new Month(5, "May"),
            new Month(6, "June"),
            new Month(7, "July"),
            new Month(8, "August"),
            new Month(9, "September"),
            new Month(10, "October"),
            new Month(11, "November"),
            new Month(12, "December")
    ));

    //    the below works but not required for the challenge
    @RequestMapping(value = "/month", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Month> getPeople() {

        return monthList;
    }

    //  Code referenced from solved dating-app example from class
    @RequestMapping(value = "/month/{monthNumber}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Month getMonthNum(@PathVariable int monthNumber) {

        Month found = null;
        for (Month month : monthList) {
            if (month.getNumber() == monthNumber) {
                found = month;
                break;
            }
        }
        if (monthNumber < 1 || monthNumber > 12) {
            throw new IllegalArgumentException("Please enter a number between 1-12");
        }
        return found;
    }

//    referenced WebServiceBuild activity from class
    @RequestMapping(value = "/randomMonth", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Month getRandomMonth() {
//        importing Random class from library
        Random randMonth = new Random();
//        .get gives you access to the list
        return monthList.get(randMonth.nextInt(12));
    }

}



