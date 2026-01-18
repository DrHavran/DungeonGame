package Dungeon.Game;

import Dungeon.Game.Entities.Entity;
import Dungeon.Game.Room.Room;
import Dungeon.Game.Room.Tile;
import Dungeon.Game.Room.TileType;
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

        drawRoom(logic.getCurrentRoom());

        if(Settings.showBounds){
            for(Rectangle rect : logic.getRectangles()){ //draw bounds of the room
                drawBound(rect);
            }
        }

        for(Entity entity : logic.getEntities()){ //draw entities
            if(Settings.showRadius){
                drawRadius(entity);
            }
            draw(entity);
        }

        draw(logic.getPlayer());
    }

    private void drawRadius(Entity entity){
        batch.end();
        sR.begin(ShapeRenderer.ShapeType.Filled);
        sR.setColor(Color.RED);
        sR.rect(entity.getSprite().getX() - entity.getDetectRadius() + entity.getSprite().getWidth()/2, entity.getSprite().getY() - entity.getDetectRadius() + entity.getSprite().getHeight()/2, entity.getDetectRadius()*2, entity.getDetectRadius()*2);
        sR.end();
        batch.begin();
    }

    private void drawBound(Rectangle rect){
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

    private void drawRoom(Room room){
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
