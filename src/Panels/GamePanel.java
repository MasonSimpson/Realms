package Panels;

import Utils.*;

import javax.swing.*;
import java.awt.*;
import Entities.Player.*;

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
    public static void startGame(JPanel parent, Player player, boolean loadedGame) {
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
        enterVillage(loadedGame);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            SaveUtils.savePlayer(player);
        }));
    }
    private static void enterVillage(boolean loadedGame) {
        adventurersVillagePanel = new AdventurersVillagePanel(player, actionPanel, interactionPanel);
        adventurersVillagePanel.setBounds(0,0,actionPanel.getWidth(), actionPanel.getHeight());
        actionPanel.add(adventurersVillagePanel);
        actionPanel.revalidate();
        actionPanel.repaint();
        if (!loadedGame) {
            adventurersVillagePanel.firstTime();
        }
        else {
            adventurersVillagePanel.villagePrompt();
        }
    }

}
