package Panels;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import GameLogic.*;
import Entities.Player.*;
import Music.*;

public class RealmSelectionPanel extends JPanel {
    private Player player;
    private InteractionPanel interactionPanel;
    private ActionPanel actionPanel;
    private AdventurePanel adventurePanel;
    private String musicPath = "src/Music/realm selection screen music.wav";
    private ArrayList<String> messages = new ArrayList<>();
    private final String[] battleButtonLabels = {"Begin", "Go Back"};
    public Realm realm;
    public Runnable action;


    public RealmSelectionPanel(Player player, ActionPanel action, InteractionPanel interaction) {
        this.setLayout(null);
        this.setBackground(Color.black);
        this.player = player;
        this.actionPanel = action;
        this.add(actionPanel);
        this.interactionPanel = interaction;
        this.add(interactionPanel);
        MusicPlayer.stop();
        MusicPlayer.play(musicPath);
        MusicPlayer.loop();
        selectRealm();
    }
    public void selectRealm() {
        messages.add("Select the Realm you wish to travel to, " + player.getName() + ".");
        String[] buttonLabels = {"Valamar", "Elvaria", "Khara", "Maguuma", "Dehara", "Return to Village", "Return to Menu"};
        Runnable[] buttonActions = {
                () -> {
                    messages.clear();
                    messages.add("You have chosen to travel to the plains of Valamar, where Goblins and Ogres wander...");
                    realm = new Realm(Realm.RealmNames.Valamar, 1, player);
                    MusicPlayer.stop();
                    musicPath = "src/Music/elyos music.wav";
                    MusicPlayer.play(musicPath);
                    MusicPlayer.loop();
                    Runnable[] battleActions = {
                            this::beginAdventuring,
                            this::returnToRealmSelect
                    };
                    interactionPanel.setGameMessages(messages, action);
                    interactionPanel.addButtons(battleButtonLabels, battleActions);
                },
                () -> {
                    messages.clear();
                    messages.add("<html>You have chosen to travel to the snowy wastelands of Elvara. This once beautiful land used to be the Elf kingdom. However, it has been ravaged by blizzards and made uninhabitable. Now, the only things lurking in the snow are bloodthirsty wolves and giant spiders...");
                    realm = new Realm(Realm.RealmNames.Elvaria, 6, player);
                    MusicPlayer.stop();
                    musicPath = "src/Music/dorne music.wav";
                    MusicPlayer.play(musicPath);
                    MusicPlayer.loop();
                    Runnable[] battleActions = {
                            this::beginAdventuring,
                            this::returnToRealmSelect
                    };
                    interactionPanel.setGameMessages(messages, action);
                    interactionPanel.addButtons(battleButtonLabels, battleActions);
                },
                () -> {
                    messages.clear();
                    messages.add("<html>You have chosen to travel to Khara, home of the giants. The giants and trolls here were once friendly towards adventurers, but that was centuries ago. After the war, they became hostile, and now they kill everything on sight...");
                    realm = new Realm(Realm.RealmNames.Khara, 11, player);
                    MusicPlayer.stop();
                    musicPath = "src/Music/highgarden music.wav";
                    MusicPlayer.play(musicPath);
                    MusicPlayer.loop();
                    Runnable[] battleActions = {
                            this::beginAdventuring,
                            this::returnToRealmSelect
                    };
                    interactionPanel.setGameMessages(messages, action);
                    interactionPanel.addButtons(battleButtonLabels, battleActions);
                },
                () -> {
                    messages.clear();
                    messages.add("<html>You have chosen to travel to Maguuma, a once beautiful land that fell victim to volcanic activity. Most of the creatures that inhabited this land have left, but monsters that can live in extreme temperatures have made their home here...");
                    realm = new Realm(Realm.RealmNames.Maguuma, 21, player);
                    MusicPlayer.stop();
                    musicPath = "src/Music/maguuma music.wav";
                    MusicPlayer.play(musicPath);
                    MusicPlayer.loop();
                    Runnable[] battleActions = {
                            this::beginAdventuring,
                            this::returnToRealmSelect
                    };
                    interactionPanel.setGameMessages(messages, action);
                    interactionPanel.addButtons(battleButtonLabels, battleActions);
                },
                () -> {
                    messages.clear();
                    messages.add("<html>You have chosen to travel to Dehara, the land of the damned. Dehara is the most dangerous realm. The demons that roam here will torture you relentlessly... ");
                    realm = new Realm(Realm.RealmNames.Dehara, 31, player);
                    MusicPlayer.stop();
                    musicPath = "src/Music/underworld music.wav";
                    MusicPlayer.play(musicPath);
                    MusicPlayer.loop();
                    Runnable[] battleActions = {
                            this::beginAdventuring,
                            this::returnToRealmSelect
                    };
                    interactionPanel.setGameMessages(messages, null);
                    interactionPanel.addButtons(battleButtonLabels, battleActions);
                },
                () -> {
                    MusicPlayer.stop();
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                    frame.getContentPane().removeAll();
                    frame.getContentPane().add(actionPanel);
                    frame.getContentPane().add(interactionPanel);
                    AdventurersVillagePanel adventurersVillagePanel = new AdventurersVillagePanel(player, actionPanel, interactionPanel);
                    frame.getContentPane().add(adventurersVillagePanel);
                    adventurersVillagePanel.villagePrompt();
                    frame.revalidate();
                    frame.repaint();
                },
                () -> {
                    MusicPlayer.stop();
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                    frame.getContentPane().removeAll();
                    frame.getContentPane().add(new MainMenuPanel());
                    frame.revalidate();
                    frame.repaint();
                }
        };
        interactionPanel.setGameMessages(messages, null);
        interactionPanel.addButtons(buttonLabels, buttonActions);
    }
    private void beginAdventuring() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.getContentPane().removeAll();
        interactionPanel.clearButtons();
        adventurePanel = new AdventurePanel(interactionPanel, actionPanel, player, realm);
        adventurePanel.setBounds(0, 0, 1600, 1000);
        adventurePanel.adventure();
        frame.getContentPane().add(adventurePanel);
        frame.revalidate();
        frame.repaint();
    }
    private void returnToRealmSelect() {
        MusicPlayer.stop();
        musicPath = "src/Music/realm selection screen music.wav";
        MusicPlayer.play(musicPath);
        MusicPlayer.loop();
        messages.clear();
        selectRealm();
    }
}
