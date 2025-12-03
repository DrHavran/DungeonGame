package Dungeon.Game.Room;

import Dungeon.Game.Settings;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class Room {

    private int[][] room;
    private final ArrayList<Tile> tiles;
    private final Texture texture;

    private float xOffset;
    private float yOffset;

    public Room(Texture texture) {
        this.tiles = new ArrayList<>();
        this.texture = texture;

        yOffset = 100;
    }

    public void edit(){
        convert();
        group();
        for(Tile tile : tiles){ //adds walls
            tile.resize();
        }
    }

    private void convert(){
        for(int row = 0; row < room.length; row++){
            for(int col = 0; col < room[row].length; col++){
                Tile tile = new Tile(col, row, texture);
                if(room[row][col] == 0){
                    tile.setEmpty();
                }else{
                    checkWalls(tile, row, col);
                }
                tiles.add(tile);
            }
        }
    }

    private void group(){
        int lastX = 0;
        int lastY = Settings.height;
        int col = 0;

        for(Tile tile : tiles){
            if(col != tile.getSprite().getY()){
                lastY -= (int) tile.getSprite().getHeight();
                lastX = 0;
                col++;
            }
            tile.getSprite().setPosition(lastX, lastY);
            lastX += (int) tile.getSprite().getWidth();
        }
    }

    private void checkWalls(Tile tile, int row, int col){
        if (row == 0 || room[row - 1][col] == 0) {
            tile.addWall("up");
        }
        if (row == room.length - 1 || room[row + 1][col] == 0) {
            tile.addWall("down");
        }
        if (col == 0 || room[row][col - 1] == 0) {
            tile.addWall("left");
        }
        if (col == room[0].length - 1 || room[row][col + 1] == 0) {
            tile.addWall("right");
        }
    }

    public void setRoom(int[][] room) {
        this.room = room;
    }
    public ArrayList<Tile> getTiles() {
        return tiles;
    }
    public float getXOffset() {
        return xOffset;
    }
    public float getYOffset() {
        return yOffset;
    }
    public void setXOffset(float xOffset) {
        this.xOffset = xOffset;
    }
    public void setYOffset(float yOffset) {
        this.yOffset = yOffset;
    }
}
