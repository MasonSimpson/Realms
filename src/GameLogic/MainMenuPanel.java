package GameLogic;

import javax.swing.*;
import java.awt.*;
import PlayerClasses.*;
import Music.*;

public class MainMenuPanel extends JPanel {
    // Declaring Swing objects being used on the MainMenuPanel
    private JLabel titleLabel;
    private Icon gameLogo;
    private JButton playButton;
    private JButton newGameButton;
    private JButton loadGameButton;
    private JButton optionsButton;
    private JButton backToMenuButton;
    private JButton exitButton;
    private JSlider musicVolumeSlider;
    private String menuMusicPath = "src/Music/menu music.wav";
    private boolean menuMusicPlaying = false;

    public MainMenuPanel() {
        super();
        this.setLayout(null);
        this.setBackground(Color.BLACK);

        showMenu();
    }

    private void showMenu() {
        this.removeAll();

        // Stops playing music if music that isn't menu music is currently playing and then starts menu music
        if (!menuMusicPlaying) {
            MusicPlayer.play(menuMusicPath);
            MusicPlayer.loop();
            menuMusicPlaying = true;
        }

        // Game logo
        gameLogo = new ImageIcon("src/Images/realms logo.png");
        titleLabel = new JLabel(gameLogo);
        titleLabel.setBounds(500, 20, 600, 300);
        this.add(titleLabel);

        // Play button
        playButton = new JButton("Play Game");
        playButton.setHorizontalAlignment(SwingConstants.CENTER);
        playButton.setVerticalAlignment(SwingConstants.CENTER);
        playButton.setIcon(new ImageIcon("src/Images/play game button.png"));
        playButton.setBounds(730, 400,150, 50);
        playButton.setBackground(Color.BLACK);
        playButton.addActionListener(e -> showGameMenu());
        this.add(playButton);

        // Options button
        optionsButton = new JButton("Options");
        optionsButton.setHorizontalAlignment(SwingConstants.CENTER);
        optionsButton.setVerticalAlignment(SwingConstants.CENTER);
        optionsButton.setIcon(new ImageIcon("src/Images/options button.png"));
        optionsButton.setBounds(730, 500,150, 50);
        optionsButton.setBackground(Color.BLACK);
        optionsButton.addActionListener(e -> showOptionsMenu());
        this.add(optionsButton);

        // Exit game button
        exitButton = new JButton("Exit Game");
        exitButton.setHorizontalAlignment(SwingConstants.CENTER);
        exitButton.setVerticalAlignment(SwingConstants.CENTER);
        exitButton.setIcon(new ImageIcon("src/Images/exit game button.png"));
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
        newGameButton = new JButton("New Game");
        newGameButton.setHorizontalAlignment(SwingConstants.CENTER);
        newGameButton.setIcon(new ImageIcon("src/Images/new game button.png"));
        newGameButton.setBounds(730, 400,150, 50);
        newGameButton.setBackground(Color.black);
        this.add(newGameButton);
        loadGameButton = new JButton("Load Game");
        loadGameButton.setHorizontalAlignment(SwingConstants.CENTER);
        loadGameButton.setIcon(new ImageIcon("src/Images/load game button.png"));
        loadGameButton.setBounds(730, 500,150, 50);
        loadGameButton.setFont(new Font("Arial", Font.BOLD, 20));
        loadGameButton.setBackground(Color.black);
        loadGameButton.setForeground(Color.white);
        this.add(loadGameButton);
        createBackToMenuButton();
        this.revalidate();
        this.repaint();
    }
    private void showOptionsMenu() {
        this.removeAll();
        this.add(titleLabel);
        JLabel musicVolumeLabel = new JLabel("Music Volume");
        musicVolumeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        musicVolumeLabel.setIcon(new ImageIcon("src/Images/music volume label.png"));
        musicVolumeLabel.setBounds(730, 400,150, 50);
        this.add(musicVolumeLabel);
        musicVolumeSlider = new JSlider(0, 100, (int) MusicPlayer.getCurrentVolume());
        musicVolumeSlider.setBounds(670, 450,250, 50);
        musicVolumeSlider.setMajorTickSpacing(1);
        musicVolumeSlider.setMinorTickSpacing(1);
        musicVolumeSlider.setPaintTicks(false);
        musicVolumeSlider.setPaintLabels(false);
        musicVolumeSlider.setBackground(Color.black);
        musicVolumeSlider.setForeground(Color.white);
        // Add a ChangeListener to the slider to update the volume
        musicVolumeSlider.addChangeListener(e -> {
            int volumeValue = musicVolumeSlider.getValue();
            MusicPlayer.setVolume(volumeValue);
        });
        this.add(musicVolumeSlider);
        createBackToMenuButton();
        this.revalidate();
        this.repaint();

    }
    private void createBackToMenuButton() {
        backToMenuButton = new JButton("Back to Menu");
        backToMenuButton.setHorizontalAlignment(SwingConstants.CENTER);
        backToMenuButton.setIcon(new ImageIcon("src/Images/back to menu button.png"));
        backToMenuButton.setBounds(705, 600,200, 50);
        backToMenuButton.setBackground(Color.black);
        backToMenuButton.addActionListener(e -> showMenu());
        this.add(backToMenuButton);
    }
}
