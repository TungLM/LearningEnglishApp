package tung.vn.learningenglishbypicture.common;

import android.media.MediaPlayer;

import java.io.IOException;

import tung.vn.learningenglishbypicture.ApplicationApp;
import tung.vn.learningenglishbypicture.R;

/**
 * Created by seven64 on 6/11/2016.
 */
public class Utility {
    private static MediaPlayer mediaPlayer = null;
    private static MediaPlayer mediaPlayerBG = null;

    public static void playBG(int idx) {
        try {
            if (mediaPlayerBG != null) {
                mediaPlayerBG.release();
                mediaPlayerBG = null;
            }
            mediaPlayerBG = MediaPlayer.create(ApplicationApp.getAppContext(), R.raw.alphabet_vn);
            mediaPlayerBG.setLooping(true);
            mediaPlayerBG.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stopBG() {
        try {
            if (mediaPlayerBG != null) {
                mediaPlayerBG.release();
                mediaPlayerBG.stop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void playBeep(int idx) {
        try {
            if (mediaPlayer != null) {
                mediaPlayer.release();
                mediaPlayer = null;
            }
            mediaPlayer = MediaPlayer.create(ApplicationApp.getAppContext(), idx);
            mediaPlayer.setLooping(false);
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void speechName(int i) {
        try {
            MediaPlayer mediaPlayerSpeech = new MediaPlayer();
            mediaPlayerSpeech.setDataSource("http://www.oxforddictionaries.com/media/english/uk_pron/a/app/apple/apple__gb_1.mp3");
            mediaPlayerSpeech.prepare();
            mediaPlayerSpeech.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
