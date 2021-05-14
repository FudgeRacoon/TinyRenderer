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
        Vector2[] sortedVertices = {a, b, c};
        for(int index = 1; index < 3; index++)
        {
            Vector2 marker = sortedVertices[index];
            int pointer = index - 1;

            while(pointer >= 0 && sortedVertices[pointer].y > marker.y)
            {
                sortedVertices[pointer + 1] = sortedVertices[pointer];
                pointer--; 
            }

            sortedVertices[++pointer] = marker;
        }

        if(sortedVertices[1].y == sortedVertices[2].y)
        {
            Utilities.FlatBottomFill(sortedVertices[0], sortedVertices[1], sortedVertices[2], color);
        }
        else if(sortedVertices[0].y == sortedVertices[1].y)
        {
            Utilities.FlatTopFill(sortedVertices[0], sortedVertices[1], sortedVertices[2], color);
        }
        else
        {
            float mY = sortedVertices[1].y;
            float t = ( mY - sortedVertices[0].y ) / ( sortedVertices[2].y - sortedVertices[0].y );
            float mX = sortedVertices[0].x + ( sortedVertices[2].x - sortedVertices[0].x) * t;

            Utilities.FlatBottomFill(sortedVertices[0], sortedVertices[1], new Vector2(mX, mY), color);
            Utilities.FlatTopFill(new Vector2(mX, mY), sortedVertices[1], sortedVertices[2], color);
        }
    }
}
