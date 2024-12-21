package Panels;

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
                    () -> System.out.println("Flee")
            };
            interactionPanel.addButtons(buttonLabels, buttonActions);
        });
        timer.setRepeats(false);
        timer.start();
    }
}
