package tinyrenderer.core;

import tinyrenderer.Application;
import tinyrenderer.math.Color;
import tinyrenderer.math.Vector3;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;

/**
 * {@code Engine} is the class that every application should call it's
 * methods upon intilization of the window. It is responsible for setting 
 * up objects in the scene and for updating them every frame with a fixed timestep.
 */
public class Engine
{
    private Engine() {}

    private static final Engine instance = new Engine();
    private static long prevNanoTime = System.nanoTime();
    private static double deltaTime = 0.0f;

    private Entity cube;
    private Entity cube_2;
    private Entity cube_3;
    
    public Camera camera;
    private float cameraMovementSpeed;
    
    private void Setup()
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

        camera = new Camera();
        camera.SetFov(25.0f);
        cameraMovementSpeed = 25.0f;

        cube = new Entity("Cube1", v, i);
        cube_2 = new Entity("Cube2", v, i);
        cube_3 = new Entity("Cube3", v, i);

        cube_2.position.x = 10;
        cube_2.position.z = 5;

        cube_3.position.x = -10;
        cube_3.position.z = 3;
    }

    private void Update()
    {   
        if(InputManager.GetKey(KeyCode.D))
            camera.position.x += cameraMovementSpeed * deltaTime;
        else if(InputManager.GetKey(KeyCode.A))
            camera.position.x -= cameraMovementSpeed * deltaTime;
        else if(InputManager.GetKey(KeyCode.W))
            camera.position.z += cameraMovementSpeed * deltaTime;
        else if(InputManager.GetKey(KeyCode.S))
            camera.position.z -= cameraMovementSpeed * deltaTime;
        else if(InputManager.GetKey(KeyCode.UP))
            camera.position.y += cameraMovementSpeed * deltaTime;
        else if(InputManager.GetKey(KeyCode.DOWN))
            camera.position.y -= cameraMovementSpeed * deltaTime;
        
        cube.Update(); cube.rotation.y += 1.0f;
        cube_2.Update(); cube_2.rotation.x += 1.0f;
        cube_3.Update(); cube_3.rotation.y -= 1.0f;
        Application.GetFrameBuffer().DrawPolygon(cube, Color.RED, false);
        Application.GetFrameBuffer().DrawPolygon(cube_2, Color.BLUE, false);
        Application.GetFrameBuffer().DrawPolygon(cube_3, Color.Yellow, false);
         
    }

    public static Engine GetInstance()
    {
        return instance;
    }

    public void Run()
    {
        this.Setup();
        
        new AnimationTimer()
        {
            @Override
            public void handle(long currentNanoTime)
            {
                deltaTime = (currentNanoTime - prevNanoTime) / 1000000000.0;
                prevNanoTime = currentNanoTime;

                InputManager.Update();

                Application.GetFrameBuffer().ClearBuffer(Color.BLACK);
                
                Update();

                Application.GetFrameBuffer().RenderBuffer();

                InputManager.PrevUpdate();
            }
            
        }.start();
    }
}
