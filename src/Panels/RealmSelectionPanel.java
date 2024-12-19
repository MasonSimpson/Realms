package Panels;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import GameLogic.*;
import Player.*;
import Entities.*;
import Music.*;

public class RealmSelectionPanel extends JPanel {
    private Player player;
    private InteractionPanel interactionPanel;
    private ActionPanel actionPanel;
    private String musicPath = "src/Music/realm selection screen music.wav";
    private ArrayList<String> messages = new ArrayList<>();
    private final String[] battleButtonLabels = {"Begin", "Go Back"};
    public Realm realm;
    public Runnable action;


    public RealmSelectionPanel(Player player) {
        this.setLayout(null);
        this.setBackground(Color.black);
        this.player = player;
        actionPanel = new ActionPanel(player);
        actionPanel.setBounds(0,0, 1600, 700);
        this.add(actionPanel);
        interactionPanel = new InteractionPanel(player);
        interactionPanel.setBounds(0,700, 1600, 300);
        this.add(interactionPanel);
        MusicPlayer.stop();
        MusicPlayer.play(musicPath);
        MusicPlayer.loop();
        selectRealm();
    }
    public void selectRealm() {
        messages.add("Select the Realm you wish to travel to, " + player.getName() + ".");
        String[] buttonLabels = {"Elyos", "Dorne", "Highgarden", "Maguuma", "Underworld", "Return to Village", "Return to Menu"};
        Runnable[] buttonActions = {
                () -> {
                    messages.clear();
                    messages.add("You have chosen to travel to the plains of Elyos, where Goblins and Ogres wander...");
                    realm = new Realm(Realm.RealmNames.Elyos, 1, player);
                    MusicPlayer.stop();
                    musicPath = "src/Music/elyos music.wav";
                    MusicPlayer.play(musicPath);
                    MusicPlayer.loop();
                    action = () -> {
                        System.out.println("Preparing to fight in Elyos...");
                    };
                    Runnable[] battleActions = {
                            () -> System.out.println("Battling in Elyos..."),
                            () -> {
                                MusicPlayer.stop();
                                musicPath = "src/Music/realm selection screen music.wav";
                                MusicPlayer.play(musicPath);
                                MusicPlayer.loop();
                                messages.clear();
                                selectRealm();
                            }
                    };
                    interactionPanel.setGameMessages(messages, action);
                    interactionPanel.addButtons(battleButtonLabels, battleActions);
                },
                () -> {
                    messages.clear();
                    messages.add("<html>You have chosen to travel to the snowy wastelands of Dorne. This once beautiful land has been ravaged by blizzards and made uninhabitable. Now, the only thing lurking in the snow are bloodthirsty wolves and giant spiders...");
                    realm = new Realm(Realm.RealmNames.Dorne, 6, player);
                    MusicPlayer.stop();
                    musicPath = "src/Music/dorne music.wav";
                    MusicPlayer.play(musicPath);
                    MusicPlayer.loop();
                    action = () -> {
                        System.out.println("Preparing to fight in Dorne...");
                    };
                    Runnable[] battleActions = {
                            () -> System.out.println("Battling in Elyos..."),
                            () -> {
                                MusicPlayer.stop();
                                musicPath = "src/Music/realm selection screen music.wav";
                                MusicPlayer.play(musicPath);
                                MusicPlayer.loop();
                                messages.clear();
                                selectRealm();
                            }
                    };
                    interactionPanel.setGameMessages(messages, action);
                    interactionPanel.addButtons(battleButtonLabels, battleActions);
                },
                () -> {
                    messages.clear();
                    messages.add("<html>You have chosen to travel to Highgarden. The giants and trolls that live here were once friendly towards adventurers, but that was centuries ago. After the war, they became hostile, and now they kill everything on sight...");
                    realm = new Realm(Realm.RealmNames.Highgarden, 11, player);
                    MusicPlayer.stop();
                    musicPath = "src/Music/highgarden music.wav";
                    MusicPlayer.play(musicPath);
                    MusicPlayer.loop();
                    action = () -> {
                        System.out.println("Preparing to fight in Highgarden...");
                    };
                    Runnable[] battleActions = {
                            () -> System.out.println("Battling in Elyos..."),
                            () -> {
                                MusicPlayer.stop();
                                musicPath = "src/Music/realm selection screen music.wav";
                                MusicPlayer.play(musicPath);
                                MusicPlayer.loop();
                                messages.clear();
                                selectRealm();
                            }
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
                    action = () -> {
                        System.out.println("Preparing to fight in Maguuma...");
                    };
                    Runnable[] battleActions = {
                            () -> System.out.println("Battling in Elyos..."),
                            () -> {
                                MusicPlayer.stop();
                                musicPath = "src/Music/realm selection screen music.wav";
                                MusicPlayer.play(musicPath);
                                MusicPlayer.loop();
                                messages.clear();
                                selectRealm();
                            }
                    };
                    interactionPanel.setGameMessages(messages, action);
                    interactionPanel.addButtons(battleButtonLabels, battleActions);
                },
                () -> {
                    messages.clear();
                    messages.add("You have chosen to travel to the Underworld, the most dangerous realm. The demons here will torture you until you beg for mercy...");
                    realm = new Realm(Realm.RealmNames.Underworld, 31, player);
                    MusicPlayer.stop();
                    musicPath = "src/Music/underworld music.wav";
                    MusicPlayer.play(musicPath);
                    MusicPlayer.loop();
                    action = () -> {
                        System.out.println("Preparing to fight in Underworld...");
                    };
                    Runnable[] battleActions = {
                            () -> System.out.println("Battling in Elyos..."),
                            () -> {
                                MusicPlayer.stop();
                                musicPath = "src/Music/realm selection screen music.wav";
                                MusicPlayer.play(musicPath);
                                MusicPlayer.loop();
                                messages.clear();
                                selectRealm();
                            }
                    };
                    interactionPanel.setGameMessages(messages, action);
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
}
