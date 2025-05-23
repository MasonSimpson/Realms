package GameLogic;

import Panels.IntroPanel;
import Panels.MainMenuPanel;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Game");
        frame.setSize(1600, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        IntroPanel introPanel = new IntroPanel(() -> {
            frame.setContentPane(new MainMenuPanel());
            frame.revalidate();
        });

        frame.setContentPane(introPanel);
        frame.setVisible(true);
    }

}
