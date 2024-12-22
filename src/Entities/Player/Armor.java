package Entities.Player;

public class Armor {
    public String name;
    public int defense;
    public int weight;
    public int durability;

    public Armor(String name, int defense, int weight, int durability) {
        this.name = name;
        this.defense = defense;
        this.weight = weight;
        this.durability = durability;
    }
    public String getName() {
        return name;
    }
    public int getDefense() {
        return defense;
    }
    public int getWeight() {
        return weight;
    }
    public int getDurability() {
        return durability;
    }
    public void decreaseDurability(int num) {
        this.durability -= num;
    }
}
