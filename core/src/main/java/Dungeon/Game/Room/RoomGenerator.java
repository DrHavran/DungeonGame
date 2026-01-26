package Dungeon.Game.Room;

import java.util.ArrayList;
import java.util.Arrays;

public class RoomGenerator {

    public int[][] generateRoom(int x, int y, ArrayList<String> rooms) {

        int[][] room = new int[y][x];

        Arrays.stream(room).forEach(o -> Arrays.fill(o, 1));

        if(rooms.contains("up")){
            room[0][x/2] = 2;
        }
        if(rooms.contains("down")){
            room[y-1][x/2] = 2;
        }
        if(rooms.contains("left")){
            room[y/2][0] = 2;
        }
        if(rooms.contains("right")){
            room[y/2][x-1] = 2;
        }

        room[y/2][x/2] = 0;

        return room;
    }
}
