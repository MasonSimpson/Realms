package PlayerClasses;

import java.util.ArrayList;

public class Player {
    public String name;
    public int level;
    public int xp;
    public int health;
    public ArrayList<Object> inventory;
    public Weapon equippedWeapon;
    public Armor equippedArmor;
    public int gold;
    public int healingPotions;
    public int carryWeight;
    private int xpToNextLevel = 100;
    private int maxHealth = 100;

    public Player(String name) {
        this.name = name;
        this.level = 1;
        this.xp = 0;
        this.health = 100;
        this.inventory = new ArrayList<>();
        this.equippedWeapon = null;
        this.equippedArmor = null;
        this.gold = 0;
        this.healingPotions = 0;
        this.carryWeight = 100;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getLevel() {
        return level;
    }
    public int getXp() {
        return xp;
    }
    public void addXp(int xp) {
        this.xp += xp;
        if (this.xp >= this.xpToNextLevel) {
            levelUp();
        }
    }
    public void levelUp() {
        this.xp -= this.xpToNextLevel;
        this.level++;
        this.xpToNextLevel *= 1.25;
        this.maxHealth += 50;
        this.gold += 100;
        this.health = this.maxHealth;
        System.out.println("You leveled up!");
        if (this.xp >= this.xpToNextLevel) {
            levelUp();
        }
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public void addHealth(int health) {
        this.health += health;
        if (this.health > this.maxHealth) {
            this.health = this.maxHealth;
        }
    }
    public void removeHealth(int health) {
        this.health -= health;
    }
    public ArrayList<Object> getInventory() {
        return inventory;
    }
    public void addItem(Object item) {
        this.inventory.add(item);
    }
    public void removeItem(Object item) {
        this.inventory.remove(item);
    }
    public int getGold() {
        return gold;
    }
    public void addGold(int gold) {
        this.gold += gold;
    }
    public int getHealingPotions() {
        return healingPotions;
    }
    public void addHealingPotions(int healingPotions) {
        this.healingPotions += healingPotions;
    }
    public int getCarryWeight() {
        return carryWeight;
    }
    public void addCarryWeight(int carryWeight) {
        this.carryWeight += carryWeight;
    }
    public String toString() {
        return "Player: " + this.name + "\n" +
                "Level: " + this.level + "\n" +
                "XP: " + this.xp + "\n" +
                "Health: " + this.health + "\n" +
                "Gold: " + this.gold + "\n" +
                "Healing Potions: " + this.healingPotions + "\n" +
                "Carry Weight: " + this.carryWeight;
    }
}
