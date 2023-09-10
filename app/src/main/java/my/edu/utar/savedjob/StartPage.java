package my.edu.utar.savedjob;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        Button start=findViewById(R.id.getstart);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartPage.this, Login.class);
                startActivity(intent);
            }
        });

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