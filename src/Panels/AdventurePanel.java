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
    private JLabel playerHealthLabel;
    private JLabel monsterHealthLabel;
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
    public void setActionPanel() {
        playerHealthLabel = new JLabel("<html>" + player.getName() + "'s health: " + player.getHealth());
        playerHealthLabel.setFont(playerHealthLabel.getFont().deriveFont(15f));
        playerHealthLabel.setForeground(Color.white);
        playerHealthLabel.setBackground(Color.black);
        playerHealthLabel.setBounds(100, 30, 400, 30);
        actionPanel.add(playerHealthLabel);
        monsterHealthLabel = new JLabel("<html>" + monster.type + "'s health: " + monster.health);
        monsterHealthLabel.setFont(monsterHealthLabel.getFont().deriveFont(15f));
        monsterHealthLabel.setForeground(Color.white);
        monsterHealthLabel.setBackground(Color.black);
        monsterHealthLabel.setBounds(100, 70, 400, 30);
        actionPanel.add(monsterHealthLabel);
        this.revalidate();
        this.repaint();
    }
    public void updateActionPanel() {
        playerHealthLabel.setText("<html>" + player.getName() + "'s health: " + player.getHealth());
        monsterHealthLabel.setText("<html>" + monster.type + "'s health: " + monster.health);
        this.revalidate();
        this.repaint();
    }
    public void adventure() {
        messages.add("Traveling through " + realm.name + "...");
        monster = new Monster(realm);
        setActionPanel();
        interactionPanel.setGameMessages(messages, null);
        timer = new Timer(3000, e -> {
            messages.clear();
            messages.add("<html>You encounter a " + monster.type + ", and it doesn't look like it's going to back down...<br>What would you like to do, " + player.getName() + "?");
            interactionPanel.setGameMessages(messages, null);
            String[] buttonLabels = {"Battle", "View Inventory", "Flee"};
            Runnable[] buttonActions = {
                    () -> {
                        messages.clear();
                        interactionPanel.clearButtons();
                        messages.add("You begin your battle with the " + monster.type + "...");
                        interactionPanel.setGameMessages(messages, null);
                        Timer battleStartTimer = new Timer(3000, startBattle -> {
                            startBattle();
                        });
                        battleStartTimer.start();
                    },
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
    public void startBattle() {
        Battle battle = new Battle(player, monster);
        messages.clear();
        messages.add("What would you like to do, " + player.getName() + "?");
        interactionPanel.setGameMessages(messages, null);
        String[] buttonLabels = {"Attack", "Heal", "View Inventory"};
        Runnable[] buttonActions = {
                () -> {
                    messages.clear();
                    interactionPanel.clearButtons();
                    int damageDealt = battle.attack();
                    messages.add("<html>You attack the " + monster.type + "!<br>" +
                            "You dealt " + damageDealt + " damage to the " + monster.type + ".");
                    interactionPanel.setGameMessages(messages, null);
                    updateActionPanel();
                },
                () -> System.out.println("Heal"),
                () -> {
                    InventoryPopout inventoryPopout = new InventoryPopout(player);
                    inventoryPopout.showInventory((JFrame) SwingUtilities.getWindowAncestor(this));
                }
        };
        interactionPanel.addButtons(buttonLabels, buttonActions);
    }
}
