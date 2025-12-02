package Dungeon.Game.Room;

public class RoomGenerator {

    private int[][] room;

    public RoomGenerator() {
        this.room = null;
    }

    public void generateRoom(int x, int y){
        room = new int[y][x];
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
