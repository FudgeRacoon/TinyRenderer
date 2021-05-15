package tinyrenderer.core;

import tinyrenderer.Application;
import tinyrenderer.geometry.ObjParser;
import tinyrenderer.math.Color;

import java.io.FileNotFoundException;
import java.io.IOException;

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
    
    public Camera camera;
    private float cameraMovementSpeed;
    private float cameraRotationSpeed;
    
    private void Setup()
    {
        camera = new Camera();
        camera.SetFov(20.0f);

        cameraMovementSpeed = 25.0f;
        cameraRotationSpeed = 45.0f;

        try
        {
            cube = ObjParser.LoadObj("cube.obj");
        }
        catch(FileNotFoundException exception)
        {
            System.err.println(exception.getMessage());
            exception.printStackTrace();
            System.exit(-1);
        }
        catch(IOException exception)
        {
            System.err.println(exception.getMessage());
            exception.printStackTrace();
            System.exit(-1);
        }
        catch(Exception exception)
        {
            System.err.println(exception.getMessage());
            exception.printStackTrace();
            System.exit(-1);
        }
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
            camera.rotation.x += cameraRotationSpeed * deltaTime;
        else if(InputManager.GetKey(KeyCode.DOWN))
            camera.rotation.x -= cameraRotationSpeed * deltaTime;
        else if(InputManager.GetKey(KeyCode.LEFT))
            camera.rotation.y += (float)(cameraRotationSpeed * deltaTime);
        else if(InputManager.GetKey(KeyCode.RIGHT))
            camera.rotation.y -= (float)(cameraRotationSpeed * deltaTime);
        
        cube.Update(); cube.rotation.y += 1.0f; cube.rotation.x += 1.0f;

        Application.GetFrameBuffer().DrawPolygon(cube, Color.WHITE, false);
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

                Application.GetFrameBuffer().ClearBuffer();
                
                Update();

                Application.GetFrameBuffer().RenderBuffer();

                InputManager.PrevUpdate();
            }
            
        }.start();
    }
}
