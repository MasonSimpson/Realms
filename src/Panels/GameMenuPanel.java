package Panels;

import Utils.*;
import Entities.Player.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameMenuPanel extends JPanel {
    private JButton newGameButton;
    private JButton loadGameButton;

    public GameMenuPanel() {
        this.setLayout(null);
        this.setBackground(Color.black);
        newGameButton = new JButton("New Game");
        newGameButton.setHorizontalAlignment(SwingConstants.CENTER);
        newGameButton.setIcon(new ImageIcon("src/Images/ButtonLabels/new game button.png"));
        newGameButton.setBounds(730, 200,150, 50);
        newGameButton.setBackground(Color.black);
        newGameButton.addActionListener(e -> characterCreation());
        this.add(newGameButton);
        loadGameButton = new JButton();
        loadGameButton.setHorizontalAlignment(SwingConstants.CENTER);
        loadGameButton.setIcon(new ImageIcon("src/Images/ButtonLabels/load game button.png"));
        loadGameButton.setBounds(730, 300,150, 50);
        loadGameButton.setBackground(Color.black);
        loadGameButton.addActionListener(e -> loadGame());
        this.add(loadGameButton);
        this.revalidate();
        this.repaint();

    }
    private void characterCreation() {
        this.removeAll();
        CharacterCreationPanel characterCreationPanel = new CharacterCreationPanel((JPanel) this.getParent());
        characterCreationPanel.setBounds(0, 50,1600,900);
        this.add(characterCreationPanel);
        this.revalidate();
        this.repaint();
    }
    private void loadGame() {
        String[] saves = SaveUtils.listSaveFiles();

        if (saves.length == 0) {
            JOptionPane.showMessageDialog(this, "No save files found.", "Load Game", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String selected = (String) JOptionPane.showInputDialog(
                this,
                "Select a save file to load:",
                "Load Game",
                JOptionPane.PLAIN_MESSAGE,
                null,
                saves,
                saves[0]
        );

        if (selected != null) {
            Player loadedPlayer = SaveUtils.loadPlayer(selected);
            if (loadedPlayer != null) {
                JPanel parent = (JPanel) this.getParent();
                GamePanel.startGame(parent, loadedPlayer, true);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to load save.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
