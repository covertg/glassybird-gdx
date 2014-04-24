package com.breezewaydevelopment.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

	public static Texture texture, splashTexture; // Our spritesheet
	public static TextureRegion splash, logo, bg, grass, skullUp, skullDown,
			bar, bird, birdDown, birdUp, playButtonUp, playButtonDown;
	public static Animation birdAnimation;
	public static Sound dead, flap, coin;
	public static BitmapFont font, shadow;
	public static Preferences prefs;

	public static void load() {
		splashTexture = new Texture(Gdx.files.internal("data/splash.png"));
		splashTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		splash = new TextureRegion(splashTexture, 0, 0, 512, 114);

		texture = new Texture(Gdx.files.internal("data/texture.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest); // Minification and magnification

		playButtonUp = new TextureRegion(texture, 0, 83, 29, 16);
		playButtonDown = new TextureRegion(texture, 29, 83, 29, 16);
		playButtonUp.flip(false, true);
		playButtonDown.flip(false, true);

		logo = new TextureRegion(texture, 0, 55, 135, 24);

		bg = new TextureRegion(texture, 0, 0, 136, 43); // x, y, width, height
		bg.flip(false, true); // libGDX assumes a y-up coord system (usage flip(boolean x, boolean y))
		grass = new TextureRegion(texture, 0, 43, 143, 11);
		grass.flip(false, true);

		skullUp = new TextureRegion(texture, 192, 0, 24, 14);
		skullDown = new TextureRegion(skullUp);
		skullDown.flip(false, true); // Flip y from skullUp
		bar = new TextureRegion(texture, 136, 16, 22, 3);
		bar.flip(false, true);

		birdDown = new TextureRegion(texture, 136, 0, 17, 12);
		birdDown.flip(false, true);
		bird = new TextureRegion(texture, 153, 0, 17, 12);
		bird.flip(false, true);
		birdUp = new TextureRegion(texture, 170, 0, 17, 12);
		birdUp.flip(false, true);
		birdAnimation = new Animation(0.06f, new TextureRegion[] { birdDown, bird, birdUp }); //.06 sec flapping anim
		birdAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG); // Back and forth

		dead = Gdx.audio.newSound(Gdx.files.internal("data/dead.wav"));
		flap = Gdx.audio.newSound(Gdx.files.internal("data/flap.wav"));
		coin = Gdx.audio.newSound(Gdx.files.internal("data/coin.wav"));

		font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
		font.setScale(.25f, -.25f);
		shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
		shadow.setScale(.25f, -.25f);

		prefs = Gdx.app.getPreferences("glassybird");
		if (!prefs.contains("highschore")) {
			setHighScore(0);
		}
	}

	public static void dispose() {
		texture.dispose();
		splashTexture.dispose();

		dead.dispose();
		flap.dispose();
		coin.dispose();

		font.dispose();
		shadow.dispose();
	}

	public static void setHighScore(int score) {
		prefs.putInteger("highscore", score);
		prefs.flush();
	}

	public static int getHighScore() {
		return prefs.getInteger("highscore");
	}

}