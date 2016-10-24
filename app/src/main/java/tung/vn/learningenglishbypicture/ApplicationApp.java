package tung.vn.learningenglishbypicture;

import android.app.Application;
import android.content.Context;

/**
 * Created by seven64 on 6/11/2016.
 */
public class ApplicationApp extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationApp.context = getApplicationContext();
    }
    public static Context getAppContext() {
        return ApplicationApp.context;
    }
}
