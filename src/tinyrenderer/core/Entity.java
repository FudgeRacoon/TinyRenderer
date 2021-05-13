package tinyrenderer.core;

import java.util.ArrayList;

import tinyrenderer.geometry.Mesh;
import tinyrenderer.math.Vector3;
import tinyrenderer.math.Color;

public class Entity extends Mesh
{
    public String name;

    public Vector3 position;
    public Vector3 rotation;
    public Vector3 scale;

    public Color color;

    private boolean isActive;

    public Entity(String name)
    {
        super();

        this.name = name;

        this.position = new Vector3();
        this.rotation = new Vector3();
        this.scale = new Vector3(1.0f, 1.0f, 1.0f);

        this.color = new Color();

        this.isActive = true;
    }
    public Entity(String name, ArrayList<Vector3> vertices, ArrayList<Integer> indices)
    {
        super(vertices, indices);

        this.name = name;

        this.position = new Vector3();
        this.rotation = new Vector3();
        this.scale = new Vector3(1.0f, 1.0f, 1.0f);

        this.color = new Color();

        this.isActive = true;
    }

    public void Update()
    {
        super.UpdateMesh(this.position, this.rotation, this.scale);
    }
}
