package Dungeon.Game.Room;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class RoomManager {

    private final Texture texture;
    private final RoomGenerator rG;

    private final ArrayList<Room> rooms;

    private Room currentRoom;

    public RoomManager(Texture texture) {
        this.rG = new RoomGenerator();
        this.rooms = new ArrayList<>();
        this.texture = texture;
    }

    public void generateRoom(int x, int y){
        Room room = new Room(texture);

        rG.generateRoom(x, y);

        room.setRoom(rG.getRoom());
        room.edit();

        rooms.add(room);

        currentRoom = room; //placeholder
    }

    public void checkMove(){
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            currentRoom.setYOffset(currentRoom.getYOffset() + 5);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            currentRoom.setYOffset(currentRoom.getYOffset() - 5);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            currentRoom.setXOffset(currentRoom.getXOffset() - 5);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            currentRoom.setXOffset(currentRoom.getXOffset() + 5);
        }
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
}
