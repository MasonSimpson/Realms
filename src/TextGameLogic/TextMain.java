package TextGameLogic;

import PlayerClasses.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class TextMain {
    // Public scanner for user input
    public static Scanner scan = new Scanner(System.in);

    public static void main (String[]args) throws InterruptedException {
        System.out.println("Welcome, adventurer, to the game of Realms! Tell me, what is your name? ");
        String playerName = scan.nextLine();
        Player player = new Player(playerName);
        Rules.promptRules(playerName);
        System.out.println("I will now let you enter the Adventurer's Village.");
        TimeUnit.SECONDS.sleep(1);
        AdventurersVillage.enterVillage();


    }

}
