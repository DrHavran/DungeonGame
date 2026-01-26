package Dungeon.Game.Room;

import Dungeon.Game.Entities.Enemies.Fox;
import Dungeon.Game.Entities.Entity;
import Dungeon.Game.Settings;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class Room {
    private final ArrayList<Entity> entities;
    private int[][] room;
    private final ArrayList<Tile> tiles;
    private final ArrayList<Tile> doors;
    private final ArrayList<Rectangle> bounds;

    public Room() {
        this.entities = new ArrayList<>();
        this.tiles = new ArrayList<>();
        this.bounds = new ArrayList<>();
        this.doors = new ArrayList<>();
    }

    private void edit(){
        convert();
        group();
        createArea();
        for(Tile tile : tiles){ //adds walls
            if(tile.getType() != TileType.EMPTY){
                tile.resize();
            }
        }
    }

    private void convert(){
        for(int row = 0; row < room.length; row++){
            for(int col = 0; col < room[row].length; col++){
                Tile tile = new Tile(col, row);
                tiles.add(tile);

                switch(room[row][col]){
                    case 0:
                        continue;
                    case 1:
                        tile.setType(TileType.NORMAL);
                        break;
                    case 2:
                        tile.setType(TileType.DOOR);
                        doors.add(tile);
                        break;
                }
                checkWalls(tile, row, col);
            }
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

    private void createArea(){
        for(Tile tile : tiles){
            if(tile.getType() != TileType.EMPTY){
                Sprite s = tile.getSprite();
                Rectangle rect = new Rectangle(
                    s.getX(),
                    s.getY(),
                    s.getWidth(),
                    s.getHeight()
                );
                bounds.add(rect);
            }
        }
    }

    public void setRoom(int[][] room) {
        this.room = room;
        edit();
    }
    public ArrayList<Tile> getTiles() {
        return tiles;
    }
    public ArrayList<Tile> getDoors() {
        return doors;
    }
    public ArrayList<Rectangle> getBounds(){
        return bounds;
    }
    public ArrayList<Entity> getEntities() {
        return entities;
    }
    public int getHeight(){
        return room.length;
    }
    public int getWidth(){
        return room[0].length;
    }
}
