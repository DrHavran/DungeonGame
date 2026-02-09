package Dungeon.Game;

import Dungeon.Game.Entities.Entity;
import Dungeon.Game.Entities.Player.Egg;
import Dungeon.Game.Entities.Player.Player;
import Dungeon.Game.Room.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class GodManager {
    private static GodManager instance;
    public static GodManager getInstance() {
        if (instance == null) instance = new GodManager();
        return instance;
    }

    private final InputManager iM;
    private final Minimap minimap;

    private final RoomGenerator rG; //room shit
    private final FloorGenerator fG; //room shit
    private float xOffset;
    private float yOffset;
    private Room[][] rooms;
    private int[] cords;
    private Room currentRoom;

    private final ArrayList<Entity> toAdd; //entity shit
    private final ArrayList<Entity> toRemove;

    private Player player;

    public GodManager() {
        this.rG = new RoomGenerator();
        this.fG = new FloorGenerator();
        this.iM = InputManager.getInstance();
        this.toAdd = new ArrayList<>();
        this.toRemove = new ArrayList<>();
        this.minimap = new Minimap();

        generateFloor();
    }

    public void update(){
        for(Entity entity : currentRoom.getEntities()){
            entity.update();
        }
        player.update();

        addEntities();
        removeEntities();
    }

    public void generateFloor(){
        rooms = new Room[11][11];
        cords = new int[]{5, 5};

        fG.generateFloor();
        int[][] floor = fG.getFloor();
        minimap.reset();
        minimap.setMap(floor);
        minimap.setStatus(5, 5, 2);

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
        currentRoom = rooms[cords[1]][cords[0]];
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
        Sprite playerSprite = player.getSprite();

        if (iM.isW() && !iM.isS()) {
            if(Settings.noBounds || checkBounds(playerSprite.getX(), playerSprite.getY() + Settings.speed) && checkBounds(playerSprite.getX() + playerSprite.getWidth(), playerSprite.getY() + Settings.speed)){
                yOffset = (yOffset + Settings.speed);
            }else{
                checkDoor();
            }
        }
        if (iM.isS() && !iM.isW()) {
            if(Settings.noBounds || checkBounds(playerSprite.getX(), playerSprite.getY() - Settings.speed) && checkBounds(playerSprite.getX() + playerSprite.getWidth(), playerSprite.getY() - Settings.speed)){
                yOffset = (yOffset - Settings.speed);
            }else{
                checkDoor();
            }
        }
        if (iM.isA() && !iM.isD()) {
            if(Settings.noBounds || checkBounds(playerSprite.getX() - Settings.speed, playerSprite.getY())){
                xOffset = (xOffset - Settings.speed);
            }else{
                checkDoor();
            }
        }
        if (iM.isD() && !iM.isA()) {
            if(Settings.noBounds || checkBounds(playerSprite.getX() + playerSprite.getWidth() + Settings.speed, playerSprite.getY())){
                xOffset = (xOffset + Settings.speed);
            }else{
                checkDoor();
            }
        }
    }

    private void checkDoor(){
        Sprite playerSprite = player.getSprite();

        for(Entity entity : currentRoom.getEntities()){
            if(!(entity instanceof Egg)){
                return;
            }
        }

        for(Tile door : currentRoom.getDoors() ){
            Sprite sprite = door.getSprite();
            float lastX = sprite.getX();
            float lastY = sprite.getY();

            sprite.setX(sprite.getX() - xOffset);
            sprite.setY(sprite.getY() - yOffset);

            if(playerSprite.getBoundingRectangle().overlaps(door.getSprite().getBoundingRectangle())){
                if (door.checkWall("left")) {
                    cords[0]--;
                    currentRoom = rooms[cords[1]][cords[0]];
                    xOffset += currentRoom.getWidth() * Settings.roomScale * 32 - playerSprite.getWidth();

                    minimap.setStatus(cords[1], cords[0], 2);
                    checkDoors();
                }
                if (door.checkWall("right")) {
                    cords[0]++;
                    currentRoom = rooms[cords[1]][cords[0]];
                    xOffset -= currentRoom.getWidth() * Settings.roomScale * 32 - playerSprite.getWidth();
                    minimap.setStatus(cords[1], cords[0], 2);
                    checkDoors();
                }
                if (door.checkWall("up")) {
                    cords[1]--;
                    currentRoom = rooms[cords[1]][cords[0]];
                    yOffset -= currentRoom.getHeight() * Settings.roomScale * 32;
                    minimap.setStatus(cords[1], cords[0], 2);
                    checkDoors();
                }
                if (door.checkWall("down")) {
                    cords[1]++;
                    currentRoom = rooms[cords[1]][cords[0]];
                    yOffset += currentRoom.getHeight() * Settings.roomScale * 32;
                    minimap.setStatus(cords[1], cords[0], 2);
                    checkDoors();
                }

                sprite.setX(lastX);
                sprite.setY(lastY);
                break;
            }
            sprite.setX(lastX);
            sprite.setY(lastY);
        }
    }

    public void checkDoors(){
        for(Tile tile : currentRoom.getTiles()){
            if(tile.getType() == TileType.DOOR){
                for(Entity entity : currentRoom.getEntities()){
                    if(!(entity instanceof Egg)){
                        tile.setStatus("_closed");
                        return;
                    }
                }
                tile.setStatus("_open");
            }
        }
    }

    private void addEntities(){
        currentRoom.getEntities().addAll(toAdd);
        if(!toAdd.isEmpty()){
            checkDoors();
        }
        toAdd.clear();
    }
    private void removeEntities(){
        for(Entity entity : toRemove){
            currentRoom.getEntities().remove(entity);
        }
        if(!toRemove.isEmpty()){
            checkDoors();
        }
        toRemove.clear();
    }

    public float getXOffset() {
        return xOffset;
    }
    public float getYOffset() {
        return yOffset;
    }
    public int[] getCords(){
        return cords;
    }
    public int[][] getMinimap(){
        return minimap.getStatus();
    }
    public Room getCurrentRoom() {
        return currentRoom;
    }
    public void addEntity(Entity entity){
        toAdd.add(entity);
    }
    public void removeEntity(Entity entity){
        toRemove.add(entity);
    }
    public void newPlayer(){
        this.player = new Player();
    }
    public Player getPlayer(){
        return player;
    }
}
