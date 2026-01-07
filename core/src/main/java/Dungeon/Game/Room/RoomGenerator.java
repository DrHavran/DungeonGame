package Dungeon.Game.Room;

public class RoomGenerator {

    private int[][] room;

    public RoomGenerator() {
        this.room = null;
    }

    public void generateRoom(int x, int y){

        room = new int[][]{
            {1, 1, 1, 1, 2, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 0, 0, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 0, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 0, 0, 1, 1, 1, 1, 1},
            {1, 1, 1, 0, 0, 1, 0, 0, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 0, 0, 1},
            {1, 1, 1, 1, 2, 1, 1, 1, 1, 1}
        };
    }

    public int[][] getRoom() {
        return room;
    }
}
