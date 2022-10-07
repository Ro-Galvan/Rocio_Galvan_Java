package org.example;

public class Farmer extends Character{

    private boolean plowing;
    private boolean harvesting;

//    super constructor
    public Farmer(String name, int strength, int health, int stamina, int speed, int attackPower, boolean running, boolean arrested) {
        super(name, strength, health, stamina, speed, attackPower, running, arrested);
        setStrength(75);
        setHealth(100);
        setStamina(75);
        setSpeed(10);
        setAttackPower(1);
        setRunning(false);
        setArrested(false);
        setPlowing(false);
        setHarvesting(false);
        this.attackAnotherCharacter();
        }
//    default constructor--might not be needed
//        public Farmer() {}



    //    Getter & Setters
    public boolean isPlowing() {
        return plowing;
    }

    public void setPlowing(boolean plowing) {
        this.plowing = plowing;
    }

    public boolean isHarvesting() {
        return harvesting;
    }

    public void setHarvesting(boolean harvesting) {
        this.harvesting = harvesting;
    }

    @Override
    public String toString() {
        return "Farmer{" +
                "plowing=" + plowing +
                ", harvesting=" + harvesting +
                '}';
    }
}
