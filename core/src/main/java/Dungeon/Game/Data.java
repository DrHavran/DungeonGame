package Dungeon.Game;

import com.badlogic.gdx.graphics.Texture;

import java.io.File;
import java.util.HashMap;

public class Data {
    private static Data instance;
    public static Data getInstance() {
        if (instance == null) instance = new Data();
        return instance;
    }

    private final HashMap<String, Texture> textures;

    public Data(){
        this.textures = new HashMap<>();
        setUpTextures();
    }

    private void setUpTextures() {
        File folder = new File("assets/sprites");
        File[] subfolders = folder.listFiles();
        assert subfolders != null;
        for (File sub : subfolders) {
            File[] files = sub.listFiles();
            assert files != null;
            for (File img : files) {
                System.out.println("Loaded " + img.getName());
                textures.put(img.getName(), new Texture(img.getAbsolutePath()));
            }
        }
    }

    public Texture get(String name){ return textures.get(name + ".png"); }
}
