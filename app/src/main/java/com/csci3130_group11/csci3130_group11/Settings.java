package com.csci3130_group11.csci3130_group11;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.os.Bundle;
import android.preference.PreferenceManager;

import java.util.Date;


/**
 * Settings class for notifications
 */
public class Settings extends PreferenceActivity {

    /*time unit*/
    private final int milliInOneHour = 3600000;

    /*current time*/
    private Date time = new Date(System.currentTimeMillis());

    /*sharedPreferences variables*/
    CheckBoxPreference warning;
    ListPreference list;

    /*shared Preferences access*/
    SharedPreferences savedData;
    SharedPreferences prefs;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        /*initializes shared preferences*/
        savedData = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        /*boolean for warning message control*/
        prefs.getBoolean("WARNING_MSG", false);
        /*initializes UI variables*/
        warning = (CheckBoxPreference) getPreferenceManager().findPreference("WarningNotification");
        list = (ListPreference) findPreference("update_time");
        list.setValueIndex(0);
        /**
         * On change method for warning message. As soon as it is changed it commits changesd
         * Alarm is initialized here and sends it to broadcasting(Background process)
         */
        warning.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if (!savedData.getBoolean("WarningNotification", false)) {
                    prefs.edit().putBoolean("WARNING_MSG", true).commit();
                    /**
                     * Android OS can only distinguished processes based on the parameters used to
                     * initialize them. Hence same parameters must be used to cancelled them.
                     * If two broadcasting activities are based on the same parameters but behave
                     * differently a different broadcast class must be use to change the parameters and
                     * allow proper distictions for cancellation at user's input.
                     */
                    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    Intent alert= new Intent(getApplicationContext(), BroadcastAlarm.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, alert, PendingIntent.FLAG_UPDATE_CURRENT);
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time.getTime(),
                            milliInOneHour,
                            pendingIntent);
                } else {
                    prefs.edit().putBoolean("WARNING_MSG", false).commit();
                    /**
                     * Alarm cancellation based on activation parameters
                     */
                    AlarmManager alarmManager= (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    Intent alert= new Intent(getApplicationContext(), BroadcastAlarm.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, alert, PendingIntent.FLAG_UPDATE_CURRENT);
                    alarmManager.cancel(pendingIntent);
                }
                return true;
            }
        });

        /**
         * On change method for update message. As soon as it is change it inizializes it.
         * Alarm is initialized here and sends it to broadcasting(Background process)
         */
        list.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                String timeText = savedData.getString("update_time", "None");
                int x = list.findIndexOfValue(timeText);
                if (!timeText.equals("None")){
                    int timeValue = Integer.parseInt(timeText);
                    timeValue= timeValue * milliInOneHour;
                    /**
                     * See previous onchange method for explanation
                     */
                    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    Intent alert= new Intent(getApplicationContext(), BroadcastUpdate.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, alert, PendingIntent.FLAG_UPDATE_CURRENT);
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time.getTime(),
                            timeValue,
                            pendingIntent);
                }
                else{
                    /**
                     * Cancellation.
                     * See previous onchange method for explanation
                     */
                    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    Intent alert= new Intent(getApplicationContext(), BroadcastUpdate.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, alert, PendingIntent.FLAG_UPDATE_CURRENT);
                    alarmManager.cancel(pendingIntent);
                }
                return true;
            }
        });


    }
}

