package be.chetouani.Management;

import be.chetouani.Scene.BaseScene;
import be.chetouani.Scene.FirstScene;
import be.chetouani.Scene.JeuScene;
import be.chetouani.Scene.MenuJeuScene;

/**
 * Created by abdel on 20/05/15.
 */
public class SceneManager  {

    private static final SceneManager INSTANCE = new SceneManager();

    public enum SceneType {SCENE_LANCEMENT,SCENE_MENU, SCENE_JEU}
    private SceneType typeSceneActuel;
    private BaseScene sceneActuel;

    private BaseScene lancementScene;
    private BaseScene menuScene;
    private BaseScene jeuScene;

    private SceneManager() {}

    public static SceneManager getInstance() {
        return INSTANCE;
    }

    public void setScene(SceneType sceneType) {
        switch (sceneType) {
            case SCENE_LANCEMENT:
                setScene(creeFistScene());
                break;
            case SCENE_MENU:
                setScene(creeMenuScene());
                break;
            case SCENE_JEU:
                setScene(creeJeuScene());
                break;
        }
    }

    public BaseScene creeFistScene() {
        lancementScene = new FirstScene();
        return lancementScene;
    }

    public BaseScene creeMenuScene() {
        menuScene = new MenuJeuScene();
        return menuScene;
    }

    public BaseScene creeJeuScene() {
        jeuScene = new JeuScene();
        return jeuScene;
    }

    private void setScene(BaseScene scene) {
        ResourceManager.getInstance().gameActivity.getEngine().setScene(scene);
        sceneActuel = scene;
        typeSceneActuel = scene.getSceneType();
    }
}
