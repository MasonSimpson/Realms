package Panels;

import javax.swing.*;
import java.awt.*;
import Player.*;


public class CharacterCreationPanel extends JPanel {
    private JPanel parentPanel; // Reference to main menu panel
    private JLabel title;
    private JLabel playerNameLabel;
    private JTextField playerNameField;
    private JLabel genderLabel;
    private JComboBox<Player.Gender> genderComboBox;
    private JLabel raceLabel;
    private JComboBox<Player.Races> raceComboBox;
    private JButton createCharacterButton;
    public CharacterCreationPanel(JPanel parent) {
        this.parentPanel = parent;
        this.setLayout(null);
        this.setBackground(Color.black);
        // Character creation title
        title = new JLabel();
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setIcon(new ImageIcon("src/Images/ButtonLabels/character creation label.png"));
        title.setBounds(530, 0, 550, 70);
        this.add(title);
        // Player name label
        playerNameLabel = new JLabel();
        playerNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        playerNameLabel.setIcon(new ImageIcon("src/Images/ButtonLabels/player name label.png"));
        playerNameLabel.setBounds(630, 100, 200, 30);
        this.add(playerNameLabel);
        // Text field to enter player name
        playerNameField = new JTextField();
        playerNameField.setHorizontalAlignment(SwingConstants.CENTER);
        playerNameField.setFont(new Font("Arial", Font.BOLD, 20));
        playerNameField.setBounds(830, 100, 100, 30);
        playerNameField.setBackground(Color.white);
        this.add(playerNameField);
        // Label for gender
        genderLabel = new JLabel();
        genderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        genderLabel.setIcon(new ImageIcon("src/Images/ButtonLabels/player sex label.png"));
        genderLabel.setBounds(630,150,200,30);
        this.add(genderLabel);
        // JCombobox for gender
        genderComboBox = new JComboBox<>(Player.Gender.values());
        genderComboBox.setBounds(830,150,100,30);
        this.add(genderComboBox);
        // Label for race
        raceLabel = new JLabel();
        raceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        raceLabel.setIcon(new ImageIcon("src/Images/ButtonLabels/player race label.png"));
        raceLabel.setBounds(630, 200, 200, 30);
        this.add(raceLabel);
        // JCombobox for race
        raceComboBox = new JComboBox<>(Player.Races.values());
        raceComboBox.setBounds(830, 200, 100, 30);
        this.add(raceComboBox);
        // Create character button
        createCharacterButton = new JButton();
        createCharacterButton.setHorizontalAlignment(SwingConstants.CENTER);
        createCharacterButton.setIcon(new ImageIcon("src/Images/ButtonLabels/create character button.png"));
        createCharacterButton.addActionListener(e -> createCharacter());
        createCharacterButton.setBounds(730, 300,200, 50);
        createCharacterButton.setBackground(Color.black);
        this.add(createCharacterButton);
        this.revalidate();
        this.repaint();
    }
    private void createCharacter() {
        String name = playerNameField.getText();
        Player.Gender gender = (Player.Gender) genderComboBox.getSelectedItem();
        Player.Races race = (Player.Races) raceComboBox.getSelectedItem();
        Player player = new Player(name, race, gender);
        GamePanel.startGame(parentPanel, player);
    }
}
