package be.chetouani.Management;

import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;

import be.chetouani.Activity.GameActivity;

/**
 * Created by abdel on 20/05/15.
 */
public class ResourceManager {

    private static final ResourceManager INSTANCE = new ResourceManager();
    public GameActivity gameActivity;
    private BitmapTextureAtlas textureLancement;
    public ITextureRegion regionTextureLancement;

    private ResourceManager() {
    }

    public static ResourceManager getInstance() {
        return INSTANCE;
    }

    public void prepare(GameActivity activity) {
        INSTANCE.gameActivity = activity;
    }

    public void chargerResourcesLancement() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("img/");
        textureLancement = new BitmapTextureAtlas(gameActivity.getTextureManager(), 2048, 2048, TextureOptions.BILINEAR);
        regionTextureLancement = BitmapTextureAtlasTextureRegionFactory.createFromAsset(textureLancement, gameActivity, "logo-Chetouani.jpg", 0, 0);
        textureLancement.load();
    }

    public void dechargerResourcesLancement() {
        textureLancement.unload();
        regionTextureLancement = null;
    }

}
