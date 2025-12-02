package Dungeon.Game.Entities;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.HashMap;

public abstract class Entity {
    protected int frame; //current frame
    protected int frameCount; //frames in animation

    protected int animationSpeedCount; //counts when to switch the frame
    protected int animationSpeed; //in how many updates does frame change

    protected final EntityManager eM;
    protected HashMap<String, HashMap<String, Integer>> animations;

    protected Sprite sprite;
    protected String animation;
    protected String type;
    protected String rotation;
    protected int size;

    protected int speed;
    protected int health;
    protected int damage;

    public Entity(){
        this.sprite = new Sprite();
        this.animations = new HashMap<>();
        this.eM = EntityManager.getInstance();

        this.speed = 0;
        this.frame = 0;
        this.rotation = "down";
    }

    abstract void update();
    abstract void loadAnimations();

    protected void changeAnimation(String string) {
        animation = type + "_" + string + "_";
        //System.out.println("current animation " + animation);
        if(!animations.isEmpty()){
            frameCount = animations.get(string).get("frames");
            animationSpeed = animations.get(string).get("speed");
        }
    }

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
    public String getRotation() {
        return rotation;
    }
    public String getAnimation() {
        return animation;
    }
    public int getFrame() {
        return frame;
    }
    public int getFrameCount() {
        return frameCount;
    }
}
