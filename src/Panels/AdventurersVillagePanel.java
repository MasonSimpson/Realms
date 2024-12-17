package Panels;

import Player.*;
import GameLogic.*;
import Music.*;
import javax.swing.*;
import java.awt.*;

public class AdventurersVillagePanel extends JPanel {
    private Player player;
    private String villageMusicPath = "src/Music/village music.wav";

    public AdventurersVillagePanel(Player player) {
        this.setLayout(null);
        this.setBackground(Color.black);
        this.player = player;
        MusicPlayer.stop();
        MusicPlayer.play(villageMusicPath);
        MusicPlayer.loop();
    }

}
