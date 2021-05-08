package tinyrenderer.core;

import tinyrenderer.Application;
import javafx.animation.AnimationTimer;

/**
 * {@code Engine} is the class that every application should call it's
 * methods upon intilization of the window. It is responsible for setting 
 * up objects in the scene and for updating them every frame with a fixed timestep.
 */
public class Engine implements IApplication
{
    private Engine() {}

    private static final Engine instance = new Engine();
    private static long prevNanoTime = System.nanoTime();
    private static double deltaTime;

    private void CalculateDelaTime(long currentNanoTime)
    {
        deltaTime = (currentNanoTime - prevNanoTime) / 1000000000.0;
        prevNanoTime = currentNanoTime;
    }

    public static Engine GetInstance()
    {
        return instance;
    }

    @Override
    public void Setup()
    {
        
    }

    @Override
    public void Update()
    {
        new AnimationTimer()
        {
            @Override
            public void handle(long currentNanoTime)
            {
                CalculateDelaTime(currentNanoTime);

                FrameBuffer frameBuffer = Application.GetFrameBuffer();

                frameBuffer.ClearBuffer(Color.BLACK);
                
                //Update objects

                frameBuffer.RenderBuffer();
            }    
        }.start();
    }
}
