package tinyrenderer.core;

import tinyrenderer.Application;

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

    public Camera camera;
    private final float cameraMovementSpeed = 50.0f;
    private final float cameraRotationSpeed = 80.0f;
    
    private void Setup()
    {
        camera = new Camera();
        camera.SetFov(20.0f);
        camera.position.z = -5.0f;
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
        else if(InputManager.GetKey(KeyCode.LEFT))
            camera.rotation.y += (float)(cameraRotationSpeed * deltaTime);
        else if(InputManager.GetKey(KeyCode.RIGHT))
            camera.rotation.y -= (float)(cameraRotationSpeed * deltaTime);
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
