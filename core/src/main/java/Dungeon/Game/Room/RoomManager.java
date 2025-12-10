package Dungeon.Game.Room;

import Dungeon.Game.Data;
import Dungeon.Game.Entities.Egg;
import Dungeon.Game.Entities.EntityManager;
import Dungeon.Game.Settings;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RoomManager {
    private static RoomManager instance;
    public static RoomManager getInstance() {
        if (instance == null) instance = new RoomManager();
        return instance;
    }

    private final EntityManager eM;
    private final RoomGenerator rG;

    double direction = 0;

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
    private Image eggImage;
    private void loadImages() {

        ImageIcon icon = new ImageIcon("assets/sprites/shooting/eggg.png");
        eggImage = icon.getImage();
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
    ArrayList<Egg> eggs = new ArrayList<>();
    public void checkMove(){
        Sprite player = eM.getPlayer().getSprite();
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            direction = -Math.PI/2;
            if(checkBounds(player.getX(), player.getY() + Settings.speed) && checkBounds(player.getX() + player.getWidth(), player.getY() + Settings.speed)){
                currentRoom.setYOffset(currentRoom.getYOffset() + Settings.speed);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            direction = Math.PI/2;
            if(checkBounds(player.getX(), player.getY() - Settings.speed) && checkBounds(player.getX() + player.getWidth(), player.getY() - Settings.speed)){
                currentRoom.setYOffset(currentRoom.getYOffset() - Settings.speed);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            direction = Math.PI;
            if(checkBounds(player.getX() - Settings.speed, player.getY())){
                currentRoom.setXOffset(currentRoom.getXOffset() - Settings.speed);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            direction = 0;
            if(checkBounds(player.getX() + player.getWidth() + Settings.speed, player.getY())){
                currentRoom.setXOffset(currentRoom.getXOffset() + Settings.speed);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            double speed = 5;
            double dx = Math.cos(direction) * speed;
            double dy = Math.sin(direction) * speed;
            Egg egg = new Egg(player.getX(),player.getY(),5,6,dx, dy,3.0);
            while (!checkBounds(egg.x, egg.y)){
                egg.x += dx;
                egg.y += dy;
                egg.rotation += egg.rotationSpeed;




            }
            eggs.removeAll(eggs);
        }

    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
}
