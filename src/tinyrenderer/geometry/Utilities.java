package tinyrenderer.geometry;

import tinyrenderer.core.Engine;
import tinyrenderer.math.Vector3;

public class Utilities 
{   
    private Utilities() {}

    public static float Clamp(float value, float min, float max)
    {
        if(value < min)
            return min;
        else if(value > max)
            return max;
        else
            return value;
    }

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
}
