package com.example.mauriciogodinez.tourguideapp;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

import java.util.ArrayList;

/**
 * Created by mauriciogodinez on 01/05/17.
 */

public class DetectedActivitiesIntentService extends IntentService{
    protected static final String TAG = "detection_is";

    public DetectedActivitiesIntentService(){
        super(TAG);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
    @Override
    protected void onHandleIntent(Intent intent){
        ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);
        Intent localIntent = new Intent(Constants.BROADCAST_ACTION);

        ArrayList <DetectedActivity> detectedActivities = (ArrayList) result.getProbableActivities();

        Log.i(TAG, "activities detected");
        localIntent.putExtra(Constants.ACTIVITY_EXTRA, detectedActivities);
        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
    }
}
