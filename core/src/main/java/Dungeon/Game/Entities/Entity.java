package Dungeon.Game.Entities;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Entity {
    protected int frame; //current frame

    protected int animationSpeedCount; //counts when to switch the frame
    protected int animationSpeed; //in how many updates does frame change

    protected int frameCount; //frames in animation

    protected final EntityManager eM;

    protected Sprite sprite;

    protected int speed;
    protected int health;
    protected int damage;

    public Entity(){
        this.sprite = new Sprite();
        this.eM = EntityManager.getInstance();
    }

    public void update(){}

    protected void updateFrame(){
        animationSpeedCount++;
        if(animationSpeedCount >= animationSpeed){
            animationSpeedCount = 0;
            frame++;
            if(frame >= frameCount){
                frame = 0;
            }
        }
    }

    public Sprite getSprite() {
        return sprite;
    }
}
