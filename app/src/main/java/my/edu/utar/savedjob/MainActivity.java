package my.edu.utar.savedjob;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private ImageButton businessButton, hotelButton, constructionButton, itButton, engineeringButton, healthcareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button home_btn=findViewById(R.id.home_btn);
        Button search_btn=findViewById(R.id.search_btn);
        Button saved_btn=findViewById(R.id.saved_btn);
        Button chat_btn=findViewById(R.id.chat_btn);
        Button profile_btn=findViewById(R.id.profile_btn);

        // Initialize category buttons
        businessButton = findViewById(R.id.business_btn);
        hotelButton = findViewById(R.id.hotel_btn);
        constructionButton = findViewById(R.id.construction_btn);
        itButton = findViewById(R.id.it_btn);
        engineeringButton = findViewById(R.id.engineering_btn);
        healthcareButton = findViewById(R.id.healthcare_btn);

        // Set click listeners for category buttons
        businessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to BusinessActivity
                Intent intent = new Intent(MainActivity.this, Business.class);
                startActivity(intent);
            }
        });

        hotelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to HotelActivity
                Intent intent = new Intent(MainActivity.this, Hotel.class);
                startActivity(intent);
            }
        });

        constructionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to ConstructionActivity
                Intent intent = new Intent(MainActivity.this, Construction.class);
                startActivity(intent);
            }
        });

        itButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to ITActivity
                Intent intent = new Intent(MainActivity.this, IT.class);
                startActivity(intent);
            }
        });

        engineeringButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to EngineeringActivity
                Intent intent = new Intent(MainActivity.this, Engineering.class);
                startActivity(intent);
            }
        });

        healthcareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to HealthcareActivity
                Intent intent = new Intent(MainActivity.this, Healthcare.class);
                startActivity(intent);
            }
        });

        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, home.class);
                startActivity(intent);
            }
        });

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                recreate();
            }
        });

        saved_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, saved.class);
                startActivity(intent);
            }
        });

        chat_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, chat.class);
                startActivity(intent);
            }
        });

        profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, profile.class);
                startActivity(intent);
            }
        });
    }
}