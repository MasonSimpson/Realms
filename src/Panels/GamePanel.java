package Panels;

import javax.swing.*;
import java.awt.*;
import Player.*;
import GameLogic.*;
import Music.*;

public class GamePanel extends JPanel {
    public static Player player;
    private static GamePanel gamePanel;
    private static InteractionPanel interactionPanel;
    private static ActionPanel actionPanel;
    private static AdventurersVillagePanel adventurersVillagePanel;

    public GamePanel() {
        this.setLayout(null);
        this.setBackground(Color.black);

    }
    public static void startGame(JPanel parent, Player player) {
        parent.removeAll();

        GamePanel.player = player;
        gamePanel = new GamePanel();
        gamePanel.setBounds(0,0, 1600,1000);
        parent.add(gamePanel);
        parent.revalidate();
        parent.repaint();
        actionPanel = new ActionPanel(player);
        actionPanel.setBounds(0,0, 1600, 700);
        interactionPanel = new InteractionPanel(player);
        interactionPanel.setBounds(0,700, 1600, 300);
        gamePanel.add(actionPanel);
        gamePanel.add(interactionPanel);
        gamePanel.revalidate();
        gamePanel.repaint();
        enterVillage();
    }
    private static void enterVillage() {
        adventurersVillagePanel = new AdventurersVillagePanel(player);
        adventurersVillagePanel.setBounds(0,0,actionPanel.getWidth(), actionPanel.getHeight());
    }

}
