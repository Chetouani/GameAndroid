package be.chetouani.Management;

import be.chetouani.Scene.BaseScene;
import be.chetouani.Scene.FirstScene;

/**
 * Created by abdel on 20/05/15.
 */
public class SceneManager  {

    private static final SceneManager INSTANCE = new SceneManager();

    public enum SceneType {SCENE_LANCEMENT}
    private SceneType typeSceneActuel;
    private BaseScene sceneActuel;

    private BaseScene lancementScene;


    private SceneManager() {}

    public static SceneManager getInstance() {
        return INSTANCE;
    }

    public void setScene(SceneType sceneType) {
        switch (sceneType) {
            case SCENE_LANCEMENT:
                setScene(creeFistScene());
                break;
        }
    }

    public BaseScene creeFistScene() {
        lancementScene = new FirstScene();
        return lancementScene;
    }

    private void setScene(BaseScene scene) {
        ResourceManager.getInstance().gameActivity.getEngine().setScene(scene);
        sceneActuel = scene;
        typeSceneActuel = scene.getSceneType();
    }
}
