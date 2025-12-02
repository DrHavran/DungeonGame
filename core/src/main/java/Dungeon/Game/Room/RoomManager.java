package Dungeon.Game.Room;


import Dungeon.Game.Settings;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class RoomManager {

    private final ArrayList<Tile> tiles;
    private final Texture texture;

    private int[][] room = {
        {1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1},
        {1, 1, 0, 1, 1},
        {1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1}
    };

    public RoomManager(Texture texture) {
        this.tiles = new ArrayList<>();
        this.texture = texture;
        convert();
        group();
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
                tile.resize();
                tiles.add(tile);
            }
        }
    }

    private void group(){
        int lastX = Settings.offSet;
        int lastY = Settings.height - Settings.offSet;
        int col = 0;

        for(Tile tile : tiles){
            if(col != tile.getSprite().getY()){
                lastY -= 32;
                lastX = Settings.offSet;
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

    public ArrayList<Tile> getTiles() {
        return tiles;
    }
}
