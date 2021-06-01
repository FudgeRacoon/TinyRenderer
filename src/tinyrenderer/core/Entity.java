package tinyrenderer.core;

import java.util.ArrayList;
import java.util.Iterator;

import tinyrenderer.geometry.IRenderable;
import tinyrenderer.geometry.Mesh;
import tinyrenderer.geometry.Triangle;
import tinyrenderer.math.Vector3;
import tinyrenderer.math.Color;

public class Entity implements IRenderable
{
    public String name;

    public Vector3 position;
    public Vector3 rotation;
    public Vector3 scale;

    public Color color;

    private boolean isActive;

    private Mesh mesh;

    public Entity(String name)
    {
        this.name = name;

        this.position = new Vector3();
        this.rotation = new Vector3();
        this.scale = new Vector3(1.0f, 1.0f, 1.0f);

        this.color = new Color();

        this.isActive = true;

        this.mesh = null;
    }
    public Entity(String name, Mesh mesh)
    {
        this.name = name;

        this.position = new Vector3();
        this.rotation = new Vector3();
        this.scale = new Vector3(1.0f, 1.0f, 1.0f);

        this.color = new Color();

        this.isActive = true;

        this.mesh = mesh;
    }

    public void Update()
    {
        if(this.mesh != null)
            mesh.UpdateMesh(this.position, this.rotation, this.scale);
    }

    public String GetName()
    {
        return this.name;
    }
    public void SetName(String name)
    {
        this.name = name;
    }
    public Mesh GetMesh()
    {
        return this.mesh;
    }
    public void SetMesh(Mesh mesh)
    {
        this.mesh = mesh;
    }
    
    @Override
    public void RenderNoFill(Color color) 
    {
        if(this.isActive)
        {
            ArrayList<Triangle> triangles =  mesh.GetTriangles();

            Iterator<Triangle> it = triangles.iterator();
            while(it.hasNext())
            {
                Triangle triangle = it.next();
                triangle.RenderNoFill(color);
            }
        }
    }

    @Override
    public void RenderFill(Color color) 
    {
        if(this.isActive)
        {
            ArrayList<Triangle> triangles =  mesh.GetTriangles();

            Iterator<Triangle> it = triangles.iterator();
            while(it.hasNext())
            {
                Triangle triangle = it.next();
                triangle.RenderFill(Color.Mult(color, triangle.colorIntestiy));
            }
        }
    }

    @Override
    public String toString()
    {
        return this.name;
    }
}
