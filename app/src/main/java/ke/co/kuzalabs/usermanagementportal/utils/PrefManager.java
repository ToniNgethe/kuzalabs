package ke.co.kuzalabs.usermanagementportal.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import ke.co.kuzalabs.usermanagementportal.App;

/**
 * Created by ngethe on 25/10/2018
 * Day  : Thursday
 * Time : 21:19
 */
public class PrefManager {

    private static Gson gson = new Gson();

    private static SharedPreferences getPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(App.getInstance()
                .getApplicationContext());
    }

    public static int getInt(String preferenceKey, int preferenceDefaultValue) {
        return getPreferences().getInt(preferenceKey, preferenceDefaultValue);
    }

    public static void putInt(String preferenceKey, int preferenceValue) {
        getPreferences().edit().putInt(preferenceKey, preferenceValue).apply();
    }

    public static boolean getBoolean(String preferenceKey, boolean preferenceDefaultValue) {
        return getPreferences().getBoolean(preferenceKey, preferenceDefaultValue);
    }

    public static void putBoolean(String preferenceKey, boolean preferenceValue) {
        getPreferences().edit().putBoolean(preferenceKey, preferenceValue).apply();
    }


    public static String getString(String preferenceKey, String preferenceDefaultValue) {
        return getPreferences().getString(preferenceKey, preferenceDefaultValue);
    }

    public static void putString(String preferenceKey, String preferenceValue) {
        getPreferences().edit().putString(preferenceKey, preferenceValue).apply();
    }

    public static void clearPrefs() {
        getPreferences().edit().clear().apply();
    }

}
