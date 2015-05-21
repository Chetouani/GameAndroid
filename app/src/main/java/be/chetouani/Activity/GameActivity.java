package be.chetouani.Activity;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.engine.options.resolutionpolicy.FixedResolutionPolicy;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.engine.options.resolutionpolicy.RelativeResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import be.chetouani.Management.ResourceManager;
import be.chetouani.Management.SceneManager;

/**
 * Created by abdel on 20/05/15.
 */

public class GameActivity  extends SimpleBaseGameActivity{

    public static final int LONGUEUR_ZONE_AFFICHAGE = 800;
    public static final int HAUTEUR_ZONE_AFFICHAGE = 480;

    private Camera zoneAffichage;
    private ResourceManager resourceManager;
    private SceneManager sceneManager;

    @Override
    public EngineOptions onCreateEngineOptions() {
        zoneAffichage = new Camera(0,0,LONGUEUR_ZONE_AFFICHAGE,HAUTEUR_ZONE_AFFICHAGE);
        EngineOptions optionMoteur = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED,
                new FillResolutionPolicy(), zoneAffichage);
        optionMoteur.getAudioOptions().setNeedsMusic(true).setNeedsSound(true);
        return optionMoteur;
    }


    @Override
    protected void onCreateResources() {
        resourceManager = ResourceManager.getInstance();
        resourceManager.prepare(this);
        resourceManager.chargerResourcesLancement();

        sceneManager = SceneManager.getInstance();
    }

    @Override
    protected Scene onCreateScene() {
        mEngine.registerUpdateHandler(new TimerHandler(2f, new ITimerCallback() {
            @Override
            public void onTimePassed(TimerHandler pTimerHandler) {
                mEngine.unregisterUpdateHandler(pTimerHandler);
                resourceManager.chargerResourcesMenu();
                sceneManager.setScene(SceneManager.SceneType.SCENE_MENU);
                resourceManager.dechargerResourcesLancement();
            }
        }));
        sceneManager.setScene(SceneManager.SceneType.SCENE_LANCEMENT);
        return sceneManager.creeFistScene();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.exit(0);
    }
}
