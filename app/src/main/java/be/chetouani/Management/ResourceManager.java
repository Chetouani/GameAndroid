package be.chetouani.Management;

import android.graphics.Color;
import android.graphics.Typeface;

import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.bitmap.AssetBitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;

import be.chetouani.Activity.GameActivity;

/**
 * Created by abdel on 20/05/15.
 */
public class ResourceManager {

    private static final ResourceManager INSTANCE = new ResourceManager();
    public GameActivity gameActivity;

    // Lancement
    private BitmapTextureAtlas textureLancement;
    public ITextureRegion regionTextureLancement;
    // Menu
    private BitmapTextureAtlas textureMenu;
    public ITextureRegion regionTextureMenu;
    // Jeu
    private BitmapTextureAtlas textureJeu;
    public ITextureRegion regionTextureJeu;
    // Paralax
    private BitmapTextureAtlas mAutoParallaxBackgroundTexture;
    public ITextureRegion mParallaxLayerBack;
    public ITextureRegion mParallaxLayerFront;


    public Font policeMenu;
    public Font policeScore;

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
        textureLancement = new BitmapTextureAtlas(gameActivity.getTextureManager(), GameActivity.LONGUEUR_ZONE_AFFICHAGE, GameActivity.HAUTEUR_ZONE_AFFICHAGE, TextureOptions.BILINEAR);
        regionTextureLancement = BitmapTextureAtlasTextureRegionFactory.createFromAsset(textureLancement, gameActivity, "lancement-jeu.jpg", 0, 0);
        textureLancement.load();
    }

    public void dechargerResourcesLancement() {
        textureLancement.unload();
        regionTextureLancement = null;
    }

    public void chargerResourcesMenu() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("img/");
        textureMenu = new BitmapTextureAtlas(gameActivity.getTextureManager(), GameActivity.LONGUEUR_ZONE_AFFICHAGE, GameActivity.HAUTEUR_ZONE_AFFICHAGE, TextureOptions.BILINEAR);
        regionTextureMenu = BitmapTextureAtlasTextureRegionFactory.createFromAsset(textureMenu, gameActivity, "ecran-menu.png", 0, 0);
        textureMenu.load();

        FontFactory.setAssetBasePath("police/");
        ITexture fontTexture = new BitmapTextureAtlas(gameActivity.getTextureManager(), GameActivity.LONGUEUR_ZONE_AFFICHAGE, GameActivity.HAUTEUR_ZONE_AFFICHAGE, TextureOptions.BILINEAR);
        policeMenu = FontFactory.createStrokeFromAsset(gameActivity.getFontManager(), fontTexture, gameActivity.getAssets(), "CalliGravity.ttf", 150, true, Color.YELLOW, 2, Color.DKGRAY);
        policeMenu.load();

    }

    public void dechargerResourcesMenu() {
        textureMenu.unload();
        regionTextureMenu = null;
        policeMenu.unload();
    }

    public void chargerResourcesJeu() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("img/");
        mAutoParallaxBackgroundTexture = new BitmapTextureAtlas(gameActivity.getTextureManager(), 3000, 1500);
        mParallaxLayerBack = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mAutoParallaxBackgroundTexture, gameActivity, "fond-ecran-jeu.jpg", 0, 0);
        mAutoParallaxBackgroundTexture.load();
        ITexture fontTexture = new BitmapTextureAtlas(gameActivity.getTextureManager(), GameActivity.LONGUEUR_ZONE_AFFICHAGE, GameActivity.HAUTEUR_ZONE_AFFICHAGE, TextureOptions.BILINEAR);
        policeScore = FontFactory.createStrokeFromAsset(gameActivity.getFontManager(), fontTexture, gameActivity.getAssets(), "BEBAS.ttf", 50, true, Color.GREEN, 2, Color.DKGRAY);
        policeScore.load();
    }

    public void dechargerResourcesJeu() {
        textureJeu.unload();
        regionTextureJeu = null;
        policeScore.unload();
    }

}
