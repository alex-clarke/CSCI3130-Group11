package com.csci3130_group11.csci3130_group11;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by sr on 2017-06-29.
 */

public class BroadcastAlarm extends BroadcastReceiver {

    /**
     * Broadcast receiver. Context where it is activated and intent (destination)
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {

        /**
         * Retrieves boolean~
         */
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        /**
         * Since our app is based on a RealTime DataBase, Notifications will be sent as soon as data
         * is received or out of range. Instead of broadcasting warning notifications, notifications
         * can be sent automatically as soon as the data is received. However, WARNING_MSG boolean
         * must be true. If notification is sent, it sets the boolean to false and an alarm will set it
         * to true every 1 hr. This avoids constant notifications if data is out of range.
         */
        if (!prefs.getBoolean("WARNING_MSG", false)){
            prefs.edit().putBoolean("WARNING_MSG", true).commit();
        }
    }

}
