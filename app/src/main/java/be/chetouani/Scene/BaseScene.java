package be.chetouani.Scene;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import be.chetouani.Activity.GameActivity;
import be.chetouani.Management.ResourceManager;
import be.chetouani.Management.SceneManager;

/**
 * Created by abdel on 20/05/15.
 */
public abstract class BaseScene extends Scene {

    protected final int LONGUEUR_SCENE = GameActivity.LONGUEUR_ZONE_AFFICHAGE;
    protected final int HAUTEUR_SCENE = GameActivity.HAUTEUR_ZONE_AFFICHAGE;

    protected GameActivity gameActivity;
    protected Engine moteur;
    protected Camera zoneAffichage;
    protected VertexBufferObjectManager vertexBufferObjectManager;
    protected ResourceManager resourceManager;
    protected SceneManager sceneManager;

    public BaseScene() {
        resourceManager = ResourceManager.getInstance();
        gameActivity = resourceManager.gameActivity;
        vertexBufferObjectManager = gameActivity.getVertexBufferObjectManager();
        moteur = gameActivity.getEngine();
        zoneAffichage = moteur.getCamera();
        sceneManager = SceneManager.getInstance();
        createScene();
    }

    public abstract void createScene();
    public abstract void onBackKeyPressed();
    public abstract SceneManager.SceneType getSceneType();
    public abstract void disposeScene();
}
