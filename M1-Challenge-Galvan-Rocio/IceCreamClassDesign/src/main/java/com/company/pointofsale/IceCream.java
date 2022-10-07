package com.company.pointofsale;

import java.util.Arrays;

public class IceCream {
    private String[] flavor;
    private int price;
    private int scoop;
    private String[] toppings;

//    Constructor
    public IceCream(String[] flavor, int price, int scoop, String[] toppings) {
        this.flavor = flavor;
        this.price = price;
        this.scoop = scoop;
        this.toppings = toppings;
    }

//    Methods
    public void totalPrice(){
        System.out.println("Hello, you had" + this.scoop + " scoops, and" + Arrays.toString(this.toppings) + " making your total: $" + price);
    }

    public void amountOfScoopsInASmallCone(){
        this.scoop = 1;
        System.out.println("There are" + this.scoop +" scoops in a small cone");
    }

    public void amountOfScoopsInAMediumCone(){
        this.scoop = 2;
        System.out.println("There are" + this.scoop +" scoops in a medium cone");
    }

    public void amountOfScoopsInALargeCone(){
        this.scoop = 3;
        System.out.println("There are" + this.scoop +" scoops in a large cone");
    }

//    Getter & Setters
    public String[] getFlavor() {
        return flavor;
    }

    public void setFlavor(String[] flavor) {
        this.flavor = flavor;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getScoop() {
        return scoop;
    }

    public void setScoop(int scoop) {
        this.scoop = scoop;
    }

    public String[] getToppings() {
        return toppings;
    }

    public void setToppings(String[] toppings) {
        this.toppings = toppings;
    }

//    toString
    @Override
    public String toString() {
        return "IceCream{" +
                "flavor=" + Arrays.toString(flavor) +
                ", price=" + price +
                ", scoop=" + scoop +
                ", toppings=" + Arrays.toString(toppings) +
                '}';
    }
}