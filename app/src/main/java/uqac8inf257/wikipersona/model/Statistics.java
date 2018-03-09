package uqac8inf257.wikipersona.model;

/**
 * Created by mimil on 2018-03-08.
 */

public class Statistics {

    private int strength;
    private int magic;
    private int endurance;
    private int agility;
    private int luck;

    public Statistics(int strength, int magic, int endurance, int agility, int luck) {
        this.strength = strength;
        this.magic = magic;
        this.endurance = endurance;
        this.agility = agility;
        this.luck = luck;
    }

    public int getStrength() {
        return strength;
    }

    public int getMagic() {
        return magic;
    }

    public int getEndurance() {
        return endurance;
    }

    public int getAgility() {
        return agility;
    }

    public int getLuck() {
        return luck;
    }
}
