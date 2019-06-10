package com.test.beatoapp.storage.pref;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.test.beatoapp.ApplicationClass;

public class PrefManager extends BasePref {

    private static final String PREF_USERNAME = "PREF_USERNAME";


    protected PrefManager(Context context, SharedPreferences sp) {
        super(context, sp);
    }


    private static PrefManager instance;

    public static synchronized PrefManager getInstance() {
        if (instance == null) {
            Context context = ApplicationClass.getInstance();
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
            instance = new PrefManager(context, sp);
        }

        return instance;
    }

    public String getPrefUsername() {
        return getString(PREF_USERNAME, "");
    }

    public void setPrefUsername(String value) {
        setString(PREF_USERNAME, value);
    }

}
