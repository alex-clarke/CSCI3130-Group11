package com.csci3130_group11.csci3130_group11;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by sr on 2017-06-29.
 */

public class BroadcastAlarm extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {


        NotificationGenerator.NotificationMessage("GREENHOUSE UPDATE",
                NotificationGenerator.UpdateInformation(context), context, DisplayCurrentData.class);

    }

}
