package Dungeon.Game.Entities.Player;

import Dungeon.Game.Entities.Entity;
import Dungeon.Game.Settings;
import com.badlogic.gdx.Gdx;

import java.util.HashMap;

public class Player extends Entity {

    private final int shootDelay;
    private int shootCount;

    public Player() {
        super();

        shootDelay = 30;
        shootCount = 0;
        health = 100;
        size = 3;

        loadAnimations();
        type = "player";
        changeAnimation("idle");

        sprite.setSize(23*size,27*size);
        sprite.setPosition((float) Settings.width/2, (float) Settings.height/2);
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

        if (iM.isW() && !iM.isS()) {
            rotation = "up";
            pressed = true;
        }
        if (iM.isS() && !iM.isW()) {
            rotation = "down";
            pressed = true;
        }
        if (iM.isA() && !iM.isD()) {
            animationRotation = "left";
            rotation = "left";
            pressed = true;
        }
        if (iM.isD() && !iM.isA()) {
            animationRotation = "right";
            rotation = "right";
            pressed = true;
        }

        if (!pressed) {
            changeAnimation("idle");
        } else{
            changeAnimation("walk");
        }
    }

    private void checkShoot(){
        if(shootCount > 0){
            shootCount--;
        }
        if (iM.isSPACE() && shootCount == 0) {
            new Egg();
            shootCount = shootDelay;
        }
    }

    @Override
    protected void loadAnimations() {
        HashMap<String, Integer> idle = new HashMap<>();
        idle.put("frames", 2);
        idle.put("speed", 15);

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
