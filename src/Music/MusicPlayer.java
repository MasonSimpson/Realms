package Music;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayer {
    private static Clip clip;
    private static FloatControl volumeControl;
    private static float currentVolume = 70;

    public static void play(String path) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            setVolume(currentVolume);
            clip.start();
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void setVolume(float volume) {
        currentVolume = volume;
        if (volumeControl != null)
        {
            // Convert volume percentage (0-100) to the scale the volume control expects
            float min = volumeControl.getMinimum();
            float max = volumeControl.getMaximum();
            float range = max - min;
            float gain = min + (range * (volume / 100f));
            volumeControl.setValue(gain);
        }
    }

    public static void stop() {
        if (clip != null && clip.isRunning())
        {
            clip.stop();
        }
    }
    public static void loop() {
        if (clip != null)
        {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
    public static float getCurrentVolume() {
        return currentVolume;
    }
}
