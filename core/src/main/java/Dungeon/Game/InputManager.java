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

    private boolean UP;
    private boolean DOWN;
    private boolean LEFT;
    private boolean RIGHT;

    public void update() {
        W = Gdx.input.isKeyPressed(Input.Keys.W);
        S = Gdx.input.isKeyPressed(Input.Keys.S);
        A = Gdx.input.isKeyPressed(Input.Keys.A);
        D = Gdx.input.isKeyPressed(Input.Keys.D);
        SPACE = Gdx.input.isKeyPressed(Input.Keys.SPACE);

        UP = Gdx.input.isKeyPressed(Input.Keys.UP);
        DOWN = Gdx.input.isKeyPressed(Input.Keys.DOWN);
        LEFT = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        RIGHT = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
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

    public boolean isUP() {return UP;}
    public boolean isDOWN() {return DOWN;}
    public boolean isLEFT() {return LEFT;}
    public boolean isRIGHT() {return RIGHT;}
}
