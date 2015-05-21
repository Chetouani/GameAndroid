package be.chetouani.Scene;

import android.os.Handler;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.AutoParallaxBackground;
import org.andengine.entity.scene.background.ParallaxBackground;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.entity.util.FPSLogger;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.HorizontalAlign;

import be.chetouani.Activity.GameActivity;
import be.chetouani.Management.SceneManager;

/**
 * Created by abdel on 20/05/15.
 */
public class JeuScene extends BaseScene implements IOnSceneTouchListener {

    private Text scoreText;
    private int score = 25;


    @Override
    public void createScene() {
        moteur.registerUpdateHandler(new FPSLogger());
        setOnSceneTouchListener(this);

        // Affiche le fond d'ecran
        AutoParallaxBackground autoParallaxBackground = new AutoParallaxBackground(0, 0, 0, 0);
        autoParallaxBackground.attachParallaxEntity(new ParallaxBackground.ParallaxEntity(0.0f, new Sprite(0, -1020, resourceManager.mParallaxLayerBack, vertexBufferObjectManager)));
        setBackground(autoParallaxBackground);

        // Zone de score
        HUD hud = new HUD();
        scoreText = new Text(GameActivity.LONGUEUR_ZONE_AFFICHAGE/2,10,resourceManager.policeScore,"0123456789",
                    new TextOptions(HorizontalAlign.CENTER),vertexBufferObjectManager);
        //scoreText.setText("25");
        scoreText.setVisible(true);
        hud.attachChild(scoreText);
        zoneAffichage.setHUD(hud);


        registerUpdateHandler(new IUpdateHandler() {
            @Override
            public void onUpdate(float pSecondsElapsed) {
                bougerMap();
            }

            @Override
            public void reset() {

            }
        });

    }

    @Override
    public void onBackKeyPressed() {
        resourceManager.chargerResourcesMenu();
        sceneManager.setScene(SceneManager.SceneType.SCENE_MENU);
        resourceManager.dechargerResourcesJeu();
    }

    @Override
    public SceneManager.SceneType getSceneType() {
        return SceneManager.SceneType.SCENE_JEU;
    }

    @Override
    public void disposeScene() {

    }


    @Override
    public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {

        return false;
    }

    private int i = 0;
    private boolean enAvant = false;

    private void bougerMap() {
        enAvantOuArriere();
        if (estFinMap() && enAvant) {
            i -= 5;
        } else {
            i += 5;
        }
        AutoParallaxBackground autoParallaxBackground = new AutoParallaxBackground(0, 0, 0, 0);
        autoParallaxBackground.attachParallaxEntity(new ParallaxBackground.ParallaxEntity(0.0f, new Sprite(i, -1020, resourceManager.mParallaxLayerBack, vertexBufferObjectManager)));
        setBackground(autoParallaxBackground);
        scoreText.setText(""+i);
    }

    private void enAvantOuArriere() {
        if (i == -3000 + GameActivity.LONGUEUR_ZONE_AFFICHAGE)
            enAvant = false;
        if (i == 0)
            enAvant = true;
    }

    private boolean estFinMap() {
       return i > -3000 + GameActivity.LONGUEUR_ZONE_AFFICHAGE;
    }




}
