package Panels;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import Player.*;
import GameLogic.*;

public class InteractionPanel extends JPanel{
    private Player player;
    private String gameMessage = "";
    private JLabel gameMessageLabel;
    private Queue<String> gameMessageQueue;

    public InteractionPanel(Player player) {
        this.setLayout(null);
        this.setBackground(Color.black);
        this.setBorder(new MatteBorder(5,0,0,0,Color.white));
        this.player = player;
        gameMessageQueue = new LinkedList<>();
        setupMessageLabel();
        setupMouseListener();
    }
    private void setupMessageLabel() {
        gameMessageLabel = new JLabel("", SwingConstants.CENTER);
        gameMessageLabel.setForeground(Color.white);
        gameMessageLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        gameMessageLabel.setBounds(100, 0, 1200, 200);
        this.add(gameMessageLabel);
    }
    private void setupMouseListener() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showNextMessage();
            }
        });
    }
    public void setGameMessages(ArrayList<String> messages) {
        for (String message : messages) {
            gameMessageQueue.offer(message);
        }
        showNextMessage();
    }
    private void showNextMessage() {
        if (gameMessageQueue.isEmpty()) {
            gameMessageLabel.setText("");
            updatePanel();
        }
        else {
            gameMessage = gameMessageQueue.poll();
            updatePanel();
        }
    }
    public void updatePanel() {
        if (gameMessage.isEmpty()) {
            gameMessageLabel.setText("");
        }
        else {
            gameMessageLabel.setText(gameMessage);
        }
        this.revalidate();
        this.repaint();
    }
}
