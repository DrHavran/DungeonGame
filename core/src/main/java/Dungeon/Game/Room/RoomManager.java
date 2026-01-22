package Dungeon.Game.Room;

import Dungeon.Game.Entities.EntityManager;
import Dungeon.Game.InputManager;
import Dungeon.Game.Settings;
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

    private float xOffset;
    private float yOffset;

    private Room[][] rooms;
    private int[] cords;
    private Room currentRoom;

    public RoomManager() {
        this.rG = new RoomGenerator();
        this.eM = EntityManager.getInstance();
        this.iM = InputManager.getInstance();

        generateFloor();
    }

    public void generateFloor(){
        rooms = new Room[8][7];
        cords = new int[]{0, 3};
        currentRoom = rooms[3][0];

        int[][] floor = new int[][]{
            {0, 0, 0, 0, 1, 0, 0},
            {0, 0, 1, 1, 1, 0, 0},
            {0, 1, 0, 1, 1, 1, 1},
            {1, 1, 1, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 1},
            {0, 1, 0, 0, 1, 1, 0},
            {0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 1}
        };

        for (int y = 0; y < floor.length; y++) {
            for (int x = 0; x < floor[y].length; x++) {
                if (floor[y][x] == 1) {
                    Room room = new Room();

                    ArrayList<String> adjRooms = new ArrayList<>();

                    if (y > 0 && floor[y - 1][x] == 1) adjRooms.add("up");
                    if (y < floor.length - 1 && floor[y + 1][x] == 1) adjRooms.add("down");
                    if (x > 0 && floor[y][x - 1] == 1) adjRooms.add("left");
                    if (x < floor[y].length - 1 && floor[y][x + 1] == 1) adjRooms.add("right");

                    room.setRoom(rG.generateRoom(5, 5, adjRooms));

                    rooms[y][x] = room;
                }
            }
        }
    }

    public boolean checkBounds(float x, float y){
        for(Rectangle rectangle : currentRoom.getBounds()){
            Rectangle rect = new Rectangle(rectangle);
            rect.x -= xOffset;
            rect.y -= yOffset;
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
                yOffset = (yOffset + Settings.speed);
            }else{
                checkDoor();
            }
        }
        if (iM.isS() && !iM.isW()) {
            if(Settings.noBounds || checkBounds(player.getX(), player.getY() - Settings.speed) && checkBounds(player.getX() + player.getWidth(), player.getY() - Settings.speed)){
                yOffset = (yOffset - Settings.speed);
            }else{
                checkDoor();
            }
        }
        if (iM.isA() && !iM.isD()) {
            if(Settings.noBounds || checkBounds(player.getX() - Settings.speed, player.getY())){
                xOffset = (xOffset - Settings.speed);
            }else{
                checkDoor();
            }
        }
        if (iM.isD() && !iM.isA()) {
            if(Settings.noBounds || checkBounds(player.getX() + player.getWidth() + Settings.speed, player.getY())){
                xOffset = (xOffset + Settings.speed);
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

            sprite.setX(sprite.getX() - xOffset);
            sprite.setY(sprite.getY() - yOffset);

            if(player.getBoundingRectangle().overlaps(door.getSprite().getBoundingRectangle())){
                if (door.checkWall("left")) {
                    cords[0]--;
                    currentRoom = rooms[cords[1]][cords[0]];
                    xOffset += currentRoom.getWidth() * Settings.roomScale * 32 - player.getWidth();
                }
                if (door.checkWall("right")) {
                    cords[0]++;
                    currentRoom = rooms[cords[1]][cords[0]];
                    xOffset -= currentRoom.getWidth() * Settings.roomScale * 32 - player.getWidth();
                }
                if (door.checkWall("up")) {
                    cords[1]--;
                    currentRoom = rooms[cords[1]][cords[0]];
                    yOffset -= currentRoom.getHeight() * Settings.roomScale * 32;
                }
                if (door.checkWall("down")) {
                    cords[1]++;
                    currentRoom = rooms[cords[1]][cords[0]];
                    yOffset += currentRoom.getHeight() * Settings.roomScale * 32;
                }

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
    public float getXOffset() {
        return xOffset;
    }
    public float getYOffset() {
        return yOffset;
    }
}
