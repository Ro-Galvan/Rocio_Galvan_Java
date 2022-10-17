package com.example.monthandmath.model;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class Month {

    @NotEmpty(message = "You must supply a value between 1-12")
    private int number;
    @NotEmpty(message = "You must supply a value for month name")
    private String name;

    public Month(){}

    public Month(int number, String name) {
        this.number = number;
        this.name = name;
    }

    //    POJO boilerplate for getter & setters, hashcode, ToSting

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Month month = (Month) o;
        return number == month.number && name == month.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, name);
    }

    @Override
    public String toString() {
        return "Month{" +
                "number=" + number +
                ", name='" + name + '\'' +
                '}';
    }
}
