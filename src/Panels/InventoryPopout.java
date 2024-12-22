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
        titleLabel.setBounds(10, 10, 300, 30);
        inventoryDialog.add(titleLabel);
        coinsLabel = new JLabel("Gold: " + player.getGold());
        coinsLabel.setFont(new Font("Arial", Font.BOLD, 20));
        coinsLabel.setBounds(10, 50, 300, 30);
        inventoryDialog.add(coinsLabel);
        healingPotionLabel = new JLabel("Healing Potions: " + player.getHealingPotions());
        healingPotionLabel.setFont(new Font("Arial", Font.BOLD, 20));
        healingPotionLabel.setBounds(10, 80, 300, 30);
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
