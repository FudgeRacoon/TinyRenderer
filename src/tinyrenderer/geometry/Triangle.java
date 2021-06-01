package tinyrenderer.geometry;

import tinyrenderer.Application;
import tinyrenderer.math.Color;
import tinyrenderer.math.Vector2;

public class Triangle implements IRenderable
{
    private Vector2 a, b, c;
    public float colorIntestiy;

    public Triangle(Vector2 a, Vector2 b, Vector2 c, float colorIntesity)
    {
        this.a = a;
        this.b = b;
        this.c = c;

        this.colorIntestiy = colorIntesity;
    }

    private float CrossProduct(Vector2 v1, Vector2 v2)
    {
        return (v1.x * v2.y) - (v1.y * v2.x);
    }

    @Override
    public void RenderNoFill(Color color)
    {
        Application.GetFrameBuffer().DrawLine(this.a.x, this.a.y, this.b.x, this.b.y, color);
        Application.GetFrameBuffer().DrawLine(this.b.x, this.b.y, this.c.x, this.c.y, color);
        Application.GetFrameBuffer().DrawLine(this.c.x, this.c.y, this.a.x, this.a.y, color);
    }

    @Override
    public void RenderFill(Color color)
    {
        int maxX = (int)Math.max(a.x, Math.max(b.x, c.x));
        int minX = (int)Math.min(a.x, Math.min(b.x, c.x));
        int maxY = (int)Math.max(a.y, Math.max(b.y, c.y));
        int minY = (int)Math.min(a.y, Math.min(b.y, c.y));

        Vector2 vs1 = new Vector2(b.x - a.x, b.y - a.y);
        Vector2 vs2 = new Vector2(c.x - a.x, c.y - a.y);

        for (int x = minX; x <= maxX; x++)
        {
            for (int y = minY; y <= maxY; y++)
            {
                Vector2 q = new Vector2(x - a.x, y - a.y);
                
                float s = (float)CrossProduct(q, vs2) / CrossProduct(vs1, vs2);
                float t = (float)CrossProduct(vs1, q) / CrossProduct(vs1, vs2); 

                if ( (s >= 0) && (t >= 0) && (s + t <= 1))
                    Application.GetFrameBuffer().DrawPixel(x, y, color);
            }
        }
    }
}
