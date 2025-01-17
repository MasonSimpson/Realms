package Music;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MusicPlayer {
    private static Clip currentClip;
    private static String musicPath;
    private static FloatControl volumeControl;
    private static float currentVolume = 70; // Default volume level (0-100)
    private static final Map<String, Clip> clipCache = new HashMap<>();

    public static void play(String path) {
        try {
            musicPath = path;
            stop(); // Stop any currently playing track
            currentClip = clipCache.computeIfAbsent(path, MusicPlayer::loadClip);
            if (currentClip == null) {
                throw new IllegalArgumentException("Audio file could not be loaded: " + path);
            }
            volumeControl = (FloatControl) currentClip.getControl(FloatControl.Type.MASTER_GAIN);
            setVolume(currentVolume); // Apply the initial volume
            currentClip.setFramePosition(0); // Reset to the beginning
            currentClip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loop() {
        if (currentClip != null) {
            currentClip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the clip indefinitely
        }
    }

    public static void stop() {
        if (currentClip != null && currentClip.isRunning()) {
            currentClip.stop();
        }
    }

    public static void setVolume(float volume) {
        currentVolume = volume;
        if (volumeControl != null) {
            float min = volumeControl.getMinimum();
            float max = volumeControl.getMaximum();
            float gain = min + (max - min) * (volume / 100f);
            volumeControl.setValue(gain); // Directly apply the volume
        }
    }

    public static float getCurrentVolume() {
        return currentVolume;
    }

    public static String getMusicPath() {
        return musicPath;
    }

    private static Clip loadClip(String path) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void cleanup() {
        clipCache.values().forEach(clip -> {
            if (clip.isRunning()) {
                clip.stop();
            }
            clip.close();
        });
        clipCache.clear();
    }
}
