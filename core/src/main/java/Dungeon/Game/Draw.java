package Dungeon.Game;

import Dungeon.Game.Entities.Entity;
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

        for(Entity entity : logic.getEntities()){ //draw everything
            draw(entity);
        }
    }

    private void draw(Entity entity) {
        Sprite sprite = entity.getSprite();

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
