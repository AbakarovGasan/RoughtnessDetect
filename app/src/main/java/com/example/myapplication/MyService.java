package com.example.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.IBinder;

import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import static androidx.core.app.ActivityCompat.requestPermissions;

public class MyService extends Service
{
    context f1;
    context f2 = new context() {
        @Override
        public Context run() {
            return getApplicationContext();
        }
    };
    NotificationManager mNotifyManager;
    NotificationCompat.Builder mBuilder;
    NotificationChannel notificationChannel;
    String NOTIFICATION_CHANNEL_ID = "1";

    public MyService() { }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("WrongConstant")
    @Override
    public void onCreate()
    {
        super.onCreate();

        Log.d("RUNNER : ", "OnCreate... \n");

        Bitmap IconLg = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_foreground);

        mNotifyManager = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(this, null);
        mBuilder.setContentTitle("")
                .setContentText("running since "+Main.time())
                .setTicker("Always running...")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setLargeIcon(IconLg)
                .setPriority(Notification.PRIORITY_HIGH)
                .setVibrate(new long[] {1000})
                .setVisibility(Notification.VISIBILITY_PUBLIC)
                .setOngoing(true)
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_HIGH);

            // Configure the notification channel.
            notificationChannel.setDescription("Channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{1000});
            notificationChannel.enableVibration(true);
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            mNotifyManager.createNotificationChannel(notificationChannel);
            mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);
            startForeground(1, mBuilder.build());

        }
        else
        {
            mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);
       //     mNotifyManager.notify(1, mBuilder.build());
        }

        try {
            Main.locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Main.locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, Main.locationListener);
        }
        catch(Exception e){
            Main.text1.setText(R.string.error_request);
        };
        Main.j = new logobj[thread.count];
        Main.anomalies = new int[thread.count];

        Main.sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Main.sensorManager.registerListener(Main.gravity, Main.sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY), SensorManager.SENSOR_DELAY_NORMAL);
        Main.sensorManager.registerListener(Main.gyroscope, Main.sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE), SensorManager.SENSOR_DELAY_NORMAL);
        Main.sensorManager.registerListener(Main.accelerometr, Main.sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        new Thread(new thread()).start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.d("RUNNER : ", "\nPERFORMING....");

        return START_STICKY;
    };

    @Override
    public void onDestroy()
    {
        thread.lint=false;
        Log.d("RUNNER : ", "\nDestroyed....");
        Log.d("RUNNER : ", "\nWill be created again automaticcaly....");
        super.onDestroy();
    }


    @Override
    public IBinder onBind(Intent intent)
    {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("NOT_YET_IMPLEMENTED");
    }
}