package Dungeon.Game.Entities;

import Dungeon.Game.Settings;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.util.HashMap;

public class Player extends Entity {

    public Player() {
        super();

        health = 167;
        int scale = 5;

        loadAnimations();
        type = "player";
        changeAnimation("idle");

        sprite.setSize(10*scale,11*scale);
        sprite.setPosition((float) Settings.width /2, (float) Settings.height /2);
    }

    @Override
    public void update() {
        if(health <= 0){
            System.out.println("Player is dead");
            Gdx.app.exit();
        }

        updateFrame();
        move();
    }

    private void move(){
        boolean pressed = false;

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            //rotation = "up";
            pressed = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            //rotation = "down";
            pressed = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            //rotation = "left";
            pressed = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
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
