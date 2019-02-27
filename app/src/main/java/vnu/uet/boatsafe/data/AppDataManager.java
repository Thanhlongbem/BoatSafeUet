

package vnu.uet.boatsafe.data;


import android.content.Context;
import javax.inject.Inject;
import javax.inject.Singleton;
import vnu.uet.boatsafe.data.prefs.Preference;
import vnu.uet.boatsafe.di.ApplicationContext;

@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = AppDataManager.class.getSimpleName();

    private final Context mContext;
    private final Preference mPreferencesHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          Preference preferencesHelper) {
        mContext = context;
        mPreferencesHelper = preferencesHelper;
    }

    @Override
    public void setExpire(boolean expire) {
        mPreferencesHelper.setExpire(expire);
    }

    @Override
    public boolean getExpire() {
        return mPreferencesHelper.getExpire();
    }
}
