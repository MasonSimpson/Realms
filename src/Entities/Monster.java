package Entities;

import java.util.*;
import GameLogic.Realm;

public class Monster {
    private Random rand = new Random();
    private Realm realm;
    public enum MonsterType {
        GOBLIN, OGRE, WOLF, SPIDER, TROLL, GIANT, CHIMERA, PHOENIX, DRAGON, DEMON, MINOTAUR, CERBERUS;
    }
    public MonsterType type;
    public int level;
    public int health;
    public int damage;

    public Monster(Realm realm) {
        this.realm = realm;
        int monsterType;
        switch (realm.name) {
            case Elyos:
                this.level = rand.nextInt(4) + 1;
                monsterType = rand.nextInt(2);
                if (monsterType == 0) {
                    this.type = MonsterType.GOBLIN;
                } else {
                    this.type = MonsterType.OGRE;
                }
                break;
            case Dorne:
                this.level = rand.nextInt(4) + 6;
                monsterType = rand.nextInt(2);
                if (monsterType == 0) {
                    this.type = MonsterType.WOLF;
                } else {
                    this.type = MonsterType.SPIDER;
                }
                break;
            case Highgarden:
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
                    this.type = MonsterType.CHIMERA;
                }
                else if (monsterType == 1) {
                    this.type = MonsterType.PHOENIX;
                }
                else {
                    this.type = MonsterType.DRAGON;
                }
                break;
            case Underworld:
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
        this.damage = 5 + (this.level - 1) * 5;
    }


}
