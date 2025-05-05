package Panels;

import javax.swing.*;
import java.awt.*;

import Music.*;

public class MainMenuPanel extends JPanel {
    // Declaring Swing objects being used on the MainMenuPanel
    private JLabel titleLabel;
    private Icon gameLogo;
    private JButton playButton;
    private JButton optionsButton;
    private JButton backToMenuButton;
    private JButton exitButton;
    private JSlider musicVolumeSlider;
    private String menuMusicPath = "src/Music/menu music 4.wav";

    public MainMenuPanel() {
        super();
        this.setLayout(null);
        this.setBackground(Color.BLACK);

        showMenu();
    }

    private void showMenu() {
        this.removeAll();

        // Stops playing music if music that isn't menu music is currently playing and then starts menu music
        if (!(MusicPlayer.getMusicPath().equals(menuMusicPath))) {
            MusicPlayer.play(menuMusicPath);
            MusicPlayer.loop();
        }

        // Game logo
        gameLogo = new ImageIcon("src/Images/ButtonLabels/realms logo.png");
        titleLabel = new JLabel(gameLogo);
        titleLabel.setBounds(500, 20, 600, 300);
        this.add(titleLabel);

        // Play button
        playButton = new JButton("Play Game");
        playButton.setHorizontalAlignment(SwingConstants.CENTER);
        playButton.setVerticalAlignment(SwingConstants.CENTER);
        playButton.setIcon(new ImageIcon("src/Images/ButtonLabels/play game button.png"));
        playButton.setBounds(730, 400,150, 50);
        playButton.setBackground(Color.BLACK);
        playButton.addActionListener(e -> showGameMenu());
        this.add(playButton);

        // Options button
        optionsButton = new JButton("Options");
        optionsButton.setHorizontalAlignment(SwingConstants.CENTER);
        optionsButton.setVerticalAlignment(SwingConstants.CENTER);
        optionsButton.setIcon(new ImageIcon("src/Images/ButtonLabels/options button.png"));
        optionsButton.setBounds(730, 500,150, 50);
        optionsButton.setBackground(Color.BLACK);
        optionsButton.addActionListener(e -> showOptionsMenu());
        this.add(optionsButton);

        // Exit game button
        exitButton = new JButton("Exit Game");
        exitButton.setHorizontalAlignment(SwingConstants.CENTER);
        exitButton.setVerticalAlignment(SwingConstants.CENTER);
        exitButton.setIcon(new ImageIcon("src/Images/ButtonLabels/exit game button.png"));
        exitButton.setBounds(730, 600,150, 50);
        exitButton.setBackground(Color.BLACK);
        exitButton.addActionListener(e -> System.exit(0));
        this.add(exitButton);

        this.revalidate();
        this.repaint();

    }
    private void showGameMenu() {
        this.removeAll();
        this.add(titleLabel);
        GameMenuPanel gameMenuPanel = new GameMenuPanel();
        gameMenuPanel.setBounds(0, 200,1600,400);
        this.add(gameMenuPanel);
        createBackToMenuButton(705, 650);
        this.revalidate();
        this.repaint();
    }
    private void showOptionsMenu() {
        this.removeAll();
        this.add(titleLabel);
        OptionsPanel optionsPanel = new OptionsPanel();
        optionsPanel.setBounds(0, 200,1600,400);
        this.add(optionsPanel);
        createBackToMenuButton(705, 650);
        this.revalidate();
        this.repaint();

    }
    private void createBackToMenuButton(int x, int y) {
        backToMenuButton = new JButton("Back to Menu");
        backToMenuButton.setHorizontalAlignment(SwingConstants.CENTER);
        backToMenuButton.setIcon(new ImageIcon("src/Images/ButtonLabels/back to menu button.png"));
        backToMenuButton.setBounds(x, y,200, 50);
        backToMenuButton.setBackground(Color.black);
        backToMenuButton.addActionListener(e -> showMenu());
        this.add(backToMenuButton);
        this.revalidate();
        this.repaint();
    }


}
