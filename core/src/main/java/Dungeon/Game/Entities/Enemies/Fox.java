package Dungeon.Game.Entities.Enemies;

import Dungeon.Game.Entities.Entity;

import java.util.HashMap;

public class Fox extends Entity {

    public Fox() {
        super();

        health = 100;
        size = 5;
        speed = 3;
        detectRadius = 200;

        loadAnimations();
        int randType = (int)(Math.random() * 4 + 1);
        switch(randType){
            case 1:
                type = "foxWood";
                speed = 6;
                break;
            case 2:
                type = "foxGold";
                break;
            default:
                type = "fox";
                break;
        }
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
                changeAnimation("chomp");
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
        idle.put("speed", 15);

        HashMap<String, Integer> walk = new HashMap<>();
        walk.put("frames", 4);
        walk.put("speed", 9);

        HashMap<String, Integer> jump = new HashMap<>();
        jump.put("frames", 4);
        jump.put("speed", 10);

        HashMap<String, Integer> chomp = new HashMap<>();
        chomp.put("frames", 7);
        chomp.put("speed", 9);

        HashMap<String, Integer> dead = new HashMap<>();
        dead.put("frames", 4);
        dead.put("speed", 15);

        animations.put("walk", walk);
        animations.put("jump", jump);
        animations.put("chomp", chomp);
        animations.put("idle", idle);
        animations.put("dead", dead);
    }
}
