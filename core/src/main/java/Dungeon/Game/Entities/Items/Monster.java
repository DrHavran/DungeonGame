package Dungeon.Game.Entities.Items;

import Dungeon.Game.Entities.Entity;

public class Monster extends Entity {

    public Monster() {
        super();

        size = 1.5f;
        type = "monster";

        int randomType = (int)(Math.random() * 5) + 1;

        switch (randomType) {
            case 1:
                changeAnimation("green");
                break;
            case 2:
                changeAnimation("normal");
                break;
            case 3:
                changeAnimation("red");
                break;
            case 4:
                changeAnimation("white");
                break;
            case 5:
                changeAnimation("yellow");
                break;
        }
    }

    @Override
    public void update(){
        offset();
    }

    @Override
    protected void loadAnimations(){
    }
}
