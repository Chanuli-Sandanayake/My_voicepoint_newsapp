package com.example.voicepoint;
import android.content.Intent;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {

    EditText usernameInput, passwordInput;
    Button loginButton;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        usernameInput = findViewById(R.id.usernameInput);
        passwordInput = findViewById(R.id.passwordInput);
        loginButton = findViewById(R.id.loginButton);
        //signUpTab = findViewById(R.id.signUpTab);
        DB = new DBHelper(this);

        loginButton.setOnClickListener(v -> {
            String username = usernameInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();

            if (username.equals("") || password.equals("")) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            } else {
                Boolean valid = DB.checkUsernamePassword(username, password);
                if (valid) {
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();

                    // Redirect to Home Screen (next activity)
                    //Intent intent = new Intent(SignInActivity.this, NewsActivity.class);
                    //startActivity(intent);
                    finish(); // optional: this finishes SignInActivity so user can’t go back


                } else {
                    Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //signUpTab.setOnClickListener(v -> {
            //Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
            //startActivity(intent);
            //finish(); // optional: close SignInActivity
        //});
    }
}
