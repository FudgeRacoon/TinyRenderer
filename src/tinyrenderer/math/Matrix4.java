package tinyrenderer.math;

/**
 * A standard 4x4 matrix
 */
public class Matrix4 
{
    private static final int ROWS = 4;
    private static final int COLOUMNS = 4;
    private float[][] n = new float[4][4];

    public Matrix4()
    {
        for(int row = 0; row < ROWS; row++)
            for(int column = 0; column < COLOUMNS; column++)
                n[row][column] = 0;
    }
    public Matrix4(float n00, float n01, float n02, float n03,
                   float n10, float n11, float n12, float n13,
                   float n20, float n21, float n22, float n23,
                   float n30, float n31, float n32, float n33)
    {
        n[0][0] = n00; n[0][1] = n01; n[0][2] = n02; n[0][3] = n03;
        n[1][0] = n10; n[1][1] = n11; n[1][2] = n12; n[1][3] = n13;
        n[2][0] = n20; n[2][1] = n21; n[2][2] = n22; n[2][3] = n23;
        n[3][0] = n30; n[3][1] = n31; n[3][2] = n32; n[3][3] = n33;
    }
    public Matrix4(Vector4 v1, Vector4 v2, Vector4 v3, Vector4 v4)
    {
        n[0][0] = v1.x; n[0][1] = v1.y; n[0][2] = v1.z; n[0][3] = v1.w;
        n[1][0] = v2.x; n[1][1] = v2.y; n[1][2] = v2.z; n[1][3] = v2.w;
        n[2][0] = v3.x; n[2][1] = v3.y; n[2][2] = v3.z; n[2][3] = v3.w;
        n[3][0] = v4.x; n[3][1] = v4.y; n[3][2] = v4.z; n[3][3] = v4.w;
    }

    public float Get(int row, int column)
    {
        return n[row][column];
    }
    public static Matrix4 Add(Matrix4 m1, Matrix4 m2)
    {
        return new Matrix4
        (
            m1.Get(0, 0) + m2.Get(0, 0), m1.Get(0, 1) + m2.Get(0, 1), m1.Get(0, 2) + m2.Get(0, 2), m1.Get(0, 3) + m2.Get(0, 3),
            m1.Get(1, 0) + m2.Get(1, 0), m1.Get(1, 1) + m2.Get(1, 1), m1.Get(1, 2) + m2.Get(1, 2), m1.Get(1, 3) + m2.Get(1, 3),
            m1.Get(2, 0) + m2.Get(2, 0), m1.Get(2, 1) + m2.Get(2, 1), m1.Get(2, 2) + m2.Get(2, 2), m1.Get(2, 3) + m2.Get(2, 3),
            m1.Get(3, 0) + m2.Get(3, 0), m1.Get(3, 1) + m2.Get(3, 1), m1.Get(3, 2) + m2.Get(3, 2), m1.Get(3, 3) + m2.Get(3, 3)
        );
    }
    public static Matrix4 Sub(Matrix4 m1, Matrix4 m2)
    {
        return new Matrix4
        (
            m1.Get(0, 0) - m2.Get(0, 0), m1.Get(0, 1) - m2.Get(0, 1), m1.Get(0, 2) - m2.Get(0, 2), m1.Get(0, 3) - m2.Get(0, 3),
            m1.Get(1, 0) - m2.Get(1, 0), m1.Get(1, 1) - m2.Get(1, 1), m1.Get(1, 2) - m2.Get(1, 2), m1.Get(1, 3) - m2.Get(1, 3),
            m1.Get(2, 0) - m2.Get(2, 0), m1.Get(2, 1) - m2.Get(2, 1), m1.Get(2, 2) - m2.Get(2, 2), m1.Get(2, 3) - m2.Get(2, 3),
            m1.Get(3, 0) - m2.Get(3, 0), m1.Get(3, 1) - m2.Get(3, 1), m1.Get(3, 2) - m2.Get(3, 2), m1.Get(3, 3) - m2.Get(3, 3)
        );
    }
    public static Matrix4 Mult(Matrix4 m1, Matrix4 m2)
    {
        return new Matrix4
        (
            m1.Get(0, 0) * m2.Get(0, 0) + m1.Get(0, 1) * m2.Get(1,0) + m1.Get(0, 2) * m2.Get(2,0) + m1.Get(0, 3) * m2.Get(3,0),
            m1.Get(0, 0) * m2.Get(0, 1) + m1.Get(0, 1) * m2.Get(1,1) + m1.Get(0, 2) * m2.Get(2,1) + m1.Get(0, 3) * m2.Get(3,1),
            m1.Get(0, 0) * m2.Get(0, 2) + m1.Get(0, 1) * m2.Get(1,2) + m1.Get(0, 2) * m2.Get(2,2) + m1.Get(0, 3) * m2.Get(3,2),
            m1.Get(0, 0) * m2.Get(0, 3) + m1.Get(0, 1) * m2.Get(1,3) + m1.Get(0, 2) * m2.Get(2,3) + m1.Get(0, 3) * m2.Get(3,3),

            m1.Get(1, 0) * m2.Get(0, 0) + m1.Get(1, 1) * m2.Get(1,0) + m1.Get(1, 2) * m2.Get(2,0) + m1.Get(1, 3) * m2.Get(3,0),
            m1.Get(1, 0) * m2.Get(0, 1) + m1.Get(1, 1) * m2.Get(1,1) + m1.Get(1, 2) * m2.Get(2,1) + m1.Get(1, 3) * m2.Get(3,1),
            m1.Get(1, 0) * m2.Get(0, 2) + m1.Get(1, 1) * m2.Get(1,2) + m1.Get(1, 2) * m2.Get(2,2) + m1.Get(1, 3) * m2.Get(3,2),
            m1.Get(1, 0) * m2.Get(0, 3) + m1.Get(1, 1) * m2.Get(1,3) + m1.Get(1, 2) * m2.Get(2,3) + m1.Get(1, 3) * m2.Get(3,3),

            m1.Get(2, 0) * m2.Get(0, 0) + m1.Get(2, 1) * m2.Get(1,0) + m1.Get(2, 2) * m2.Get(2,0) + m1.Get(2, 3) * m2.Get(3,0),
            m1.Get(2, 0) * m2.Get(0, 1) + m1.Get(2, 1) * m2.Get(1,1) + m1.Get(2, 2) * m2.Get(2,1) + m1.Get(2, 3) * m2.Get(3,1),
            m1.Get(2, 0) * m2.Get(0, 2) + m1.Get(2, 1) * m2.Get(1,2) + m1.Get(2, 2) * m2.Get(2,2) + m1.Get(2, 3) * m2.Get(3,2),
            m1.Get(2, 0) * m2.Get(0, 3) + m1.Get(2, 1) * m2.Get(1,3) + m1.Get(2, 2) * m2.Get(2,3) + m1.Get(2, 3) * m2.Get(3,3),

            m1.Get(3, 0) * m2.Get(0, 0) + m1.Get(3, 1) * m2.Get(1,0) + m1.Get(3, 2) * m2.Get(2,0) + m1.Get(3, 3) * m2.Get(3,0),
            m1.Get(3, 0) * m2.Get(0, 1) + m1.Get(3, 1) * m2.Get(1,1) + m1.Get(3, 2) * m2.Get(2,1) + m1.Get(3, 3) * m2.Get(3,1),
            m1.Get(3, 0) * m2.Get(0, 2) + m1.Get(3, 1) * m2.Get(1,2) + m1.Get(3, 2) * m2.Get(2,2) + m1.Get(3, 3) * m2.Get(3,2),
            m1.Get(3, 0) * m2.Get(0, 3) + m1.Get(3, 1) * m2.Get(1,3) + m1.Get(3, 2) * m2.Get(2,3) + m1.Get(3, 3) * m2.Get(3,3)
        );
    }
    public static Vector4 Mult(Matrix4 m, Vector4 v)
    {
       return new Vector4
       (
            m.Get(0, 0) * v.x + m.Get(0, 1) * v.y + m.Get(0, 2) * v.z + m.Get(0, 3) * v.w,
            m.Get(1, 0) * v.x + m.Get(1, 1) * v.y + m.Get(1, 2) * v.z + m.Get(1, 3) * v.w,
            m.Get(2, 0) * v.x + m.Get(2, 1) * v.y + m.Get(2, 2) * v.z + m.Get(2, 3) * v.w,
            m.Get(3, 0) * v.x + m.Get(3, 1) * v.y + m.Get(3, 2) * v.z + m.Get(3, 3) * v.w
       );
    }

    public static Matrix4 Identity()
    {
        return new Matrix4
        (
            1, 0, 0, 0,
            0, 1, 0, 0,
            0, 0, 1, 0,
            0, 0, 0, 1
        );
    }
    public static Matrix4 Translate(Vector3 direction)
    {
        Matrix4 m = Identity();

        m.n[0][3] = direction.x;
        m.n[1][3] = direction.y;
        m.n[2][3] = direction.z;

        return m;
    }
    public static Matrix4 Scale(Vector3 scaleFactor)
    {
        Matrix4 m = Identity();

        m.n[0][0] = scaleFactor.x;
        m.n[1][1] = scaleFactor.y;
        m.n[2][2] = scaleFactor.z;
        
        return m;
    }
    public static Matrix4 Rotate(float angle, Vector3 axis)
    {
        Matrix4 m = Identity();

        if(axis.x == 1 && axis.y == 0 && axis.z == 0)
        {
            m.n[1][1] = (float)Math.cos(Math.toRadians(angle));
            m.n[1][2] = (float)Math.sin(Math.toRadians(angle));
            m.n[2][1] = (float)-Math.sin(Math.toRadians(angle));
            m.n[2][2] = (float)Math.cos(Math.toRadians(angle));

            return m;
        }
        else if(axis.x == 0 && axis.y == 1 && axis.z == 0)
        {
            m.n[0][0] = (float)Math.cos(Math.toRadians(angle));
            m.n[0][2] = (float)-Math.sin(Math.toRadians(angle));
            m.n[2][0] = (float)Math.sin(Math.toRadians(angle));
            m.n[2][2] = (float)Math.cos(Math.toRadians(angle));

            return m;
        }
        else if(axis.x == 0 && axis.y == 0 && axis.z == 1)
        {
            m.n[0][0] = (float)Math.cos(Math.toRadians(angle));
            m.n[0][1] = (float)-Math.sin(Math.toRadians(angle));
            m.n[1][0] = (float)Math.sin(Math.toRadians(angle));
            m.n[1][1] = (float)Math.cos(Math.toRadians(angle));

            return m;
        }
        else
            return m;
    }
}
