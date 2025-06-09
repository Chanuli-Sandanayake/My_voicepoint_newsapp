package com.example.voicepoint;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditProfileActivity extends AppCompatActivity {

    EditText editUsername, editEmail;
    Button saveButton;
    ImageButton backButton;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        editUsername = findViewById(R.id.editUsername);
        editEmail = findViewById(R.id.editEmail);
        saveButton = findViewById(R.id.saveButton);
        backButton = findViewById(R.id.backButton);
        DB = new DBHelper(this);

        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String oldUsername = prefs.getString("username", "");
        String oldEmail = prefs.getString("email", "");

        // Prefill fields
        editUsername.setText(oldUsername);
        editEmail.setText(oldEmail);

        saveButton.setOnClickListener(v -> {
            String newUsername = editUsername.getText().toString().trim();
            String newEmail = editEmail.getText().toString().trim();

            if (newUsername.isEmpty() || newEmail.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean updated = DB.updateUser(oldUsername, newUsername, newEmail);
            if (updated) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("username", newUsername);
                editor.putString("email", newEmail);
                editor.apply();

                Toast.makeText(this, "Profile updated", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MenuActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Update failed", Toast.LENGTH_SHORT).show();
            }
        });

        backButton.setOnClickListener(v -> {
            startActivity(new Intent(this, MenuActivity.class));
            finish();
        });
    }
}
