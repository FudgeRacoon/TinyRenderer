package tinyrenderer.geometry;

import tinyrenderer.Application;
import tinyrenderer.core.Engine;
import tinyrenderer.math.Vector2;
import tinyrenderer.math.Vector3;
import tinyrenderer.math.Color;

public class Utilities 
{   
    private Utilities() {}

    public static Vector3 GetFaceNormal(Vector3 a, Vector3 b, Vector3 c)
    {
        Vector3 ab = Vector3.Sub(b, a);
        Vector3 ac = Vector3.Sub(c, a);
        ab = Vector3.Normalize(ab);
        ac = Vector3.Normalize(ac);

        Vector3 normal = Vector3.Cross(ab, ac);
        return Vector3.Normalize(normal);
    }

    public static boolean BackFaceCull(Vector3 a, Vector3 b, Vector3 c)
    {
        Vector3 normal = GetFaceNormal(a, b, c);

        Vector3 cameraRay = Vector3.Sub(a, Engine.GetInstance().camera.direction);

        return Vector3.Dot(cameraRay, normal) > 0 ? false : true;
    }    

    public static void FlatBottomFill(Vector2 a, Vector2 b, Vector2 c, Color color)
    {
        float inverseSlopeLeft = (b.x - a.x) / (b.y - a.y);
        float inverseSlopeRight = (c.x - a.x) / (c.y - a.y);

        float left = a.x;
        float right = a.x;

        for(float scanLine = a.y; scanLine <= c.y; scanLine++)
        {
            Application.GetFrameBuffer().DrawLine(left, scanLine, right, scanLine, color);
            left += inverseSlopeLeft;
            right += inverseSlopeRight;
        }
    }

    public static void FlatTopFill(Vector2 a, Vector2 b, Vector2 c, Color color)
    {
        float inverseSlopeLeft = (a.x - c.x) / (a.y - c.y);
        float inverseSlopeRight = (b.x - c.x) / (b.y - c.y);

        float left = c.x;
        float right = c.x;

        for(float scanLine = c.y; scanLine >= a.y; scanLine--)
        {
            Application.GetFrameBuffer().DrawLine(left, scanLine, right, scanLine, color);
            left += inverseSlopeLeft;
            right += inverseSlopeRight;
        }
    }
}
