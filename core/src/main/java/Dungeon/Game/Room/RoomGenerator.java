package Dungeon.Game.Room;

import java.util.ArrayList;

public class RoomGenerator {

    public int[][] generateRoom(int x, int y, ArrayList<String> rooms) {

        int[][] room = new int[][]{
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };

        if(rooms.contains("up")){
            room[0][4] = 2;
        }
        if(rooms.contains("down")){
            room[8][4] = 2;
        }
        if(rooms.contains("left")){
            room[4][0] = 2;
        }
        if(rooms.contains("right")){
            room[4][8] = 2;
        }



        return room;
    }
}
