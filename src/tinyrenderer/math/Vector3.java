package tinyrenderer.math;

/**
 * Representation of 3D vectors and points.
 */
public class Vector3 implements Comparable<Vector3>
{
    public float x, y, z;

    public Vector3()
    {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }
    public Vector3(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static final Vector3 ZERO = new Vector3();
    public static final Vector3 UP = new Vector3(0, 1, 0);
    public static final Vector3 DOWN = new Vector3(0, -1, 0);
    public static final Vector3 RIGHT = new Vector3(1, 0, 0);
    public static final Vector3 LEFT = new Vector3(-1, 0, 0);
    public static final Vector3 FRONT = new Vector3(0, 0, 1);

    public static Vector3 Add(Vector3 v1, Vector3 v2)
    {
        return new Vector3(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
    }
    public static Vector3 Sub(Vector3 v1, Vector3 v2)
    {
        return new Vector3(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z);
    }
    public static Vector3 Mult(Vector3 v, float value)
    {
        return new Vector3(v.x * value, v.y * value, v.z * value);
    }
    public static Vector3 Negate(Vector3 v)
    {
        return new Vector3(-v.x, -v.y, -v.z);
    }

    public static float Magnitude(Vector3 v)
    {
        return (float)Math.sqrt((v.x * v.x) + (v.y * v.y) + (v.z * v.z));
    }
    public static Vector3 Normalize(Vector3 v)
    {
        return Vector3.Mult(v, 1 / Vector3.Magnitude(v));
    }
    public static Float Dot(Vector3 v1, Vector3 v2)
    {
        return ((v1.x * v2.x) + (v1.y * v2.y) + (v1.z * v2.z));
    }
    public static Vector3 Cross(Vector3 v1, Vector3 v2)
    {
        return new Vector3((v1.y * v2.z) - (v1.z * v2.y), (v1.z * v2.x) - (v1.x * v2.z), (v1.x * v2.y) - (v1.y * v2.x));
    }
    public static float Distance(Vector3 v1, Vector3 v2)
    {
        float diffX = v1.x - v2.x;
        float diffY = v1.y - v2.y;
        float diffZ = v1.z - v2.z;
        return Vector3.Magnitude(new Vector3(diffX, diffY, diffZ));
    }
    public static float Angle(Vector3 v1, Vector3 v2)
    {
        float dot = Vector3.Dot(v1, v2);
        float v1Mag = Vector3.Magnitude(v1);
        float v2Mag = Vector3.Magnitude(v2);

        return (float)Math.acos(dot / v1Mag * v2Mag);
    }
    public static Vector3 Lerp(Vector3 v1, Vector3 v2, float t)
    {
        Vector3 result = Vector3.Sub(v2, v1);
        result = Vector3.Mult(result, t);
        return Vector3.Add(v1, result);
    }

    @Override
    public int compareTo(Vector3 v)
    {
        float thisMag = Vector3.Magnitude(this);
        float otherMag = Vector3.Magnitude(v);

        if(thisMag > otherMag)
            return 1;
        else if(thisMag < otherMag)
            return -1;
        else
            return 0;
    }

    @Override public String toString()
    {
        return this.x + " " + this.y + " " + this.z;
    }
}
