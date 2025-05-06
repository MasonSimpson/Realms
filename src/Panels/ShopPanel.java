package Panels;

import Entities.Player.*;
import Music.MusicPlayer;

import javax.swing.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.awt.*;

public class ShopPanel extends JPanel {
    private InteractionPanel interactionPanel;
    private ActionPanel actionPanel;
    private Player player;
    private ArrayList<String> messages = new ArrayList<>();
    private String musicPath = "src/Music/shop music.wav";
    private JPanel selectorPanel;
    private JButton leftArrow;
    private JButton rightArrow;
    private JLabel quantityLabel;

    public ShopPanel(Player player, ActionPanel actionPanel, InteractionPanel interactionPanel) {
        this.setLayout(null);
        this.player = player;
        this.interactionPanel = interactionPanel;
        this.actionPanel = actionPanel;
        MusicPlayer.play(musicPath);
        MusicPlayer.loop();

        this.add(actionPanel);
        this.add(interactionPanel);

        shopPrompt();
    }
    private void shopPrompt() {
        messages.clear();
        messages.add("Welcome to the Shop! What would you like to do?");
        interactionPanel.setGameMessages(messages, null);
        String[] buttonLabels = {"Purchase Health Potions", "View Inventory", "Leave Shop"};
        Runnable[] actions = {
                this::purchasePotions,
                () -> {
                    InventoryPopout inventoryPopout = new InventoryPopout(player);
                    inventoryPopout.showInventory((JFrame) SwingUtilities.getWindowAncestor(this));
                },
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
                }
        };
        interactionPanel.addButtons(buttonLabels, actions);
    }
    private void purchasePotions() {
        int[] quantity = {0};

        leftArrow = new JButton("<");
        rightArrow = new JButton(">");
        quantityLabel = new JLabel("0");
        quantityLabel.setForeground(Color.WHITE);

        selectorPanel = new JPanel();
        selectorPanel.setOpaque(false);
        selectorPanel.add(leftArrow);
        selectorPanel.add(quantityLabel);
        selectorPanel.add(rightArrow);

        Runnable updateCost = () -> {
            int cost = quantity[0] * 50;
            StringBuilder message = new StringBuilder("<html>");
            message.append("How many health potions would you like to purchase? Each costs 50 gold.<br>");
            message.append("{{selector}}<br><br>");
            message.append("<div style='text-align:center;'>Total Cost: ").append(cost).append("</div><br>");
            messages.clear();
            messages.add(message.toString().replace("{{selector}}", ""));
            interactionPanel.setGameMessages(messages, null);
        };

        leftArrow.addActionListener(e -> {
            if (quantity[0] > 0) {
                quantity[0]--;
                quantityLabel.setText(String.valueOf(quantity[0]));
                updateCost.run();
            }
        });

        rightArrow.addActionListener(e -> {
            quantity[0]++;
            quantityLabel.setText(String.valueOf(quantity[0]));
            updateCost.run();
        });

        selectorPanel.setBounds(630, 75, 120, 30);
        interactionPanel.add(selectorPanel);
        interactionPanel.repaint();

        updateCost.run();

        String[] buttonLabels = {"Purchase", "View Inventory", "Go Back"};
        Runnable[] actions = {
                () -> {
                    int totalCost = quantity[0] * 50;

                    if (player.getGold() >= totalCost && quantity[0] > 0) {
                        player.removeGold(totalCost);
                        player.addHealingPotions(quantity[0]);

                        messages.clear();
                        messages.add("<html>Thank you for your purchase!<br>You bought " + quantity[0] + " potions for " + totalCost + " gold.</html>");
                        interactionPanel.setGameMessages(messages, null);
                    } else {
                        messages.clear();
                        messages.add("<html>Not enough gold or invalid quantity selected.</html>");
                        interactionPanel.setGameMessages(messages, null);
                    }
                    if (selectorPanel != null) {
                        interactionPanel.remove(selectorPanel);
                        interactionPanel.repaint();
                    }
                    interactionPanel.clearButtons();
                    interactionPanel.addButtons(
                         new String[] { "View Inventory", "Go Back" },
                         new Runnable[] {
                                 () -> {
                                     InventoryPopout inventoryPopout = new InventoryPopout(player);
                                     inventoryPopout.showInventory((JFrame) SwingUtilities.getWindowAncestor(this));
                                 },
                                 () -> {
                                     interactionPanel.remove(selectorPanel);
                                     shopPrompt();
                                 }
                         }
                    );
                },
                () -> {
                    InventoryPopout inventoryPopout = new InventoryPopout(player);
                    inventoryPopout.showInventory((JFrame) SwingUtilities.getWindowAncestor(this));
                },
                () -> {
                    interactionPanel.remove(selectorPanel);
                    shopPrompt();
                }
        };
        interactionPanel.clearButtons();
        interactionPanel.addButtons(buttonLabels, actions);
    }
}
