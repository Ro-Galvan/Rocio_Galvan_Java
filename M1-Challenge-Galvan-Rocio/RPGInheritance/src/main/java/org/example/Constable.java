package org.example;

public class Constable extends Character {

    private String jurisdiction;

//    created constructor matching super using Intellij
    public Constable(String name, int strength, int health, int stamina, int speed, int attackPower, boolean running, boolean arrested) {
        super(name, strength, health, stamina, speed, attackPower, running, arrested);
        setStrength(60);
        setHealth(100);
        setStamina(60);
        setSpeed(20);
        setAttackPower(5);
        setRunning(false);
        setArrested(false);
        this.jurisdiction = "I reside over the North Carolina region, you better watch out buddy!";
        this.attackAnotherCharacter();
        this.arrestAnotherCharacter();
    }


    public void arrestAnotherCharacter(){

        System.out.println("you've been arrested" + getName());
    }

    //    Getter & Setters
    public String getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(String jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    @Override
    public String toString() {
        return "Constable{" +
                "jurisdiction='" + jurisdiction + '\'' +
                '}';
    }
}
