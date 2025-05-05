package Utils;

import Entities.Player.*;

import java.io.*;
import java.util.ArrayList;

public class SaveUtils {
    private static final String SAVE_FOLDER = "saves";

    public static void savePlayer(Player player) {
        try {
            File dir = new File(SAVE_FOLDER);
            if (!dir.exists())
                dir.mkdirs();

            File file = new File(dir, player.getName() + ".dat");
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(player);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Player loadPlayer(String fileName) {
        try {
            File file = new File(SAVE_FOLDER, fileName);
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            Player player = (Player) in.readObject();
            in.close();
            return player;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String[] listSaveFiles() {
        File dir = new File(SAVE_FOLDER);
        if (!dir.exists() || !dir.isDirectory())
            return new String[0];
        return dir.list((d, name) -> name.endsWith(".dat"));
    }
}
