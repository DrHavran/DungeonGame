package Dungeon.Game.Entities;

import Dungeon.Game.InputManager;
import Dungeon.Game.Room.RoomManager;
import Dungeon.Game.Settings;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.HashMap;

public abstract class Entity {
    protected int frame; //current frame
    protected int frameCount; //frames in animation

    protected int animationSpeedCount; //counts when to switch the frame
    protected int animationSpeed; //in how many updates does frame change

    protected final EntityManager eM;
    protected final InputManager iM;
    protected final RoomManager rM;
    protected HashMap<String, HashMap<String, Integer>> animations;

    protected Sprite sprite;
    protected String animation;
    protected String type;
    protected String rotation;
    protected String animationRotation;
    protected float size;

    protected int speed;
    protected int health;
    protected int damage;

    public Entity(){
        this.sprite = new Sprite();
        this.animations = new HashMap<>();
        this.eM = EntityManager.getInstance();
        this.iM = InputManager.getInstance();
        this.rM = RoomManager.getInstance();

        this.speed = 0;
        this.frame = 0;
        this.frameCount = 1;
        this.animationRotation = "right";
        this.rotation = "right";
    }

    public abstract void update();
    protected abstract void loadAnimations();

    protected void changeAnimation(String string) {
        animation = type + "_" + string + "_";
        //System.out.println("current animation " + animation);
        if(!animations.isEmpty()){
            frameCount = animations.get(string).get("frames");
            if(frameCount <= frame){
                frame = 0;
            }
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

    protected void offset(){
        Sprite player = eM.getPlayer().getSprite();

        if (iM.isW() && !iM.isS()) {
            if(rM.checkBounds(player.getX(), player.getY() + Settings.speed) && rM.checkBounds(player.getX() + player.getWidth(), player.getY() + Settings.speed)){
                sprite.setY(sprite.getY() - Settings.speed);
            }
        }
        if (iM.isS() && !iM.isW()) {
            if(rM.checkBounds(player.getX(), player.getY() - Settings.speed) && rM.checkBounds(player.getX() + player.getWidth(), player.getY() - Settings.speed)){
                sprite.setY(sprite.getY() + Settings.speed);
            }
        }
        if (iM.isA() && !iM.isD()) {
            if(rM.checkBounds(player.getX() - Settings.speed, player.getY())){
                sprite.setX(sprite.getX() + Settings.speed);
            }
        }
        if (iM.isD() && !iM.isA()) {
            if(rM.checkBounds(player.getX() + player.getWidth() + Settings.speed, player.getY())){
                sprite.setX(sprite.getX() - Settings.speed);
            }
        }
    }

    public Sprite getSprite() {
        return sprite;
    }
    public String getAnimationRotation() {
        return animationRotation;
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
