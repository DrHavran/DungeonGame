package Dungeon.Game.Room;

import Dungeon.Game.Entities.EntityManager;
import Dungeon.Game.Settings;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class RoomManager {

    private final EntityManager eM;
    private final RoomGenerator rG;

    private final ArrayList<Room> rooms;
    private Room currentRoom;

    public RoomManager() {
        this.rG = new RoomGenerator();
        this.eM = EntityManager.getInstance();
        this.rooms = new ArrayList<>();
    }

    public void generateRoom(int x, int y){
        Room room = new Room();

        rG.generateRoom(x, y);

        room.setRoom(rG.getRoom());
        room.edit();

        rooms.add(room);

        currentRoom = room; //placeholder
    }

    public boolean checkBounds(float x, float y){
        for(Rectangle rectangle : currentRoom.getBounds()){

            Rectangle rect = new Rectangle(rectangle);
            rect.x -= currentRoom.getXOffset();
            rect.y -= currentRoom.getYOffset();
            if(rect.contains(x, y)){
                return true;
            }
        }
        return false;
    }

    public void checkMove(){
        Sprite player = eM.getPlayer().getSprite();

        // --- ADDED: direction calculation + normalization ---
        float dx = 0;
        float dy = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.W)) dy += 1;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) dy -= 1;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) dx -= 1;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) dx += 1;

        float length = (float)Math.sqrt(dx * dx + dy * dy);
        if (length != 0) {
            dx /= length;
            dy /= length;
        }
        // --- END OF ADDED SECTION ---

        float speed = Settings.speed;

        // Your original movement, only replaced +/- speed by dx/dy * speed

        // UP
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            if(checkBounds(player.getX(), player.getY() + dy * speed) &&
                checkBounds(player.getX() + player.getWidth(), player.getY() + dy * speed)){
                currentRoom.setYOffset(currentRoom.getYOffset() + dy * speed);
            }
        }

        // DOWN
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            if(checkBounds(player.getX(), player.getY() + dy * speed) &&
                checkBounds(player.getX() + player.getWidth(), player.getY() + dy * speed)){
                currentRoom.setYOffset(currentRoom.getYOffset() + dy * speed);
            }
        };

        // LEFT
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            if(checkBounds(player.getX() + dx * speed, player.getY())){
                currentRoom.setXOffset(currentRoom.getXOffset() + dx * speed);
            }
        }

        // RIGHT
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            if(checkBounds(player.getX() + player.getWidth() + dx * speed, player.getY())){
                currentRoom.setXOffset(currentRoom.getXOffset() + dx * speed);
            }
        }
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
}
