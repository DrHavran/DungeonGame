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
    protected int detectRadius;
    protected boolean isAttacking;

    public Entity(){
        this.sprite = new Sprite();
        this.animations = new HashMap<>();
        this.eM = EntityManager.getInstance();
        this.iM = InputManager.getInstance();
        this.rM = RoomManager.getInstance();

        this.speed = 0;
        this.frame = 0;
        this.frameCount = 1;
        this.isAttacking = false;
        this.animationRotation = "right";
        this.rotation = "right";
    }

    public abstract void update();
    protected abstract void loadAnimations();

    protected void changeAnimation(String string) {
        animation = type + "_" + string;
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
            if(Settings.noBounds || rM.checkBounds(player.getX(), player.getY() + Settings.speed) && rM.checkBounds(player.getX() + player.getWidth(), player.getY() + Settings.speed)){
                sprite.setY(sprite.getY() - Settings.speed);
            }
        }
        if (iM.isS() && !iM.isW()) {
            if(Settings.noBounds || rM.checkBounds(player.getX(), player.getY() - Settings.speed) && rM.checkBounds(player.getX() + player.getWidth(), player.getY() - Settings.speed)){
                sprite.setY(sprite.getY() + Settings.speed);
            }
        }
        if (iM.isA() && !iM.isD()) {
            if(Settings.noBounds || rM.checkBounds(player.getX() - Settings.speed, player.getY())){
                sprite.setX(sprite.getX() + Settings.speed);
            }
        }
        if (iM.isD() && !iM.isA()) {
            if(Settings.noBounds || rM.checkBounds(player.getX() + player.getWidth() + Settings.speed, player.getY())){
                sprite.setX(sprite.getX() - Settings.speed);
            }
        }
    }

    protected boolean checkRange(){
        return eM.getPlayer().getSprite().getX() < sprite.getX() + detectRadius + sprite.getWidth()/2 &&
            eM.getPlayer().getSprite().getX() + eM.getPlayer().getSprite().getWidth() > sprite.getX() - detectRadius + sprite.getWidth()/2 &&
            eM.getPlayer().getSprite().getY() < sprite.getY() + detectRadius + sprite.getHeight()/2 &&
            eM.getPlayer().getSprite().getY() + eM.getPlayer().getSprite().getHeight() > sprite.getY() - detectRadius + sprite.getHeight()/2;
    }

    protected boolean touchingPlayer(){
        return eM.getPlayer().getSprite().getBoundingRectangle().overlaps(sprite.getBoundingRectangle());
    }

    protected void moveToPlayer(){
        double x = (eM.getPlayer().getSprite().getX() + eM.getPlayer().getSprite().getWidth()/2) - (sprite.getX() + sprite.getWidth()/2);
        double y = (eM.getPlayer().getSprite().getY() + eM.getPlayer().getSprite().getHeight()/2) - (sprite.getY()  + sprite.getHeight()/2);
        double length = Math.sqrt(x * x + y * y);

        float normalizedX = (float) (x / length * speed);
        float normalizedY = (float) (y / length * speed);

        System.out.println(normalizedX + " " + normalizedY);

        if(normalizedX > 0){
            if(rM.checkBounds(sprite.getX() + sprite.getWidth() + normalizedX, sprite.getY())){
                sprite.setX(sprite.getX() + normalizedX);
            }
        }else{
            if(rM.checkBounds(sprite.getX() + normalizedX, sprite.getY())){
                sprite.setX(sprite.getX() + normalizedX);
            }
        }

        if(normalizedY < 0){
            if(rM.checkBounds(sprite.getX(), sprite.getY() + sprite.getHeight() + normalizedY)){
                sprite.setY(sprite.getY() + normalizedY);
            }
        }else{
            if(rM.checkBounds(sprite.getX(), sprite.getY() + normalizedY)){
                sprite.setY(sprite.getY() + normalizedY);
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
    public int getDetectRadius() {
        return detectRadius;
    }
    public int getFrame() {
        return frame;
    }
    public int getFrameCount() {
        return frameCount;
    }
}
