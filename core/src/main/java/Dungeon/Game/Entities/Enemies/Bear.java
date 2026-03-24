package Dungeon.Game.Entities.Enemies;

import Dungeon.Game.Entities.Entity;

import java.util.HashMap;

public class Bear extends Entity {

    public Bear() {
        super();

        health = 100;
        size = 6;
        speed = 2;
        detectRadius = 200;

        loadAnimations();
        type = "bear";
        changeAnimation("idle");

        sprite.setSize(32*size,32*size);
    }

    @Override
    public void update() {
        updateFrame();
        offset();
        move();
    }

    private void move(){
        if(!dead){
            if(!touchingPlayer()){
                if(checkRange()){
                    isAttacking = true;
                    changeAnimation("walk");
                }

                if(isAttacking){
                    moveToPlayer();
                }
            }else{
                changeAnimation("swipe");
            }

            if(god.getPlayer().getSprite().getX() + god.getPlayer().getSprite().getWidth()/2 > sprite.getX() + sprite.getWidth()/2){
                animationRotation = "right";
            }else{
                animationRotation = "left";
            }
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

        HashMap<String, Integer> dead = new HashMap<>();
        dead.put("frames", 5);
        dead.put("speed", 15);

        animations.put("walk", walk);
        animations.put("jump", jump);
        animations.put("chomp", chomp);
        animations.put("swipe", swipe);
        animations.put("taunt", taunt);
        animations.put("idle", idle);
        animations.put("dead", dead);
    }
}
