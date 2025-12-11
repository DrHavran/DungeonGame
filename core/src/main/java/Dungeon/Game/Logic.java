package Dungeon.Game;

import Dungeon.Game.Entities.Entity;
import Dungeon.Game.Entities.EntityManager;
import Dungeon.Game.Entities.Player.Player;
import Dungeon.Game.Room.Room;
import Dungeon.Game.Room.RoomManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class Logic {

    private final EntityManager eM;
    private final RoomManager rM;
    private final Data data;

    public Logic() {
        this.eM = EntityManager.getInstance();
        this.data = Data.getInstance();
        this.rM = RoomManager.getInstance();

        eM.newPlayer();
        rM.generateRoom(30, 15);
    }

    public void update(){
        eM.update();
        rM.checkMove();
    }

    public Room getCurrentRoom(){ return rM.getCurrentRoom(); }
    public Texture getTexture(String name){return data.get(name);}
    public ArrayList<Entity> getEntities() {
        return eM.getEntities();
    }
    public Player getPlayer(){
        return eM.getPlayer();
    }
    public ArrayList<Rectangle> getRectangles(){
        return rM.getCurrentRoom().getBounds();
    }
}
