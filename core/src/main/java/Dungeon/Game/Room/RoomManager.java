package Dungeon.Game.Room;

import Dungeon.Game.Entities.EntityManager;
import Dungeon.Game.InputManager;
import Dungeon.Game.Settings;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class RoomManager {
    private static RoomManager instance;
    public static RoomManager getInstance() {
        if (instance == null) instance = new RoomManager();
        return instance;
    }

    private final EntityManager eM;
    private final InputManager iM;
    private final RoomGenerator rG;

    private final Room[][] rooms;
    private final int[] cords;
    private Room currentRoom;

    public RoomManager() {
        this.rG = new RoomGenerator();
        this.eM = EntityManager.getInstance();
        this.iM = InputManager.getInstance();

        cords = new int[]{2, 2};
        this.rooms = new Room[5][5];
        generateFloor();
    }

    public void generateFloor(){
        generateRoom(10, 10);
    }

    private void generateRoom(int x, int y){
        Room room = new Room();

        room.setRoom(rG.generateRoom(x, y));
        room.edit();

        rooms[cords[0]][cords[1]-1] = room;
        rooms[cords[0]][cords[1]] = room;
        rooms[cords[0]+1][cords[1]] = room;
        rooms[cords[0]-1][cords[1]] = room;

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
            }else{
                checkDoor();
            }
        }
        if (iM.isS() && !iM.isW()) {
            if(Settings.noBounds || checkBounds(player.getX(), player.getY() - Settings.speed) && checkBounds(player.getX() + player.getWidth(), player.getY() - Settings.speed)){
                currentRoom.setYOffset(currentRoom.getYOffset() - Settings.speed);
            }else{
                checkDoor();
            }
        }
        if (iM.isA() && !iM.isD()) {
            if(Settings.noBounds || checkBounds(player.getX() - Settings.speed, player.getY())){
                currentRoom.setXOffset(currentRoom.getXOffset() - Settings.speed);
            }else{
                checkDoor();
            }
        }
        if (iM.isD() && !iM.isA()) {
            if(Settings.noBounds || checkBounds(player.getX() + player.getWidth() + Settings.speed, player.getY())){
                currentRoom.setXOffset(currentRoom.getXOffset() + Settings.speed);
            }else{
                checkDoor();
            }
        }
    }

    private void checkDoor(){
        Sprite player = eM.getPlayer().getSprite();
        for(Tile door : currentRoom.getDoors() ){
            Sprite sprite = door.getSprite();
            float lastX = sprite.getX();
            float lastY = sprite.getY();

            sprite.setX(sprite.getX() - currentRoom.getXOffset());
            sprite.setY(sprite.getY() - currentRoom.getYOffset());

            if(player.getBoundingRectangle().overlaps(door.getSprite().getBoundingRectangle())){
               if(door.checkWall("up")){
                   currentRoom = rooms[cords[0]][cords[1]-1];
                   currentRoom.setYOffset(currentRoom.getYOffset() - (currentRoom.getHeight() * Settings.roomScale * 32));
                   cords[1] = cords[1] - 1;
               }else if(door.checkWall("down")){
                   currentRoom = rooms[cords[0]][cords[1]+1];
                   currentRoom.setYOffset(currentRoom.getYOffset() + (currentRoom.getHeight() * Settings.roomScale * 32));
                   cords[1] = cords[1] + 1;
               }else if(door.checkWall("left")){
                   currentRoom = rooms[cords[0]-1][cords[1]];
                   currentRoom.setXOffset(currentRoom.getXOffset() + (currentRoom.getWidth() * Settings.roomScale * 32) - player.getWidth());
                   cords[0] = cords[0] - 1;
               }else if(door.checkWall("right")){
                   currentRoom = rooms[cords[0]+1][cords[1]];
                   currentRoom.setXOffset(currentRoom.getXOffset() - (currentRoom.getWidth() * Settings.roomScale * 32) + player.getWidth());
                   cords[0] = cords[0] + 1;
               }
                System.out.println("Switched rooms");
                eM.getEntities().clear();
                sprite.setX(lastX);
                sprite.setY(lastY);
                break;
            }
            sprite.setX(lastX);
            sprite.setY(lastY);
        }
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
}
