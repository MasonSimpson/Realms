package PlayerClasses;

public class Player {
    public String name;
    public int level;
    public int xp;
    public int health;
    // public Inventory playerInv;

    public Player(String name) {
        this.name = name;
        this.level = 1;
        this.xp = 0;
        this.health = 100;
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
    public void setLevel(int level) {
        this.level = level;
    }
    public int getXp() {
        return xp;
    }
    public void setXp(int xp) {
        this.xp = xp;
    }
    public void addXp(int xp) {
        this.xp += xp;
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public void addHealth(int health) {
        this.health += health;
    }
    public void removeHealth(int health) {
        this.health -= health;
    }
}
