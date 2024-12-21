package Panels;

import Music.MusicPlayer;
import Player.*;
import GameLogic.*;
import Entities.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AdventurePanel extends JPanel {
    private InteractionPanel interactionPanel;
    private ActionPanel actionPanel;
    private Player player;
    private Realm realm;
    private ArrayList<String> messages = new ArrayList<>();
    private Timer timer;
    private Monster monster;
    private int goldEarned = 0;
    private int xpEarned = 0;
    public AdventurePanel(InteractionPanel interactionPanel, ActionPanel actionPanel, Player player, Realm realm) {
        this.setLayout(null);
        this.setBackground(Color.black);
        this.interactionPanel = interactionPanel;
        this.actionPanel = actionPanel;
        this.add(interactionPanel);
        this.add(actionPanel);
        this.player = player;
        this.realm = realm;
    }
    public void adventure() {
        messages.add("Traveling through " + realm.name + "...");
        interactionPanel.setGameMessages(messages, null);
        timer = new Timer(3000, e -> {
            messages.clear();
            monster = new Monster(realm);
            messages.add("<html>You encounter a " + monster.type + ", and it doesn't look like it's going to back down...<br>What would you like to do, " + player.getName() + "?");
            interactionPanel.setGameMessages(messages, null);
            String[] buttonLabels = {"Battle", "View Inventory", "Flee"};
            Runnable[] buttonActions = {
                    () -> System.out.println("Battle"),
                    () -> {
                        InventoryPopout inventoryPopout = new InventoryPopout(player);
                        inventoryPopout.showInventory((JFrame) SwingUtilities.getWindowAncestor(this));
                    },
                    () -> {
                        messages.clear();
                        messages.add("<html>You decide to flee from the " + monster.type + ", and you escape back to the Adventurer's Village...<br>" +
                                "You earned " + goldEarned + " gold and " + xpEarned + " XP during your adventure.<br>" +
                                "Traveling back to Adventurer's Village...");
                        interactionPanel.setGameMessages(messages, null);
                        interactionPanel.clearButtons();
                        Timer tempTimer = new Timer(6000, returnToVillage -> {
                            MusicPlayer.stop();
                            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                            frame.getContentPane().removeAll();
                            frame.getContentPane().add(actionPanel);
                            frame.getContentPane().add(interactionPanel);
                            AdventurersVillagePanel adventurersVillagePanel = new AdventurersVillagePanel(player, actionPanel, interactionPanel);
                            frame.getContentPane().add(adventurersVillagePanel);
                            adventurersVillagePanel.returnToVillage();
                            frame.revalidate();
                            frame.repaint();
                        });
                        tempTimer.setRepeats(false);
                        tempTimer.start();
                    }
            };
            interactionPanel.addButtons(buttonLabels, buttonActions);
        });
        timer.setRepeats(false);
        timer.start();
    }
}
