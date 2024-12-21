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

public class InteractionPanel extends JPanel{
    private JPanel buttonPanel;
    private Player player;
    private String gameMessage = "";
    private JLabel gameMessageLabel;
    private Queue<String> gameMessageQueue;
    private Runnable onMessagesComplete;

    public InteractionPanel(Player player) {
        this.setLayout(null);
        this.setBackground(Color.black);
        this.setBorder(new MatteBorder(5,0,0,0,Color.white));
        this.player = player;
        gameMessageQueue = new LinkedList<>();
        setupMessageLabel();
        setupMouseListener();
        setupButtonPanel();
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
                if (gameMessageQueue.isEmpty()) {
                    clearButtons();
                }
                showNextMessage();
            }
        });
    }
    private void setupButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.black);
        buttonPanel.setBounds(100, 200, 1200, 100);
        this.add(buttonPanel);
    }
    public void showButtons(boolean show) {
        buttonPanel.setVisible(show); // Toggles visibility of the button panel
        this.revalidate();
        this.repaint();
    }
    public void setGameMessages(ArrayList<String> messages, Runnable onComplete) {
        for (String message : messages) {
            gameMessageQueue.offer(message);
        }
        this.onMessagesComplete = onComplete;
        showNextMessage();
    }
    private void showNextMessage() {
        if (gameMessageQueue.isEmpty()) {
            gameMessageLabel.setText("");
            if (onMessagesComplete != null) {
                onMessagesComplete.run();
            }
            updatePanel();
        }
        else {
            gameMessage = gameMessageQueue.poll();
            updatePanel();
        }
    }
    public void addButtons(String[] buttonLabels, Runnable[] actions) {
        clearButtons();

        for (int i = 0; i < buttonLabels.length; i++) {
            final int index = i; // Create effectively final copy of i
            JButton button = new JButton(buttonLabels[i]);
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            button.setBackground(Color.black);
            button.setForeground(Color.white);
            button.setSize(150, 50);
            button.addActionListener(e -> actions[index].run());
            buttonPanel.add(button);
        }
        showButtons(true);
        this.revalidate();
        this.repaint();
    }
    public void clearButtons() {
        buttonPanel.removeAll();
        this.revalidate();
        this.repaint();
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
