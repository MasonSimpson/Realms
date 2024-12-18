package TestDrivers;

import Panels.*;
import Player.*;

import javax.swing.*;

public class RealmSelectionTest {
    public static void main (String[]args) throws InterruptedException {
        Player player = new Player("Test", Player.Races.HUMAN, Player.Classes.MAGE, Player.Gender.MALE);
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
