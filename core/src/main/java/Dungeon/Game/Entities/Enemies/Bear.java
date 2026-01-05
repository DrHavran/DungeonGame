package Dungeon.Game.Entities.Enemies;

import Dungeon.Game.Entities.Entity;

import java.util.HashMap;

public class Bear extends Entity {

    public Bear() {
        super();

        health = 100;
        size = 6;
        detectRadius = 200;

        loadAnimations();
        type = "bear";
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
        idle.put("speed", 14);

        HashMap<String, Integer> walk = new HashMap<>();
        walk.put("frames", 6);
        walk.put("speed", 10);

        HashMap<String, Integer> jump = new HashMap<>();
        jump.put("frames", 4);
        jump.put("speed", 11);

        HashMap<String, Integer> chomp = new HashMap<>();
        chomp.put("frames", 6);
        chomp.put("speed", 9);

        HashMap<String, Integer> swipe = new HashMap<>();
        swipe.put("frames", 7);
        swipe.put("speed", 9);

        HashMap<String, Integer> taunt = new HashMap<>();
        taunt.put("frames", 11);
        taunt.put("speed", 9);

        animations.put("walk", walk);
        animations.put("jump", jump);
        animations.put("chomp", chomp);
        animations.put("swipe", swipe);
        animations.put("taunt", taunt);
        animations.put("idle", idle);
    }
}
