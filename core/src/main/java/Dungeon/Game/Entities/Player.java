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
            pressed = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            sprite.setY(sprite.getY() - speed);
            pressed = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            pressed = true;
            sprite.setX(sprite.getX() - speed);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            pressed = true;
            sprite.setX(sprite.getX() + speed);
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
        idle.put("frames", 6);
        idle.put("speed", 8);

        HashMap<String, Integer> walk = new HashMap<>();
        walk.put("frames", 6);
        walk.put("speed", 8);

        animations.put("walk", walk);
        animations.put("idle", idle);
    }

    public void damage(int hit) {
        health -= hit;
    }
}
