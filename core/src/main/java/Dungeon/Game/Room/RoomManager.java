package Dungeon.Game.Room;

import Dungeon.Game.Entities.EntityManager;
import Dungeon.Game.Settings;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class RoomManager {

    private final Texture texture;
    private final EntityManager eM;
    private final RoomGenerator rG;

    private final ArrayList<Room> rooms;
    private Room currentRoom;

    public RoomManager(Texture texture) {
        this.rG = new RoomGenerator();
        this.eM = EntityManager.getInstance();
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
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            if(checkBounds(player.getX(), player.getY() + Settings.speed)){
                currentRoom.setYOffset(currentRoom.getYOffset() + Settings.speed);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            if(checkBounds(player.getX(), player.getY() - Settings.speed)){
                currentRoom.setYOffset(currentRoom.getYOffset() - Settings.speed);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            if(checkBounds(player.getX() - Settings.speed, player.getY())){
                currentRoom.setXOffset(currentRoom.getXOffset() - Settings.speed);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            if(checkBounds(player.getX() + player.getWidth() + Settings.speed, player.getY())){
                currentRoom.setXOffset(currentRoom.getXOffset() + Settings.speed);
            }
        }
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
}
