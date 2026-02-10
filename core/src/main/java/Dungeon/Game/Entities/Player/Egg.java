package Dungeon.Game.Entities.Player;

import Dungeon.Game.Entities.Entity;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Egg extends Entity {

    private final int[] vector = {0, 0};

    public Egg(int dmg) {
        super();

        this.damage = dmg;
        size = 1.5f;
        speed = 6;
        type = "egg";
        changeAnimation("shoot");

        switch (god.getPlayer().getRotation()){
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
        sprite.setOriginCenter();

        Sprite player = god.getPlayer().getSprite();
        sprite.setPosition(
            player.getX() + player.getWidth()/2 - this.sprite.getWidth()/2,
            player.getY() + player.getHeight()/2 - this.sprite.getHeight()/2);

        god.addEntity(this);
    }

    @Override
    public void update(){
        offset();
        sprite.setX(sprite.getX()  + vector[0]*speed);
        sprite.setY(sprite.getY()  + vector[1]*speed);
        sprite.rotate(5);

        if(!god.checkBounds(sprite.getX(), sprite.getY())){
            god.removeEntity(this);
        }

        for(Entity entity : god.getCurrentRoom().getEntities()){
            if(entity instanceof Egg){
                continue;
            }
            if(sprite.getBoundingRectangle().overlaps(entity.getSprite().getBoundingRectangle())){
                entity.damage(damage);
                god.removeEntity(this);
            }
        }
    }

    @Override
    protected void loadAnimations(){
    }
}

