package Panels;

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
        newGameButton.setIcon(new ImageIcon("src/Images/new game button.png"));
        newGameButton.setBounds(730, 200,150, 50);
        newGameButton.setBackground(Color.black);
        newGameButton.addActionListener(e -> characterCreation());
        this.add(newGameButton);
        loadGameButton = new JButton();
        loadGameButton.setHorizontalAlignment(SwingConstants.CENTER);
        loadGameButton.setIcon(new ImageIcon("src/Images/load game button.png"));
        loadGameButton.setBounds(730, 300,150, 50);
        loadGameButton.setBackground(Color.black);
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
}
