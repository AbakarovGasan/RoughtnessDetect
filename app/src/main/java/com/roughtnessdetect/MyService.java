package com.roughtnessdetect;

import android.annotation.SuppressLint;
import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import com.roughtnessdetect.R;


class RequestLocation implements Runnable{
    static boolean isProviderEnabled = true;
    @Override
    public void run() {
        if (!Main.locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            if (isProviderEnabled) MainActivity.dialog_3.show();
            isProviderEnabled = false;
        }
        else{
            isProviderEnabled = true;
        }
    }
};


class thread implements Runnable {
    static public RequestLocation requestLocation = new RequestLocation();
    static long dumklink = 0; //число, показывающее, сколько сделано подсчетов
    static boolean lint = false;
    static float max;
    static float min;
    static float avr;
    static float diff;
    static boolean up = false;
    static boolean down = false;

    static void setext(int ji){
        if (  Main.j[ji].z < 6.3f || Main.j[ji].z > 13.3f ){
            Main.anindex += 1;
            Main.anomalies[Main.anindex] = ji;
            Main.j[ji].isanomalie = true;
        }
    };

    static void setext(){
        if (thread.track) {
            thread.track = false;
            if (Main.layout == 0) {
                Main.btnthread.setText(R.string.start);
            }
        }
    }

    static int sleep = 100;
    static int count = 21600000;
    static getstring GPS;
    static boolean w;
    static int lasttoast = -1; //число, показывающее последнее обновление GPS
    @SuppressLint("StaticFieldLeak")
    static Activity activity;
    static Runnable updatetext = new Runnable() {
        @SuppressLint("SetTextI18n")
        @Override
        public void run() {
            Main.textedit.setText(Integer.toString(thread.sleep));
        }
    };
    protected static boolean track = false;
    static int getvalue( String f, int j ){
        if (f.equals("")) return j;
        else return Integer.parseInt(f);
    }
    static float getvalue( String f, float j ){
        if (f.equals("")) return j;
        else return Float.parseFloat(f);
    }
    public static void reset(){
        thread.sleep = getvalue(Main.textedit.getText().toString(), 100);

        Main.activity.runOnUiThread(updatetext);
        Main.j = new logobj[thread.count];
        Main.anomalies = new int[thread.count];
        thread.lint = true;
        Main.ji = -1;
        Main.anindex = -1;
        thread.dumklink = 0;
        thread.max=0;
        thread.min=0;
        thread.avr=0;
    }
    public static void set(boolean h){
        if (h){
            reset();
        }
        thread.track = h;
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void run() {
        reset();
        while (thread.lint) {
            if (Main.locationListener.lat == Main.locationListener.lon && Main.locationListener.lat == 0) {
                Main.logs = new Main.time();
                while (thread.lint && Main.locationListener.lat == Main.locationListener.lon && Main.locationListener.lat == 0) {
                    Main.activity.runOnUiThread(Main.updateUI);
                    Main.activity.runOnUiThread(requestLocation);
                    try {
                        Thread.sleep(thread.sleep);
                    } catch (Exception ignored) {
                    }
                    setext();
                }
                Main.activity.runOnUiThread(requestLocation);
            }
            Main.activity.runOnUiThread(Main.updateUI);
            Main.activity.runOnUiThread(requestLocation);
            if (thread.track) {
                Main.ji += 1;
                thread.dumklink += 1;

                if (Main.ji == thread.count) {
                    Main.ji = 0;
                    lasttoast=-1;
                }
           /*/     if (Main.enable_gravity) {
                    Main.j[(int) Main.ji] = new logobj(
                            Main.locationListener.lat,
                            Main.locationListener.lon,
                            Main.gravity.x,
                            Main.gravity.y,
                            Main.gravity.z,
                            new Main.time(),
                            Main.locationListener.isnew);
                }
                else*/{
                    Main.j[(int) Main.ji] = new logobj(Main.locationListener.lat,
                            Main.locationListener.lon,
                            Main.accelerometer.x,
                            Main.accelerometer.y,
                            Main.accelerometer.z,
                            new Main.time(),
                            Main.locationListener.isnew);
                }
                setext(Main.ji); //проверка на аномалии
                //обновление GPS меток
                if (Main.locationListener.isnew){
                    Main.locationListener.isnew = false;
                    if (lasttoast!=-1){
                        if (Main.ji > (lasttoast+1)) {
                            int div = Main.ji - lasttoast;
                            int i = 1;
                            Log.d("hello", "here an error");
                            double lat = (Main.j[(int) Main.ji].lat - Main.j[(int) lasttoast].lat) / div;
                            double lon = (Main.j[(int) Main.ji].lon - Main.j[(int) lasttoast].lon) / div;
                            lasttoast += 1;
                            while (lasttoast < Main.ji - 1) {
                                Log.d( "hello" , Integer.toString( lasttoast ));
                                Main.j[(int) lasttoast].lat += lat * i;
                                Main.j[(int) lasttoast].lon += lat * i;
                                lasttoast += 1;
                                i+=1;
                            }
                        }
                    }
                    else {
                        lasttoast = Main.ji;
                    }
                }

            };
            try {
                Thread.sleep(thread.sleep);
            } catch (Exception ignored) {
            }
        }
        Main.textmain.setText("");
        thread.dumklink = 0;
    }
}


public class MyService extends Service {

    public static Intent intent;

    NotificationManager mNotifyManager;
    NotificationCompat.Builder mBuilder;
    NotificationChannel notificationChannel;
    String NOTIFICATION_CHANNEL_ID = "1";

    public MyService() { }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint({"WrongConstant", "MissingPermission"})
    @Override
    public void onCreate()
    {
        super.onCreate();
        intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        Log.d("RUNNER : ", "OnCreate... \n");

        Bitmap IconLg = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_foreground);

        mNotifyManager = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(this, "com.example.myapplication");
        mBuilder.setContentTitle("");
        mBuilder.setContentText("Запущен с " + Main.time());
        mBuilder.setTicker(" ");
        mBuilder.setSmallIcon(R.drawable.ic_launcher_foreground);
        mBuilder.setLargeIcon(IconLg);
        mBuilder.setPriority(Notification.PRIORITY_HIGH);
        mBuilder.setVibrate(new long[] {1000});
        mBuilder.setVisibility(Notification.VISIBILITY_PUBLIC);
        mBuilder.setOngoing(true);
        mBuilder.setAutoCancel(true);
        mBuilder.setContentIntent( PendingIntent.getActivity(this, 0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT) );

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                    "Notifications", NotificationManager.IMPORTANCE_HIGH);
            // Configure the notification channel.
            notificationChannel.setDescription("");
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
            //      mNotifyManager.notify(1, mBuilder.build());
        }

        try {
            Main.locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Main.locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, Main.locationListener);
        }
        catch(Exception e){
            Main.textmain.setText(R.string.error_request);
        };
        Main.j = new logobj[thread.count];
        Main.anomalies = new int[thread.count];
        Main.sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Main.sensorManager.registerListener(Main.gravity, Main.sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY), SensorManager.SENSOR_DELAY_NORMAL);
        Main.sensorManager.registerListener(Main.accelerometer, Main.sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER_UNCALIBRATED), SensorManager.SENSOR_DELAY_NORMAL);
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
