package Dungeon.Game.Entities.Player;

import Dungeon.Game.Entities.Entity;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Egg extends Entity {

    private final int[] vector = {0, 0};

    public Egg() {
        super();

        size = 1.5f;
        speed = 3;
        type = "egg";
        changeAnimation("shoot");
        rotation = "null";

        switch (eM.getPlayer().getRotation()){
            case "right":
                vector[0] = 1;
                break;
            case "left":
                vector[0] = -1;
                break;
            case "up":
                vector[1] = 1;
                break;
            case "down":
                vector[1] = -1;
                break;
        }

        sprite.setSize(15*size,16*size);

        Sprite player = eM.getPlayer().getSprite();
        sprite.setPosition(
            player.getX() + player.getWidth()/2,
            player.getY() + player.getHeight()/2);

        eM.addEntity(this);
    }

    @Override
    public void update(){
        sprite.setX(sprite.getX()  + vector[0]*speed);
        sprite.setY(sprite.getY()  + vector[1]*speed);
    }

    @Override
    protected void loadAnimations(){

    }
}

