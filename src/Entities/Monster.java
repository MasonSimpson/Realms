package Entities;

import java.util.*;

public class Monster {
    private Random rand = new Random();
    public enum MonsterType {
        GOBLIN, OGRE, WOLF, SPIDER, TROLL, UNDEAD, GIANT, DRAGON
    }
    public MonsterType type;
    public int level;
    public int health;
    public int damage;

    public Monster() {
        this.type = generateMonsterType();
        this.level = rand.nextInt(10) + 1;
        this.health = rand.nextInt(100) + 100;
        this.damage = rand.nextInt(10) + 1;
    }
    private MonsterType generateMonsterType() {
        int randomNum = rand.nextInt(8);
        return switch (randomNum) {
            case 0 -> MonsterType.GOBLIN;
            case 1 -> MonsterType.OGRE;
            case 2 -> MonsterType.WOLF;
            case 3 -> MonsterType.SPIDER;
            case 4 -> MonsterType.TROLL;
            case 5 -> MonsterType.UNDEAD;
            case 6 -> MonsterType.GIANT;
            case 7 -> MonsterType.DRAGON;
            default -> generateMonsterType();
        };
    }

}
