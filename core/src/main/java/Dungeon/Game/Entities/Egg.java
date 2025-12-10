package Dungeon.Game.Entities;

import Dungeon.Game.Room.Room;
import Dungeon.Game.Room.RoomManager;

public class Egg {
    private RoomManager rm = RoomManager.getInstance();
    public float x, y;              // pozice
    public int width, height;     // velikost
    public double dx, dy;         // rychlost po ose X a Y
    public double rotation;       // úhel rotace
    public double rotationSpeed;  // rychlost rotace



    public Egg(float x, float y, int width, int height, double dx, double dy, double rotationSpeed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.dx = dx;
        this.dy = dy;
        this.rotation = 0;
        this.rotationSpeed = rotationSpeed;

    }

    public void collision(Room room) {

    }



}

