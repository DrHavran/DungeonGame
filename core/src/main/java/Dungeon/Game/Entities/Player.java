package Dungeon.Game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Player extends Entity {

    public Player() {
        super();

        speed = 5;
        health = 100;

        sprite.setPosition(100, 100);
    }

    @Override
    public void update() {
        if(health <= 0){
            Gdx.app.exit();
        }

        updateFrame();
        move();
    }

    private void move(){
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            sprite.setY(sprite.getY() + speed);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            sprite.setY(sprite.getY() - speed);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            sprite.setX(sprite.getX() - speed);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            sprite.setX(sprite.getX() + speed);
        }
    }

    public void damage(int hit) {
        health -= hit;
    }
}
