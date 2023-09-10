package my.edu.utar.savedjob;

import android.app.DatePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class profile extends AppCompatActivity {

    EditText etname, etabout, etbirthday, etlocation, etspec, etjob, etphone, etemail;
    ImageView ivIns, ivFB, ivTwitter;
    ImageButton imgBtn;
    Button button;
    DocumentReference documentReference;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Calendar calendar;
    public static final String LOGOUT_ACTION = "my.edu.utar.jobsearchapp.LOGOUT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        etname = findViewById(R.id.textView);
        etabout = findViewById(R.id.AboutEdit);
        etbirthday = findViewById(R.id.birth_dateEdit);
        etlocation = findViewById(R.id.locationEdit);
        etspec = findViewById(R.id.Spec_Edit);
        etjob = findViewById(R.id.job_YearEdit);
        etphone = findViewById(R.id.PhoneEdit);
        etemail = findViewById(R.id.EmailEdit);
        button = findViewById(R.id.saved_btn);
        imgBtn = (ImageButton)findViewById(R.id.logout);
        ivIns = findViewById(R.id.ig_icon);
        ivFB = findViewById(R.id.fb_icon);
        ivTwitter = findViewById(R.id.twitter_icon);

        Button home_btn=findViewById(R.id.home_btn);
        Button search_btn=findViewById(R.id.search_btn);
        Button saved_btn=findViewById(R.id.saved_btn);
        Button chat_btn=findViewById(R.id.chat_btn);
        Button profile_btn=findViewById(R.id.profile_btn);


        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profile.this, home.class);
                startActivity(intent);
            }
        });

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profile.this, MainActivity.class);
                startActivity(intent);
            }
        });

        saved_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profile.this, saved.class);
                startActivity(intent);
            }
        });

        chat_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profile.this, chat.class);
                startActivity(intent);
            }
        });

        profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recreate();
            }
        });

        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);

                updateCalendar();
            }
            private void updateCalendar(){
                String Format = "dd/MM/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(Format, Locale.US);
                etbirthday.setText(sdf.format(calendar.getTime()));
            }
        };

        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Send a broadcast intent to log out all activities
                Intent logoutIntent = new Intent(LOGOUT_ACTION);
                sendBroadcast(logoutIntent);

                // Close the current activity
                finish();
            }
        });

        etbirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(profile.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        ivFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //initialise link and package
                String sAppLink = "fb://page/237564710351658";
                String sPackage = "com.facebook.katana";
                String sWebLink = "https://www.facebook.com";
                //create method
                openLink(sAppLink, sPackage, sWebLink);
            }
        });

        ivIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //initialise link and package
                String sAppLink = "https://www.instagram.com";
                String sPackage = "com.instagram.android";
                //Call method
                openLink(sAppLink, sPackage, sAppLink);
            }
        });

        ivTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //initialise link and package
                String sAppLink = "twitter://user?screen_name=AndroidCoding_";
                String sPackage = "com.twitter.android";
                String sWebLink = "https://www.twitter.com";
                //Call method
                openLink(sAppLink, sPackage, sWebLink);
            }
        });


        documentReference = db.collection("user_profile").document();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Call the method to upload data to Firebase when the button is clicked
                uploadData();
            }
        });


    }

    private void openLink(String sAppLink, String sPackage, String sWebLink) {
        //Try Catch
        try {
            //When application is installed
            //Initialise uri
            Uri uri = Uri.parse(sAppLink);
            //Initialise intent
            Intent intent = new Intent(Intent.ACTION_VIEW);
            //Set data
            intent.setData(uri);
            //Set package
            intent.setPackage(sPackage);
            //Set flag
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //Start activity
            startActivity(intent);
        } catch (ActivityNotFoundException activityNotFoundException) {
            //Open link in browser
            //Initialise uri
            Uri uri = Uri.parse(sWebLink);
            //Initialise intent
            Intent intent = new Intent(Intent.ACTION_VIEW);
            //Set data
            intent.setData(uri);
            //Set flag
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //Start activity
            startActivity(intent);
        }
    }
    public void uploadData() {
        String name = etname.getText().toString();
        String about = etabout.getText().toString();
        String birthday = etbirthday.getText().toString();
        String location = etlocation.getText().toString();
        String spec = etspec.getText().toString();
        String jobY = etjob.getText().toString();
        String phone = etphone.getText().toString();
        String email = etemail.getText().toString();

        if (!TextUtils.isEmpty(name) || !TextUtils.isEmpty(about) || !TextUtils.isEmpty(birthday)
                || !TextUtils.isEmpty(location) || !TextUtils.isEmpty(spec) || !TextUtils.isEmpty(jobY)
                || !TextUtils.isEmpty(phone) || !TextUtils.isEmpty(email)) {

            Map<String, String> profile = new HashMap<>();
            profile.put("name", name);
            profile.put("about", about);
            profile.put("birthday", birthday);
            profile.put("location", location);
            profile.put("specialisation", spec);
            profile.put("jobYear", jobY);
            profile.put("phoneNum", phone);
            profile.put("email", email);

            documentReference.set(profile).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    // Data has been successfully uploaded to Firebase
                    Toast.makeText(profile.this, "Profile information uploaded successfully", Toast.LENGTH_SHORT).show();
                }

            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(Exception e) {
                    //If failure to update
                    Toast.makeText(profile.this, "Failed to upload profile information: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(this, "Please complete your profile information", Toast.LENGTH_SHORT).show();
        }
    }


}