package Dungeon.Game.Entities.Enemies;

import Dungeon.Game.Entities.Entity;

import java.util.HashMap;

public class Wolf extends Entity {

    public Wolf() {
        super();

        health = 100;
        size = 4;
        detectRadius = 200;

        loadAnimations();
        type = "wolf";
        changeAnimation("idle");

        sprite.setSize(32*size,32*size);
        sprite.setPosition(100, 100);
    }

    @Override
    public void update() {
        updateFrame();
        offset();
        move();
    }

    private void move(){
        if(checkRange()){
            changeAnimation("walk");
        }else {
            changeAnimation("idle");
        }

        if(eM.getPlayer().getSprite().getX() > sprite.getX()){
            animationRotation = "right";
        }else{
            animationRotation = "left";
        }
    }

    @Override
    protected void loadAnimations() {
        HashMap<String, Integer> idle = new HashMap<>();
        idle.put("frames", 4);
        idle.put("speed", 10);

        HashMap<String, Integer> walk = new HashMap<>();
        walk.put("frames", 6);
        walk.put("speed", 9);

        animations.put("walk", walk);
        animations.put("idle", idle);
    }
}
