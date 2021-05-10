package tinyrenderer.core;

import tinyrenderer.Application;
import tinyrenderer.geometry.Mesh;
import tinyrenderer.math.Color;
import tinyrenderer.math.Vector3;

import java.util.ArrayList;

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

    private Mesh cube;

    @Override
    public void Setup()
    {
        Vector3[] vertices = 
        {
            new Vector3(-1.0f, -1.0f,  1.0f), //0
            new Vector3( 1.0f, -1.0f,  1.0f), //1
            new Vector3( 1.0f,  1.0f,  1.0f), //2
            new Vector3(-1.0f,  1.0f,  1.0f), //3
            new Vector3(-1.0f, -1.0f, -1.0f), //4
            new Vector3( 1.0f, -1.0f, -1.0f), //5
            new Vector3( 1.0f,  1.0f, -1.0f), //6
            new Vector3(-1.0f,  1.0f, -1.0f)  //7
        };

        int[] indices = 
        {
            0, 1, 2,
            2, 3, 0,
            
            1, 5, 6,
            6, 2, 1,
            
            7, 6, 5,
            5, 4, 7,
            
            4, 0, 3,
            3, 7, 4,
            
            4, 5, 1,
            1, 0, 4,
            
            3, 2, 6,
            6, 7, 3
        };

        ArrayList<Vector3> v = new ArrayList<>();
        for(int i = 0; i < vertices.length; i++)
            v.add(vertices[i]);

        ArrayList<Integer> i = new ArrayList<>();
        for(int j = 0; j < indices.length; j++)
            i.add(indices[j]);

        cube = new Mesh(v, i);
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

                Application.GetFrameBuffer().ClearBuffer(Color.BLACK);
                
                //Update objects
                cube.UpdateMesh();
                Application.GetFrameBuffer().DrawPolygon(cube, Color.RED, false);

                Application.GetFrameBuffer().RenderBuffer();
            }    
        }.start();
    }
}
