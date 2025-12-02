package Dungeon.Game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {
    private Draw draw;

    @Override
    public void create() {
        draw = new Draw();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.3f, 1f);
        draw.begin();
        draw.update();
        draw.end();
    }

    @Override
    public void dispose() {
        draw.dispose();
    }
}
