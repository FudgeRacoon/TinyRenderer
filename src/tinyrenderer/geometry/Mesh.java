package tinyrenderer.geometry;

import tinyrenderer.Application;
import tinyrenderer.core.Engine;
import tinyrenderer.math.*;

import java.util.ArrayList;

public class Mesh
{
    private ArrayList<Vector3> vertices;
    private ArrayList<Integer> indices;
    private ArrayList<Triangle> triangles;

    private final Vector3 lightDirection = Vector3.FRONT;

    public Mesh() {}
    public Mesh(ArrayList<Vector3> vertices, ArrayList<Integer> indices)
    {
        this.vertices = vertices;
        this.indices = indices;
        this.triangles = new ArrayList<Triangle>();
    }

    public void UpdateMesh(Vector3 position, Vector3 rotation, Vector3 scale)
    {
        this.triangles.clear();

        for(int i = 0; i < this.indices.size(); i += 3)
        {
            Vector3[] processingVertices = new Vector3[3];
            Vector2[] projectedVertices = new Vector2[3];

            processingVertices[0] = this.vertices.get(this.indices.get(i) - 1);
            processingVertices[1] = this.vertices.get(this.indices.get(i + 1) - 1);
            processingVertices[2] = this.vertices.get(this.indices.get(i + 2) - 1);

            for(int j = 0; j < 3; j++)
            {
                Vector4 processingVertex = Vector4.toVector4(processingVertices[j]);

                //#region Local to World space using model matrix
                Matrix4 scaleMatrix = Matrix4.Scale(scale);
                Matrix4 rotateX = Matrix4.Rotate(rotation.x, Vector3.RIGHT);
                Matrix4 rotateY = Matrix4.Rotate(rotation.y, Vector3.UP);
                Matrix4 rotateZ = Matrix4.Rotate(rotation.z, Vector3.FRONT);
                Matrix4 translate = Matrix4.Translate(position);

                Matrix4 model = Matrix4.Identity();
                model = Matrix4.Mult(scaleMatrix, model);
                model = Matrix4.Mult(rotateX, model);
                model = Matrix4.Mult(rotateY, model);
                model = Matrix4.Mult(rotateZ, model);
                model = Matrix4.Mult(translate, model);

                processingVertex = Matrix4.Mult(model, processingVertex);
                //#endregion

                //#region World to View space using view matrix
                processingVertex = Matrix4.Mult(Engine.GetInstance().camera.GetViewMatrix(), processingVertex);
                //#endregion

                //#region View to Clip space using projection matrix
                processingVertex = Matrix4.Mult(Engine.GetInstance().camera.GetProjectionMatrix(), processingVertex);
                //#endregion
                
                processingVertices[j] = Vector4.ToVector3(processingVertex);

                //#region Perspective Division
                if(processingVertex.w != 0.0f)
                {
                    processingVertex.x /= processingVertex.w;
                    processingVertex.y /= processingVertex.w;
                    processingVertex.z /= processingVertex.w;
                }
                //#endregion

                //#region Window to Viewport
                projectedVertices[j] = new Vector2(processingVertex.x, processingVertex.y);
                projectedVertices[j].x *= 100;
                projectedVertices[j].y *= 100;
                projectedVertices[j].x += Application.GetFrameBuffer().GetTextureBuffer().getWidth() / 2;
                projectedVertices[j].y += Application.GetFrameBuffer().GetTextureBuffer().getHeight() / 2;
                //#endregion
            }

            //#region Flat-Shading
            Vector3 normal = Utilities.GetFaceNormal(processingVertices[0], processingVertices[1], processingVertices[2]);
            float colorIntensity = Vector3.Dot(lightDirection, normal);
            //#endregion

            //#region Back-Face Culling
            if(!Utilities.BackFaceCull(processingVertices[0], processingVertices[1], processingVertices[2]))
                this.triangles.add(new Triangle(projectedVertices[0], projectedVertices[1], projectedVertices[2], colorIntensity));
            //#endregion
        }
    }

    public ArrayList<Triangle> GetTriangles()
    {
        return this.triangles;
    }
}
