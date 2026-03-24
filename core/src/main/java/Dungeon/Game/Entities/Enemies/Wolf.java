package Dungeon.Game.Entities.Enemies;

import Dungeon.Game.Entities.Entity;

import java.util.HashMap;

public class Wolf extends Entity {

    public Wolf() {
        super();

        health = 100;
        size = 5;
        speed = 4;
        detectRadius = 200;

        loadAnimations();
        type = "wolf";
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
                changeAnimation("chomp2");
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
        idle.put("speed", 13);

        HashMap<String, Integer> walk = new HashMap<>();
        walk.put("frames", 6);
        walk.put("speed", 11);

        HashMap<String, Integer> jump = new HashMap<>();
        jump.put("frames", 5);
        jump.put("speed", 10);

        HashMap<String, Integer> chomp = new HashMap<>();
        chomp.put("frames", 6);
        chomp.put("speed", 9);

        HashMap<String, Integer> chomp2 = new HashMap<>();
        chomp2.put("frames", 6);
        chomp2.put("speed", 9);

        HashMap<String, Integer> taunt = new HashMap<>();
        taunt.put("frames", 10);
        taunt.put("speed", 8);

        HashMap<String, Integer> dead = new HashMap<>();
        dead.put("frames", 5);
        dead.put("speed", 14);

        animations.put("walk", walk);
        animations.put("jump", jump);
        animations.put("chomp", chomp);
        animations.put("chomp2", chomp2);
        animations.put("taunt", taunt);
        animations.put("idle", idle);
        animations.put("dead", dead);
    }
}
