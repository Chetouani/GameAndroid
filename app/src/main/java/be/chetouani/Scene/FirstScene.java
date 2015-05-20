package be.chetouani.Scene;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;

import be.chetouani.Management.SceneManager;

/**
 * Created by abdel on 20/05/15.
 */
public class FirstScene extends BaseScene{

    @Override
    public void createScene() {
        Sprite lancement = new Sprite(0, 0, resourceManager.regionTextureLancement, vertexBufferObjectManager) {
            @Override
            protected void preDraw(GLState pGLState, Camera pCamera) {
                super.preDraw(pGLState, pCamera);
                pGLState.enableDither();
            }
        };
        lancement.setPosition(0,0);
        attachChild(lancement);
    }

    @Override
    public void onBackKeyPressed() {
        gameActivity.finish();
    }

    @Override
    public SceneManager.SceneType getSceneType() {
        return SceneManager.SceneType.SCENE_LANCEMENT;
    }

    @Override
    public void disposeScene() {

    }
}
