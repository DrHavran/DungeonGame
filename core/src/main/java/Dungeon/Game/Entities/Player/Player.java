package Dungeon.Game.Entities.Player;

import Dungeon.Game.Entities.Entity;
import Dungeon.Game.Settings;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.util.HashMap;

public class Player extends Entity {
    public Player() {
        super();

        health = 100;
        size = 3;

        loadAnimations();
        type = "player";
        changeAnimation("idle");

        sprite.setSize(32*size,29*size);
        sprite.setPosition((float) Settings.width /2, (float) Settings.height /2);
    }

    @Override
    public void update() {
        if(health <= 0){
            Gdx.app.exit();
        }

        updateFrame();
        move();
        checkShoot();
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

    private void checkShoot(){
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            new Egg();
        }
    }

    @Override
    protected void loadAnimations() {
        HashMap<String, Integer> idle = new HashMap<>();
        idle.put("frames", 2);
        idle.put("speed", 10);

        HashMap<String, Integer> walk = new HashMap<>();
        walk.put("frames", 6);
        walk.put("speed", 10);

        animations.put("walk", walk);
        animations.put("idle", idle);
    }

    public void damage(int hit) {
        health -= hit;
    }
}
