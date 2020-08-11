package com.example.myapplication;

import com.softmoore.android.graphlib.*;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
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
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.app.PendingIntent.getActivity;

/*
abstract class TextViewListener implements TextWatcher {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        //here is your code
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // TODO Auto-generated method stub
        }
        @Override
        public void afterTextChanged(Editable s) {
        // TODO Auto-generated method stub
        }
}


/*
class keylisten implements TextWatcher {
    public EditText text;
    public String buff1;
    public String buff2;
    public String buff3;
    public keylisten(EditText text4) {
        this.text=text4;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        buff1 = s.subSequence(start, start+count).toString();
        buff2 = s.subSequence(start+count, start+count+after-1).toString();
    }
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        buff3 = (s.subSequence(start, start+count).toString());
        int i = 0;
        int len = buff3.length();
        char t;
        int u=0;
        while (i<len){
            t = buff3.charAt(i);
            i+=1;
            while (t<'0'||t>'9'){
                u=i-1;
                if (i==len){
                    i+=1;
                    break;
                };
                t = buff3.charAt(i);
                i+=1;
            }
            if (u!=0){
                buff3 = buff3.substring(0, u)+buff3.substring(i-1);
                len-=(u-i-1);
            }
        }
        text.setText(buff3);
    }
    @Override
    public void afterTextChanged(Editable s) {

    }
};*/
class cast{
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
        dot = Main.ji;
        left = (thread.count >= thread.dumklink )?(int) thread.dumklink: (int) thread.count;
        maxpages = left / logs;
        if ((left % logs)==0){
            maxpages -=1;
        }
        lint = thread.lint;
        text();
    }

    static void text(){
        text(0);
    }

    @SuppressLint("SetTextI18n")
    static void text(int add){
        int ji = 0;
        int i = 0;
        int l = 0;
        try {
            if (!lint) return;
            if (Main.locationListener.lon == Main.locationListener.lat && Main.locationListener.lat == 0) {
                Main.text3.setText( Main.getstring.run(R.string.GPS)+ " "+ Main.logs.toString());
                return;
            }
            if (!thread.track){
                Main.text3.setText("");
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
        }
        catch (Exception e){
            message.show(e.toString()+Integer.toString(ji), 2147);
        }
/*  page += add;
        if (page<0){
            page = 0;
        }
        if (page > maxpages){
            page = maxpages;
        }
        int i = logs;
        int ji = dot;
        ji -= page*logs;
        ji -= 1;
        if (ji<0){
            ji = count - ji;
        }
        while (i!=0) {
            while (ji >= 0 || i > 0){
                cont += Main.j[ji].toString();
                ji-=1;
                i-=1;
            }
            j= count - 1;
        }
        try {
            Main.settext(cont);
        }
        catch (Exception e){
            Main.text3.setText(e.toString());
        }*/
    }

    public static void set_graph() {
        Graph graph = new Graph.Builder().build();
        Main.graph.setGraph(graph);
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
                Main.text3.setText(Main.getstring.run(R.string.GPS) + " " + Main.logs.toString());
                return;
            }
            if (dot==-1) return;
            if (!thread.track) {
                Main.text3.setText("");
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
class loc implements LocationListener {
    public double lat;
    public double lon;
    public boolean toast = false;
    @Override
    public void onLocationChanged(@NonNull Location location) {
        this.lat = location.getLatitude();
        this.lon = location.getLongitude();
        this.toast = true;
    }

    @Override
    public void onProviderDisabled (String provider){
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
class loc1 implements SensorEventListener {
/* float[] rotationMatrix = new float[9];
    float[] orientation = new float[3];
    */
    public float x;
    public float y;
    public float z;
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        this.x = sensorEvent.values[0];
        this.y = sensorEvent.values[1];
        this.z = sensorEvent.values[2];/*
        public void onSensorChanged(SensorEvent sensorEvent) {

            // Convert the result Vector to a Rotation Matrix.
            SensorManager.getRotationMatrixFromVector(rotationMatrix,
                    sensorEvent.values);
            // Extract the orientation from the Rotation Matrix.
            SensorManager.getOrientation(rotationMatrix, orientation);
            Main.x = orientation[0]; // Yaw
            Main.y = orientation[1]; // Pitch
            Main.z = orientation[2]; // Roll*/
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}

class loc2 implements SensorEventListener {
    /* float[] rotationMatrix = new float[9];
        float[] orientation = new float[3];
        */
    public float max = 0;
    public float min = 1000000;
    public float acc = 0;
    public float x;
    public float y;
    public float z;/*
    public float x1;
    public float x2;
    public float x3;
    public float val[] = {0.0f, 0.0f, 0.0f};*/
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        this.x = sensorEvent.values[0];
        this.y = sensorEvent.values[1];
        this.z = sensorEvent.values[2];
        float n = this.x * this.x + this.y * this.y + this.z * this.z;
        if (n > max) this.max = n;
        if (n < min) this.min = n;
        this.acc = this.max - this.min;
     /*   this.val = sensorEvent.values;/*
        public void onSensorChanged(SensorEvent sensorEvent) {

            // Convert the result Vector to a Rotation Matrix.
            SensorManager.getRotationMatrixFromVector(rotationMatrix,
                    sensorEvent.values);
            // Extract the orientation from the Rotation Matrix.
            SensorManager.getOrientation(rotationMatrix, orientation);
            Main.x = orientation[0]; // Yaw
            Main.y = orientation[1]; // Pitch
            Main.z = orientation[2]; // Roll

/*    public void callibrate(){
        x1 = val[0];
        x2 = val[1];
        x3 = val[2] - 9.81f;
    }*/
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}

interface getstring{
    String run(int id);
}

class ext{
    float x;
    float y;
    float z;
    public ext(){
        this.x=0;
        this.y=0;
        this.z=0;
    }
    public ext(logobj u){
        this.x = u.x;
        this.y = u.y;
        this.z = u.z;
    };
}

class thread implements Runnable {
    public static Context packageContext;
    static DecimalFormat dec = new DecimalFormat("0.0000000");
    static long dumklink = 0; //число, показывающее, сколько сделано подсчетов
    static boolean lint = false;
    static float sensibility = 0.80f;
    static boolean ex1 = false;
    static float ex2 = 0;
    static ext ex3 = new ext(); //ext - вектор гравитации
    static ext ex4 = new ext();
    static int ex5 = 0;
    static int ex6 = 0;
    static boolean ex7 = false;

    static float vectorg( logobj j ){
        // квадрат длины вектора cилы гравитации
        return j.x1*j.x1 + j.y1*j.y1 + j.z1*j.z1;
    };

    static float cosv(ext a, ext b){
        //квадрат косинуса между векторами
        return (float) ((a.x*b.x+a.y*b.y+a.z*b.z)*(a.x*b.x+a.y*b.y+a.z*b.z)/((a.x*a.x+a.y*a.y+a.z*a.z)*(b.x*b.x+b.y*b.y+b.z*b.z)));
    }

    static void setext( int ji ){
        logobj u1 = Main.j[ ji ];
        float g = vectorg( u1 ); // длина вектора гироскопа
        boolean g1 = g>0.1;
        boolean g2 = g>0.8;
        if ( g1 & (!ex1) ){ // если g > 0.2 и флаг ex1 = false,  (по умолчанию false)
            ex1=true;       // то задаем флаг ex1 = true
            ex3=new ext( Main.j[ji] ); //здесь задаем начальный вектор
            //cилы гравитации, действующее на устройство
        }
        if (g2){
            ex4=new ext( Main.j[ji]);
            if (cosv(ex3, ex4)<sensibility){ //eсли квадрат косинуса угла между векторами ex3 и ex4 < sensibility
                Main.anindex +=1;
                Main.anomalies[Main.anindex] = ji;

          /*      activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        message.show("hello, i am anomalie");
                    }
                });*/

            }
        }
        if (!g1 & (ex1)){
            ex1=false;
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
            Main.text5.setText(Integer.toString(thread.sleep));
            Main.text4.setText(Float.toString(thread.sensibility));
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
        thread.sleep = getvalue(Main.text5.getText().toString(), 100);
        thread.sensibility = getvalue(Main.text4.getText().toString(), 0.80f);
        if (thread.sensibility>1){
            thread.sensibility = 0.80f;
        }
        activity.runOnUiThread(updatetext);
        Main.j = new logobj[thread.count];
        Main.anomalies = new int[thread.count];
        thread.lint = true;
        Main.ji = -1;
        Main.anindex = -1;
        thread.dumklink = 0;
    }
    public static void set(boolean h){
        if (h) {
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
                        activity.runOnUiThread(Main.updateUI);
                        try {
                            Thread.sleep(thread.sleep);
                        } catch (Exception ignored) {
                        }
                    }
                }
                activity.runOnUiThread(Main.updateUI);
                if (thread.track) {
                    Main.ji += 1;
                    thread.dumklink += 1;

                    if (Main.ji == thread.count) {
                        Main.ji = 0;
                        lasttoast=-1;
                    }

                    Main.j[(int) Main.ji] = new logobj(Main.locationListener.lat, Main.locationListener.lon, Main.accelerometr.x, Main.accelerometr.y,
                            Main.accelerometr.z, Main.gyroscope.x, Main.gyroscope.y, Main.gyroscope.z,  new Main.time(), Main.locationListener.toast);
                    setext(Main.ji); //проверка на аномалии

                    //обновление GPS меток
                    if (Main.locationListener.toast){
                        Main.locationListener.toast = false;
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
            Main.text1.setText("");
            thread.dumklink = 0;
    }
}
class start implements View.OnClickListener {
    Runnable setview;

    public start(Runnable ji) {
        this.setview = ji;
    }

    @Override
    public void onClick(View view) {
        setview.run();
    }
}
interface context {
    Context run();
};
interface setcontext {
    void run(int i);
};
interface findview {
    View run(int i);
};
class message {
    static context getContext;
    static Toast toast;
    static Handler handler;
    static Runnable run = new Runnable() {
        @Override
        public void run() {
            toast.cancel();
        }
    };
    static void show(String tp){
        show(tp, 500);
    }
    @SuppressLint("ShowToast")
    static void show(String tp, int stay) {
        toast = Toast.makeText(getContext.run(), tp, Toast.LENGTH_SHORT);
        toast.show();
        handler = new Handler();
        handler.postDelayed(run, stay);
    }
}
class logobj {
    public double lon;
    public double lat;
    public float x;
    public float y;
    public float z;
    public float x1;
    public float y1;
    public float z1;
    public Main.time t;
    public boolean toast;
    logobj(double lon, double lat, float x, float y, float z, float x1, float y1, float z1, Main.time t, boolean toast) {
        this.lon = lon;
        this.lat = lat;
        this.x = x;
        this.y = y;
        this.z = z;
        this.t = t;
        this.y1 = y1;
        this.z1 = z1;
        this.x1 = x1;
        this.toast = toast;
    }
    public String toString() {
        return "<a>" + this.t.toString() + " </a><a href=https://maps.google.com/?q=" + thread.dec.format(this.lon) + ","
                + thread.dec.format(this.lat) + ">(" + thread.dec.format(this.lon) + ", " + thread.dec.format(this.lat) +
                ")</a><a><br>(" + thread.dec.format(this.x)
                + ", " + thread.dec.format(this.y) + ", " + thread.dec.format(this.z) + ")<br>("+
                thread.dec.format(this.x1) + ", " + thread.dec.format(this.y1) + ", " + thread.dec.format(this.z1) + ")<br></a>";
    }
}
/*
class MyFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // This callback will only be called when MyFragment is at least Started.


        // The callback can be enabled or disabled here or in handleOnBackPressed()
    }
}
*/

public class Main extends  FragmentActivity {
    public static Main.time logs;
    public static FileOutputStream logtext;
    public static logobj[] j; //данные, получаемые в ходе исследования дороги
    public static int ji = -1; //последний элемент из j
    public static int[] anomalies;
    public static int anindex = -1;
    @SuppressLint("StaticFieldLeak")
    public static Button btn;
    @SuppressLint("StaticFieldLeak")
    public static Button btn2;
    @SuppressLint("StaticFieldLeak")
    public static Button btn3;
    @SuppressLint("StaticFieldLeak")
    public static Button btn4;
    @SuppressLint("StaticFieldLeak")
    public static Button btn5;
    @SuppressLint("StaticFieldLeak")
    public static Button btn6;
    @SuppressLint("StaticFieldLeak")
    public static TextView text1;
    @SuppressLint("StaticFieldLeak")
    public static TextView text3;
    @SuppressLint("StaticFieldLeak")
    public static EditText text4;
    @SuppressLint("StaticFieldLeak")
    public static EditText text5;
    @SuppressLint({"StaticFieldLeak", "UseSwitchCompatOrMaterialCode"})
    public static Switch switch1;
    @SuppressLint("StaticFieldLeak")
    public static Button btntext;
    @SuppressLint("StaticFieldLeak")
    public static Button btndiag;
/*  public static double longitude = 0;
    public static double latitude = 0;
    public static float x = 0;
    public static float y = 0;
    public static float z = 0;
    */
    public static int layout = 0;
    public static loc locationListener = new loc();
    public static loc2 accelerometr = new loc2();
    public static loc2 gravity = new loc2();
    public static loc1 gyroscope = new loc1();

    public static LocationManager locationManager;
    public static SensorManager sensorManager;
    public static context getcon;
    public static setcontext setcon;
    public static findview find;
    public static GraphView graph;

    public static getstring getstring; //get.run() возвращает строку
//
    public static float vector(loc1 j){
        return j.x*j.x+j.y*j.y+j.z*j.z;
    };

    public static float vector(loc2 j){
        return j.x*j.x+j.y*j.y+j.z*j.z;
    };

    public static Runnable updateUI = new Runnable() {
        @SuppressLint("SetTextI18n")
        @Override
        public void run() {
            if (Main.locationListener.lat == Main.locationListener.lon && Main.locationListener.lat == 0) {
                Main.text1.setText(String.format(getstring.run(R.string.wait_loading),
                        thread.dec.format(Main.gravity.x) ,
                        thread.dec.format(Main.gravity.y) ,
                        thread.dec.format(Main.gravity.z) ,
                        thread.dec.format(Main.gyroscope.x) ,
                        thread.dec.format(Main.gyroscope.y) ,
                        thread.dec.format(Main.gyroscope.z) ,
                        thread.dec.format(vector(Main.gyroscope))));

            } else     {
                Main.text1.setText(String.format(getstring.run(R.string.gps_log),
                        Main.locationListener.lat ,
                        Main.locationListener.lon ,
                        thread.dec.format(Main.gravity.x) ,
                        thread.dec.format(Main.gravity.y) ,
                        thread.dec.format(Main.gravity.z) ,
                        thread.dec.format(Main.gyroscope.x) ,
                        thread.dec.format(Main.gyroscope.y) ,
                        thread.dec.format(Main.gyroscope.z) ,
                        thread.dec.format(vector(Main.gyroscope))));
            }
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // If request is cancelled, the result arrays are empty.
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            set_layout();
            startService();
        } else {

        }
        return;


        // other 'case' lines to check for other
        // permissions this app might request
    };


    public static start start1 = new start(new Runnable() {
        @Override
        public void run() {
            set_menu();

        }
    });
    public static start start2 = new start(new Runnable() {
        @Override
        public void run() {
            set_layout();
        }
    });
    public static start next2 = new start(new Runnable() {
        @Override
        public void run() {
            cast2.text(1);
        }
    });
    public static start prev2 = new start(new Runnable() {
        @Override
        public void run() {
            cast2.text(-1);
        }
    });
    public static start fresh2 = new start(new Runnable() {
        @Override
        public void run() {
            cast2.set();
        }
    });
    public static start next = new start(new Runnable() {
        @Override
        public void run() {
            cast.text(1);
        }
    });
    public static start prev = new start(new Runnable() {
        @Override
        public void run() {
            cast.text(-1);
        }
    });
    public static start fresh = new start(new Runnable() {
        @Override
        public void run() {
            cast.set();
        }
    });/*
    public static start callibrate = new start (new Runnable(){
        @Override
        public void run() {
            accelerometr.callibrate();
        }
    });*/
    public static start switchto = new start(new Runnable() {
        @Override
        public void run() {
            if (switch1.isChecked()){
                text4.setText("0.8");
                text5.setText("100");
                text4.setEnabled(false);
                text5.setEnabled(false);
            }
            else{
                text4.setEnabled(true);
                text5.setEnabled(true);
            }
        }
    });

/*
    public static start start3 = new start( new Runnable(){
        @Override
        public void run() {
            set_logs();
            thread.lint=false;
        }
    } );
*/
    public static View.OnClickListener start4 = new View.OnClickListener() {
        @SuppressLint({"SetTextI18n", "SdCardPath"})
        @Override
        public void onClick(View view) {
            Button btn = (Button) view;
            if (!thread.track) {
                btn.setText(R.string.stop);
                thread.set(true);

            } else {
                btn.setText(R.string.start);
                thread.set(false);
            }
        }
    };

    public static View.OnClickListener bdiag = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            set_logs2();
        }
    };
    public static View.OnClickListener btext = new View.OnClickListener() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onClick(View view) {
            set_logs();
        }
    };
    public static View.OnClickListener close = new View.OnClickListener() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onClick(View view) {
            thread.activity.finishAffinity();
            stopService();
            int pid = android.os.Process.myPid();
            android.os.Process.killProcess(pid);
        }
    };

    public static start startres = start2;
/*
    public static View.OnKeyListener keylisten = new View.OnKeyListener() {
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            // If the event is a key-down event on the "enter" button
            message.show("hello");
            return false;
        }
    };
*/
    static void settext(String j){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            text3.setText(Html.fromHtml(j, Html.FROM_HTML_MODE_COMPACT));
        } else {
            text3.setText(Html.fromHtml(j));
        }
    };
    public static Calendar calendar;
    /*   static Date lasttime;
    *  */
    public static SimpleDateFormat mdformat;

    static class time{
        Date self;
        time(){
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
/*    static public String time(){
        return mdformat.format( calendar.getTime());
    };*/
    static void set_graph(){
        setcon.run(R.layout.graph);
        graph   = (GraphView)find.run(R.id.graph_view);
        cast.set_graph();
    };
    static void set_layout(){
        layout  = 0;
        setcon.run(R.layout.layout);
        btn     = (Button)   find.run(R.id.button);
        btn3    = (Button)   find.run(R.id.button3);
  //      btn6    = (Button)   find.run(R.id.button8);
  //      btn6.setOnClickListener(callibrate);
        text1   = (TextView) find.run(R.id.textView);
        text4   = (EditText) find.run(R.id.textView4);
        text5   = (EditText) find.run(R.id.textView5);
        switch1 = (Switch)   find.run(R.id.switch1);
        btn.setOnClickListener(start1);
        btn3.setOnClickListener(start4);
        btn3.setText(thread.track?R.string.stop:R.string.start);
        switch1.setOnClickListener(switchto);
    };
    static void set_logs(){
        layout = 1;
        setcon.run(R.layout.logs);
   //     btn2    = (Button)   find.run(R.id.button2);
        btn3    = (Button)   find.run(R.id.button4);
        btn4    = (Button)   find.run(R.id.button5);
        btn5    = (Button)   find.run(R.id.button6);
        text3   = (TextView) find.run(R.id.textView3);
   //     btn2.setOnClickListener(start2);
        btn3.setOnClickListener(prev);
        btn4.setOnClickListener(next);
        btn5.setOnClickListener(fresh);
/*/ text3.setMovementMethod(new ScrollingMovementMethod());*/
        text3.setMovementMethod(LinkMovementMethod.getInstance());
        cast.set();
    }
    static void set_logs2(){
        layout = 1;
        setcon.run(R.layout.logs);
        //     btn2    = (Button)   find.run(R.id.button2);
        btn3    = (Button)   find.run(R.id.button4);
        btn4    = (Button)   find.run(R.id.button5);
        btn5    = (Button)   find.run(R.id.button6);
        text3   = (TextView) find.run(R.id.textView3);
        //     btn2.setOnClickListener(start2);
        btn3.setOnClickListener(prev2);
        btn4.setOnClickListener(next2);
        btn5.setOnClickListener(fresh2);
        /*/ text3.setMovementMethod(new ScrollingMovementMethod());*/
        text3.setMovementMethod(LinkMovementMethod.getInstance());
        cast2.set();
    }
    static void set_menu(){
        layout = 2;
        setcon.run(R.layout.menu);
    //    btn2    = (Button)   find.run(R.id.button9);
    //    btn2.setOnClickListener(start2);
        btntext = (Button)   find.run(R.id.btntext);
        btndiag = (Button)   find.run(R.id.btntext2);
        btn2    = (Button)   find.run(R.id.btntext3);
        btntext.setOnClickListener(btext);
        btndiag.setOnClickListener(bdiag);
        btn2.setOnClickListener(close);
    };

    @SuppressLint({"SimpleDateFormat", "SetTextI18n"})
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Main.getstring = new getstring() {
            @Override
            public String run(int id) {
               return getResources().getString( id );
            }
        };
        super.onCreate(savedInstanceState);
        getcon = new context() {
            @Override
            public Context run() {
                return Main.this.getApplicationContext();
            }
        };
        setcon = new setcontext() {
            @Override
            public void run(int i) {
                setContentView(i);
            }
        };
        find = new findview() {
            @Override
            public View run(int i) {
                return findViewById(i);
            }
        };
        message.getContext = getcon;
        thread.activity = this;
        calendar = Calendar.getInstance();
        mdformat = new SimpleDateFormat("HH:mm:ss");

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Log.d("myLogs" , "hello world!!!!!!!!!!!!!!!!");
   //     startService();
  //      startService(new Intent(getApplicationContext(),MyService.class));

         //getPackageManager().getLaunchIntentForPackage("com.example.myapplication.MyService");
    //    startService(new Intent( this, MyService.class));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_BACKGROUND_LOCATION}, 101);
            }
            else{
                set_layout();
                Log.d("d", "started");
                startService();
            }
        }
        else{
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
            }
            else{
                set_layout();
                Log.d("d", "started");
                startService();
            }
        };

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                if (layout == 0) return;
                set_layout();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);

    };

    void startService(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            thread.activity.startForegroundService(new Intent(thread.activity, MyService.class));
        }
        else
        {
            thread.activity.startService(new Intent(thread.activity, MyService.class));
        }
    };



    static void stopService() {
        thread.activity.stopService(new Intent(thread.activity, MyService.class));
    };
}
/* android.text.style.ClickableSpan can solve your problem.

SpannableString ss = new SpannableString("Android is a Software stack");
ClickableSpan clickableSpan = new ClickableSpan() {
    @Override
    public void onClick(View textView) {
        startActivity(new Intent(MyActivity.this, NextActivity.class));
    }
    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.setUnderlineText(false);
    }
};
ss.setSpan(clickableSpan, 22, 27, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

TextView textView = (TextView) findViewById(R.id.hello);
textView.setText(ss);
textView.setMovementMethod(LinkMovementMethod.getInstance());
textView.setHighlightColor(Color.TRANSPARENT); */