package com.csci3130_group11.csci3130_group11;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Broascast class for Update Notifications. Alarm from settings activates it
 * Created by sr on 2017-07-21.
 */

public class BroadcastUpdate extends BroadcastReceiver {

    /**
     * Broadcast receiver. Context where it is activated and intent (destination)
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationGenerator.NotificationMessage("GREENHOUSE UPDATE",
                NotificationGenerator.UpdateInformation(context), context, DisplayCurrentData.class);

    }

}
