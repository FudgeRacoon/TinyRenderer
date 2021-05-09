package tinyrenderer.math;

/**
 * Representation of 4D vectors. This structure is mostly
 * used to represent homogeneous coordinates.
 */
public class Vector4
{
    public float x, y, z, w;
    
    public Vector4()
    {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.w = 1;
    }
    public Vector4(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = 1;
    }
    public Vector4(float x, float y, float z, float w)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }
  
    public static Vector4 Add(Vector4 v1, Vector4 v2)
    {
        return new Vector4(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z, v1.w + v2.w);
    }
    public static Vector4 Sub(Vector4 v1, Vector4 v2)
    {
        return new Vector4(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z, v1.w - v2.w);
    }
    public static Vector4 Mult(Vector4 v, float value)
    {
        return new Vector4(v.x * value, v.y * value, v.z * value, v.w * value);
    }
    public static Vector4 Negate(Vector4 v)
    {
        return new Vector4(-v.x, -v.y, -v.z, -v.w);
    }

    public static Vector3 ToVector3(Vector4 v)
    {
        return new Vector3(v.x, v.y, v.z);
    }
    public static Vector4 toVector4(Vector3 v)
    {
        return new Vector4(v.x, v.y, v.z);
    }

    @Override
    public String toString()
    {
        return this.x + " "  + this.y + " " + this.z + " " + this.w;
    }
}
