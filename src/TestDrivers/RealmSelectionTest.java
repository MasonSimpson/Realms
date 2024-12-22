package TestDrivers;

import Panels.*;
import Entities.Player.*;

import javax.swing.*;

public class RealmSelectionTest {
    public static void main (String[]args) throws InterruptedException {
        Player player = new Player("Test", Player.Races.HUMAN, Player.Gender.MALE);
        player.addHealingPotions(5);
        player.setHealth(75);
        System.out.println(player.getHealingPotions());
        RealmSelectionPanel panel = new RealmSelectionPanel(player);

        JFrame frame = new JFrame("Realms");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600, 1000);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(panel);
        frame.setVisible(true);


    }
}
