package Dungeon.Game.Entities;

import Dungeon.Game.GodManager;
import Dungeon.Game.InputManager;
import Dungeon.Game.Settings;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.HashMap;

public abstract class Entity {
    protected int frame; //current frame
    protected int frameCount; //frames in animation

    protected int animationSpeedCount; //counts when to switch the frame
    protected int animationSpeed; //in how many updates does frame change

    protected final InputManager iM;
    protected final GodManager god;
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
        this.iM = InputManager.getInstance();
        this.god = GodManager.getInstance();

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
        Sprite player = god.getPlayer().getSprite();

        if (iM.isW() && !iM.isS()) {
            if(Settings.noBounds || god.checkBounds(player.getX(), player.getY() + Settings.speed) && god.checkBounds(player.getX() + player.getWidth(), player.getY() + Settings.speed)){
                sprite.setY(sprite.getY() - Settings.speed);
            }
        }
        if (iM.isS() && !iM.isW()) {
            if(Settings.noBounds || god.checkBounds(player.getX(), player.getY() - Settings.speed) && god.checkBounds(player.getX() + player.getWidth(), player.getY() - Settings.speed)){
                sprite.setY(sprite.getY() + Settings.speed);
            }
        }
        if (iM.isA() && !iM.isD()) {
            if(Settings.noBounds || god.checkBounds(player.getX() - Settings.speed, player.getY())){
                sprite.setX(sprite.getX() + Settings.speed);
            }
        }
        if (iM.isD() && !iM.isA()) {
            if(Settings.noBounds || god.checkBounds(player.getX() + player.getWidth() + Settings.speed, player.getY())){
                sprite.setX(sprite.getX() - Settings.speed);
            }
        }
    }

    protected boolean checkRange(){
        return god.getPlayer().getSprite().getX() < sprite.getX() + detectRadius + sprite.getWidth()/2 &&
            god.getPlayer().getSprite().getX() + god.getPlayer().getSprite().getWidth() > sprite.getX() - detectRadius + sprite.getWidth()/2 &&
            god.getPlayer().getSprite().getY() < sprite.getY() + detectRadius + sprite.getHeight()/2 &&
            god.getPlayer().getSprite().getY() + god.getPlayer().getSprite().getHeight() > sprite.getY() - detectRadius + sprite.getHeight()/2;
    }

    protected boolean touchingPlayer(){
        return god.getPlayer().getSprite().getBoundingRectangle().overlaps(sprite.getBoundingRectangle());
    }

    protected void moveToPlayer(){
        double x = (god.getPlayer().getSprite().getX() + god.getPlayer().getSprite().getWidth()/2) - (sprite.getX() + sprite.getWidth()/2);
        double y = (god.getPlayer().getSprite().getY() + god.getPlayer().getSprite().getHeight()/2) - (sprite.getY()  + sprite.getHeight()/2);
        double length = Math.sqrt(x * x + y * y);

        float normalizedX = (float) (x / length * speed);
        float normalizedY = (float) (y / length * speed);

        if(normalizedX > 0){
            if(
                god.checkBounds(sprite.getX() + sprite.getWidth() + normalizedX, sprite.getY()) && god.checkBounds(sprite.getX() + sprite.getWidth() + normalizedX, sprite.getY() + sprite.getHeight())){
                sprite.setX(sprite.getX() + normalizedX);
            }
        }else{
            if(god.checkBounds(sprite.getX() + normalizedX, sprite.getY()) && god.checkBounds(sprite.getX() + normalizedX, sprite.getY() + sprite.getHeight())){
                sprite.setX(sprite.getX() + normalizedX);
            }
        }

        if(normalizedY > 0){
            if(god.checkBounds(sprite.getX(), sprite.getY() + sprite.getHeight() + normalizedY) && god.checkBounds(sprite.getX() + sprite.getWidth(), sprite.getY() + sprite.getHeight() + normalizedY)){
                sprite.setY(sprite.getY() + normalizedY);
            }
        }else{
            if(god.checkBounds(sprite.getX(), sprite.getY() + normalizedY) && god.checkBounds(sprite.getX() + sprite.getWidth(), sprite.getY() + normalizedY) ){
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
    public void damage(int damage){
        health = health - damage;
        if(health <= 0){
            god.removeEntity(this);
        }
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
