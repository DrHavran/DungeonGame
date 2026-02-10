package Dungeon.Game;

import Dungeon.Game.Entities.Enemies.Fox;
import Dungeon.Game.Entities.Player.Player;
import Dungeon.Game.Room.Room;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class Logic {

    private final GodManager god;
    private final InputManager iM;
    private final Data data;

    public Logic() {
        this.god = GodManager.getInstance();
        this.data = Data.getInstance();
        this.iM = InputManager.getInstance();

        god.newPlayer();
        god.addEntity(new Fox());
    }

    public void update(){
        iM.update();
        god.update();
        god.checkMove();
    }

    public int[][] getMinimap(){
        return god.getMinimap();
    }
    public int getHP(){
        return god.getPlayer().getHP();
    };
    public int getMaxHP(){
        return god.getPlayer().getMaxHP();
    }
    public int[] getCords(){
        return god.getCords();
    }
    public float getXOffset(){ return god.getXOffset(); }
    public float getYOffset(){ return god.getYOffset(); }
    public Room getCurrentRoom() {
        return god.getCurrentRoom();
    }
    public Texture getTexture(String name){return data.get(name);}
    public Player getPlayer(){
        return god.getPlayer();
    }
    public ArrayList<Rectangle> getRectangles(){
        return god.getCurrentRoom().getBounds();
    }
}
