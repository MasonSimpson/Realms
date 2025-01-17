package Panels;

import Music.MusicPlayer;

import javax.swing.*;
import java.awt.*;

public class OptionsPanel extends JPanel {
    private JSlider musicVolumeSlider;
    private JLabel musicVolumeLabel;

    public OptionsPanel() {
        this.setLayout(null);
        this.setBackground(Color.black);

        // Music Volume Label
        musicVolumeLabel = new JLabel("Music Volume");
        musicVolumeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        musicVolumeLabel.setIcon(new ImageIcon("src/Images/ButtonLabels/music volume label.png"));
        musicVolumeLabel.setBounds(730, 150, 150, 50);
        this.add(musicVolumeLabel);

        // Music Volume Slider
        musicVolumeSlider = new JSlider(0, 100, (int) MusicPlayer.getCurrentVolume());
        musicVolumeSlider.setBounds(670, 200, 250, 50);
        musicVolumeSlider.setBackground(Color.black);
        musicVolumeSlider.setForeground(Color.white);

        musicVolumeSlider.addChangeListener(e -> {
            int volumeValue = musicVolumeSlider.getValue();
            MusicPlayer.setVolume(volumeValue);
        });

        this.add(musicVolumeSlider);
        this.revalidate();
        this.repaint();
    }
}
