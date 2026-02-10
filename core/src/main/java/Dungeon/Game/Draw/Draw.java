package Dungeon.Game.Draw;

import Dungeon.Game.Entities.Entity;
import Dungeon.Game.Logic;
import Dungeon.Game.Room.Room;
import Dungeon.Game.Room.Tile;
import Dungeon.Game.Room.TileType;
import Dungeon.Game.Settings;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Draw {
    private final SpriteBatch batch; //draw stuff
    private final ShapeRenderer sR;

    private final Logic logic;

    public Draw() {
        this.logic = new Logic();
        this.batch = new SpriteBatch();
        this.sR = new ShapeRenderer();
    }

    public void update(){
        logic.update();

        draw(logic.getCurrentRoom());

        if(Settings.showBounds){
            for(Rectangle rect : logic.getRectangles()){ //draw bounds of the room
                draw(rect);
            }
        }

        for(Entity entity : logic.getCurrentRoom().getEntities()){ //draw entities
            if(Settings.showRadius){
                drawRadius(entity);
            }
            draw(entity);
        }

        draw(logic.getPlayer());

        drawMinimap();
        drawHP();
    }

    private void drawHP(){
        int hp = logic.getHP();
        int maxHp = logic.getMaxHP();

        for(int i = 0; i < maxHp/2; i++){
            batch.draw(logic.getTexture("emptyHeart"), 10 + i*40, Settings.height - 50, 40, 40);
        }

        if(hp % 2 == 0){
            for(int i = 0; i < hp/2; i++){
                batch.draw(logic.getTexture("fullHeart"), 10 + i*40, Settings.height - 50, 40, 40);
            }
        }else{
            for(int i = 0; i < (hp-1)/2; i++){
                batch.draw(logic.getTexture("fullHeart"), 10 + i*40, Settings.height - 50, 40, 40);
                if(i + 1 == (hp-1)/2){
                    batch.draw(logic.getTexture("halfHeart"), 10 + (1+i)*40, Settings.height - 50, 40, 40);
                }
            }
        }
    }
    private void drawMinimap(){
        int[][] minimap = logic.getMinimap();

        batch.end();
        sR.begin(ShapeRenderer.ShapeType.Line);
        sR.setColor(Color.BLACK);
        sR.rect(Settings.width-165, Settings.height-165, 165, 165);
        sR.end();
        sR.begin(ShapeRenderer.ShapeType.Filled);
        for(int y = 0; y < minimap.length; y++){
            for(int x = 0; x < minimap[y].length; x++){
                if(minimap[y][x] == 0){
                    continue;
                }else if(logic.getMinimap()[y][x] == 1){
                    sR.setColor(Color.DARK_GRAY);
                }else if(logic.getMinimap()[y][x] == 2){
                    sR.setColor(Color.TAN);
                }
                sR.rect(Settings.width - 165 + x*15, Settings.height - 165 + (10 - y) * 15, 15, 15);
            }
        }
        sR.end();
        batch.begin();
        int[] cords = logic.getCords();
        batch.draw(logic.getTexture("Icon"), Settings.width - 164 + cords[0]*15, Settings.height - 164 + (10 - cords[1]) * 15, 13, 13);
    }

    private void drawRadius(Entity entity){
        batch.end();
        sR.begin(ShapeRenderer.ShapeType.Filled);
        sR.setColor(Color.RED);
        sR.rect(entity.getSprite().getX() - entity.getDetectRadius() + entity.getSprite().getWidth()/2, entity.getSprite().getY() - entity.getDetectRadius() + entity.getSprite().getHeight()/2, entity.getDetectRadius()*2, entity.getDetectRadius()*2);
        sR.end();
        batch.begin();
    }

    private void draw(Rectangle rect){
        batch.end();
        sR.begin(ShapeRenderer.ShapeType.Filled);
        sR.setColor(Color.RED);
        sR.rect(rect.x - logic.getXOffset(), rect.y - logic.getYOffset(), rect.width, rect.height);
        sR.end();
        batch.begin();
    }

    private void draw(Entity entity) {
        Sprite sprite = entity.getSprite();
        Texture texture = logic.getTexture(entity.getAnimation());
        int frameWidth = texture.getWidth() / entity.getFrameCount();

        sprite.setTexture(texture);
        switch (entity.getAnimationRotation()){
            case "right":
                sprite.setRegion(
                    frameWidth * entity.getFrame(), 0,
                    frameWidth, texture.getHeight()
                );
                break;

            case "left":
                sprite.setRegion(
                    frameWidth * entity.getFrame() + frameWidth, 0,
                    -frameWidth, texture.getHeight()
                );
        }

        sprite.draw(batch);
    }

    private void draw(Room room) {
        for(Tile tile : room.getTiles()){
            if(tile.getType() == TileType.EMPTY){
                continue;
            }

            Sprite sprite = tile.getSprite();

            float lastX = sprite.getX();
            float lastY = sprite.getY();

            sprite.setX(sprite.getX() - logic.getXOffset());
            sprite.setY(sprite.getY() - logic.getYOffset());

            sprite.draw(batch);

            sprite.setX(lastX);
            sprite.setY(lastY);
        }
    }

    public void begin() {
        batch.begin();
    }
    public void end() {
        batch.end();
    }
    public void dispose() {
        batch.dispose();
    }
}
