package Dungeon.Game.Entities.Enemies;

import Dungeon.Game.Entities.Entity;

import java.util.HashMap;

public class Fox extends Entity {

    public Fox() {
        super();

        health = 100;
        size = 5;
        speed = 1;
        detectRadius = 200;

        loadAnimations();
        type = "fox";
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

        animations.put("walk", walk);
        animations.put("jump", jump);
        animations.put("chomp", chomp);
        animations.put("idle", idle);
    }
}
