package tinyrenderer.math;

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
    public Vector4(Vector3 v)
    {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
        this.w = 1;
    }

}
