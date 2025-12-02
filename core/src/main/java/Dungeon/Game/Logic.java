package Dungeon.Game;

import Dungeon.Game.Entities.Entity;
import Dungeon.Game.Entities.EntityManager;
import com.badlogic.gdx.graphics.Texture;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Logic {

    private final HashMap<String, Texture> textures;
    private final EntityManager eM;

    public Logic() {
        this.eM = EntityManager.getInstance();
        this.textures = new HashMap<>();
        setUpTextures();

        eM.addTest();
    }

    public void update(){
        eM.update();
    }

    private void setUpTextures() {
        File folder = new File("assets/sprites");
        File[] subfolders = folder.listFiles();
        assert subfolders != null;
        for (File sub : subfolders) {
            File[] files = sub.listFiles();
            assert files != null;
            for (File img : files) {
                System.out.println("Loading " + img.getName());
                textures.put(img.getName(), new Texture(img.getAbsolutePath()));
            }
        }
    }

    public Texture getTexture(String name){return textures.get(name + ".png");}
    public ArrayList<Entity> getEntities() {
        return eM.getEntities();
    }
}
