package Panels;

import Music.MusicPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IntroPanel extends JPanel {
    private float alpha = 0.0f; // Transparency level for fading
    private Timer fadeInTimer, fadeOutTimer, titleFadeInTimer, flashTimer;
    private boolean showHexzyText = true;
    private boolean showTitleImage = false;
    private boolean showClickText = false;
    private float clickTextAlpha = 0.0f;
    private boolean increasingAlpha = true;
    private Image titleImage;
    private Runnable onComplete;
    private String menuMusicPath = "src/Music/menu music 4.wav";

    public IntroPanel(Runnable onComplete) {
        this.onComplete = onComplete;
        this.setBackground(Color.BLACK);

        // Load title image
        titleImage = new ImageIcon("src/Images/ButtonLabels/realms logo.png").getImage();

        startHexzyAnimation();
    }

    private void startHexzyAnimation() {
        // Start music
        MusicPlayer.play(menuMusicPath);
        MusicPlayer.loop();

        fadeInTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alpha += 0.05f;
                if (alpha >= 1.0f) {
                    alpha = 1.0f;
                    fadeInTimer.stop();
                    startHexzyFadeOut();
                }
                repaint();
            }
        });
        fadeInTimer.start();
    }

    private void startHexzyFadeOut() {
        fadeOutTimer = new Timer(70, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alpha -= 0.03f;
                if (alpha <= 0.0f) {
                    alpha = 0.0f;
                    fadeOutTimer.stop();
                    showHexzyText = false;
                    startTitleFadeIn();
                }
                repaint();
            }
        });
        fadeOutTimer.start();
    }

    private void startTitleFadeIn() {
        showTitleImage = true;
        alpha = 0.0f; // Reset alpha for the title fade-in
        titleFadeInTimer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alpha += 0.05f;
                if (alpha >= 1.0f) {
                    alpha = 1.0f;
                    titleFadeInTimer.stop();
                    startClickToStartText();
                }
                repaint();
            }
        });
        titleFadeInTimer.start();
    }

    private void startClickToStartText() {
        showClickText = true;

        flashTimer = new Timer(70, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (increasingAlpha) {
                    clickTextAlpha += 0.1f;
                    if (clickTextAlpha >= 1.0f) {
                        clickTextAlpha = 1.0f;
                        increasingAlpha = false;
                    }
                } else {
                    clickTextAlpha -= 0.1f;
                    if (clickTextAlpha <= 0.2f) {
                        clickTextAlpha = 0.2f;
                        increasingAlpha = true;
                    }
                }
                repaint();
            }
        });
        flashTimer.start();

        // Allow clicking to transition to the main menu
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (onComplete != null) {
                    onComplete.run();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (showHexzyText) {
            // Draw "Hexzy presents..." text
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 48));
            FontMetrics fm = g2d.getFontMetrics();
            String text = "Hexzy presents...";
            int x = (getWidth() - fm.stringWidth(text)) / 2;
            int y = getHeight() / 2;
            g2d.drawString(text, x, y);
        }

        if (showTitleImage) {
            // Draw the title image with fade-in effect
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            int titleX = 400;
            int titleY = 50;
            int titleWidth = 800;
            int titleHeight = 500;
            g2d.drawImage(titleImage, titleX, titleY, titleWidth, titleHeight, null);
        }

        if (showClickText) {
            // Draw "Click to Start" text with flashing transparency
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, clickTextAlpha));
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 36));
            FontMetrics fm = g2d.getFontMetrics();
            String clickText = "Click to Start";
            int x = (getWidth() - fm.stringWidth(clickText)) / 2;
            int y = 800;
            g2d.drawString(clickText, x, y);
        }
    }
}
