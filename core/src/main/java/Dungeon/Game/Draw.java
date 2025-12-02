package Dungeon.Game;

import Dungeon.Game.Entities.Entity;
import Dungeon.Game.Room.Tile;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Draw {
    private final SpriteBatch batch; //draw stuff

    private final Logic logic;

    public Draw() {
        this.logic = new Logic();
        this.batch = new SpriteBatch();
    }

    public void update(){
        logic.update();

        for(Tile tile : logic.getTiles()){ //draw room
            if(!tile.isEmpty()){
                drawTile(tile);
            }
        }

        for(Entity entity : logic.getEntities()){ //draw everything
            draw(entity);
        }
    }

    private void draw(Entity entity) {
        Sprite sprite = entity.getSprite();
        Texture texture = logic.getTexture(entity.getAnimation() + entity.getRotation());
        int frameWidth = texture.getWidth() / entity.getFrameCount();

        sprite.setTexture(texture);
        sprite.setRegion(
            frameWidth * entity.getFrame(), 0,
            frameWidth, texture.getHeight()
        );

        sprite.draw(batch);
    }
    private void drawTile(Tile tile){
        Sprite sprite = tile.getSprite();
        sprite.draw(batch);
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
