package Panels;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import GameLogic.*;
import Player.*;
import Entities.*;
import Music.*;

public class RealmSelectionPanel extends JPanel {
    private Player player;
    private InteractionPanel interactionPanel;
    private ActionPanel actionPanel;
    private String realmSelectionMusicPath = "src/Music/realm selection screen music.wav";
    private ArrayList<String> messages = new ArrayList<>();

    public RealmSelectionPanel(Player player) {
        this.setLayout(null);
        this.setBackground(Color.black);
        this.player = player;
        actionPanel = new ActionPanel(player);
        actionPanel.setBounds(0,0, 1600, 700);
        this.add(actionPanel);
        interactionPanel = new InteractionPanel(player);
        interactionPanel.setBounds(0,700, 1600, 300);
        this.add(interactionPanel);
        MusicPlayer.stop();
        MusicPlayer.play(realmSelectionMusicPath);
        MusicPlayer.loop();
        selectRealm();
    }
    public void selectRealm() {
        messages.add("Select the Realm you wish to travel to, " + player.getName() + ".");
        String[] buttonLabels = {"Elyos", "Dorne", "Highgarden", "Maguuma", "Underworld", "Return to Village", "Return to Menu"};
        Runnable[] buttonActions = {
                () -> System.out.println("Selected Elyos"),
                () -> System.out.println("Selected Dorne"),
                () -> System.out.println("Selected Highgarden"),
                () -> System.out.println("Selected Maguuma"),
                () -> System.out.println("Selected Underworld"),
                () -> {
                    MusicPlayer.stop();
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                    frame.getContentPane().removeAll();
                    frame.getContentPane().add(actionPanel);
                    frame.getContentPane().add(interactionPanel);
                    AdventurersVillagePanel adventurersVillagePanel = new AdventurersVillagePanel(player, actionPanel, interactionPanel);
                    frame.getContentPane().add(adventurersVillagePanel);
                    adventurersVillagePanel.villagePrompt();
                    frame.revalidate();
                    frame.repaint();
                },
                () -> {
                    MusicPlayer.stop();
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                    frame.getContentPane().removeAll();
                    frame.getContentPane().add(new MainMenuPanel());
                    frame.revalidate();
                    frame.repaint();
                }
        };
        interactionPanel.setGameMessages(messages, null);
        interactionPanel.addButtons(buttonLabels, buttonActions);
    }
}
