package GameLogic;

import Entities.*;
import Player.*;
import java.util.ArrayList;

public class Realm {
    public enum RealmNames {
        Elyos,
        Dorne,
        Highgarden,
        Maguuma,
        Underworld,
    }
    public RealmNames name;
    public boolean highEnoughLevel;
    public ArrayList<Monster> monstersInRealm;
    public Player player;
    public Realm (RealmNames name, int requiredLevel, Player player) {
        this.name = name;
        this.player = player;
        highEnoughLevel = player.getLevel() >= requiredLevel;

    }
}