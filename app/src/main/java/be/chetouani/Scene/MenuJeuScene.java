package be.chetouani.Scene;

import android.opengl.GLES20;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.TextMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ColorMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;
import org.andengine.util.color.Color;

import be.chetouani.Management.SceneManager;

/**
 * Created by abdel on 20/05/15.
 */
public class MenuJeuScene extends BaseScene implements MenuScene.IOnMenuItemClickListener {

    protected MenuScene menu;
    public final int ID_PLAY = 0;
    public final int ID_QUIT = 1;

    @Override
    public void createScene() {
        Sprite menuSprite = new Sprite(0, 0, resourceManager.regionTextureMenu, vertexBufferObjectManager) {
            @Override
            protected void preDraw(GLState pGLState, Camera pCamera) {
                super.preDraw(pGLState, pCamera);
                pGLState.enableDither();
            }
        };
        menuSprite.setPosition(0,0);
        attachChild(menuSprite);

        menu = creeMenu();
        this.setChildScene(menu, false, true, true);
    }

    @Override
    public void onBackKeyPressed() {

    }

    @Override
    public SceneManager.SceneType getSceneType() {
        return SceneManager.SceneType.SCENE_MENU;
    }

    @Override
    public void disposeScene() {

    }

    protected MenuScene creeMenu() {
        final MenuScene menuScene = new MenuScene(zoneAffichage);

        final IMenuItem playMenuItem = new ColorMenuItemDecorator(
                new TextMenuItem(ID_PLAY, resourceManager.policeMenu, "Play", vertexBufferObjectManager),
                new Color(0.0f, 0.2f, 0.4f),new Color(1,1,1));
        final IMenuItem quitMenuItem = new ColorMenuItemDecorator(
                new TextMenuItem(ID_QUIT, resourceManager.policeMenu, "Quit", vertexBufferObjectManager),
                new Color(0.0f, 0.2f, 0.4f),new Color(1,1,1));

        playMenuItem.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        quitMenuItem.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);

        menuScene.addMenuItem(playMenuItem);
        menuScene.addMenuItem(quitMenuItem);

        menuScene.buildAnimations();
        menuScene.setBackgroundEnabled(false);
        menuScene.setOnMenuItemClickListener(this);
        return menuScene;
    }

    @Override
    public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX, float pMenuItemLocalY) {
        switch (pMenuItem.getID()) {
            case ID_PLAY :
                resourceManager.chargerResourcesJeu();
                sceneManager.setScene(SceneManager.SceneType.SCENE_JEU);
                resourceManager.dechargerResourcesMenu();
                break;
            case ID_QUIT : gameActivity.finish() ; break;
            default: return false;
        }
        return true;
    }
}
