package tinyrenderer.math;

import tinyrenderer.geometry.Utilities;

public class Color 
{
    public int r, g, b, a;

    public Color()
    {
        this.r = 255;
        this.g = 255;
        this.b = 255;
        this.a = 255;
    }
    public Color(int r, int g, int b)
    {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = 255;
    }
    public Color(int r, int g, int b, int a)
    {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public static final Color WHITE = new Color(255, 255, 255);
    public static final Color BLACK = new Color(0, 0, 0);
    public static final Color RED = new Color(255, 0, 0);
    public static final Color BLUE = new Color(0, 0, 255);
    public static final Color GREEN = new Color(0, 255, 0);
    public static final Color PINK = new Color(255, 192, 203);
    public static final Color CYAN = new Color(0, 255, 255);
    public static final Color PURPLE = new Color(128, 0, 128);
    public static final Color Yellow = new Color(255, 255, 0);

    public static Color Mult(Color color, float value)
    {
        return new Color
        (
            (int)Utilities.Clamp(color.r * value, 0, 255),
            (int)Utilities.Clamp(color.g * value, 0, 255),
            (int)Utilities.Clamp(color.b * value, 0, 255)
        );
    }

    /**
     * Converts a RGB {@code color}  to it's hexadecimal equivalent.
     * 
     * @param color RGB color
     * 
     * @return hexadicemal representation of the RGB {@code color} 
     */
    public static int RgbToHex(Color color)
    {
        int a = color.a;
        int r = color.r;
        int g = color.g;
        int b = color.b;

        return (a << 24) + (r << 16) + (g << 8) + b;
    }
}
