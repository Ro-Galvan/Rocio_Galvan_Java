package org.example;

public class Warrior extends Character {

    private int shieldStrength;

    public Warrior(String name, int strength, int health, int stamina, int speed, int attackPower, boolean running, boolean arrested) {
        super(name, strength, health, stamina, speed, attackPower, running, arrested);

        setStrength(75);
        setHealth(100);
        setStamina(100);
        setSpeed(50);
        setAttackPower(10);
        setShieldStrength(100);
        setRunning(false);
        setArrested(false);
        this.attackAnotherCharacter();
    }

    public int getShieldStrength() {
        return shieldStrength;
    }

    public void setShieldStrength(int shieldStrength) {
        this.shieldStrength = shieldStrength;
    }

    @Override
    public String toString() {
        return "Warrior{" +
                "shieldStrength=" + shieldStrength +
                '}';
    }
}
