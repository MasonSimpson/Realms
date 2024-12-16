package GameLogic;

import PlayerClasses.*;
import javax.swing.*;

public class Main {

    public static void main (String[]args) throws InterruptedException {
        MainMenuPanel menuPanel = new MainMenuPanel();

        JFrame frame = new JFrame("Realms");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600, 1000);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(menuPanel);
        frame.setVisible(true);


    }

}
