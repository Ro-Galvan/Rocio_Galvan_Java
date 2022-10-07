package com.company.factory;

import java.util.Arrays;

public class IceCream {
    //    fields/data members
    private String[] flavor;
    private int salePrice;
    private int productionCost;
    private int productionTime;
    private String[] ingredients;

//    Constructor
    public IceCream(String[] flavor, int salePrice, int productionCost, int productionTime, String[] ingredients) {
        this.flavor = flavor;
        this.salePrice = salePrice;
        this.productionCost = productionCost;
        this.productionTime = productionTime;
        this.ingredients = ingredients;
    }

//    Methods
    public void tubsOfIceCreamPerMachine(){
        int tubs = 100;
        int hours= 8;
        System.out.println("1 machine makes " + tubs * hours + " tubs per day!!");
    }
    public void wholesalePrice(){
        System.out.println("we sell each tub to retailers for" + this.salePrice);
    }

    public void amountOfMachinesNeeded(){
        System.out.println(this.flavor.length + "is how many machines we have running at once");
        System.out.println("Each flavor requires it's own machine to avoid extra cleaning and maximize productivity");
    }

//    Getter & Setters
    public String[] getFlavor() {
        return flavor;
    }

    public void setFlavor(String[] flavor) {
        this.flavor = flavor;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public int getProductionCost() {
        return productionCost;
    }

    public void setProductionCost(int productionCost) {
        this.productionCost = productionCost;
    }

    public int getProductionTime() {
        return productionTime;
    }

    public void setProductionTime(int productionTime) {
        this.productionTime = productionTime;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }


//    toString
    @Override
    public String toString() {
        return "IceCream{" +
                "flavor=" + Arrays.toString(flavor) +
                ", salePrice=" + salePrice +
                ", productionCost=" + productionCost +
                ", productionTime=" + productionTime +
                ", ingredients=" + Arrays.toString(ingredients) +
                '}';
    }
}