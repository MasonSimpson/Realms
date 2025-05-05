package Panels;

import Entities.Player.*;

import javax.swing.*;
import java.awt.*;


public class InventoryPopout {
    private Player player;
    private JDialog inventoryDialog;
    private JButton closeButton;
    private JLabel titleLabel;
    private JLabel coinsLabel;
    private JLabel healingPotionLabel;
    private JLabel levelLabel;
    private JLabel xpLabel;

    public InventoryPopout(Player player) {
        this.player = player;
    }
    public void showInventory(JFrame parent) {
        inventoryDialog = new JDialog(parent, "Inventory", true);
        inventoryDialog.setUndecorated(true);
        inventoryDialog.setSize(800, 600);
        inventoryDialog.setLayout(null);
        inventoryDialog.getContentPane().setBackground(new Color(0, 50, 0));

        titleLabel = new JLabel("Inventory");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.white);
        titleLabel.setBounds(10, 10, 300, 30);
        inventoryDialog.add(titleLabel);
        levelLabel = new JLabel("Level: " + player.getLevel());
        levelLabel.setForeground(Color.white);
        levelLabel.setFont(new Font("Arial", Font.BOLD, 20));
        levelLabel.setBounds(10, 50, 300, 30);
        inventoryDialog.add(levelLabel);
        xpLabel = new JLabel("XP Needed to Level Up: " + (player.xpToNextLevel - player.getXp()));
        xpLabel.setForeground(Color.white);
        xpLabel.setFont(new Font("Arial", Font.BOLD, 20));
        xpLabel.setBounds(10, 70, 300, 30);
        inventoryDialog.add(xpLabel);
        coinsLabel = new JLabel("Gold: " + player.getGold());
        coinsLabel.setForeground(Color.white);
        coinsLabel.setFont(new Font("Arial", Font.BOLD, 20));
        coinsLabel.setBounds(10, 90, 300, 30);
        inventoryDialog.add(coinsLabel);
        healingPotionLabel = new JLabel("Healing Potions: " + player.getHealingPotions());
        healingPotionLabel.setForeground(Color.white);
        healingPotionLabel.setFont(new Font("Arial", Font.BOLD, 20));
        healingPotionLabel.setBounds(10, 110, 300, 30);
        inventoryDialog.add(healingPotionLabel);
        closeButton = new JButton("Close");
        closeButton.setFont(new Font("Arial", Font.BOLD, 20));
        closeButton.setBounds(10, 300, 100, 30);
        closeButton.setBackground(Color.BLACK);
        closeButton.setForeground(Color.WHITE);
        closeButton.addActionListener(e -> inventoryDialog.dispose());
        inventoryDialog.add(closeButton);

        inventoryDialog.setLocationRelativeTo(parent);
        inventoryDialog.setVisible(true);


    }
}
