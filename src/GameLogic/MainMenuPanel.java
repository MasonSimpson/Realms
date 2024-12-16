package GameLogic;

import javax.swing.*;
import java.awt.*;
import PlayerClasses.*;

public class MainMenuPanel extends JPanel {
    // Declaring Swing objects being used on the MainMenuPanel
    private JLabel titleLabel;
    private Icon gameLogo;
    private JButton playButton;
    private JButton rulesButton;
    private JButton optionsButton;
    private JButton exitButton;

    public MainMenuPanel() {
        super();
        this.setLayout(null);
        this.setBackground(Color.BLACK);

        showMenu();
    }

    private void showMenu() {
        // Game logo
        gameLogo = new ImageIcon("src/Images/realms logo.png");
        titleLabel = new JLabel(gameLogo);
        titleLabel.setBounds(500, 20, 600, 300);
        this.add(titleLabel);

        // Play button
        playButton = new JButton("Play Game");
        playButton.setBounds(730, 400,150, 50);
        playButton.setFont(new Font("Arial", Font.BOLD, 20));
        playButton.setBackground(Color.BLACK);
        playButton.setForeground(Color.white);
        //playButton.addActionListener(e -> )
        this.add(playButton);

    }
}
