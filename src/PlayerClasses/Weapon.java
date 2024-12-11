package PlayerClasses;

public class Weapon {
    public String name;
    public int damage;
    public int weight;
    public int durability;

    public Weapon(String name, int damage, int weight, int durability) {
        this.name = name;
        this.damage = damage;
        this.weight = weight;
        this.durability = durability;
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
    public void decreaseDurability(int num) {
        this.durability -= num;
    }
}
