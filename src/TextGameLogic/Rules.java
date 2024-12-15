package TextGameLogic;

import static TextGameLogic.TextMain.scan;
import java.util.concurrent.TimeUnit;

public class Rules {
    public static void promptRules(String playerName) throws InterruptedException {
        System.out.println("Hello, " + playerName + "! Would you like to hear the rules of the game? (y/n)");
        String firstTime = scan.nextLine();
        if (firstTime.equals("y")) {
            System.out.println("I see! I will explain the rules of the game to you.\n\n");
            TimeUnit.SECONDS.sleep(1);
            Rules.displayRules();
        }
        else if (firstTime.equals("n")) {
            System.out.println("Okay, I will skip the rules for now.\n\n");
            TimeUnit.SECONDS.sleep(1);
            AdventurersVillage.enterVillage();
        }
        else {
            System.out.println("I'm sorry, I didn't understand that. Please try again.");
            TimeUnit.SECONDS.sleep(1);
            Rules.promptRules(playerName);
        }
    }
    public static void displayRules() throws InterruptedException {
        System.out.println("Welcome to the game of Realms! In this game, you will be fighting monsters to defeat them and earn gold. " +
                "You will be able to equip armor and weapons\nto help you defeat the monsters. You can find new equipment from defeated monsters " +
                "or you can buy them from the Shop in the Adventurer's Village.\nYou will also be able to use spells to heal yourself. The more monsters you defeat," +
                "the more Realms you can adventure to! But be warned, each Realm is harder than the last...");
        TimeUnit.SECONDS.sleep(5);
        System.out.print("\n\n");
    }
}
