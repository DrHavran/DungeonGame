package Dungeon.Game.Entities.Player;

import Dungeon.Game.Entities.Entity;
import Dungeon.Game.Settings;
import com.badlogic.gdx.Gdx;

import java.util.HashMap;

public class Player extends Entity {

    private final int shootDelay;
    private int shootCount;
    private int maxHealth;
    private int shootDirection = 2;

    public Player() {
        super();

        shootDelay = 30;
        shootCount = 0;
        maxHealth = 10;
        health = 10;
        size = 3;
        damage = 25;

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

        if (iM.isUP() && !iM.isDOWN() && shootCount == 0){
            shootDirection = 1;
            new Egg(damage, shootDirection);
            shootCount = shootDelay;
        }
        if (iM.isRIGHT() && !iM.isLEFT() && shootCount == 0){
            shootDirection = 2;
            new Egg(damage, shootDirection);
            shootCount = shootDelay;
        }
        if (iM.isDOWN() && !iM.isUP() && shootCount == 0){
            shootDirection = 3;
            new Egg(damage, shootDirection);
            shootCount = shootDelay;
        }
        if (iM.isLEFT() && !iM.isRIGHT() && shootCount == 0){
            shootDirection = 4;
            new Egg(damage, shootDirection);
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

    public int getHP(){
        return health;
    }
    public int getMaxHP(){
        return maxHealth;
    }
}
