package tinyrenderer.geometry;

import tinyrenderer.Application;
import tinyrenderer.math.*;

import java.util.ArrayList;
import java.util.Iterator;

public class Mesh implements IRenderable
{
    private ArrayList<Vector3> vertices;
    private ArrayList<Integer> indices;
    private ArrayList<Triangle> triangles;

    public Mesh(ArrayList<Vector3> vertices, ArrayList<Integer> indices)
    {
        this.vertices = vertices;
        this.indices = indices;
        this.triangles = new ArrayList<Triangle>();
    }

    public void UpdateMesh()
    {
        this.triangles.clear();

        for(int i = 0; i < this.indices.size() / 3; i++)
        {
            Vector3[] processingVertices = new Vector3[3];
            processingVertices[0] = this.vertices.get(this.indices.get(i));
            processingVertices[1] = this.vertices.get(this.indices.get(i + 1));
            processingVertices[2] = this.vertices.get(this.indices.get(i + 2));

            Vector2[] projectedVertices = new Vector2[3];

            for(int j = 0; j < 3; j++)
            {
                //Model

                //View

                //Projection
                Vector3 vertix = processingVertices[j];
                projectedVertices[j] = new Vector2((vertix.x / vertix.z) * 100, (vertix.y / vertix.z) * 100);
                projectedVertices[j] = Vector2.Add(projectedVertices[j], new Vector2(
                    (float)Application.GetFrameBuffer().GetTextureBuffer().getWidth() / 2, 
                    (float)Application.GetFrameBuffer().GetTextureBuffer().getHeight() / 2));
            }

            this.triangles.add(new Triangle(projectedVertices[0], projectedVertices[1], projectedVertices[2]));
        }
    }

    @Override
    public void RenderNoFill(Color color)
    {
        Iterator<Triangle> it = this.triangles.iterator();
        while(it.hasNext())
        {
            Triangle triangle = it.next();
            triangle.RenderNoFill(color);
        }
    }

    @Override
    public void RenderFill(Color color)
    {

    }
}
