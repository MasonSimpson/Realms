package GameLogic;

import Player.*;
import Entities.*;
import java.util.*;

public class Battle {
    private Player player;
    private Monster monster;
    public Battle(Player player, Monster monster) {
        this.player = player;
        this.monster = monster;
    }
    public int attack() {
        int damage = player.calculateDamage();
        monster.removeHealth(damage);
        return damage;
    }
    public void heal() {}
    public int monsterChoice() {
        Random rand = new Random();
        int choice = rand.nextInt(2);
        switch (choice) {
            case 0:
                player.removeHealth(monster.damage);
                break;
            case 1:
                if (monster.healthPotions == 0)
                    return monsterChoice();
                monster.addHealth(rand.nextInt(3) + 25);
                break;
            default:
                break;
        }
        return choice;

    }
}

