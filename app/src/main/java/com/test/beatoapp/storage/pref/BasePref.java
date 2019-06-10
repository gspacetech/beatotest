package com.test.beatoapp.storage.pref;

import android.content.Context;
import android.content.SharedPreferences;

public class BasePref {

    protected Context context;
    protected SharedPreferences preferences;

    protected BasePref(Context context, SharedPreferences sp) {
        this.context = context;
        this.preferences = sp;
    }

    /**
     * Store some string in SharedPreferences using a key value and the data
     *
     * @param key
     * @param value
     */

    protected void setString(String key, String value) {
        preferences.edit().putString(key, value).commit();
    }

    /**
     * Get string value from SharedPreferences using key value
     *
     * @param key
     * @param def
     * @return a string
     */

    protected String getString(String key, String def) {
        return preferences.getString(key, def);
    }

    /**
     * Store some boolean value in SharedPreferences using a key value and the
     * data
     *
     * @param key
     * @param value
     */

    protected void setBoolean(String key, boolean value) {
        preferences.edit().putBoolean(key, value).commit();
    }

    /**
     * Get boolean value from SharedPreferences using key value
     *
     * @param key
     * @param def
     * @return a boolean value
     */

    protected boolean getBoolean(String key, boolean def) {
        return preferences.getBoolean(key, def);
    }

    /**
     * Store some integer value in SharedPreferences using a key value and the
     * data
     *
     * @param key
     * @param value
     */

    protected void setInt(String key, int value) {
        preferences.edit().putString(key, Integer.toString(value)).commit();
    }

    /**
     * Get integer value from SharedPreferences using key value
     *
     * @param key
     * @param def
     * @return a integer value
     */

    protected int getInt(String key, int def) {
        return Integer.parseInt(preferences.getString(key, Integer.toString(def)));
    }

    /**
     * Store some Long value in SharedPreferences using a key value and the data
     *
     * @param key
     * @param value
     */

    protected void setLong(String key, long value) {
        preferences.edit().putString(key, Long.toString(value)).commit();
    }

    /**
     * Get Long value from SharedPreferences using key value
     *
     * @param key
     * @param def
     * @return a Long value
     */

    protected long getLong(String key, long def) {
        return Long.parseLong(preferences.getString(key, Long.toString(def)));
    }

    /**
     * Store some Double value in SharedPreferences using a key value and the
     * data
     *
     * @param key
     * @param value
     */

    protected void setDouble(String key, double value) {
        preferences.edit().putString(key, Double.toString(value)).commit();
    }

    /**
     * Get Double value from SharedPreferences using key value
     *
     * @param key
     * @param def
     * @return a Double value
     */

    protected double getDouble(String key, double def) {
        return Double.parseDouble(preferences.getString(key, Double.toString(def)));
    }

    public void clearPreferences() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }

    /*public void remove(String key) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.apply();
    }*/



}
