package Panels;

import Player.*;

import javax.swing.*;
import java.awt.*;


public class InventoryPopout {
    private Player player;
    private JDialog inventoryDialog;
    private JButton closeButton;
    private JLabel titleLabel;
    private JLabel coinsLabel;
    private JLabel healthPotionLabel;

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
        coinsLabel = new JLabel("Coins");
        coinsLabel.setFont(new Font("Arial", Font.BOLD, 20));
        coinsLabel.setBounds(10, 50, 300, 30);
        inventoryDialog.add(coinsLabel);
        healthPotionLabel = new JLabel("Health Potions");
        healthPotionLabel.setFont(new Font("Arial", Font.BOLD, 20));
        healthPotionLabel.setBounds(10, 80, 300, 30);
        inventoryDialog.add(healthPotionLabel);
        closeButton = new JButton("Close");
        closeButton.setFont(new Font("Arial", Font.BOLD, 20));
        closeButton.setBounds(10, 100, 100, 30);
        closeButton.setBackground(Color.BLACK);
        closeButton.setForeground(Color.WHITE);
        closeButton.addActionListener(e -> inventoryDialog.dispose());
        inventoryDialog.add(closeButton);

        inventoryDialog.setLocationRelativeTo(parent);
        inventoryDialog.setVisible(true);


    }
}
