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

    public static float c = 3;

    public Mesh(ArrayList<Vector3> vertices, ArrayList<Integer> indices)
    {
        this.vertices = vertices;
        this.indices = indices;
        this.triangles = new ArrayList<Triangle>();
    }

    public void UpdateMesh()
    {
        this.triangles.clear();

        for(int i = 0; i < this.indices.size(); i += 3)
        {
            Vector3[] processingVertices = new Vector3[3];
            
            processingVertices[0] = this.vertices.get(this.indices.get(i));
            processingVertices[1] = this.vertices.get(this.indices.get(i + 1));
            processingVertices[2] = this.vertices.get(this.indices.get(i + 2));

            Vector2[] projectedVertices = new Vector2[3];

            for(int j = 0; j < 3; j++)
            {
                Matrix4 scale = Matrix4.Scale(new Vector3(1, 1, 1));
                Matrix4 rotateX = Matrix4.Rotate(0.0f, Vector3.RIGHT);
                Matrix4 rotateY = Matrix4.Rotate(0.0f, Vector3.DOWN);
                Matrix4 rotateZ = Matrix4.Rotate(0.0f, Vector3.FRONT);
                Matrix4 translate = Matrix4.Translate(new Vector3(0, 0, 3));

                Matrix4 model = Matrix4.Identity();
                
                model = Matrix4.Mult(scale, model);
                model = Matrix4.Mult(rotateX, model);
                model = Matrix4.Mult(rotateY, model);
                model = Matrix4.Mult(rotateZ, model);
                model = Matrix4.Mult(translate, model);

                Vector4 processingVertex = Vector4.toVector4(processingVertices[j]);
                processingVertex = Matrix4.Mult(model, processingVertex);
                processingVertices[j] = Vector4.ToVector3(processingVertex);
                
                //Projection
                Vector3 vertix = processingVertices[j];
                projectedVertices[j] = new Vector2(vertix.x / vertix.z, vertix.y / vertix.z);
                projectedVertices[j].x *= 100;
                projectedVertices[j].y *= 100;
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
