package com.example.voicepoint;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class DevInfoActivity extends AppCompatActivity {

    Button exitButton;
    ImageView profileImage;
    ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dev_info);

        profileImage = findViewById(R.id.profileImage);
        exitButton = findViewById(R.id.exitButton);
        backButton = findViewById(R.id.backButton);

        Glide.with(this)
                .load(R.drawable.chanuli)
                .apply(RequestOptions.circleCropTransform())
                .into(profileImage);





        exitButton.setOnClickListener(v -> {
            startActivity(new Intent(DevInfoActivity.this, NewsActivity.class));
            finish();
        });

        backButton.setOnClickListener(v -> {
            startActivity(new Intent(DevInfoActivity.this, MenuActivity.class));
            finish();
        });
    }
}
