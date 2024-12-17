package Panels;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import Player.*;
import GameLogic.*;

public class InteractionPanel extends JPanel{
    public InteractionPanel(Player player) {
        this.setLayout(null);
        this.setBackground(Color.black);
        this.setBorder(new MatteBorder(5,0,0,0,Color.white));
    }
}
