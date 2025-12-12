package Dungeon.Game.Room;

import Dungeon.Game.Entities.EntityManager;
import Dungeon.Game.InputManager;
import Dungeon.Game.Settings;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class RoomManager {
    private static RoomManager instance;
    public static RoomManager getInstance() {
        if (instance == null) instance = new RoomManager();
        return instance;
    }

    private final EntityManager eM;
    private final InputManager iM;
    private final RoomGenerator rG;

    private final ArrayList<Room> rooms;
    private Room currentRoom;

    public RoomManager() {
        this.rG = new RoomGenerator();
        this.eM = EntityManager.getInstance();
        this.iM = InputManager.getInstance();
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

        if (iM.isW() && !iM.isS()) {
            if(Settings.noBounds || checkBounds(player.getX(), player.getY() + Settings.speed) && checkBounds(player.getX() + player.getWidth(), player.getY() + Settings.speed)){
                currentRoom.setYOffset(currentRoom.getYOffset() + Settings.speed);
            }
        }
        if (iM.isS() && !iM.isW()) {
            if(Settings.noBounds || checkBounds(player.getX(), player.getY() - Settings.speed) && checkBounds(player.getX() + player.getWidth(), player.getY() - Settings.speed)){
                currentRoom.setYOffset(currentRoom.getYOffset() - Settings.speed);
            }
        }
        if (iM.isA() && !iM.isD()) {
            if(Settings.noBounds || checkBounds(player.getX() - Settings.speed, player.getY())){
                currentRoom.setXOffset(currentRoom.getXOffset() - Settings.speed);
            }
        }
        if (iM.isD() && !iM.isA()) {
            if(Settings.noBounds || checkBounds(player.getX() + player.getWidth() + Settings.speed, player.getY())){
                currentRoom.setXOffset(currentRoom.getXOffset() + Settings.speed);
            }
        }
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
}
