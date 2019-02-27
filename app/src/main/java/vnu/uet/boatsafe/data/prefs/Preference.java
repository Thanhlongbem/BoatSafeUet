package vnu.uet.boatsafe.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import vnu.uet.boatsafe.di.ApplicationContext;

/**
 * Created by nvt2896 on 18/05/2017.
 */

public class Preference implements PreferenceHelper{

    private SharedPreferences sharedPreferencesAcc;
    private SharedPreferences sharedPreferencesSetting;
    private String PREFS_ACCOUNT = "PREFS_ACCOUNT";
    private String PREFS_SETTING = "PREFS_SETTING";
    private static Preference instance;

    private String EXPIRE_APP = "EXPIRE_APP";

    public static Preference buildInstance(Context context){

        if (instance == null) {
            instance = new Preference(context);
        }
        return instance;
    }


    @Inject
    public Preference(@ApplicationContext Context context) {
        sharedPreferencesAcc = context.getSharedPreferences(PREFS_ACCOUNT, Context.MODE_PRIVATE);
        sharedPreferencesSetting = context.getSharedPreferences(PREFS_SETTING, Context.MODE_PRIVATE);
    }

    @Override
    public void setExpire(boolean expire) {
        sharedPreferencesSetting.edit().putBoolean(EXPIRE_APP,expire).apply();
    }

    @Override
    public boolean getExpire() {
        return sharedPreferencesSetting.getBoolean(EXPIRE_APP,false);
    }
}
