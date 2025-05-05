package Entities;

import java.util.*;
import GameLogic.Realm;

public class Monster {
    private Random rand = new Random();
    private Realm realm;
    public enum MonsterType {
        GOBLIN, OGRE, WOLF, SPIDER, TROLL, GIANT, LAVA_SPRITE, PHOENIX, DRAGON, DEMON, MINOTAUR, CERBERUS;
    }
    public MonsterType type;
    public int level;
    public int health;
    public int maxHealth;
    public int damage;
    public int healthPotions;

    public Monster(Realm realm) {
        this.realm = realm;
        int monsterType;
        switch (realm.name) {
            case Valamar:
                this.level = rand.nextInt(4) + 1;
                monsterType = rand.nextInt(2);
                if (monsterType == 0) {
                    this.type = MonsterType.GOBLIN;
                } else {
                    this.type = MonsterType.OGRE;
                }
                break;
            case Elvaria:
                this.level = rand.nextInt(4) + 6;
                monsterType = rand.nextInt(2);
                if (monsterType == 0) {
                    this.type = MonsterType.WOLF;
                } else {
                    this.type = MonsterType.SPIDER;
                }
                break;
            case Khara:
                this.level = rand.nextInt(9) + 11;
                monsterType = rand.nextInt(2);
                if (monsterType == 0) {
                    this.type = MonsterType.TROLL;
                } else {
                    this.type = MonsterType.GIANT;
                }
                break;
            case Maguuma:
                this.level = rand.nextInt(9) + 21;
                monsterType = rand.nextInt(3);
                if (monsterType == 0) {
                    this.type = MonsterType.LAVA_SPRITE;
                }
                else if (monsterType == 1) {
                    this.type = MonsterType.PHOENIX;
                }
                else {
                    this.type = MonsterType.DRAGON;
                }
                break;
            case Dehara:
                this.level = rand.nextInt(4) + 31;
                monsterType = rand.nextInt(3);
                if (monsterType == 0) {
                    this.type = MonsterType.DEMON;
                }
                else if (monsterType == 1) {
                    this.type = MonsterType.MINOTAUR;
                }
                else {
                    this.type = MonsterType.CERBERUS;
                }
                break;
            default:
                break;
        }
        this.health = 50 + (this.level - 1) * 25;
        this.maxHealth = health;
        this.damage = 5 + (this.level - 1) * 5;
        this.healthPotions = this.level;
        System.out.println("Monster type: " + this.type);
        System.out.println("Level: " + this.level);
        System.out.println("Health: " + this.health);
        System.out.println("Max Health: " + this.maxHealth);
        System.out.println("Damage: " + this.damage);
        System.out.println("Health Potions: " + this.healthPotions);
    }
    public int getLevel() {
        return level;
    }
    public void removeHealth(int health) {
        this.health -= health;
    }
    public void addHealth(int health) {
        this.health += health;
        this.healthPotions--;
    }
    public void die() {
        this.health = 0;
        this.healthPotions = 0;
        this.level = 0;
        this.maxHealth = 0;
        this.damage = 0;

    }


}
