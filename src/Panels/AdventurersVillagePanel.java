package Panels;

import Entities.Player.*;
import Music.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AdventurersVillagePanel extends JPanel {
    private Player player;
    private String villageMusicPath = "src/Music/village music.wav";
    private ActionPanel actionPanel;
    private InteractionPanel interactionPanel;
    private ArrayList<String> messages = new ArrayList<>();

    public AdventurersVillagePanel(Player player, ActionPanel action, InteractionPanel interaction) {
        this.setLayout(null);
        this.setBackground(Color.black);
        this.player = player;
        this.actionPanel = action;
        this.interactionPanel = interaction;
        MusicPlayer.stop();
        MusicPlayer.play(villageMusicPath);
        MusicPlayer.loop();
    }
    public void firstTime() {
        messages.add("""
                <html>
                You enter the gate of the Adventurer's Village, eager to begin your new career as an adventurer. The mayor of the village greets you at the gate, with a friendly smile spread wide across his face.
                "Greetings, new adventurer! It's a pleasure to meet you. I am the mayor of the Adventurer's Village, welcome!" The mayor extends his hand out and embraces you in a firm handshake before continuing.
                "All newcomers receive a brand new house here, free of charge! You can use it to rest between adventures." You can also visit our Shop and use your hard earned gold coins to buy weapons, armor, and
                stock up on Health potions. Again, welcome to our village, newcomer! If you have any questions feel free to stop by my office."<br>
                Click to continue
                """);
        messages.add("""
                <html>
                You thank the mayor for his help, and he smiles again before turning and walking away. You continue on your way through the village, admiring the sheer amount of buildings and people that are around.
                Eventually you stumble across your house, and you walk inside to be greeted with a single room containing a small bed, fireplace, and a single window illuminating the room. You quicly realize that you
                are tired from all of your travels, and you decide to go to bed, and you wake up the next morning, reenergized... <br>
                Click to continue
                """);
        interactionPanel.setGameMessages(messages, this::villagePrompt);
    }
    public void villagePrompt() {
        messages.clear();
        messages.add("What would you like to do today, " + player.getName() + "?");
        interactionPanel.setGameMessages(messages, null);

        String[] buttonLabels = {"Enter Realm", "Visit Shop", "View Inventory", "Visit Mayor", "Sleep", "Return to Menu"};
        Runnable[] actions = {
                () -> {
                    MusicPlayer.stop();
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                    frame.getContentPane().removeAll();
                    frame.setContentPane(new RealmSelectionPanel(player, actionPanel, interactionPanel));
                    frame.revalidate();
                    frame.repaint();
                },
                () -> System.out.println("Visiting shop..."),
                () -> {
                    InventoryPopout inventoryPopout = new InventoryPopout(player);
                    inventoryPopout.showInventory((JFrame) SwingUtilities.getWindowAncestor(this));
                },
                () -> System.out.println("Visiting the mayor..."),
                () -> {
                    messages.clear();
                    messages.add("You decide to go to sleep, and you wake up the next day...");
                    interactionPanel.setGameMessages(messages, null);
                    interactionPanel.clearButtons();
                    Timer timer = new Timer(3000, e ->{
                        villagePrompt();
                    });
                    timer.setRepeats(false);
                    timer.start();
                },
                () -> {
                    MusicPlayer.stop();
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                    frame.getContentPane().removeAll();
                    frame.setContentPane(new MainMenuPanel());
                    frame.revalidate();
                    frame.repaint();
                }
        };
        interactionPanel.addButtons(buttonLabels, actions);
    }
    public void returnToVillage() {
        messages.clear();
        messages.add("<html>You made it back to the safety of your home. Exhausted from your travels, you call it a day and decide to sleep, and you wake up the next morning...");
        interactionPanel.setGameMessages(messages, null);
        Timer timer = new Timer(4000, e -> {
            villagePrompt();
        });
        timer.setRepeats(false);
        timer.start();
    }

}

