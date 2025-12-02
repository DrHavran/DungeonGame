package Dungeon.Game.Room;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

public class Tile {
    private final Sprite sprite;
    private final ArrayList<String> walls;
    private boolean isEmpty;

    public Tile(float x, float y, Texture texture){
        this.sprite = new Sprite();
        this.walls = new ArrayList<>();
        this.isEmpty = false;

        sprite.setTexture(texture);

        sprite.setPosition(x, y);
    }

    public void resize(){
        int regionX = 6;
        int regionY = 30;
        int regionWidth = 32;
        int regionHeight = 32;

        for(String wall : walls){
            switch (wall){
                case "up":
                    regionY = 0;
                    regionHeight += 30;
                    break;
                case "down":
                    regionHeight += 7;
                    break;
                case "right":
                    regionWidth += 6;
                    break;
                case "left":
                    regionX = 0;
                    regionWidth += 6;
                    break;
            }
        }

        sprite.setRegion(regionX, regionY, regionWidth, regionHeight);
        sprite.setSize(regionWidth, regionHeight);
    }

    public void addWall(String wall){
        walls.add(wall);
    }
    public void setEmpty(){
        isEmpty = true;
    }
    public boolean isEmpty() {
        return isEmpty;
    }
    public Sprite getSprite() {
        return sprite;
    }
}
