package Dungeon.Game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.util.HashMap;

public class Player extends Entity {

    public Player() {
        super();

        speed = 5;
        health = 100;

        loadAnimations();
        type = "player";
        changeAnimation("idle");

        int scale = 5;

        sprite.setSize(10*scale,11*scale);
        sprite.setPosition(100, 100);
    }

    @Override
    public void update() {
        if(health <= 0){
            Gdx.app.exit();
        }

        updateFrame();
        move();
    }

    private void move(){
        boolean pressed = false;

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            sprite.setY(sprite.getY() + speed);
            //rotation = "up";
            pressed = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            sprite.setY(sprite.getY() - speed);
            //rotation = "down";
            pressed = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            sprite.setX(sprite.getX() - speed);
            //rotation = "left";
            pressed = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            sprite.setX(sprite.getX() + speed);
            //rotation = "right";
            pressed = true;
        }

        if (!pressed) {
            changeAnimation("idle");
        } else{
            changeAnimation("walk");
        }
    }

    @Override
    protected void loadAnimations() {
        HashMap<String, Integer> idle = new HashMap<>();
        idle.put("frames", 2);
        idle.put("speed", 20);

        HashMap<String, Integer> walk = new HashMap<>();
        walk.put("frames", 2);
        walk.put("speed", 20);

        animations.put("walk", walk);
        animations.put("idle", idle);
    }

    public void damage(int hit) {
        health -= hit;
    }
}
