package tinyrenderer.core;

import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import tinyrenderer.Application;
import tinyrenderer.geometry.IRenderable;
import tinyrenderer.math.Color;

/**
 * The {@code FrameBuffer} class stores pixel data that are used
 * to render and manage frames displayed to the window.
 */
public class FrameBuffer 
{
    private int[] pixelBuffer;
    private WritableImage textureBuffer;
    private PixelWriter pixelWriter;

    private final float FRAME_BUFFER_SIZE = 0.75f;

    public FrameBuffer()
    {
        this.pixelBuffer = new int[(int)(Application.GetWidth() * FRAME_BUFFER_SIZE) * Application.GetHeight()];
        this.textureBuffer = new WritableImage((int)(Application.GetWidth() * FRAME_BUFFER_SIZE), Application.GetHeight());
        this.pixelWriter = textureBuffer.getPixelWriter();
    }

    /**
     * Stores the pixel data associated with the texture into it.
     * 
     * @apiNote {@code RenderBuffer} should be called after clearing
     *          and performing updates on all objects in the world.
     */
    public void RenderBuffer()
    {
        this.pixelWriter.setPixels
        (
            0, 
            0, 
            (int)this.textureBuffer.getWidth(), 
            (int)this.textureBuffer.getHeight(), 
            PixelFormat.getIntArgbInstance(), 
            this.pixelBuffer, 
            0, 
            (int)this.textureBuffer.getWidth()
        );
    }

    /**
     * Renders a pixel onto the framebuffer by storing pixel data
     * in the specified coordinates of the texture.
     * 
     * @param x The x coordinate of the pixel to write
     * 
     * @param y The y coordinate of the pixel to write
     * 
     * @param color The color of the pixel
     * 
     * @throws NullPointerException
     */
    public void DrawPixel(int x, int y, Color color)
    {
        try
        {
            if(color == null)
                throw new NullPointerException("DrawPixel(int, int, Color) expects a color object but null was passed");
            else if(x >= 0 && x < this.textureBuffer.getWidth() && y >= 0 && y < this.textureBuffer.getHeight())
                this.pixelBuffer[(y * (int)this.textureBuffer.getWidth()) + x] = Color.RgbToHex(color);
        }
        catch(NullPointerException exception)
        {
            System.err.println(exception.getMessage());
            exception.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Renders a line segment onto the framebuffer.
     * 
     * @implNote {@code DrawLine} internally draws a line using
     *            the DDA line algorithm. It must be noted that
     *            DDA is not efficent as it rounds floating point
     *            values which decreases perfomance. A better approach
     *            is to use the bresenham line algorithm 
     * 
     * @param x1 The intial x coordinate of the line
     * @param y1 The intial y coordinate of the line
     * @param x2 The end x coordinate of the line
     * @param y2 The end y coordinate of the line
     * @param color The color of the line
     * 
     * @throws NullPointerException
     */
    public void DrawLine(float x1, float y1, float x2, float y2, Color color)
    {
        try
        {
            if(color == null)
                throw new NullPointerException("DrawLine(int, int, int, int, Color) expects a color object but null was passed");
            else
            {
                float deltaX = x2 - x1;
                float deltaY = y2 - y1;
                
                float steps = (float)Math.abs(deltaX) > Math.abs(deltaY) ? Math.abs(deltaX) : Math.abs(deltaY);

                float xInc = deltaX / steps;
                float yInc = deltaY / steps;

                float x = x1;
                float y = y1;

                for(int i = 0; i < steps; i++)
                {
                    DrawPixel(Math.round(x), Math.round(y), color);
                    x += xInc;
                    y += yInc;
                }
            }
        }
        catch(NullPointerException exception)
        {
            System.err.println(exception.getMessage());
            System.exit(-1);
        }
    }

    /**
     * Renders a {@code IRenderable} object onto the framebuffer.
     * 
     * @param polygon The object to render
     * @param color The color of the object
     * @param fill  Is the object filled or not
     * 
     * @throws NullPointerException
     */
    public void DrawPolygon(IRenderable polygon, Color color, boolean fill)
    {
        try
        {
            if(color == null)
                throw new NullPointerException("DrawPolygon(IRenderable, Color, boolean) expects a color object but null was passed");
            else
            {
                if(!fill)
                    polygon.RenderNoFill(color);
                else
                    polygon.RenderFill(color);
            }
        }
        catch(NullPointerException exception)
        {
            System.err.println(exception.getMessage());
            System.exit(-1);
        }
    }

    /**
     * Clears the entire framebuffer by storing each pixel
     * in the buffer with a specific color.
     * 
     * @param color The color to fill the entire framebuffer with
     * 
     * @throws NullPointerException
     */
    public void ClearBuffer(Color color)
    {
        try
        {
            if(color == null)
                throw new NullPointerException("ClearBuffer(Color) expects a color object but null was passed");
            else
                for(int y = 0; y < this.textureBuffer.getHeight(); y++)
                    for(int x = 0; x < this.textureBuffer.getWidth(); x++)
                        this.pixelBuffer[(y * (int)this.textureBuffer.getWidth()) + x] = Color.RgbToHex(color);
        }
        catch(NullPointerException exception)
        {
            System.err.println(exception.getMessage());
            System.exit(-1);
        }
    }

    /**
     * Returns the texture associated with the framebuffer.
     * 
     * @return The texture as an image
     */
    public Image GetTextureBuffer()
    {
        return (Image)this.textureBuffer;
    }
}
