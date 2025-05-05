package Panels;

import Music.MusicPlayer;
import Entities.Player.*;
import GameLogic.*;
import Entities.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AdventurePanel extends JPanel {
    private InteractionPanel interactionPanel;
    private ActionPanel actionPanel;
    private Player player;
    private Realm realm;
    private JLabel playerHealthLabel;
    private JLabel monsterHealthLabel;
    private ArrayList<String> messages = new ArrayList<>();
    private Timer timer;
    private Monster monster;
    private int goldEarned = 0;
    private int xpEarned = 0;
    public AdventurePanel(InteractionPanel interaction, ActionPanel action, Player player, Realm realm) {
        this.setLayout(null);
        this.setBackground(Color.black);
        this.interactionPanel = interaction;
        this.actionPanel = action;
        this.player = player;
        this.realm = realm;

        actionPanel.setBounds(0, 0, 1600, 700);
        interactionPanel.setBounds(0, 700, 1600, 300);
        this.add(interactionPanel);
        this.add(actionPanel);

        setActionPanel();
    }
    public void setActionPanel() {
        actionPanel.removeAll();
        playerHealthLabel = new JLabel("<html>" + player.getName() + "'s health: " + player.getHealth());
        playerHealthLabel.setFont(playerHealthLabel.getFont().deriveFont(15f));
        playerHealthLabel.setForeground(Color.white);
        playerHealthLabel.setBackground(Color.black);
        playerHealthLabel.setBounds(100, 30, 400, 30);
        actionPanel.add(playerHealthLabel);
        this.revalidate();
        this.repaint();
    }
    public void updateActionPanel() {
        playerHealthLabel.setText("<html>" + player.getName() + "'s health: " + player.getHealth());
        monsterHealthLabel.setText("<html>" + monster.type + "'s health: " + monster.health);
        this.revalidate();
        this.repaint();
    }
    public void adventure() {
        messages.add("Traveling through " + realm.name + "...");
        monster = new Monster(realm);
        interactionPanel.setGameMessages(messages, null);
        timer = new Timer(3000, e -> {
            messages.clear();
            messages.add("<html>You encounter a Level " + monster.getLevel() + " " + monster.type + ", and it doesn't look like it's going to back down...<br>What would you like to do, " + player.getName() + "?");
            monsterHealthLabel = new JLabel("<html>" + monster.type + "'s health: " + monster.health);
            monsterHealthLabel.setFont(monsterHealthLabel.getFont().deriveFont(15f));
            monsterHealthLabel.setForeground(Color.white);
            monsterHealthLabel.setBackground(Color.black);
            monsterHealthLabel.setBounds(100, 70, 400, 30);
            actionPanel.add(monsterHealthLabel);
            this.revalidate();
            this.repaint();
            interactionPanel.setGameMessages(messages, null);
            String[] buttonLabels = {"Battle", "View Inventory", "Flee"};
            Runnable[] buttonActions = {
                    () -> {
                        messages.clear();
                        interactionPanel.clearButtons();
                        messages.add("You begin your battle with the " + monster.type + "...");
                        interactionPanel.setGameMessages(messages, null);
                        Timer battleStartTimer = new Timer(3000, startBattle -> {
                            battlePrompt(new Battle(player, monster));
                        });
                        battleStartTimer.setRepeats(false);
                        battleStartTimer.start();
                    },
                    () -> {
                        InventoryPopout inventoryPopout = new InventoryPopout(player);
                        inventoryPopout.showInventory((JFrame) SwingUtilities.getWindowAncestor(this));
                    },
                    () -> {
                        messages.clear();
                        messages.add("<html>You decide to flee from the " + monster.type + ", and you escape back to the Adventurer's Village...<br>" +
                                "You earned " + goldEarned + " gold and " + xpEarned + " XP during your adventure.<br>" +
                                "Traveling back to Adventurer's Village...");
                        interactionPanel.setGameMessages(messages, null);
                        interactionPanel.clearButtons();
                        actionPanel.removeAll();
                        Timer tempTimer = new Timer(6000, returnToVillage -> {
                            MusicPlayer.stop();
                            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                            frame.getContentPane().removeAll();
                            frame.getContentPane().add(actionPanel);
                            frame.getContentPane().add(interactionPanel);
                            AdventurersVillagePanel adventurersVillagePanel = new AdventurersVillagePanel(player, actionPanel, interactionPanel);
                            frame.getContentPane().add(adventurersVillagePanel);
                            adventurersVillagePanel.returnToVillage();
                            frame.revalidate();
                            frame.repaint();
                        });
                        tempTimer.setRepeats(false);
                        tempTimer.start();
                    }
            };
            interactionPanel.addButtons(buttonLabels, buttonActions);
        });
        timer.setRepeats(false);
        timer.start();
    }
    public void battlePrompt(Battle battle) {
        messages.clear();
        messages.add("What would you like to do, " + player.getName() + "?");
        interactionPanel.setGameMessages(messages, null);
        String[] buttonLabels = {"Attack", "Heal", "View Inventory"};
        Runnable[] buttonActions = {
                () -> {
                    messages.clear();
                    interactionPanel.clearButtons();
                    int damageDealt = battle.attack();
                    messages.add("<html>You attack the " + monster.type + "!<br>" +
                            "You dealt " + damageDealt + " damage to the " + monster.type + ".");
                    interactionPanel.setGameMessages(messages, null);
                    if (monster.health < 0) {
                        monster.health = 0;
                    }
                    updateActionPanel();
                    Timer timer = new Timer(3000, winner -> {
                        determineWinner(battle,2);
                    });
                    timer.setRepeats(false);
                    timer.start();

                },
                () -> {
                    messages.clear();
                    interactionPanel.clearButtons();
                    if (player.getHealingPotions() == 0) {
                        messages.add("You do not have any healing potions to use.");
                        interactionPanel.setGameMessages(messages, null);
                        Timer timer = new Timer(3000, restartBattlePrompt -> {
                            battlePrompt(battle);
                        });
                        timer.setRepeats(false);
                        timer.start();
                    }
                    else {
                        int damageHealed = battle.heal();
                        messages.add("<html>You use one of your health potions to heal!<br>" +
                                "You restored " + damageHealed + " health.<br>" +
                                "You now have " + player.getHealingPotions() + " health potions.");
                        interactionPanel.setGameMessages(messages, null);
                        updateActionPanel();
                        Timer timer = new Timer(3000, winner -> {
                            determineWinner(battle,2);
                        });
                        timer.setRepeats(false);
                        timer.start();
                    }
                },
                () -> {
                    InventoryPopout inventoryPopout = new InventoryPopout(player);
                    inventoryPopout.showInventory((JFrame) SwingUtilities.getWindowAncestor(this));
                }
        };
        interactionPanel.addButtons(buttonLabels, buttonActions);
    }
    public void monsterAction(Battle battle) {
        Timer userBattlePrompt = new Timer(2500, winner -> {
            determineWinner(battle, 1);
        });
        messages.clear();
        interactionPanel.clearButtons();
        messages.add("Waiting for monster action...");
        interactionPanel.setGameMessages(messages, null);
        Timer timer = new Timer(2500, monsterAction -> {
            messages.clear();
            int monsterChoice = battle.monsterChoice();
            switch (monsterChoice) {
                case 0:
                    messages.add("<html>The " + monster.type + " attacks you!<br>" +
                            "The " + monster.type + " dealt " + monster.damage + " damage to you.");
                    interactionPanel.setGameMessages(messages, null);
                    updateActionPanel();
                    break;
                case 1:
                    messages.add("<html>The " + monster.type + " uses a healing potion!<br>" +
                            "The " + monster.type + " restored " + battle.monsterHealthHealed + " health.");
                    interactionPanel.setGameMessages(messages, null);
                    updateActionPanel();
                    break;
                default:
                    break;
            }
            userBattlePrompt.setRepeats(false);
            userBattlePrompt.start();
        });
        timer.setRepeats(false);
        timer.start();
    }
    public void determineWinner(Battle battle, int turn) {
        if (monster.health == 0) {
            battle.calculateEarnings();
            player.addGold(battle.goldWon);
            boolean leveledUp = player.addXp(battle.xpEarned);
            goldEarned += battle.goldWon;
            xpEarned += battle.xpEarned;
            monster.die();
            actionPanel.removeAll();
            setActionPanel();
            messages.clear();
            StringBuilder messageBuilder = new StringBuilder("<html>");
            messageBuilder.append("You have successfully defeated the ").append(monster.type).append("!<br>");
            messageBuilder.append("You have earned ").append(battle.goldWon).append(" gold and ").append(battle.xpEarned).append(" xp.<br>");

            if (leveledUp) {
                messageBuilder.append("You have leveled up! Your health has raised to ")
                        .append(player.getHealth()).append(".<br>");
            }

            messageBuilder.append("What would you like to do?");
            messages.add(messageBuilder.toString());
            interactionPanel.setGameMessages(messages, null);
            String[] buttonLabels = {"Continue Adventure", "View Inventory", "Return to Village"};
            Runnable[] buttonActions = {
                    () -> {
                        messages.clear();
                        interactionPanel.clearButtons();
                        adventure();
                    },
                    () -> {
                        InventoryPopout inventoryPopout = new InventoryPopout(player);
                        inventoryPopout.showInventory((JFrame) SwingUtilities.getWindowAncestor(this));
                    },
                    () -> {
                        messages.clear();
                        interactionPanel.clearButtons();
                        messages.add("Returning to the Adventurer's Village...");
                        interactionPanel.setGameMessages(messages, null);
                        actionPanel.removeAll();
                        Timer timer = new Timer(5000, returnToVillage -> {
                            MusicPlayer.stop();
                            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                            frame.getContentPane().removeAll();
                            frame.getContentPane().add(actionPanel);
                            frame.getContentPane().add(interactionPanel);
                            AdventurersVillagePanel adventurersVillagePanel = new AdventurersVillagePanel(player, actionPanel, interactionPanel);
                            frame.getContentPane().add(adventurersVillagePanel);
                            adventurersVillagePanel.returnToVillage();
                            frame.revalidate();
                            frame.repaint();
                        });
                        timer.setRepeats(false);
                        timer.start();
                    }
            };
            interactionPanel.addButtons(buttonLabels, buttonActions);
        }
        else if (player.getHealth() == 0) {
            messages.clear();
            monster.die();
            messages.add("You have died. Respawning you in the Adventurer's Village...");
            interactionPanel.setGameMessages(messages, null);
            Timer timer = new Timer(5000, returnToVillage -> {
                AdventurersVillagePanel adventurersVillagePanel = new AdventurersVillagePanel(player, actionPanel, interactionPanel);
                Timer villagePromptTimer = new Timer(2500, restartVillagePrompt -> {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                    frame.getContentPane().removeAll();
                    frame.getContentPane().add(actionPanel);
                    frame.getContentPane().add(interactionPanel);
                    frame.getContentPane().add(adventurersVillagePanel);
                    adventurersVillagePanel.villagePrompt();
                    player.setHealth(player.maxHealth);
                    frame.revalidate();
                    frame.repaint();
                });
                messages.clear();
                messages.add("You have been revived by the clerics in the Adventurer's Village. You awake in your house, your health fully restored...");
                interactionPanel.setGameMessages(messages, null);
                actionPanel.removeAll();
                this.revalidate();
                this.repaint();
                villagePromptTimer.setRepeats(false);
                villagePromptTimer.start();
            });
            timer.setRepeats(false);
            timer.start();
        }
        else {
            if (turn == 1) {
                battlePrompt(battle);
            }
            else if (turn == 2) {
                monsterAction(battle);
            }
        }
    }
}
