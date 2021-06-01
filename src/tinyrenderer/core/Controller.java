package tinyrenderer.core;

import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.input.ContextMenuEvent;

public class Controller 
{
    @FXML private ListView<Entity> entityList;
    @FXML private ContextMenu listViewContextMenu;

    public void HandleContextMenu(ContextMenuEvent event)
    {
        listViewContextMenu.show(entityList, event.getScreenX(), event.getScreenY());
    }
    public void HandleAddEntity()
    {
        Entity entity = EntityManager.GetInstance().AddEntity();
        entityList.getItems().add(entity);
    }
}
