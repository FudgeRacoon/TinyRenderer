package tinyrenderer.core;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyCode;
import tinyrenderer.math.Vector2;

public class InputManager
{
    private InputManager() {}

    private static Node node;
    private static ArrayList<String> keyState;
    private static ArrayList<String> prevKeyState;
    private static Vector2 mousePosition;

    public static void Init(Node sceneNode)
    {
        node = sceneNode; node.setFocusTraversable(true);
        keyState = new ArrayList<String>();
        prevKeyState = new ArrayList<String>();
        mousePosition = new Vector2();
    }

    public static void Update()
    {
        node.setOnKeyPressed(new EventHandler<KeyEvent>()
            {
                @Override
                public void handle(KeyEvent event)
                {
                    String keyCode = event.getCode().toString();
                    if(!keyState.contains(keyCode))
                        keyState.add(keyCode);
                }
            }
        );

        node.setOnKeyReleased(new EventHandler<KeyEvent>()
            {
                @Override
                public void handle(KeyEvent event)
                {
                    String keyCode = event.getCode().toString();
                    keyState.remove(keyCode);
                }
            }
        );
        
        node.setOnMouseMoved(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event)
                {
                    mousePosition.x = (float)event.getX();
                    mousePosition.y = (float)event.getY();
                }
            }
        );
    }

    public static void PrevUpdate()
    {
        prevKeyState.clear();
        prevKeyState.addAll(keyState);
    }

    /**
     * Returns true during the frame the user starts pressing down the key identified by name.
     */
    public static boolean GetKey(KeyCode keyCode)
    {
        return keyState.contains(keyCode.toString());
    }
    /**
     * Returns true during the frame the user starts pressing down the key identified by name.
     */
    public static boolean GetKeyDown(KeyCode keyCode)
    {
        return !prevKeyState.contains(keyCode.toString()) && keyState.contains(keyCode.toString());
    }
    /**
     * Returns true during the frame the user releases the key identified by name.
     */
    public static boolean GetKeyUp(KeyCode keyCode)
    {
        return prevKeyState.contains(keyCode.toString()) && !keyState.contains(keyCode.toString());
    }
    /**
     * Returns the current mouse position in pixel coordinates.
     */
    public static Vector2 GetMousePosition()
    {
        return mousePosition;
    }
}
