package PlayerClasses;

public class Weapon {
    public String name;
    public int damage;
    public int weight;
    public int durability;
    public int rarity;
    public int value;

    public Weapon(String name, int damage, int weight, int durability, int rarity, int value) {
        this.name = name;
        this.damage = damage;
        this.weight = weight;
        this.durability = durability;
        this.rarity = rarity;
        this.value = value;
    }
    public String getName() {
        return name;
    }
    public int getDamage() {
        return damage;
    }
    public int getWeight() {
        return weight;
    }
    public int getDurability() {
        return durability;
    }
    public int getRarity() {
        return rarity;
    }
    public int getValue() {
        return value;
    }
    public void decreaseDurability(int num) {
        this.durability -= num;
    }
}
