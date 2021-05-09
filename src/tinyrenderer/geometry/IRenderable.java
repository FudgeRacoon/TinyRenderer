package tinyrenderer.geometry;

import tinyrenderer.math.Color;

public interface IRenderable 
{   
    /**
     * This function defines how an object shall be rendererd 
     * on the screen by the renderer in order to be unfilled
     */
    public void RenderNoFill(Color color);

    /**
     * This function defines how an object shall be rendererd 
     * on the screen by the renderer in order to be filled
     */
    public void RenderFill(Color color);    
}
