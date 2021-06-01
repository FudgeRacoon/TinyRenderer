package tinyrenderer.core;

import java.util.TreeMap;
import java.util.Map.Entry;

public class EntityManager 
{
    private EntityManager() {}
    private static final EntityManager instance = new EntityManager();

    private TreeMap<String,Entity> entites = new TreeMap<String,Entity>();
    private static int entityCount = 0;
    private static final String DEFAULT_NAME = "GameObject";
    
    public static EntityManager GetInstance()
    {
        return instance;
    } 

    public Entity AddEntity()
    {
        String name = DEFAULT_NAME + String.valueOf(entityCount++);

        Entity entity = new Entity(name);
        this.entites.put(entity.GetName(), entity);
        return entity;
    }   
    public void RemoveEntity(String name)
    {
        this.entites.remove(name);
    }
    public Entity GetEntity(String name)
    {
        return this.entites.get(name);
    }

    public void UpdateEntities()
    {
        for(Entry<String,Entity> entry : this.entites.entrySet())
            entry.getValue().Update();
    }

    public void ReleaseEntities()
    {
        this.entites.clear();
    }
}
