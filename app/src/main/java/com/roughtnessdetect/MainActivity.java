package com.roughtnessdetect;


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;

public class MainActivity extends FragmentActivity {
    static public AlertDialog.Builder builder;
    static public Intent myAppSettings;
    static public Intent requestProvider;
    static public AlertDialog dialog_1;
    static public AlertDialog dialog_2;
    static public AlertDialog dialog_3;


    void initialize_dialogs(){
        builder = new AlertDialog.Builder(Main.activity);
        builder.setMessage(R.string.alert);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Main.kill();
            }
        });
        dialog_1 = builder.create();

        builder = new AlertDialog.Builder(Main.activity);
        builder.setMessage(R.string.request_permission);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Main.kill();
            }
        });
        builder.setNegativeButton(R.string.settings, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                startActivity(myAppSettings);
                Main.kill();
            }
        });
        dialog_2 = builder.create();

        builder = new AlertDialog.Builder(Main.activity);
        builder.setMessage(R.string.request_location);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        builder.setNegativeButton(R.string.settings, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                startActivity(requestProvider);
            }
        });
        dialog_3 = builder.create();

        myAppSettings = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + getPackageName()));
        myAppSettings.addCategory(Intent.CATEGORY_DEFAULT);
        myAppSettings.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        requestProvider = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
    };
/*
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 168) {
            if (PackageManager.PERMISSION_GRANTED == checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)){
                Main.set_layout();
                Main.startService();
            } else {
                Main.dialog_2.show();
            }
        }
    }
*/
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Main.activity = this;
        initialize_dialogs();

        if (!this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_SENSOR_GYROSCOPE)) {
            dialog_1.show();
        }

        if (!this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_SENSOR_ACCELEROMETER)) {
            dialog_1.show();
            return;
        }
        Main.getpackagename = new getpackagename() {
            @Override
            public String run() {
                return getPackageName();
            }
        };
        Main.getstring = new getstring() {
            @Override
            public String run(int id) {
                return getResources().getString( id );
            }
        };
        Main.getapplicationcontext = new getcontext() {
            @Override
            public Context run() {
                return getApplicationContext();
            }
        };
        Main.setcontextview = new setcontext() {
            @Override
            public void run(int i) {
                setContentView(i);
            }
        };
        Main.findviewbyid = new findview() {
            @Override
            public View run(int i) {
                return findViewById(i);
            }
        };

        Main.mdformat = new SimpleDateFormat("HH:mm:ss");

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getOnBackPressedDispatcher().addCallback(this, Main.callback);

        Main.buildsdk_O = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O);
        String [] permissions = Main.buildsdk_O ? new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION} : new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION};

        Log.d("hello", "hi");
        if (PackageManager.PERMISSION_GRANTED != checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)){
            requestPermissions(permissions, 101);
        }
        else{
            Main.set_layout();
            Main.startService();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0) {
            if (PackageManager.PERMISSION_GRANTED == checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)) {
                Main.set_layout();
                Main.startService();
            } else {
                dialog_2.show();
            }
        }
    };
}

interface getcontext {
    Context run();
};
interface setcontext {
    void run(int i);
};
interface findview {
    View run(int i);
};
interface getstring{
    String run(int id);
};
interface getpackagename{
    String run();
}