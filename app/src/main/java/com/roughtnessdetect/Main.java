package com.roughtnessdetect;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.ViewGroupOverlay;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.OverlayManager;
import org.osmdroid.views.overlay.Polyline;
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay;

import static java.lang.Math.sqrt;

class loclisten implements LocationListener {
    public double lat;
    public double lon;
    public boolean isnew = false;

    @Override
    public void onLocationChanged(@NonNull Location location) {
        this.lat = location.getLatitude();
        this.lon = location.getLongitude();
        this.isnew = true;
    }

    @Override
    public void onProviderDisabled (String provider) {
        this.lat = 0;
        this.lon = 0;
    }

    @Override
    public void onProviderEnabled (String provider){
        this.lat = 0;
        this.lon = 0;
    }

    @Override
    public void onStatusChanged(String j, int a, Bundle i){

    }
}


class rotate{
    public float [][] k;
    public rotate(float [] va){
        float x = va[0];
        float y = va[1];
        float z = va[2];
        float j = (float) sqrt(x*x+y*y+z*z);
        float h = (float) sqrt(j*j - y*y);
        float sy = y/-j;
        float cy = h/j;
        float sx = x/h;
        float cx = z/h;
        float xcs = sx*sx + cx*cx;
        float ycs = - cy*cy - sy*sy;
        this.k = new float[][] {{cx/xcs, 0, -sx/xcs}, {-(sx*sy)/(xcs*ycs), -cy/ycs, -(cx*sy)/(xcs*ycs)}, {-(sx*cy)/(xcs*ycs), sy/ycs, -(cy*cx)/(ycs*xcs)}};
    }
    public float [] run(float [] o){
        int i=0;
        int u=0;
        float[] e = new float[3];
        while (i<3){
            e[i] = k[i][0]*o[0]+k[i][1]*o[1]+k[i][2]*o[2];
            i+=1;
        }
        return e;
    }
}

class acclisten implements SensorEventListener {
    public boolean callibrated = false;
    rotate r;

    void reset(){
        this.callibrated = false;
    }
    void callibrate(){
        this.callibrated = true;
        this.r = new rotate( values );
    }

    public float max = 0;
    public float min = 1000000;
    public float acc = 0;
    public float[] values = {0f, 0f, 0f};
    public float x;
    public float y;
    public float z;
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float [] j;
        this.values = sensorEvent.values;
        if (this.callibrated) {
            j = r.run(sensorEvent.values);
        }
        else{
            j = sensorEvent.values;
        }
        this.x = j[0];
        this.y = j[1];
        this.z = j[2];
        float n = this.x * this.x + this.y * this.y + this.z * this.z;
        if (n > max) this.max = n;
        if (n < min) this.min = n;
        this.acc = this.max - this.min;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}

class logobj {
    public double lon;
    public double lat;
    public float x;
    public float y;
    public float z;/*
    public float x1;
    public float y1;
    public float z1;*/
    public Main.time t;
    public boolean toast;
    public boolean isanomalie;
    logobj(double lon, double lat, float x, float y, float z, Main.time t, boolean toast) {
        this.lon = lon;
        this.lat = lat;
        this.x = x;
        this.y = y;
        this.z = z;
        this.t = t;
        this.toast = toast;
        this.isanomalie = false;
    }

    public String toString() {
        return "<a>" + this.t.toString() + " </a><a href=https://maps.google.com/?q=" + Main.dec.format(this.lon) + ","
                + Main.dec.format(this.lat) + ">(" + Main.dec.format(this.lon) + ", " + Main.dec.format(this.lat) +
                ")</a><a><br>(" + Main.dec.format(this.x)
                + ", " + Main.dec.format(this.y) + ", " + Main.dec.format(this.z) + ")<br>";
    }
}

@SuppressLint("UseSwitchCompatOrMaterialCode")
public class Main {
    public static loclisten locationListener = new loclisten();
    public static acclisten accelerometer = new acclisten();
    public static acclisten gravity = new acclisten();
    @SuppressLint("StaticFieldLeak")
    public static Activity activity;
    public static int ji = -1;
    public static time logs;
    public static logobj[] j;
    public static LocationManager locationManager;
    public static int[] anomalies;
    public static SensorManager sensorManager;
    public static int anindex = -1;
    static DecimalFormat dec = new DecimalFormat("0.0000000");
 //   public static boolean gravity_exists;
    public static getpackagename getpackagename;
    public static getstring getstring;
    public static getcontext getapplicationcontext;
    public static setcontext setcontextview;
    public static findview findviewbyid;
    public static int layout = 0;
    public static SimpleDateFormat mdformat;
    public static boolean buildsdk_O;
    public static OnBackPressedCallback callback = new OnBackPressedCallback(true ) {
        @Override
        public void handleOnBackPressed() {
            if (Main.layout == 0) return;
            Main.set_layout();
        }
    };
    static public Calendar calendar;

    public static class time{
        Date self;
        public time(){
            calendar = Calendar.getInstance();
            this.self = calendar.getTime();
        };

        @Override
        public String toString(){
            return mdformat.format( this.self );
        };
    }
    static String time(){
        return new Main.time().toString();
    };
 //   public static boolean enable_gravity;
    @SuppressLint("StaticFieldLeak")
    public static Button btnthread;
    @SuppressLint("StaticFieldLeak")
    public static Button btncallibrate;
    @SuppressLint("StaticFieldLeak")
    public static Button btnshowlogs;
    @SuppressLint("StaticFieldLeak")
    public static Button btnclose;
    @SuppressLint("StaticFieldLeak")
    public static Switch switch1;
    @SuppressLint("StaticFieldLeak")
    public static Switch switch2;
    @SuppressLint("StaticFieldLeak")
    public static TextView textmain;
    @SuppressLint("StaticFieldLeak")
    public static TextView textedit;
    @SuppressLint("StaticFieldLeak")
    public static Button btntext2;
    @SuppressLint("StaticFieldLeak")
    public static Button btntext1;
    @SuppressLint("StaticFieldLeak")
    public static Button btntext3;

    public static MapView mMapView;
    public static MapController mMapController;

    public static Runnable updateUI = new Runnable() {
        @SuppressLint("SetTextI18n")
        @Override
        public void run() {
            String out = "";
            if (Main.locationListener.lat == Main.locationListener.lon && Main.locationListener.lat == 0) {
                out = getstring.run(R.string.wait_loading);
            } else {
                out = String.format(getstring.run(R.string.gps_log),
                        Main.locationListener.lat,
                        Main.locationListener.lon);
            }
   /*/         if (enable_gravity){
                out += String.format(getstring.run(R.string.gravity),
                        Main.dec.format(Main.gravity.x),
                        Main.dec.format(Main.gravity.y),
                        Main.dec.format(Main.gravity.z));
            }
            else */ {
                out += String.format(getstring.run(R.string.accelerometer),
                        Main.dec.format(Main.accelerometer.x),
                        Main.dec.format(Main.accelerometer.y),
                        Main.dec.format(Main.accelerometer.z));
            }
            Main.textmain.setText(out);
        }
    };


    private static final View.OnClickListener callibrate = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            accelerometer.callibrate();
            gravity.callibrate();
        }
    };
    public static View.OnClickListener trackstate = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            accelerometer.callibrate();
            gravity.callibrate();
            Button btn = (Button) view;
            if (!thread.track) {
                if (Main.locationListener.lat == 0 && Main.locationListener.lon == 0){
                    message.show(Main.getstring.run(R.string.GPS_still_loading));
                }
                else {
                    btn.setText(R.string.stop);
                    thread.set(true);
                }
            }
            else {
                btn.setText(R.string.start);
                thread.set(false);
            }
        }
    };
    public static View.OnClickListener close = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Main.stopService();
            Main.kill();
        }
    };
    public static View.OnClickListener showlogsmenu = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Main.set_menu();
        }
    };
    public static View.OnClickListener switchto1 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (switch1.isChecked()){
                textedit.setText("100");
                textedit.setEnabled(false);
            }
            else{
                textedit.setEnabled(true);
            }
        }
    };/*
    public static View.OnClickListener switchto2 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Main.enable_gravity = !Main.enable_gravity;
        }
    };*/
    public static Button btnprev;
    public static Button btnnext;
    public static Button btnrefresh;
    public static TextView textlogs;

    public static void kill() {
        Main.activity.finishAffinity();
        int pid = android.os.Process.myPid();
        android.os.Process.killProcess(pid);
    }

    public static void set_layout(){
        layout  = 0;
        setcontextview.run(R.layout.layout);
        btnshowlogs = (Button) findviewbyid.run(R.id.btnshowlogs);
        btnthread = (Button) findviewbyid.run(R.id.btnthread);
        btncallibrate = (Button) findviewbyid.run(R.id.btncallibrate);
        btnclose = (Button) findviewbyid.run(R.id.btnclose);
        switch1 = (Switch) findviewbyid.run(R.id.switch1);
   //     switch2 = (Switch) findviewbyid.run(R.id.switch2);
        textmain = (TextView) findviewbyid.run(R.id.textmain);
        textedit = (TextView) findviewbyid.run(R.id.textedit);

        btnthread.setText(thread.track?R.string.stop:R.string.start);

        btncallibrate.setOnClickListener(callibrate);
        btnthread.setOnClickListener(trackstate);
        btnclose.setOnClickListener(close);
        btnshowlogs.setOnClickListener(showlogsmenu);
        switch1.setOnClickListener(switchto1);
/*
        if (Main.gravity_exists) {
            switch2.setOnClickListener(switchto2);
            switch2.setChecked(!enable_gravity);
        }
        else{
            switch2.setVisibility(View.GONE);
        }*/
    }

    public static void set_map(){
        layout = 3;
        setcontextview.run(R.layout.map);
    };

    public static void set_logs(){
        layout = 1;
        setcontextview.run(R.layout.logs);
        btnprev = (Button) findviewbyid.run(R.id.btnprev);
        btnnext = (Button) findviewbyid.run(R.id.btnnext);
        btnrefresh = (Button) findviewbyid.run(R.id.btnrefresh);
        textlogs   = (TextView) findviewbyid.run(R.id.textlogs);
        textlogs.setMovementMethod(LinkMovementMethod.getInstance());
    }
    public static void set_menu(){
        layout = 2;
        setcontextview.run(R.layout.menu);
        //    btn2    = (Button)   find.run(R.id.button9);
        //    btn2.setOnClickListener(start2);
        btntext1 = (Button)   findviewbyid.run(R.id.btntext1);
        btntext2 = (Button)   findviewbyid.run(R.id.btntext2);
        btntext3 = (Button)   findviewbyid.run(R.id.btntext3);
        btntext1.setOnClickListener(btext1);
        btntext2.setOnClickListener(btext2);
        btntext3.setOnClickListener(btext3);
    }

    static void settext(String j){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textlogs.setText(Html.fromHtml(j, Html.FROM_HTML_MODE_COMPACT));
        } else {
            textlogs.setText(Html.fromHtml(j));
        }
    };

    @SuppressLint("NewApi")
    public static void startService() {
        if (Main.buildsdk_O) {
            Main.activity.startForegroundService(new Intent(Main.activity, MyService.class));
        }
        else
        {
            Main.activity.startService(new Intent(Main.activity, MyService.class));
        }
    };
    static public void stopService() {
        Main.activity.stopService(new Intent(Main.activity, MyService.class));
    };

    static public View.OnClickListener btext1 = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            set_logs();
            cast.create();
        }
    };
    static public View.OnClickListener btext2 = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            set_logs();
            cast2.create();
        }
    };
    static public View.OnClickListener btext3 = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            set_map();
            cast3.create();
        }
    };
}

class cast {
    static int page = 0;
    static int maxpages = 0;
    static int count;
    static int dot = 0;
    static int left = 0;
    static int logs = 13;
    static String cont;
    static boolean lint;
    static View.OnClickListener prev = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            cast.text(-1);
        }
    };
    static View.OnClickListener next = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            cast.text(1);
        }
    };
    static View.OnClickListener fresh = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            cast.set();
        }
    };

    static void create() {
        Main.btnprev.setOnClickListener(prev);
        Main.btnnext.setOnClickListener(next);
        Main.btnrefresh.setOnClickListener(fresh);
        cast.set();
    }

    static void set() {
        page = 0;
        count = thread.count;
        dot = Main.ji;
        left = (thread.count >= thread.dumklink) ? (int) thread.dumklink : (int) thread.count;
        maxpages = left / logs;
        if ((left % logs) == 0) {
            maxpages -= 1;
        }
        lint = thread.lint;
        text();
    }

    static void text() {
        text(0);
    }

    @SuppressLint("SetTextI18n")
    static void text(int add) {
        int ji = 0;
        int i = 0;
        int l = 0;
        try {
            if (!lint) return;
            if (Main.locationListener.lon == Main.locationListener.lat && Main.locationListener.lat == 0) {
                Main.textlogs.setText(Main.getstring.run(R.string.GPS) + " " + Main.logs.toString());
                return;
            }
            if (!thread.track) {
                Main.textlogs.setText("");
                return;
            }
            page += add;
            if (page < 0) page = 0;
            else if (page > maxpages) page = maxpages;
            ji = dot;
            ///    message.show(Integer.toString(maxpages));
            i = logs;
            l = left - page * logs;
            if (l < i) i = l;
            ji -= page * logs;
            cont = "";
            while (i > 0) {
                while (ji >= 0 && i > 0) {
                    cont += Main.j[ji].toString();
                    i -= 1;
                    ji -= 1;
                }
                ji = count - 1;
            }
            Main.settext(cont);
        } catch (Exception e) {
            message.show(e.toString() + Integer.toString(ji), 2147);
        }
    }
}

class cast3{
    public static void addMarker(double lat, double lon){
        Marker startMarker = new Marker(Main.mMapView);
        startMarker.setPosition( new GeoPoint(lat, lon) );
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        Main.mMapView.getOverlays().add(startMarker);
    }
    public static Runnable setmapview = new Runnable() {
        @Override
        public void run() {
            try {
                if (Main.locationListener.lat == 0 && Main.locationListener.lon == 0) {
                    message.show(Main.getstring.run(R.string.GPS_still_loading));
                }
                //    new Thread(setmapview).start();

                Configuration.getInstance().setUserAgentValue(Main.getpackagename.run());
                Main.mMapView = (MapView) Main.findviewbyid.run(R.id.map);
                Main.mMapController = (MapController) Main.mMapView.getController();
                Main.mMapController.setZoom(10);
                Main.mMapController.setCenter(new GeoPoint(42.01, 42.00));

                RotationGestureOverlay mRotationGestureOverlay = new RotationGestureOverlay(Main.activity, Main.mMapView);
                mRotationGestureOverlay.setEnabled(true);
                Main.mMapView.setMultiTouchControls(true);
                Main.mMapView.getOverlays().add(mRotationGestureOverlay);

                Polyline path = new Polyline();
                path.setColor(Color.parseColor("#00FF00"));

                int i = 0;
                while (i<=Main.ji){
                    path.addPoint(new GeoPoint(Main.j[i].lat, Main.j[i].lon));
                    i++;
                }
                Main.mMapView.getOverlays().add(path);

                while (true) {
                    if (Main.ji > i) {
                        i = Main.ji;
                        path.addPoint(new GeoPoint(Main.j[i].lat, Main.j[i].lon));
                        if (Main.j[i].isanomalie){
                            addMarker(Main.j[i].lat, Main.j[i].lon);
                        }
                        try {
                            Thread.sleep(thread.sleep);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Main.mMapView.getOverlays().add(path);
                    }
                }
            }
            catch (java.lang.NullPointerException ignore){

            };
        }
    };

    public static void create(){
        new Thread(setmapview).start();
    }
}

class cast2{
    static int page = 0;
    static int maxpages = 0;
    static int count;
    static int dot = 0;
    static int left = 0;
    static int logs = 13;
    static String cont;
    static boolean lint;
    static void set() {
        page = 0;
        count = thread.count;
        dot = Main.anindex;
        left = (thread.count >= Main.anindex+1 )?(int) Main.anindex+1: (int) thread.count;
        maxpages = left / logs;
        if ((left % logs)==0){
            maxpages -=1;
        }
        lint = thread.lint;
        text();
    }
    static View.OnClickListener prev = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            cast.text(-1);
        }
    };
    static View.OnClickListener next = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            cast.text(1);
        }
    };
    static View.OnClickListener fresh = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            cast.set();
        }
    };
    static void create(){
        Main.btnprev.setOnClickListener(prev);
        Main.btnnext.setOnClickListener(next);
        Main.btnrefresh.setOnClickListener(fresh);
        cast.set();
    }
    static void text(){
        text(0);
    }
    @SuppressLint("SetTextI18n")
    static void text(int add) {
        int ji = 0;
        int i = 0;
        int l = 0;
        try {
            if (!lint) return;
            if (Main.locationListener.lon == Main.locationListener.lat && Main.locationListener.lat == 0) {
                Main.textlogs.setText(Main.getstring.run(R.string.GPS) + " " + Main.logs.toString());
                return;
            }
            if (dot==-1) return;
            if (!thread.track) {
                Main.textlogs.setText("");
                return;
            }
            page += add;
            if (page < 0) page = 0;
            else if (page > maxpages) page = maxpages;
            ji = dot;

            //       message.show(Integer.toString(Main.anindex));

            i = logs;
            l = left - page * logs;
            if (l < i) i = l;
            ji -= page * logs;
            cont = "";
            while (i > 0) {
                while (ji >= 0 && i > 0) {
                    cont += Main.j[ Main.anomalies[ ji  ] ].toString();
                    i -= 1;
                    ji -= 1;
                }
                ji = count - 1;
            }
            Main.settext(cont);
        } catch (Exception e) {
            message.show(e.toString() + Integer.toString(ji), 2147);
        }
    }
}

class message {
    static Toast toast;
    static Handler handler;
    static private String tp = "";
    static private int stay = 500;
    static Runnable run = new Runnable() {
        @Override
        public void run() {
            toast.cancel();
        }
    };
    static public void show(){
        Main.activity.runOnUiThread(show);
    };
    static public void show( String j ){
        tp = j;
        Main.activity.runOnUiThread(show);
        tp = "";
    };
    static public void show( String j, int h){
        stay = h;
        tp = j;
        Main.activity.runOnUiThread(show);
        tp = "";
        stay = 500;
    };
    @SuppressLint("ShowToast")
    static public Runnable show = new Runnable() {
        @Override
        public void run() {
            toast = Toast.makeText(Main.getapplicationcontext.run(), tp, Toast.LENGTH_SHORT);
            toast.show();
            handler = new Handler();
            handler.postDelayed(run, stay);
        }
    };
}