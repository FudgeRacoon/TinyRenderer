package tinyrenderer.geometry;

import tinyrenderer.Application;
import tinyrenderer.core.Engine;
import tinyrenderer.math.*;

import java.util.ArrayList;
import java.util.Iterator;

public class Mesh implements IRenderable
{
    private ArrayList<Vector3> vertices;
    private ArrayList<Integer> indices;
    private ArrayList<Triangle> triangles;

    private float c = 0.0f;

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
            Vector2[] projectedVertices = new Vector2[3];

            processingVertices[0] = this.vertices.get(this.indices.get(i));
            processingVertices[1] = this.vertices.get(this.indices.get(i + 1));
            processingVertices[2] = this.vertices.get(this.indices.get(i + 2));

            for(int j = 0; j < 3; j++)
            {
                Vector4 processingVertex = Vector4.toVector4(processingVertices[j]);
                Engine.GetInstance().camera.position.z = -5;

                //#region Local to World space
                Matrix4 scale = Matrix4.Scale(new Vector3(1, 1, 1));
                Matrix4 rotateX = Matrix4.Rotate(0.0f, Vector3.RIGHT);
                Matrix4 rotateY = Matrix4.Rotate(c+= 0.005f, Vector3.UP);
                Matrix4 rotateZ = Matrix4.Rotate(0.0f, Vector3.FRONT);
                Matrix4 translate = Matrix4.Translate(new Vector3(0, 0, 0));

                Matrix4 model = Matrix4.Identity();
                model = Matrix4.Mult(scale, model);
                model = Matrix4.Mult(rotateX, model);
                model = Matrix4.Mult(rotateY, model);
                model = Matrix4.Mult(rotateZ, model);
                model = Matrix4.Mult(translate, model);

                processingVertex = Matrix4.Mult(model, processingVertex);
                //#endregion

                //#region World to View space
                processingVertex = Matrix4.Mult(Engine.GetInstance().camera.GetViewMatrix(), processingVertex);
                //#endregion

                //#region View to Clip space
                processingVertex = Matrix4.Mult(Engine.GetInstance().camera.GetProjectionMatrix(), processingVertex);
                //#endregion

                //#region Perspective Division
                if(processingVertex.w != 0.0f)
                {
                    processingVertex.x /= processingVertex.w;
                    processingVertex.y /= processingVertex.w;
                    processingVertex.z /= processingVertex.w;
                }
                //#endregion

                //#region Scale and position the mesh in center of scene
                projectedVertices[j] = new Vector2(processingVertex.x, processingVertex.y);
                projectedVertices[j].x *= 100;
                projectedVertices[j].y *= 100;
                projectedVertices[j].x += Application.GetFrameBuffer().GetTextureBuffer().getWidth() / 2;
                projectedVertices[j].y += Application.GetFrameBuffer().GetTextureBuffer().getHeight() / 2;
                //#endregion
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
