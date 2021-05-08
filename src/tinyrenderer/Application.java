package tinyrenderer;

import tinyrenderer.core.Engine;
import tinyrenderer.core.FrameBuffer;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Application extends javafx.application.Application
{
    private static Stage window;
    private static Parent root;
    private static Scene scene;
    private static FrameBuffer frameBuffer;

    private static final int WIDTH = 1024;
    private static final int HEIGHT = 768;

    @Override 
    public void start(Stage stage) throws Exception
    {
        //Init the main window layout
        root = FXMLLoader.load(getClass().getResource("MainLayout.fxml"));
        
        //Init window scene
        scene = new Scene(root, WIDTH, HEIGHT);
        
        //Init framebuffer
        frameBuffer = new FrameBuffer();
        ImageView imageView = (ImageView)root.lookup("#imageView");
        imageView.setImage(frameBuffer.GetTextureBuffer());
        
        //Init window
        window = stage;
        window.setTitle("TinyRenderer");
        window.setScene(scene);
        window.setResizable(false);
        window.show();
        
        //Invoke the engine setup and start the program loop
        Engine.GetInstance().Setup();
        Engine.GetInstance().Update();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    public static int GetWidth()
    {
        return WIDTH;
    }
    public static int GetHeight()
    {
        return HEIGHT;
    }
    public static Stage GetWindow()
    {
        return window;
    }
    public static Parent Getroot()
    {
        return root;
    }
    public static Scene GetScene()
    {
        return scene;
    }
    public static FrameBuffer GetFrameBuffer()
    {
        return frameBuffer;
    }
}
