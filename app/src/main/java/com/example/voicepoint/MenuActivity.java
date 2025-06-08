package com.example.voicepoint;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    TextView nameText, emailText;
    TextView homeText, sportsText, academicText, eventsText, devText, logoutText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        nameText = findViewById(R.id.nameText);
        emailText = findViewById(R.id.emailText);

        // Edit Profile button actions
        //ImageView editIcon = findViewById(R.id.editIcon);
        //TextView editProfileText = findViewById(R.id.editProfileText);

        //editIcon.setOnClickListener(v -> {
            //startActivity(new Intent(this, EditProfileActivity.class));
        //});

        //editProfileText.setOnClickListener(v -> {
            //startActivity(new Intent(this, EditProfileActivity.class));
        //});

        // Load username and email from shared preferences
        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String username = prefs.getString("username", "User");
        String email = prefs.getString("email", "user@example.com");

        nameText.setText(username);
        emailText.setText(email);

        // Navigation icons
        homeText = findViewById(R.id.homeText);
        sportsText = findViewById(R.id.sportsText);
        academicText = findViewById(R.id.academicText);
        eventsText = findViewById(R.id.eventsText);
        devText = findViewById(R.id.devText);
        logoutText = findViewById(R.id.logoutText);

        homeText.setOnClickListener(v -> startActivity(new Intent(this, NewsActivity.class)));

        sportsText.setOnClickListener(v -> {
            Intent i = new Intent(this, NewsDetailsActivity.class);
            i.putExtra("category", "sports");
            startActivity(i);
        });

        academicText.setOnClickListener(v -> {
            Intent i = new Intent(this, NewsDetailsActivity.class);
            i.putExtra("category", "academic");
            startActivity(i);
        });

        eventsText.setOnClickListener(v -> {
            Intent i = new Intent(this, NewsDetailsActivity.class);
            i.putExtra("category", "events");
            startActivity(i);
        });

        //devText.setOnClickListener(v -> startActivity(new Intent(this, DevInfoActivity.class)));

        logoutText.setOnClickListener(v -> {
            Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class)); // Back to splash
            finish();
        });
    }
}
