package Dungeon.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class InputManager {
    private static InputManager instance;
    public static InputManager getInstance() {
        if (instance == null) instance = new InputManager();
        return instance;
    }

    private boolean W;
    private boolean S;
    private boolean A;
    private boolean D;
    private boolean SPACE;

    public void update() {
        W = Gdx.input.isKeyPressed(Input.Keys.W);
        S = Gdx.input.isKeyPressed(Input.Keys.S);
        A = Gdx.input.isKeyPressed(Input.Keys.A);
        D = Gdx.input.isKeyPressed(Input.Keys.D);
        SPACE = Gdx.input.isKeyPressed(Input.Keys.SPACE);
    }

    public boolean isW() {
        return W;
    }
    public boolean isS() {
        return S;
    }
    public boolean isA() {
        return A;
    }
    public boolean isD() {
        return D;
    }
    public boolean isSPACE() {
        return SPACE;
    }
}
