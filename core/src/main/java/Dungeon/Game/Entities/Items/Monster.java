package Dungeon.Game.Entities.Items;

import Dungeon.Game.Entities.Entity;
import Dungeon.Game.Settings;

import java.util.HashMap;

public class Monster extends Entity {

    public Monster(float x, float y) {
        super();

        size = 1.5f;
        sprite.setPosition(x, y);

        int randomType = (int)(Math.random() * 4) + 1;

        switch (randomType) {
            case 1:
                type = "monsterGreen";
                break;
            case 2:
                type = "monsterNormal";
                break;
            case 3:
                type = "monsterRed";
                break;
            case 4:
                type = "monsterWhite";
                break;
            case 5:
                type = "monsterYellow";
                break;
        }
        changeAnimation("idle");

        sprite.setSize(32*size,32*size);
    }

    @Override
    public void update(){
        offset();
    }

    @Override
    protected void loadAnimations(){
        HashMap<String, Integer> idle = new HashMap<>();
        idle.put("frames", 1);
        idle.put("speed", 1);

        animations.put("idle", idle);
    }
}
