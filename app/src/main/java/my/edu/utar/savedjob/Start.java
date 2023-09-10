package my.edu.utar.savedjob;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

public class Start extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //profile->SplashScreen.class
                Intent intent = new Intent(Start.this, SplashScreen.class);
                startActivity(intent);
                finish();
            }
        }, 2000);

        // Register a BroadcastReceiver to listen for the logout intent
        IntentFilter intentFilter = new IntentFilter(profile.LOGOUT_ACTION);
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // Log out and finish the activity
                finish();
            }
        };
        registerReceiver(receiver, intentFilter);


    }
}
