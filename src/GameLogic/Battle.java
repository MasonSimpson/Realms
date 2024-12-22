package GameLogic;

import Entities.Player.*;
import Entities.*;
import java.util.*;

public class Battle {
    private Player player;
    private Monster monster;
    public int monsterHealthHealed;
    public int goldWon;
    public int xpEarned;
    public Battle(Player player, Monster monster) {
        this.player = player;
        this.monster = monster;
    }
    public int attack() {
        int damage = player.calculateDamage();
        monster.removeHealth(damage);
        return damage;
    }
    public int heal() {
        player.healingPotions--;
        int healthToAdd = (int) (player.maxHealth * 0.25); // Health potions heal 1/4 of players max Health
        player.addHealth(healthToAdd);
        return healthToAdd;
    }
    public int monsterChoice() {
        Random rand = new Random();
        int num = rand.nextInt(20);
        boolean choice = num % 2 == 0;
        if (choice) {
            player.removeHealth(monster.damage);
            if (player.health <= 0) {
                player.health = 0;
            }
            return 0;
        }
        else {
            if (monster.healthPotions == 0 || monster.maxHealth == monster.health)
                return monsterChoice();
            monsterHeal();
            return 1;
        }
    }
    public void monsterHeal() {
        Random rand = new Random();
        int healthToAdd = rand.nextInt(3) + 25;
        monster.addHealth(healthToAdd);
        if (monster.health > monster.maxHealth) {
            monster.health = monster.maxHealth;
        }
        monster.healthPotions = monster.healthPotions - 1;
        this.monsterHealthHealed = healthToAdd;
    }
    public void calculateEarnings() {
        this.goldWon = 25 + monster.level * 5;
        this.xpEarned = monster.level * 5;
    }

}

