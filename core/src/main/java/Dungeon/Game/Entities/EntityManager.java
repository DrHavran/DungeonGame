package Dungeon.Game.Entities;

import java.util.ArrayList;

public class EntityManager {
    private static EntityManager instance;
    public static EntityManager getInstance() {
        if (instance == null) instance = new EntityManager();
        return instance;
    }

    private final ArrayList<Entity> entities;
    private final ArrayList<Entity> toAdd;
    private final ArrayList<Entity> toRemove;

    private Player player;

    public EntityManager() {
        this.entities = new ArrayList<>();
        this.toAdd = new ArrayList<>();
        this.toRemove = new ArrayList<>();
    }

    public void addTest(){
        this.player = new Player();
    }

    public void update(){
        player.update();
        for(Entity entity : entities){
            entity.update();
        }

        addEntities();
        removeEntities();
    }

    private void addEntities(){
        entities.addAll(toAdd);
        toAdd.clear();
    }
    private void removeEntities(){
        for(Entity entity : toRemove){
            entities.remove(entity);
        }
        toRemove.clear();
    }

    public void addEntity(Entity entity){
        toAdd.add(entity);
    }
    public void removeEntity(Entity entity){
        toRemove.add(entity);
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }   //getters
    public Player getPlayer() {
        return player;
    }
}
