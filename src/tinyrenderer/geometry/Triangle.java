package tinyrenderer.geometry;

import tinyrenderer.Application;
import tinyrenderer.math.Color;
import tinyrenderer.math.Vector2;

public class Triangle implements IRenderable
{
    private Vector2 a, b, c;

    public Triangle(Vector2 a, Vector2 b, Vector2 c)
    {
        this.a = a;
        this.b = b;
        this.c = c;
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

    }
}
