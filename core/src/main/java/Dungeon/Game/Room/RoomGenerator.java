package Dungeon.Game.Room;

import java.util.HashSet;
import java.util.Random;

public class RoomGenerator {

    private int[][] room;

    public RoomGenerator() {
        this.room = null;

    }

    public void generateRoom(int x, int y){
        room = new int[y][x];
        Random rand = new Random();
        HashSet<int[]> visited = new HashSet<>();

        int[] start = {rand.nextInt(x), y};
        int[] end = {rand.nextInt(x), 0};
        
        int[] current = new int[2];

        while(visited.contains(end)){
            loadAround();

        }

        for(int row = 0; row < room.length; row++){
            for(int col = 0; col < room[row].length; col++){
                room[row][col] = (int) (Math.random() * 2);
            }
        }
    }

    public int[][] getRoom() {
        return room;
    }
}
