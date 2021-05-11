package tinyrenderer.math;

/**
 * Representation of 2D vectors and points.
 */
public class Vector2 implements Comparable<Vector2>
{
    public float x, y;

    public Vector2()
    {
        this.x = 0;
        this.y = 0;
    }
    public Vector2(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public static final Vector2 ZERO = new Vector2();
    public static final Vector2 UP = new Vector2(0, 1);
    public static final Vector2 DOWN = new Vector2(0, -1);
    public static final Vector2 RIGHT = new Vector2(1, 0);
    public static final Vector2 LEFT = new Vector2(-1, 0);

    public static Vector2 Add(Vector2 v1, Vector2 v2)
    {
        return new Vector2(v1.x + v2.x, v1.y + v2.y);
    }
    public static Vector2 Sub(Vector2 v1, Vector2 v2)
    {
        return new Vector2(v1.x - v2.x, v1.y - v2.y);
    }
    public static Vector2 Mult(Vector2 v, float value)
    {
        return new Vector2(v.x * value, v.y * value);
    }
    public static Vector2 Negate(Vector2 v)
    {
        return new Vector2(-v.x, -v.y);
    }

    public static float Magnitude(Vector2 v)
    {
        return (float)Math.sqrt((v.x * v.x) + (v.y * v.y));
    }
    public static Vector2 Normalize(Vector2 v)
    {
        return Vector2.Mult(v, 1 / Vector2.Magnitude(v));
    }
    public static Float Dot(Vector2 v1, Vector2 v2)
    {
        return ((v1.x * v2.x) + (v1.y * v2.y));
    }
    public static float Distance(Vector2 v1, Vector2 v2)
    {
        float diffX = v1.x - v2.x;
        float diffY = v1.y - v2.y;
        return Vector2.Magnitude(new Vector2(diffX, diffY));
    }
    public static float Angle(Vector2 v1, Vector2 v2)
    {
        float dot = Vector2.Dot(v1, v2);
        float v1Mag = Vector2.Magnitude(v1);
        float v2Mag = Vector2.Magnitude(v2);

        return (float)Math.acos(dot / v1Mag * v2Mag);
    }
    public static Vector2 Lerp(Vector2 v1, Vector2 v2, float t)
    {
        Vector2 result = Vector2.Sub(v2, v1);
        result = Vector2.Mult(result, t);
        return Vector2.Add(v1, result);
    }

    @Override
    public int compareTo(Vector2 v)
    {
        float thisMag = Vector2.Magnitude(this);
        float otherMag = Vector2.Magnitude(v);

        if(thisMag > otherMag)
            return 1;
        else if(thisMag < otherMag)
            return -1;
        else
            return 0;
    }

    @Override public String toString()
    {
        return this.x + " " + this.y;
    }
}
