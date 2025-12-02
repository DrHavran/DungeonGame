package Dungeon.Game.Room;

import Dungeon.Game.Settings;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

public class Tile {
    private final Sprite sprite;
    private final ArrayList<String> walls;
    private boolean isEmpty;
    private final int scale = Settings.roomScale;

    public Tile(float x, float y, Texture texture){
        this.sprite = new Sprite(texture);
        this.walls = new ArrayList<>();
        this.isEmpty = false;

        sprite.setPosition(x, y);
        sprite.setRegion(6, 30, 32, 32);
        sprite.setSize(32 * scale, 32 * scale);
    }

    public void resize(){
        int regionX = 6;
        int regionY = 30;
        int regionWidth = 32;
        int regionHeight = 32;

        for(String wall : walls){
            switch (wall){
                case "up":
                    regionHeight += 30;
                    regionY = 0;
                    break;
                case "down":
                    regionHeight += 7;
                    sprite.setY(sprite.getY() - 7 * scale);
                    break;
                case "right":
                    regionWidth += 6;
                    break;
                case "left":
                    regionWidth += 6;
                    regionX = 0;
                    sprite.setX(sprite.getX() - 6 * scale);
                    break;
            }
        }

        sprite.setRegion(regionX, regionY, regionWidth, regionHeight);
        sprite.setSize(regionWidth * scale, regionHeight * scale);
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
