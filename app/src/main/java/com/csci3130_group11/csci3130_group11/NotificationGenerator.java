package com.csci3130_group11.csci3130_group11;
import java.util.Random;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Class to generate Notifications.
 * Created by sr on 2017-06-22.
 */

public class NotificationGenerator extends AppCompatActivity{
    /**
     * ID for notification and limits for random number generation
     */
    private static int uniqueID;
    private static final int maxNum = 99999;
    private static final int minNum=10000;

    protected  String message;

    protected Light l;
    protected Humidity h;
    protected Temperature t;



    /**
     * Generates random ID (int) and updates static variable UniqueID
     */
    private static void randomIdGenerator(){
        Random random = new Random();
        uniqueID = random.nextInt(maxNum) + minNum;
    }

    /**
     * Creates a notification message
     * @param msgTitle
     * @param msg
     * @param CurrentActivity (Background activity)
     * @param problem (activity that will open after clikcing on the notification)
     */
    protected  static void NotificationMessage(String msgTitle, String msg, Context CurrentActivity, Class problem){

        randomIdGenerator();

        // creates the notification based on the activity that called the method (should be Application)
        NotificationCompat.Builder notification;
        notification = new NotificationCompat.Builder(CurrentActivity);
        //This assures that the notifaction only appears once
        notification.setAutoCancel(true);
        // symbol added
        notification.setSmallIcon(R.drawable.flowering);
        // ticker is an ID i think,,,
        notification.setTicker("Data Update");
        // Allows to set multiple lines of texts instead of one and wraps around it
        notification.setStyle(new NotificationCompat.BigTextStyle());
        // shows when the notiication was sent
        notification.setWhen(System.currentTimeMillis());
        // sets title and message
        notification.setContentTitle(msgTitle);
        notification.setContentText(msg);

        // creates an intent from Application context and will send the user to problem page
        // once user clicks on it
        Intent intent = new Intent (CurrentActivity, problem);
        // creates the pending intent
        PendingIntent pendingIntent = PendingIntent.getActivity(CurrentActivity, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        // links it
        notification.setContentIntent(pendingIntent);

        //Updates
        NotificationManager nm = (NotificationManager) CurrentActivity.getSystemService(NOTIFICATION_SERVICE);

        // send
        nm.notify(uniqueID, notification.build());

    }

    /**
     *Creates a Message based on current information
     * @return
     */
    protected  static String UpdateInformation(Context context){

        String message= "" ;

        Light l;
        Humidity h;
        Temperature t;



        Util.retrieveSavedRanges(context);
        t = Util.getTemperature();
        h= Util.getHumidity();
        l=Util.getLight();



        if (!t.checkMeasurement(t.getCurrent())){
            message = "TEMPERATURE IS OUT OF RANGE: " + t.getCurrent()+ " C\n";
        } else{
            message = "Temperature: "+t.getCurrent()+ " C\n";
        }
        if (!l.checkMeasurement(l.getCurrent())){
            message = message + "LIGHT IS OUT OF RANGE: " + l.getCurrent() + " %\n";
        } else{
            message = message + "Light: " + l.getCurrent() + " %\n";
        }
        if (!h.checkMeasurement(h.getCurrent())){
           message = message + "HUMIDITY IS OUT OF RANGE: " + h.getCurrent() + " %\n";
        } else{
            message = message + "Humidity: " + l.getCurrent() + " %\n";
        }

        return message;
    }

}
