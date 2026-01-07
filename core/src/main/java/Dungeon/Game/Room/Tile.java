package Dungeon.Game.Room;

import Dungeon.Game.Data;
import Dungeon.Game.Settings;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

public class Tile {
    private final Data data;
    private final Sprite sprite;
    private TileType type;
    private final ArrayList<String> walls;
    private final int scale;

    public Tile(float x, float y){
        this.sprite = new Sprite();
        this.walls = new ArrayList<>();
        this.data = Data.getInstance();
        this.type = TileType.EMPTY;
        this.scale = Settings.roomScale;

        sprite.setPosition(x, y);
        sprite.setSize(32 * scale, 32 * scale);
    }

    public void resize(){
        int regionX = 6;
        int regionY = 31;
        int regionWidth = 32;
        int regionHeight = 32;

        for(String wall : walls){
            switch (wall){
                case "up":
                    regionHeight += 31;
                    regionY = 0;
                    break;
                case "down":
                    regionHeight += 6;
                    sprite.setY(sprite.getY() - 6 * scale);
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
    public void setType(TileType type){
        this.type = type;
        sprite.setTexture(data.get(type.toString().toLowerCase()));
    }
    public TileType getType() {
        return type;
    }
    public Sprite getSprite() {
        return sprite;
    }
}
